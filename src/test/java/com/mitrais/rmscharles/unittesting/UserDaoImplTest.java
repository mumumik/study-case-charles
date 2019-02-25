package com.mitrais.rmscharles.unittesting;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mitrais.rmscharles.dao.UserDao;
import com.mitrais.rmscharles.model.User;

public class UserDaoImplTest {
	
	private UserDao userDaoMock = mock(UserDao.class);
	
	@Test
	public void findById_ReturnsUserDataById() {
		
		// SETUP
        when(userDaoMock.find((long)1)).thenReturn(
                        new User((long)1, "User 1", "1234")
        );
        
        // ACTION
        User theUser = userDaoMock.find((long)1);
        
        // ASSERTION
        assertEquals((long)1, (long)theUser.getId());
        assertEquals("User 1", theUser.getUserName());
        assertEquals("1234", theUser.getPassword());
	}
	
	@Test
	public void findAll_ReturnsAllUser() {
		
		// SETUP
		when(userDaoMock.findAll()).thenReturn(
				Arrays.asList(
					new User((long)1,"User 1","1234"),
					new User((long)2,"User 2","1234")
				)
		);
		
		// ACTION
		List<User> theUser = userDaoMock.findAll();
		
		// ASSERTION
		assertNotNull(theUser);
	}
	
	@Test
	public void findAll_ReturnsNull() {
		
		// SETUP
		when(userDaoMock.findAll()).thenReturn(null);
		
		// ACTION
		List<User> theUser = userDaoMock.findAll();
		
		// ASSERTION
		assertNull(theUser);
	}
	
	@Test
	public void findByUsername_ReturnsUserDataByUsername() {
		
		// SETUP
		when(userDaoMock.findByUserName("charles")).thenReturn(
				Optional.of(new User((long)1, "charles", "1234"))
		        );
		
		// ACTION
		Optional<User> actual = userDaoMock.findByUserName("charles");
		
		// ASSERTION
		User theUser = actual.get();
		assertEquals((long)1, (long)theUser.getId());
        assertEquals("charles", theUser.getUserName());
        assertEquals("1234", theUser.getPassword());
        
	}
	
	@Test
	public void findByUsername_ReturnsNull() {
		
		// SETUP
		when(userDaoMock.findByUserName("charles")).thenReturn(null);
		
		// ACTION
		Optional<User> actual = userDaoMock.findByUserName("charles");
		
		// ASSERTION
		try {
			User theUser = actual.get();
			assertNull(theUser);
		} 
		catch(NullPointerException npe) {
			// success
		}
		
        
	}
	
	@Test
	public void save_ReturnsTrue() {
		
		// SETUP
		User theUser = new User("charlessss","1234");
		
		// ACTION
		boolean actual = userDaoMock.save(theUser);
		
		// ASSERTION
		assertEquals(true, actual);
		
	}

}
