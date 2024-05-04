package com.getposted.model;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PhoneNumberDAOImplTest {

    private static PhoneNumberDAOImpl phoneNumberDAOImpl = new PhoneNumberDAOImpl();

    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }

    @Test
    public void getAllTest() throws SQLException{
        List<PhoneNumber> phoneNumbers = phoneNumberDAOImpl.getAll();

        for(PhoneNumber phoneNumber: phoneNumbers){
            assertTrue(phoneNumber.getInquiryId() >= 1);
            assertTrue(phoneNumber.getPhoneNumber().length() >= 1);
        }
    }

    @Test
    public void insertTest() throws SQLException{
        PhoneNumber phoneNumber = new PhoneNumber();
        int inquiryId = 1;
        String phoneNumber1 = "0245542111";

        phoneNumber = new PhoneNumber(phoneNumber1,inquiryId);

        phoneNumberDAOImpl.insert(phoneNumber);

        boolean change = false;

        for(PhoneNumber phoneNumer4: phoneNumberDAOImpl.getList(1)){
            if(phoneNumer4.getPhoneNumber().equals(phoneNumber1)) change = true;
        }
        assertTrue(change);

    }

    @Test
    public void getListTest() throws SQLException{
        List<PhoneNumber> phoneNumbers = phoneNumberDAOImpl.getList(1);

        boolean changed = false;
        for(PhoneNumber phoneNumber : phoneNumbers){
            if(phoneNumber.getPhoneNumber().equals("1234567890")) changed = true;
        }

        assertTrue(changed);
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
