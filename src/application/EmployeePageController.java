package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

	

public class EmployeePageController implements Initializable {

	

    @FXML
    private Button addbtn;

    @FXML
    private TableColumn<Employees,Date> colEnd;

    @FXML
    private TableColumn<Employees,String> colFirstName;

    @FXML
    private TableColumn<Employees,Date> colHire;

    @FXML
    private TableColumn<Employees,Integer> colID;

    @FXML
    private TableColumn<Employees,String> colLastName;

    @FXML
    private TableColumn<Employees,Date> colStart;

    @FXML
    private DatePicker dpConEnd;

    @FXML
    private DatePicker dpConStart;

    @FXML
    private DatePicker dpHireDate;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfLastName;

    @FXML
    private TableView<Employees> tvEmployees;

    
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
	String pwd="Password123";
	
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
				rs.getDate("HireDate"), rs.getDate("ContractStart"), rs.getDate("ContractEnd"));
		employeesList.add(employees);
	}
	
	
	}catch(Exception ex) {
		System.out.println(ex);
		return null;
	}
	return employeesList;
} 


public void showEmployees() {
	
	ObservableList<Employees> list = getEmployeesList();
	
	colID.setCellValueFactory(new PropertyValueFactory<Employees,Integer>("id"));
	colFirstName.setCellValueFactory(new PropertyValueFactory<Employees,String>("FirstName"));
	colLastName.setCellValueFactory(new PropertyValueFactory<Employees,String>("LastName"));
	colHire.setCellValueFactory(new PropertyValueFactory<Employees,Date>("HireDate"));
	colStart.setCellValueFactory(new PropertyValueFactory<Employees,Date>("ContractStart"));
	colEnd.setCellValueFactory(new PropertyValueFactory<Employees,Date>("ContractEnd"));
	
	tvEmployees.setItems(list);
}





private void insertEmployee() {
	String query= "INSERT INTO employees("
			
			+ "`firstName`,"
			+ "`lastName`,"
			+ "`hireDate`,"
			+ "`contractStart`,"
			+ "`contractEnd`)"
			+ " VALUES('"+tfFirstName.getText()+"','"+tfLastName.getText()+
			"','"+dpHireDate.getValue()+"','" +dpConStart.getValue()+"','" +dpConEnd.getValue()+"')";
	executeInsert(query);
	showEmployees();
	clearFields();
	showAlert("New Employee Added","Added");
}

private void executeInsert(String query) {
	Connection conn= getConnection();
	Statement st;
	
	try {
		st=conn.createStatement();
		st.executeUpdate(query);
	}catch(Exception ex) {
		ex.printStackTrace();
	}
  }

private void clearFields() {
	tfFirstName.clear();
	tfID.clear();
	tfLastName.clear();
	dpConEnd.getEditor().clear();
	dpHireDate.getEditor().clear();
	dpConStart.getEditor().clear();
}

private void showAlert(String msg,String title) {
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle(title);
	alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
}

}