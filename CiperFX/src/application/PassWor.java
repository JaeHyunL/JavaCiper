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
		byte[] salt2 = new byte[8];  //8바이트만큼 솔트를 사용할것 
		SecureRandom random = new SecureRandom(); //랜덤값 
		random.nextBytes(salt2);
		k34 = salt2;
		salt.setText(bytesToHex(salt2));
		
	}
	
	
	
	
	
	public void ciper(ActionEvent Event) throws GeneralSecurityException {
		String pass2 = pass.getText();
		char[] password = pass2.toCharArray();
		//System.out.println("입력 패스워드: "+pass);
		//System.out.println("입력 패스워드: "+pass2);

	//	System.out.println("입력패스워드 캐릭터형"+password);
		int count2 = Integer.parseInt(count.getText());
		//System.out.println("반복횟수"+count2);
		PBEKeySpec keySpec = new PBEKeySpec(password);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
	//	System.out.println("생성된 비밀키: "+bytesToHex(secretKey.getEncoded()));
		
		PBEParameterSpec params = new PBEParameterSpec(k34, count2); //솔트값하고 인터레션 카운트 값 이지랄한대 
		//System.out.println("생성된 파라메터: "+params);
		Charset charset = Charset.forName("UTF-8");
		//System.out.println("생성"+aaad.getText());
		String plainText = aaad.getText();
		byte[] encryptData = encrypt(secretKey, params, plainText.getBytes(charset));
		//System.out.println("암호문: "+bytesToHex(encryptData));
		ciper.setText(bytesToHex(encryptData));
	}
	
	public static byte[] decrypt(SecretKey secretKey, PBEParameterSpec params, byte[] encryptData) 
			throws GeneralSecurityException {
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, params);
			byte[] plainData = cipher.doFinal(encryptData); //암호문을 바이트어레이로 넣엇는데 차이점은 암호화데이터를 매개변수로 넣어줌 
			return plainData;
		}
	public static byte[] encrypt(SecretKey secretKey, PBEParameterSpec params, byte[] plainData)  //암호키 넣고 파라미터스펙 넣고 플레인 데이터를 바이트 어레이로 넣어줌 
			throws GeneralSecurityException {
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES"); //사이퍼 객채 생성하고 PBEWithMD5AndDes 알고리즘으로 사이퍼 객체화하는것을 선언함 
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, params); //init은 운영모드를 트루 펄스로 넣어줌  키들어가고 스팩들어가고 
			byte[] encryptData = cipher.doFinal(plainData);//doFinal <<암호화 함수 메서드를 실행 plainData를 넣어줌 
			return encryptData; //암호화결과를 리턴해줌 
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
