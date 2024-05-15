package com.getposted.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface InquiryDAO extends DAO<Inquiry> {
    /*
    ? inqueries total price
    ? total inqueries count
    ? total copies sold
    ? total users purchased publications

    ? list of inqueries of a user id
    ? count of inqueries of a user id
    ? total inquiry price of a user id
    ? total copies bought from a user id

    ? list of inqueries for a publication id
    ? count of inqueries for a publication id
    ? total inquery price for a publication id
    ? total count of sales for a publication id
    ? total page count sold from a publication id

    ? user purchased publication ids
    ? publication purchased user ids

    
    ? Total count of inquries shipped,Rejected,..
    * 
    */
    /*
     ? Get All Inquries of a publisher  = select * from Inquiry WHERE PublisherId = 1
     ? Get All Inquries of a publisher when excluding on state = select * from Inquiry WHERE PublisherId = 1 AND state <> "Rejected"
     ? Total hard copies Printed/printing/tobePrint/rejected = select SUM(count) AS count from Inquiry WHERE PublisherId = q AND state = "Shipped"
     ? 
     ? Total Discount for all inquries of a publisher = SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = 1) AS discount
     ? Total Discount for all inquries of a given publisher's given state inquries = SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = 1 AND state = "Pending") AS discount
     ? Total Discount for all inquries of a given publisher except an state inquries = SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = 1 AND state <> "Rejected") AS discount
     ? Total Discount for all inquries of a given publisher except given states inquries = SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = 1 AND state NOT IN ("Rejected","Accepted")) AS discount
     ? Total Discount for all inquries of a given publisher for given states inquries = SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = 1 AND state  IN ("Rejected","Accepted")) AS discount
     ? 
     ? Total publisher Commission for all inquries of a publisher = SELECT SUM(totalCommission) AS sum FROM (SELECT (count*hardCopyCommission) as totalCommission, I.id FROM Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE publisherId = 1) AS commission
     ! Total publisher Commission for all inquries of a given publisher's given state inquries = SELECT SUM(totalCommission) AS sum FROM (SELECT (count*hardCopyCommission) as totalCommission, I.id FROM Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE publisherId = 1 AND state = "Pending") AS commission
     ? Total publisher Commission for all inqueries of a given publisher fo given states inquries = SELECT SUM(totalCommission) AS sum FROM (SELECT (count*hardCopyCommission) as totalCommission, I.id FROM Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE publisherId = 1 AND state  In ("Pending","Accepted")) AS commission
     ! Total publisher Commission for all inqueries of a given publisher except an given state inqueries = SELECT SUM(totalCommission) AS sum FROM (SELECT (count*hardCopyCommission) as totalCommission, I.id FROM Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE publisherId = 1 AND state <> "Pending") AS commission
     ? Total publisher Commission for all inqueries of a given publisher except given states inquries = SELECT SUM(totalCommission) AS sum FROM (SELECT (count*hardCopyCommission) as totalCommission, I.id FROM Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE publisherId = 1 AND state NOT IN ("Pending","Accepted")) AS commission
     ? 
     ? Total Cost for pages for all inquries of a given publisher = SELECT SUM(totalCostForPages) AS sum FROM (SELECT (I.count*P.pageCount*PU.hardCopyPricePerPage) AS totalCostForPages FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON PU.id = I.publisherId WHERE publisherId = 1) AS cost
     ! Total Cost for pages for all inqueries of a given publisher given state inqueries = SELECT SUM(totalCostForPages) AS sum FROM (SELECT (I.count*P.pageCount*PU.hardCopyPricePerPage) AS totalCostForPages FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON PU.id = I.publisherId WHERE publisherId = 1 AND state = "Accepted") AS cost
     ? Total Cost for pages for all inqueries of a give publisher excluding given states inqueries = SELECT SUM(totalCostForPages) AS sum FROM (SELECT (I.count*P.pageCount*PU.hardCopyPricePerPage) AS totalCostForPages FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON PU.id = I.publisherId WHERE publisherId = 1 AND state NOT IN ("Accepted","Pending")) AS cost
     ? Total Cost for pages for all inqueries of a given publisher given states inqueries = SELECT SUM(totalCostForPages) AS sum FROM (SELECT (I.count*P.pageCount*PU.hardCopyPricePerPage) AS totalCostForPages FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON PU.id = I.publisherId WHERE publisherId = 1 AND state In ("Accepted","Pending")) AS cost
     ! Total Cost for pages for all inqueries of a give publisher excluding a given state inqueries = SELECT SUM(totalCostForPages) AS sum FROM (SELECT (I.count*P.pageCount*PU.hardCopyPricePerPage) AS totalCostForPages FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON PU.id = I.publisherId WHERE publisherId = 1 AND state <> "Accepted" ) AS cost
     ? 
     ? Total author commission given by a given publisher for every inquery = SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1) AS totalCommission
     ! Total author commission given by a given publisher for given state inqueries = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND state = "Accepted") AS totalCommission
     ? Total author commission given by a given publisher for given states inqueires = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND state IN ("Accepted","Pending")) AS totalCommission
     ! Total author commission given by a given publisher except state inqueires = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND state <> "Pending") AS totalCommission
     ? Total author commission given by a given publisher except states inqueires = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND state NOT IN ("Accepted","Pending")) AS totalCommission
     ? 
     * Total author Commission given by a given publisher for a given author = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = 1 AND authorId = 1) AS totalCommission
     ! Total author Commission given by a given publisher for a given author for given state = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = 1 AND authorId = 1 AND state = "Accepted") AS totalCommission
     ? Total author Commission given by a given publisher for a given author for given states = SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = 1 AND authorId = 1 AND state IN ("Accepted")) AS totalCommission
     ! Total author Commission given by a given publisher for a given author except a state = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = 1 AND authorId = 1 AND state <> "Accepted") AS totalCommission
     ? Total author Commission given by a given publisher for a given author except states = SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = 1 AND authorId = 1 AND state NOT IN ("Accepted","Pending")) AS totalCommission
     ? 
     ? Total author Commission givne by a given publisher for a given publication = SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND I.publicationId = 1 ) AS totalCommission
     ! Total author Commission givne by a given publisher for a given publication for given state = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND I.publicationId = 1 AND state = "Accepted" ) AS totalCommission
     ? Total author Commission givne by a given publisher for a given publication for given states = SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND I.publicationId = 1 AND state IN ("Accepted","Pending") ) AS totalCommission
     ! Total author Commission givne by a given publisher for a given publication except a given state = SELECT SUM(commission) FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND I.publicationId = 1 AND state <> "Accepted" ) AS totalCommission
     ? Total author Commission givne by a given publisher for a given publication except a given states  = SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1 AND I.publicationId = 1 AND state NOT IN ("Accepted","Pending") ) AS totalCommission
     ? 
     ? All Author ids whos publications sold as hard copies of given pulisher = SELECT A.id FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = 1 GROUP BY A.id
     */
    List<Inquiry> getAllHardCopyInquiriesByPurchasedDate(boolean desc,int publisherId) throws SQLException;
    List<Inquiry> getListOfHardCopyInquiriesByPurchasedDate(int limit, boolean desc, int publisherId) throws SQLException;
    List<Inquiry> getAllHardCopyInquiriesByPrice(boolean desc, int publisherId) throws SQLException;
    List<Inquiry> getListOfHardCopyInquiriesByPrice(int limit, boolean desc, int publisherId) throws SQLException;
    List<Inquiry> getAllOfHardCopyInquiriesForAState(String state, int publisherId) throws SQLException;
    List<Inquiry> getListOfHardCopyInquiriesForAState(String state, int limit, int publisherId) throws SQLException;
    List<Inquiry> getALLHardCopyInquiriesForAPurchasedDate(Date date, int publisherid) throws SQLException;
    
    double getTotalSumOfHardCopyInquriesPrices(int publisherId) throws SQLException;
    int getTotalHardCopyInquiryCount(int publisherId) throws SQLException;
    int getTotalHardCopiesSold(int publisherId) throws SQLException;
    int getTotalHardCopiesSoldInGivenStates(int publisherId,String... states) throws SQLException;
    int getTotalHardCopiesSoldInAllStatesExceptTheGiveStates(int publisherId,String... states) throws SQLException;
    int TotalUsersWhoPerchasedPublications(int publisherId) throws SQLException;
    List<Inquiry> getALLHardCopyInquiriesOfAGiveUser(int userId, int publisherId) throws SQLException;
    int getTotalHardCopyInquiryCountOfAGiveUser(int userId, int publisherId) throws SQLException;
    double getTotalHardCopyInquriyPayementFromAUser(int userId, int publisherId) throws SQLException;
    int getTotalPublicationsCopiesPurchasedByUser(int userId, int publisherId) throws SQLException;
    List<Inquiry> getALLHardCopyInquiriesForAPublication(int publicationId, int publisherId) throws SQLException;
    int getTotalHardCopyInquiryCountForAPublication(int publicationId, int publisherId) throws SQLException;
    double getTotalHardCopyInquriyPayementsForAPublication(int publicationId, int publisherId) throws SQLException;
    int getTotalHardCopiesSoldFromAPublication(int publicationId, int publisherId) throws SQLException;
    int getTotalHardCopyPagesCountSoldFromAPublication(int publicationId, int publisherId) throws SQLException;
    List<Integer> getUserPurchasedAllHardCopyPublicationsIds(int userId, int publisherId) throws SQLException;
    List<Integer> getAllUserIdsWhoPurchasedHardCopiesOfTheGiveNPublication(int publicationId, int publisherId) throws SQLException;
    int getCountOfHardCopyInquriesInAState(String state,int publisherId) throws SQLException;
    List<Inquiry> getListOfHardCopyInquiriesFromGivenInquryIdList(int publisherId,int ...inqueryIds) throws SQLException;

    List<Inquiry> getAllHardCopyInquiriesByAPublisher(int publisherId) throws SQLException;
    List<Inquiry> getAllHardCopyInquiriesInEachStateExceptTheGivenState(int publisherId, String state) throws SQLException;
    int getTotalCountOfHardCopyInquriesInGivenState(int publisherId,String state) throws SQLException;

    double getSumOfAllDiscountGivenForEveryHardCopyInqury(int publisherId) throws SQLException;
    double getSumOFAllDiscountGivenToEveryHardCopyInquryInGivenState(int publisherId, String state) throws SQLException;
    double getSumOfAllDiscountGivenToEveryHardCopyInquriyInAnyStateExceptGivenState(int publisherId, String state) throws SQLException;
    double getSumOfAllDiscountGivenForEveryHardCopyInquryInAnyStateExceptGivenStates(int publisherId, String... state) throws SQLException;
    double getSumOfAllDiscountGivenForEveryHardCopyInquryInGivenStates(int publisherId, String... state) throws SQLException;

    double getSumOfAllCommissionGotFormEveryHardCopyInqury(int publisherId) throws SQLException;
    double getSumOfAllCommissionGotFormEveryHardCopyInquryInGivenStates(int publisherId, String... state) throws SQLException;
    double getSumOfAllCommissionGotFormEveryHardCopyInquryInAnyStateExceptGivenStates(int publisherId, String... state) throws SQLException;

    double getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachState(int publisherId) throws SQLException;
    double getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachStateExceptGivenStates(int publisherId, String... state) throws SQLException;
    double getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInGivenStates(int publisherId, String... state) throws SQLException;

    double getSumOfEveryAuthorTotalInquryCommission(int publisherId) throws SQLException;
    double getSumOfEveryAuthorTotalInquryCommissionInGiveStates(int publisherId, String... state) throws SQLException;
    double getSumOfEveryAuthorTotalInquryCommissionInAnyStateExceptGivenStates(int publisherId, String... state) throws SQLException;

    double getSumOfAnAuthorTotalCommissionInEveryHardCopyInqury(int publisherId, int authorId) throws SQLException;
    double getSumOfAnAuthorTotalCommissionInGivenStatesHardCopyInquries(int publisherId, int authorId,String... state) throws SQLException;
    double getSumOfAnAuthorTotalCommissionInAnyStateExceptGivenStatesHardCopyInquries(int publisherId, int authorId,String... state) throws SQLException;

    double getSumOfAuthorHardCopyCommissionInAllInquryInAllStates(int publisherId, int publicationId) throws SQLException;
    double getSumOfAuthorHardCopyCommissionInGivenStatesInquries(int publisherId, int publicationId,String... state) throws SQLException;
    double getSumOfAuthorHardCopyCommissionInAnyStateExceptGivenStatesInquries(int publisherId, int publicationId,String... state) throws SQLException;

    List<Integer> getAuthorIdsWhosPublicationsWasSoldAsHardCopies(int publisherId) throws SQLException;
}