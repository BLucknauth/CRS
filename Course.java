import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
	// This class allows each course to be an object.
	// This way each course can store its own name, ID, maximum students
	// enrolled students, instructor, section, and location.
	// There is also a boolean that checks whether it is full or not.
	// That boolean also doesn't allow new students to be registered 
	// to an already full class.
	
	private String courseName;
	private String courseID;
	private int maxStu;
	private int currStu;
	private ArrayList<Student> stuList = new ArrayList<Student>(); 
	private String instructor;
	private int section;
	private String location;
	private boolean isFull = false;
	
	
	public Course(String courseName, String courseID, int maxStu, int currStu, 
					String instructor, int section, String location) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.maxStu = maxStu;
		this.currStu = currStu;
		this.instructor = instructor;
		this.section = section;
		this.location = location; 
	}
	
	public Course() {
		this(null, null, -1, -1, null, -1, null);
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	public int getMaxStu() {
		return maxStu;
	}
	public void setMaxStu(int maxStu) {
		this.maxStu = maxStu;
	}
	
	public int getCurrStu() {
		return currStu;
	}
	public void setCurrStu(int currStu) {
		this.currStu = currStu;
	}
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean getIsFull() {
		if (currStu < maxStu) {
			isFull = false;
		} else {
			isFull = true;
		}
		return isFull;
	}
	public void setIsFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	public void stuListAdd(Student student) {
		if (isFull) {
			System.out.println("Cannot register to a full class.");
		} else {
			currStu += 1; 
			stuList.add(student);
		}
	}
	public void stuListRemove(Student student) {
		currStu -= 1; 
		stuList.remove(student); 
	}
	
	public ArrayList<Student> stuList() {
		return stuList; 
	}
	
	public String toString() {
		String string = "Course Name: " + courseName;
		string = string + "\n Course ID: " + courseID;
		string = string + "\n Max Students: " + maxStu;
		string = string + "\n Current Students: " + currStu;
		string = string + "\n Course Instructor: " + instructor;
		string = string + "\n Course Section: " + section;
		string = string + "\n Course Location: " + location;
		
		return string;
	}

}
