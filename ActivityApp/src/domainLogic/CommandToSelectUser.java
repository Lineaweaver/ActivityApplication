package domainLogic;

import java.sql.SQLException;

/**
 * Retrieve a specified user from the database into the domain model
 */
public class CommandToSelectUser implements Command
{
	private String userName;
	private String password;
	private Person person = new Person();

	/**
	 * @param userName
	 *            the username from the user's credentials
	 * @param password
	 *            the password from the user's credentials
	 */
	public CommandToSelectUser(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Create the domain model object for the specified user (retrieve that user
	 * from the database)
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		try {
			person.SelectUser(userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void testExecute()
//	{
//		person.testSelectUser(userName, password);
//	}

	/**
	 * Get the password that was given as part of the user's credentials
	 * 
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * This should return the appropriate Person object from the domain model.
	 * Null if the credentials of the user were invalid
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public Person getResult()
	{
		Person person;
		person =  SelectedPerson.getSelectedPerson();
		return person;
	}
	
//	public Person getTestResult()
//	{
//		Person person;
//		person =  SelectedPerson.getSelectedPerson();
//		return person;
//	}

	/**
	 * Get the user name that was given as part of the user's credentials
	 * 
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}

}
