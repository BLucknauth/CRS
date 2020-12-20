public class Admin extends User implements AdminInterface{
	// This class is simply the username and password of Admin
	// in order to check the Admin username and password. 
	
	// Both username and password are static 
	// because they are the same for all admins.
	private static String username = "Admin";
	private static String password = "Admin001";

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password; 
	}
	
}
