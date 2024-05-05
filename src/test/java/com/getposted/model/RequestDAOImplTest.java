package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

// @Ignore
public class RequestDAOImplTest {

    private static RequestDAOImpl requestDAOImpl = new RequestDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Request request;

        int id = 40;
        String requestedContent = "Requests forr delete new content on technology";
        int authorId = 5;
        int publisherId = 2;

        request = new Request(id, requestedContent, authorId, publisherId);

        int affectedRows = requestDAOImpl.insert(request);
        assertEquals(affectedRows, 1);

        assertNotNull(requestDAOImpl.get(id));

        affectedRows = requestDAOImpl.delete(request);
        assertNull(requestDAOImpl.get(id));
    }

    @Test
    public void testGet() throws SQLException {
        // (1, 'Request for new content on technology', 1, 1)
        Request request;

        int id = 1;
        String requestedContent = "Request for new content on technology";
        int authorId = 1;
        int publisherId = 1;

        request = requestDAOImpl.get(id);

        assertEquals(request.getId(), id);
        assertEquals(request.getRequestedContent(), requestedContent);
        assertEquals(request.getAuthorId(), authorId);
        assertEquals(request.getPublisherId(), publisherId);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Request> requests = requestDAOImpl.getAll();
        assertTrue(requests.size() >= 10);

        for (Request request : requests) {
            assertTrue(request.getId() >= 1);
            assertTrue(request.getRequestedContent().length() >= 1);
            assertTrue(request.getAuthorId() >= 1);
            assertTrue(request.getPublisherId() >= 1);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Request request;

        int id = 24;
        String requestedContent = "Requests for new content on technology";
        int authorId = 2;
        int publisherId = 4;

        request = new Request();

        request.setId(id);
        request.setRequestedContent(requestedContent);
        request.setAuthorId(authorId);
        request.setPublisherId(publisherId);

        int affectedRows = requestDAOImpl.insert(request);
        assertEquals(affectedRows, 1);

        request = requestDAOImpl.get(id);

        assertEquals(request.getId(), id);
        assertEquals(request.getRequestedContent(), requestedContent);
        assertEquals(request.getAuthorId(), authorId);
        assertEquals(request.getPublisherId(), publisherId);
    }

    @Test
    public void testUpdate() throws SQLException {
        Request request;

        int id = 28;
        String requestedContent = "Requests forr new content on technology";
        int authorId = 4;
        int publisherId = 2;

        request = new Request(id, requestedContent, authorId, publisherId);

        int affectedRows = requestDAOImpl.insert(request);
        assertEquals(affectedRows, 1);

        requestedContent = "Requests new content on technology";
        authorId = 8;
        publisherId = 4;

        request = new Request(id, requestedContent, authorId, publisherId);

        affectedRows = requestDAOImpl.update(request);
        assertEquals(affectedRows, 1);

        request = requestDAOImpl.get(id);

        assertEquals(request.getId(), id);
        assertEquals(request.getRequestedContent(), requestedContent);
        assertEquals(request.getAuthorId(), authorId);
        assertEquals(request.getPublisherId(), publisherId);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
