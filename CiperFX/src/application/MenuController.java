package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {


	public void hashButton(ActionEvent event) throws IOException {
		//System.out.println("�׽�Ʈ");
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Hash.fxml"));
		Scene scene = new Scene(root,400,600);
		primaryStage.setScene(scene); //â�� ũ�⿡ �°� ���°� 
		primaryStage.setTitle("�ؽ��Լ�");
		primaryStage.show();

	}
	
	public void macButton(ActionEvent event) throws IOException {
		Stage primaryStage2 = new Stage();
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/mac.fxml"));
		Scene scene = new Scene(root2,400,600);
		primaryStage2.setScene(scene); //â�� ũ�⿡ �°� ���°� 
		primaryStage2.setTitle("��");
		primaryStage2.show();

	}
	
	public void PassButton(ActionEvent event) throws IOException {
		Stage primaryStage3 = new Stage();
		Parent root3 = FXMLLoader.load(getClass().getResource("/application/PassWor.fxml"));
		Scene scene = new Scene(root3,400,600);
		primaryStage3.setScene(scene); //â�� ũ�⿡ �°� ���°� 
		primaryStage3.setTitle("�н�");
		primaryStage3.show();

	}
	public void SymButton(ActionEvent event) throws IOException {
		Stage primaryStage4 = new Stage();
		Parent root4 = FXMLLoader.load(getClass().getResource("/application/SymCiper.fxml"));
		Scene scene = new Scene(root4,400,600);
		primaryStage4.setScene(scene); //â�� ũ�⿡ �°� ���°� 
		primaryStage4.setTitle("��Ī");
		primaryStage4.show();
	}
	
	public void PbkButton(ActionEvent event) throws IOException {
		Stage primaryStage5 = new Stage();
		Parent root5 = FXMLLoader.load(getClass().getResource("/application/Pbk.fxml"));
		Scene scene = new Scene(root5,400,600);
		primaryStage5.setScene(scene); //â�� ũ�⿡ �°� ���°� 
		primaryStage5.setTitle("PbK");
		primaryStage5.show();
	}
	public void FileButton(ActionEvent event) throws IOException {
		Stage primaryStage6 = new Stage();
		Parent root6 = FXMLLoader.load(getClass().getResource("/application/fileout.fxml"));
		Scene scene = new Scene(root6,400,600);
		primaryStage6.setScene(scene); //â�� ũ�⿡ �°� ���°� 
		primaryStage6.setTitle("���Ͼ�ȣȭ");
		primaryStage6.show();
	}
		
	public void RsaSignButton(ActionEvent event) throws IOException {
		Stage primaryStage7 = new Stage();
		Parent root7 = FXMLLoader.load(getClass().getResource("/application/RSAsign.fxml"));
		Scene scene = new Scene(root7,400,600);
		primaryStage7.setScene(scene); //â�� ũ�⿡ �°� ���°� 
		primaryStage7.setTitle("RSA ���ڼ���");
		primaryStage7.show();
	}
	public void X509Button(ActionEvent event) throws IOException {
		Stage primaryStage8 = new Stage();
		Parent root8 = FXMLLoader.load(getClass().getResource("/application/X509sign.fxml"));
		Scene scene = new Scene(root8,400,800);
		primaryStage8.setScene(scene); //â�� ũ�⿡ �°� ���°� 
		primaryStage8.setTitle("X509������ ����");
		primaryStage8.show();
	}
}
