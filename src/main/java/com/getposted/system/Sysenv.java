package com.getposted.system;

import java.lang.System;

public class Sysenv {

    public static void setEnv(String key, String value){
        System.setProperty(key, value);
    }

    public static String getEnv(String key){
        String envValue = System.getenv(key);
        String propertyValue = System.getProperty(key);

        if (propertyValue == null) return envValue;

        return propertyValue;
    }
    
    public static void deleteEnv(String key){
        System.clearProperty(key);
    }
}
