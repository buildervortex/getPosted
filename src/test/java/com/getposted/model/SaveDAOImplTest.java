package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
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
    public void testDelete() throws SQLException{
        save.setPublicationId(2);
        save.setUserId(4);
        saveDAOImpl.insert(save);
        saveDAOImpl.delete(save);
        List<Save> saves = saveDAOImpl.getList(1);
        boolean contains = false;
        for (Save save : saves){
            if(save.getUserId() == 4 && save.getPublicationId() == 2){
                contains = true;
            }
        }
        assertFalse(contains);
    }

    @Test
    public void testGetAll()  throws SQLException{
        List<Save> saves = saveDAOImpl.getAll();
        assertTrue(saves.size() >= 20);

        for (Save save : saves) {
            assertTrue(save.getUserId() >= 0);
        }
    }

    @Test
    public void testGetList() throws SQLException{
        List<Save> saves = saveDAOImpl.getList(2);

        for (Save save : saves) {
            assertTrue(save.getUserId() == 2);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        save.setPublicationId(4);
        save.setUserId(1);
        saveDAOImpl.insert(save);
        List<Save> saves = saveDAOImpl.getList(1);
        boolean changed = false;
        for (Save save : saves){
            if(save.getUserId() == 1 && save.getPublicationId() == 4){
                changed = true;
            }
        }
        assertTrue(changed);

    }

    @Test
    public void testUpdate() {

    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
