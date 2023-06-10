package Infix_to_Postfix;

/**
 * The interface of the GenericStack class which lists all the methods needed in the class.
 * @author alvinanto
 * @param <E>
 */
public interface GenericStackInterface<E>
{
	//The void method push(E item) pushes the item in to the stack.
	public void push(E item);
	
	//The method pop() removes the topmost element from the stack and returns it.
	public E pop();
	
	//The void method popAll() removes all the elements from the stack.
	public void popAll();
	
	//The method peek() returns the topmost element on the stack.
	public E peek();
	
	//The method isEmpty() returns true is the stack is empty and false otherwise.
	public boolean isEmpty();
	
	//The method size() returns the size of the stack or the list.
	public int size();

}
