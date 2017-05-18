/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: May 3, 2017
 * File: MyStack.java
 * Sources of Help: Piazza
 *
 * This class contains the abstract data type object Deque12 which
 * is a Double Ended Queue that can be used either as a stack or a queue.
 * This class implements the stack methods so to act as a maze
 * solver.
*/
package hw4;

/**
 * This class contains the abstract data type object Deque12 which
 * is a Double Ended Queue that can be used either as a stack or a queue.
 * This class implements the stack methods so to act as a maze
 * solver.
 * @author jingyi
 *
 * @param <E>
 */
public class MyStack<E> implements BoundedStack<E>  {

	private Deque12 ForBoundedStackMets;
	
	/**
	 * a public constructor with a single argument of type <tt>int</tt>, 
	 * which specifies the capacity of the BoundedStack.  The constructor 
	 * should throw an IllegalArgumentException if the specified capacity 
	 * is negative.
	 */
	  public MyStack(int capacity) throws IllegalArgumentException{
		  
		  if (capacity < 0){
			  throw new IllegalArgumentException();
		  }
		  
		  ForBoundedStackMets = new Deque12(capacity);
	  }
	
	
	/**
	   * Returns the capacity of this BoundedStack, that is,
	   * the maximum number of elements it can hold.
	   * <br>PRECONDITION: none
	   * <br>POSTCONDITION: the BoundedStack is unchanged.
	   * @return the capacity of this BoundedStack
	   */
	  public int capacity(){
		  
		  return ForBoundedStackMets.capacity();
	  }
	  
	  
	  /**
	   * Returns the number of elements in this BoundedStack.
	   * <br>PRECONDITION: none
	   * <br>POSTCONDITION: the BoundedStack is unchanged.
	   * @return the number of elements in this BoundedStack
	   */
	  public int size(){
		  
		  return ForBoundedStackMets.size();
	  }
	  
	  
	  /**
	   * Adds the specified element to the top of this BoundedStack.
	   * Returns true if the operation succeeded, else false.
	   * <br>PRECONDITION: the BoundedStack's size is less than its capacity.
	   * <br>POSTCONDITION: the element is now the top element in this
	   * BoundedStack, none of the other elements have been changed, and
	   * the size is increased by 1.
	   * @param e the element to add to the stack
	   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
	   * @throws NullPointerException if the specified element is null,
	   * and size is less than capacity
	   */
	  public boolean push(E e) throws NullPointerException{
		  
		  if (e == null && ForBoundedStackMets.size() < ForBoundedStackMets.capacity()){
			  throw new NullPointerException();
		  }
		  
		  return ForBoundedStackMets.addBack(e);
	  }
	  
	  
	  /**
	   * Removes the element at the top of this BoundedStack.
	   * Returns the element removed, or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedStack's size is greater than zero.
	   * <br>POSTCONDITION: the top element in this BoundedStack has been removed,
	   * none of the other elements have been changed, and
	   * the size is decreased by 1.
	   * @return  the element removed, or <tt>null</tt> if the size was zero.
	   */
	  public E pop(){
		  
		  return (E)ForBoundedStackMets.removeBack();
	  }
	  
	  
	  /**
	   * Returns the element at the top of this BoundedStack,
	   * or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedStack's size is greater than zero.
	   * <br>POSTCONDITION: The BoundedStack is unchanged.
	   * @return  the element at the top, or <tt>null</tt> if the size was zero.
	   */
	  public E peek(){
		  
		  return (E)ForBoundedStackMets.peekBack();
	  }
}
