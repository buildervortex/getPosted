package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("CountryDAOImplTest")
public class CountryDAOImplTest {
    private static CountryDAOImpl countryDAOImpl = new CountryDAOImpl();

    @BeforeClass
    public static void createDatebase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Country country = new Country();
        country.setId(50);
        country.setCountry("testDelete");

        int rowsAffected = countryDAOImpl.insert(country);
        assertEquals(rowsAffected, 1);

        rowsAffected = countryDAOImpl.delete(country);
        assertEquals(rowsAffected, 1);

        assertTrue(countryDAOImpl.get(50) == null);
        assertNull(countryDAOImpl.get(50));
    }

    @Test
    public void testGetInt() throws SQLException {
        Country country = new Country();
        country = countryDAOImpl.get(1);
        assertEquals(country.getId(), 1);
        assertEquals(country.getCountry(), "United States");
        country = countryDAOImpl.get(2);
        assertEquals(country.getId(), 2);
        assertEquals(country.getCountry(), "Canada");
    }

    @Test
    public void testGetString() throws SQLException {
        Country country = new Country();
        country = countryDAOImpl.get("United States");
        assertEquals(country.getId(), 1);
        assertEquals(country.getCountry(), "United States");
        country = countryDAOImpl.get("Germany");
        assertEquals(country.getId(), 4);
        assertEquals(country.getCountry(), "Germany");
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Country> countries = countryDAOImpl.getAll();
        assertTrue(countries.size() >= 10);
        for (Country country : countries) {
            assertTrue(country.getCountry().length() >= 2);
        }
    }

    @Test
    public void testGetList() throws SQLException {
        List<Country> countries = countryDAOImpl.getList(5);
        assertTrue(countries.size() == 5);
        for (Country country : countries) {
            assertTrue(country.getCountry().length() >= 2);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Country country = new Country();
        country.setCountry("testInsert");
        country.setId(28);

        int rowsAffected = countryDAOImpl.insert(country);
        assertEquals(rowsAffected, 1);

        assertEquals(countryDAOImpl.get(28).getCountry(), "testInsert");
        assertEquals(countryDAOImpl.get("testInsert").getId(), 28);
    }

    @Test
    public void testUpdate() throws SQLException {
        Country country = new Country();
        country.setCountry("testUpdate");
        country.setId(40);

        int rowsAffected = countryDAOImpl.insert(country);
        assertEquals(rowsAffected, 1);

        country.setCountry("testUpdate2");

        rowsAffected = countryDAOImpl.update(country);
        assertEquals(rowsAffected, 1);

        assertTrue(countryDAOImpl.get(40).getCountry().equals("testUpdate2"));
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
