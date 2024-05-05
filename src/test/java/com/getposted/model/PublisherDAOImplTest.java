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

// @Ignore("PublisherDAOImplTest")
public class PublisherDAOImplTest {

    private static PublisherDAOImpl publisherImpl = new PublisherDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Publisher publisher = new Publisher();

        int id = 80;
        String address = "testDelete";
        String webSite = "sampleWebsitee";
        String name = "Publisher 80";
        String email = "publisher80@example.com";
        double hardCopyPageCommissionForAuthor = 10.00;
        String salt = "salt88";
        String password = "testDelete";
        double hardCopyDiscount = 10.00;
        String pepper = "pepper80";
        double hardCopyPricePerPage = 50.00;
        double softCopyCommission = 45.00;
        double hardCopyCommission = 12.00;

        publisher = new Publisher(id, address, webSite, name, email, hardCopyPageCommissionForAuthor, salt, password,
                hardCopyDiscount, pepper, hardCopyPricePerPage, softCopyCommission, hardCopyCommission);

        int rowsAffected = publisherImpl.insert(publisher);
        assertEquals(rowsAffected, 1);

        rowsAffected = publisherImpl.delete(publisher);
        assertEquals(rowsAffected, 1);
        assertNull(publisherImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        Publisher publisher = new Publisher();
        publisher = publisherImpl.get(1);
        assertTrue(publisher.getId() == 1);
        assertEquals(publisher.getAddress(), "123 Main St, Anytown USA");
        assertTrue(publisher.getWebSite().equals("www.publisher1.com"));
        assertTrue(publisher.getName().equals("Publisher 1"));
        assertTrue(publisher.getEmail().equals("publisher1@example.com"));
        assertTrue(publisher.getHardCopyPageCommissionForAuthor() == 0.15);
        assertEquals(publisher.getSalt(), "salt1");
        assertTrue(publisher.getPassword().equals("password1"));
        assertTrue(publisher.getHardCopyDiscount() == 0.10);
        assertEquals(publisher.getPepper(), "pepper1");
        assertTrue(publisher.getHardCopyPricePerPage() == 0.50);
        assertTrue(publisher.getSoftCopyCommission() == 0.20);
        assertTrue(publisher.getHardCopyCommission() == 0.25);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Publisher> publishers = publisherImpl.getAll();
        assertTrue(publishers.size() >= 10);

        for (Publisher publisher : publishers) {
            assertTrue(publisher.getId() >= 1);
            assertTrue(publisher.getAddress().length() >= 1);
            assertTrue(publisher.getWebSite().length() >= 1);
            assertTrue(publisher.getName().length() >= 1);
            assertTrue(publisher.getEmail().length() >= 1);
            assertTrue(publisher.getHardCopyPageCommissionForAuthor() >= 0);
            assertTrue(publisher.getSalt().length() >= 1);
            assertTrue(publisher.getPassword().length() >= 1);
            assertTrue(publisher.getHardCopyDiscount() >= 0);
            assertTrue(publisher.getPepper().length() >= 1);
            assertTrue(publisher.getHardCopyPricePerPage() >= 0);
            assertTrue(publisher.getSoftCopyCommission() >= 0);
            assertTrue(publisher.getHardCopyCommission() >= 0);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Publisher publisher = new Publisher();
        int id = 21;
        String address = "testInsert";
        String webSite = "sampleWebsite";
        String name = "Publisher 21";
        String email = "publisher21@example.com";
        double hardCopyPageCommissionForAuthor = 5.00;
        String salt = "salt21";
        String password = "testInsert";
        double hardCopyDiscount = 5.00;
        String pepper = "pepper21";
        double hardCopyPricePerPage = 5.00;
        double softCopyCommission = 5.00;
        double hardCopyCommission = 5.00;

        publisher.setId(id);
        publisher.setAddress(address);
        publisher.setWebSite(webSite);
        publisher.setName(name);
        publisher.setEmail(email);
        publisher.setHardCopyPageCommissionForAuthor(hardCopyPageCommissionForAuthor);
        publisher.setSalt(salt);
        publisher.setPassword(password);
        publisher.setHardCopyDiscount(hardCopyDiscount);
        publisher.setPepper(pepper);
        publisher.setHardCopyPricePerPage(hardCopyPricePerPage);
        publisher.setSoftCopyCommission(softCopyCommission);
        publisher.setHardCopyCommission(hardCopyCommission);

        int rowsAffected = publisherImpl.insert(publisher);
        assertEquals(rowsAffected, 1);

        publisher = publisherImpl.get(21);
        assertTrue(publisher.getId() == id);
        assertTrue(publisher.getAddress().equals(address));
        assertTrue(publisher.getWebSite().equals(webSite));
        assertTrue(publisher.getName().equals(name));
        assertTrue(publisher.getEmail().equals(email));
        assertTrue(publisher.getHardCopyPageCommissionForAuthor() == hardCopyPageCommissionForAuthor);
        assertTrue(publisher.getSalt().equals(salt));
        assertTrue(publisher.getPassword().equals(password));
        assertTrue(publisher.getHardCopyDiscount() == hardCopyDiscount);
        assertTrue(publisher.getPepper().equals(pepper));
        assertTrue(publisher.getHardCopyPricePerPage() == hardCopyPricePerPage);
        assertTrue(publisher.getSoftCopyCommission() == softCopyCommission);
        assertTrue(publisher.getHardCopyCommission() == hardCopyCommission);

    }

    @Test
    public void testUpdate() throws SQLException {
        Publisher publisher = new Publisher();
        int id = 99;
        String address = "testUpdate";
        String webSite = "sampleWebsitee";
        String name = "Publisher 99";
        String email = "publisher99@example.com";
        double hardCopyPageCommissionForAuthor = 7.00;
        String salt = "salt99";
        String password = "testUpdate";
        double hardCopyDiscount = 8.00;
        String pepper = "pepper99";
        double hardCopyPricePerPage = 8.00;
        double softCopyCommission = 8.00;
        double hardCopyCommission = 8.00;

        publisher = new Publisher(id, address, webSite, name, email, hardCopyPageCommissionForAuthor, salt, password,
                hardCopyDiscount, pepper, hardCopyPricePerPage, softCopyCommission, hardCopyCommission);

        int rowsAffected = publisherImpl.insert(publisher);
        assertEquals(rowsAffected, 1);

        address = "testUpdatee";
        webSite = "sampleWebsiteee";
        name = "Publisher 98";
        email = "publisher98@example.com";
        hardCopyPageCommissionForAuthor = 7.05;
        salt = "salt98";
        password = "testUpdatee";
        hardCopyDiscount = 8.08;
        pepper = "pepper98";
        hardCopyPricePerPage = 8.08;
        softCopyCommission = 8.08;
        hardCopyCommission = 8.08;

        publisher = new Publisher(id, address, webSite, name, email, hardCopyPageCommissionForAuthor, salt, password,
                hardCopyDiscount, pepper, hardCopyPricePerPage, softCopyCommission, hardCopyCommission);

        rowsAffected = publisherImpl.update(publisher);
        assertEquals(rowsAffected, 1);

        publisher = publisherImpl.get(id);
        assertTrue(publisher.getId() == id);
        assertTrue(publisher.getAddress().equals(address));
        assertTrue(publisher.getWebSite().equals(webSite));
        assertTrue(publisher.getName().equals(name));
        assertTrue(publisher.getEmail().equals(email));
        assertTrue(publisher.getHardCopyPageCommissionForAuthor() == hardCopyPageCommissionForAuthor);
        assertTrue(publisher.getSalt().equals(salt));
        assertTrue(publisher.getPassword().equals(password));
        assertTrue(publisher.getHardCopyDiscount() == hardCopyDiscount);
        assertTrue(publisher.getPepper().equals(pepper));
        assertTrue(publisher.getHardCopyPricePerPage() == hardCopyPricePerPage);
        assertTrue(publisher.getSoftCopyCommission() == softCopyCommission);
        assertTrue(publisher.getHardCopyCommission() == hardCopyCommission);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}