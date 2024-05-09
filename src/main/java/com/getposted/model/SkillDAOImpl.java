package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.getposted.logger.Logging;
import java.util.logging.Logger;

public class SkillDAOImpl implements SkillDAO {

	private static Logger logger = Logging.getLogger(SkillDAOImpl.class.getName());

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
	public Skill get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Skill skill = null;
		String sqlTemplate = "SELECT * FROM Skill WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setInt(1, id);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.SkillDAOImpl class at getId() method the id is %d. The exception message is %s",
					id, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qSkill = rs.getString("skill");

			skill = new Skill(qId, qSkill);
		}

		return skill;
	}

	@Override
	public List<Skill> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Skill> skillList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Skill ORDER BY skill";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.SkillDAOImpl class at getAll() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qSkill = rs.getString("skill");
			skillList.add(new Skill(qId, qSkill));
		}

		return skillList;
	}

	@Override
	public int insert(Skill skill) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "INSERT INTO Skill(id,skill) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setInt(1, skill.getId());
		ps.setString(2, skill.getSkill());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.SkillDAOImpl class at insert() method. The exception message is %s. The inserted Skill name is %s and id is %d",
					e.getMessage(), skill.getSkill(), skill.getId()));
			throw e;
		}

		return rowsAffected;
	}

	@Override
	public int update(Skill skill) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "UPDATE Skill SET skill = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setString(1, skill.getSkill());
		ps.setInt(2, skill.getId());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.SkillDAOImpl class at update() method. The exception message is %s. The updated Skill name is %s and id is %d",
					e.getMessage(), skill.getSkill(), skill.getId()));
			throw e;
		}

		return rowsAffected;
	}

	@Override
	public int delete(Skill skill) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "DELETE FROM Skill WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setInt(1, skill.getId());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.SkillDAOImpl class at delete() method. The exception message is %s. The deleted Skill name is %s and id is %d",
					e.getMessage(), skill.getSkill(), skill.getId()));
			throw e;
		}

		return rowsAffected;
	}

	@Override
	public List<Skill> getList(int limit) throws SQLException {
		Connection con = Database.getConnection();
		List<Skill> skillList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Skill ORDER BY skill LIMIT ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setInt(1, limit);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.SkillDAOImpl class at getList(). The limit is %d .The exception message is %s",
					limit, e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qSkill = rs.getString("skill");
			skillList.add(new Skill(qId, qSkill));
		}

		return skillList;
	}

	@Override
	public Skill get(String skill) throws SQLException {
		Connection con = Database.getConnection();
		Skill skillOb = null;
		String sqlTemplate = "SELECT * FROM Skill WHERE skill = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setString(1, skill);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.SkillDAOImpl class at getId() method the id is %s. The exception message is %s",
					skill, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qSkill = rs.getString("skill");

			skillOb = new Skill(qId, qSkill);
		}

		return skillOb;
	}
}
