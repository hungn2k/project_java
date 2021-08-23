package Login;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DangNhap {
	private  String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TaiKhoan;user=sa;password=123456";
	private String sl1="select TK.username,TK.pw from TK where TK.username = '";
	private String sl2 ="' and TK.pw = '";
	private String sl3 ="';";
	@FXML
	private TextField name;
	@FXML
	private TextField password;
	@FXML
	private Button cancelButton;

	public void cancelButton(ActionEvent e) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	@FXML
	Button btn =new Button();
	public void login(ActionEvent e) {
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			if(con!=null) {
			String tf1=name.getText();
			String tf2=password.getText();
			Statement statement =  (Statement) con.createStatement();
			ResultSet rs = statement.executeQuery(sl1+tf1+sl2+tf2+sl3);
			if(rs.next()) {
				Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
		    	FXMLLoader loader = new FXMLLoader();
		    	loader.setLocation(getClass().getResource("/MainWindow/Sample1.fxml"));
		    	Parent view = loader.load();
		    	Scene scene = new Scene(view);
		    	stage.setScene(scene);
				}
			else {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setHeaderText("username or password is null");
				alert.show();
			}
			}
		}catch (Exception ex) {
		    ex.printStackTrace();
	}
	}
}
	