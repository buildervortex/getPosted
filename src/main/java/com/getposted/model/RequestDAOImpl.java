package com.getposted.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class RequestDAOImpl implements RequestDAO {

    private static Logger logger = Logging.getLogger(RequestDAOImpl.class.getName());

    @Override
    public Request get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Request request = null;
        String sqlTemplate = "SELECT * FROM Request WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RequestDAOImpl class at getId() method the id is %d. The exception message is %s",
                    id, e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int qid = rs.getInt("id");
            String requestedContent = rs.getString("requestedContent");
            int authorId = rs.getInt("authorId");
            int publisherId = rs.getInt("publisherId");

            request = new Request(qid, requestedContent, authorId, publisherId);
        }
        con.close();
        return request;

    }

    @Override
    public List<Request> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Request> requests = new ArrayList<Request>();
        String sqlTemplate = "SELECT * FROM Request ";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RequestDAOImpl class at getAll() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            String requestedContent = rs.getString("requestedContent");
            int authorId = rs.getInt("authorId");
            int publisherId = rs.getInt("publisherId");

            requests.add(new Request(id, requestedContent, authorId, publisherId));
        }
        con.close();
        return requests;
    }

    @Override
    public int insert(Request request) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Request (id, requestedContent, authorId, publisherId) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, request.getId());
        ps.setString(2, request.getRequestedContent());
        ps.setInt(3, request.getAuthorId());
        ps.setInt(4, request.getPublisherId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RequestDAOImpl class at insert() method . The exception message is %s. The id is %s. The requestedContent is %s. The authorId is %s. The publisherId is %s.",
                    e.getMessage(), request.getId(), request.getRequestedContent(), request.getAuthorId(),
                    request.getPublisherId()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int update(Request request) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Request SET requestedContent = ?, authorId = ?, publisherId = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setString(1, request.getRequestedContent());
        ps.setInt(2, request.getAuthorId());
        ps.setInt(3, request.getPublisherId());
        ps.setInt(4, request.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RequestDAOImpl class at update() method . The exception message is %s. The id is %s. The requestedContent is %s. The authorId is %s. The publisherId is %s.",
                    e.getMessage(), request.getId(), request.getRequestedContent(), request.getAuthorId(),
                    request.getPublisherId()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int delete(Request request) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Request WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, request.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RequestDAOImpl class at delete() method . The exception message is %s. The id is %s. The requestedContent is %s. The authorId is %s. The publisherId is %s.",
                    e.getMessage(), request.getId(), request.getRequestedContent(), request.getAuthorId(),
                    request.getPublisherId()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public List<Request> getAllRequestFromSpecificAuthor(int publisherId, int authorId) throws SQLException {
        Connection con = Database.getConnection();
        List<Request> requests = new ArrayList<Request>();
        String sqlTemplate = "SELECT * FROM Request WHERE authorId = ? AND publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);
        ps.setInt(2, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RequestDAOImpl class at getAllRequestFromSpecificAuthor() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            String requestedContent = rs.getString("requestedContent");
            int qauthorId = rs.getInt("authorId");
            int qpublisherId = rs.getInt("publisherId");

            requests.add(new Request(id, requestedContent, qauthorId, qpublisherId));
        }
        con.close();
        return requests;
    }

    @Override
    public List<Request> getAllRequestForSpecificPublisher(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Request> requests = new ArrayList<Request>();
        String sqlTemplate = "SELECT * FROM Request WHERE  publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RequestDAOImpl class at getAllRequestFromSpecificAuthor() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            String requestedContent = rs.getString("requestedContent");
            int qauthorId = rs.getInt("authorId");
            int qpublisherId = rs.getInt("publisherId");

            requests.add(new Request(id, requestedContent, qauthorId, qpublisherId));
        }
        con.close();
        return requests;
    }

    @Override
    public int getAllRequestCountFromASpecificAuthor(int publisherId, int authorId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Request WHERE authorId = ? AND publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);
        ps.setInt(2, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RequestDAOImpl class at getAllRequestFromSpecificAuthor() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }
        con.close();
        return count;
    }

}
