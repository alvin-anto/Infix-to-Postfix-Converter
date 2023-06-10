/**
 * 
 */
package Infix_to_Postfix;

//importing java.io.* package for using IOException
import java.io.*;
import java.util.*;


/**
 * The Helper class
 * @author alvinanto
 */
public class Helper 
{
	static ArrayList<Expression> expressionsList;

	/**
	 * The static void method start() creates an empty array list that can be used to store a list of Expression objects.
	 * This method also calls create() method with a reference to the array list, which adds a list of Expression objects into the array list
	 * and displayAndMore() method with a reference to the array list, which displays the infix, postfix expressions and the value after evaluation.
	 * @throws IOException 
	 */
	public static void start() throws IOException
	{
		expressionsList = new ArrayList<Expression>();
		create(expressionsList);
		displayAndMore(expressionsList);
	}
	
	/**
	 * The static void method create(ArrayList<Expression> arrayList) takes in lines from the text file and stores it as Expression objects in the ArrayList
	 * @param expList
	 * @throws IOException
	 */
	public static void create(ArrayList<Expression> expList) throws IOException
	{
		//creating a new FileReader object with "infixExpressions.txt".
		FileReader file1 = new FileReader("infixExpressions.txt");
		
		//creating BufferedReader object with file1.
		BufferedReader file2 = new BufferedReader(file1);
		
		//reads the first line from the text file.
		String line = file2.readLine();
		
		//using the overloaded constructor with the line as the argument to test the program.
		expList.add(new Expression(line));
		
		//reads the next line
		line = file2.readLine();
		
		//the loop is ran till there is no other expressions in the text file and the expression on each line is added as a Expression objects, which are then added to the array list.
		while (line != null)
		{
			expList.add(new Expression(line));
			line = file2.readLine();
		}
		
		//close the files used for taking input from the text file.
		file2.close();
		file1.close();
	}
	
	/**
	 * The displayAndMore(ArrayList<Expression> arrayList) displays the infix, postfix expressions and value after the evaluation.
	 * @param arrayList
	 * @throws IndexOutOfBoundsException
	 */
	public static void displayAndMore(ArrayList<Expression> expList) throws IndexOutOfBoundsException
	{
		for (int i = 0; i < expList.size(); i++)
		{
			System.out.println("Infix: " + expList.get(i) + "\t\t" + "Postfix: " + expList.get(i).PostFixExp() + "\t\t" + "Value: " + expList.get(i).evaluatePostFix()+ "\n");
		}
	}

}
