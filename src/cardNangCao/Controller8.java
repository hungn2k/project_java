package cardNangCao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class Controller8 {
	public int x;
	private  String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TheNganHang;user=sa;password=123456";
	private String ud1="UPDATE TNH set ID='";
	private String db2="',TNH.name='";
	private String db3="',thoigianlamthe='";
	private String db4="',TNH.loaithe='";
	private String db5="',TNH.sodu='";
	private String db6="' where TNH.ID = ";
	private String db7=";";
	@FXML
	private TextField ID;
	@FXML
	private TextField Name;
	@FXML
	private TextField Day;
	@FXML
	private TextField Month;
	@FXML
	private TextField Year;
	@FXML
	private TextField SoDu;
	@FXML
	private TextField LoaiThe;
	
	public void setTNC(TNC tnc) {
		ID.setText(Integer.toString(tnc.ID));
		Name.setText(tnc.name);
		Year.setText(Integer.toString(tnc.time.get(tnc.time.YEAR)));
		Month.setText(Integer.toString(tnc.time.get(tnc.time.MONTH)));
		Day.setText(Integer.toString(tnc.time.get(tnc.time.DAY_OF_MONTH)));
		SoDu.setText(Integer.toString(tnc.SoDu));
		LoaiThe.setText("The Nang Cao");
		x=Integer.parseInt(ID.getText());
	}
	public void Edit(ActionEvent e) {
		int ID1 = Integer.parseInt(ID.getText());
		String name= Name.getText();
		int day1 =Integer.parseInt(Day.getText());
		int month1= Integer.parseInt(Month.getText());
		int Year1=Integer.parseInt(Year.getText());
		int SoDu1 = Integer.parseInt(SoDu.getText());
		String lt=LoaiThe.getText();
		String ud=ud1+ID1+db2+name+db3+Year1+"/"+month1+"/"+day1+db4+lt+db5+SoDu1+db6+x+db7;;
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			if(con!=null) {
			Statement statement =  (Statement) con.createStatement();
			statement.execute(ud);
			}else {
				System.out.println("Khong ket noi duoc sql");
			}
			}catch (Exception ex) {
			    ex.printStackTrace();
			}
	}
	public void back(ActionEvent e) {
		Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/cardNangCao/Sample6.fxml"));
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
