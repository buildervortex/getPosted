package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

// @Ignore()
public class SearchTagDAOImplTest {

    private static SearchTagDAOImpl searchTagDAOImpl = new SearchTagDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        SearchTag searchTag = new SearchTag();

        searchTag.setPublicationId(9);
        searchTag.setTagName("testDelete");

        int rowsAffected = searchTagDAOImpl.insert(searchTag);
        assertEquals(rowsAffected, 1);

        rowsAffected = searchTagDAOImpl.delete(searchTag);
        assertEquals(rowsAffected, 1);
    }

    @Test
    public void testGetList() throws SQLException {
        List<SearchTag> tags = searchTagDAOImpl.getList(1);
        assertTrue(tags.size() >= 1);

        for (SearchTag searchTag : tags) {
            assertTrue(searchTag.getPublicationId() >= 1);
            assertTrue(searchTag.getTagName().length() >= 1);
        }
    }

    @Test
    public void testGetAll() throws SQLException {
        List<SearchTag> searchTags = searchTagDAOImpl.getAll();
        assertTrue(searchTags.size() >= 10);

        for (SearchTag searchTag : searchTags) {
            assertTrue(searchTag.getTagName().length() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        SearchTag searchTag = new SearchTag();

        searchTag.setPublicationId(4);
        searchTag.setTagName("codding");

        int rowsAffected = searchTagDAOImpl.insert(searchTag);
        assertEquals(rowsAffected, 1);

        boolean changed = false;

        for (SearchTag tag : searchTagDAOImpl.getList(4)) {
            if (tag.getPublicationId() == 4 && tag.getTagName().equals("codding"))
                changed = true;
        }
        assertTrue(changed);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
