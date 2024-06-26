package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

// @Ignore
public class PhoneNumberDAOImplTest {

    private static PhoneNumberDAOImpl phoneNumberDAOImpl = new PhoneNumberDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void deleteTest() throws SQLException{
        PhoneNumber phoneNumber = new PhoneNumber();

        int inquiryId = 4;
        String phoneNumber1 = "02455421234";
        phoneNumber = new PhoneNumber(phoneNumber1, inquiryId);

        int rowsAffected = phoneNumberDAOImpl.insert(phoneNumber);
        assertEquals(rowsAffected,1);

        boolean change = false;

        for (PhoneNumber phoneNumer4 : phoneNumberDAOImpl.getList(4)) {
            if (phoneNumer4.getPhoneNumber().equals(phoneNumber1))
                change = true;
        }
        assertTrue(change);

        rowsAffected = phoneNumberDAOImpl.delete(phoneNumber);
        assertEquals(rowsAffected,1);

        change = false;

        for (PhoneNumber phoneNumer4 : phoneNumberDAOImpl.getList(4)) {
            if (phoneNumer4.getPhoneNumber().equals(phoneNumber1))
                change = true;
        }
        assertFalse(change);
    }
    @Test
    public void getAllTest() throws SQLException {
        List<PhoneNumber> phoneNumbers = phoneNumberDAOImpl.getAll();

        for (PhoneNumber phoneNumber : phoneNumbers) {
            assertTrue(phoneNumber.getInquiryId() >= 1);
            assertTrue(phoneNumber.getPhoneNumber().length() >= 1);
        }
    }

    @Test
    public void insertTest() throws SQLException {
        PhoneNumber phoneNumber = new PhoneNumber();
        int inquiryId = 1;
        String phoneNumber1 = "0245542111";

        phoneNumber = new PhoneNumber(phoneNumber1, inquiryId);

        int rowsAffected = phoneNumberDAOImpl.insert(phoneNumber);
        assertEquals(rowsAffected,1);

        boolean change = false;

        for (PhoneNumber phoneNumer4 : phoneNumberDAOImpl.getList(1)) {
            if (phoneNumer4.getPhoneNumber().equals(phoneNumber1))
                change = true;
        }
        assertTrue(change);

    }

    @Test
    public void getListTest() throws SQLException {
        List<PhoneNumber> phoneNumbers = phoneNumberDAOImpl.getList(1);

        boolean changed = false;
        for (PhoneNumber phoneNumber : phoneNumbers) {
            if (phoneNumber.getPhoneNumber().equals("1234567890"))
                changed = true;
        }

        assertTrue(changed);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
