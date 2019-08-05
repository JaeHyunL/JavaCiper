package application;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Pbk {
	private byte[] salt ; 
	@FXML
	private TextArea RandT;
	@FXML
	private TextArea CiperT;
	@FXML
	private TextArea PassT;
	@FXML
	private TextArea PbkT;
	@FXML
	private TextArea count;
	@FXML
	private TextArea plainT;
	private int iterationCount ;
	private SecretKey secretKey;
	Charset charset = Charset.forName("UTF-8");
	public static String bytesToHex(byte[] bytes) {
	    StringBuilder sb = new StringBuilder(bytes.length * 2);
	    @SuppressWarnings("resource")
		Formatter formatter = new Formatter(sb);
	    for (byte b : bytes) {
	        formatter.format("%02x", b);
	    }
	    return sb.toString();
	}
	public  void soltAction() {
		salt = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		System.out.println("salt: "+bytesToHex(salt));
		RandT.setText(bytesToHex(salt));
	}
	public void KeyAction() throws NoSuchAlgorithmException, InvalidKeySpecException {
	System.out.println("허허");
	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	iterationCount = Integer.parseInt(count.getText());
	PBEKeySpec keySpec = new PBEKeySpec(PassT.getText().toCharArray(), salt, iterationCount, 128);
	 secretKey = new SecretKeySpec(keyFactory.generateSecret(keySpec).getEncoded(), "AES");
	System.out.println("생성된 비밀키: "+bytesToHex(secretKey.getEncoded()));
	PbkT.setText(bytesToHex(secretKey.getEncoded()));
	
	}
	public void CiperAction() throws GeneralSecurityException {
		byte[] encryptData = encrypt(secretKey, plainT.getText().getBytes(charset));
		CiperT.setText(bytesToHex(encryptData));
	}
	public static byte[] encrypt(SecretKey secretKey, byte[] plainData)
			throws GeneralSecurityException {
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				byte[] encryptData = cipher.doFinal(plainData);
				return encryptData;
			}
			
			public static byte[] decrypt(SecretKey secretKey, byte[] encryptData)
			throws GeneralSecurityException {
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				byte[] plainData = cipher.doFinal(encryptData);
				return plainData;
			}
			

}
