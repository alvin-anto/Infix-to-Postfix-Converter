package Infix_to_Postfix;

/**
* The exception class handles the error when there is a run time error.
* creates a StackException class, that prints an exception message, that extends RuntimeException
* @author alvinanto
*/
public class StackException extends RuntimeException
{

	public StackException(String message) 
	{
		super(message);
	}

}
