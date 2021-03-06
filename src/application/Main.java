package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage stg; 
	
	@Override
	public void start(Stage primaryStage) throws Exception
		{
			stg = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
			primaryStage.setTitle("Login");
			primaryStage.setScene(new Scene(root,600,400));
			primaryStage.show();		
			primaryStage.setMaximized(true);}
	
	 public void changeScene(String fxml,String title) throws IOException {
	        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
	        stg.getScene().setRoot(pane);
	        stg.setTitle(title);
	    }
	 
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
