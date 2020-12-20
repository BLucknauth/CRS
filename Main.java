import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		// This method is where the actual Course Registration System happens.
		// First, it looks for the serialized files to read in the courses
		// and the students
		// if there are no serialized files, it reads the courses into an
		// arrayList from the .csv file.
		// Afterwards, it asks the user if they are an admin or student
		// and prompts them to enter their username and password.
		// Each student's username and password are stored within 
		// each Student object. 
		// Therefore, the program goes through the serialized file of students
		// to determine if the username and password match any in the file.
		// Once the password is matched the method uses user input to 
		// create, delete, edit, etc. courses and students as specified
		// in the requirements. 

		//create ser,
		File f = new File("courseFile.ser");
		File s = new File("studentsFile.ser");

		//create courseList to add all courses to 
		ArrayList<Course> courseList = new ArrayList<Course>();
		ArrayList<Student> studentList = new ArrayList<Student>();

		//if the file exists, read it from serialized file, 
		//if not read from .csv

		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream("courseFile.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Course temp = new Course();

				//this loop will keep going until it throws EOFException
				//at which point deserialization is done 
				//and exception is then caught

				while(temp != null) {
					temp = (Course) ois.readObject();
					courseList.add(temp);
				}

				ois.close();
				fis.close();

			} catch (IOException ex) {
				System.out.println("Deserialization finished");
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				return;
			}

		} else {
			String fileName = "MyUniversityCourses.csv";
			String line = null;


			try {
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				//read first line + do nothing with it
				//since that's just the heading of the csv
				bufferedReader.readLine(); 

				while((line = bufferedReader.readLine()) != null) {

					//System.out.println(line);
					ArrayList<String> CommaDelineated = 
							new ArrayList<>(Arrays.asList(line.split(",")));

					Course c = new Course();

					c.setCourseName(CommaDelineated.get(0));
					c.setCourseID(CommaDelineated.get(1));
					c.setMaxStu(Integer.parseInt(CommaDelineated.get(2)));
					c.setCurrStu(Integer.parseInt(CommaDelineated.get(3)));
					c.setInstructor(CommaDelineated.get(5));
					c.setSection(Integer.parseInt(CommaDelineated.get(6)));
					c.setLocation(CommaDelineated.get(7));

					courseList.add(c);
				}

				bufferedReader.close();

			} catch(FileNotFoundException ex) {
				System.out.println("Unable to open file " + fileName);
				ex.printStackTrace();

			} catch(IOException ex) {
				System.out.println("Error reading file " + fileName);
				ex.printStackTrace();
			}

		}

		if (s.exists()) {
			try {
				FileInputStream fis = new FileInputStream("studentsFile.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Student temp = new Student();

				//this loop will keep going until it throws EOFException
				//at which point deserialization is done 
				//and exception is then caught

				while(temp != null) {
					temp = (Student) ois.readObject();
					studentList.add(temp);
				}

				ois.close();
				fis.close();

			} catch (IOException ex) {
				System.out.println("Deserialization finished");
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				return;
			}
		}

		Scanner in = new Scanner(System.in);

		//ask user if they're an admin/student then check username/pw for both
		boolean isAdmin = false;
		boolean runProgram = true; 

		System.out.println("Are you a(n):");
		System.out.println("1. Admin");
		System.out.println("2. Student");
		int output = in.nextInt();

		//user is either admin or not, if admin change boolean to true
		//use boolean for if statements
		if (output == 1) {
			isAdmin = true;
		}

		//get username/pw
		in.nextLine(); 
		System.out.println("Enter your username: ");
		String username = in.nextLine();
		System.out.println("Enter your password: ");
		String password = in.nextLine();

		//create admin object so can check username + password
		Admin admin = new Admin(); 
		while (runProgram && isAdmin) {
			if (username.equals(admin.getUsername()) && 
					password.equals(admin.getPassword())) {

				System.out.println("1. Course Management");
				System.out.println("2. Reports");
				int option = in.nextInt();

				if (option == 1) {
					System.out.println("1. Create a new course");
					System.out.println("2. Delete a course");
					System.out.println("3. Edit a course");
					System.out.println("4. Display information for a given course");
					System.out.println("5. Register a student");
					System.out.println("6. Exit"); 
					option = in.nextInt();

					if (option == 1) {
						//have to clear next line before getting next input
						in.nextLine();

						System.out.print("\n Enter course name: ");
						String courseName = in.nextLine();

						System.out.print("\n Enter course ID: ");
						String courseID = in.nextLine();

						System.out.print("\n Enter max students: ");
						int maxStudents = in.nextInt();

						//have to clear next line before getting next input
						in.nextLine();

						System.out.print("\n Enter course instructor: ");
						String courseInstructor = in.nextLine();

						System.out.print("\n Enter course section #: ");
						int courseSection = in.nextInt();

						//have to clear next line before getting next input
						in.nextLine();

						System.out.print("\n Enter course location: ");
						String courseLocation = in.nextLine();

						Course tempCourse = new Course(courseName, courseID, maxStudents, 0, 
								courseInstructor, courseSection, courseLocation);

						courseList.add(tempCourse);

						System.out.println("Course added."); 

					} else if (option == 2) {
						//have to clear next line before getting next input
						in.nextLine();

						System.out.print("\n Enter course name: ");
						String courseName = in.nextLine();
						boolean courseNotFound = true;

						//control list with boolean so once course is found loop stops
						for (int i = 0; i<courseList.size(); i++) {

							if (courseNotFound && 
									(courseList.get(i)).getCourseName().equals(courseName)) {
								courseList.remove(i);
								courseNotFound = false; //will stop loop
							}
						}
						System.out.println("Course removed"); 

					} else if (option == 3) {
						in.nextLine();

						System.out.print("\n Enter course name: ");
						String courseName = in.nextLine();

						boolean courseNotFound = true;
						int courseIndex = 0; 

						//find what index in array course is to edit it

						for (int i = 0; i<courseList.size(); i++) {

							if (courseNotFound && 
									(courseList.get(i)).getCourseName().equals(courseName)) {
								courseIndex = i;
								courseNotFound = false; //will stop loop
							}
						}

						System.out.println("1. Edit course name");
						System.out.println("2. Edit course ID");
						System.out.println("3. Edit max students");
						System.out.println("4. Edit instructor");
						System.out.println("5. Edit section #");
						System.out.println("6. Edit location");

						option = in.nextInt();

						if (option == 1) {
							in.nextLine();

							//use index to edit desired course
							System.out.print("\n Enter course name: ");
							String edit = in.nextLine();
							(courseList.get(courseIndex)).setCourseName(edit);
						} else if (option == 2) {
							in.nextLine(); 

							//can use index to edit desired course
							System.out.print("\n Enter course ID: ");
							String edit = in.nextLine();
							(courseList.get(courseIndex)).setCourseID(edit);
						} else if (option == 3) {
							in.nextLine(); 

							//can use index to edit desired course
							System.out.print("\n Enter max students: ");
							int edit = in.nextInt();
							(courseList.get(courseIndex)).setMaxStu(edit);
						} else if (option == 4) {
							in.nextLine(); 

							//can use index to edit desired course
							System.out.print("\n Enter course instructor: ");
							String edit = in.nextLine();
							(courseList.get(courseIndex)).setInstructor(edit);
						} else if (option == 5) {
							in.nextLine(); 

							//can use index to edit desired course
							System.out.print("\n Enter Section #: ");
							int edit = in.nextInt();
							(courseList.get(courseIndex)).setSection(edit);
						} else if (option == 6) {
							in.nextLine(); 

							//can use index to edit desired course
							System.out.print("\n Enter location: ");
							String edit = in.nextLine();
							(courseList.get(courseIndex)).setLocation(edit);
						}
					} else if (option == 4) {
						in.nextLine();
						System.out.println("Enter course ID: ");
						String cID = in.nextLine();

						boolean courseNotFound = true;
						for (int i = 0; i<courseList.size(); i++) {
							if (courseNotFound && 
									(courseList.get(i)).getCourseID().equals(cID)) {
								System.out.println((courseList.get(i)));
								courseNotFound = false; //will stop loop
							}
						}
					} else if (option == 5) {
						//get info to register a new student
						in.nextLine();

						System.out.print("\n Enter Student first name: ");
						String stuFN = in.nextLine();
						System.out.print("\n Enter Student last name: ");
						String stuLN = in.nextLine();
						System.out.print("\n Enter Student username: ");
						String stuUsername = in.nextLine();
						System.out.print("\n Enter Student password: ");
						String stuPW = in.nextLine();

						Student tempStu = new Student(stuFN, stuLN, stuUsername, stuPW);
						studentList.add(tempStu);		
					} else if (option == 6) {
						runProgram = false; 
					} else {
						System.out.println("Enter a valid option.");
					}

				} else if (option == 2) {
					System.out.println("1. View all courses");
					System.out.println("2. View full courses");
					System.out.println("3. Write to a file list of courses that are full");
					System.out.println("4. View names of students registered to a course");
					System.out.println("5. View list of courses a student is registered in");
					System.out.println("6. Sort courses based on # of students registered"); 
					System.out.println("7. Exit"); 
					option = in.nextInt();

					if (option == 1) {
						for (int i = 0; i<courseList.size(); i++) {
							System.out.println();
							System.out.println(courseList.get(i));
						}

					} else if (option == 2) {
						for (int i = 0; i<courseList.size(); i++) {
							if ((courseList.get(i)).getIsFull()) {
								System.out.println(courseList.get(i));
							}

						}

					} else if (option == 3) {
						// if isFull write name of course to a file 
						try {
							FileWriter writer = new FileWriter("fullCourses.txt");
							for (int i = 0; i<courseList.size(); i++) {
								if ((courseList.get(i)).getIsFull()) {
									writer.write(courseList.get(i).getCourseName());
								}
							}
							writer.close();
						} catch (IOException ex) {
							System.out.println("Error writing file.");
							ex.printStackTrace();
						}

					} else if (option == 4) {
						in.nextLine();
						ArrayList<Student> registeredStudents = new ArrayList<Student>();

						System.out.println("Enter name of course: ");
						String courseName = in.nextLine();

						boolean courseNotFound = true;
						int courseIndex = 0;

						//courseIndex points to course in ArrayList of courses
						for (int i = 0; i<courseList.size(); i++) {

							if (courseNotFound && 
									(courseList.get(i)).getCourseName().equals(courseName)) {
								courseIndex = i;
								courseNotFound = false; //will stop loop
							}
						}

						//pass stuList ArrayList within Course into registeredStudents ArrayList 
						//this way we can iterate through registered students to print
						registeredStudents = (courseList.get(courseIndex)).stuList();
						for (int i=0; i<registeredStudents.size(); i++) {
							System.out.println(registeredStudents.get(i));
						}

					} else if (option == 5) {
						in.nextLine();
						//find student using their full name
						//then print student's course list

						System.out.println("Enter student first name: ");
						String studentFN = in.nextLine();

						System.out.println("Enter student last name: ");
						String studentLN = in.nextLine();

						boolean studentNotFound = true;
						int studentIndex = 0;

						for (int i = 0; i<courseList.size(); i++) {	

							if (studentNotFound && 
									(studentList.get(i)).getFirstName().equals(studentFN) && 
									(studentList.get(i)).getLastName().equals(studentLN)) {
								studentIndex = i;
								studentNotFound = false; //will stop loop
							}
						}

						ArrayList<Course> studentCourses = new ArrayList<Course>();
						studentCourses = (studentList.get(studentIndex)).enrolledCourses();

						for (int i = 0; i < studentCourses.size(); i++) {
							System.out.println();
							System.out.println(studentCourses.get(i));
						}

					} else if (option == 6) {
						//bubble sort courses in a new arraylist based on currStu

						ArrayList<Course> tempCourseList = new ArrayList<Course>(); 
						boolean sorted = false;
						Course tempCourse; 
						tempCourseList = courseList; 


						while(!sorted) {
							sorted = true;

							for (int i = 0; i < tempCourseList.size()-1; i++) {

								if ((tempCourseList.get(i)).getCurrStu() > 
								(tempCourseList.get(i+1)).getCurrStu()) {

									tempCourse = tempCourseList.get(i);

									tempCourseList.set(i, tempCourseList.get(i+1));
									tempCourseList.set(i+1, tempCourse);

									sorted = false; 
								}
							}
						}

						for (int i = 0; i<tempCourseList.size(); i++) {
							System.out.println();
							System.out.println(tempCourseList.get(i));
						}

					} else if (option == 7) {
						runProgram = false; 
					} else {
						System.out.println("Enter a valid option.");
					}
				}

			}
		}

		while (runProgram && !isAdmin) {
			boolean passwordMatch = false; 
			int thisStudentIndex = 0;

			//check whether username + pw match
			for (int i = 0; i < studentList.size(); i++) {
				if (username.equals((studentList.get(i)).getUsername()) &&
						password.equals((studentList.get(i)).getPassword())) {
					passwordMatch = true;
					thisStudentIndex = i;
				}
			}

			if (passwordMatch) { 
				//valid username + pw, proceed to course mgmt
				System.out.println("Course Management");
				System.out.println("1. View all courses");
				System.out.println("2. View all courses that are not full");
				System.out.println("3. Register in a course");
				System.out.println("4. Withdraw from a course");
				System.out.println("5. View all courses student is registered in");
				System.out.println("6. Exit");

				int option = in.nextInt();

				if (option == 1) {
					for (int i = 0; i < courseList.size(); i++) {
						System.out.println();
						System.out.println(courseList.get(i));
					}

				} else if (option == 2) {
					for (int i = 0; i<courseList.size(); i++) {
						if (!(courseList.get(i)).getIsFull()) {
							System.out.println(courseList.get(i));
						}
					}

				} else if (option == 3) {
					in.nextLine();

					System.out.print("\n Enter the name of the course you "
							+ "want to enroll in: ");
					String courseName = in.nextLine();
					int courseIndex = 0;

					for (int i=0; i<courseList.size(); i++) {
						if (courseName.equalsIgnoreCase(courseList.get(i).getCourseName())) {
							courseIndex = i;
						}
					}

					//add course to student's enrolled courses
					studentList.get(thisStudentIndex).addCourse(courseList.get(courseIndex));

					//add student to course's enrolled students
					courseList.get(courseIndex).stuListAdd(studentList.get(thisStudentIndex));

					System.out.println("Course added.");
				} else if (option == 4) {	
					in.nextLine();

					System.out.print("\n Enter the name of the course you "
							+ "want to withdraw from: ");
					String courseName = in.nextLine();
					int courseIndex = 0;

					for (int i=0; i<courseList.size(); i++) {
						if (courseName.equalsIgnoreCase(courseList.get(i).getCourseName())) {
							courseIndex = i;
						}
					}

					//remove course from student
					studentList.get(thisStudentIndex).removeCourse(courseList.get(courseIndex));

					//remove student from course
					courseList.get(courseIndex).stuListRemove(studentList.get(thisStudentIndex));

					System.out.println("Course withdrawn.");

				} else if (option == 5) {
					ArrayList<Course> enrolledCourses = new ArrayList<Course>();

					enrolledCourses = studentList.get(thisStudentIndex).enrolledCourses();

					for (int i = 0; i < enrolledCourses.size(); i++) {
						System.out.println(enrolledCourses.get(i));
					}

				} else if (option == 6) {
					runProgram = false;
				} else {
					System.out.println("Enter a valid option.");
				}
			} else {
				System.out.println("Incorrect username/password.");
				runProgram = false;
			}
		}


		in.close();
		//once program is done, deserialize to .ser
		//deserialize the student array as well to keep track of students!

		try {
			FileOutputStream fos = new FileOutputStream("courseFile.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (int i = 0; i < courseList.size(); i++) {
				oos.writeObject(courseList.get(i));
			}

			oos.close();
			fos.close();
			System.out.println("Serialization complete");

		} catch (IOException ex) {
			System.out.println("Error writing file.");
			ex.printStackTrace();
		}

		try {
			FileOutputStream fos = new FileOutputStream("studentsFile.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (int i = 0; i < studentList.size(); i++) {
				oos.writeObject(studentList.get(i));
			}

			oos.close();
			fos.close();
			System.out.println("Serialization complete");

		} catch (IOException ex) {
			System.out.println("Error writing file.");
			ex.printStackTrace();
		}
	}

}
