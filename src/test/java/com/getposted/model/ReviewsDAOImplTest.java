package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ReviewsDAOImplTest {

    private static ReviewsDAOImpl reviewsDAOImpl = new ReviewsDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Reviews reviews;

        int id = 28;
        int value = 1;
        String review = "testDelete";
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        int publicationId = 9;
        int userId = 7;

        reviews = new Reviews(id, value, review, date, publicationId, userId);

        int rowsAffected = reviewsDAOImpl.insert(reviews);
        assertEquals(rowsAffected, 1);

        assertNotNull(reviewsDAOImpl.get(id));

        rowsAffected = reviewsDAOImpl.delete(reviews);
        assertEquals(rowsAffected, 1);

        assertNull(reviewsDAOImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        Reviews reviews;
        int id = 1;
        int value = 5;
        String review = "Great book!";
        String date = "2024-04-01";
        int publicationId = 1;
        int userId = 1;

        assertNull(reviewsDAOImpl.get(0));

        reviews = reviewsDAOImpl.get(id);

        assertEquals(reviews.getId(), id);
        assertEquals(reviews.getValue(), value);
        assertEquals(reviews.getReview(), review);
        assertEquals(reviews.getDate().toString(), date);
        assertEquals(reviews.getPublicationId(), publicationId);
        assertEquals(reviews.getUserId(), userId);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Reviews> reviews = reviewsDAOImpl.getAll();
        assertTrue(reviews.size() >= 10);

        for (Reviews review : reviews) {
            assertTrue(review.getId() >= 1);
            assertTrue(review.getValue() >= 0 && review.getValue() <= 5);
            assertTrue(review.getReview().length() >= 1 || review.getReview() == null);
            assertTrue(review.getDate().toString().length() == 10);
            assertTrue(review.getPublicationId() >= 1);
            assertTrue(review.getUserId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Reviews reviews;

        int id = 24;
        int value = 4;
        String review = null;
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        int publicationId = 4;
        int userId = 1;

        reviews = new Reviews();

        reviews.setId(id);
        reviews.setValue(value);
        reviews.setReview(review);
        reviews.setDate(date);
        reviews.setPublicationId(publicationId);
        reviews.setUserId(userId);

        int rowsAffected = reviewsDAOImpl.insert(reviews);
        assertEquals(rowsAffected, 1);

        reviews = reviewsDAOImpl.get(id);

        assertEquals(reviews.getId(), id);
        assertEquals(reviews.getValue(), value);
        assertEquals(reviews.getReview(), review);
        assertEquals(reviews.getDate().toString(), date.toString());
        assertEquals(reviews.getPublicationId(), publicationId);
        assertEquals(reviews.getUserId(), userId);
    }

    @Test
    public void testUpdate() throws SQLException {
        Reviews reviews;

        int id = 28;
        int value = 2;
        String review = "testUpdate";
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        int publicationId = 2;
        int userId = 4;

        reviews = new Reviews(id, value, review, date, publicationId, userId);

        int rowsAffected = reviewsDAOImpl.insert(reviews);
        assertEquals(rowsAffected, 1);

        value = 5;
        review = "testUpdateeeeee";
        date = new Date(Calendar.getInstance().getTimeInMillis());
        publicationId = 7;
        userId = 2;

        reviews = new Reviews(id, value, review, date, publicationId, userId);

        rowsAffected = reviewsDAOImpl.update(reviews);
        assertEquals(rowsAffected, 1);

        reviews = reviewsDAOImpl.get(id);

        assertEquals(reviews.getId(), id);
        assertEquals(reviews.getValue(), value);
        assertEquals(reviews.getReview(), review);
        assertEquals(reviews.getDate().toString(), date.toString());
        assertEquals(reviews.getPublicationId(), publicationId);
        assertEquals(reviews.getUserId(), userId);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }

    @Test
    public void testGet2() throws SQLException {
        Reviews reviews;
        int id = 1;
        int value = 5;
        String review = "Great book!";
        String date = "2024-04-01";
        int publicationId = 1;
        int userId = 1;

        reviews = reviewsDAOImpl.get(1, 1);

        assertEquals(reviews.getId(), id);
        assertEquals(reviews.getValue(), value);
        assertEquals(reviews.getReview(), review);
        assertEquals(reviews.getDate().toString(), date);
        assertEquals(reviews.getPublicationId(), publicationId);
        assertEquals(reviews.getUserId(), userId);
    }

    @Test
    public void testGetAllPublicationsOrderByThereReviewValue() throws SQLException {
        List<Integer> publicationIds = reviewsDAOImpl.getAllPublicationsOrderByThereReviewValue(true);
        assertTrue(publicationIds.size() >= 10);
    }

    @Test
    public void testGetListOfPublicationsOrderByThereReviewValue() throws SQLException {
        List<Integer> publicationIds = reviewsDAOImpl.getListOfPublicationsOrderByThereReviewValue(true, 2);
        assertTrue(publicationIds.size() == 2);
    }

    @Test
    public void testGetOrderByDate() throws SQLException {
        List<Reviews> reviews = reviewsDAOImpl.getOrderByDate(1, false);
        assertTrue(reviews.size() >= 1);

        for (Reviews review : reviews) {
            assertTrue(review.getId() >= 1);
            assertTrue(review.getValue() >= 0 && review.getValue() <= 5);
            assertTrue(review.getReview().length() >= 1 || review.getReview() == null);
            assertTrue(review.getDate().toString().length() == 10);
            assertTrue(review.getPublicationId() >= 1);
            assertTrue(review.getUserId() >= 1);
        }
    }

    @Test
    public void testGetOrderByValue() throws SQLException {
        List<Reviews> reviews = reviewsDAOImpl.getOrderByValue(1, false);
        assertTrue(reviews.size() >= 1);

        for (Reviews review : reviews) {
            assertTrue(review.getId() >= 1);
            assertTrue(review.getValue() >= 0 && review.getValue() <= 5);
            assertTrue(review.getReview().length() >= 1 || review.getReview() == null);
            assertTrue(review.getDate().toString().length() == 10);
            assertTrue(review.getPublicationId() >= 1);
            assertTrue(review.getUserId() >= 1);
        }
    }

    @Test
    public void testGetRate() throws SQLException {
        double value = reviewsDAOImpl.getRate(1);
        assertTrue(value == 5.0000);
    }

    @Test
    public void testGetRatedUserIds() throws SQLException {
        List<Integer> userIds = reviewsDAOImpl.getRatedUserIds(1);
        assertNotNull(userIds);
        assertTrue(userIds.size() == 1);
        assertTrue(userIds.contains(1));
    }

    @Test
    public void testGetReviesByValue() throws SQLException {
        List<Reviews> reviews = reviewsDAOImpl.getReviesByValue(1, 5);
        assertTrue(reviews.size() >= 1);

        for (Reviews review : reviews) {
            assertTrue(review.getId() >= 1);
            assertTrue(review.getValue() == 5);
            assertTrue(review.getReview().length() >= 1 || review.getReview() == null);
            assertTrue(review.getDate().toString().length() == 10);
            assertTrue(review.getPublicationId() >= 1);
            assertTrue(review.getUserId() >= 1);
        }
    }
}
