package com.getposted.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.junit.Ignore;
import org.junit.Test;

import com.getposted.random.RandomString;

@Ignore
public class PasswordTest {
    
    private static String password = "samplePassword";
    private static String salt = RandomString.getRandomString(20);
    private static String pepper = RandomString.getRandomString(25);
    @Test
    public void testHas() throws NoSuchAlgorithmException{
        String hashPassowrd = Passowrd.getHash(password,salt,pepper);
        String hashPassowrd1 = Passowrd.getHash(password,salt,pepper);
        assertTrue(hashPassowrd.length() == 128);
        assertEquals(hashPassowrd,hashPassowrd1);
    }
    
}
