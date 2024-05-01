package com.getposted.system;

import org.junit.Test;
import static org.junit.Assert.*;

import com.getposted.system.Sysenv;

public class TestSysenv {

    /*
     * This test need the environment variable testKey2 setted to testValue2
     */
    private static String key1 = "testKey1";
    private static String value1 = "testValue1";
    private static String key2 = "testKey2";
    private static String value2 = "testValue2";
    private static String value4 = "testValue4";
    
    @Test
    public void testProperty(){
        Sysenv.setEnv(key1,value1);
        assertTrue(value1.equals(Sysenv.getEnv(key1)));
    }

    @Test
    public void testEnv(){
        assertTrue(value2.equals(Sysenv.getEnv(key2)));
    }

    @Test
    public void testEnvPriority(){
        Sysenv.setEnv(key2,value4);
        assertTrue(value2.equals(Sysenv.getEnv(key2)));
    }
}