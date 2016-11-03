package ru.itis.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class HashGenerator {

    public static String md5ApacheHash(String text){
        return DigestUtils.md5Hex(text);
    }

}
