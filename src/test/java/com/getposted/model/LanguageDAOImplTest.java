package com.getposted.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import com.getposted.model.LanguageDAOImpl;
import com.getposted.model.Language;
import com.getposted.system.Sysenv;

@Ignore("LanguageDAOImplTest")
public class LanguageDAOImplTest {

    private static Language language = new Language();
    private static LanguageDAOImpl languageDAOImpl = new LanguageDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }


    @Test
    public void testGetData() throws SQLException {
        language = languageDAOImpl.get(1);
        assertTrue(language.getId() == 1);
        assertTrue(language.getLanguage().equals("English"));
        language = languageDAOImpl.get(2);
        assertTrue(language.getId() == 2);
        assertTrue(language.getLanguage().equals("French"));
        language = languageDAOImpl.get(10);
        assertTrue(language.getId() == 10);
        assertTrue(language.getLanguage().equals("Hindi"));
        language = languageDAOImpl.get(19);
        assertTrue(language.getId() == 19);
        assertTrue(language.getLanguage().equals("Swedish"));
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Language> languages = languageDAOImpl.getAll();
        assertTrue(languages.size() >= 20);
        for (Language language : languages) {
            assertTrue(language.getLanguage().length() >= 2);
        }
    }

    @Test
    public void testGetList() throws SQLException {
        List<Language> languages = languageDAOImpl.getList(5);
        assertTrue(languages.size() == 5);
        for (Language language : languages) {
            assertTrue(language.getLanguage().length() >= 2);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        language.setId(22);
        language.setLanguage("myLanguage");
        languageDAOImpl.insert(language);
        languageDAOImpl.get("myLanguage");
        assertNotNull(languageDAOImpl.get("myLanguage"));
        assertNotNull(languageDAOImpl.get(22));
        assertTrue(languageDAOImpl.get("myLanguage").getLanguage().equals("myLanguage"));
    }

    @Test(expected = SQLException.class)
    public void testForException() throws SQLException {
        language.setId(24);
        language.setLanguage("myLanguage");
        languageDAOImpl.insert(language);
        languageDAOImpl.insert(language);
    }

    @Test
    public void testUpdate() throws SQLException {
        language.setId(24);
        language.setLanguage("testUpdate");
        languageDAOImpl.insert(language);
        language.setLanguage("hello");
        languageDAOImpl.update(language);
        assertTrue(languageDAOImpl.get(24).getLanguage().equals("hello"));
    }


    @Test
    public void testDelete() throws SQLException{
        language.setId(25);
        language.setLanguage("testDelete");
        languageDAOImpl.insert(language);
        languageDAOImpl.delete(language);
        assertTrue(languageDAOImpl.get(25) == null);
        assertNull(languageDAOImpl.get(25));
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }

}
