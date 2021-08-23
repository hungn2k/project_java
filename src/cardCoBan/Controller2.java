package cardCoBan;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller2 implements Initializable {
	private  String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TheNganHang;user=sa;password=123456";
	private String sql = "select TNH.name,TNH.ID,year(tnh.thoigianlamthe) as nam,month(tnh.thoigianlamthe) as thang,day(tnh.thoigianlamthe) as ngay,tnh.SoDu "
			+ " from TNH where TNH.loaithe like '%The Co Ban%';";
		private String del = "delete from TNH where TNH.ID= ";
	@FXML
	private TableView<TCB> table;
	@FXML
	TableColumn<TCB, Integer> ID;
	@FXML
	TableColumn<TCB, String> Name;
	@FXML
	TableColumn<TCB, Calendar> time;
	@FXML
	TableColumn<TCB, Integer> SoDu ;
	private ObservableList<TCB> ListThe;
	@Override
	public void initialize(URL location,ResourceBundle resources) {
	//	Calendar calendar =Calendar.getInstance();
		ListThe = FXCollections.observableArrayList(
				);
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			if(con!=null) {
			Statement statement =  (Statement) con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				TCB tcb ;
				Calendar calendar = Calendar.getInstance();
				String name = rs.getString(1);
				int id =rs.getInt(2);
				int nam=rs.getInt(3);
				int thang=rs.getInt(4);
				int ngay=rs.getInt(5);
				int sodu=rs.getInt(6);
			//	System.out.println(nam+" "+ thang+" "+ngay);
				calendar.set(nam,thang, ngay);
				 tcb = new TCB(name,id,calendar,sodu);
			//	 System.out.println(tcb.time.get(tcb.time.YEAR));
				ListThe.add(tcb);
				table.setItems(ListThe);
			}
			}else {
				System.out.println("Khong ket noi duoc sql");
			}
			}catch (Exception ex) {
			    ex.printStackTrace();
			}
		final DateFormat dateFormat = DateFormat.getDateInstance() ; // cai dat lai cach in thoi gian
		time.setCellFactory(col -> new TableCell<TCB, Calendar>() {
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
		ID.setCellValueFactory(new PropertyValueFactory<TCB, Integer>("ID"));
		Name.setCellValueFactory(new PropertyValueFactory<TCB, String>("name"));
		time.setCellValueFactory(new PropertyValueFactory<TCB, Calendar>("calendar"));
		SoDu.setCellValueFactory(new PropertyValueFactory<TCB, Integer>("sodu"));
	}
	public void Add(ActionEvent e) throws IOException {
		Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/cardCoBan/Sample4.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	stage.setScene(scene);
	}
	public void delete(ActionEvent e) {
		TCB selected = table.getSelectionModel().getSelectedItem();
		int x=selected.ID;
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			if(con!=null) {
				Statement statement =  (Statement) con.createStatement();
				statement.execute(del+x+";");
			}
		}catch (Exception ex) {
		    ex.printStackTrace();
		}
		//ListThe.remove(selected);
		//table.setItems(ListThe);
	}
	public void Edit(ActionEvent e) throws IOException {
		Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/cardCoBan/Sample5.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	Controller5 controller = loader.getController();
    	TCB selected = table.getSelectionModel().getSelectedItem();
    	controller.setTCB(selected);
    	stage.setScene(scene);
	}
	public void back(ActionEvent e) throws IOException{
    	Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Card/Sample3.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	//Controller2 controller = loader.getController();
    	stage.setScene(scene);
    }
} 