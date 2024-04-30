package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorDAOImplTest {

    private static Author author = new Author();
    private static AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();


    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }


    @Test
    public void testDelete() throws SQLException{
        author.setId(74);
        author.setEmail("testDelete@gmail.com");
        author.setPhoneNumber("8234568450");
        author.setBio("Lorem ipsum dolop sit amet, consectetur adipiscing elit.");
        author.setPassword("testpasswordasdfasdf");
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        author.setDob(date);
        author.setFirstName("testDelete1");
        author.setMiddleName("testDelete2");
        author.setLastName("testDeletet4");
        author.setUserName("testDelete8");
        authorDAOImpl.insert(author);
        authorDAOImpl.delete(author);
    }

    @Test
    public void testGet() throws SQLException{
        // (1,'author1@example.com', '1234567890', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'password1', '1990-01-01', 'John', NULL, 'Doe', 'john_doe')
        author = authorDAOImpl.get(1);
        assertTrue(author.getId() == 1);
        assertEquals(author.getEmail(),"author1@example.com");
        assertEquals(author.getPhoneNumber(),"1234567890");
        assertEquals(author.getBio(),"Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        assertEquals(author.getPassword(),"password1");
        assertEquals(author.getDob().toString(),"1990-01-01");
        assertEquals(author.getFirstName(),"John");
        assertEquals(author.getMiddleName(),null);
        assertEquals(author.getLastName(),"Doe");
        assertEquals(author.getUserName(),"john_doe");
    }

    @Test
    public void testGetAll() throws SQLException{
        List<Author> authors = authorDAOImpl.getAll();
        assertTrue(authors.size() >= 20);
        for (Author author : authors){
            assertTrue(author.getEmail().length() >= 2);
        }
        for (Author author : authors){
            assertTrue(author.getBio().length() >= 2);
        }
        for (Author author : authors){
            assertTrue(author.getPassword().length() >= 2);
        }
        for (Author author : authors){
            assertTrue(author.getDob().toString().length() >= 2);
        }
        for (Author author : authors){
            assertTrue(author.getFirstName().length() >= 2);
        }
        for (Author author : authors){
            assertTrue(author.getLastName().length() >= 2);
        }
        for (Author author : authors){
            assertTrue(author.getUserName().length() >= 2);
        }
        for (Author author : authors){
            assertTrue(author.getPhoneNumber().length() >= 2);
        }
    }

    @Test
    public void testInsert() throws SQLException{
        author.setId(28);
        author.setEmail("testInsert@gmail.com");
        author.setPhoneNumber("1234568890");
        author.setBio("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        author.setPassword("testpassword");
        author.setFirstName("test");
        author.setMiddleName("test");
        author.setLastName("test");
        author.setUserName("test");
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        author.setDob(date);
        authorDAOImpl.insert(author);
        author = authorDAOImpl.get(28);
        assertTrue(author.getId() == 28);
        assertEquals(author.getEmail(),"testInsert@gmail.com");
        assertEquals(author.getPhoneNumber(),"1234568890");
        assertEquals(author.getBio(),"Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        assertEquals(author.getPassword(),"testpassword");
        assertEquals(author.getFirstName(),"test");
        assertEquals(author.getMiddleName(),"test");
        assertEquals(author.getLastName(),"test");
        assertEquals(author.getUserName(),"test");
        assertEquals(author.getDob().toString(),date.toString());
    }

    @Test(expected = SQLException.class)
    public void testExceptionForInsert() throws SQLException{
        author.setId(70);
        author.setEmail("testExceptionForInsert@gmail.com");
        author.setPhoneNumber("1234568450");
        author.setBio("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        author.setPassword("testpassword");
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        author.setDob(date);
        author.setFirstName("test");
        author.setMiddleName("test");
        author.setLastName("test");
        author.setUserName("test");
        authorDAOImpl.insert(author);
        authorDAOImpl.insert(author);
    }

    @Test
    public void testUpdate() throws SQLException{
        author.setId(50);
        author.setEmail("testUpdate@gmail.com");
        author.setPhoneNumber("1234998899");
        authorDAOImpl.insert(author);
        author.setEmail("testUpdate@gmail2.com");
        authorDAOImpl.update(author);
        author = authorDAOImpl.get(50);
        assertTrue(author.getId() == 50);
        assertEquals(author.getEmail(),"testUpdate@gmail2.com");
        assertEquals(author.getPhoneNumber(),"1234998899");
        assertEquals(author.getBio(),"Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        assertEquals(author.getPassword(),"testpassword");
        assertEquals(author.getFirstName(),"test");
        assertEquals(author.getMiddleName(),"test");
        assertEquals(author.getLastName(),"test");
        assertEquals(author.getUserName(),"test");
        author.setLastName("newLastName");
        author.setUserName("newUserName");
        Calendar cl = Calendar.getInstance(TimeZone.getTimeZone("Asia/Colombo"));
        Date date = new Date(cl.getTimeInMillis());
        author.setDob(date);
        authorDAOImpl.update(author);
        assertEquals(author.getLastName(),"newLastName");
        assertEquals(author.getUserName(),"newUserName");
        assertEquals(author.getDob().toString(),date.toString());
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}