import java.util.ArrayList;

public interface StudentInterface {
	
	public void setFirstName(String firstName);
	public String getFirstName();
	
	public void setLastName(String lastName);
	public String getLastName();
	
	public void setUsername(String username);
	public String getUsername();
	
	public void addCourse(Course c);
	public void removeCourse(Course c);
	
	public ArrayList<Course> enrolledCourses();
	
	public String toString();

}
