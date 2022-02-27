package application;

public class Users {
String username;
String hash;
String salt;
String role;
String status;



public Users(String username, String hash, String salt, String role, String status) {
	super();
	this.username = username;
	this.hash = hash;
	this.salt = salt;
	this.role = role;
	this.status = status;
}


public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getHash() {
	return hash;
}
public void setHash(String hash) {
	this.hash = hash;
}
public String getSalt() {
	return salt;
}
public void setSalt(String salt) {
	this.salt = salt;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
