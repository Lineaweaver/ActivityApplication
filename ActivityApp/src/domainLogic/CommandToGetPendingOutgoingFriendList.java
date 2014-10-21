package domainLogic;
import java.util.ArrayList;

/**
 * Cause the list of pending friend requests from this user to other users to be fetched
 * from the domain model (may or may not cause reading from the DB depending on
 * the state of the domain model)
 * 
 * @author merlin
 *
 */
public class CommandToGetPendingOutgoingFriendList implements Command
{

	private int userID;
	private Person person = new Person();

	/**
	 * The userID of the current user
	 * 
	 * @param userID
	 *            unique
	 */
	public CommandToGetPendingOutgoingFriendList(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		person.PendingOutgoingFriendList(userID);

	}

	/**
	 * A list of the friends associated with the given user
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public ArrayList<Person> getResult()
	{
		ArrayList<Person> result = person.getOutgoingPendingFriendList();
		return result;
	}

}
