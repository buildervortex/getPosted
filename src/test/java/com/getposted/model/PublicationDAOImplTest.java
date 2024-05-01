package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PublicationDAOImplTest {

    private static Publication publication = new Publication();
    private static PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException{

        publication = publicationDAOImpl.get(15);
        publication.setAuthorId(2);
        publication.setSize(20);
        publication.setPublishedDate(Date.valueOf("2023-01-15"));
        publication.setCategoryId(8);
        publication.setLanguageId(4);
        publication.setPdfPath("/pdfs/publication1.pdf");
        publication.setPageCount(200);
        publication.setSoftCopyDiscount(0.10);
        publication.setTitle("testDeleteTitle");
        publication.setDate(Date.valueOf("2023-01-15"));
        publication.setSoftCopyPrice(10.00);
        publication.setId(40);
        publicationDAOImpl.insert(publication);
        publicationDAOImpl.delete(publication);
        assertTrue(publicationDAOImpl.get(40) == null);
    }

    @Test
    public void testGet() throws SQLException {
        publication = publicationDAOImpl.get(1);
        assertNotNull(publication);
        assertEquals(publication.getId(), 1);
        assertEquals(publication.getDescription(),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et velit justo. Vivamus et sollicitudin turpis. Sed sed mauris nec nisi accumsan tincidunt.");
        assertEquals(publication.getDate().toString(), "2023-01-10");
        assertEquals(publication.getSize(), 500);
        assertEquals(publication.getPdfPath(), "/pdfs/publication1.pdf");
        assertTrue(publication.getSoftCopyPrice() == 10.00);
        assertEquals(publication.getPageCount(), 200);
        assertTrue(publication.getSoftCopyDiscount() == 0.10);
        assertEquals(publication.getTitle(), "Publication 1");
        assertEquals(publication.getPublishedDate().toString(), "2023-01-15");
        assertEquals(publication.getCategoryId(), 1);
        assertEquals(publication.getLanguageId(), 1);
        assertEquals(publication.getAuthorId(), 1);
        assertEquals(publication.getId(), 1);

    }

    @Test
    public void testGetAll() throws SQLException{
        List<Publication> publications = publicationDAOImpl.getAll();
        assertTrue(publications.size() >= 20);

        for (Publication publication: publications){
            assertTrue(publication.getDescription().length() >= 2);
            assertTrue(publication.getDate().toString().length() >= 2);
            assertTrue(publication.getSize() >= 0);
            assertTrue(publication.getPdfPath().length() >= 2);
            assertTrue(publication.getSoftCopyPrice() >= 0);
            assertTrue(publication.getPageCount() >= 0);
            assertTrue(publication.getSoftCopyDiscount() >= 0);
            assertTrue(publication.getTitle().length() >= 2);
            assertTrue(publication.getPublishedDate().toString().length() >= 2);
            assertTrue(publication.getCategoryId() >= 0);
            assertTrue(publication.getLanguageId() >= 0);
            assertTrue(publication.getAuthorId() >= 0);
        }
    }

    @Test
    public void testInsert() throws SQLException {

        publication.setId(21);
        publication.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et velit justo. Vivamus et sollicitudin turpis. Sed sed mauris nec nisi accumsan tincidunt.");
        publication.setDate(Date.valueOf("2023-01-10"));
        publication.setSize(500);
        publication.setPdfPath("/pdfs/publication1.pdf");
        publication.setSoftCopyPrice(10.00);
        publication.setPageCount(200);
        publication.setSoftCopyDiscount(0.10);
        publication.setTitle("Publication 1");
        publication.setPublishedDate(Date.valueOf("2023-01-15"));
        publication.setCategoryId(1);
        publication.setLanguageId(1);
        publication.setAuthorId(1);
        publicationDAOImpl.insert(publication);
        publication = publicationDAOImpl.get(21);
        assertTrue(publication.getId() == 21);
        assertTrue(publication.getDescription().equals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et velit justo. Vivamus et sollicitudin turpis. Sed sed mauris nec nisi accumsan tincidunt."));
        assertTrue(publication.getDate().toString().equals("2023-01-10"));
        assertTrue(publication.getSize()==500);
        assertTrue(publication.getPdfPath().equals("/pdfs/publication1.pdf"));
        assertTrue(publication.getSoftCopyDiscount() == 0.10);
        assertEquals(publication.getTitle() , "Publication 1");
        assertEquals(publication.getPublishedDate().toString(), "2023-01-15");
        assertTrue(publication.getCategoryId() == 1);
        assertTrue(publication.getLanguageId() == 1);
        assertTrue(publication.getAuthorId() == 1);

    }

    @Test
    public void testUpdate() throws SQLException{
        publication = publicationDAOImpl.get(5);
        publication.setDescription("testUpdateDescription");
        publication.setDate(Date.valueOf("2023-01-10"));
        publication.setSize(500);
        publication.setPdfPath("/pdfs/publication1.pdf");
        publication.setSoftCopyPrice(10.00);
        publication.setPageCount(200);
        publication.setSoftCopyDiscount(0.10);
        publication.setTitle("testUpdateTitle");
        publication.setPublishedDate(Date.valueOf("2023-01-15"));
        publication.setCategoryId(1);
        publication.setLanguageId(1);
        publication.setAuthorId(1);
        publicationDAOImpl.update(publication);
        publication = publicationDAOImpl.get(5);
        assertTrue(publication.getDescription().equals("testUpdateDescription"));
        assertTrue(publication.getDate().toString().equals("2023-01-10"));
        assertTrue(publication.getSize() == 500);
        assertEquals(publication.getPdfPath(),"/pdfs/publication1.pdf");
        assertTrue(publication.getSoftCopyDiscount() == 0.10);
        assertEquals(publication.getTitle(), "testUpdateTitle");
        assertEquals(publication.getPublishedDate().toString(), "2023-01-15");
        assertTrue(publication.getCategoryId() == 1);
        assertTrue(publication.getLanguageId() == 1);
        assertTrue(publication.getAuthorId() == 1);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
