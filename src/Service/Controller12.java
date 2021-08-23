package Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;

import cardCoBan.TCB;
import cardNangCao.TNC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller12 {
	private  String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TheNganHang;user=sa;password=123456";
	private String sl1 = "select TNH.name,TNH.ID,year(tnh.thoigianlamthe) as nam,month(tnh.thoigianlamthe) as thang,day(tnh.thoigianlamthe) as ngay,tnh.SoDu,b.tg "
			+ " from TNH, (select TNH.ID,DATEDIFF(month,TNH.thoigianlamthe,'";
			
	private String sl12="') as tg from TNH)b where b.ID=TNH.ID and TNH.loaithe like '%The Co Ban%';";
	private String sl22="') as tg from TNH)b where b.ID=TNH.ID and TNH.loaithe like '%The Nang Cao%';";
	public int Sum=0;
	@FXML
	private TextField KetQua;
	@FXML
	private  DatePicker datePicker = new DatePicker();

	public void search(ActionEvent e) {

		try {
			Connection con = DriverManager.getConnection(DB_URL);
			if(con!=null) {
				Sum =0;
				LocalDate cl1;
				cl1 = datePicker.getValue();
				
			Statement statement1 =  (Statement) con.createStatement();
			ResultSet rs = statement1.executeQuery(sl1+cl1.getYear()+"/"+cl1.getMonth()+"/"+cl1.getDayOfMonth()+sl12);
			while(rs.next()) {
				TCB tcb ;
				Calendar calendar = Calendar.getInstance();
				String name = rs.getString(1);
				int id =rs.getInt(2);
				int nam=rs.getInt(3);
				int thang=rs.getInt(4);
				int ngay=rs.getInt(5);
				int sodu=rs.getInt(6);
				int x=rs.getInt(7);
				calendar.set(nam,thang, ngay);
				tcb = new TCB(name,id,calendar,sodu);
				if(x>0) {
				Sum+= tcb.PDT()*x;
				}
			}
			Statement statement2 =  (Statement) con.createStatement();
			ResultSet rs2 = statement2.executeQuery(sl1+cl1.getYear()+"/"+cl1.getMonth()+"/"+cl1.getDayOfMonth()+sl22);
			while(rs2.next()) {
				TNC tnc ;
				Calendar calendar = Calendar.getInstance();
				String name = rs2.getString(1);
				int id =rs2.getInt(2);
				int nam=rs2.getInt(3);
				int thang=rs2.getInt(4);
				int ngay=rs2.getInt(5);
				int sodu=rs2.getInt(6);
				int x=rs2.getInt(7);
				calendar.set(nam,thang, ngay);
				tnc = new TNC(name,id,calendar,sodu);
				if(x>0) {
					Sum+= tnc.PDT()*x;
					}
			}
			}else {
				System.out.println("Khong ket noi duoc sql");
			}
			}catch (Exception ex) {
			    ex.printStackTrace();
			}
		KetQua.setText(Integer.toString(Sum));
}
	public void back(ActionEvent e) {
		Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/MainWindow/Sample1.fxml"));
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

//(select CONVERT(datetime,