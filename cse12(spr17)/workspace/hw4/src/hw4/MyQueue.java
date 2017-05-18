package hw4;

public class MyQueue<T> implements Stack_QueueInterface<T> {
	
	private T[] queueArray;
	private int ptr2Front = 0;
	private int ptr2Back = 0;
	private int nelems;
	

	public MyQueue(){
		
		queueArray = (T[]) new Object[5];
		
		nelems = 0;
		
	}
	
	
	public boolean isEmpty(){
		
		return (ptr2Front == ptr2Back);
	}
	
	
	/* Since Queue is FIFO, we add at the back and remove at the front.
	 * scenarios we have to consider: normal case of adding(when front is at 
	 * 0, case when array is going to be full, 
	 * @see hw4.Stack_QueueInterface#addElement(java.lang.Object)
	 */
	public void addElement(T newItem){

		// if array is going to be full
		if ((ptr2Back+1)%size() == ptr2Front){
			
			// create new ref to larger array.
			T[] largerArray = (T[]) new Object[queueArray.length *2];
			
			// assign the elements to the twice as large array
			for (int i =0; i < size(); i++){
				
				largerArray[i] = queueArray[i];
			}
			
			// put the new elem at the end of queue
			largerArray[size()] = newItem;
			
			// points to the index behind last elem
			ptr2Back = size()+1;
			
			
			// reference variable array to larger array
			queueArray = largerArray;
		} 
		// if ptr2back has to add over array size but array not full
		else if (ptr2Back == (queueArray.length-1)) {
			
			ptr2Back = 0;
			
			queueArray[ptr2Back] = newItem;
			
			//update to point to empty elem after last elem
			ptr2Back++;
		}
		// if normal add 
		else {
			
			queueArray[ptr2Back] = newItem;
			
			// update to point to empty elem after last elem
			ptr2Back++;
		}
		
		// increment num of elems of array
		nelems++;
		
	}
	
	
	/* Since Queue is FIFO, we add at the back and remove at the front.
	 * case if empty, case if not empty? case if first element is at
	 * last index?
	 * @see hw4.Stack_QueueInterface#removeElement()
	 */
	public T removeElement(){
		
		// ensure queue is not empty, if empty throws exception
		peek();
		
		// if ptr2Front points to last index, needs to point to
		// start of queue after removal
		if (ptr2Front == queueArray.length-1){
			
			ptr2Front = 0;
			
			return queueArray[queueArray.length-1];
		}
		else {
			ptr2Front++;
			
			return queueArray[ptr2Front--];
		}
		
	}
	
	
	/** Returns the number of items in the storage 
	    * @return the number of items in the storage
	    */ 
	public int size(){
		
		return nelems;
		
	}
	
	
	/** Returns the next item to be removed
	 * @return element to be removed next
	 * @throws NullPointerException if list is empty
	 */
	public T peek(){
		
		if (ptr2Front == ptr2Back){
			throw new NullPointerException();
		}
		else{
			return queueArray[ptr2Front];
		}
	}
	
}
