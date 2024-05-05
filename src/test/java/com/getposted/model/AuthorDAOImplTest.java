package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("AuthorDAOImpTest")
public class AuthorDAOImplTest {

    private static AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Author author = new Author();
        author.setId(20);
        author.setEmail("testDelete@gmail.com");
        author.setPhoneNumber("8234568450");
        author.setSalt("deleteSalt");
        author.setBio("Lorem ipsum dolop sit amet, consectetur adipiscing elit.");
        author.setPepper("deletePepper");
        author.setPassword("testpasswordasdfasdf");
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        author.setDob(date);
        author.setFirstName("testDelete1");
        author.setMiddleName("testDelete2");
        author.setLastName("testDeletet4");
        author.setUserName("testDelete8");
        author.setCountryId(1);

        int rowsAffected = authorDAOImpl.insert(author);
        assertEquals(rowsAffected, 1);

        rowsAffected = authorDAOImpl.delete(author);
        assertEquals(rowsAffected, 1);

        assertNull(authorDAOImpl.get(20));
    }

    @Test
    public void testGet() throws SQLException {
        Author author = new Author();
        author = authorDAOImpl.get(1);
        assertTrue(author.getId() == 1);

        assertEquals(author.getEmail(), "author1@example.com");
        assertEquals(author.getPhoneNumber(), "1234567890");
        assertEquals(author.getSalt(), "salt1");
        assertEquals(author.getBio(), "Author 1 bio");
        assertEquals(author.getPepper(), "pepper1");
        assertEquals(author.getPassword(), "password1");
        assertEquals(author.getDob().toString(), "1990-01-01");
        assertEquals(author.getFirstName(), "John");
        assertEquals(author.getMiddleName(), "A");
        assertEquals(author.getLastName(), "Doe");
        assertEquals(author.getUserName(), "johndoe1");
        assertEquals(author.getCountryId(), 1);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Author> authors = authorDAOImpl.getAll();
        assertTrue(authors.size() >= 10);
        for (Author author : authors) {
            assertTrue(author.getEmail().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getPhoneNumber().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getSalt().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getBio().length() >= 1 || author.getBio() == null);
        }
        for (Author author : authors) {
            assertTrue(author.getPepper().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getPassword().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getDob().toString().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getFirstName().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getMiddleName().length() >= 1 || author.getMiddleName() == null);
        }
        for (Author author : authors) {
            assertTrue(author.getLastName().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getUserName().length() >= 1);
        }
        for (Author author : authors) {
            assertTrue(author.getCountryId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Author author = new Author();
        int id = 25;
        String email = "testInsert@gmail.com";
        String phoneNumber = "12345458890";
        String salt = "testSalt";
        String bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        String pepper = "testPepper";
        String password = "testInsertPassword";
        Date dob = new Date(Calendar.getInstance().getTimeInMillis());
        String firstName = "test";
        String middleName = "test";
        String lastName = "test";
        String userName = "test";
        int countryId = 1;

        author.setId(id);
        author.setEmail(email);
        author.setPhoneNumber(phoneNumber);
        author.setSalt(salt);
        author.setBio(bio);
        author.setPepper(pepper);
        author.setPassword(password);
        author.setDob(dob);
        author.setFirstName(firstName);
        author.setMiddleName(middleName);
        author.setLastName(lastName);
        author.setUserName(userName);
        author.setCountryId(countryId);

        int rowsAffected = authorDAOImpl.insert(author);
        assertEquals(rowsAffected, 1);

        author = authorDAOImpl.get(25);
        assertTrue(author.getId() == id);
        assertEquals(author.getEmail(), email);
        assertEquals(author.getPhoneNumber(), phoneNumber);
        assertEquals(author.getSalt(), salt);
        assertEquals(author.getBio(), bio);
        assertEquals(author.getPepper(), pepper);
        assertEquals(author.getPassword(), password);
        assertEquals(author.getFirstName(), firstName);
        assertEquals(author.getMiddleName(), middleName);
        assertEquals(author.getLastName(), lastName);
        assertEquals(author.getUserName(), userName);
        assertEquals(author.getDob().toString(), dob.toString());
        assertEquals(author.getCountryId(), countryId);
    }

    @Test
    public void testUpdate() throws SQLException {
        Author author = new Author();
        int id = 50;
        String email = "testUpdate@gmail.com";
        String phoneNumber = "12245458890";
        String salt = "testSaltt";
        String bio = "Lorem ipsusm dolor sit amet, consectetur adipiscing elit.";
        String pepper = "testPeppper";
        String password = "testUpdatePassword";
        Date dob = new Date(Calendar.getInstance().getTimeInMillis());
        String firstName = "testt";
        String middleName = "testt";
        String lastName = "testt";
        String userName = "testt";
        int countryId = 2;

        author.setId(id);
        author.setEmail(email);
        author.setPhoneNumber(phoneNumber);
        author.setSalt(salt);
        author.setBio(bio);
        author.setPepper(pepper);
        author.setPassword(password);
        author.setDob(dob);
        author.setFirstName(firstName);
        author.setMiddleName(middleName);
        author.setLastName(lastName);
        author.setUserName(userName);
        author.setCountryId(countryId);

        int rowsAffected = authorDAOImpl.insert(author);
        assertEquals(rowsAffected, 1);

        email = "testUpdatee@gmail.com";
        phoneNumber = "122245458890";
        salt = "testSalttt";
        bio = "Lorem ipsusm dtolor sit amet, consectetur adipiscing elit.";
        pepper = "testPepppeer";
        password = "testUpddatePassword";
        dob = new Date(Calendar.getInstance().getTimeInMillis());
        firstName = "testtt";
        middleName = "testtt";
        lastName = "testtt";
        userName = "testtt";
        countryId = 4;

        author.setEmail(email);
        author.setPhoneNumber(phoneNumber);
        author.setSalt(salt);
        author.setBio(bio);
        author.setPepper(pepper);
        author.setPassword(password);
        author.setDob(dob);
        author.setFirstName(firstName);
        author.setMiddleName(middleName);
        author.setLastName(lastName);
        author.setUserName(userName);
        author.setCountryId(countryId);

        rowsAffected = authorDAOImpl.update(author);
        assertEquals(rowsAffected, 1);

        author = authorDAOImpl.get(50);
        assertTrue(author.getId() == id);
        assertEquals(author.getEmail(), email);
        assertEquals(author.getPhoneNumber(), phoneNumber);
        assertEquals(author.getSalt(), salt);
        assertEquals(author.getBio(), bio);
        assertEquals(author.getPepper(), pepper);
        assertEquals(author.getPassword(), password);
        assertEquals(author.getFirstName(), firstName);
        assertEquals(author.getMiddleName(), middleName);
        assertEquals(author.getLastName(), lastName);
        assertEquals(author.getUserName(), userName);
        assertEquals(author.getDob().toString(), dob.toString());
        assertEquals(author.getCountryId(), countryId);
    }

    @Test(expected = SQLException.class)
    public void testExceptionForInsert() throws SQLException {
        Author author = new Author();
        author.setId(70);
        author.setEmail("testExceptionForInsert@gmail.com");
        author.setPhoneNumber("1234568450");
        author.setSalt("testSalt");
        author.setBio("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        author.setPepper("testPepper");
        author.setPassword("testpassword");
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        author.setDob(date);
        author.setFirstName("test");
        author.setMiddleName("test");
        author.setLastName("test");
        author.setUserName("test");
        author.setCountryId(1);
        authorDAOImpl.insert(author);
        authorDAOImpl.insert(author);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}