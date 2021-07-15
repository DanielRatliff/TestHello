package com.example.userTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.dao.FileIO;
import com.example.exceptions.UserDoesNotExistException;
import com.example.models.User;
import com.example.services.UserService;

public class UserServiceTest {
	private User testUser1 = new User("test","User","testPass");
	private User testUser2 = new User("test","User","Pass");
	private UserService uServ = new UserService("test.txt");
	private FileIO<User> io = new FileIO<User>("test.txt");
	
	@Before
	public void clearTestFile() {
		if(Files.exists(Paths.get("test.txt"))) {
			System.out.println("clearing test file");
			try {
				Files.delete(Paths.get("test.txt"));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void testEmptyUserList() {
		List<User> userList = uServ.getAllUsers();
		assertEquals("list should have no users",0,userList.size());
		
	}
	@Test
	public void testCreateNewUser() {
		User rick = uServ.signUp("Rick", "Sanchez","ilovemorty");
		List<User> userList = uServ.getAllUsers();
		assertNotNull(rick);
		assertEquals(rick.getUsername(),userList.get(0).getUsername());
		assertEquals(1,userList.size());
	}
	
	@Test
	public void testCreateMutliUsers() {
		User morty = uServ.signUp("morty", "Smith","ihaterick");
		User rick = uServ.signUp("Rick", "Sanchez","ilovemorty");
		List<User> userList = uServ.getAllUsers();
		
		assertNotNull(morty);
		assertNotNull(rick);
		assertEquals(2,userList.size());
		
	}
	@Test(expected=UserDoesNotExistException.class)
	public void testUserDoesNotExist() {
		User rick = uServ.login("Rick","ilovemorty");
	}
}
