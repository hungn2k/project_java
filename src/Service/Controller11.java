package Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;

import Card.The;
import cardCoBan.TCB;
import cardNangCao.TNC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller11 {
	private  String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TheNganHang;user=sa;password=123456";
	private String sl1 = "select TNH.name,TNH.ID,year(tnh.thoigianlamthe) as nam,month(tnh.thoigianlamthe) as thang,day(tnh.thoigianlamthe) as ngay,tnh.SoDu "
			+ " from TNH where TNH.loaithe like '%The Co Ban%';";
	private String sl2 = "select TNH.name,TNH.ID,year(tnh.thoigianlamthe) as nam,month(tnh.thoigianlamthe) as thang,day(tnh.thoigianlamthe) as ngay,tnh.SoDu "
			+ " from TNH where TNH.loaithe like '%The Nang Cao%';";
	@FXML
	private TableView<The> table;
	@FXML
	TableColumn<The, Integer> ID;
	@FXML
	TableColumn<The, String> Name;
	@FXML
	TableColumn<The, Calendar> time;
	@FXML
	TableColumn<The, Integer> SoDu;
	@FXML
	private TextField NoiDung;
	
	private ObservableList<The> ListThe;
	public void search(ActionEvent e) {
		int x= Integer.parseInt(NoiDung.getText());
		ListThe = FXCollections.observableArrayList();
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			if(con!=null) {
			Statement statement1 =  (Statement) con.createStatement();
			ResultSet rs = statement1.executeQuery(sl1);
			while(rs.next()) {
				TCB tcb ;
				Calendar calendar = Calendar.getInstance();
				String name = rs.getString(1);
				int id =rs.getInt(2);
				int nam=rs.getInt(3);
				int thang=rs.getInt(4);
				int ngay=rs.getInt(5);
				int sodu=rs.getInt(6);
				calendar.set(nam,thang, ngay);
				 tcb = new TCB(name,id,calendar,sodu);
				 if(tcb.PDT()>x) {
				ListThe.add((The)tcb);
				}
			}
			Statement statement2 =  (Statement) con.createStatement();
			ResultSet rs2 = statement2.executeQuery(sl2);
			while(rs2.next()) {
				TNC tnc ;
				Calendar calendar = Calendar.getInstance();
				String name = rs2.getString(1);
				int id =rs2.getInt(2);
				int nam=rs2.getInt(3);
				int thang=rs2.getInt(4);
				int ngay=rs2.getInt(5);
				int sodu=rs2.getInt(6);
				calendar.set(nam,thang, ngay);
				 tnc = new TNC(name,id,calendar,sodu);
				 if(tnc.PDT()>x) {
				ListThe.add((The)tnc);
				}
			}
			
				table.setItems(ListThe);
			}else {
				System.out.println("Khong ket noi duoc sql");
			}
			}catch (Exception ex) {
			    ex.printStackTrace();
			}
		final DateFormat dateFormat = DateFormat.getDateInstance() ; // cai dat lai cach in thoi gian
		time.setCellFactory(col -> new TableCell<The, Calendar>() {
		    @Override
		    public void updateItem(Calendar item, boolean empty) {
		        super.updateItem(item, empty);
		        if (item == null) {
		            setText(null);
		        } else {
		            setText(dateFormat.format(item.getTime()));
		        }
		    }
		});
		ID.setCellValueFactory(new PropertyValueFactory<The, Integer>("ID"));
		Name.setCellValueFactory(new PropertyValueFactory<The, String>("name"));
		time.setCellValueFactory(new PropertyValueFactory<The, Calendar>("calendar"));
		SoDu.setCellValueFactory(new PropertyValueFactory<The, Integer>("sodu"));
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