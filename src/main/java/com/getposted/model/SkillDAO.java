package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface SkillDAO extends DAO<Skill> {
	List<Skill> getList(int limit) throws SQLException;

	Skill get(String skill) throws SQLException;
}
