package com.getposted.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class HasDAOImpl implements HasDAO{

    private static Logger logger = Logging.getLogger(HasDAOImpl.class.getName());

    @Override
    public Has get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Has> getAll() throws SQLException {
        Connection con = Database.getConnection();
		List<Has> has = new ArrayList();
		String sqlTemplate = "SELECT * FROM Has";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try{
			rs = select.executeQuery();
		}
		catch(SQLException e){
			logger.warning(String.format("There is SQLException happend in the com.getposted.model.HasDAOImpl class at getAll() .The exception message is %s",e.getMessage()));
			throw e;
		}

		while(rs.next()){
            int authorId = rs.getInt("authorId");
            int skillId = rs.getInt("skillId");

            has.add(new Has(authorId,skillId));
		}

		return has;
    }

    @Override
    public int insert(Has has) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Has (authorId, skillId) VALUES (?,?)";
        PreparedStatement insert = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;
    
        insert.setInt(1, has.getAuthorId());
        insert.setInt(2, has.getSkillId());
    
        try {
            rowsAffected = insert.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format("There is SQLException happend in the com.getposted.model.HasDAOImpl class at insert() method. The exception message is %s", e.getMessage()));
            throw e;
        }
    
        return rowsAffected;
    }

    @Override
    public int update(Has has) throws SQLException {
        int rowsAffected = -1;
        return rowsAffected;
    }

    @Override
    public int delete(Has has) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Has WHERE authorId =? AND skillId =?";
        PreparedStatement delete = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;
    
        delete.setInt(1, has.getAuthorId());
        delete.setInt(2, has.getSkillId());
    
        try {
            rowsAffected = delete.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format("There is SQLException happend in the com.getposted.model.HasDAOImpl class at delete() method. The exception message is %s", e.getMessage()));
            throw e;
        }
    
        return rowsAffected;
    }

    @Override
    public List<Has> getList(int authorId) throws SQLException {
        Connection con = Database.getConnection();
		List<Has> has = new ArrayList();
		String sqlTemplate = "SELECT * FROM Has WHERE authorId = ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

        select.setInt(1, authorId);

		try{
			rs = select.executeQuery();
		}
		catch(SQLException e){
			logger.warning(String.format("There is SQLException happend in the com.getposted.model.HasDAOImpl class at getList() .The exception message is %s",e.getMessage()));
			throw e;
		}

		while(rs.next()){
            int qauthorId = rs.getInt("authorId");
            int skillId = rs.getInt("skillId");

            has.add(new Has(qauthorId,skillId));
		}

		return has;
    }
}
