package application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Fileout {
	 @FXML
	    private Button btn1;
	 @FXML 
	 private TextField TextF;
	 @FXML
	 private Label good;
	 public File selectedFile;
	 private String path;
	 
	 /*
	  * 파일 선택 함수 
	  */
	 public void Button1Action(ActionEvent event){
	        FileChooser fc = new FileChooser();
	        fc.setInitialDirectory(new File ("C:\\Program Files"));
	        fc.getExtensionFilters().addAll(new ExtensionFilter("txt files","*.txt"));
	         selectedFile = fc.showOpenDialog(null);
	        if(selectedFile != null){
	          	TextF.setText(selectedFile.getAbsolutePath());
	        } else {
	            System.out.println("File is not valid");
	        }
	    }
	
	 
	 /*
	  * @return 파일에 대한 암 복호화
	  */
	 public void ciperAction(ActionEvent event) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		
		 	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			String transformation = "AES/ECB/PKCS5Padding";
			File plainFile = new File(selectedFile.getAbsolutePath());
			File encryptFile = new File("암호화된"+selectedFile.getName());
			File decryptFile = new File("복호화된"+selectedFile.getName());
			 path = selectedFile.getAbsolutePath();
			{
				Cipher cipher = Cipher.getInstance(transformation);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				InputStream input =null;
				OutputStream output = null;
				try {
					input = new BufferedInputStream(new FileInputStream(plainFile));
					output = new CipherOutputStream(new BufferedOutputStream(new
					FileOutputStream(encryptFile)), cipher);
					int read = 0;
					byte[] buffer = new byte[1024];
					while ((read = input.read(buffer)) != -1) {
						output.write(buffer, 0, read);
					}
				} finally {
					if (output != null) try {output.close();} catch(IOException ie) {}
					if (input != null) try {input.close();} catch(IOException ie) {}
				}
			}
			{
				Cipher cipher = Cipher.getInstance(transformation);
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				InputStream input = null;
				OutputStream output = null;
				try {
					input = new CipherInputStream(new BufferedInputStream(new
					FileInputStream(encryptFile)), cipher);
					output = new BufferedOutputStream(new
					FileOutputStream(decryptFile));
					int read = 0;
					byte[] buffer = new byte[1024];
					while ((read = input.read(buffer)) != -1) {
						output.write(buffer, 0, read);
					}
				} finally {
					if (output != null) try {output.close();} catch(IOException ie) {}
					if (input != null) try {input.close();} catch(IOException ie) {}
				}
			}
			good.setText(decryptFile.getAbsolutePath());
	 }
}

