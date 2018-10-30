package com.casoo7.common.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * 加密工具
 * @author yangri
 *
 */
public class RSAUtil {
	/**
	 * 
	 * 生成公私钥
	 * 
	 */
	public Map<String, String> getAllKey(){
    	Map<String, String> map = new HashMap<>();
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom(new Date().toString().getBytes());
            keyPairGenerator.initialize(1024, secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            String tpublicKeyBytes = new String(Base64.encodeBase64(publicKeyBytes));
            String tprivateKeyBytes = new String(Base64.encodeBase64(privateKeyBytes));
            map.put("publickey", tpublicKeyBytes);
            map.put("privatekey", tprivateKeyBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }
	/**
	 * 
	 * 获取公钥
	 * 
	 */
	public static PublicKey getPublicKey(byte[] publicKeyBytes) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes); 
        KeyFactory kf = KeyFactory.getInstance("RSA");  
        return kf.generatePublic(spec); 
    }
	/**
	 * 
	 * 获取私钥
	 * 
	 */
	public static PrivateKey getPrivateKey(byte[] privateKeyBytes)throws Exception { 
        PKCS8EncodedKeySpec spec =new PKCS8EncodedKeySpec(privateKeyBytes); 
        KeyFactory kf = KeyFactory.getInstance("RSA"); 
        return kf.generatePrivate(spec); 
    }	
	/**
	 * 
	 * 加密
	 * 
	 */
	public String getEncrypt(String publicKey,String password){
		Cipher cipher;
    	RSAPublicKey rsaPubKey;
    	try {
    		rsaPubKey = (RSAPublicKey) getPublicKey(Base64.decodeBase64(publicKey));
    		cipher = Cipher.getInstance("RSA");
    		cipher.init(Cipher.ENCRYPT_MODE, rsaPubKey); 
    		byte[] cipherText = cipher.doFinal(password.getBytes()); 
    		return new String(Base64.encodeBase64(cipherText));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
	}
	/**
	 * 
	 * 解密
	 * 
	 */
	public static String getDecrypt(String publicKey,String  privateKey,String enpassword)throws Exception {
    	String password = "";
    	Cipher cipher;
    	PublicKey pubKey = getPublicKey(Base64.decodeBase64(publicKey));
    	PrivateKey privKey = getPrivateKey(Base64.decodeBase64(privateKey));
    	try{
	    	cipher = Cipher.getInstance("RSA");
	    	cipher.init(Cipher.ENCRYPT_MODE, pubKey);
	        cipher.init(Cipher.DECRYPT_MODE, privKey);
	        byte[] plainText = cipher.doFinal(Base64.decodeBase64(enpassword));
	        password = new String(plainText);
	        return password;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return null;
    }
	
	public static void main(String[] args) {
		try{
			RSAUtil m = new RSAUtil();
//			Map<String,String> map = new HashMap<>();
//			map = m.getAllKey();
//			System.out.println(map.get("publickey"));
//			System.out.println(map.get("privatekey"));
//			String pw=m.getEncrypt("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNxFGStd6RpamAUBjiUYqrbwQeCG0dDE1gyYvC+rIO4jITCk0R67DJuq351FgvW5zGQ4AEJl7LtV19XzYi7JhedL7gC0eP9xxdBeStnoemtYK3xtkvTlxiI2Xwie68nNNwi9zT+tZT1jAUWDsC/8RmHOColOQGIgZv33mh7Bur8wIDAQAB", "0123456789");
//			System.out.println(pw);
			String pw = m.getDecrypt("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNxFGStd6RpamAUBjiUYqrbwQeCG0dDE1gyYvC+rIO4jITCk0R67DJuq351FgvW5zGQ4AEJl7LtV19XzYi7JhedL7gC0eP9xxdBeStnoemtYK3xtkvTlxiI2Xwie68nNNwi9zT+tZT1jAUWDsC/8RmHOColOQGIgZv33mh7Bur8wIDAQAB", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI3EUZK13pGlqYBQGOJRiqtvBB4IbR0MTWDJi8L6sg7iMhMKTRHrsMm6rfnUWC9bnMZDgAQmXsu1XX1fNiLsmF50vuALR4/3HF0F5K2eh6a1grfG2S9OXGIjZfCJ7ryc03CL3NP61lPWMBRYOwL/xGYc4KiU5AYiBm/feaHsG6vzAgMBAAECgYBf27E0vJkkbpnwUzigP3a+eLCWBiiRjbH8kgtY6d7gRX6KdVqrlWzS2tGsrjJ6wz18qkOziqZqo9X1Yh3e+5ACtHMSV5uNf5ooP0yx88Ey0PfoxUQLRMioeu7+kHR5xdZYSXYShSuTRFUps4hvXO7hNWMpvx0q/oWPMi5ZBannEQJBAPW/FG6duRBNgRozzvQV9xkgF04DXQRKMlUgVeRN1Chjc6hTLG4znjlhXTvak8x/iZ41ejYC+CJfwzlDfz3Pxt8CQQCTrpcDv38kmbBXqxXp++P72d1dqtOFVNtoI2tBiuWBvD4xUQpZ6dI7asYUqGvvJU6HxWD8mKVMhBtTX2BAZuFtAkBXAzpT1Grjl92/jlqX2HMupUs7Jzu3OiJKO/HNrbUSO+yGzDRuLL64aH4fpEwy23G54AuOtkxvt2Kjkv+pvjkXAkAZyJeRDfPyveptw+71hQbOx8DAQ7zeQGx2CAkHzmo3dlgdYBrAi/Qqb3/Zx3XgsNC/H5TJRRVHAKGMstApLk0ZAkEAvHUMkTcXHS+TitbWFzcob9QzUO8euIAODQmqZAlCuVQJgIPH2U9prJdXHXwWyWUksJN250XMkRthnPM6rQDeow==", "d8bS1LtBVYq6lBppsXAYw2h/X4nkhSxZxKWD8ymWuyh+JPKmap0h/wvyVmJmlzneqbR8kAuyavuw5fUPVmZFDebrndFx3OJLcOcAG0MAOXl6LH/PdMbMyVO0pzetyc+aAJViKaQR/Kyrjqx5hJF/tjv6hy5ucFZn118Q3hz8nNM=");
			System.out.println(pw);
		}catch(Exception e){
			
		}
	}
}
