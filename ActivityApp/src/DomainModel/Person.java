package DomainModel;


import DataMappers.DataMapper;
import DataMappers.MyThreadLocal;

/**
 * @author josh
 *
 */
public class Person extends DomainObject
{
	private String username;
	private String password;
	private String displayName;
	private int ID; // If ID is not auto generated
	
	FriendsList myFriends;
	PendingFriendsList myPendingFriends;
	
	/**
	 * Constructor
	 */
	public Person() 
	{
		myFriends = new FriendsList();
		myPendingFriends = new PendingFriendsList();
	}
	
	public Person(String name, String password, String username, int id)
	{
		displayName = name;
		this.password = password;
		this.username = username;
		ID = id;
		myFriends = new FriendsList();
		myPendingFriends = new PendingFriendsList();
	}
	
	/**
	 * Constructor with parameters
	 * @param myFriends
	 * @param myPendingFriends 
	 */
	public Person(FriendsList myFriends, PendingFriendsList myPendingFriends) 
	{
		this.myFriends = myFriends;
		this.myPendingFriends = myPendingFriends;
	}
	
	/**
	 * @return username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return displayName
	 */
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * @param displayName
	 */
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	
	/**
	 * Static find method for persisting a person
	 * @param username
	 * @param password 
	 * @return Person
	 */
	public static Person findPerson(String username, String password) 
	{
		DataMapper pdm = MyThreadLocal.get(); 
		return pdm.findPerson(username, password);
	}

	/**
	 * @param ID
	 * @return Person
	 */
	public static Person findPerson(int ID) 
	{
		DataMapper pdm = MyThreadLocal.get();
		return pdm.findPerson(ID);
	}
	
	/**
	 * 
	 * @param userNameOfRequester 
	 * Find the person based on the Username
	 * @return the person
	 */
	private Person findPerson(String userNameOfRequester) {
		DataMapper  dm = MyThreadLocal.get();
		return dm.findPerson(userNameOfRequester);
	}

	/**
	 * 
	 * @param userIDOfRequestee is the person that is being as to be someone's friend
	 * @param userNameOfRequester is the person asking to be someone's friend
	 * 
	 * Find both people, add each other to their friends list, mark person as dirty.
	 */
	public void acceptFriendRequest(int userIDOfRequestee, String userNameOfRequester)
	{
		Person friend = new Person();
		friend = findPerson(userIDOfRequestee);
		Person requester = new Person();
		requester = findPerson(userNameOfRequester);
		friend.myFriends.add(requester);
		requester.myFriends.add(friend);
		
		this.markDirty(friend);
		this.markDirty(requester);
	}
	
	/**
	 * @return the array list of friends for that person
	 */
	public FriendsList findFriends()
	{
		DataMapper pdm = MyThreadLocal.get();
		return pdm.findFriends(ID);
	}
	
	public PendingFriendsList findPendingFriends(int ID) {
		DataMapper pdm = MyThreadLocal.get();
		return pdm.findPendingFriends(ID);
	}
	
	/**
	 * @return get the number of friends
	 */
	public int getNumberOfFriends()
	{
		int size = myFriends.getFriendList().size();
		return size;
	}
	
	public void setID(int ID) 
	{
		this.ID = ID;
	}
	
	public int getID()
	{
		return ID;
	}
}
