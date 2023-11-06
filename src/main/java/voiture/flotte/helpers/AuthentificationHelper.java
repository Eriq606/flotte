package voiture.flotte.helpers;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class AuthentificationHelper {
   private final long EXPIRATION=1800;
   public long getEXPIRATION() {
      return EXPIRATION;
   }
   public static String createSHAHash(String input) throws NoSuchAlgorithmException {
      String hashtext=null;
      MessageDigest md=MessageDigest.getInstance("SHA3-256");
      byte[] messageDigest=md.digest(input.getBytes(StandardCharsets.UTF_8));
      hashtext=convertToHex(messageDigest);
      return hashtext;
   }
   private static String convertToHex(final byte[] messageDigest) {
      BigInteger bigint=new BigInteger(1, messageDigest);
      String hexText=bigint.toString(16);
      while (hexText.length() < 32) {
         hexText="0".concat(hexText);
      }
      return hexText;
   }
   public static String decodeBase64(String hash){
      return new String(Base64.getDecoder().decode(hash));
   }
   public static String getAuthorizationContent(String auth){
      return auth.split(" ")[1];
   }
   public static String[] getUserInfo(String auth){
      return decodeBase64(auth).split(":");
   }
   public static String createToken(String auth, LocalDateTime now) throws NoSuchAlgorithmException{
      String[] infos=AuthentificationHelper.getUserInfo(auth);
      String currentDateHash=now.format(DateTimeFormatter.ofPattern("YmMDDDYHHssm"));
      String token=AuthentificationHelper.createSHAHash(infos[0])+currentDateHash;
      return token;
   }
}
