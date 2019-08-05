package application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class Mac {
	
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
			sb.append(String.format("%02x", b)); // b�� �����Ͱ��� 16���� ������ ǥ����  
		}
		return sb.toString();

	}
	
	public void Randnum(ActionEvent Evnet) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] readByte = new byte[16];
		sr.nextBytes(readByte);
		PublicKey.setText(bytesToHex(readByte));
		
		
	}
	
	
	public void ciper(ActionEvent Event) throws Exception {

		KeyGenerator kg = KeyGenerator.getInstance("AES"); 
		SecretKey key = kg.generateKey();
		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(),"HmacSHA1");

		byte[] keybyte = key.getEncoded(); 
		System.out.println("Ű  : "+bytesToHex(keybyte));
		System.out.println("Ű���� : "+keybyte.length+ " byte" );
		System.out.println("�����̽���");
		

		
	}
	
	public String getChoice(ObservableList<String> hashItem) {
		String ciper = choiceBox.getValue();
	//	System.out.println(ciper);
		return ciper;
	}
	
	
}
