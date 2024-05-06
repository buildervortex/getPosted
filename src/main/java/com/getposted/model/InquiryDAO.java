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
    * Total copies sold from a category
    * List of category ids which are sold
    * total sales count for a category id
    * total sales count for a language id
    * LIst of language ids which are sold
    
    */
    List<Inquiry> getAllInquiriesByPurchasedDate(boolean desc) throws SQLException;
    List<Inquiry> getListOfInquiriesByPurchasedDate(int limit, boolean desc) throws SQLException;
    List<Inquiry> getAllInquiriesByPrice(boolean desc) throws SQLException;
    List<Inquiry> getListOfInquiriesByPrice(int limit, boolean desc) throws SQLException;
    List<Inquiry> getAllOfInquiriesForAState(String state) throws SQLException;
    List<Inquiry> getListOfInquiriesForAState(String state, int limit) throws SQLException;
    List<Inquiry> testGetALLInquiriesForAPurchasedDate(Date date) throws SQLException;
    
    double getTotalSumOfInquriesPrice() throws SQLException;
    int getTotalInquiryCount() throws SQLException;
    int getTotalCopiesSold() throws SQLException;
    int getTotalPublicationPurchasedUsers() throws SQLException;
    List<Inquiry> getALLInquiriesOfAUser(int userId) throws SQLException;
    int getTotalInquiryCountOfAUser(int userId) throws SQLException;
    double getTotalInquriyPayementFromAUser(int userId) throws SQLException;
    int getTotalPublicationCopiesPurchasedByUser(int userId) throws SQLException;
    List<Inquiry> getALLInquiriesOfAPublication(int publicationId) throws SQLException;
    int getTotalInquiryCountOfAPublication(int publicationId) throws SQLException;
    double getTotalInquriyPayementForAPublication(int publicationId) throws SQLException;
    int getTotalCopiesSoldFromAPublication(int publicationId) throws SQLException;
    int getTotalPageCountSoldFromAPublication(int publicationId) throws SQLException;
    List<Integer> getUserPurchasedAllPublicationsIds(int userId) throws SQLException;
    List<Integer> getPublicationPurchasedAllUserIds(int publicationId) throws SQLException;
    int getCountOfInquriesInAState(String state) throws SQLException;

    List<Inquiry> getListOfInquiriesFromInquryIdList(int ...inqueryIds) throws SQLException;
}
