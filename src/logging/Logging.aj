package logging;

/**<p>
 * The aspect logs all function calls belonging to a class implementing
 * the LogMethods interface. This aspect assumes a well made 
 * toString() method for all custom objects on which the aspect is
 * used. </p> 
 * <p>
 * The aspect does not log static methods. </p>
 * @author Song
 * @version The aspect is fully realized. The only thing left to do
 * is the Logger input class.
 */
aspect Logging {

	// Intercepting all constructor executions in classes implementing
	// the LogMethods interface
	pointcut LogConstructors(LogMethods logClass):
		execution(LogMethods+.new(..)) && target(logClass);
	
	// Intercepting all method calls within classes implementing
	// the LogMethods interface
	pointcut LogMethodCalls(LogMethods logClass):
		call(* LogMethods+.*(..)) && target(logClass);// && within(LoggedClass);
	
	// after each constructor execution, log the instantiation
	after(LogMethods logClass) : LogConstructors(logClass) {
		
		String stringToLog; // end result string to be logged
		Object[] args; // arguments taken by the constructor
		String parameters; // a string of the arguments taken
		String objectType; // the type of the created object
		
		// get the constructor arguments
		args = thisJoinPoint.getArgs();
		parameters = "with parameters:";
		for(int i = 0; i < args.length; i++) {
			
			if (i == args.length - 1) {
				parameters += " " + args[i].getClass().getSimpleName()
						+ " " + args[i] + ".";
				break;
			}
			parameters += " " + args[i].getClass().getSimpleName()
					+ " " + args[i] + ";";
		}
		
		// get the created object type
		objectType = logClass.getClass().getSimpleName();
		
		// form the string to log
		// if object is created without any parameter
		if (args.length == 0) {
			stringToLog = "Created object of type " + objectType 
					+ " with no parameters.";
		}
		// constructor takes some arguments
		else {
			stringToLog = "Created object of type " + objectType 
					+ " " + parameters;
		}

		// Use logger from the receiver
		Logger.log(stringToLog);

	}
	
	// before each non static method call, log the call
	before(LogMethods logClass): LogMethodCalls(logClass){
		
		String stringToLog; // end result to log
		String callerClass; // the name of the class where the method was called
		Object[] args; // method arguments
		String parameters = ""; // string that presents the method arguments
		String objectType; // the type of this (object)
		String signature; // method signature name
		
		
		// get the name of the caller class, with packages
		callerClass = logClass.getClass().getCanonicalName();
		// in the case of an anonymous class
		if (callerClass.equals("null")) callerClass = "anonymous";
		
		// get the method arguments
		args = thisJoinPoint.getArgs();
		
		// form the string that presents the arguments
		// implicitly use the toString method
		for(int i = 0 ; i < args.length; i++) {
			
			if (i == args.length - 1) {
				parameters += " " + args[i].getClass().getSimpleName()
						+ " " + args[i] + ".";
				break;
			}
			parameters += " " + args[i].getClass().getSimpleName()
					+ " " + args[i] + ";";
		} 
		
		// get the target object's type
		objectType = logClass.getClass().getCanonicalName();
		
		// get the method signature name
		signature = thisJoinPoint.getSignature().getName();
		
		
		// group every string into one string
		// if the method takes no argument
		if ( args.length == 0) {
			stringToLog = "Class " + callerClass + " called object "
					+ objectType + "'s method \"" + signature 
					+ "\" with no parameter.";
		}
		// the method takes some arguments
		else {
			stringToLog = "Class " + callerClass + " called object "
					+ objectType + "'s method \"" + signature 
					+ "\" with parameters:" + parameters;
		}
		
		// Use logger from the receiver
		Logger.log(stringToLog);
	}
}
