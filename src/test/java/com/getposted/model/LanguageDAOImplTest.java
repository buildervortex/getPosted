package com.getposted.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

// @Ignore("LanguageDAOImplTest")
public class LanguageDAOImplTest {

    private static LanguageDAOImpl languageDAOImpl = new LanguageDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testGetData() throws SQLException {
        Language language = new Language();
        language = languageDAOImpl.get(1);
        assertTrue(language.getId() == 1);
        assertTrue(language.getLanguage().equals("English"));
        language = languageDAOImpl.get(2);
        assertTrue(language.getId() == 2);
        assertTrue(language.getLanguage().equals("Spanish"));
        language = languageDAOImpl.get(10);
        assertTrue(language.getId() == 10);
        assertTrue(language.getLanguage().equals("Portuguese"));
        language = languageDAOImpl.get(8);
        assertTrue(language.getId() == 8);
        assertTrue(language.getLanguage().equals("Russian"));
    }

    @Test
    public void testGetByLanguage() throws SQLException {
        Language language = new Language();
        language = languageDAOImpl.get("Chinese");
        assertTrue(language.getId() == 6);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Language> languages = languageDAOImpl.getAll();
        assertTrue(languages.size() >= 10);
        for (Language qlanguage : languages) {
            assertTrue(qlanguage.getLanguage().length() >= 2);
        }
    }

    @Test
    public void testGetList() throws SQLException {
        List<Language> languages = languageDAOImpl.getList(5);
        assertTrue(languages.size() == 5);
        for (Language qlanguage : languages) {
            assertTrue(qlanguage.getLanguage().length() >= 2);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Language language = new Language();
        language.setId(12);
        language.setLanguage("myLanguage");

        int rowsAffected = languageDAOImpl.insert(language);
        assertEquals(rowsAffected, 1);

        assertNotNull(languageDAOImpl.get("myLanguage"));
        assertNotNull(languageDAOImpl.get(12));
        assertTrue(languageDAOImpl.get(12).getLanguage().equals("myLanguage"));
        assertTrue(languageDAOImpl.get("myLanguage").getLanguage().equals("myLanguage"));
    }

    @Test
    public void testUpdate() throws SQLException {
        Language language = new Language();

        language.setId(24);
        language.setLanguage("testUpdate");

        int rowsAffected = languageDAOImpl.insert(language);
        assertEquals(rowsAffected, 1);

        language.setLanguage("hello");
        rowsAffected = languageDAOImpl.update(language);
        assertEquals(rowsAffected, 1);

        assertTrue(languageDAOImpl.get(24).getLanguage().equals("hello"));
    }

    @Test
    public void testDelete() throws SQLException {
        Language language = new Language();
        language.setId(25);
        language.setLanguage("testDelete");

        int rowsAffected = languageDAOImpl.insert(language);
        assertEquals(rowsAffected, 1);

        languageDAOImpl.delete(language);
        assertEquals(rowsAffected, 1);

        assertTrue(languageDAOImpl.get(25) == null);
        assertNull(languageDAOImpl.get(25));
    }

    @Test(expected = SQLException.class)
    public void testForException() throws SQLException {
        Language language = new Language();
        language.setId(25);
        language.setLanguage("myLanguage");

        languageDAOImpl.insert(language);
        languageDAOImpl.insert(language);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}