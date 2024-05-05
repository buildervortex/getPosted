package com.getposted.model;

import static org.junit.Assert.assertEquals;
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

// @Ignore
public class PurchaseDAOImplTest {

    private static PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();

    @BeforeClass
    public static void createDatabase(){
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
        assertEquals(result,1);
        
        result = purchaseDAOImpl.delete(purchase);
        assertEquals(result,1);

        assertNull(purchaseDAOImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        Purchase purchase;

        purchase = purchaseDAOImpl.get(1);

        assertEquals(purchase.getId(),1);
        assertEquals(purchase.getPurchasedDate().toString(),"2024-04-01");
        assertTrue(purchase.getPrice()==19.99);
        assertEquals(purchase.getPurchasedTime().toString(),"12:00:00");
        assertEquals(purchase.getUserId(),1);
        assertEquals(purchase.getPublisherId(),1);
        assertEquals(purchase.getPublicationId(),1);

    }

    @Test
    public void testGetAll() throws SQLException {
        List<Purchase> purchases = purchaseDAOImpl.getAll();

        assertTrue(purchases.size()>=10);

        for(Purchase purchase : purchases){
            assertTrue(purchase.getId()>=1);
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
        // (id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId) VALUES(1, '2024-04-01', 19.99, '12:00:00', 1, 1, 1)
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
        // (id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId) VALUES(1, '2024-04-01', 19.99, '12:00:00', 1, 1, 1)
        int id = 28;
        Date purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        double price = 49.99;
        Time purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int userId = 4;
        int publisherId = 4;
        int publicationId = 4;

        purchase = new Purchase(id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId);

        int result = purchaseDAOImpl.insert(purchase);
        assertEquals(result,1);

        purchasedDate = new Date(Calendar.getInstance().getTimeInMillis());
        price = 59.99;
        purchasedTime = new Time(Calendar.getInstance().getTimeInMillis());
        userId = 5;
        publisherId = 5;
        publicationId = 5;

        purchase = new Purchase(id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId);

        result = purchaseDAOImpl.update(purchase);

        assertEquals(result,1);
        purchase = purchaseDAOImpl.get(id);
        assertEquals(purchase.getId(), id);
        assertEquals(purchase.getPurchasedDate().toString(), purchasedDate.toString());
        assertTrue(purchase.getPrice() == price);
        assertEquals(purchase.getPurchasedTime().toString(), purchasedTime.toString());
        assertEquals(purchase.getUserId(), userId);
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
