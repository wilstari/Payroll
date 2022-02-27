package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersPageController implements Initializable {

	@FXML
    private ToggleGroup activity;

    @FXML
    private Button addbtn;

    @FXML
    private TableColumn<Users, String> colPerms;

    @FXML
    private TableColumn<Users, String> colStatus;

    @FXML
    private TableColumn<Users, String> colUsername;

    @FXML
    private VBox errorlbl;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TableView<Users> tvUsers;
    
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showUsers();
		
	}
	

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
	
	public ObservableList<Users> getEmployeesList(){
		ObservableList<Users> usersList = FXCollections.observableArrayList();
		Connection conn = getConnection();
		String query = "SELECT * FROM userlogin";
		Statement st;
		ResultSet rs;
		
		
		try {
		st=conn.createStatement();
		rs=st.executeQuery(query);
		Users users;
		
		while(rs.next()){
			users = new Users (rs.getString("username"),rs.getString("hash"),rs.getString("salt"),rs.getString("role"),rs.getString("activitystatus"));
			usersList.add(users);
		}
		
		
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		return usersList;
	} 

	
	
	public void showUsers() {
		
		ObservableList<Users> list = getEmployeesList();
		
		colUsername.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
		colPerms.setCellValueFactory(new PropertyValueFactory<Users,String>("role"));
		colStatus.setCellValueFactory(new PropertyValueFactory<Users,String>("activitystatus"));
		
		tvUsers.setItems(list);
	}
	
}
	
