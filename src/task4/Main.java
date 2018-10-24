package task4;

import java.util.LinkedList;
import java.util.Scanner;

/**<p>
 * The main class of the student-exam association application contains the main
 * method to run the application. 
 * </p> <p>
 * The application is very simple. It displays a text at the prompt from the start
 * and asks user for input through standard in. Possible commands to execute by input are the
 * described below. The program keeps asking for input unless "exit" is called by the user.
 * </p> <ol>
 * 	<li> Create a student. The student must have a one-word name. No identical names are
 * allowed. 
 * 
 * <ul><li>Command is: student &lsaquo;name&rsaquo; </li></ul></li>
 * 
 * 	<li> Create an exam. The exam is identified by its course code and takes an additional 
 * parameter called weight. Both the course code and weight are integers, and the course 
 * code must be unique.  
 *
 * <ul><li>Command is: exam &lsaquo;course code&rsaquo; &lsaquo;weight&rsaquo; </li></ul></li>
 * 
 * 	<li> Assign a student to an exam. The student and the exam to be associated must already 
 * exist in the running program. To check this, use the "who" command described below. 
 * 	
 * <ul><li>Command is: assign &lsaquo;student name&rsaquo; &lsaquo;exam course code&rsaquo; </li></ul></li>
 * 
 * 	<li> View the registered students. All created students in the running session are displayed
 * to the screen. 
 * 	
 * <ul><li>Command is: who </li></ul></li>
 * 
 * 	<li> View the assigned students to a particular exam. All students assigned to the exam are
 * displayed to the screen. 
 * 	
 * <ul><li>Command is: who &lsaquo;exam course code&rsaquo; </li></ul></li>
 * 
 * 	<li> Exit the program. All current data is lost. 
 * 	
 * <ul><li>Command is: exit </li></ul></li>
 *</ol>
 *<p>
 * What is not featured in this program. There are many features not implemented in the 
 * application, such as a command to display all exams, an alphabetic sort of student names,
 * uniqueness checking among names and courses, and of course saving a program session's data. 
 * Furthermore, student and exam uniqueness is solely the responsibility of the user.
 *</p>
 *
 * @author Song
 * @version 0.9 Before confirming.
 * @since October 9, 2018
 */
public class Main {
    
	/**
	 * A linked list containing all the students entered by the user.
	 */
	private static LinkedList<Student> students = new LinkedList<Student>();
	
	/**
	 * A linked list containing all the exams entered by the user
	 */
	private static LinkedList<Exam> exams = new LinkedList<Exam>();
	
	/**
	 * A command-line prompt interactive program that asks the user for inputs
	 * 
	 * @param args Not used.
	 */
	public static void main(String[] args){
		
		// user input
		String input;
		Scanner scanner = new Scanner(System.in);
		boolean keepAsking = true;
		
		String welcome = "Welcome to the final exam planning program. The program helps the user\n "
				+ "with exam logistics such as who is registered in the exam and what course is\n "
				+ "the exam for.\n";
		
		String directive = "\nType the following with the right arguments, "
				+ "one word student names and number course code only:\n"
				+ "To create a student: student <student name> \n"
				+ "To create an exam: exam <exam course code> <exam weight>\n"
				+ "To assign a student to an exam: assign <student name> <exam course code>\n"
				+ "To see who are the students: who \n"
				+ "To see who are assigned to an exam: who <exam course code>\n"
				+ "To exit: exit\n"
				+ "For the complete documentation, consult the Javadoc.";
		
		System.out.println(welcome);
		System.out.println(directive);
		
		while(keepAsking){	
			
			// get user input
			input = scanner.nextLine();
			
			// parse
			String[] tokens = input.split(" ");
			
			// depending on the first word received, do the following
			switch(tokens[0].toLowerCase()){
			
			case "student":
				createStudent(tokens);
				continue;
				
			case "exam":
				createExam(tokens);
				continue;
				
			case "assign":
				assignStudent(tokens);
				continue;
			
			case "who":
				who(tokens);
				continue;
			
			case "exit":
				System.out.println("Program exits.");
				scanner.close();
				System.exit(1);
			
			default:
				System.out.println("Invalid input");
				break;
			}
		}

	}
	
