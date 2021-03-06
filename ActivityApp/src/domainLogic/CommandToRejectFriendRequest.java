package domainLogic;

import java.sql.SQLException;

/**
 * Reject a friend request from one user to another
 * @author merlin
 *
 */
public class CommandToRejectFriendRequest implements Command
{

	private int userIDOfRequestee;
	private String userNameOfRequester;


	/**
	 * 
	 * @param userIDOfRequestee the User ID of the user rejecting the request
	 * @param userNameOfRequester the User Name of the user who initiated the friend request
	 */
	public CommandToRejectFriendRequest(int userIDOfRequestee, String userNameOfRequester)
	{
		this.userIDOfRequestee = userIDOfRequestee;
		this.userNameOfRequester = userNameOfRequester;
		
	}
	
	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		Person person = new Person();
		try {
			person.rejectFriendRequest(userIDOfRequestee, userNameOfRequester);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	/**
	 * Nothing needs to be retrieved from this command
	 * @see Command#getResult()
	 */
	@Override
	public Object getResult()
	{
		return null;
	}

}
