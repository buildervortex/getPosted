package com.getposted.random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

// @Ignore
public class RandomStringTest {

    @Test
    public void testRandomStringSize(){
        String randomString = RandomString.getRandomString(20);
        String randomString2 = RandomString.getRandomString(40);

        assertEquals(randomString.length(),20);
        assertEquals(randomString2.length(),40);
    }

    @Test
    public void testRandomStringRandomness(){
        String randomString = RandomString.getRandomString(20);
        String randomString2 = RandomString.getRandomString(20);
        String randomString4 = RandomString.getRandomString(20);
        String randomString5 = RandomString.getRandomString(20);

        assertFalse(randomString.equals(randomString5));
        assertFalse(randomString2.equals(randomString4));
        assertFalse(randomString.equals(randomString4));
        assertFalse(randomString2.equals(randomString));
    }
    
}