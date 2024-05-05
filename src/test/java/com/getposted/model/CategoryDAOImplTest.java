package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

// @Ignore("Skipped CategoryDAOImpTest")
public class CategoryDAOImplTest {

    private static Category category = new Category();
    private static CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        category.setCategory("testDelete");
        category.setId(27);
        categoryDAOImpl.insert(category);
        assertNotNull(categoryDAOImpl.get(27));

        categoryDAOImpl.delete(category);

        assertTrue(categoryDAOImpl.get(27) == null);
    }

    @Test
    public void testGetInt() throws SQLException {
        category = categoryDAOImpl.get(1);
        assertEquals(category.getCategory(), "Technology");
        category = categoryDAOImpl.get(2);
        assertEquals(category.getCategory(), "Business");
    }

    @Test
    public void testGetSrting() throws SQLException {
        category = categoryDAOImpl.get("Technology");
        assertEquals(1, category.getId());
        category = categoryDAOImpl.get("Science");
        assertEquals(10, category.getId());
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Category> categoryList = categoryDAOImpl.getAll();
        assertTrue(categoryList.size() >= 10);

        for (Category category : categoryList) {
            assertTrue(category.getCategory().length() >= 2);
        }
    }

    @Test
    public void testGetList() throws SQLException {
        List<Category> categoryList = categoryDAOImpl.getList(5);
        assertTrue(categoryList.size() == 5);

        for (Category category : categoryList) {
            assertTrue(category.getCategory().length() >= 2);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        category.setCategory("testInsert");
        category.setId(21);

        categoryDAOImpl.insert(category);

        categoryDAOImpl.get("testInsert");
        assertNotNull(categoryDAOImpl.get("testInsert"));
        assertNotNull(categoryDAOImpl.get(21));
        assertEquals("testInsert", categoryDAOImpl.get(21).getCategory());
    }

    @Test
    public void testUpdate() throws SQLException {
        category.setCategory("testUpdate");
        category.setId(22);

        categoryDAOImpl.insert(category);

        category.setCategory("testUpdate2");
        categoryDAOImpl.update(category);

        assertEquals(categoryDAOImpl.get(22).getCategory(), "testUpdate2");
    }

    @Test(expected = SQLException.class)
    public void testExceptionForInsert() throws SQLException {
        category.setCategory("testExceptionForInsert");
        categoryDAOImpl.insert(category);
        categoryDAOImpl.insert(category);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
