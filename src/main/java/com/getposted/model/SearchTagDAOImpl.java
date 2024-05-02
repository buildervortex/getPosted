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

    private static Logger logger = Logging.getLogger(SkillDAOImpl.class.getName());
    @Override
    public SearchTag get(int publicationId) throws SQLException {
        Connection con = Database.getConnection();
		SearchTag searchTag = null;
		String sqlTemplate = "SELECT * FROM SearchTag WHERE publicationId = ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setInt(1,publicationId);

		try{
			rs = select.executeQuery();
		}
		catch(SQLException e){
			logger.warning(String.format("There is SQLException happend in the com.getposted.model.SearchTagImpl class at getId() method the id is %d. The exception message is %s",publicationId,e.getMessage()));
			throw e;
		}

		if(rs.next()){
			String tagName = rs.getString("tagName");
            int qpublicationId = rs.getInt("publicationId");

			searchTag = new SearchTag(tagName, publicationId);
		}

		return searchTag;
    }

    @Override
    public List<SearchTag> getAll() throws SQLException {
        Connection con = Database.getConnection();
		List<SearchTag> searchTags = new ArrayList();
		String sqlTemplate = "SELECT * FROM SearchTag";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try{
			rs = select.executeQuery();
		}
		catch(SQLException e){
			logger.warning(String.format("There is SQLException happend in the com.getposted.model.SearchTag class at getAll() .The exception message is %s",e.getMessage()));
			throw e;
		}

		while(rs.next()){
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
        PreparedStatement insert = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;
    
        insert.setString(1, searchTag.getTagName());
        insert.setInt(2, searchTag.getPublicationId());
    
        try {
            rowsAffected = insert.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format("There is SQLException happend in the com.getposted.model.SearchTagDAOImpl class at insert() method. The exception message is %s", e.getMessage()));
            throw e;
        }
    
        return rowsAffected;
    }

    @Override
    public int update(SearchTag searchTag) throws SQLException {
    Connection con = Database.getConnection();
    String sqlTemplate = "UPDATE SearchTag SET tagName =?, publicationId =? WHERE tagName =? AND publicationId =?";
    PreparedStatement update = con.prepareStatement(sqlTemplate);
    int rowsAffected = 0;

    update.setString(1, searchTag.getTagName());
    update.setInt(2, searchTag.getPublicationId());
    update.setString(3, searchTag.getTagName()); // Assuming we want to update based on both tagName and publicationId
    update.setInt(4, searchTag.getPublicationId());

    try {
        rowsAffected = update.executeUpdate();
    } catch (SQLException e) {
        logger.warning(String.format("There is SQLException happend in the com.getposted.model.SearchTagDAOImpl class at update() method. The exception message is %s", e.getMessage()));
        throw e;
    }

    return rowsAffected;
}

@Override
public int delete(SearchTag searchTag) throws SQLException {
    Connection con = Database.getConnection();
    String sqlTemplate = "DELETE FROM SearchTag WHERE tagName =? AND publicationId =?";
    PreparedStatement delete = con.prepareStatement(sqlTemplate);
    int rowsAffected = 0;

    delete.setString(1, searchTag.getTagName());
    delete.setInt(2, searchTag.getPublicationId());

    try {
        rowsAffected = delete.executeUpdate();
    } catch (SQLException e) {
        logger.warning(String.format("There is SQLException happend in the com.getposted.model.SearchTagDAOImpl class at delete() method. The exception message is %s", e.getMessage()));
        throw e;
    }

    return rowsAffected;
}
    
}
