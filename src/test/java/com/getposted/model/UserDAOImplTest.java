package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import java.util.List;
import java.util.Calendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

// @Ignore()
public class UserDAOImplTest {

    private static UserDAOImpl userDAOImpl = new UserDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        User user = new User();
        int id = 40;
        String email = "testDelete@example.com";
        String password = "testDeletepassword";
        Date dob = new Date(Calendar.getInstance().getTimeInMillis());
        String userName = "usernamedelete";
        String salt = "saltdelete";
        String pepper = "pepperdelete";
        String firstName = "deletefirstname";
        String middleName = "deleteMiddlename";
        String lastname = "deleteLastName";

        user = new User(id, email, password, userName, dob, salt, pepper, firstName, middleName, lastname);

        int rowsAffected = userDAOImpl.insert(user);
        assertEquals(rowsAffected, 1);

        user = userDAOImpl.get(id);

        rowsAffected = userDAOImpl.delete(user);
        assertEquals(rowsAffected, 1);

        assertNull(userDAOImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        User user = new User();
        assertNull(userDAOImpl.get(0));
        user = userDAOImpl.get(1);
        assertTrue(user.getId() == 1);
        assertEquals(user.getEmail(), "user1@example.com");
        assertEquals(user.getPassword(), "password1");
        assertEquals(user.getUserName(), "user1");
        assertEquals(user.getDob().toString(), "1995-03-10");
        assertEquals(user.getSalt(), "salt1");
        assertEquals(user.getPepper(), "pepper1");
        assertEquals(user.getFirstName(), "Alice");
        assertEquals(user.getMiddleName(), "A");
        assertEquals(user.getLastName(), "Smith");
    }

    @Test
    public void testGetAll() throws SQLException {
        List<User> users = userDAOImpl.getAll();
        assertTrue(users.size() >= 10);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        User user = new User();
        int id = 25;
        String email = "testInsert@example.com";
        String password = "testinsertpassword";
        String userName = "username";
        Date dob = new Date(Calendar.getInstance().getTimeInMillis());
        String salt = "saltinsert";
        String pepper = "pepperinsert";
        String firstName = "insertfirstname";
        String middleName = "insertMiddlename";
        String lastname = "insertLastName";

        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserName(userName);
        user.setDob(dob);
        user.setSalt(salt);
        user.setPepper(pepper);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastname);

        int rowsAffected = userDAOImpl.insert(user);
        assertEquals(rowsAffected, 1);

        user = userDAOImpl.get(id);
        assertTrue(user.getId() == id);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getUserName(), userName);
        assertEquals(user.getDob().toString(), dob.toString());
        assertEquals(user.getSalt(), salt);
        assertEquals(user.getPepper(), pepper);
        assertEquals(user.getFirstName(), firstName);
        assertEquals(user.getMiddleName(), middleName);
        assertEquals(user.getLastName(), lastname);

    }

    @Test
    public void testUpdate() throws SQLException {
        User user = new User();
        int id = 28;
        String email = "testUpdate@example.com";
        String password = "testupdatepassword";
        String userName = "usernameupdate";
        Date dob = new Date(Calendar.getInstance().getTimeInMillis());
        String salt = "saltupdate";
        String pepper = "pepperupdate";
        String firstName = "updatefirstname";
        String middleName = "updateMiddlename";
        String lastname = "updateLastName";

        user = new User(id, email, password, userName, dob, salt, pepper, firstName, middleName, lastname);

        int rowsAffected = userDAOImpl.insert(user);
        assertEquals(rowsAffected, 1);

        email = "testUpdatee@example.com";
        password = "testupdateepassword";
        userName = "usernameupdatee";
        dob = new Date(Calendar.getInstance().getTimeInMillis());
        salt = "saltupdatee";
        pepper = "pepperupdatee";
        firstName = "updatefirstnamee";
        middleName = "updateMiddlenamee";
        lastname = "updateLastNamee";

        user = new User(id, email, password, userName, dob, salt, pepper, firstName, middleName, lastname);
        rowsAffected = userDAOImpl.update(user);
        assertEquals(rowsAffected, 1);

        user = userDAOImpl.get(id);
        assertTrue(user.getId() == id);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getUserName(), userName);
        assertEquals(user.getDob().toString(), dob.toString());
        assertEquals(user.getSalt(), salt);
        assertEquals(user.getPepper(), pepper);
        assertEquals(user.getFirstName(), firstName);
        assertEquals(user.getMiddleName(), middleName);
        assertEquals(user.getLastName(), lastname);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }

    @Test
    public void testGetAllUsersByGivenEmailPattern() throws SQLException {
        List<User> users = userDAOImpl.getAllUsersByGivenEmailPattern("@");
        assertTrue(users.size() >= 4);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
        }
    }

    @Test
    public void testGetAllUsersByGivenUserNamePattern() throws SQLException {
        List<User> users = userDAOImpl.getAllUsersByGivenUserNamePattern("1");
        assertTrue(users.size() >= 2);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
        }
    }

    @Test
    public void testGetAllUsersExceptGivenUsersIds() throws SQLException {
        List<User> users = userDAOImpl.getAllUsersExceptGivenUsersIds(1, 2);
        assertTrue(users.size() >= 2);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
            assertFalse(user.getId() == 1 || user.getId() == 2);
        }
    }

    @Test
    public void testGetAllUsersForGivenNamePattern() throws SQLException {
        List<User> users = userDAOImpl.getAllUsersForGivenNamePattern("al");
        assertTrue(users.size() >= 1);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
        }
    }

    @Test
    public void testGetListOfUsersByGivenEmailPattern() throws SQLException {
        List<User> users = userDAOImpl.getListOfUsersByGivenEmailPattern("@",1);
        assertTrue(users.size() == 1);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
        }
    }

    @Test
    public void testGetListOfUsersByGivenUserNamePattern() throws SQLException {
        List<User> users = userDAOImpl.getListOfUsersByGivenUserNamePattern("1", 1);
        assertTrue(users.size() == 1);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
        }
    }

    @Test
    public void testGetListOfUsersForGivenNamePattern() throws SQLException {
        List<User> users = userDAOImpl.getListOfUsersForGivenNamePattern("al", 1);
        assertTrue(users.size() == 1);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
        }
    }

    @Test
    public void testGetUserFullName() throws SQLException {
        String fullName = userDAOImpl.getUserFullName(1);
        assertEquals("Alice A Smith", fullName);
    }

    @Test
    public void testGetUsersForGivenUserIds() throws SQLException {
        List<User> users = userDAOImpl.getUsersForGivenUserIds(1, 2);
        assertTrue(users.size() == 2);

        for (User user : users) {
            assertTrue(user.getEmail().length() >= 1);
            assertTrue(user.getPassword().length() >= 1);
            assertTrue(user.getUserName().length() >= 1);
            assertNotNull(user.getDob());
            assertTrue(user.getSalt().length() >= 1);
            assertTrue(user.getPepper().length() >= 1);
            assertTrue(user.getFirstName().length() >= 1);
            assertTrue(user.getMiddleName().length() >= 1 || user.getMiddleName() == null);
            assertTrue(user.getLastName().length() >= 1);
            assertTrue(user.getId() == 1 || user.getId() == 2);

        }
    }

}
