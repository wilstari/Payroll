package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SettingsPageController  {
	
	 @FXML
	    private BorderPane mainPane;
	 

	
	public void usersPage(ActionEvent event) throws IOException {
	GridPane view= FXMLLoader.load(getClass().getResource("usersPage.fxml"));	
	mainPane.setCenter(view);
	}
}


