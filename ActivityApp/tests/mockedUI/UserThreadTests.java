package mockedUI;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import mockedUI.UserThread;

import org.junit.Test;

import domainLogic.CommandToAcceptFriendRequest;
import domainLogic.CommandToCreateUser;
import domainLogic.CommandToGetPendingIncomingFriendList;
import domainLogic.CommandToPersistChanges;
import domainLogic.CommandToSelectUser;
import domainLogic.Person;
import domainLogic.SelectedPerson;
import domainLogic.UnitOfWork;

/**
 * Tests related to the processing of commands by the UserThread class.  Note that many of these tests use
 * existing commands as examples of commands with a particular structure
 * @author Merlin
 *
 */
public class UserThreadTests
{

	@Test
	public void canSeparateInstructionFromResults() throws FileNotFoundException
	{
		UserThread t = new UserThread("FriendRequestTest");
		String[]parts = t.splitInstruction("PendingOutgoingFriendList; henry");
		assertEquals(2, parts.length);
		assertEquals("PendingOutgoingFriendList", parts[0]);
		assertEquals("henry", parts[1]);
		
		parts = t.splitInstruction("AcceptFriendRequest henry");
		assertEquals(1, parts.length);
		assertEquals("AcceptFriendRequest henry", parts[0]);
	}
	@Test
	public void testCommandType() throws FileNotFoundException
	{
		UserThread t = new UserThread("FriendRequestTest");
		assertEquals(CommandToAcceptFriendRequest.class, t.getCommandClass("CommandToAcceptFriendRequest"));
		
	}
	
	@Test
	public void buildNoParameterCommand() throws FileNotFoundException
	{
		UserThread t = new UserThread("FriendRequestTest");
		CommandToPersistChanges command = (CommandToPersistChanges) t.buildCommand("CommandToPersistChanges");
		assertEquals(CommandToPersistChanges.class, command.getClass());
	}

	@Test
	public void buildOneParameterCommand() throws FileNotFoundException 
	{
		UserThread t = new UserThread("FriendRequestTest");
		CommandToGetPendingIncomingFriendList command = (CommandToGetPendingIncomingFriendList) t.buildCommand("CommandToGetPendingIncomingFriendList 8");
		assertEquals(CommandToGetPendingIncomingFriendList.class, command.getClass());
		assertEquals(8, command.getUserID());
	}
	
	@Test
	public void buildTwoParameterCommand() throws FileNotFoundException 
	{
		UserThread t = new UserThread("FriendRequestTest");
		CommandToAcceptFriendRequest command = (CommandToAcceptFriendRequest) t.buildCommand("CommandToAcceptFriendRequest 3 Elizabeth");
		assertEquals(CommandToAcceptFriendRequest.class, command.getClass());
		assertEquals(3, command.getUserIDOfRequestee());
		assertEquals("Elizabeth", command.getUserNameOfRequester());
	}
	
	@Test
	public void buildTwoStringParametersCommand() throws FileNotFoundException
	{
		UserThread t = new UserThread("FriendRequestTest");
		CommandToSelectUser command = (CommandToSelectUser) t.buildCommand("CommandToSelectUser Elizabeth secret");
		assertEquals(CommandToSelectUser.class, command.getClass());
		assertEquals("Elizabeth", command.getUserName());
		assertEquals("secret", command.getPassword());
	}
	@Test
	public void buildThreeParameterCommand() throws FileNotFoundException 
	{
		UserThread t = new UserThread("FriendRequestTest");
		CommandToCreateUser command = (CommandToCreateUser) t.buildCommand("CommandToCreateUser uName pw Elizabeth");
		assertEquals(CommandToCreateUser.class, command.getClass());
		assertEquals("uName", command.getUserName());
		assertEquals("pw", command.getPassword());
		assertEquals("Elizabeth", command.getDisplayName());
	}
	
	@Test
	public void oneInstructionWithResult() throws FileNotFoundException
	{
		UserThread t = new UserThread("FriendRequestTest");
		assertTrue( t.executeInstruction("MockCommand 3 mymessage; 3 mymessage worked"));
	}
	
	@Test
	public void canPassUserID() throws FileNotFoundException
	{
		UserThread t = new UserThread("FriendRequestTest");
		t.setCurrentUserID(-1);
		assertTrue( t.executeInstruction("MockCommand <userID> mymessage; -1 mymessage worked"));
	}
	
}
