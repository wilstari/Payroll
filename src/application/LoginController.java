package application;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;


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
    byte[]salt=  createSalt();

    public Connection getConnection() {
    	Connection conn;
    	String user ="root";
    	String pwd="Password123";
    	
    	try {
    		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll",user,pwd);
    		return conn;
    	}catch(Exception ex) {
    		
    		
    		return null;
    	}
    	
    }

    public void userLogin(ActionEvent event) throws IOException {
    	checkLogin();

    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        
       
        if(userText.getText().toString().equals("test") && passText.getText().toString().equals("123")) {
        	errorlbl.setText("Success!");
System.out.println(PasswordHashing("321", salt));
System.out.println(Arrays.toString(salt));
getpass();
            m.changeScene("landingPage.fxml");
        }

        else if(userText.getText().isEmpty() && passText.getText().isEmpty()) {
        	errorlbl.setText("Please enter your login details.");
        }


        else {
        	errorlbl.setText("Wrong username or password!");
        }
        
        
    }
    
   public static String PasswordHashing(String password, byte[]salt){
	   try {
		MessageDigest messageDigest= MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes());
		messageDigest.reset();
		messageDigest.update(salt);
		
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
   
   public static byte[] createSalt(){
	  byte[] bytes= new byte[20];
	  SecureRandom random = new SecureRandom();
	  random.nextBytes(bytes);
	  return bytes;
   }
   
   public String getpass(){
		
		Connection conn = getConnection();
		String query = "SELECT hash FROM userlogin WHERE id=1";
		Statement st;
		ResultSet rs;
		String users=null;
		
		
		try {
		st=conn.createStatement();
		rs=st.executeQuery(query);
		if(rs.next()) {
			users=rs.getString(1);
		}
	
		
			
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		System.out.println(users);		
		return "";
	} 
   
	
}
