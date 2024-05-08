package com.getposted.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PublicationDAO extends DAO<Publication> {

    List<Publication> getAllPublicationsFilterBy(String filter,boolean desc) throws SQLException;                  // SELECT * FROM Publication ORDER BY date ASC
    List<Publication> getListOfPublicationsFilterBy(String filter,boolean desc, int limit)throws SQLException;    // SELECT * FROM Publication ORDER BY date ASC LIMIT 1
    List<Publication> getAllPublicationsBelongsToACategory(int categoryId)throws SQLException;                     // SELECT * FROM Publication WHERE categoryId = 1
    List<Publication> getListOfPublicationsBelongsToACategory(int categoryId, int limit)throws SQLException;       // SELECT * FROM Publication WHERE categoryId = 1 LIMIT 1
    List<Publication> getAllPublicationsBelongsToALanguage(int languageId)throws SQLException;                     // SELECT * FROM Publication WHERE languageId = 1
    List<Publication> getListOfPublicationsBelongsToALanguage(int languageId, int limit)throws SQLException;       // SELECT * FROM Publication WHERE languageId = 1 LIMIT 1
    List<Publication> getAllPublicationsBelongsToAnAuthor(int authorId)throws SQLException;                        // SELECT * FROM Publication WHERE authorId = 1
    List<Publication> getListOfPublicationsBelongsToAnAuthor(int authorId, int limit)throws SQLException;          // SELECT * FROM Publication WHERE authorId = 1 LIMIT 1
    int getTotalPublicationCount()throws SQLException;                                                             // SELECT COUNT(id) AS count FROM Publication
    int getTotalPublicationCountForGivenCategoryId(int categoryId)throws SQLException;                             // SELECT COUNT(id) AS count FROM Publication WHERE categoryId = 1
    int getTotalPublicationCountForGivenLanguageId(int languageId)throws SQLException;                             // SELECT COUNT(id) AS count FROM Publication WHERE languageId = 1
    int getTotalPublicationCountForGivenAuthorId(int authorId)throws SQLException;                                 // SELECT COUNT(id) AS count FROM Publication WHERE authorId = 1
    List<Publication> getPublicationsByGivenPublicationIds(int... ids)throws SQLException;                         // SELECT * FROM Publication WHERE id IN (1,2)
    List<Publication> getAllPublicationsExceptGivenIdPublications(int... ids)throws SQLException;                  // SELECT * FROM Publication WHERE id NOT IN (1,2)
    List<Publication> getAllPublicationsForGivenTitlePattern(String pattern)throws SQLException;                   // SELECT * FROM Publication WHERE LOWER(title) LIKE LOWER("%1%")
    List<Publication> getListOfPublicationsForGivenTitlePattern(String pattern, int limit)throws SQLException;     // SELECT * FROM Publication WHERE LOWER(title) LIKE LOWER("%1%") LIMIT 1
    List<Publication> getAllPublicationsForGivenPublishedDate(Date date)throws SQLException;                                // SELECT * FROM Publication WHERE publishedDate = "2024-04-15"
    List<Publication> getAllPublicationsForGivenPublishedDateRange(Date startDate, Date endDate)throws SQLException;        // SELECT * FROM Publication WHERE publishedDate BETWEEN "2020-04-15" AND "2025-01-01"
    List<Publication> getListOfPublicationsForGivenPublishedDateRange(Date startDate, Date endDate, int limit)throws SQLException;  // SELECT * FROM Publication WHERE publishedDate BETWEEN "2020-04-15" AND "2025-01-01" LIMIT 1
    List<Publication> getListOfPublicationsForGivenPublishedDate(Date date, int limit)throws SQLException;                  // SELECT * FROM Publication WHERE publishedDate = "2024-04-15" LIMIT 1
    List<Publication> getListOfPublicationsForGivenPriceRange(double startPrice, double endPrice, int limit)throws SQLException;      // SELECT * FROM Publication WHERE softCopyPrice BETWEEN 0 AND 20 LIMIT 1
    List<Publication> getAllPublicationsForGivenPriceRange(double startPrice, double endPrice)throws SQLException; // SELECT * FROM Publication WHERE softCopyPrice BETWEEN 0 AND 20

}
