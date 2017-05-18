/**
 * Name: Jingyi Tay
 * Login: cs12sie
 * Date: 5/3/2017
 * File: Deque12.java
 * Sources of Help: piazza, write-up
 * 
 * class implements the ADT DoubleEnded Queue methods
 * using ADP ArrayList.
 * 
 */
package hw4;

import java.util.ArrayList;

/**
 * * class implements the ADT DoubleEnded Queue methods
 * using ADP ArrayList.
 * @author jingyi
 *
 * @param <E>
 */
public class Deque12<E> implements BoundedDeque<E> {
    	
	//resizable array
	private ArrayList<E> storeElements;
	
	// number of actual elements input by user instead of user defined
	// deque size
	private int userInputs;
	
	
	/* a public constructor with a single argument of type int, which 
	 * specifies the capacity of the BoundedDeque.  The constructor 
	 * should throw an IllegalArgumentException if the specified
	 * capacity is negative.	
	 */ 
	public Deque12(int capacity) throws IllegalArgumentException{
		   
		 // if capacity is -ve, throw exception
		 if (capacity < 0){
			  throw new IllegalArgumentException();
		 }
		 
		 storeElements = new ArrayList<E>(capacity);
		 
		 // add null into array so arraylist's size can
		 // return Deque's capacity
		 for (int i=0; i<capacity; i++){
			 storeElements.add(null);
		 }  
		  
	}

	
	/**
	   * Returns the capacity of this BoundedDeque, that is,
	   * the maximum number of elements it can hold.  
	   * <br>PRECONDITION: none 
	   * <br>POSTCONDITION: the BoundedDeque is unchanged.  
	   * @return the capacity of this BoundedDeque
	   */
	public int capacity(){
		
		return storeElements.size();
		  
	}
	  
    	  
	 /**
	   * Returns the number of elements in this BoundedDeque. 
	   * <br>PRECONDITION: none 
	   * <br>POSTCONDITION: the BoundedDeque is unchanged. 
	   * @return the number of elements in this BoundedDeque
	   */
	public int size(){
		  
		return userInputs;
	}

	  /**
	   * Adds the specified element to the front of this BoundedDeque.
	   * Returns true if the operation succeeded, else false. 
	   * <br>PRECONDITION: the BoundedDeque's size is less than its capacity. 
	   * <br>POSTCONDITION: the element is now the front element in this 
	   * BoundedDeque, none of the other elements have been changed, and
	   * the size is increased by 1. 
	   * @param e the element to add to the front of the list
	   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
	   * @throws NullPointerException if the specified element is null,
	   * and size is less than capacity
	   */
	public boolean addFront(E e) throws NullPointerException{
		
		int elem2Replace = 0;
		boolean truth;
		
		// if user wants to add null element and there is still capacity
		// to add elements
		if (e == null && this.size() < this.capacity()){
			throw new NullPointerException();
		}
		
		// if user wants to add null element but no more capacity
		// or valid element but no more capacity
		if ( e==null && this.size() == this.capacity()){
			truth = false;
		}
		else if (e != null && this.size() < this.capacity()){
			// increment the number of user input elements(size)  
			userInputs++;
			
			// shifts existing elems to the right of array
			// size-1 because the last size is empty and 
			// I want to move element to the last size
			for (int i=size()-1; i>0; i--){
				
				storeElements.set(i, storeElements.get(i-1));
				
			}
			
			storeElements.set(0, e);
			
			truth = true;
		}
		else{
			return false;
		}
		
		return truth;
	}
	 
	  
	  /**
	   * Adds the specified element to the back of this BoundedDeque.
	   * Returns true if the operation succeeded, else false. 
	   * <br>PRECONDITION: the BoundedDeque's size is less than its capacity. 
	   * <br>POSTCONDITION: the element is now the back element in this 
	   * BoundedDeque, none of the other elements have been changed, and
	   * the size is increased by 1. 
	   * @param e the element to add to the back of the list
	   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
	   * @throws NullPointerException if the specified element is null,
	   * and size is less than capacity
	   */
	public boolean addBack(E e) throws NullPointerException{
		
		boolean truth;
		  
		if (e == null && this.size() < this.capacity()){
			throw new NullPointerException();
		}
		
		if (this.size() == this.capacity()){
			
			truth = false;
		}
		else{
			
			storeElements.set(userInputs, e);
			userInputs++;
			
			truth = true;
		}
		
		return truth;
	}

	  
	  /**
	   * Removes the element at the front of this BoundedDeque.
	   * Returns the element removed, or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedDeque's size is greater than zero.
	   * <br>POSTCONDITION: the front element in this BoundedDeque has been removed,
	   * none of the other elements have been changed, and
	   * the size is decreased by 1.
	   * @return  the element removed, or <tt>null</tt> if the size was zero.
	   */
	public E removeFront(){
		
		E elemRemoved;
		  
		if (this.size() == 0){
			elemRemoved = null;
		}
		else{
			
			elemRemoved = (E)storeElements.get(0);
			
			// shifts existing elems to the right of array
			// size-1 because the last size is empty and 
			// I want to move element to the last size
			for (int i=0; i<storeElements.size()-1; i++){
							
			    storeElements.set(i, storeElements.get(i+1));
							
			}
			
			userInputs--;
		}
		
		return elemRemoved;
		
	}
	  
	
	  /**
	   * Removes the element at the back of this BoundedDeque.
	   * Returns the element removed, or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedDeque's size is greater than zero.
	   * <br>POSTCONDITION: the back element in this BoundedDeque has been removed,
	   * none of the other elements have been changed, and
	   * the size is decreased by 1.
	   * @return  the element removed, or <tt>null</tt> if the size was zero.
	   */
	public E removeBack(){
		
		E elemRemoved;
		
		if (this.size() == 0){
			elemRemoved = null;
		}
		else{
			
			elemRemoved = (E) storeElements.remove(this.size()-1);
			
			userInputs--;
		}
		
		return elemRemoved;
		
	}

	  
	  /**
	   * Returns the element at the front of this BoundedDeque,
	   * or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedDeque's size is greater than zero.
	   * <br>POSTCONDITION: The BoundedDeque is unchanged.
	   * @return  the element at the front, or <tt>null</tt> if the size was zero.
	   */
	public E peekFront(){
		  
		if ( this.size() == 0){
			return null;
		}
		else{
			return (E)storeElements.get(0);
		}
	}

	  
	  /**
	   * Returns the element at the back of this BoundedDeque,
	   * or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedDeque's size is greater than zero.
	   * <br>POSTCONDITION: The BoundedDeque is unchanged.
	   * @return  the element at the back, or <tt>null</tt> if the size was zero.
	   */
	
	public E peekBack(){
		  
		if (this.size() == 0){
			return null;
		}
		else{
			return (E)storeElements.get(size()-1);
		}
	}

}
