package Estruturas.Tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Converter {
    public static String CriptografarMd5(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] senhaBytes = senha.getBytes();
            md.update(senhaBytes);
            byte[] digest = md.digest();
    
            StringBuilder hashString = new StringBuilder();
            for (byte b : digest) {
                hashString.append(String.format("%02x", b));
            }
    
            return hashString.toString(); // Retorna a senha em MD5 como uma string
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Em caso de erro, retorna null ou lida com exceção de outra forma
        }
    }



    
}
    

