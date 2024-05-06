package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
    public static void createDatabase() {
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

        inquiry = new Inquiry(id, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count,
                country, shippedDate, state, price, publicationId, userId, publisherId);

        int rowsAffected = inquiryDAOImpl.insert(inquiry);
        assertEquals(rowsAffected, 1);

        rowsAffected = inquiryDAOImpl.delete(inquiry);
        assertEquals(rowsAffected, 1);

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
        assertEquals(inquiry.getId(), id);
        assertEquals(inquiry.getPurchasedDate().toString(), purchasedDate);
        assertEquals(inquiry.getShippingAddress(), shippingAddress);
        assertEquals(inquiry.getPostalCode(), postalCode);
        assertEquals(inquiry.getPurchasedTime().toString(), purchasedTime);
        assertEquals(inquiry.getContactName(), contactName);
        assertEquals(inquiry.getCount(), count);
        assertEquals(inquiry.getCountry(), country);
        assertEquals(inquiry.getShippedDate().toString(), shippedDate);
        assertEquals(inquiry.getState(), state);
        assertTrue(inquiry.getPrice() == price);
        assertEquals(inquiry.getPublicationId(), publicationId);
        assertEquals(inquiry.getUserId(), userId);
        assertEquals(inquiry.getPublisherId(), publisherId);
    }

    @Test
    public void testGetAll() throws SQLException {

        List<Inquiry> inquiries = inquiryDAOImpl.getAll();
        assertTrue(inquiries.size() >= 10);

        for (Inquiry i : inquiries) {
            assertTrue(i.getId() >= 1);
            assertTrue(i.getPurchasedDate().toString().length() == 10);
            assertTrue(i.getShippingAddress().length() >= 1);
            assertTrue(i.getPostalCode().length() >= 1);
            assertTrue(i.getPurchasedTime().toString().length() == 8);
            assertTrue(i.getContactName().length() >= 1);
            assertTrue(i.getCount() >= 1);
            assertTrue(i.getCountry().length() >= 1);
            assertTrue(i.getShippedDate().toString().length() == 10);
            assertTrue(i.getState().equals(Inquiry.ACCEPTED) || i.getState().equals(Inquiry.PENDING)
                    || i.getState().equals(Inquiry.PROCESSING) || i.getState().equals(Inquiry.REJECTED)
                    || i.getState().equals(Inquiry.SHIPPED));
            assertTrue(i.getPrice() >= 0);
            assertTrue(i.getPublicationId() >= 1);
            assertTrue(i.getUserId() >= 1);
            assertTrue(i.getPublisherId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Inquiry inquiry = new Inquiry();
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

        int rowsAffected = inquiryDAOImpl.insert(inquiry);
        assertEquals(rowsAffected, 1);

        inquiry = inquiryDAOImpl.get(id);
        assertNotNull(inquiry);
        assertEquals(inquiry.getId(), id);
        assertEquals(inquiry.getPurchasedDate().toString(), purchasedDate.toString());
        assertEquals(inquiry.getShippingAddress(), shippingAddress);
        assertEquals(inquiry.getPostalCode(), postalCode);
        assertEquals(inquiry.getPurchasedTime().toString(), purchasedTime.toString());
        assertEquals(inquiry.getContactName(), contactName);
        assertEquals(inquiry.getCount(), count);
        assertEquals(inquiry.getCountry(), country);
        assertEquals(inquiry.getShippedDate().toString(), shippedDate.toString());
        assertEquals(inquiry.getState(), state);
        assertTrue(inquiry.getPrice() == price);
        assertEquals(inquiry.getPublicationId(), publicationId);
        assertEquals(inquiry.getUserId(), userId);
        assertEquals(inquiry.getPublisherId(), publisherId);

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

        inquiry = new Inquiry(id, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count,
                country, shippedDate, state, price, publicationId, userId, publisherId);

        int rowsAffected = inquiryDAOImpl.insert(inquiry);
        assertEquals(rowsAffected, 1);

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

        inquiry = new Inquiry(id, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count,
                country, shippedDate, state, price, publicationId, userId, publisherId);

        rowsAffected = inquiryDAOImpl.update(inquiry);
        assertEquals(rowsAffected, 1);

        inquiry = inquiryDAOImpl.get(id);
        assertNotNull(inquiry);
        assertEquals(inquiry.getId(), id);
        assertEquals(inquiry.getPurchasedDate().toString(), purchasedDate.toString());
        assertEquals(inquiry.getShippingAddress(), shippingAddress);
        assertEquals(inquiry.getPostalCode(), postalCode);
        assertEquals(inquiry.getPurchasedTime().toString(), purchasedTime.toString());
        assertEquals(inquiry.getContactName(), contactName);
        assertEquals(inquiry.getCount(), count);
        assertEquals(inquiry.getCountry(), country);
        assertEquals(inquiry.getShippedDate().toString(), shippedDate.toString());
        assertEquals(inquiry.getState(), state);
        assertTrue(inquiry.getPrice() == price);
        assertEquals(inquiry.getPublicationId(), publicationId);
        assertEquals(inquiry.getUserId(), userId);
        assertEquals(inquiry.getPublisherId(), publisherId);
    }

    @Test
    public void testGetAllInquiriesByPurchasedDate() throws SQLException{
        List<Inquiry> invs = inquiryDAOImpl.getAllInquiriesByPurchasedDate(false);
        assertNotNull(invs);
        assertTrue(invs.size()>=10);
        assertEquals(invs.get(0).getPurchasedDate().toString(),"2024-04-01");
    }

    @Test
    public void testGetListOfInquiriesByPurchasedDate() throws SQLException{
        List<Inquiry> invs = inquiryDAOImpl.getListOfInquiriesByPurchasedDate(5,false);
        assertNotNull(invs);
        assertTrue(invs.size() == 5);
        assertEquals(invs.get(0).getPurchasedDate().toString(),"2024-04-01");
    }

    @Test
    public void testGetAllInquiriesByPrice() throws SQLException{
        List<Inquiry> invs = inquiryDAOImpl.getAllInquiriesByPrice(true);
        assertNotNull(invs);
        assertTrue(invs.size()>=10);
        assertTrue(invs.get(0).getPrice()== 74.99);
    }

    @Test
    public void testgetListOfInquiriesByPrice() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getListOfInquiriesByPurchasedDate(5,false);
        assertNotNull(invs);
        assertTrue(invs.size() == 5);
        assertTrue(invs.get(0).getPrice()== 19.99);
    }

    @Test
    public void testGetAllOfInquiriesForAState() throws SQLException{
        List<Inquiry> invs = inquiryDAOImpl.getAllOfInquiriesForAState(Inquiry.PENDING);
        assertNotNull(invs);
        assertTrue(invs.size()==4-1);
        for(Inquiry inv: invs){
            assertEquals(inv.getState(),Inquiry.PENDING);
        }
    }

    @Test
    public void testGetListOfInquiriesForAState() throws SQLException{
        List<Inquiry> invs = inquiryDAOImpl.getListOfInquiriesForAState(Inquiry.REJECTED,1);
        assertNotNull(invs);
        assertTrue(invs.size() == 1);
        for(Inquiry inv: invs){
            assertEquals(inv.getState(),Inquiry.REJECTED);
        }
    }

    @Test
    public void testGetCountOfInquriesInAState() throws SQLException{
        int count = inquiryDAOImpl.getCountOfInquriesInAState(Inquiry.SHIPPED);
        assertEquals(count,2);
    }

    @Test
    public void testGetALLInquiriesForAPurchasedDate() throws SQLException{
        List<Inquiry> invs = inquiryDAOImpl.testGetALLInquiriesForAPurchasedDate(Date.valueOf("2024-04-01"));
        assertNotNull(invs);
        assertTrue(invs.size() == 1);
        for(Inquiry inv: invs){
            assertEquals(inv.getPurchasedDate().toString(),"2024-04-01");
        }
    }

    @Test
    public void testGetTotalSumOfInquriesPrice() throws SQLException{
        double sum = inquiryDAOImpl.getTotalSumOfInquriesPrice();
        assertTrue(sum == 569.88);
    }

    @Test
    public void testGetTotalInquiryCount() throws SQLException{
        int count = inquiryDAOImpl.getTotalInquiryCount();
        assertEquals(count,12);
    }

    @Test
    public void testGetTotalCopiesSold() throws SQLException{
        int count = inquiryDAOImpl.getTotalCopiesSold();
        assertEquals(count,29);
    }

    @Test
    public void testGetTotalPublicationPurchasedUsers() throws SQLException{
        int count = inquiryDAOImpl.getTotalPublicationPurchasedUsers();
        assertEquals(count,10);
    }

    @Test
    public void testGetALLInquiriesOfAUser() throws SQLException{
        List<Inquiry> invs = inquiryDAOImpl.getALLInquiriesOfAUser(1);
        Inquiry inquiry = new Inquiry();
        assertNotNull(invs);
        assertTrue(invs.size() == 1);
        
        inquiry = invs.get(0);
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
        assertEquals(inquiry.getId(), id);
        assertEquals(inquiry.getPurchasedDate().toString(), purchasedDate);
        assertEquals(inquiry.getShippingAddress(), shippingAddress);
        assertEquals(inquiry.getPostalCode(), postalCode);
        assertEquals(inquiry.getPurchasedTime().toString(), purchasedTime);
        assertEquals(inquiry.getContactName(), contactName);
        assertEquals(inquiry.getCount(), count);
        assertEquals(inquiry.getCountry(), country);
        assertEquals(inquiry.getShippedDate().toString(), shippedDate);
        assertEquals(inquiry.getState(), state);
        assertTrue(inquiry.getPrice() == price);
        assertEquals(inquiry.getPublicationId(), publicationId);
        assertEquals(inquiry.getUserId(), userId);
        assertEquals(inquiry.getPublisherId(), publisherId);
    }


    @Test
    public void testGetTotalInquiryCountOfAUser() throws SQLException{
        int count = inquiryDAOImpl.getTotalInquiryCountOfAUser(2);
        assertEquals(count,2);
    }

    @Test
    public void testGetTotalInquriyPayementFromAUser() throws SQLException{
        double sum = inquiryDAOImpl.getTotalInquriyPayementFromAUser(2);
        assertTrue(sum == 94.98);
    }

    @Test
    public void testGetTotalPublicationCopiesPurchasedByUser() throws SQLException{
        int count = inquiryDAOImpl.getTotalPublicationCopiesPurchasedByUser(2);
        assertEquals(count,15);
    }

    @Test
    public void testgetALLInquiriesOfAPublication() throws SQLException{
        List<Inquiry> invs = inquiryDAOImpl.getALLInquiriesOfAPublication(1);
        Inquiry inquiry = new Inquiry();
        assertNotNull(invs);
        assertTrue(invs.size() == 4-1);
        
        inquiry = invs.get(1);
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
        assertEquals(inquiry.getId(), id);
        assertEquals(inquiry.getPurchasedDate().toString(), purchasedDate);
        assertEquals(inquiry.getShippingAddress(), shippingAddress);
        assertEquals(inquiry.getPostalCode(), postalCode);
        assertEquals(inquiry.getPurchasedTime().toString(), purchasedTime);
        assertEquals(inquiry.getContactName(), contactName);
        assertEquals(inquiry.getCount(), count);
        assertEquals(inquiry.getCountry(), country);
        assertEquals(inquiry.getShippedDate().toString(), shippedDate);
        assertEquals(inquiry.getState(), state);
        assertTrue(inquiry.getPrice() == price);
        assertEquals(inquiry.getPublicationId(), publicationId);
        assertEquals(inquiry.getUserId(), userId);
        assertEquals(inquiry.getPublisherId(), publisherId);
    }

    @Test
    public void testGetTotalInquiryCountOfAPublication() throws SQLException{
        int count = inquiryDAOImpl.getTotalInquiryCountOfAPublication(1);
        assertEquals(count,4-1);
    }


    @Test
    public void testGetTotalInquriyPayementForAPublication() throws SQLException{
        double sum = inquiryDAOImpl.getTotalInquriyPayementForAPublication(1);
        assertTrue(sum == 164.97);
    }

    @Test
    public void testGetTotalCopiesSoldFromAPublication() throws SQLException{
        int count = inquiryDAOImpl.getTotalCopiesSoldFromAPublication(1);
        assertEquals(count,16);
    }

    @Test
    public void testGetTotalPageCountSoldFromAPublication() throws SQLException{
        int count = inquiryDAOImpl.getTotalPageCountSoldFromAPublication(1);
        assertEquals(count,300);
    }

    @Test
    public void testGetUserPurchasedAllPublicationsIds() throws SQLException{
        List<Integer> publicatiList = inquiryDAOImpl.getUserPurchasedAllPublicationsIds(2);
        assertNotNull(publicatiList);
        assertEquals(publicatiList.size(),2);

        assertTrue(publicatiList.contains(1));
        assertTrue(publicatiList.contains(2));
        assertFalse(publicatiList.contains(4));
        assertFalse(publicatiList.contains(5));
    }

    @Test
    public void testGetPublicationPurchasedAllUserIds() throws SQLException{
        List<Integer> useridList = inquiryDAOImpl.getPublicationPurchasedAllUserIds(1);
        assertNotNull(useridList);
        assertEquals(useridList.size(),4-1);

        assertTrue(useridList.contains(1));
        assertTrue(useridList.contains(2));
        assertTrue(useridList.contains(4));
        assertFalse(useridList.contains(5));
    }

    @Test
    public void testGetListOfInquiriesFromInquryIdList() throws SQLException{
        List<Inquiry> inquiries = inquiryDAOImpl.getListOfInquiriesFromInquryIdList(1,2);
        assertNotNull(inquiries);
        assertEquals(inquiries.size() , 2);

        boolean contains = false;
        for(Inquiry inquiry : inquiries){
            if(inquiry.getId() == 1 || inquiry.getId() == 2) contains = true;
        }

        assertTrue(contains);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
