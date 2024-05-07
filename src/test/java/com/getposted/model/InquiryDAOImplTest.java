package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
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
    public void testGetListRepresentation() throws SQLException {
        String[] list = new String[2];
        list[0] = "Accessed";
        list[1] = "Pending";

        String representation = inquiryDAOImpl.getListRepresentation(list);
        assertEquals("( \"Accessed\",\"Pending\" )", representation);
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
    public void testGetAllHardCopyInquiriesByPurchasedDate() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getAllHardCopyInquiriesByPurchasedDate(false, 1);
        assertNotNull(invs);
        assertTrue(invs.size() == 3);
        assertEquals(invs.get(0).getPurchasedDate().toString(), "2024-04-01");
    }

    @Test
    public void testGetListOfHardCopyInquiriesByPurchasedDate() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getListOfHardCopyInquiriesByPurchasedDate(2, false, 1);
        assertNotNull(invs);
        assertTrue(invs.size() == 2);
        assertEquals(invs.get(0).getPurchasedDate().toString(), "2024-04-01");
    }

    @Test
    public void testGetAllHardCopyInquiriesByPrice() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getAllHardCopyInquiriesByPrice(true, 1);
        assertNotNull(invs);
        assertTrue(invs.size() >= 3);
        assertTrue(invs.get(0).getPrice() == 74.99);
    }

    @Test
    public void testGetListOfHardCopyInquiriesByPrice() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getListOfHardCopyInquiriesByPrice(2, false, 1);
        assertNotNull(invs);
        assertTrue(invs.size() == 2);
        assertTrue(invs.get(0).getPrice() == 19.99);
    }

    @Test
    public void testGetAllOfHardCopyInquiriesForAState() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getAllOfHardCopyInquiriesForAState(Inquiry.PENDING, 1);
        assertNotNull(invs);
        assertTrue(invs.size() == 1);
        for (Inquiry inv : invs) {
            assertEquals(inv.getState(), Inquiry.PENDING);
        }
    }

    @Test
    public void testGetListOfHardCopyInquiriesForAState() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getListOfHardCopyInquiriesForAState(Inquiry.PENDING, 1, 1);
        assertNotNull(invs);
        assertTrue(invs.size() == 1);
        for (Inquiry inv : invs) {
            assertEquals(inv.getState(), Inquiry.PENDING);
        }
    }

    @Test
    public void testGetALLHardCopyInquiriesForAPurchasedDate() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getALLHardCopyInquiriesForAPurchasedDate(Date.valueOf("2024-04-01"), 1);
        assertNotNull(invs);
        assertTrue(invs.size() == 1);
        for (Inquiry inv : invs) {
            assertEquals(inv.getPurchasedDate().toString(), "2024-04-01");
        }
    }

    @Test
    public void testGetTotalSumOfHardCopyInquriesPrices() throws SQLException {
        double sum = inquiryDAOImpl.getTotalSumOfHardCopyInquriesPrices(1);
        assertTrue(sum == 164.97);
    }

    @Test
    public void testGetTotalHardCopyInquiryCount() throws SQLException {
        int count = inquiryDAOImpl.getTotalHardCopyInquiryCount(1);
        assertEquals(count, 4 - 1);
    }

    @Test
    public void testGetTotalHardCopiesSold() throws SQLException {
        int count = inquiryDAOImpl.getTotalHardCopiesSold(1);
        assertEquals(count, 16);
    }

    /* */
    @Test
    public void testGetTotalHardCopiesSoldInGivenStates() throws SQLException {
        int count = inquiryDAOImpl.getTotalHardCopiesSoldInGivenStates(1, Inquiry.ACCEPTED, Inquiry.PENDING);
        assertEquals(count, 16);
    }

    @Test
    public void testGetTotalHardCopiesSoldInAllStatesExceptTheGiveStates() throws SQLException {
        int count = inquiryDAOImpl.getTotalHardCopiesSoldInAllStatesExceptTheGiveStates(1, Inquiry.ACCEPTED,
                Inquiry.PENDING);
        assertEquals(count, 0);
    }

    @Test
    public void TestTotalUsersWhoPerchasedPublications() throws SQLException {
        int count = inquiryDAOImpl.TotalUsersWhoPerchasedPublications(1);
        assertEquals(count, 4 - 1);
    }

    @Test
    public void testGetALLHardCopyInquiriesOfAGiveUser() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getALLHardCopyInquiriesOfAGiveUser(1, 1);
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
    public void testGetTotalHardCopyInquiryCountOfAGiveUser() throws SQLException {
        int count = inquiryDAOImpl.getTotalHardCopyInquiryCountOfAGiveUser(2, 1);
        assertEquals(count, 1);
    }

    @Test
    public void testGetTotalInquriyPayementFromAUser() throws SQLException {
        double sum = inquiryDAOImpl.getTotalHardCopyInquriyPayementFromAUser(2, 1);
        assertTrue(sum == 69.99);
    }

    @Test
    public void testGetTotalPublicationsCopiesPurchasedByUser() throws SQLException {
        int count = inquiryDAOImpl.getTotalPublicationsCopiesPurchasedByUser(2, 1);
        assertEquals(count, 14);
    }

    @Test
    public void testGetALLHardCopyInquiriesForAPublication() throws SQLException {
        List<Inquiry> invs = inquiryDAOImpl.getALLHardCopyInquiriesForAPublication(1, 1);
        Inquiry inquiry = new Inquiry();
        assertNotNull(invs);
        assertTrue(invs.size() == 4 - 1);

        inquiry = invs.get(2);
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
    public void testGetTotalInquiryCountOfAPublication() throws SQLException {
        int count = inquiryDAOImpl.getTotalHardCopyInquiryCountForAPublication(1, 1);
        assertEquals(count, 4 - 1);
    }

    @Test
    public void testGetTotalHardCopyInquriyPayementsForAPublication() throws SQLException {
        double sum = inquiryDAOImpl.getTotalHardCopyInquriyPayementsForAPublication(1, 1);
        assertTrue(sum == 164.97);
    }

    @Test
    public void testGetTotalHardCopiesSoldFromAPublication() throws SQLException {
        int count = inquiryDAOImpl.getTotalHardCopiesSoldFromAPublication(1, 1);
        assertEquals(count, 16);
    }

    @Test
    public void testGetTotalHardCopyPagesCountSoldFromAPublication() throws SQLException {
        int count = inquiryDAOImpl.getTotalHardCopyPagesCountSoldFromAPublication(1, 1);
        assertEquals(count, 1600);
    }

    @Test
    public void testGetUserPurchasedAllHardCopyPublicationsIds() throws SQLException {
        List<Integer> publicatiList = inquiryDAOImpl.getUserPurchasedAllHardCopyPublicationsIds(2, 1);
        assertNotNull(publicatiList);
        assertEquals(publicatiList.size(), 1);

        assertTrue(publicatiList.contains(1));
        assertFalse(publicatiList.contains(4));
        assertFalse(publicatiList.contains(5));
    }

    @Test
    public void testGetAllUserIdsWhoPurchasedHardCopiesOfTheGiveNPublication() throws SQLException {
        List<Integer> useridList = inquiryDAOImpl.getAllUserIdsWhoPurchasedHardCopiesOfTheGiveNPublication(1, 1);
        assertNotNull(useridList);
        assertEquals(useridList.size(), 4 - 1);

        assertTrue(useridList.contains(1));
        assertTrue(useridList.contains(2));
        assertTrue(useridList.contains(4));
        assertFalse(useridList.contains(5));
    }

    @Test
    public void testGetCountOfInquriesInAState() throws SQLException {
        int count = inquiryDAOImpl.getCountOfHardCopyInquriesInAState(Inquiry.SHIPPED, 1);
        assertEquals(0, count);
    }

    @Test
    public void testGetListOfHardCopyInquiriesFromGivenInquryIdList() throws SQLException {
        List<Inquiry> inquiries = inquiryDAOImpl.getListOfHardCopyInquiriesFromGivenInquryIdList(1, 2, 1);
        assertNotNull(inquiries);
        assertEquals(inquiries.size(), 1);

        boolean contains = false;
        for (Inquiry inquiry : inquiries) {
            if (inquiry.getId() == 1)
                contains = true;
        }

        assertTrue(contains);
    }

    @Test
    public void testGetAllHardCopyInquiriesByAPublisher() throws SQLException {
        List<Inquiry> inquiries = inquiryDAOImpl.getAllHardCopyInquiriesByAPublisher(1);
        assertNotNull(inquiries);

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
            assertTrue(i.getPublicationId() == 1);
            assertTrue(i.getUserId() >= 1);
            assertTrue(i.getPublisherId() >= 1);
        }
    }

    @Test
    public void testGetAllHardCopyInquiriesInEachStateExceptTheGivenState() throws SQLException {
        List<Inquiry> inquiries = inquiryDAOImpl.getAllHardCopyInquiriesInEachStateExceptTheGivenState(1,
                Inquiry.ACCEPTED);
        assertNotNull(inquiries);

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
            assertFalse(i.getState().equals(Inquiry.ACCEPTED));
            assertTrue(i.getPrice() >= 0);
            assertTrue(i.getPublicationId() == 1);
            assertTrue(i.getUserId() >= 1);
            assertTrue(i.getPublisherId() >= 1);
        }
    }

    @Test
    public void testGetTotalCountOfHardCopyInquriesInGivenState() throws SQLException {
        int count = inquiryDAOImpl.getTotalCountOfHardCopyInquriesInGivenState(1, Inquiry.ACCEPTED);
        assertEquals(15, count);
    }

    @Test
    public void testGetSumOfAllDiscountGivenForEveryHardCopyInqury() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAllDiscountGivenForEveryHardCopyInqury(1);
        assertTrue(sum == 1.60);
    }

    @Test
    public void testGetSumOFAllDiscountGivenToEveryHardCopyInquryInGivenState() throws SQLException {
        double sum = inquiryDAOImpl.getSumOFAllDiscountGivenToEveryHardCopyInquryInGivenState(1, Inquiry.PENDING);
        assertTrue(sum == 0.10);
    }

    @Test
    public void testGetSumOfAllDiscountGivenToEveryHardCopyInquriyInAnyStateExceptGivenState() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAllDiscountGivenToEveryHardCopyInquriyInAnyStateExceptGivenState(1,
                Inquiry.PENDING);
        assertTrue(sum == 1.50);
    }

    @Test
    public void testGetSumOfAllDiscountGivenForEveryHardCopyInquryInAnyStateExceptGivenStates() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAllDiscountGivenForEveryHardCopyInquryInAnyStateExceptGivenStates(1,
                Inquiry.REJECTED, Inquiry.ACCEPTED);
        assertTrue(sum == 0.10);
    }

    @Test
    public void testGetSumOfAllDiscountGivenForEveryHardCopyInquryInGivenStates() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAllDiscountGivenForEveryHardCopyInquryInGivenStates(1, Inquiry.REJECTED,
                Inquiry.ACCEPTED);
        assertTrue(sum == 1.50);
    }

    @Test
    public void testGetSumOfAllCommissionGotFormEveryHardCopyInqury() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAllCommissionGotFormEveryHardCopyInqury(1);
        assertTrue(sum == 4.00);
    }

    @Test
    public void testGetSumOfAllCommissionGotFormEveryHardCopyInquryInGivenStates() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAllCommissionGotFormEveryHardCopyInquryInGivenStates(1, Inquiry.REJECTED,
                Inquiry.PENDING);
        assertTrue(sum == 0.25);
    }

    @Test
    public void testGetSumOfAllCommissionGotFormEveryHardCopyInquryInAnyStateExceptGivenStates() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAllCommissionGotFormEveryHardCopyInquryInAnyStateExceptGivenStates(1,
                Inquiry.REJECTED, Inquiry.PENDING);
        assertTrue(sum == 3.75);
    }

    @Test
    public void testGetSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachState() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachState(1);
        assertTrue(sum == 800);
    }

    @Test
    public void testGetSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachStateExceptGivenStates()
            throws SQLException {
        double sum = inquiryDAOImpl.getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachStateExceptGivenStates(1,
                Inquiry.REJECTED, Inquiry.ACCEPTED);
        assertTrue(sum == 50);
    }

    @Test
    public void testGetSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInGivenStates() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInGivenStates(1, Inquiry.PENDING,
                Inquiry.ACCEPTED);
        assertTrue(sum == 800);
    }

    @Test
    public void testGetSumOfEveryAuthorTotalInquryCommission() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfEveryAuthorTotalInquryCommission(1);
        assertTrue(sum == 240);
    }

    @Test
    public void testGetSumOfEveryAuthorTotalInquryCommissionInGiveStates() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfEveryAuthorTotalInquryCommissionInGiveStates(1, Inquiry.ACCEPTED);
        assertTrue(sum == 225);
    }

    @Test
    public void testGetSumOfEveryAuthorTotalInquryCommissionInAnyStateExceptGivenStates() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfEveryAuthorTotalInquryCommissionInAnyStateExceptGivenStates(1,
                Inquiry.ACCEPTED, Inquiry.REJECTED);
        assertTrue(sum == 15);
    }

    @Test
    public void testGetSumOfAnAuthorTotalCommissionInEveryHardCopyInqury() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAnAuthorTotalCommissionInEveryHardCopyInqury(1, 1);
        assertTrue(sum == 240);
    }

    @Test
    public void testGetSumOfAnAuthorTotalCommissionInGivenStatesHardCopyInquries() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAnAuthorTotalCommissionInGivenStatesHardCopyInquries(1, 1,
                Inquiry.ACCEPTED);
        assertTrue(sum == 225);
    }

    @Test
    public void testGetSumOfAnAuthorTotalCommissionInAnyStateExceptGivenStatesHardCopyInquries() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAnAuthorTotalCommissionInAnyStateExceptGivenStatesHardCopyInquries(1, 1,
                Inquiry.ACCEPTED,Inquiry.REJECTED);
        assertTrue(sum == 15);
    }
    @Test
    public void testGetSumOfAuthorHardCopyCommissionInAllInquryInAllStates() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAuthorHardCopyCommissionInAllInquryInAllStates(1, 1);
        assertTrue(sum == 240);
    }

    @Test
    public void testGetSumOfAuthorHardCopyCommissionInGivenStatesInquries() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAuthorHardCopyCommissionInGivenStatesInquries(1, 1,
                Inquiry.ACCEPTED,Inquiry.REJECTED);
        assertTrue(sum == 225);
    }

    @Test
    public void testGetSumOfAuthorHardCopyCommissionInAnyStateExceptGivenStatesInquries() throws SQLException {
        double sum = inquiryDAOImpl.getSumOfAuthorHardCopyCommissionInAnyStateExceptGivenStatesInquries(1, 1,
                Inquiry.ACCEPTED,Inquiry.REJECTED);
        assertTrue(sum == 15);
    }

    @Test
    public void testGetAuthorIdsWhosPublicationsWasSoldAsHardCopies() throws SQLException{
        List<Integer> authorIds = inquiryDAOImpl.getAuthorIdsWhosPublicationsWasSoldAsHardCopies(1);
        assertTrue(authorIds.size()==1);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
