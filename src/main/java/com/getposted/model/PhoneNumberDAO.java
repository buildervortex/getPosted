package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface PhoneNumberDAO extends DAO<PhoneNumber>{
    public List<PhoneNumber> getList(int inquiryId) throws SQLException;
}
