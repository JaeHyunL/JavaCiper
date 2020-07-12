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

/* 
 * @param hash 해쉬 값 을 입력변수로 받음
 * @param hashItem 암호화 알고리즘 종류  리스트  
 * @return 16진수로 데이터 값을 반환함 
 */
public class Hash {
	private static String bytesToHex(byte[] hash) {
		StringBuilder sb = new StringBuilder();
		for(byte b : hash) {
			sb.append(String.format("%02x", b));  
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
	/*
	 * @return 난수 값 생성
	 */
	public void Randnum(ActionEvent Evnet) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] readByte = new byte[16];
		sr.nextBytes(readByte);
		hashTextFiled.setText(bytesToHex(readByte));
	}
	
	/*
	 * input 평문 
	 * @return 암호문 
	 * @param md 인스턴스를 가지고있음 
	 * @param hash 암호화 된 내용을 가지고 있음 
	 */
	public void ciper(ActionEvent Event) throws NoSuchAlgorithmException {
		if(getChoice(hashItem).equals("SHA-1"))
		{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(hashTextFiled.getText().getBytes());
		byte[] hash = md.digest();
		hashTextArea.setText(bytesToHex(hash));
		
		}
		if(getChoice(hashItem).equals("SHA-256")) {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
			
		}
		if(getChoice(hashItem).equals("SHA-512")) {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
		}

		if(getChoice(hashItem).equals("MD5")) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));	
		}

		if(getChoice(hashItem).equals("SHA-384")) {
			MessageDigest md = MessageDigest.getInstance("SHA-384");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
		}	
		
		if(getChoice(hashItem).equals("SHA-224")) {
			MessageDigest md = MessageDigest.getInstance("SHA-224");
			md.update(hashTextFiled.getText().getBytes());
			byte[] hash = md.digest();
			hashTextArea.setText(bytesToHex(hash));
			
		}
	
	}
	/* 
	 * @return gui에서 선택한 해당 알고리즘 
	 */
	public String getChoice(ObservableList<String> hashItem) {
		String ciper = choiceBox.getValue();
		return ciper;
	}
}
