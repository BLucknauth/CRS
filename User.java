abstract class User {
	// student and admin will inherit this class
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	abstract String getUsername();
	public void setUsername(String username) {
		this.username = username;
	}
	
	abstract String getPassword();
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
