package Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;

import cardCoBan.TCB;
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

public class Controller9 {
	private  String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TheNganHang;user=sa;password=123456";
	private String sl1 = "select distinct TNH.name,TNH.ID,year(TNH.thoigianlamthe) as nam,month(TNH.thoigianlamthe)as thang,day(TNH.thoigianlamthe) as ngay,TNH.sodu,TNH.loaithe	from TNH,(select CONVERT(varchar,thoigianlamthe,103)as t,ID from TNH)b";
	private String sl2 =" where (TNH.ID=b.ID and b.t like '%";
	private String sl3 ="%' ) or name like '%";
	private String sl4 ="%';";
	private String sql = "select TNH.name,TNH.ID,year(tnh.thoigianlamthe) as nam,month(tnh.thoigianlamthe) as thang,day(tnh.thoigianlamthe) as ngay,tnh.SoDu "
			+ " from TNH;";
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
	TableColumn<TCB, Integer> SoDu;
	@FXML
	private TextField NoiDung;
	
	private ObservableList<TCB> ListThe;
	public void search(ActionEvent e) {
		String nd = NoiDung.getText();
		sql = sl1+sl2+nd+sl3+nd+sl4;
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
				calendar.set(nam,thang, ngay);
				 tcb = new TCB(name,id,calendar,sodu);
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
	public void Edit(ActionEvent e) throws IOException {
		String s="select loaithe from TNH where ID=";
		Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("Sample10.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	Controller10 controller = loader.getController();
    	TCB selected = table.getSelectionModel().getSelectedItem();
    	int ID1 = selected.ID;
    	String loai =new String();
    	try {
			Connection con = DriverManager.getConnection(DB_URL);
			Statement statement = (Statement) con.createStatement();
			ResultSet rs = statement.executeQuery(s+ID1+";");
			rs.next();
			loai = rs.getString(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	controller.setTCB(selected,loai);
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