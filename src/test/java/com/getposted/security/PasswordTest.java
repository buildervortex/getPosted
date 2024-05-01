package com.getposted.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class PasswordTest {
    
    private static String password = "samplePassword";
    @Test
    public void testHas() throws NoSuchAlgorithmException{
        String hashPassowrd = Passowrd.getHash(password);
        String hashPassowrd1 = Passowrd.getHash(password);
        assertTrue(hashPassowrd.length() == 128);
        assertEquals(hashPassowrd,hashPassowrd1);
    }
    
}
