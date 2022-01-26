package application;

public class Employees {
	 int id;
	 String FirstName;
	 String LastName;
	 String HireDate;
	 String ContractStart;
	 String ContractEnd;
	 
	 
	public Employees(int id, String firstName, String lastName, String hireDate, String contractStart,
			String contractEnd) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		HireDate = hireDate;
		ContractStart = contractStart;
		ContractEnd = contractEnd;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public String getHireDate() {
		return HireDate;
	}


	public void setHireDate(String hireDate) {
		HireDate = hireDate;
	}


	public String getContractStart() {
		return ContractStart;
	}


	public void setContractStart(String contractStart) {
		ContractStart = contractStart;
	}


	public String getContractEnd() {
		return ContractEnd;
	}


	public void setContractEnd(String contractEnd) {
		ContractEnd = contractEnd;
	}
	
	
	
}