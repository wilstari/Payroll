package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

	

public class EmployeePageController implements Initializable {

	
	@FXML
    private DatePicker  EndDate;

    @FXML
    private DatePicker HireDate;

    @FXML
    private DatePicker StartDate;
    
    @FXML
    private Button addbtn;
    
    @FXML
    private TableColumn<Employees, String> colEnd;
    
    @FXML
    private TableColumn<Employees, String> colHire;
    
    @FXML
    private TableColumn<Employees, String> colStart;
	
	@FXML
	private TableView<Employees> tvEmployees;
	
    @FXML
    private TableColumn<Employees, String> colFirstName;

    @FXML
    private TableColumn<Employees, String> colLastName;

    @FXML
    private TableColumn<Employees, Integer> colID;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfLastName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	showEmployees();
	}
    
    public void AddNewEmployee(ActionEvent event) throws IOException {
    	insertEmployee();

    }


public Connection getConnection() {
	Connection conn;
	String user ="root";
	String pwd="Wilsonman$123";
	
	try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll",user,pwd);
		return conn;
	}catch(Exception ex) {
		
		
		return null;
	}
	
}

public ObservableList<Employees> getEmployeesList(){
	ObservableList<Employees> employeesList = FXCollections.observableArrayList();
	Connection conn = getConnection();
	String query = "SELECT * FROM employees";
	Statement st;
	ResultSet rs;
	
	try {
	st=conn.createStatement();
	rs=st.executeQuery(query);
	Employees employees;
	
	while(rs.next()){
		employees = new Employees (rs.getInt("ID"),rs.getString("FirstName"),rs.getString("LastName"),
				rs.getString("HireDate"), rs.getString("ContractStart"), rs.getString("ContractEnd"));
		employeesList.add(employees);
	}
	
	
	}catch(Exception ex) {
		return null;
	}
	return employeesList;
} 


public void showEmployees() {
	ObservableList<Employees> list = getEmployeesList();
	colID.setCellValueFactory(new PropertyValueFactory<Employees,Integer>("id"));
	colFirstName.setCellValueFactory(new PropertyValueFactory<Employees,String>("FirstName"));
	colLastName.setCellValueFactory(new PropertyValueFactory<Employees,String>("LastName"));
	colHire.setCellValueFactory(new PropertyValueFactory<Employees,String>("HireDate"));
	colStart.setCellValueFactory(new PropertyValueFactory<Employees,String>("ContractStart"));
	colStart.setCellValueFactory(new PropertyValueFactory<Employees,String>("ContractEnd"));
	tvEmployees.setItems(list);
}

private void insertEmployee() {
	String query= "INSERT INTO employees VALUES("+tfID.getText()+",'"+tfFirstName.getText()+"','"+tfLastName.getText()+
			"','"+EndDate.getPromptText()+"','" +HireDate.getPromptText()+"','" +HireDate.getPromptText()+"')";
	ExecuteInsert(query);
	showEmployees();		
}

private void ExecuteInsert(String query) {
	Connection conn= getConnection();
	Statement st;
	
	try {
		st=conn.createStatement();
		st.executeUpdate(query);
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	
	
	
	
}
}