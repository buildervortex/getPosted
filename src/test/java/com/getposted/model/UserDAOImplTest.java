package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore()
public class UserDAOImplTest {

    private static User user = new User();
    private static UserDAOImpl userDAOImpl = new UserDAOImpl();

    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }
    @Test
    public void testDelete() throws SQLException{
        user.setId(50);
        user.setEmail("testDelete@example.com");
        user.setPassword("testDeletePassword");
        user.setUserName("testDeleteUserName");
        Date date = new java.sql.Date(new java.util.Date().getTime());
        user.setDob(date);
        user.setFirstName("testDeleteFirstName");
        user.setMiddleName("testDeletetMiddleName");
        user.setLastName("testDeleteLastName");
        userDAOImpl.insert(user);
        user = userDAOImpl.get(50);
        userDAOImpl.delete(user);
        assertNull(userDAOImpl.get(50));
    }

    @Test
    public void testGet() throws SQLException{
        assertNull(userDAOImpl.get(0));
        user = userDAOImpl.get(1);
        assertTrue(user.getId() ==  1);
        assertEquals(user.getEmail(),"user1@example.com");
        assertEquals(user.getPassword(),"password1");
        assertEquals(user.getUserName(),"user1");
        assertEquals(user.getDob().toString(),"1995-01-15");
        assertEquals(user.getFirstName(),"John");
        assertEquals(user.getMiddleName(),null);
        assertEquals(user.getLastName(),"Doe");
    }

    @Test
    public void testGetAll() throws SQLException{
        List<User> users = userDAOImpl.getAll();
        assertTrue(users.size() >= 20);
        
        for(User user: users){
            assertTrue(user.getEmail().length()>2);
            assertTrue(user.getPassword().length() >2);
            assertTrue(user.getUserName().length()>2);
            assertNotNull(user.getDob());
            assertTrue(user.getFirstName().length() >2);
            assertTrue(user.getLastName().length()>2);
        }
    }

    @Test
    public void testInsert() throws SQLException{
        // id,email, password, userName, dob, firstName, middleName, lastName
        user.setId(24);
        user.setEmail("testInsert@example.com");
        user.setPassword("testInsertPassword");
        user.setUserName("testInsertUserName");
        Date date = new java.sql.Date(new java.util.Date().getTime());
        user.setDob(date);
        user.setFirstName("testInsertFirstName");
        user.setMiddleName("testInsertMiddleName");
        user.setLastName("testInsertLastName");
        userDAOImpl.insert(user);
        user = userDAOImpl.get(24);
        assertEquals(user.getEmail(),"testInsert@example.com");
        assertEquals(user.getPassword(),"testInsertPassword");
        assertEquals(user.getUserName(),"testInsertUserName");
        assertEquals(user.getDob().toString(),date.toString());
        assertEquals(user.getFirstName(),"testInsertFirstName");
        assertEquals(user.getMiddleName(),"testInsertMiddleName");
        assertEquals(user.getLastName(),"testInsertLastName");
        assertTrue(user.getId() == 24);
    }

    @Test
    public void testUpdate() throws SQLException{
        user = userDAOImpl.get(10);
        user.setEmail("testUpdate@example.com");
        user.setPassword("testUpdatePassword");
        user.setUserName("testUpdateUserName");
        Date date = new java.sql.Date(new java.util.Date().getTime());
        user.setDob(date);
        user.setFirstName("testUpdateFirstName");
        user.setMiddleName("testUpdateMiddleName");
        user.setLastName("testUpdateLastName");
        userDAOImpl.update(user);
        user = userDAOImpl.get(10);
        assertEquals(user.getEmail(),"testUpdate@example.com");
        assertEquals(user.getPassword(),"testUpdatePassword");
        assertEquals(user.getUserName(),"testUpdateUserName");
        assertEquals(user.getDob().toString(),date.toString());
        assertEquals(user.getFirstName(),"testUpdateFirstName");
        assertEquals(user.getMiddleName(),"testUpdateMiddleName");
        assertEquals(user.getLastName(),"testUpdateLastName");
        assertTrue(user.getId() == 10);
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
