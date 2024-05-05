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

public class IsNotifiedByDAOImplTest {

    private static IsNotifiedByDAOImpl isNotifiedDAOImpl = new IsNotifiedByDAOImpl();

    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }
    
    @Test
    public void testDelete() throws SQLException {
        IsNotifiedBy isNotifiedBy;

        int id = 48;
        String notifications = "Notification 48";
        Date notifiedDate = new Date(Calendar.getInstance().getTimeInMillis());
        Time notifiedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int authorId = 6;
        int publisherId = 4;

        isNotifiedBy = new IsNotifiedBy(id, notifications, notifiedDate, notifiedTime, authorId, publisherId);

        int result = isNotifiedDAOImpl.insert(isNotifiedBy);
        assertEquals(result,1);

        result = isNotifiedDAOImpl.delete(isNotifiedBy);

        assertNull(isNotifiedDAOImpl.get(id));
    }

    
    @Test
    public void testGet() throws SQLException {
        IsNotifiedBy isNotifiedBy;

        assertNull(isNotifiedDAOImpl.get(0));
        
        isNotifiedBy = isNotifiedDAOImpl.get(1);

        assertEquals(isNotifiedBy.getId(),1);
        assertEquals(isNotifiedBy.getNotification(),"Notification 1");
        assertEquals(isNotifiedBy.getNotifiedDate().toString(),"2024-04-01");
        assertEquals(isNotifiedBy.getNotifiedTime().toString(),"12:00:00");
        assertEquals(isNotifiedBy.getAuthorId(),1);
        assertEquals(isNotifiedBy.getPublisherId(),1);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<IsNotifiedBy> isInformeds = isNotifiedDAOImpl.getAll();

        assertTrue(isInformeds.size() >= 10);

        for (IsNotifiedBy isNotifiedBy :isInformeds){
            assertTrue(isNotifiedBy.getId()>=1);
            assertTrue(isNotifiedBy.getNotification().length()>=1);
            assertTrue(isNotifiedBy.getNotifiedDate().toString().length() == 10);
            assertTrue(isNotifiedBy.getNotifiedTime().toString().length() == 8);
            assertTrue(isNotifiedBy.getAuthorId() >= 1);
            assertTrue(isNotifiedBy.getPublisherId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        IsNotifiedBy isNotifiedBy = new IsNotifiedBy();

        int id = 24;
        String notifications = "Notification 24";
        Date notifiedDate = new Date(Calendar.getInstance().getTimeInMillis());
        Time notifiedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int authorId = 2;
        int publisherId = 2;
        

        isNotifiedBy.setId(id);
        isNotifiedBy.setNotification(notifications);
        isNotifiedBy.setNotifiedDate(notifiedDate);
        isNotifiedBy.setNotifiedTime(notifiedTime);
        isNotifiedBy.setAuthorId(authorId);
        isNotifiedBy.setPublisherId(publisherId);

        int result = isNotifiedDAOImpl.insert(isNotifiedBy);
        assertEquals(result,1);

        isNotifiedBy = isNotifiedDAOImpl.get(id);
        assertNotNull(isNotifiedBy);

        assertEquals(isNotifiedBy.getId(),id);
        assertEquals(isNotifiedBy.getNotification(),notifications);
        assertEquals(isNotifiedBy.getNotifiedDate().toString(),notifiedDate.toString());
        assertEquals(isNotifiedBy.getNotifiedTime().toString(),notifiedTime.toString());
        assertEquals(isNotifiedBy.getAuthorId(),authorId);
        assertEquals(isNotifiedBy.getPublisherId(),publisherId);
    }

    @Test
    public void testUpdate() throws SQLException {
        IsNotifiedBy isNotifiedBy = new IsNotifiedBy();

        int id = 28;
        String notifications = "Notification 28";
        Date notifiedDate = new Date(Calendar.getInstance().getTimeInMillis());
        Time notifiedTime = new Time(Calendar.getInstance().getTimeInMillis());
        int authorId = 4;
        int publisherId = 4;

        isNotifiedBy = new IsNotifiedBy(id, notifications, notifiedDate, notifiedTime, authorId, publisherId);
        int result = isNotifiedDAOImpl.insert(isNotifiedBy);
        assertEquals(result,1);
        
        notifications = "Notification 58";
        notifiedDate = new Date(Calendar.getInstance().getTimeInMillis());
        notifiedTime = new Time(Calendar.getInstance().getTimeInMillis());
        authorId = 5;
        publisherId = 5;

        isNotifiedBy = new IsNotifiedBy(id, notifications, notifiedDate, notifiedTime, authorId, publisherId);
        result = isNotifiedDAOImpl.update(isNotifiedBy);
        assertEquals(result,1);

        isNotifiedBy = isNotifiedDAOImpl.get(id);

        assertEquals(isNotifiedBy.getId(),id);
        assertEquals(isNotifiedBy.getNotification(),notifications);
        assertEquals(isNotifiedBy.getNotifiedDate().toString(),notifiedDate.toString());
        assertEquals(isNotifiedBy.getNotifiedTime().toString(),notifiedTime.toString());
        assertEquals(isNotifiedBy.getAuthorId(),authorId);
        assertEquals(isNotifiedBy.getPublisherId(),publisherId);
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
