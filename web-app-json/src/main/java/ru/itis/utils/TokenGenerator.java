package ru.itis.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class TokenGenerator {

    private static SecureRandom random = new SecureRandom();

    public  static BigInteger nextSessionId() {
        return new BigInteger(30, random);
    }

}
