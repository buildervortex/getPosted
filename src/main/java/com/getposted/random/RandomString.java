package com.getposted.random;

public class RandomString {
    private static String SIMPLELETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static String CAPITALLETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String NUMBERS = "0123456789";
    private static String SYMBOLES = "~!@#$%^&*(){}|\\;:/?.>,<-+=";

    public static String getRandomString(int length){
        String complexString = SIMPLELETTERS+CAPITALLETTERS+NUMBERS+SYMBOLES;
        String randomString="";

        for(int i = 0; i<length; i++){
            randomString+= complexString.charAt(getNumber(complexString.length()));
        }
        return randomString;
    }
    private static int getNumber(int maxSize){
        return (int) Math.round(Math.random()*(maxSize-1));
    }
}
