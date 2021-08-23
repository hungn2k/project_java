package MainWindow;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
public class Controller1 {
	
    public void change(ActionEvent e) throws IOException{
    	Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Card/Sample3.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	//Controller2 controller = loader.getController();
    	stage.setScene(scene);
    }
    public void search(ActionEvent e) throws IOException{
    	Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Service/Sample9.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	//Controller2 controller = loader.getController();
    	stage.setScene(scene);
    }
    public void tinh(ActionEvent e) throws IOException{
    	Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Service/Sample11.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	//Controller2 controller = loader.getController();
    	stage.setScene(scene);
    }
    public void laiSuat(ActionEvent e) throws IOException{
    	Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Service/Sample12.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	//Controller2 controller = loader.getController();
    	stage.setScene(scene);
    }
    public void back(ActionEvent e) throws IOException{
    	Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Login/ManHinh.fxml"));
    	Parent view = loader.load();
    	Scene scene = new Scene(view);
    	//Controller2 controller = loader.getController();
    	stage.setScene(scene);
    }
} 