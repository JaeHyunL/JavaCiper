package application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class hMac {
	
	@FXML
	private TextArea PublicKey;
	
	@FXML
	private TextArea Text;
	
	@FXML
	private TextArea CiperText;
	@FXML
	private ChoiceBox<String> choiceBox;
	
	 
	ObservableList<String> macItem = 
			FXCollections.observableArrayList("HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512");
	

	@FXML
	private void initialize() {
		choiceBox.setValue("HmacMD5");
		choiceBox.setItems(macItem);

	}
	private static String bytesToHex(byte[] hash) {
		StringBuilder sb = new StringBuilder();
		for(byte b : hash) {
			sb.append(String.format("%02x", b)); // b에 데이터값을 16진수 형으로 표기함  
		}
		return sb.toString();

	}

	public void Randnum(ActionEvent Evnet) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] readByte = new byte[16];
		sr.nextBytes(readByte);
		PublicKey.setText(bytesToHex(readByte));
		
	}
	
	/*
	 * hmac 함수 값 계산 
	 * @param 입력값 String 
	 * @return hmac값 계산 
	 */
	public void ciper(ActionEvent Event) throws Exception {
	
		if(getChoice(macItem).equals("HmacSHA1")) {
				SecretKeySpec keySpec = new SecretKeySpec(PublicKey.getText().getBytes(),"HmacSHA1");
		byte[] keybyte = keySpec.getEncoded(); 
		
		String plaintext = Text.getText();
		   Mac mac = Mac.getInstance("HmacSHA1");
		   mac.init(keySpec); 
		   mac.update(plaintext.getBytes()); 
		   byte[] digest01 = mac.doFinal();
		   CiperText.setText(bytesToHex(digest01));
		}
				if(getChoice(macItem).equals("HmacMD5")) {
					SecretKeySpec keySpec = new SecretKeySpec(PublicKey.getText().getBytes(),"HmacMD5");
			byte[] keybyte = keySpec.getEncoded(); 
			
			String plaintext = Text.getText();
			   Mac mac = Mac.getInstance("HmacMD5");
			   mac.init(keySpec); 
			   mac.update(plaintext.getBytes()); 
			   byte[] digest01 = mac.doFinal();
			   CiperText.setText(bytesToHex(digest01));
			}
			if(getChoice(macItem).equals("HmacSHA224")) {
				SecretKeySpec keySpec = new SecretKeySpec(PublicKey.getText().getBytes(),"HmacSHA224");
		byte[] keybyte = keySpec.getEncoded(); 
		
		String plaintext = Text.getText();
		   Mac mac = Mac.getInstance("HmacSHA224");
		   mac.init(keySpec); 
		   mac.update(plaintext.getBytes()); 
		   byte[] digest01 = mac.doFinal();
		   CiperText.setText(bytesToHex(digest01));
		}	
				if(getChoice(macItem).equals("HmacSHA256")) {
				SecretKeySpec keySpec = new SecretKeySpec(PublicKey.getText().getBytes(),"HmacSHA256");
		byte[] keybyte = keySpec.getEncoded(); 
		
		String plaintext = Text.getText();
		   Mac mac = Mac.getInstance("HmacSHA256");
		   mac.init(keySpec); 
		   mac.update(plaintext.getBytes()); 
		   byte[] digest01 = mac.doFinal();
		   CiperText.setText(bytesToHex(digest01));
	}
	
				if(getChoice(macItem).equals("HmacSHA384")) {
					SecretKeySpec keySpec = new SecretKeySpec(PublicKey.getText().getBytes(),"HmacSHA384");
			byte[] keybyte = keySpec.getEncoded(); 
			
			String plaintext = Text.getText();
			   Mac mac = Mac.getInstance("HmacSHA384");
			   mac.init(keySpec); 
			   mac.update(plaintext.getBytes()); 
			   byte[] digest01 = mac.doFinal();
			   CiperText.setText(bytesToHex(digest01));
		}
				if(getChoice(macItem).equals("HmacSHA512")) {
					SecretKeySpec keySpec = new SecretKeySpec(PublicKey.getText().getBytes(),"HmacSHA512");
			byte[] keybyte = keySpec.getEncoded(); 
			
			String plaintext = Text.getText();
			   Mac mac = Mac.getInstance("HmacSHA512");
			   mac.init(keySpec); 
			   mac.update(plaintext.getBytes()); 
			   byte[] digest01 = mac.doFinal();
			   CiperText.setText(bytesToHex(digest01));
		}
	
	}
	/*
	 * Item 선택 함수
	 */
	public String getChoice(ObservableList<String> hashItem) {
		String ciper = choiceBox.getValue();
		return ciper;
	}
	
	
}
