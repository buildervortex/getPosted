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

    private static String getListRepresentation(int[] ids) {
        String listRepresentation = "";
        listRepresentation += "( ";
        for (int i = 0; i < ids.length; i++) {
            listRepresentation += ids[i];
            if (i != ids.length - 1)
                listRepresentation += ",";
        }
        listRepresentation += " )";
        return listRepresentation;
    }

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

    @Override
    public List<Publication> getAllPublicationsFilterBy(String filter, boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = String.format("SELECT * FROM Publication ORDER BY %s %s", filter, order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsFilterBy().The exception message is %s",
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
    public List<Publication> getListOfPublicationsFilterBy(String filter, boolean desc, int limit) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = String.format("SELECT * FROM Publication ORDER BY %s %s LIMIT ?", filter, order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsFilterBy().The exception message is %s",
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
    public List<Publication> getAllPublicationsBelongsToACategory(int categoryId) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE categoryId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, categoryId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsBelongsToACategory().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int languageId = rs.getInt("languageId");
            int authorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, languageId, authorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsBelongsToACategory(int categoryId, int limit) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE categoryId = ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, categoryId);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsBelongsToACategory().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int languageId = rs.getInt("languageId");
            int authorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, languageId, authorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsBelongsToALanguage(int languageId) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE languageId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, languageId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsBelongsToALanguage().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int authorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, authorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsBelongsToALanguage(int languageId, int limit) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE languageId = ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, languageId);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsBelongsToALanguage().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int authorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, authorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsBelongsToAnAuthor(int authorId) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE authorId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsBelongsToAnAuthor().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsBelongsToAnAuthor(int authorId, int limit) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE authorId = ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsBelongsToAnAuthor().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public int getTotalPublicationCount() throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Publication";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getTotalPublicationCount().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public int getTotalPublicationCountForGivenCategoryId(int categoryId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Publication WHERE categoryId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, categoryId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getTotalPublicationCountForGivenCategoryId().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public int getTotalPublicationCountForGivenLanguageId(int languageId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Publication WHERE languageId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, languageId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getTotalPublicationCountForGivenLanguageId().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public int getTotalPublicationCountForGivenAuthorId(int authorId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Publication WHERE authorId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getTotalPublicationCountForGivenAuthorId().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public List<Publication> getPublicationsByGivenPublicationIds(int... ids) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = String.format("SELECT * FROM Publication WHERE id IN %s", getListRepresentation(ids));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getPublicationsByGivenPublicationIds().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsExceptGivenIdPublications(int... ids) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = String.format("SELECT * FROM Publication WHERE id NOT IN %s", getListRepresentation(ids));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsExceptGivenIdPublications().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsForGivenTitlePattern(String pattern) throws SQLException {
        pattern = "%" + pattern + "%";
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE LOWER(title) LIKE LOWER(?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, pattern);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsForGivenTitlePattern().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsForGivenTitlePattern(String pattern, int limit) throws SQLException {
        pattern = "%" + pattern + "%";
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE LOWER(title) LIKE LOWER(?) LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, pattern);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsForGivenTitlePattern().The exception message is %s",
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
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, date, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsForGivenPublishedDate(Date date) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE publishedDate = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, date);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsForGivenPublishedDate().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsForGivenPublishedDateRange(Date startDate, Date endDate)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE publishedDate BETWEEN ? AND ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, startDate);
        ps.setDate(2, endDate);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsForGivenPublishedDateRange().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsForGivenPublishedDateRange(Date startDate, Date endDate, int limit)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE publishedDate BETWEEN ? AND ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, startDate);
        ps.setDate(2, endDate);
        ps.setInt(4 - 1, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsForGivenPublishedDateRange().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsForGivenPublishedDate(Date date, int limit) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE publishedDate = ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, date);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsForGivenPublishedDate().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsForGivenPriceRange(double startPrice, double endPrice, int limit)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE softCopyPrice BETWEEN ? AND ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDouble(1, startPrice);
        ps.setDouble(2, endPrice);
        ps.setInt(3, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsForGivenPriceRange().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsForGivenPriceRange(double startPrice, double endPrice)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE softCopyPrice BETWEEN ? AND ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDouble(1, startPrice);
        ps.setDouble(2, endPrice);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsForGivenPriceRange().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsForAGivenDate(Date date) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE date = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, date);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsForAGivenDate().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsForAGivenDate(Date date, int limit) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE date = ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, date);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsForAGivenDate().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getAllPublicationsForAGivenDateRange(Date startDate, Date endDate) throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE date BETWEEN ? AND ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, startDate);
        ps.setDate(2, endDate);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getAllPublicationsForAGivenDateRange().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }

    @Override
    public List<Publication> getListOfPublicationsForAGivenDateRange(Date startDate, Date endDate, int limit)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication WHERE date BETWEEN ? AND ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, startDate);
        ps.setDate(2, endDate);
        ps.setInt(3, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at getListOfPublicationsForAGivenDateRange().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {

            int Id = rs.getInt("id");
            String description = rs.getString("description");
            Date qdate = rs.getDate("date");
            int size = rs.getInt("size");
            String pdfPath = rs.getString("pdfPath");
            double softCopyPrice = rs.getDouble("softCopyPrice");
            int pageCount = rs.getInt("pageCount");
            double softCopyDiscount = rs.getDouble("softCopyDiscount");
            String title = rs.getString("title");
            Date publishedDate = rs.getDate("publishedDate");
            int qcategoryId = rs.getInt("categoryId");
            int qlanguageId = rs.getInt("languageId");
            int qauthorId = rs.getInt("authorId");
            Publication publication = new Publication(Id, description, qdate, size, pdfPath, softCopyPrice, pageCount,
                    softCopyDiscount, title, publishedDate,
                    qcategoryId, qlanguageId, qauthorId);
            publicationList.add(publication);
        }

        return publicationList;
    }
}