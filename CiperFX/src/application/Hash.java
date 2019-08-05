package application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Hash {
	private static String bytesToHex(byte[] hash) {
		StringBuilder sb = new StringBuilder();
		for(byte b : hash) {
			sb.append(String.format("%02x", b)); // b에 데이터값을 16진수 형으로 표기함  
		}
		return sb.toString();

	}
	
	ObservableList<String> hashItem = 
			FXCollections.observableArrayList("MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512");
	@FXML
	private TextField hashTextFiled;
	
	@FXML
	private TextArea hashTextArea;
	
	@FXML
	private ChoiceBox<String> choiceBox;
	
	
	@FXML
	private void initialize() {
		choiceBox.setValue("SHA-1");
		choiceBox.setItems(hashItem);

	}
	
	public void Randnum(ActionEvent Evnet) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] readByte = new byte[16];
		sr.nextBytes(readByte);
		hashTextFiled.setText(bytesToHex(readByte));
		
		System.out.println(bytesToHex(readByte));
	}
	
	public void ciper(ActionEvent Event) throws NoSuchAlgorithmException {
		if(getChoice(hashItem).equals("SHA-1"))
		{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(hashTextFiled.getText().getBytes());
		byte[] hash = md.digest();
		hashTextArea.setText(bytesToHex(hash));
	//	System.out.println("성공");
		
		}
		if(getChoice(hashItem).equals("SHA-256")) {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
		//	System.out.println("성공");
			
		}
		if(getChoice(hashItem).equals("SHA-512")) {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
		//	System.out.println("성공");
			
		}

		if(getChoice(hashItem).equals("MD5")) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
		//	System.out.println("성공");
			
		}

		if(getChoice(hashItem).equals("SHA-384")) {
			MessageDigest md = MessageDigest.getInstance("SHA-384");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
		//	System.out.println("성공");
			
		}	if(getChoice(hashItem).equals("SHA-224")) {
			MessageDigest md = MessageDigest.getInstance("SHA-224");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
		//	System.out.println("성공");
			
		}
		

		//	else
			
		//System.out.println("실패");
	
	}
	
	public String getChoice(ObservableList<String> hashItem) {
		String ciper = choiceBox.getValue();
	//	System.out.println(ciper);
		return ciper;
	}
}
