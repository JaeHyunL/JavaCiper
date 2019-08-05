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
import javax.crypto.spec.PBEParameterSpec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PassWor {

	@FXML
	private TextArea salt;
	@FXML
	private TextArea aaad;
	
	@FXML
	private TextArea pass;
	@FXML
	private TextArea ciper;
	@FXML
	private TextArea count;
	@FXML 
	private TextArea keybyte;
	
	
	
	
	public byte[] k34 ;
	public byte[] k35 = new byte[8];
	
	
	
	public void Randnum(ActionEvent Evnet) throws NoSuchAlgorithmException {
		byte[] salt2 = new byte[8];  //8����Ʈ��ŭ ��Ʈ�� ����Ұ� 
		SecureRandom random = new SecureRandom(); //������ 
		random.nextBytes(salt2);
		k34 = salt2;
		salt.setText(bytesToHex(salt2));
		
	}
	
	
	
	
	
	public void ciper(ActionEvent Event) throws GeneralSecurityException {
		String pass2 = pass.getText();
		char[] password = pass2.toCharArray();
		//System.out.println("�Է� �н�����: "+pass);
		//System.out.println("�Է� �н�����: "+pass2);

	//	System.out.println("�Է��н����� ĳ������"+password);
		int count2 = Integer.parseInt(count.getText());
		//System.out.println("�ݺ�Ƚ��"+count2);
		PBEKeySpec keySpec = new PBEKeySpec(password);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
	//	System.out.println("������ ���Ű: "+bytesToHex(secretKey.getEncoded()));
		
		PBEParameterSpec params = new PBEParameterSpec(k34, count2); //��Ʈ���ϰ� ���ͷ��� ī��Ʈ �� �������Ѵ� 
		//System.out.println("������ �Ķ����: "+params);
		Charset charset = Charset.forName("UTF-8");
		//System.out.println("����"+aaad.getText());
		String plainText = aaad.getText();
		byte[] encryptData = encrypt(secretKey, params, plainText.getBytes(charset));
		//System.out.println("��ȣ��: "+bytesToHex(encryptData));
		ciper.setText(bytesToHex(encryptData));
	}
	
	public static byte[] decrypt(SecretKey secretKey, PBEParameterSpec params, byte[] encryptData) 
			throws GeneralSecurityException {
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, params);
			byte[] plainData = cipher.doFinal(encryptData); //��ȣ���� ����Ʈ��̷� �־��µ� �������� ��ȣȭ�����͸� �Ű������� �־��� 
			return plainData;
		}
	public static byte[] encrypt(SecretKey secretKey, PBEParameterSpec params, byte[] plainData)  //��ȣŰ �ְ� �Ķ���ͽ��� �ְ� �÷��� �����͸� ����Ʈ ��̷� �־��� 
			throws GeneralSecurityException {
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES"); //������ ��ä �����ϰ� PBEWithMD5AndDes �˰������� ������ ��üȭ�ϴ°��� ������ 
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, params); //init�� ���带 Ʈ�� �޽��� �־���  Ű���� ���ѵ��� 
			byte[] encryptData = cipher.doFinal(plainData);//doFinal <<��ȣȭ �Լ� �޼��带 ���� plainData�� �־��� 
			return encryptData; //��ȣȭ����� �������� 
		}			
	
	
	public static String bytesToHex(byte[] bytes) {
	    StringBuilder sb = new StringBuilder(bytes.length * 2);
	    @SuppressWarnings("resource")
		Formatter formatter = new Formatter(sb);
	    for (byte b : bytes) {
	        formatter.format("%02x", b);
	    }
	    return sb.toString();
	}
}
