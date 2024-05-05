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

        return rowsAffected;
    }

    @Override
    public List<SearchTag> getList(int publicationId) throws SQLException {
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
                    "There is SQLException happend in the com.getposted.model.SearchTagImpl class at getId() method the id is %d. The exception message is %s",
                    publicationId, e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            String tagName = rs.getString("tagName");
            int qpublicationId = rs.getInt("publicationId");

            searchTags.add(new SearchTag(tagName, qpublicationId));
        }

        return searchTags;
    }

}
