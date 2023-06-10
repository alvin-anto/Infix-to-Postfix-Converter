/**
 * 
 */
package Infix_to_Postfix;

//importing the java.util.* package to use arrayList and its operations
import java.util.*;

/**
* @author alvinanto
* @param <E>
*
*/
public class GenericStack<E> implements GenericStackInterface<E> 
{
	
	//creating an array list to act as a stack and making it private
	private ArrayList<E> list;
	
	/**
	 * GenericStack() constructor creates a new ArrayList and use list to reference it
	 */
	public GenericStack() 
	{
		list = new ArrayList<>();
	}
	
	/**
	 * The void method push(E item) pushes the item in to the stack
	 * @param item
	 */
	public void push(E item)
	{
		list.add(item);
	}
	
	/**
	 * The method pop() removes the topmost element from the stack and returns it
	 * @return the topmost element in the stack
	 * @throws StackException if there is no elements in the stack at the time of calling the method.
	 */
	public E pop() throws StackException
	{
		if (isEmpty())
		{
			throw new StackException("Stack already empty");
		}
		else
		{
			E topElement = list.get(size() - 1);
			list.remove(size() - 1);
			return topElement;
		}
	}
	
	/**
	 * The void method popAll() removes all the elements from the stack.
	 * @throws StackException if the stack is already empty and there is no element left to be removed.
	 */
	public void popAll() throws StackException
	{
		if (isEmpty())
		{
			throw new StackException("Stack already empty");
		}
		else
		{
			list.clear();
		}
	}
	
	/**
	 * The method peek() returns the topmost element on the stack
	 * @return the topmost element on the stack
	 * @throws StackException if the stack is empty and there is not topmost element to return.
	 */
	public E peek() throws StackException
	{
		if (isEmpty())
		{
			throw new StackException("Stack empty");
		}
		else
		{
			E topElement = list.get(size() - 1);
			return topElement;
		}
	}
	
	/**
	 * The method isEmpty() returns true is the stack is empty and false otherwise.
	 * @return true if the list is empty and false is it is not.
	 */
	public boolean isEmpty()
	{
		if (list.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * The method size() returns the size of the stack or the list.
	 * return size of the list
	 */
	public int size()
	{
		return list.size();
	}


}
