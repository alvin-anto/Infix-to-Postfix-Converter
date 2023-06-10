/**
 * @author alvinanto
 * 04/01/2022
 * 213 Project 3
 */
package Infix_to_Postfix;

import java.util.*;

/**
 * @author alvinanto
 * The Expression class is used to create Expression objects whohc are infix expressions which are then converted to postfix and then evaluated using different methods.
 */
public class Expression {

	//instance variable for storing the infix expression as string
	private String infixExpression;
	
	/**
	 * The default constructor which assigns "" to infixExpression
	 */
	public Expression() 
	{
		infixExpression = "";
	}
	
	/**
	 * The overloaded constructor takes in an infix expression as string as a parameter and stores that in infixExpression
	 */
	public Expression(String infix)
	{
		infixExpression = infix;
	}
	
	/**
	 * The getter method to return the infixExpression
	 * @return infixExpression
	 */
	public String getInfixExpression()
	{
		return infixExpression;
	}
	
	/**
	 * The setter method to set the infixExpression to be the value passed as the argument.
	 * @param infix
	 */
	public void setInfixExpression(String infix)
	{
		infixExpression = infix;
	}
	
	/**
	 * The infix expression is converted to postfix expression and then returned.
	 * @return the postfix expression as a list of tokens.
	 */
	public StringTokenizer convertInfixToPostFix()
	{
		//creates a new GenericStack with String type
		GenericStack<String> stack = new GenericStack<String>();
		
		//variable of String type to store the Postfix expression
		String exp = "";
		
		//creates a StringTokenizer object which tokenizes infixExpression and stores it as tokens.
		StringTokenizer stringTokens = new StringTokenizer(infixExpression, "+-*/()", true);
		
		//loop runs till the tokens are finished
		while (stringTokens.hasMoreTokens())
		{
			String token = stringTokens.nextToken();

			//if the token is (, pushing it to the stack
			if (token.equals("("))
			{
				stack.push(token);
			}
			//if the token is an operator
			else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^"))
			{
				//if the stack is empty, pushing the token to the stack
				if (stack.isEmpty()) 
				{
					stack.push(token);
				}
				else
				{
					if (orderOfOperations(token) > 0)
					{
						//according to the order of operations and associativity, the token is either pushed to the stack or the top element is popped accordingly.
						if ((orderOfOperations(stack.peek()) == orderOfOperations(token)) && (associativity(token).equals("lToR")))
						{
							exp += " " + stack.pop();
							stack.push(token);
						}
						else if ((orderOfOperations(stack.peek()) == orderOfOperations(token)) && (associativity(token).equals("rToL")))
						{
							stack.push(token);
						}
						
						else if (orderOfOperations(stack.peek()) > orderOfOperations(token))
						{
							exp += " " + stack.pop();
							stack.push(token);
						}
						else
						{
							stack.push(token);
						}
					}
				}
			}
			//if the token is a space, simply add the space to the postfix expression
			else if (token.equals(" "))
			{
				exp += token;
			}
			//if the token is ),
			else if (token.equals(")"))
			{
				//continue with the next iteration of the loop if the stack is empty
				if (stack.isEmpty())
				{
					continue;
				}
				//pop the topmost stack element until there is a (
				else
				{
					while (!(stack.peek().equals("(")))
					{
						exp += " " + stack.pop();
					}
					stack.pop();
				}
			}
			else
			{
				exp += " " + token;
			}
		}
		while (!(stack.isEmpty()))
		{
			exp += " " + stack.pop();
		}
		//returning the postfix expression as tokens.
		return new StringTokenizer(exp, " */+-()", true);
	}
	
	/**
	 * The method postFixExp() calls the method to convert infix to postfix first and then return the postfix expression as a string
	 * @return postFixExp postfix expression as a string
	 */
	public String PostFixExp()
	{
		//converting to postfix and storing that in tok
		StringTokenizer tok = this.convertInfixToPostFix();
		String postFixExp = "";
		//loop runs till there are tokens remaining and then adds it to a string
		while (tok.hasMoreTokens())
		{
			postFixExp += tok.nextToken();
		}
		//returns the postfix expression as a string
		return postFixExp;
	}
	
	/**
	 * The method evaluatePostFix() evaluates the postFix expression and returns the value as an integer.
	 * @return the value of the evaluated postfix expression
	 */
	public int evaluatePostFix()
	{
		//creates a StringTokenizer object which tokenizes infixExpression and stores it as tokens.
		StringTokenizer tokens = new StringTokenizer(this.PostFixExp(), " ", false);
		
		//creates a new GenericStack with Integer type
		GenericStack<Integer> stack = new GenericStack<Integer>();
		
		//runs the loop till there are remaining tokens and then evaluating the postfix expression using the stack.
		while (tokens.hasMoreTokens())
		{
			String token = tokens.nextToken();
			
			//if the token is a space, continue with the next iteration of the loop.
			if (token.equals(" "))
			{
				continue;
			}
			//if the token is an operator, applies it to the two topmost elements in the stack and then pushing that result back to the stack
			else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^"))
			{
				if (token.equals("+"))
				{
					stack.push(stack.pop() + stack.pop());
				}
				else if (token.equals("-"))
				{
					int i =stack.pop();
					stack.push(stack.pop() - i);
				}
				else if (token.equals("*"))
				{
					stack.push(stack.pop() * stack.pop());
				}
				else if (token.equals("/"))
				{
					int i = stack.pop();
					stack.push(stack.pop() / i);
				}
				else
				{
					int k = stack.pop();
					Integer i = (int) Math.pow(stack.pop(), k);
					stack.push(i);
				}	
			}
			else
			{
				stack.push(Integer.valueOf(token));
			}
		}
		return stack.pop();	
	}
	
	/**
	 * The method orderOfOperations() returns 1, 2, 3 based on the precedence of the operator with 3, being the highest precedence.
	 * @param operation
	 * @return 1, 2, 3 based on the precedence of the operator with 3, being the highest precedence.
	 */
	public int orderOfOperations(String operation)
	{
		//if the operation is + or -
		if (operation.equals("+") || operation.equals("-"))
		{
			return 1;
		}
		//if the operation is * or /
		else if (operation.equals("*") || operation.equals("/"))
		{
			return 2;
		}
		//if the operation is ^
		else if (operation.equals("^"))
		{
			return 3;
		}
		else
		{
			return -1;
		}
	}
	
	/**
	 * The method associativity() returns lToR if the associativity is from left to right, or rToL if it is right to left.
	 * @param operation
	 * @return lToR if the associativity is from left to right, or rToL if it is right to left.
	 */
	public String associativity(String operation)
	{
		if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/"))
		{
			return "lToR";
		}
		else if (operation.equals("^"))
		{
			return "rToL";
		}
		else
		{
			return "";
		}
	}
	
	//overridden toString() method that prints out the infix expression
	public String toString()
	{
		return this.getInfixExpression();
	}
	
	//overridden equlas() method that compares two expressions
	public boolean equals(Expression myExp)
	{
		return infixExpression.equals(myExp.getInfixExpression());
	}
	

}
