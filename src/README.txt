To use this AspectJ implementation of a logger, the user must first make a class 
called Logger.java with a public static void method called log(String). This
class is then put into the logging package. 

To use the package on Java code, the user must import the interface LogMethods
from the logging package and implement it on classes that the logging module
will track. By default (and there is no other options, yet), the logging module
logs all methods, including constructors, within an implemented class. A default
string that describes each logging is passed to the log method provided by the 
user-made Logger.java class.

It is unclear whether this module logs static methods. Please try it and report
the result.