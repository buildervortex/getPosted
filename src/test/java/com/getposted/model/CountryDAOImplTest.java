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
    private static Country country = new Country();
    private static CountryDAOImpl countryDAOImpl = new CountryDAOImpl();

    @BeforeClass
    public static void createDatebase(){
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        country.setId(50);
        country.setCountry("testDelete");
        countryDAOImpl.insert(country);
        countryDAOImpl.delete(country);
        assertTrue(countryDAOImpl.get(50) == null);
        assertNull(countryDAOImpl.get(25));
    }

    @Test
    public void testGetInt() throws SQLException {
        country = countryDAOImpl.get(1);
        assertEquals(country.getId(),1);
        assertEquals(country.getCountry(),"United States");
        country = countryDAOImpl.get(2);
        assertEquals(country.getId(),2);
        assertEquals(country.getCountry(),"Canada");
    }

    @Test
    public void testGetString() throws SQLException {
        country = countryDAOImpl.get("United States");
        assertEquals(country.getId(),1);
        assertEquals(country.getCountry(),"United States");
        country = countryDAOImpl.get("Canada");
        assertEquals(country.getId(),2);
        assertEquals(country.getCountry(),"Canada");
    }

    @Test
    public void testGetAll() throws SQLException{
        List<Country> countries = countryDAOImpl.getAll();
        assertTrue(countries.size() >= 20);
        for (Country country : countries){
            assertTrue(country.getCountry().length() >= 2);
        }
    }

    @Test
    public void testGetList() throws SQLException {
        List<Country> countries = countryDAOImpl.getList(5);
        assertTrue(countries.size() == 5);
        for (Country country : countries){
            assertTrue(country.getCountry().length() >= 2);
        }
    }

    @Test
    public void testInsert() throws SQLException{
        country.setCountry("testInsert");
        country.setId(28);
        countryDAOImpl.insert(country);
        assertEquals(countryDAOImpl.get(28).getCountry(),"testInsert");
        assertEquals(countryDAOImpl.get("testInsert").getId(),28);
    }

    @Test
    public void testUpdate() throws SQLException {
        country.setCountry("testUpdate");
        country.setId(40);
        countryDAOImpl.insert(country);
        country.setCountry("testUpdate2");
        countryDAOImpl.update(country);
        assertTrue(countryDAOImpl.get(40).getCountry().equals("testUpdate2"));
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
