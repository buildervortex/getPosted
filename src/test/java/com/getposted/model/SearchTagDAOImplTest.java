package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchTagDAOImplTest {

    private static SearchTag searchTag = new SearchTag();
    private static SearchTagDAOImpl searchTagDAOImpl = new SearchTagDAOImpl();

    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }
    @Test
    public void testDelete() throws SQLException{

        searchTag.setPublicationId(9);
        searchTag.setTagName("testDelete");
        searchTagDAOImpl.insert(searchTag);
        searchTagDAOImpl.delete(searchTag);
    }

    @Test
    public void testGet() throws SQLException {
        // 'programming', 1
        searchTag = searchTagDAOImpl.get(1);
        assertEquals(searchTag.getPublicationId(), 1);
        assertEquals(searchTag.getTagName(), "coding");
    }

    @Test
    public void testGetAll() throws SQLException {
        List<SearchTag> searchTags = searchTagDAOImpl.getAll();
        assertTrue(searchTags.size() >= 20);

        for (SearchTag searchTag : searchTags) {
            assertTrue(searchTag.getTagName().length() >= 2);
        }
    }

    @Test
    public void testInsert() throws SQLException{

        searchTag.setPublicationId(4);
        searchTag.setTagName("codding");
        searchTagDAOImpl.insert(searchTag);
        assertEquals(searchTagDAOImpl.get(4).getTagName(), "codding");
        assertEquals(searchTagDAOImpl.get(4).getPublicationId(), 4);
    }

    @Test
    public void testUpdate() throws SQLException{

        searchTag.setPublicationId(8);
        searchTag.setTagName("nano");
        searchTagDAOImpl.insert(searchTag);
        searchTag.setPublicationId(8);
        searchTag.setTagName("nano2");
        searchTagDAOImpl.update(searchTag);
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
