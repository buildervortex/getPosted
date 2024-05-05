package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore()
public class SaveDAOImplTest {

    private static Save save = new Save();
    private static SaveDAOImpl saveDAOImpl = new SaveDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        save.setPublicationId(2);
        save.setUserId(4);
        save.setDate(new Date(Calendar.getInstance().getTimeInMillis()));

        int rowsAffected = saveDAOImpl.insert(save);
        assertEquals(rowsAffected, 1);

        rowsAffected = saveDAOImpl.delete(save);
        assertEquals(rowsAffected, 1);

        List<Save> saves = saveDAOImpl.getList(4);
        boolean contains = false;
        for (Save save : saves) {
            if (save.getUserId() == 4 && save.getPublicationId() == 2) {
                contains = true;
            }
        }
        assertFalse(contains);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Save> saves = saveDAOImpl.getAll();
        assertTrue(saves.size() >= 10);

        for (Save save : saves) {
            assertTrue(save.getUserId() >= 0);
            assertTrue(save.getPublicationId() >= 0);
            assertTrue(save.getDate().toString().length() == 10);
        }
    }

    @Test
    public void testGetList() throws SQLException {
        List<Save> saves = saveDAOImpl.getList(2);

        for (Save save : saves) {
            assertTrue(save.getUserId() == 2);
            assertTrue(save.getPublicationId() >= 0);
            assertTrue(save.getDate().toString().length() == 10);
        }
    }

    @Test
    public void testInsert() throws SQLException {

        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        int publicationId = 4;
        int userId = 1;

        save.setDate(date);
        save.setPublicationId(publicationId);
        save.setUserId(userId);

        int rowsAffected = saveDAOImpl.insert(save);
        assertEquals(rowsAffected, 1);

        List<Save> saves = saveDAOImpl.getList(userId);
        boolean changed = false;
        for (Save save : saves) {
            if (save.getUserId() == userId && save.getPublicationId() == publicationId) {
                changed = true;
            }
        }
        assertTrue(changed);

    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
