package org.sup.supabaseconfx.bd.hash;

import javax.crypto.SecretKeyFactory;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class configHash {

   private configHash(){};

   private static final String ALGORITMO = "PBKDF2WithHmacSHA256";
   private static final int CICLOS = 120_00;
   private static final int LONGITUD_CLAVE = 256;


   public static byte[] generarSalt(){
       byte[] salt = new byte[16];
       new SecureRandom().nextBytes(salt);
       return salt;
   }

   public static byte[] generarHash(char[] contrasenia, byte[] salt)throws Exception{
       PBEKeySpec spec = new PBEKeySpec(contrasenia, salt, CICLOS, LONGITUD_CLAVE);
       SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITMO);
       byte[] hash = skf.generateSecret(spec).getEncoded();

       Arrays.fill(contrasenia, '\u0000');
       return hash;
   }

   public static boolean comprobarUsuario(char[] contrasenia, byte[] salt, byte[] hashComparado)throws Exception{
       byte[] candidato = generarHash(contrasenia, salt);
       boolean ok = MessageDigest.isEqual(candidato, hashComparado);
       Arrays.fill(candidato, (byte) 0);
       Arrays.fill(contrasenia, '\u0000');
       return ok;
   }

   public static String toBase64(byte[] array){
       return java.util.Base64.getEncoder().encodeToString(array);
   }

   public static byte[] fromBase64(String texto){
       return java.util.Base64.getDecoder().decode(texto);
   }



}
