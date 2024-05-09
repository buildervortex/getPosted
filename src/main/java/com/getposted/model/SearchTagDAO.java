package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface SearchTagDAO extends DAO<SearchTag> {
    List<SearchTag> getAll(int publicationId) throws SQLException;
    List<Integer> getAllPublicationsIdWithGivenTagPattern(String tagPattern) throws SQLException;   // SELECT publicationId FROM SearchTag WHERE LOWER(tagName) LIKE LOWER("%1%")
    List<Integer> getListOfPublicationsIdWithGivenTagPattern(String tagPattern, int limit) throws SQLException; // SELECT publicationId FROM SearchTag WHERE LOWER(tagName) LIKE LOWER("%1%") LIMIT 1
    List<SearchTag> getAllSearchTagsWithGivenTagPattern(String tagPattern) throws SQLException; // SELECT * FROM SearchTag WHERE LOWER(tagName) LIKE LOWER("%1%")
    List<SearchTag> getListOfSearchTagsWithGivenTagPattern(String tagPattern, int limit) throws SQLException; // SELECT * FROM SearchTag WHERE LOWER(tagName) LIKE LOWER("%1%") LIMIT 1
    List<SearchTag> getList(int publicationId, int limit) throws SQLException; // SELECT * FROM SearchTag WHERE publicationId = 2 LIMIT 1
    int getNumberOfSearchTagsWithGivenPublication(int publicationId) throws SQLException; // SELECT COUNT(publicationId) AS count FROM SearchTag WHERE publicationId = 1 GROUP BY publicationId
}
