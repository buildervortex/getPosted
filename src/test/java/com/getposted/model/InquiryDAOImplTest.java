package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore()
public class InquiryDAOImplTest {

    private static InquiryDAOImpl inquiryDAOImpl = new InquiryDAOImpl();

    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }
    @Test
    public void testDelete() throws SQLException {
        Inquiry inquiry = new Inquiry();

        int id = 68;
        Date purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        String shippingAddress = "123 Mainn St, Anytown US";
        String postalCode = "12342";
        Time purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        String contactName = "testDelete";
        int count = 8;
        String country = "US";
        Date shippedDate = new Date(Calendar.getInstance().getTimeInMillis());
        String state = Inquiry.PENDING;
        double price = 9.99;
        int publicationId = 8;
        int userId = 8;
        int publisherId = 8;

        inquiry = new Inquiry(id, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count, country, shippedDate, state, price, publicationId, userId, publisherId);

        inquiryDAOImpl.insert(inquiry);

        inquiryDAOImpl.delete(inquiry);
        assertNull(inquiryDAOImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        Inquiry inquiry = new Inquiry();

        int id = 1;
        String purchasedDate = "2024-04-01";
        String shippingAddress = "123 Main St, Anytown USA";
        String postalCode = "12345";
        String purchasedTime = "12:00:00";
        String contactName = "John Doe";
        int count = 1;
        String country = "USA";
        String shippedDate = "2024-04-05";
        String state = Inquiry.ACCEPTED;
        double price = 19.99;
        int publicationId = 1;
        int userId = 1;
        int publisherId = 1;



        inquiry = inquiryDAOImpl.get(1);
        assertNull(inquiryDAOImpl.get(0));
        assertNotNull(inquiry);
        assertEquals(inquiry.getId(),id);
        assertEquals(inquiry.getPurchasedDate().toString(),purchasedDate);
        assertEquals(inquiry.getShippingAddress(),shippingAddress);
        assertEquals(inquiry.getPostalCode(),postalCode);
        assertEquals(inquiry.getPurchasedTime().toString(),purchasedTime);
        assertEquals(inquiry.getContactName(),contactName);
        assertEquals(inquiry.getCount(),count);
        assertEquals(inquiry.getCountry(),country);
        assertEquals(inquiry.getShippedDate().toString(),shippedDate);
        assertEquals(inquiry.getState(),state);
        assertTrue(inquiry.getPrice() == price);
        assertEquals(inquiry.getPublicationId(),publicationId);
        assertEquals(inquiry.getUserId(),userId);
        assertEquals(inquiry.getPublisherId(),publisherId);
    }

    @Test
    public void testGetAll() throws SQLException{
        Inquiry inquiry = new Inquiry();

// (1, '2024-04-01', '123 Main St, Anytown USA', '12345', '12:00:00', 'John Doe', 1, 'USA', '2024-04-05', 'Accepted', 19.99, 1, 1, 1)
        List<Inquiry> inquiries = inquiryDAOImpl.getAll();
        assertTrue(inquiries.size() >= 10);

        for(Inquiry i: inquiries){
            assertTrue(i.getId()>=1);
            assertTrue(i.getPurchasedDate().toString().length() == 10);
            assertTrue(i.getShippingAddress().length() >= 1);
            assertTrue(i.getPostalCode().length() >= 1);
            assertTrue(i.getPurchasedTime().toString().length() == 8);
            assertTrue(i.getContactName().length() >= 1);
            assertTrue(i.getCount() >= 1);
            assertTrue(i.getCountry().length() >= 1);
            assertTrue(i.getShippedDate().toString().length() == 10);
            assertTrue(i.getState().equals(Inquiry.ACCEPTED) || i.getState().equals( Inquiry.PENDING) || i.getState().equals(Inquiry.PROCESSING) || i.getState().equals(Inquiry.REJECTED) || i.getState().equals(Inquiry.SHIPPED) );
            assertTrue(i.getPrice() >= 0);
            assertTrue(i.getPublicationId()>=1);
            assertTrue(i.getUserId() >= 1);
            assertTrue(i.getPublisherId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Inquiry inquiry = new Inquiry();
    // (id, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count, country, shippedDate, state, price, publicationId, userId, publisherId)
        int id = 24;
        Date purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        String shippingAddress = "123 Mainn St, Anytown USA";
        String postalCode = "12347";
        Time purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        String contactName = "testInsert";
        int count = 1;
        String country = "USA";
        Date shippedDate = new Date(Calendar.getInstance().getTimeInMillis());
        String state = Inquiry.ACCEPTED;
        double price = 19.99;
        int publicationId = 2;
        int userId = 2;
        int publisherId = 2;

        inquiry.setId(id);
        inquiry.setPurchasedDate(purchasedDate);
        inquiry.setShippingAddress(shippingAddress);
        inquiry.setPostalCode(postalCode);
        inquiry.setPurchasedTime(purchasedTime);
        inquiry.setContactName(contactName);
        inquiry.setCount(count);
        inquiry.setCountry(country);
        inquiry.setShippedDate(shippedDate);
        inquiry.setState(state);
        inquiry.setPrice(price);
        inquiry.setPublicationId(publicationId);
        inquiry.setUserId(userId);
        inquiry.setPublisherId(publisherId);

        inquiryDAOImpl.insert(inquiry);


        inquiry = inquiryDAOImpl.get(id);
        assertNotNull(inquiry);
        assertEquals(inquiry.getId(),id);
        assertEquals(inquiry.getPurchasedDate().toString(),purchasedDate.toString());
        assertEquals(inquiry.getShippingAddress(),shippingAddress);
        assertEquals(inquiry.getPostalCode(),postalCode);
        assertEquals(inquiry.getPurchasedTime().toString(),purchasedTime.toString());
        assertEquals(inquiry.getContactName(),contactName);
        assertEquals(inquiry.getCount(),count);
        assertEquals(inquiry.getCountry(),country);
        assertEquals(inquiry.getShippedDate().toString(),shippedDate.toString());
        assertEquals(inquiry.getState(),state);
        assertTrue(inquiry.getPrice() == price);
        assertEquals(inquiry.getPublicationId(),publicationId);
        assertEquals(inquiry.getUserId(),userId);
        assertEquals(inquiry.getPublisherId(),publisherId);

    }

    @Test
    public void testUpdate() throws SQLException {
        Inquiry inquiry = new Inquiry();

        int id = 28;
        Date purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        String shippingAddress = "123 Mainn St, Anytown USAA";
        String postalCode = "12348";
        Time purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        String contactName = "testUpdate";
        int count = 2;
        String country = "USAa";
        Date shippedDate = new Date(Calendar.getInstance().getTimeInMillis());
        String state = Inquiry.PENDING;
        double price = 29.99;
        int publicationId = 4;
        int userId = 1;
        int publisherId = 5;

        inquiry = new Inquiry(id, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count, country, shippedDate, state, price, publicationId, userId, publisherId);

        inquiryDAOImpl.insert(inquiry);
        
        purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        shippingAddress = "123 Mainn St, Anytown USAAa";
        postalCode = "12342";
        purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        contactName = "testtUpdate";
        count = 4;
        country = "USAaa";
        shippedDate = new Date(Calendar.getInstance().getTimeInMillis());
        state = Inquiry.SHIPPED;
        price = 40.99;
        publicationId = 5;
        userId = 4;
        publisherId = 2;

        inquiry = new Inquiry(id, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count, country, shippedDate, state, price, publicationId, userId, publisherId);

        inquiryDAOImpl.update(inquiry);

        inquiry = inquiryDAOImpl.get(id);
        assertNotNull(inquiry);
        assertEquals(inquiry.getId(),id);
        assertEquals(inquiry.getPurchasedDate().toString(),purchasedDate.toString());
        assertEquals(inquiry.getShippingAddress(),shippingAddress);
        assertEquals(inquiry.getPostalCode(),postalCode);
        assertEquals(inquiry.getPurchasedTime().toString(),purchasedTime.toString());
        assertEquals(inquiry.getContactName(),contactName);
        assertEquals(inquiry.getCount(),count);
        assertEquals(inquiry.getCountry(),country);
        assertEquals(inquiry.getShippedDate().toString(),shippedDate.toString());
        assertEquals(inquiry.getState(),state);
        assertTrue(inquiry.getPrice() == price);
        assertEquals(inquiry.getPublicationId(),publicationId);
        assertEquals(inquiry.getUserId(),userId);
        assertEquals(inquiry.getPublisherId(),publisherId);
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
