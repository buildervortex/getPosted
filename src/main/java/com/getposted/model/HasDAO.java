package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface HasDAO extends DAO<Has> {
    List<Has> getList(int authorId) throws SQLException;
    List<String> getListOfSkillNames(int authorId) throws SQLException;
    List<Integer> getListOfSkillIds(int authorId) throws SQLException;
    List<Integer> getListOfAuthorsForASkill(int skillId) throws SQLException;
    int getCountOfAuthorsForASkill(int skillId) throws SQLException;
    int getCountOfSkillsForAnAuthor(int authorId) throws SQLException;
}
