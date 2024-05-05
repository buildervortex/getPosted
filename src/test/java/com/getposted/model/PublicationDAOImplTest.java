package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore()
public class PublicationDAOImplTest {

    private static PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {

        Publication publication = new Publication();
        int id = 88;
        String description = "testDeleteDescription";
        Date date = Date.valueOf("2025-04-05");
        int size = 548;
        String pdfPath = "/pdf/asdfasdfasd";
        double softCopyPrice = 4.55;
        int pageCount = 204;
        double softCopyDiscount = 20.4;
        String title = "testUpdatetle";
        Date publishedDate = Date.valueOf("2084-04-16");
        int categoryId = 6;
        int languageId = 8;
        int authorId = 2;

        publication = new Publication(id, description, date, size, pdfPath, softCopyPrice, pageCount, softCopyDiscount,
                title, publishedDate, categoryId, languageId, authorId);

        int rowsAffected = publicationDAOImpl.insert(publication);
        assertEquals(rowsAffected, 1);

        rowsAffected = publicationDAOImpl.delete(publication);
        assertEquals(rowsAffected, 1);

        assertTrue(publicationDAOImpl.get(id) == null);
    }

    @Test
    public void testGet() throws SQLException {
        Publication publication = new Publication();
        publication = publicationDAOImpl.get(1);
        assertNotNull(publication);

        assertEquals(publication.getId(), 1);
        assertEquals(publication.getDescription(), "Publication 1 description");
        assertEquals(publication.getDate().toString(), "2024-04-01");
        assertEquals(publication.getSize(), 500);
        assertEquals(publication.getPdfPath(), "/path/to/pdf1.pdf");
        assertTrue(publication.getSoftCopyPrice() == 10.99);
        assertEquals(publication.getPageCount(), 100);
        assertTrue(publication.getSoftCopyDiscount() == 0.15);
        assertEquals(publication.getTitle(), "Publication 1");
        assertEquals(publication.getPublishedDate().toString(), "2024-04-15");
        assertEquals(publication.getCategoryId(), 1);
        assertEquals(publication.getLanguageId(), 1);
        assertEquals(publication.getAuthorId(), 1);

    }

    @Test
    public void testGetAll() throws SQLException {
        List<Publication> publications = publicationDAOImpl.getAll();
        assertTrue(publications.size() >= 10);

        for (Publication publication : publications) {
            assertTrue(publication.getDescription().length() >= 1);
            assertTrue(publication.getDate().toString().length() >= 1);
            assertTrue(publication.getSize() >= 0);
            assertTrue(publication.getPdfPath().length() >= 1);
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
        Publication publication = new Publication();

        publication.setId(21);
        publication.setDescription(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et velit justo. Vivamus et sollicitudin turpis. Sed sed mauris nec nisi accumsan tincidunt.");
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

        int rowsAffected = publicationDAOImpl.insert(publication);
        assertEquals(rowsAffected, 1);

        publication = publicationDAOImpl.get(21);
        assertTrue(publication.getId() == 21);
        assertTrue(publication.getDescription().equals(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et velit justo. Vivamus et sollicitudin turpis. Sed sed mauris nec nisi accumsan tincidunt."));
        assertTrue(publication.getDate().toString().equals("2023-01-10"));
        assertTrue(publication.getSize() == 500);
        assertTrue(publication.getPdfPath().equals("/pdfs/publication1.pdf"));
        assertTrue(publication.getSoftCopyDiscount() == 0.10);
        assertEquals(publication.getTitle(), "Publication 1");
        assertEquals(publication.getPublishedDate().toString(), "2023-01-15");
        assertTrue(publication.getCategoryId() == 1);
        assertTrue(publication.getLanguageId() == 1);
        assertTrue(publication.getAuthorId() == 1);

    }

    @Test
    public void testUpdate() throws SQLException {

        Publication publication = new Publication();

        int id = 48;
        String description = "testUpdateDescription";
        Date date = Date.valueOf("2024-04-05");
        int size = 540;
        String pdfPath = "/pdf";
        double softCopyPrice = 4.05;
        int pageCount = 20;
        double softCopyDiscount = 2.4;
        String title = "testUpdateTitle";
        Date publishedDate = Date.valueOf("2024-04-16");
        int categoryId = 1;
        int languageId = 2;
        int authorId = 4;

        publication = new Publication(id, description, date, size, pdfPath, softCopyPrice, pageCount, softCopyDiscount,
                title, publishedDate, categoryId, languageId, authorId);

        int rowsAffected = publicationDAOImpl.insert(publication);
        assertEquals(rowsAffected, 1);

        description = "testUpdateDescriptionn";
        date = Date.valueOf("2024-04-25");
        size = 545;
        pdfPath = "/pdf/asdf";
        softCopyPrice = 4.07;
        pageCount = 25;
        softCopyDiscount = 4.4;
        title = "testUpdateTiitle";
        publishedDate = Date.valueOf("2024-07-16");
        categoryId = 2;
        languageId = 4;
        authorId = 1;

        publication = new Publication(id, description, date, size, pdfPath, softCopyPrice, pageCount, softCopyDiscount,
                title, publishedDate, categoryId, languageId, authorId);

        rowsAffected = publicationDAOImpl.update(publication);
        assertEquals(rowsAffected, 1);

        publication = publicationDAOImpl.get(48);
        assertTrue(publication.getDescription().equals(description));
        assertTrue(publication.getDate().toString().equals(date.toString()));
        assertTrue(publication.getSize() == size);
        assertEquals(publication.getPdfPath(), pdfPath);
        assertTrue(publication.getSoftCopyDiscount() == softCopyDiscount);
        assertEquals(publication.getTitle(), title);
        assertEquals(publication.getPublishedDate().toString(), publishedDate.toString());
        assertTrue(publication.getCategoryId() == categoryId);
        assertTrue(publication.getLanguageId() == languageId);
        assertTrue(publication.getAuthorId() == authorId);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
