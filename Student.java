import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable, StudentInterface{
	// This class stores each student as an object
	// It contains first name, last name, username, and password
	// Most importantly it contains an array list of all the courses a student
	// is enrolled in.
	ArrayList<Course> enrolledCourses = new ArrayList<Course>();
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	public Student(String firstName, String lastName,
			String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password; 
	}
	public Student() {
		this(null, null, null, null);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	public void addCourse(Course c) {
		enrolledCourses.add(c);
	}
	public void removeCourse(Course c) { 
		enrolledCourses.remove(c);
	}
	
	public ArrayList<Course> enrolledCourses() {
		return enrolledCourses;
	}
	
	public String toString() {
		String string = "First name: " + firstName;
		string = string + "\n Last name: " + lastName;
		string = string + "\n Username: " + username;
		string = string + "\n Password: " + password;
		return string;
	}
}
