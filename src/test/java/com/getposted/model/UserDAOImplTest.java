package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

// @Ignore()
public class UserDAOImplTest {

    private static User user = new User();
    private static UserDAOImpl userDAOImpl = new UserDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        int id = 40;
        String email = "testDelete@example.com";
        String password = "testDeletepassword";
        String userName = "usernamedelete";
        Date dob = new Date(Calendar.getInstance().getTimeInMillis());
        String salt = "saltdelete";
        String pepper = "pepperdelete";
        String firstName = "deletefirstname";
        String middleName = "deleteMiddlename";
        String lastname = "deleteLastName";

        user = new User(id, email, password, userName, dob, salt, pepper, firstName, middleName, lastname);
        userDAOImpl.insert(user);

        user = userDAOImpl.get(id);
        userDAOImpl.delete(user);
        assertNull(userDAOImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        // 1, 'user1@example.com', 'password1', 'user1', '1995-03-10', 'salt1',
        // 'pepper1', 'Alice', 'A', 'Smith'
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
        // id,email, password, userName, dob, salt, pepper firstName, middleName,
        // lastName
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
        userDAOImpl.insert(user);

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
        userDAOImpl.insert(user);

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
        userDAOImpl.update(user);

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
}
