package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

// @Ignore
public class RatesDAOImplTest {

    private static RatesDAOImpl ratesDAOImpl = new RatesDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Rates rates;

        int value = 4;
        String review = "delete";
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        int authorId = 9;
        int userId = 2;

        rates = new Rates(value, review, date, authorId, userId);

        int rowsAffected = ratesDAOImpl.insert(rates);
        assertEquals(rowsAffected, 1);
        assertNotNull(ratesDAOImpl.get(authorId, userId));

        rowsAffected = ratesDAOImpl.delete(rates);
        assertEquals(rowsAffected, 1);

        assertNull(ratesDAOImpl.get(authorId, userId));

    }

    @Test
    public void testGet() throws SQLException {
        Rates rates;

        int authorId = 1;
        int userId = 1;

        assertNull(ratesDAOImpl.get(0, 0));

        rates = ratesDAOImpl.get(authorId, userId);

        assertEquals(rates.getValue(), 5);
        assertEquals(rates.getReview(), "Great book!");
        assertEquals(rates.getDate().toString(), "2024-04-01");
        assertEquals(rates.getAuthorId(), authorId);
        assertEquals(rates.getUserId(), userId);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Rates> rates = ratesDAOImpl.getAll();
        assertTrue(rates.size() >= 10);

        for (Rates rate : rates) {
            assertTrue(rate.getValue() >= 0 && rate.getValue() <= 5);
            assertTrue(rate.getReview().length() >= 1 || rate.getReview() == null);
            assertTrue(rate.getDate().toString().length() == 10);
            assertTrue(rate.getAuthorId() >= 1);
            assertTrue(rate.getUserId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Rates rates;

        int value = 4;
        String review = null;
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        int authorId = 4;
        int userId = 1;

        rates = new Rates();

        rates.setValue(value);
        rates.setReview(review);
        rates.setDate(date);
        rates.setAuthorId(authorId);
        rates.setUserId(userId);

        int rowsAffected = ratesDAOImpl.insert(rates);
        assertEquals(rowsAffected, 1);

        rates = ratesDAOImpl.get(authorId, userId);

        assertEquals(rates.getValue(), value);
        assertEquals(rates.getReview(), review);
        assertEquals(rates.getDate().toString(), date.toString());
        assertEquals(rates.getAuthorId(), authorId);
        assertEquals(rates.getUserId(), userId);
    }

    @Test
    public void testUpdate() throws SQLException {
        Rates rates;

        int value = 1;
        String review = "update";
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        int authorId = 7;
        int userId = 1;

        rates = new Rates(value, review, date, authorId, userId);

        int rowsAffected = ratesDAOImpl.insert(rates);
        assertEquals(rowsAffected, 1);

        value = 5;
        review = "hello world";
        date = new Date(Calendar.getInstance().getTimeInMillis());

        rates = new Rates(value, review, date, authorId, userId);

        rowsAffected = ratesDAOImpl.update(rates);
        assertEquals(rowsAffected, 1);

        rates = ratesDAOImpl.get(authorId, userId);

        assertEquals(rates.getValue(), value);
        assertEquals(rates.getReview(), review);
        assertEquals(rates.getDate().toString(), date.toString());
        assertEquals(rates.getAuthorId(), authorId);
        assertEquals(rates.getUserId(), userId);
    }

    @Test
    public void testGetRate() throws SQLException {
        double rate = ratesDAOImpl.getRate(2);
        assertTrue(rate == 4.3333);
    }

    @Test
    public void testGetAllAuthorsOrderByThereRateValue() throws SQLException {
        List<Integer> authorList = ratesDAOImpl.getAllAuthorsOrderByThereRateValue(true);
        assertTrue(authorList.size() >= 10);
        assertTrue(authorList.get(0) == 2);
    }

    @Test
    public void testGetListOfAuthorsOrderByThereRateValue() throws SQLException {
        List<Integer> authorList = ratesDAOImpl.getListOfAuthorsOrderByThereRateValue(true,2);
        assertEquals(2,authorList.size());
        assertTrue(authorList.get(0) == 2);
    }

    @Test
    public void testGetOrderByValue() throws SQLException {
        List<Rates> rates = ratesDAOImpl.getOrderByValue(2, false);
        assertTrue(rates.size() >= 3);

        for (Rates rate : rates) {
            assertTrue(rate.getValue() >= 0 && rate.getValue() <= 5);
            assertTrue(rate.getReview().length() >= 1 || rate.getReview() == null);
            assertTrue(rate.getDate().toString().length() == 10);
            assertTrue(rate.getAuthorId() >= 1);
            assertTrue(rate.getUserId() >= 1);
        }
    }

    @Test
    public void testGetOrderedByDate() throws SQLException {
        List<Rates> rates = ratesDAOImpl.getOrderedByDate(2, false);
        assertTrue(rates.size() >= 3);

        assertEquals(rates.get(0).getDate().toString(), Date.valueOf("2024-04-02").toString());

        for (Rates rate : rates) {
            assertTrue(rate.getValue() >= 0 && rate.getValue() <= 5);
            assertTrue(rate.getReview().length() >= 1 || rate.getReview() == null);
            assertTrue(rate.getDate().toString().length() == 10);
            assertTrue(rate.getAuthorId() >= 1);
            assertTrue(rate.getUserId() >= 1);
        }
    }

    @Test
    public void testGetRatedUserIds() throws SQLException {
        List<Integer> userIds = ratesDAOImpl.getRatedUserIds(2);
        assertTrue(userIds.contains(1));
        assertTrue(userIds.contains(2));
        assertTrue(userIds.contains(3 - 1));
        assertFalse(userIds.contains(4));
    }

    @Test
    public void testGetRatesByValue() throws SQLException {
        List<Rates> rates = ratesDAOImpl.getRatesByValue(2,4);
        assertTrue(rates.size() >= 2);

        for (Rates rate : rates) {
            assertTrue(rate.getValue() == 4);
            assertTrue(rate.getReview().length() >= 1 || rate.getReview() == null);
            assertTrue(rate.getDate().toString().length() == 10);
            assertTrue(rate.getAuthorId() >= 1);
            assertTrue(rate.getUserId() >= 1);
        }
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }

}