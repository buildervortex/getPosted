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

// @Ignore
public class HasDAOImplTest {

    private static HasDAOImpl hasDAOImpl = new HasDAOImpl();

    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Has has = new Has();

        has.setAuthorId(2);
        has.setSkillId(4);

        int rowsAffected = hasDAOImpl.insert(has);
        assertEquals(rowsAffected, 1);
        rowsAffected = hasDAOImpl.delete(has);
        assertEquals(rowsAffected, 1);

        List<Has> have = hasDAOImpl.getList(1);
        boolean contains = false;
        for (Has qhas : have){
            if(qhas.getAuthorId() == 2 && qhas.getSkillId() == 4){
                contains = true;
            }
        }
        assertFalse(contains);
    }

    @Test
    public void testGetAll() throws SQLException {

        List<Has> have = hasDAOImpl.getAll();
        assertTrue(have.size() >= 10);

        for (Has has : have) {
            assertTrue(has.getAuthorId() >= 0);
            assertTrue(has.getSkillId()>= 0);
        }
    }

    @Test
    public void testGetList() throws SQLException {
        List<Has> have = hasDAOImpl.getList(2);

        for (Has has : have) {
            assertTrue(has.getAuthorId() == 2);
            assertTrue(has.getSkillId()>= 0);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Has has = new Has();
        int authorId = 4;
        int skillId = 1;

        has.setAuthorId(authorId);
        has.setSkillId(skillId);

        int rowsAffected = hasDAOImpl.insert(has);
        assertEquals(rowsAffected, 1);

        List<Has> have = hasDAOImpl.getList(authorId);
        boolean changed = false;
        for (Has qhas : have){
            if(qhas.getAuthorId() == authorId && has.getSkillId() == skillId){
                changed = true;
            }
        }
        assertTrue(changed);
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
