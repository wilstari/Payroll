package application;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginController {

	public LoginController() {

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
System.out.println(PasswordHashing("123"));
            m.changeScene("landingPage.fxml");
        }

        else if(userText.getText().isEmpty() && passText.getText().isEmpty()) {
        	errorlbl.setText("Please enter your login details.");
        }


        else {
        	errorlbl.setText("Wrong username or password!");
        }
        
        
    }
    
   public static String PasswordHashing(String password){
	   try {
		MessageDigest messageDigest= MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes());
		byte[] resultByteArray= messageDigest.digest();
		StringBuilder sb= new StringBuilder();
		for (byte b: resultByteArray) {
			sb.append(String.format("%02x",b));
		}
		return sb.toString();
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	   return"";
	   
	   
    	
	   
    }
	
}
