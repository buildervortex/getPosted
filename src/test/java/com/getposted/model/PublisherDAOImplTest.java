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

@Ignore("PublisherDAOImplTest")
public class PublisherDAOImplTest {

    private static Publisher publisher = new Publisher();
    private static PublisherDAOImpl publisherImpl = new PublisherDAOImpl();
    
    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }
    
    @Test
    public void testDelete() throws SQLException{
        publisher = publisherImpl.get(10);
        publisher.setEmail("testDelete@example.com");
        publisher.setId(40);
        publisherImpl.insert(publisher);
        publisherImpl.delete(publisher);
        assertNull(publisherImpl.get(40));
    }

    @Test
    public void testGet() throws SQLException{
        publisher = publisherImpl.get(1);
        assertTrue(publisher.getId() == 1);
        assertTrue(publisher.getFirstName().equals("John"));
        assertEquals(publisher.getMiddleName(),null);
        assertTrue(publisher.getLastName().equals("Doe"));
        assertTrue(publisher.getEmail().equals("publisher1@example.com"));
        assertTrue(publisher.getAddress().equals("123 Main St, Cityville, USA"));
        assertTrue(publisher.getWebSite().equals("www.publisher1.com"));
        assertTrue(publisher.getHardCopyPageCommissionForAuthor() == 5.00);
        assertTrue(publisher.getPassword().equals("password1"));
        assertTrue(publisher.getHardCopyDiscount() == 10.00);
        assertTrue(publisher.getHardCopyPricePerPage() == 0.25);
        assertTrue(publisher.getSoftCopyCommission() == 15.00);
        assertTrue(publisher.getHardCopyPageCommission() == 0.50);
    }

    @Test
    public void testGetAll() throws SQLException{
        List<Publisher> publishers = publisherImpl.getAll();
        assertTrue(publishers.size() >= 20);

        for (Publisher publisher: publishers){
            assertTrue(publisher.getFirstName().length() >= 2);
            assertTrue(publisher.getLastName().length() >= 2);
            assertTrue(publisher.getEmail().length() >= 2);
            assertTrue(publisher.getAddress().length() >= 2);
            assertTrue(publisher.getWebSite().length() >= 2);
            assertTrue(publisher.getHardCopyPageCommissionForAuthor() >= 0);
            assertTrue(publisher.getPassword().length() >= 0);
            assertTrue(publisher.getHardCopyDiscount() >= 0);
            assertTrue(publisher.getHardCopyPricePerPage() >= 0);
            assertTrue(publisher.getSoftCopyCommission() >= 0);
            assertTrue(publisher.getHardCopyPageCommission() >= 0);
        }
    }

    @Test
    public void testInsert() throws SQLException{
        publisher.setId(21);
        publisher.setAddress("123 Main St, Cityville, USA");
        publisher.setWebSite("www.publisher21.com");
        publisher.setFirstName("John");
        publisher.setMiddleName(null);
        publisher.setLastName("Doe");
        publisher.setEmail("publisher21@example.com");
        publisher.setHardCopyPageCommissionForAuthor(5.00);
        publisher.setPassword("password");
        publisher.setHardCopyDiscount(10.00);
        publisher.setHardCopyPricePerPage(0.25);
        publisher.setSoftCopyCommission(15.00);
        publisher.setHardCopyPageCommission(0.50);
        publisherImpl.insert(publisher);
        publisher = publisherImpl.get(21);
        assertTrue(publisher.getId() == 21);
        assertTrue(publisher.getFirstName().equals("John"));
        assertEquals(publisher.getMiddleName(),null);
        assertTrue(publisher.getLastName().equals("Doe"));
        assertTrue(publisher.getEmail().equals("publisher21@example.com"));
        assertTrue(publisher.getAddress().equals("123 Main St, Cityville, USA"));
        assertTrue(publisher.getWebSite().equals("www.publisher21.com"));
        assertTrue(publisher.getHardCopyPageCommissionForAuthor() == 5.00);
        assertTrue(publisher.getPassword().equals("password"));
        assertTrue(publisher.getHardCopyDiscount() == 10.00);
        assertTrue(publisher.getHardCopyPricePerPage() == 0.25);
        assertTrue(publisher.getSoftCopyCommission() == 15.00);
        assertTrue(publisher.getHardCopyPageCommission() == 0.50);
    }

    @Test
    public void testUpdate() throws SQLException{
        //id,address, webSite, firstName, middleName, lastName, email, hardCopyPageCommissionForAuthor, password, hardCopyDiscount, hardCopyPricePerPage, softCopyCommission, hardCopyPageCommission
        publisher = publisherImpl.get(5);
        publisher.setAddress("testUpdateAddress");
        publisher.setWebSite("testUpdateWebSite");
        publisher.setFirstName("testUpdateFirstName");
        publisher.setMiddleName("testUpdateMiddleName");
        publisher.setLastName("testUpdateLastName");
        publisher.setEmail("testUpdate@gmail.com");
        publisher.setHardCopyPageCommissionForAuthor(10.0);
        publisher.setPassword("testUpdatePassword");
        publisher.setHardCopyDiscount(20.0);
        publisher.setHardCopyPricePerPage(40.0);
        publisher.setSoftCopyCommission(50.0);
        publisher.setHardCopyPageCommission(60.0);
        publisherImpl.update(publisher);

        publisher = publisherImpl.get(5);
        assertEquals(publisher.getAddress(),"testUpdateAddress");
        assertEquals(publisher.getWebSite(),"testUpdateWebSite");
        assertEquals(publisher.getFirstName(),"testUpdateFirstName");
        assertEquals(publisher.getMiddleName(),"testUpdateMiddleName");
        assertEquals(publisher.getLastName(),"testUpdateLastName");
        assertTrue(publisher.getHardCopyPageCommissionForAuthor()==10.0);
        assertEquals(publisher.getPassword(),"testUpdatePassword");
        assertTrue(publisher.getHardCopyDiscount()==20.0);
        assertTrue(publisher.getHardCopyPricePerPage()==40.0);
        assertTrue(publisher.getSoftCopyCommission()==50.0);
        assertTrue(publisher.getHardCopyPageCommission() ==60.0);
    }

    @AfterClass
    public static void deleteDatabase(){
        TestDataBase.deleteDatabase();
    }
}
