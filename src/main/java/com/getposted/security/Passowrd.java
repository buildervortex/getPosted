package com.getposted.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class Passowrd {

    private static final String algorithm = "SHA-512";
    private static Logger logger = Logging.getLogger(Passowrd.class.getName());
    public static final int saltLength = 20;
    public static final int pepperLength = 25;

    public static String getHash(String password, String salt, String pepper) {
        String hash = null;
        MessageDigest digest = null;
        String securePassword = salt+password+pepper;

        try{
            digest = MessageDigest.getInstance(algorithm);
        }
        catch(NoSuchAlgorithmException e){
            logger.severe(String.format("NoSuchAlgorithmException occurecred. The algorithm name is %s. the error message is %s",Passowrd.algorithm,e.toString()));
        }

        digest.update(securePassword.getBytes(StandardCharsets.UTF_8));

        byte[] hashedBytes = digest.digest();

        StringBuilder sb = new StringBuilder();

        for (byte b: hashedBytes){
            sb.append(String.format("%02x",b));
        }

        hash = sb.toString();

        return hash;

    }
    
}
