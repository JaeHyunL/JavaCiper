package application;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class SymCiper {
	private SecretKey secretKey2;
	private IvParameterSpec ivParameterSpec2;
	private Charset charset2;
	private byte[] encryptData;
	@FXML
	private ChoiceBox<String> modChoice;
	
	@FXML
	private ChoiceBox<String> AlgoChoiceBox;

	@FXML
	private TextArea KeyText;
	
	@FXML
	private TextArea VText;
	
	@FXML
	private TextArea PlainT;
	
	@FXML
	private TextArea CiperT;
	@FXML 
	private TextArea DecT;
	
	ObservableList<String> modItem = 
			FXCollections.observableArrayList("ECB모드구현준비중","CBC");

	ObservableList<String> AlgoItem = 
			FXCollections.observableArrayList("AES/128","DES모드구현준비중");
	@FXML
	private void initialize() {
		AlgoChoiceBox.setValue("AES/128");
		AlgoChoiceBox.setItems(AlgoItem);
		modChoice.setValue("CBC");
		modChoice.setItems(modItem);
		
	}
	public  void Randnum(ActionEvent Evnet) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();
		secretKey2 = secretKey;
		byte[] printKey = secretKey.getEncoded();
		KeyText.setText(bytesToHex(printKey));
	}
	
	public String getChoice(ObservableList<String> hashItem) {
		String ciper = modChoice.getValue();
	//	System.out.println(ciper);
		return ciper;
	}
	
	public String getChoice2(ObservableList<String> hashItem) {
		String ciper2 = modChoice.getValue();
		return ciper2;
	}
	
	
	private static String bytesToHex(byte[] hash) {
		StringBuilder sb = new StringBuilder();
		for(byte b : hash) {
			sb.append(String.format("%02x", b)); // b에 데이터값을 16진수 형으로 표기함  
		}
		return sb.toString();

	}

	
	
	public static byte[] encrypt(SecretKey secretKey, IvParameterSpec
			ivParameterSpec, byte[] plainData) throws GeneralSecurityException {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			byte[] encryptData = cipher.doFinal(plainData);
			return encryptData;
		}
		
		public static byte[] decrypt(SecretKey secretKey, IvParameterSpec
			ivParameterSpec, byte[] encryptData) throws GeneralSecurityException {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			byte[] plainData = cipher.doFinal(encryptData);
			return plainData;
		}
		
	public void IVAction() {
		
		SecureRandom random = new SecureRandom();
		byte[] ivData = new byte[16]; // 128 bit
		random.nextBytes(ivData);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivData);
		ivParameterSpec2 = ivParameterSpec;
		Charset charset = Charset.forName("UTF-8");
		charset2 = charset;
		VText.setText(bytesToHex(ivParameterSpec.getIV()));
	}
	
	public void ciperAction(ActionEvent event) {
		try {
			encryptData = encrypt(secretKey2, ivParameterSpec2, PlainT.getText().getBytes(charset2));
			CiperT.setText(bytesToHex(encryptData));
			//CiperT.setText(bytesToHex(encryptData));
		} catch (GeneralSecurityException e) {
			System.out.println("오류나는듯?");
			e.printStackTrace();
		}
	}
	public void decryptAction(ActionEvent event) throws GeneralSecurityException {
		byte[] plainData = decrypt(secretKey2, ivParameterSpec2, encryptData);
		DecT.setText(new String(plainData, charset2));
		System.out.println("복호문 :"+new String(plainData, charset2));  
	}
	
}
