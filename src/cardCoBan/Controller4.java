package cardCoBan;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;

public class Controller4 {
	
	private  String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TheNganHang;user=sa;password=123456";
	
	@FXML
	private TextField ID;
	@FXML
	private TextField Name;

	@FXML
	private TextField SoDu;
	@FXML
	private  DatePicker datePicker = new DatePicker();
	public void add(ActionEvent e) {
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			if(con!=null) {
			LocalDate cl1;
			cl1 = datePicker.getValue();
			Statement statement =  (Statement) con.createStatement();
			int id= Integer.parseInt(ID.getText());
			String name = Name.getText();
			int day = cl1.getDayOfMonth();
			int month = cl1.getMonthValue();
			int year = cl1.getYear();
			int sodu=Integer.parseInt(SoDu.getText());
			String addsql="insert into TNH values('" +id+ "','" +name+ "','" +year+ "/"+month+"/"+day+ "','"+sodu+"','The co Ban');";
			statement.executeUpdate(addsql);
			}
			else {
				System.out.println("Khong ket noi duoc sql");
			}
	}catch (Exception ex) {
    ex.printStackTrace();
	}
}
	public void back(ActionEvent e) {
		Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/cardCoBan/Sample2.fxml"));
    	Parent view = null;
		try {
			view = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Scene scene = new Scene(view);
    	stage.setScene(scene);
	}
}