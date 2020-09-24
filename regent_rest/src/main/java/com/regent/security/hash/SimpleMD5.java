package com.regent.security.hash;
import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;
 
 
 public class SimpleMD5
 {
   public static String hash(String password) {
     String generatedPassword = null;
     
     try {
       MessageDigest md = MessageDigest.getInstance("MD5");
       
       md.update(password.getBytes());
       
       byte[] bytes = md.digest();
 
       
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < bytes.length; i++)
       {
         sb.append(Integer.toString((bytes[i] & 0xFF) + 256, 16).substring(1));
       }
       
       generatedPassword = sb.toString();
     }
     catch (NoSuchAlgorithmException e) {
       
       e.printStackTrace();
     } 
     return generatedPassword;
   }
 }
