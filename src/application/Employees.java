package application;

import java.sql.Date;

public class Employees {
	 int id;
	 String FirstName;
	 String LastName;
	 Date HireDate;
	 Date ContractStart;
	 Date ContractEnd;
	 
	 
	public Employees(int id, String firstName, String lastName, Date hireDate, Date contractStart,
			Date contractEnd) {
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


	public Date getHireDate() {
		return HireDate;
	}


	public void setHireDate(Date hireDate) {
		HireDate = hireDate;
	}


	public Date getContractStart() {
		return ContractStart;
	}


	public void setContractStart(Date contractStart) {
		ContractStart = contractStart;
	}


	public Date getContractEnd() {
		return ContractEnd;
	}


	public void setContractEnd(Date contractEnd) {
		ContractEnd = contractEnd;
	}
	
	
	
}