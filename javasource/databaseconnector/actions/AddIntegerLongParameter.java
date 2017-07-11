// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package databaseconnector.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import databaseconnector.impl.JdbcConnector;

public class AddIntegerLongParameter extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.Long index;
	private java.lang.Long value;

	public AddIntegerLongParameter(IContext context, java.lang.Long index, java.lang.Long value)
	{
		super(context);
		this.index = index;
		this.value = value;
	}

	@Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		JdbcConnector.addParameter(index.intValue(), value);
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "AddIntegerLongParameter";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
