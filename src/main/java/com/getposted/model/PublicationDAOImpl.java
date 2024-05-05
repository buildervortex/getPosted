package com.getposted.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class PublicationDAOImpl implements PublicationDAO {

    private static Logger logger = Logging.getLogger(PublicationDAOImpl.class.getName());

    @Override
    public Publication get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Publication publication = null;
        String sqlTemplate = "SELECT * FROM Publication WHERE id =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationImpl class at getId() method the id is %d. The exception message is %s",
                    id, e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date date = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int categoryId = rs.getInt("categoryId");
            int languageId = rs.getInt("languageId");
            int authorId = rs.getInt("authorId");
            publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    categoryId, languageId, authorId);
        }

        return publication;
    }

    @Override
    public List<Publication> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAll().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date date = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int categoryId = rs.getInt("categoryId");
            int languageId = rs.getInt("languageId");
            int authorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    categoryId, languageId, authorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public int insert(Publication publication) throws SQLException {

        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Publication (id,description, date, size, pdfPath, softCopyPrice, pageCount, softCopyDiscount, title, publishedDate, categoryId, languageId, authorId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, publication.getId());
        ps.setString(2, publication.getDescription());
        ps.setDate(3, publication.getDate());
        ps.setInt(4, publication.getSize());
        ps.setString(5, publication.getPdfPath());
        ps.setDouble(6, publication.getSoftCopyPrice());
        ps.setInt(7, publication.getPageCount());
        ps.setDouble(8, publication.getSoftCopyDiscount());
        ps.setString(9, publication.getTitle());
        ps.setDate(10, publication.getPublishedDate());
        ps.setInt(11, publication.getCategoryId());
        ps.setInt(12, publication.getLanguageId());
        ps.setInt(13, publication.getAuthorId());
        try {
            // Your code here
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at insert() method. The exception message is %s. The inserted Publication details are: id=%d, description=%s, date=%s, size=%d, pdfPath=%s, softCopyPrice=%f, pageCount=%d, softCopyDiscount=%f, title=%s, publishedDate=%s, categoryId=%d, languageId=%d, authorId=%d",
                    e.getMessage(), publication.getId(), publication.getDescription(), publication.getDate(),
                    publication.getSize(), publication.getPdfPath(), publication.getSoftCopyPrice(),
                    publication.getPageCount(), publication.getSoftCopyDiscount(), publication.getTitle(),
                    publication.getPublishedDate(), publication.getCategoryId(), publication.getLanguageId(),
                    publication.getAuthorId()));
            throw e;
        }
        return rowsAffected;
    }

    @Override
    public int update(Publication publication) throws SQLException {

        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Publication SET description =?, date =?, size =?, pdfPath =?, softCopyPrice =?, pageCount =?, softCopyDiscount =?, title =?, publishedDate =?, categoryId =?, languageId =?, authorId =? WHERE id =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setString(1, publication.getDescription());
        ps.setDate(2, publication.getDate());
        ps.setInt(3, publication.getSize());
        ps.setString(4, publication.getPdfPath());
        ps.setDouble(5, publication.getSoftCopyPrice());
        ps.setInt(6, publication.getPageCount());
        ps.setDouble(7, publication.getSoftCopyDiscount());
        ps.setString(8, publication.getTitle());
        ps.setDate(9, publication.getPublishedDate());
        ps.setInt(10, publication.getCategoryId());
        ps.setInt(11, publication.getLanguageId());
        ps.setInt(12, publication.getAuthorId());
        ps.setInt(13, publication.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at update() method. The exception message is %s. The updated Publication details are: id=%d, description=%s, date=%s, size=%d, pdfPath=%s, softCopyPrice=%f, pageCount=%d, softCopyDiscount=%f, title=%s, publishedDate=%s, categoryId=%d, languageId=%d, authorId=%d",
                    e.getMessage(), publication.getId(), publication.getDescription(), publication.getDate(),
                    publication.getSize(), publication.getPdfPath(), publication.getSoftCopyPrice(),
                    publication.getPageCount(), publication.getSoftCopyDiscount(), publication.getTitle(),
                    publication.getPublishedDate(), publication.getCategoryId(), publication.getLanguageId(),
                    publication.getAuthorId()));
            throw e;
        }
        return rowsAffected;
    }

    @Override
    public int delete(Publication publication) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Publication WHERE id =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, publication.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at delete() method. The exception message is %s. The deleted Publication id is %d.",
                    e.getMessage(), publication.getId()));
            throw e;
        }
        return rowsAffected;
    }
}