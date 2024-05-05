package com.getposted.model;

import static org.junit.Assert.assertEquals;
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

// @Ignore
public class IsInformedDAOImplTest {

    private static IsInformedDAOImpl isInformedDAOImpl = new IsInformedDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        IsInformed isInformed;

        int id = 48;
        String notification = "Notification 48";
        Date notifiedDate = new Date(Calendar.getInstance().getTimeInMillis());
        Time notifiedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int userId = 6;
        int publisherId = 4;

        isInformed = new IsInformed(id, notification, notifiedDate, notifiedTime, userId, publisherId);

        int rowsAffected = isInformedDAOImpl.insert(isInformed);
        assertEquals(rowsAffected, 1);

        rowsAffected = isInformedDAOImpl.delete(isInformed);
        assertEquals(rowsAffected,1);

        assertNull(isInformedDAOImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        IsInformed isInformed;

        assertNull(isInformedDAOImpl.get(0));

        isInformed = isInformedDAOImpl.get(1);

        assertEquals(isInformed.getId(), 1);
        assertEquals(isInformed.getNotification(), "Notification 1");
        assertEquals(isInformed.getNotifiedDate().toString(), "2024-04-01");
        assertEquals(isInformed.getNotifiedTime().toString(), "12:00:00");
        assertEquals(isInformed.getUserId(), 1);
        assertEquals(isInformed.getPublisherId(), 1);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<IsInformed> isInformeds = isInformedDAOImpl.getAll();

        assertTrue(isInformeds.size() >= 10);

        for (IsInformed isInformed : isInformeds) {
            assertTrue(isInformed.getId() >= 1);
            assertTrue(isInformed.getNotification().length() >= 1);
            assertTrue(isInformed.getNotifiedDate().toString().length() == 10);
            assertTrue(isInformed.getNotifiedTime().toString().length() == 8);
            assertTrue(isInformed.getUserId() >= 1);
            assertTrue(isInformed.getPublisherId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        IsInformed isInformed = new IsInformed();

        int id = 24;
        String notification = "Notification 24";
        Date notifiedDate = new Date(Calendar.getInstance().getTimeInMillis());
        Time notifiedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int userId = 2;
        int publisherId = 2;

        isInformed.setId(id);
        isInformed.setNotification(notification);
        isInformed.setNotifiedDate(notifiedDate);
        isInformed.setNotifiedTime(notifiedTime);
        isInformed.setUserId(userId);
        isInformed.setPublisherId(publisherId);

        int rowsAffected = isInformedDAOImpl.insert(isInformed);
        assertEquals(rowsAffected, 1);

        isInformed = isInformedDAOImpl.get(id);
        assertNotNull(isInformed);

        assertEquals(isInformed.getId(), id);
        assertEquals(isInformed.getNotification(), notification);
        assertEquals(isInformed.getNotifiedDate().toString(), notifiedDate.toString());
        assertEquals(isInformed.getNotifiedTime().toString(), notifiedTime.toString());
        assertEquals(isInformed.getUserId(), userId);
        assertEquals(isInformed.getPublisherId(), publisherId);
    }

    @Test
    public void testUpdate() throws SQLException {
        IsInformed isInformed = new IsInformed();

        int id = 28;
        String notification = "Notification 28";
        Date notifiedDate = new Date(Calendar.getInstance().getTimeInMillis());
        Time notifiedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int userId = 4;
        int publisherId = 4;

        isInformed = new IsInformed(id, notification, notifiedDate, notifiedTime, userId, publisherId);

        int rowsAffected = isInformedDAOImpl.insert(isInformed);
        assertEquals(rowsAffected, 1);

        notification = "Notification 58";
        notifiedDate = new Date(Calendar.getInstance().getTimeInMillis());
        notifiedTime = new Time(Calendar.getInstance().getTimeInMillis());
        userId = 5;
        publisherId = 5;

        isInformed = new IsInformed(id, notification, notifiedDate, notifiedTime, userId, publisherId);

        rowsAffected = isInformedDAOImpl.update(isInformed);
        assertEquals(rowsAffected, 1);

        isInformed = isInformedDAOImpl.get(id);

        assertEquals(isInformed.getId(), id);
        assertEquals(isInformed.getNotification(), notification);
        assertEquals(isInformed.getNotifiedDate().toString(), notifiedDate.toString());
        assertEquals(isInformed.getNotifiedTime().toString(), notifiedTime.toString());
        assertEquals(isInformed.getUserId(), userId);
        assertEquals(isInformed.getPublisherId(), publisherId);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
