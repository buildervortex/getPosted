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

@Ignore
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

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}