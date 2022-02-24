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
import java.sql.SQLException;
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
    private String userNames=null;
    private String userHashs=null; 
    private String userSalts=null; 

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
    	//System.out.println("test:"+PasswordHashing("456",createSalt()));
    	
    	checkLogin();
    	
    	
    	 
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        errorlbl.setText("");
        if(userText.getText().isBlank() || passText.getText().isBlank()) {
        	errorlbl.setText("Please enter your login details.");
        }


        else {
        	errorlbl.setText("Wrong username or password!");
        }
      
     if(!userText.getText().isBlank() && !passText.getText().isBlank()) {
    	 getUser();
        if(userText.getText().toString().equals(userNames) && (PasswordHashing(passText.getText().toString(),userSalts.getBytes())).equals(userHashs)) {
            m.changeScene("landingPage.fxml");
        }
     }
        
    
        
     }

        
        
        
    
    
   public static String PasswordHashing(String password, byte[]salt){
	   try {
		MessageDigest messageDigest= MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes());
		
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
   
   public String getUser(){
		
	   Connection conn = getConnection();
		String userHash = "SELECT hash FROM userlogin WHERE username='"+userText.getText()+"'";
		String userSalt = "SELECT salt FROM userlogin WHERE username='"+userText.getText()+"'";
		String userName = "SELECT username FROM userlogin WHERE username='"+userText.getText()+"'";
		Statement st;
		ResultSet rs;
		 
		
		
		try {
		st=conn.createStatement();
		rs=st.executeQuery(userHash);
		if(rs.next()) {
			userHashs=rs.getString(1);
		}
	
		
			
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		try {
			rs=st.executeQuery(userSalt);
			if(rs.next()) {
				userSalts=rs.getString(1);
			}
		
			
				
			}catch(Exception ex) {
				System.out.println(ex);
				return null;
			}
		try {
			rs=st.executeQuery(userName);
			if(rs.next()) {
				userNames=rs.getString(1);
			}
		
			
				
			}catch(Exception ex) {
				System.out.println(ex);
				return null;
			}
		
		System.out.println(userNames);
		System.out.println(passText.getText().toString());
		System.out.println(userSalts);	
		System.out.println("entered:"+PasswordHashing(passText.getText().toString(),userSalts.getBytes()));
		System.out.println("db:"+userHashs);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	} 
   
	
}
