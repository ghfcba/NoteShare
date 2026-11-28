package ru.itis.noteshare.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
    public static String hashPassword(String pass) {
        String salt = BCrypt.gensalt(12);

        return BCrypt.hashpw(pass,salt);
    }

    public static boolean verify(String pass, String hashpass) {
        return BCrypt.checkpw(pass,hashpass);
    }
}