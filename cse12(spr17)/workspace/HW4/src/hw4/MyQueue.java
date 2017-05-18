/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: May 3, 2017
 * File: MyStack.java
 * Sources of Help: Piazza
 *
 * This class contains the abstract data type object Deque12 which
 * is a Double Ended Queue that can be used either as a stack or a queue.
 * This class implements the queue methods so to act as a maze
 * solver.
*/
package hw4;

/**
 * This class contains the abstract data type object Deque12 which
 * is a Double Ended Queue that can be used either as a stack or a queue.
 * This class implements the queue methods so to act as a maze
 * solver.
 * @author jingyi
 *
 * @param <E>
 */
public class MyQueue<E> implements BoundedQueue<E> {

	  private Deque12 Que4Mets;
	
	  /*
	   * a public constructor with a single argument of type <tt>int</tt>,
	   *  which specifies the capacity of the BoundedQueue.  The constructor 
	   *  should throw an IllegalArgumentException if the specified capacity
	   *  is negative.
	   */
	  public MyQueue(int capacity) throws IllegalArgumentException{
	 	
		  if (capacity < 0){
			  throw new IllegalArgumentException();
		  }
		  
		  Que4Mets = new Deque12(capacity);
	  }
	  
	  
	/**
	   * Returns the capacity of this BoundedQueue, that is,
	   * the maximum number of elements it can hold.
	   * <br>PRECONDITION: none
	   * <br>POSTCONDITION: the BoundedQueue is unchanged.
	   * @return the capacity of this BoundedQueue
	   */
	  public int capacity(){
		  return Que4Mets.capacity();
	  }
	  
	  
	  /**
	   * Returns the number of elements in this BoundedQueue.
	   * <br>PRECONDITION: none
	   * <br>POSTCONDITION: the BoundedQueue is unchanged.
	   * @return the number of elements in this BoundedQueue
	   */
	  public int size(){
		  
		  return Que4Mets.size();
	  }
	  
	  
	  /**
	   * Adds the specified element to the tail of this BoundedQueue.
	   * Returns true if the operation succeeded, else false.
	   * <br>PRECONDITION: the BoundedQueue's size is less than its capacity.
	   * <br>POSTCONDITION: the element is now the tail element in this
	   * BoundedQueue, none of the other elements have been changed, and
	   * the size is increased by 1.
	   * @param e the element to add to the queue
	   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
	   * @throws NullPointerException if the specified element is null,
	   * and size is less than capacity
	   */
	  public boolean enqueue(E e) throws NullPointerException{
		  
		  if (e == null && Que4Mets.size() < Que4Mets.capacity()){
			  throw new NullPointerException();
		  }
		  else{
			  Que4Mets.addFront(e);
		  }
		  return Que4Mets.addFront(e);
	  }
	  
	  
	  /**
	   * Removes the element at the head of this BoundedQueue.
	   * Returns the element removed, or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedQueue's size is greater than zero.
	   * <br>POSTCONDITION: the head element in this BoundedQueue has been removed,
	   * none of the other elements have been changed, and
	   * the size is decreased by 1.
	   * @return  the element removed, or <tt>null</tt> if the size was zero.
	   */
	  public E dequeue(){
		  
		  return (E)Que4Mets.removeFront();
	  }
	  
	  
	  /**
	   * Returns the element at the head of this BoundedQueue,
	   * or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedQueue's size is greater than zero.
	   * <br>POSTCONDITION: The BoundedQueue is unchanged.
	   * @return  the element at the head, or <tt>null</tt> if the size was zero.
	   */
	  public E peek(){
		  return (E) Que4Mets.peekFront();
	  }
}
