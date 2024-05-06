package com.getposted.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class HasDAOImpl implements HasDAO {

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
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at getAll() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int authorId = rs.getInt("authorId");
            int skillId = rs.getInt("skillId");

            has.add(new Has(authorId, skillId));
        }

        return has;
    }

    @Override
    public int insert(Has has) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Has (authorId, skillId) VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;

        ps.setInt(1, has.getAuthorId());
        ps.setInt(2, has.getSkillId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at insert() method. The exception message is %s",
                    e.getMessage()));
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
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;

        ps.setInt(1, has.getAuthorId());
        ps.setInt(2, has.getSkillId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at delete() method. The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        return rowsAffected;
    }

    @Override
    public List<Has> getList(int authorId) throws SQLException {
        Connection con = Database.getConnection();
        List<Has> has = new ArrayList();
        String sqlTemplate = "SELECT * FROM Has WHERE authorId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at getList() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qauthorId = rs.getInt("authorId");
            int skillId = rs.getInt("skillId");

            has.add(new Has(qauthorId, skillId));
        }

        return has;
    }

    @Override
    public List<String> getListOfSkillNames(int authorId) throws SQLException {
        Connection con = Database.getConnection();
        List<String> listOfSkills = new ArrayList();
        String sqlTemplate = "SELECT Skill.skill FROM Has LEFT JOIN Skill ON Has.skillId = Skill.id WHERE authorId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at getListOfSkillNames() .The exception message is %s. The author id is %s.",
                    e.getMessage(),authorId));
            throw e;
        }

        while (rs.next()) {
            String skillName = rs.getString("skill");
            listOfSkills.add(skillName);
        }

        return listOfSkills;
    }


    @Override
    public List<Integer> getListOfSkillIds(int authorId) throws SQLException {
        Connection con = Database.getConnection();
        List<Integer> listOfSkills = new ArrayList();
        String sqlTemplate = "SELECT Skill.id FROM Has LEFT JOIN Skill ON Has.skillId = Skill.id WHERE authorId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at getListOfSkillIds() .The exception message is %s. The author id is %s.",
                    e.getMessage(),authorId));
            throw e;
        }

        while (rs.next()) {
            int skillId = rs.getInt("id");
            listOfSkills.add(skillId);
        }

        return listOfSkills;
    }

    @Override
    public List<Integer> getListOfAuthorsForASkill(int skillId) throws SQLException {
        Connection con = Database.getConnection();
        List<Integer> listOfAuthorIds = new ArrayList();
        String sqlTemplate = "SELECT Has.authorId FROM Has RIGHT JOIN Skill ON Has.skillId = Skill.id WHERE Skill.id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, skillId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at getListOfAuthorsForASkill() .The exception message is %s. The skill id is %s.",
                    e.getMessage(),skillId));
            throw e;
        }

        while (rs.next()) {
            int authorId = rs.getInt("authorId");
            listOfAuthorIds.add(authorId);
        }

        return listOfAuthorIds;
    }

    @Override
    public int getCountOfAuthorsForASkill(int skillId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(Has.authorId) AS count FROM Has RIGHT JOIN Skill ON Has.skillId = Skill.id WHERE Skill.id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, skillId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at getCountOfAuthorsForASkill() .The exception message is %s. The skill id is %s.",
                    e.getMessage(),skillId));
            throw e;
        }

        if(rs.next()){
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public int getCountOfSkillsForAnAuthor(int authorId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(Skill.id) count FROM Has LEFT JOIN Skill ON Has.skillId = Skill.id WHERE authorId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.HasDAOImpl class at getCountOfSkillsForAnAuthor() .The exception message is %s. The author id is %s.",
                    e.getMessage(),authorId));
            throw e;
        }

        while (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

}
