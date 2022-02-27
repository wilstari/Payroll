package application;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LandingPageController implements Initializable{

    @FXML
    private Button employeesbtn;
    
    @FXML
    private Button settingsbtn;
    
    private static String userRole;
    Main m = new Main();
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	settingsbtn.setDisable(true);
		settingsbtn.setVisible(false);
    	userRole=application.LoginController.getRole();
    	roleVisibilty();
    	
    	
    }
    
    public void roleVisibilty() {
    	if(userRole.equals("admin")) {
    		settingsbtn.setDisable(false);
    		settingsbtn.setVisible(true);
    	}
    }

 public void changeSceneEmployees(ActionEvent event) throws IOException {
	 m.changeScene("employeePage.fxml","Employees");
 }
 
 public void changeSceneSettings(ActionEvent event) throws IOException {
	 
	 m.changeScene("settingsPage.fxml","Settings");
}


     

    
}
