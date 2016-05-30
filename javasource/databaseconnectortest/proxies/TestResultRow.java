// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package databaseconnectortest.proxies;

public class TestResultRow
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject testResultRowMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "DatabaseConnectorTest.TestResultRow";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		name("name"),
		number("number");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public TestResultRow(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "DatabaseConnectorTest.TestResultRow"));
	}

	protected TestResultRow(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject testResultRowMendixObject)
	{
		if (testResultRowMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("DatabaseConnectorTest.TestResultRow", testResultRowMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a DatabaseConnectorTest.TestResultRow");

		this.testResultRowMendixObject = testResultRowMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'TestResultRow.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static databaseconnectortest.proxies.TestResultRow initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return databaseconnectortest.proxies.TestResultRow.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.getSudoContext() can be used to obtain sudo access).
	 */
	public static databaseconnectortest.proxies.TestResultRow initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new databaseconnectortest.proxies.TestResultRow(context, mendixObject);
	}

	public static databaseconnectortest.proxies.TestResultRow load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return databaseconnectortest.proxies.TestResultRow.initialize(context, mendixObject);
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of name
	 */
	public final String getname()
	{
		return getname(getContext());
	}

	/**
	 * @param context
	 * @return value of name
	 */
	public final String getname(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (String) getMendixObject().getValue(context, MemberNames.name.toString());
	}

	/**
	 * Set value of name
	 * @param name
	 */
	public final void setname(String name)
	{
		setname(getContext(), name);
	}

	/**
	 * Set value of name
	 * @param context
	 * @param name
	 */
	public final void setname(com.mendix.systemwideinterfaces.core.IContext context, String name)
	{
		getMendixObject().setValue(context, MemberNames.name.toString(), name);
	}

	/**
	 * @return value of number
	 */
	public final Integer getnumber()
	{
		return getnumber(getContext());
	}

	/**
	 * @param context
	 * @return value of number
	 */
	public final Integer getnumber(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (Integer) getMendixObject().getValue(context, MemberNames.number.toString());
	}

	/**
	 * Set value of number
	 * @param number
	 */
	public final void setnumber(Integer number)
	{
		setnumber(getContext(), number);
	}

	/**
	 * Set value of number
	 * @param context
	 * @param number
	 */
	public final void setnumber(com.mendix.systemwideinterfaces.core.IContext context, Integer number)
	{
		getMendixObject().setValue(context, MemberNames.number.toString(), number);
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return testResultRowMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final databaseconnectortest.proxies.TestResultRow that = (databaseconnectortest.proxies.TestResultRow) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "DatabaseConnectorTest.TestResultRow";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
