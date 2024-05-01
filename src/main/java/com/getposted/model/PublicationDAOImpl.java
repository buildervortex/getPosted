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
        PreparedStatement select = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        select.setInt(1, id);

        try {
            rs = select.executeQuery();
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
        // id,description, date, size, pdfPath, softCopyPrice, pageCount,
        // softCopyDiscount,
        // title, publishedDate, categoryId, languageId, authorId
        Connection con = Database.getConnection();
        List<Publication> publicationList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publication";
        PreparedStatement select = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = select.executeQuery();
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
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setInt(1, publication.getId());
        st.setString(2, publication.getDescription());
        st.setDate(3, publication.getDate());
        st.setInt(4, publication.getSize());
        st.setString(5, publication.getPdfPath());
        st.setDouble(6, publication.getSoftCopyPrice());
        st.setInt(7, publication.getPageCount());
        st.setDouble(8, publication.getSoftCopyDiscount());
        st.setString(9, publication.getTitle());
        st.setDate(10, publication.getPublishedDate());
        st.setInt(11, publication.getCategoryId());
        st.setInt(12, publication.getLanguageId());
        st.setInt(13, publication.getAuthorId());
        try {
            // Your code here
            result = st.executeUpdate();
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
        return result;
    }

    @Override
    public int update(Publication publication) throws SQLException {
        // public Publication(int id, String description, Date date, int size, String
        // pdfPath, double softCopyPrice,String title, Date publishedDate, int
        // categoryId, int languageId, int authorId)
        // CREATE TABLE Publication( description VARCHAR(500), date DATE, size INT(40)
        // NOT NULL DEFAULT 0, pdfPath VARCHAR(80) NOT NULL, softCopyPrice DECIMAL(10,2)
        // NOT NULL DEFAULT 0.0, pageCount VARCHAR(20) NOT NULL DEFAULT 1,
        // softCopyDiscount DECIMAL(10,2) NOT NULL DEFAULT 0.0, title VARCHAR(80) NOT
        // NULL, publishedDate DATE, id INT(25) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        // categoryId INT(25), languageId INT(25), authorId INT(25), FOREIGN
        // KEY(categoryId) REFERENCES Category(id), FOREIGN KEY(languageId) REFERENCES
        // Language(id), FOREIGN KEY(authorId) REFERENCES Author(id) );
        // (id,description, date, size, pdfPath, softCopyPrice, pageCount,
        // softCopyDiscount, title, publishedDate, categoryId, languageId, authorId)

        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Publication SET description =?, date =?, size =?, pdfPath =?, softCopyPrice =?, pageCount =?, softCopyDiscount =?, title =?, publishedDate =?, categoryId =?, languageId =?, authorId =? WHERE id =?";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;
        
        st.setString(1, publication.getDescription());
        st.setDate(2, publication.getDate());
        st.setInt(3, publication.getSize());
        st.setString(4, publication.getPdfPath());
        st.setDouble(5, publication.getSoftCopyPrice());
        st.setInt(6, publication.getPageCount());
        st.setDouble(7, publication.getSoftCopyDiscount());
        st.setString(8, publication.getTitle());
        st.setDate(9, publication.getPublishedDate());
        st.setInt(10, publication.getCategoryId());
        st.setInt(11, publication.getLanguageId());
        st.setInt(12, publication.getAuthorId());
        st.setInt(13, publication.getId());
        
        try {
            result = st.executeUpdate();
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
        return result;
    }

    @Override
    public int delete(Publication publication) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Publication WHERE id =?";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;
    
        st.setInt(1, publication.getId());
    
        try {
            result = st.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                "There is SQLException happend in the com.getposted.model.PublicationDAOImpl class at delete() method. The exception message is %s. The deleted Publication id is %d.",
                e.getMessage(), publication.getId()));
            throw e;
        }
        return result;
    }
}