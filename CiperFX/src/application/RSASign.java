package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Formatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class RSASign {

	
	@FXML
	private TextArea SignPublic;
	@FXML
	private TextArea SignPrivate;
	@FXML
	private TextArea PlainT;
	@FXML
	private TextArea SignCreate;
	@FXML
	private Label Label2;
	
	File publicKeyFile = new File("public.key");
	File privateKeyFile = new File("private.key");
	PublicKey publicKey = null;
	PrivateKey privateKey = null;
	private byte[] signature;
	private Charset charset=  Charset.forName("UTF-8") ;
	public void RSAcompare(ActionEvent evnet) throws GeneralSecurityException {
		
		boolean verified = verify(publicKey, signature, PlainT.getText().getBytes(charset));
		System.out.println("서명검증 = " + verified);
		Label2.setText(String.valueOf(verified));
	}
	public void RSACrea(ActionEvent evnet ) throws GeneralSecurityException {
		signature = sign(privateKey, PlainT.getText().getBytes(charset));
		SignCreate.setText(bytesToHex(signature));

	}
	public void RSAcreatee(ActionEvent event) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		if (publicKeyFile.exists()&&  privateKeyFile.exists()) {
		Path publicFile = Paths.get("public.key");
		byte[] publicKeyBytes = Files.readAllBytes(publicFile);
		Path privateFile = Paths.get("private.key");
		byte[] privateKeyBytes = Files.readAllBytes(privateFile);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
		privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
		SignPublic.setText(bytesToHex(publicKeyBytes));
		SignPrivate.setText(bytesToHex(privateKeyBytes));
		
		}
		else {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			KeyPair pair = generator.generateKeyPair();
			publicKey = pair.getPublic();
			privateKey = pair.getPrivate();
			FileOutputStream outputPublic = new FileOutputStream(new File("public.key"));
			outputPublic.write(publicKey.getEncoded());
			FileOutputStream outputPrivate = new FileOutputStream(new File("private.key"));
			outputPrivate.write(privateKey.getEncoded());
		}
	}
	/*public void RSAcreate(ActionEvent event) throws NoSuchAlgorithmException, IOException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();
		publicKey = pair.getPublic();
		privateKey = pair.getPrivate();
		FileOutputStream outputPublic = new FileOutputStream(new File("public.key"));
		outputPublic.write(publicKey.getEncoded());
		FileOutputStream outputPrivate = new FileOutputStream(new File("private.key"));
		outputPrivate.write(privateKey.getEncoded());
	}*/
	
	
	public static byte[] sign(PrivateKey privateKey, byte[] plainData) throws
	GeneralSecurityException {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(plainData);
		byte[] signatureData = signature.sign();
		return signatureData;
	}
	
	public static boolean verify(PublicKey publicKey, byte[] signatureData,
	byte[] plainData) throws GeneralSecurityException {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(plainData);
		return signature.verify(signatureData);
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
