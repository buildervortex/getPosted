package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    private static CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Category category = new Category();
        category.setCategory("testDelete");
        category.setId(27);

        int rowsAffected = categoryDAOImpl.insert(category);
        assertEquals(rowsAffected, 1);

        assertNotNull(categoryDAOImpl.get(27));

        rowsAffected = categoryDAOImpl.delete(category);
        assertEquals(rowsAffected, 1);

        assertTrue(categoryDAOImpl.get(27) == null);
    }

    @Test
    public void testGetInt() throws SQLException {
        Category category = new Category();
        category = categoryDAOImpl.get(1);
        assertEquals(category.getCategory(), "Technology");
        category = categoryDAOImpl.get(2);
        assertEquals(category.getCategory(), "Business");
    }

    @Test
    public void testGetSrting() throws SQLException {
        Category category = new Category();
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
        Category category = new Category();
        category.setCategory("testInsert");
        category.setId(21);

        int rowsAffected = categoryDAOImpl.insert(category);
        assertEquals(rowsAffected, 1);

        categoryDAOImpl.get("testInsert");
        assertNotNull(categoryDAOImpl.get("testInsert"));
        assertNotNull(categoryDAOImpl.get(21));
        assertEquals("testInsert", categoryDAOImpl.get(21).getCategory());
    }

    @Test
    public void testUpdate() throws SQLException {
        Category category = new Category();
        category.setCategory("testUpdate");
        category.setId(22);

        int rowsAffected = categoryDAOImpl.insert(category);
        assertEquals(rowsAffected, 1);

        category.setCategory("testUpdate2");
        rowsAffected = categoryDAOImpl.update(category);
        assertEquals(rowsAffected, 1);

        assertEquals(categoryDAOImpl.get(22).getCategory(), "testUpdate2");
    }

    @Test(expected = SQLException.class)
    public void testExceptionForInsert() throws SQLException {
        Category category = new Category();
        category.setCategory("testExceptionForInsert");
        categoryDAOImpl.insert(category);
        categoryDAOImpl.insert(category);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }

    @Test
    public void testGetAllCategoriesExceptGivenIds() throws SQLException {
        List<Category> categoryList = categoryDAOImpl.getAllCategoriesExceptGivenIds(1, 2);
        assertTrue(categoryList.size() >= 2);

        for (Category category : categoryList) {
            assertTrue(category.getCategory().length() >= 2);
            assertFalse(category.getId() == 1 || category.getId() == 2);
        }
    }

    @Test
    public void testGetCategoriesByGivenIds() throws SQLException {
        List<Category> categoryList = categoryDAOImpl.getCategoriesByGivenIds(1, 2);
        assertTrue(categoryList.size() == 2);

        for (Category category : categoryList) {
            assertTrue(category.getCategory().length() >= 2);
            assertTrue(category.getId() == 1 || category.getId() == 2);
        }
    }

    @Test
    public void testGetCategoriesOnAPattern() throws SQLException {
        List<Category> categoryList = categoryDAOImpl.getCategoriesOnAPattern("ar");
        assertTrue(categoryList.size() == 1);

        for (Category category : categoryList) {
            assertTrue(category.getCategory().length() >= 2);
        }
    }

    @Test
    public void testGetCategoryCount() throws SQLException {
        int count = categoryDAOImpl.getCategoryCount();
        assertTrue(count >= 10);
    }
}
