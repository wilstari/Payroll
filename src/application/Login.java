package application;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;


public class Login {

	public Login() {

    }

    @FXML
    private Button loginbtn;
    @FXML
    private Label errorlbl;
    @FXML
    private TextField userText;
    @FXML
    private PasswordField passText;



    public void userLogin(ActionEvent event) throws IOException {
    	checkLogin();

    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        if(userText.getText().toString().equals("test") && passText.getText().toString().equals("123")) {
        	errorlbl.setText("Success!");

            m.changeScene("landingPage.fxml");
        }

        else if(userText.getText().isEmpty() && passText.getText().isEmpty()) {
        	errorlbl.setText("Please enter your data.");
        }


        else {
        	errorlbl.setText("Wrong username or password!");
        }
    }
	
}
