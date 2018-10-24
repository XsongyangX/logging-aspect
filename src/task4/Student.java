package task4;

/**
 * A student object. The only attribute is a string, the name.
 * 
 * @author Song
 */
public class Student implements logging.LogMethods{
    
    protected String name;
    
    public Student(String name) {
        
    	this.name = name;
    }
    
    public String toString(){
    	return this.name;
    }
}
