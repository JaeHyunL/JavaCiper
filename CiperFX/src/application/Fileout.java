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
	 
	 public void Button1Action(ActionEvent event){
	        FileChooser fc = new FileChooser();
	        fc.setInitialDirectory(new File ("C:\\Program Files"));
	        fc.getExtensionFilters().addAll(new ExtensionFilter("txt files","*.txt"));
	         selectedFile = fc.showOpenDialog(null);
	        if(selectedFile != null){
	        	System.out.println("파일암호화" + selectedFile.getAbsolutePath());
	          	TextF.setText(selectedFile.getAbsolutePath());
	          	System.out.println("이게뭐냐면은"+selectedFile.getName());
	        } else {
	            System.out.println("File is not valid");
	        }
	    }
	
	 public void ciperAction(ActionEvent event) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		

		 	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			String transformation = "AES/ECB/PKCS5Padding";
			File plainFile = new File(selectedFile.getAbsolutePath());
		//	File plainFile = new File("plain.txt");
			System.out.println("파일이생성"+ plainFile);
		File encryptFile = new File("암호화된"+selectedFile.getName());
			File decryptFile = new File("복호화된"+selectedFile.getName());
			 path = selectedFile.getAbsolutePath();
			
			// 파일 암호화
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
			
			// 파일 복호화
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

