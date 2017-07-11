package databaseconnector.impl;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.mendix.core.objectmanagement.member.MendixHashString;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaPrimitive.PrimitiveType;

import databaseconnector.interfaces.ConnectionManager;
import databaseconnector.interfaces.ObjectInstantiator;

/**
 * JdbcConnector implements the execute query (and execute statement) functionality, and returns a {@link Stream} of {@link IMendixObject}s.
 */
public class JdbcConnector {
  private final ILogNode logNode;
  private final ObjectInstantiator objectInstantiator;
  private final ConnectionManager connectionManager;

  public JdbcConnector(final ILogNode logNode, final ObjectInstantiator objectInstantiator, final ConnectionManager connectionManager) {
    this.logNode = logNode;
    this.objectInstantiator = objectInstantiator;
    this.connectionManager = connectionManager;
  }

  public JdbcConnector(final ILogNode logNode) {
    this(logNode, new ObjectInstantiatorImpl(), ConnectionManagerSingleton.getInstance());
  }

  public Stream<IMendixObject> executeQuery(final String jdbcUrl, final String userName, final String password, final IMetaObject metaObject, final String sql,
      final IContext context) throws SQLException {
    String entityName = metaObject.getName();
    
    Function<Map<String, Optional<Object>>, IMendixObject> toMendixObject = columns -> {
      IMendixObject obj = objectInstantiator.instantiate(context, entityName);
      
      BiConsumer<String, Optional<Object>> setMemberValue = (name, value) -> {
        PrimitiveType type = metaObject.getMetaPrimitive(name).getType();
        // convert to suitable value (different for Binary type)
        Function<Object, Object> toSuitableValue = toSuitableValue(type);
        // for Boolean type, convert null to false
        Supplier<Object> defaultValue = () -> type == PrimitiveType.Boolean ? Boolean.FALSE : null;
        // apply two functions declared above
        Object convertedValue = value.map(toSuitableValue).orElseGet(defaultValue);
        // update object with converted value
        if (type == PrimitiveType.HashString)
          ((MendixHashString) obj.getMember(context, name)).setInitialHash((String) convertedValue);
        else
          obj.setValue(context, name, convertedValue);
      };
      
      columns.forEach(setMemberValue);
      logNode.trace("Instantiated object: " + obj);
      return obj;
    };

    return executeQuery(jdbcUrl, userName, password, metaObject, sql).map(toMendixObject);
  }

  private Function<Object, Object> toSuitableValue(final PrimitiveType type) {
    return v -> type == PrimitiveType.Binary ? new ByteArrayInputStream((byte[]) v) : v;
  }

  private Stream<Map<String, Optional<Object>>> executeQuery(final String jdbcUrl, final String userName, final String password, final IMetaObject metaObject, final String sql) throws SQLException {
    logNode.trace(String.format("executeQuery: %s, %s, %s", jdbcUrl, userName, sql));

    try (Connection connection = connectionManager.getConnection(jdbcUrl, userName, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
    	addParameters(preparedStatement);
    	try (ResultSet resultSet = preparedStatement.executeQuery()) {
    		ResultSetReader resultSetReader = new ResultSetReader(resultSet, metaObject);
		      reset();
		
		      return resultSetReader.readAll().stream();
    	}
    }
  }

  public long executeStatement(final String jdbcUrl, final String userName, final String password, final String sql) throws SQLException {
    logNode.trace(String.format("executeStatement: %s, %s, %s", jdbcUrl, userName, sql));

    try (Connection connection = connectionManager.getConnection(jdbcUrl, userName, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    	addParameters(preparedStatement);
      long result = preparedStatement.executeUpdate();
      reset();
      return result;
    }
  }
  
  // Support for parameters
  static ThreadLocal<Map<Integer, Object>> nextParameters = new ThreadLocal<Map<Integer, Object>>();
  
  private void addParameters(PreparedStatement preparedStatement) throws SQLException {
	  if (nextParameters.get() != null && !nextParameters.get().isEmpty()) {
		  int index = 1;
		  while(nextParameters.get().containsKey(index)) {
			  Object value = nextParameters.get().get(index);
			  if (value == null) {
				  preparedStatement.setString(index, null);
			  } else if (value instanceof String) {
				  preparedStatement.setString(index, (String) value);
			  } else if (value instanceof Boolean) {
				  preparedStatement.setBoolean(index,  (Boolean) value);
			  } else if (value instanceof Date) {
				  preparedStatement.setDate(index, new java.sql.Date(((Date)value).getTime())); 
			  } else if (value instanceof Float) {
				  preparedStatement.setFloat(index, (Float) value);
			  } else if (value instanceof BigDecimal) {
				  preparedStatement.setBigDecimal(index, (BigDecimal) value);
			  } else if (value instanceof Double) {
				  preparedStatement.setDouble(index, (Double) value);
			  } else if (value instanceof Long) {
				  preparedStatement.setLong(index, (Long) value);
			  } else {
				  logNode.error("Unknown type: " + value.toString());
			  }
			  
			  index++;
		  }
	  }
  }
  
  public static Map<Integer, Object> getNextParameters() {
		if (nextParameters.get() == null) {
			nextParameters.set(new HashMap<Integer, Object>());
		}
		return nextParameters.get();
	}
	
	public static void reset() {
		nextParameters = new ThreadLocal<Map<Integer, Object>>();
	}
	
	public static void addParameter(Integer index, Object value) {
		getNextParameters().put(index, value);
	}
}
