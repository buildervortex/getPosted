package com.getposted.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class SearchTagDAOImpl implements SearchTagDAO {

    private static Logger logger = Logging.getLogger(SearchTagDAOImpl.class.getName());

    @Override
    public SearchTag get(int publicationId) throws SQLException {
        return null;
    }

    @Override
    public List<SearchTag> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<SearchTag> searchTags = new ArrayList();
        String sqlTemplate = "SELECT * FROM SearchTag";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTag class at getAll() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            String tagName = rs.getString("tagName");
            int qpublicationId = rs.getInt("publicationId");

            searchTags.add(new SearchTag(tagName, qpublicationId));
        }
        con.close();
        return searchTags;
    }

    @Override
    public int insert(SearchTag searchTag) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO SearchTag (tagName, publicationId) VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;

        ps.setString(1, searchTag.getTagName());
        ps.setInt(2, searchTag.getPublicationId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTagDAOImpl class at insert() method. The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int update(SearchTag searchTag) throws SQLException {
        return -1;
    }

    @Override
    public int delete(SearchTag searchTag) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM SearchTag WHERE tagName =? AND publicationId =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;

        ps.setString(1, searchTag.getTagName());
        ps.setInt(2, searchTag.getPublicationId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTagDAOImpl class at delete() method. The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public List<SearchTag> getAll(int publicationId) throws SQLException {
        Connection con = Database.getConnection();
        List<SearchTag> searchTags = new ArrayList<SearchTag>();
        String sqlTemplate = "SELECT * FROM SearchTag WHERE publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTagImpl class at getAll() method the id is %d. The exception message is %s",
                    publicationId, e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            String tagName = rs.getString("tagName");
            int qpublicationId = rs.getInt("publicationId");

            searchTags.add(new SearchTag(tagName, qpublicationId));
        }
        con.close();
        return searchTags;
    }

    @Override
    public List<Integer> getAllPublicationsIdWithGivenTagPattern(String tagPattern) throws SQLException {
        tagPattern = "%" + tagPattern + "%";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = "SELECT publicationId FROM SearchTag WHERE LOWER(tagName) LIKE LOWER(?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, tagPattern);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTagImpl class at getAllPublicationsIdWithGivenTagPattern(). The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationIds.add(rs.getInt("publicationId"));
        }
        con.close();
        return publicationIds;
    }

    @Override
    public List<Integer> getListOfPublicationsIdWithGivenTagPattern(String tagPattern, int limit) throws SQLException {
        tagPattern = "%" + tagPattern + "%";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = "SELECT publicationId FROM SearchTag WHERE LOWER(tagName) LIKE LOWER(?) LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, tagPattern);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTagImpl class at getListOfPublicationsIdWithGivenTagPattern(). The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationIds.add(rs.getInt("publicationId"));
        }
        con.close();
        return publicationIds;
    }

    @Override
    public List<SearchTag> getAllSearchTagsWithGivenTagPattern(String tagPattern) throws SQLException {
        tagPattern = "%" + tagPattern + "%";
        Connection con = Database.getConnection();
        List<SearchTag> searchTags = new ArrayList();
        String sqlTemplate = "SELECT * FROM SearchTag WHERE LOWER(tagName) LIKE LOWER(?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, tagPattern);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTag class at getAllSearchTagsWithGivenTagPattern() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            String tagName = rs.getString("tagName");
            int qpublicationId = rs.getInt("publicationId");

            searchTags.add(new SearchTag(tagName, qpublicationId));
        }
        con.close();
        return searchTags;
    }

    @Override
    public List<SearchTag> getListOfSearchTagsWithGivenTagPattern(String tagPattern, int limit) throws SQLException {
        tagPattern = "%" + tagPattern + "%";
        Connection con = Database.getConnection();
        List<SearchTag> searchTags = new ArrayList();
        String sqlTemplate = "SELECT * FROM SearchTag WHERE LOWER(tagName) LIKE LOWER(?) LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, tagPattern);
        ps.setInt(2,limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTag class at getListOfSearchTagsWithGivenTagPattern() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            String tagName = rs.getString("tagName");
            int qpublicationId = rs.getInt("publicationId");

            searchTags.add(new SearchTag(tagName, qpublicationId));
        }
        con.close();
        return searchTags;
    }

    @Override
    public List<SearchTag> getList(int publicationId, int limit) throws SQLException {
        Connection con = Database.getConnection();
        List<SearchTag> searchTags = new ArrayList();
        String sqlTemplate = "SELECT * FROM SearchTag WHERE publicationId = ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTag class at getList() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            String tagName = rs.getString("tagName");
            int qpublicationId = rs.getInt("publicationId");

            searchTags.add(new SearchTag(tagName, qpublicationId));
        }
        con.close();
        return searchTags;
    }

    @Override
    public int getNumberOfSearchTagsWithGivenPublication(int publicationId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(publicationId) AS count FROM SearchTag WHERE publicationId = ? GROUP BY publicationId";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SearchTag class at getList() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if(rs.next()){
            count = rs.getInt("count");
        }
        con.close();
        return count;
    }

}
