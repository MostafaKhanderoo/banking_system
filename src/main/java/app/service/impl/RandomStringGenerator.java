package app.service.impl;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private RandomStringGenerator() {
    }

    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String cardNumber = "1234567890";
    private static final short length =10;
    private static final SecureRandom random= new SecureRandom();

    public static String generateRandomString(){
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i <=10; i++)
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));

        return stringBuilder.toString();
    }

    public static String cardNumberRandomString(){
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i =0 ;i<=10;i++)
            stringBuilder.append(cardNumber.charAt(random.nextInt(cardNumber.length())));

        return stringBuilder.toString();
    }
    public static String cvvRandomString(){
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i =0 ;i<=2;i++)
            stringBuilder.append(cardNumber.charAt(random.nextInt(cardNumber.length())));

        return stringBuilder.toString();
    }

}