	/**
	 * Create a Student object
	 * 
	 * "To create a student: student <student name> \n"
	 * 
	 * @param tokens User input tokens, guaranteed to be length >= 1
	 */
	private static void createStudent(String[] tokens) {
		
		String inputName;
		Student newStudent;
		
		// too many arguments
		if (tokens.length > 2) System.out.println("Too many inputs. \n" 
				+ "\"To create a student: student <student name>\" \n");
		
		// just enough, create student and attach to the linked-list
		else{
			
			// get token
			inputName = tokens[1];
			
			// create object and attach
			newStudent = new Student(inputName);
			students.add(newStudent);
		}
		
	}

	/**
	 * Create an Exam object
	 * 
	 * "To create an exam: exam <exam course code> <exam weight>\n"
	 * 
	 * @param tokens User input tokens, guaranteed to be length >= 1
	 */
	private static void createExam(String[] tokens) {
		
		int inputCourseCode, inputWeight;
		Exam newExam;
		
		// Too many arguments
		if (tokens.length > 3) System.out.println("Too many arguments. \n "
				+ "\" To create an exam: exam <exam course code> <exam weight>\" \n");
		
		// Just enough arguments, create object and attach to linked-list
		else{
			
			// parse types
			inputCourseCode = Integer.parseInt(tokens[1]);
			inputWeight = Integer.parseInt(tokens[2]);
			
			// create object and attach
			newExam = new Exam(inputCourseCode, inputWeight);
			exams.add(newExam);
		}
	}

	/**
	 * "To see who are the students: who \n"
	*  "To see who are assigned to an exam: who <exam course code>\n"
	 * @param tokens User input tokens, length >= 1
	 */
	private static void who(String[] tokens) {
		
		int inputCourseCode;
		boolean foundExam = false;
		
		// one argument = who
		if (tokens.length == 1){
			
			if (students.size() == 0) System.out.println("No students.");
			else System.out.println(students.toString());
		}
		// two arguments = who <course code>
		else if (tokens.length == 2){
			
			if (exams.size() == 0) System.out.println("No exams");
			else {
				
				// find exam
				inputCourseCode = Integer.parseInt(tokens[1]);
				
				for(Exam exam:exams){
					
					if (exam.getCourseCode() == inputCourseCode){
						System.out.println(exam.getStudents());
						foundExam = true;
					}
				}
				
				// exam not found
				if(foundExam == false) System.out.println("Exam not found.");
				
			}
		}
		// more than 2 arguments
		else System.out.println("Expect 0 or 1 argument, ie. "
				+ "\"who\" or \"who <course code> \"");
	}

	
	
	/**
	 * "To assign a student to an exam: assign <student name> <exam course code>\n"
	 * @param tokens User input tokens, length >= 1
	 */
	private static void assignStudent(String[] tokens) {
		
		int inputExam;
		String inputStudent;
		Exam foundExam = null;
		Student foundStudent = null;
		
		// too many arguments
		if (tokens.length > 3){
			System.out.println("Too many arguments. \n"
					+ "To assign a student to an exam: assign <student name> <exam course code>");
		}
		
		// two arguments
		else {
			
			// parse into correct type
			inputExam = Integer.parseInt(tokens[2]);
			inputStudent = tokens[1];
			
			// find the exam, store into foundExam
			for(Exam exam: exams){
				if (exam.getCourseCode() == inputExam) foundExam = exam;
			}
			
			// find the student, store into foundStudent
			for(Student s: students){
				if(s.toString().equals(inputStudent)) foundStudent = s;
			}
			
			// not found, print message
			if (foundExam == null) System.out.println("No such exam");
			else if(foundStudent == null) System.out.println("No such student");
			
			// assign student
			else {
				foundExam.assign(foundStudent);
			}
		}
	}
}
