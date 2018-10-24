package task4;

import java.util.LinkedList;

/**
 * An exam object. The class contains students within a linked-list.
 * 
 * @author Song
 */
public class Exam implements logging.LogMethods{
    
    protected int courseCode;
    protected int weight;
    protected LinkedList<Student> students;
    
    /**
     * Creates an exam object.
     * @param courseCode A unique ID number for the course.
     * @param weight An integer from 0 to 100.
     */
    public Exam(int courseCode, int weight) {
        
    	this.courseCode = courseCode;
    	this.weight = weight;
    	this.students = new LinkedList<Student>();
    }
    
    public void assign(Student s){
    	students.add(s);
    }
    
    public String toString(){
    	return "Exam for course code " + this.courseCode + " with weight " + weight 
    			+ " and " + students.size() + " many students in it";
    }
    
    public int getCourseCode(){
    	return courseCode;
    }
    
    public String getStudents(){
    	return students.toString();
    }
}
