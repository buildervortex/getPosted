package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PurchaseDAOImplTest {

    private static PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Purchase purchase;
        int id = 40;
        Date purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        double price = 89.99;
        Time purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int userId = 2;
        int publisherId = 2;
        int publicationId = 2;

        purchase = new Purchase(id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId);

        int result = purchaseDAOImpl.insert(purchase);
        assertEquals(result, 1);

        result = purchaseDAOImpl.delete(purchase);
        assertEquals(result, 1);

        assertNull(purchaseDAOImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        Purchase purchase;

        purchase = purchaseDAOImpl.get(1);

        assertEquals(purchase.getId(), 1);
        assertEquals(purchase.getPurchasedDate().toString(), "2024-04-01");
        assertTrue(purchase.getPrice() == 19.99);
        assertEquals(purchase.getPurchasedTime().toString(), "12:00:00");
        assertEquals(purchase.getUserId(), 1);
        assertEquals(purchase.getPublisherId(), 1);
        assertEquals(purchase.getPublicationId(), 1);

    }

    @Test
    public void testGetAll() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAll();

        assertTrue(purchases.size() >= 10);

        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertTrue(purchase.getPurchasedDate().toString().length() == 10);
            assertTrue(purchase.getPrice() >= 0.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() >= 1);
            assertTrue(purchase.getPublicationId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Purchase purchase;
        int id = 24;
        Date purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        double price = 19.99;
        Time purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int userId = 2;
        int publisherId = 2;
        int publicationId = 2;

        purchase = new Purchase(id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId);

        int result = purchaseDAOImpl.insert(purchase);
        assertEquals(result, 1);

        purchase = purchaseDAOImpl.get(id);
        assertEquals(purchase.getId(), id);
        assertEquals(purchase.getPurchasedDate().toString(), purchasedDate.toString());
        assertTrue(purchase.getPrice() == price);
        assertEquals(purchase.getPurchasedTime().toString(), purchasedTime.toString());
        assertEquals(purchase.getUserId(), userId);

    }

    @Test
    public void testUpdate() throws SQLException {
        Purchase purchase;
        // (id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId)
        // VALUES(1, '2024-04-01', 19.99, '12:00:00', 1, 1, 1)
        int id = 28;
        Date purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        double price = 49.99;
        Time purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int userId = 4;
        int publisherId = 4;
        int publicationId = 4;

        purchase = new Purchase(id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId);

        int result = purchaseDAOImpl.insert(purchase);
        assertEquals(result, 1);

        purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        price = 59.99;
        purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        userId = 5;
        publisherId = 5;
        publicationId = 5;

        purchase = new Purchase(id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId);

        result = purchaseDAOImpl.update(purchase);

        assertEquals(result, 1);
        purchase = purchaseDAOImpl.get(id);
        assertEquals(purchase.getId(), id);
        assertEquals(purchase.getPurchasedDate().toString(), purchasedDate.toString());
        assertTrue(purchase.getPrice() == price);
        assertEquals(purchase.getPurchasedTime().toString(), purchasedTime.toString());
        assertEquals(purchase.getUserId(), userId);
    }

    @Test
    public void testGetAllPurchasesCount() throws SQLException {
        int count = purchaseDAOImpl.getAllPurchasesCount(1);
        assertEquals(1, count);
    }

    @Test
    public void testGetSumOfAllSoftCopyPurchasesPrices() throws SQLException {
        double sum = purchaseDAOImpl.getSumOfAllSoftCopyPurchasesPrices(1);
        assertTrue(sum == 19.99);
    }

    @Test
    public void testGetTotalUserCountWhoPurchasedSoftCopies() throws SQLException {
        int count = purchaseDAOImpl.getTotalUserCountWhoPurchasedSoftCopies(1);
        assertEquals(1, count);
    }

    @Test
    public void testGetAllOrderedPurchasesInAPublisher() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAllOrderedPurchasesInAPublisher(1);

        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertTrue(purchase.getPurchasedDate().toString().length() == 10);
            assertTrue(purchase.getPrice() >= 0.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() == 1);
            assertTrue(purchase.getPublicationId() >= 1);
        }
    }

    @Test
    public void testGetAllPurchasesOrderdByPrice() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAllPurchasesOrderdByPrice(false, 1);

        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertTrue(purchase.getPurchasedDate().toString().length() == 10);
            assertTrue(purchase.getPrice() >= 0.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() == 1);
            assertTrue(purchase.getPublicationId() >= 1);
        }
    }

    @Test
    public void testGetAllPurchasesInSpecifiedDate() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAllPurchasesInSpecifiedDate(Date.valueOf("2024-04-01"), 1);

        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertEquals(purchase.getPurchasedDate().toString(), "2024-04-01");
            assertTrue(purchase.getPrice() >= 0.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() == 1);
            assertTrue(purchase.getPublicationId() >= 1);
        }
    }

    @Test
    public void testGetLIstOfPurchasesInSpecifiedDate() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getLIstOfPurchasesInSpecifiedDate(Date.valueOf("2024-04-01"), 1, 1);

        assertTrue(purchases.size() <= 1);
        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertEquals(purchase.getPurchasedDate().toString(), "2024-04-01");
            assertTrue(purchase.getPrice() >= 0.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() == 1);
            assertTrue(purchase.getPublicationId() >= 1);
        }
    }

    @Test
    public void testGetAllPurchasesInSpecifiedDateRange() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAllPurchasesInSpecifiedDateRange(Date.valueOf("2000-01-01"),
                Date.valueOf("2088-04-01"), 1);

        assertTrue(purchases.size() <= 1);
        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertEquals(purchase.getPurchasedDate().toString(), "2024-04-01");
            assertTrue(purchase.getPrice() >= 0.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() == 1);
            assertTrue(purchase.getPublicationId() >= 1);
        }
    }

    @Test
    public void testGetAllPurchasesInSpecifiedPriceRange() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAllPurchasesInSpecifiedPriceRange(0.00, 100.0, 1);

        assertTrue(purchases.size() <= 1);
        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertTrue(purchase.getPurchasedDate().toString().length() == 10);
            assertTrue(purchase.getPrice() >= 0.0 && purchase.getPrice() <= 100.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() == 1);
            assertTrue(purchase.getPublicationId() >= 1);
        }
    }

    @Test
    public void testGetAllPurchasesOfAUser() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAllPurchasesOfAUser(1, 1);

        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertTrue(purchase.getPurchasedDate().toString().length() == 10);
            assertTrue(purchase.getPrice() >= 0.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() == 1);
            assertTrue(purchase.getPublicationId() >= 1);
        }
    }

    @Test
    public void testGetAUserPurchasesCount() throws SQLException {
        int count = purchaseDAOImpl.getAUserPurchasesCount(1, 1);
        assertEquals(1, count);
    }

    @Test
    public void getAllPurchasesOfAPublicationId() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAllPurchasesOfAPublicationId(1, 1);
        assertNotNull(purchases);

        for (Purchase purchase : purchases) {
            assertTrue(purchase.getId() >= 1);
            assertTrue(purchase.getPurchasedDate().toString().length() == 10);
            assertTrue(purchase.getPrice() >= 0.0);
            assertTrue(purchase.getPurchasedTime().toString().length() == 8);
            assertTrue(purchase.getUserId() >= 1);
            assertTrue(purchase.getPublisherId() == 1);
            assertTrue(purchase.getPublicationId() == 1);
        }
    }

    @Test
    public void testGetAPublicationPurchasesCount() throws SQLException {
        int count = purchaseDAOImpl.getAPublicationPurchasesCount(1, 1);
        assertEquals(1, count);
    }

    @Test
    public void testGetSumOfAllPurchasesPricesOfAUser() throws SQLException {
        double count = purchaseDAOImpl.getSumOfAllPurchasesPricesOfAUser(1, 1);
        assertTrue(count == 19.99);
    }

    @Test
    public void testGetSumOfAllPurchasesPricesOfAPulication() throws SQLException {
        double count = purchaseDAOImpl.getSumOfAllPurchasesPricesOfAPulication(1, 1);
        assertTrue(count == 19.99);
    }

    @Test
    public void testGetUserPurchasedPublicationsIds() throws SQLException {
        List<Integer> userPurchasedPublicationsIds = purchaseDAOImpl.getUserPurchasedPublicationsIds(1, 1);
        assertTrue(userPurchasedPublicationsIds.contains(1));
        assertFalse(userPurchasedPublicationsIds.contains(2));
    }

    @Test
    public void testGetPublicationPurchasedUserIds() throws SQLException {
        List<Integer> userIds = purchaseDAOImpl.getPublicationPurchasedUserIds(1, 1);
        assertTrue(userIds.contains(1));
        assertFalse(userIds.contains(2));
    }

    @Test
    public void testGetAllPulicationIdsOrderdInTopSelling() throws SQLException {
        List<Integer> userIds = purchaseDAOImpl.getAllPulicationIdsOrderdInTopSelling(1, true);
        assertTrue(userIds.contains(1));
        assertFalse(userIds.contains(2));
    }

    @Test
    public void testGetListOfPulicationIdsOrderdInTopSelling() throws SQLException {
        List<Integer> userIds = purchaseDAOImpl.getListOfPulicationIdsOrderdInTopSelling(1, true, 1);
        assertTrue(userIds.contains(1));
        assertFalse(userIds.contains(2));
    }

    @Test
    public void testGetSumOfAllDiscountsGivenToUsersByAllAuthors() throws SQLException {
        double count = purchaseDAOImpl.getSumOfAllDiscountsGivenToUsersByAllAuthors(1);
        assertTrue(count == 0.15);
    }

    @Test
    public void testGetSumOfAllDiscountsGivenToUsersByAnAuthor() throws SQLException {
        double count = purchaseDAOImpl.getSumOfAllDiscountsGivenToUsersByAnAuthor(1,1);
        assertTrue(count == 0.15);
    }

    @Test
    public void testGetTotalDiscountsGotByAnUser() throws SQLException {
        double count = purchaseDAOImpl.getTotalDiscountsGotByAnUser(1,1);
        assertTrue(count == 0.15);
    }

    @Test
    public void testGetRecivedSoftCopyCommissionFromAllPurchases() throws SQLException {
        double count = purchaseDAOImpl.getRecivedSoftCopyCommissionFromAllPurchases(1);
        assertTrue(count == 0.20);
    }

    @Test
    public void testGetRecivedSoftCopyCommissionFromAPublication() throws SQLException {
        double count = purchaseDAOImpl.getRecivedSoftCopyCommissionFromAPublication(1,4);
        assertTrue(count == 0);
    }

    @Test
    public void testGetRecivedSoftCopyCommissionFromAUser() throws SQLException {
        double count = purchaseDAOImpl.getRecivedSoftCopyCommissionFromAUser(1,1);
        assertTrue(count == 0.2);
    }

    @Test
    public void testGetRecivedSoftCopyCommissionFromAnAuthor() throws SQLException {
        double count = purchaseDAOImpl.getRecivedSoftCopyCommissionFromAnAuthor(1,1);
        assertTrue(count == 0.2);
    }

    @Test
    public void testGetAllAuthorIdsOrderdInTopSelling() throws SQLException {
        List<Integer> count = purchaseDAOImpl.getAllAuthorIdsOrderdInTopSelling(1,true);
        assertTrue(count.contains(1));
        assertFalse(count.contains(2));
    }

    @Test
    public void testGetListOfAuthorIdsOrderdInTopSelling() throws SQLException {
        List<Integer> count = purchaseDAOImpl.getListOfAuthorIdsOrderdInTopSelling(1,true,1);
        assertTrue(count.contains(1));
        assertFalse(count.contains(2));
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
