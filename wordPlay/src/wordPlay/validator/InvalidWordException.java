package wordPlay.validator;

import java.lang.Exception;

public class InvalidWordException extends Exception {
	public InvalidWordException(String s){  
		  super(s);  
    }
}