package ru.itis.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(password.getBytes());
        StringBuilder stringBuffer = new StringBuilder();
        for (byte hashByte : hashBytes) {
            stringBuffer.append(String.format("%02x", hashByte));
        }
        return stringBuffer.toString();
    }

}
