package com.getposted.model;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore()
public class InquiryDAOImplTest {

    private static User user = new User();
    private static UserDAOImpl userDAOImpl = new UserDAOImpl();
    private static Inquiry inquiry = new Inquiry();
    private static InquiryDAOImpl inquiryDAOImpl = new InquiryDAOImpl();

    @BeforeClass
    public static void createDatabase(){
        TestDataBase.createAll();
    }
    @Test
    public void testDelete() {

    }

    @Test
    public void testGet() {

    }

    @Test
    public void testGetAll() {

    }

    @Test
    public void testInsert() {

    }

    @Test
    public void testUpdate() {

    }
}
