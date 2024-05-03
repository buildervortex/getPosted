package com.getposted.system;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Ignore;

@Ignore
public class TestSysenv {

    /*
     * This test need the environment variable testKey2 setted to testValue2
     */
    private static String key1 = "testKey1";
    private static String value1 = "testValue1";
    private static String key2 = "testKey2";
    private static String value4 = "testValue4";
    private static String key4 = "testKey4";
    
    @Test
    public void testProperty(){
        Sysenv.setEnv(key1,value1);
        assertTrue(value1.equals(Sysenv.getEnv(key1)));
    }

    @Test
    public void testEnv(){
        assertTrue("testValue10".equals(Sysenv.getEnv(key4)));
    }

    @Test
    public void testPropertyPriority(){
        Sysenv.setEnv(key2,value4);
        assertTrue(value4.equals(Sysenv.getEnv(key2)));
    }
}