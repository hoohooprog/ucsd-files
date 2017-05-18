package hw6;

import java.util.*;

public class dHeap<T extends Comparable<? super T>> 
        implements dHeapInterface<T> {
    
    public T[] heap; //heap array
    private int d; //branching factor
    private int nelems = 0;
    private boolean isMaxHeap; //boolean to indicate whether heap is max or min
	private boolean isDHeap;
	private boolean doneBubbling=false;
		
		
	/**
	 * Initializes a binary max heap with capacity = 5
	 */
	public dHeap() {
		
		heap = (T[]) new Comparable[5];
		
		isMaxHeap = true;
		
	}
	
	
    /**
     * Initializes a binary max heap with a given initial capacity.
     * 
     * @param heapSize The initial capacity of the heap.
     */
	public dHeap( int heapSize ) {
	
	    heap = (T[]) new Comparable[heapSize];
	    
		isMaxHeap = true;
	}

	
	/**
	 * Initializes a d-ary heap (with a given value for d), with a given
	 * initial capacity.
	 * 
	 * @param d The number of child nodes each node in the heap should have.
	 * @param heapSize The initial capacity of the heap.
	 * @param isMaxHeap indicates whether the heap should be max or min
	 * @throws IllegalArgumentException if d is less than one.
	 */
	@SuppressWarnings( "unchecked" )
    public dHeap( int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
	   
	   if (d < 1){
		   throw new IllegalArgumentException();
	   }
	   
	   heap = (T[]) new Comparable[heapSize];
	   
	   
	   this.d = d;
	   isDHeap = true;
	   this.isMaxHeap = isMaxHeap;
	   
	}

	/**
     * Returns the number of elements stored in the heap.
     * 
     * @return The number of elements stored in the heap.
     */
	@Override
	public int size() {
	    
	   return nelems;
	    
	}
	
	
	/**
     * Adds the specified element to the heap; o cannot be null. 
     * Resizes the storage if full.
     * 
     * @param o The element to add.
     * @throws NullPointerException if o is null.
     */
	@Override
	public void add( T data ) throws NullPointerException {
	     
		if ( data == null){
			throw new NullPointerException();
		}
		
		if (size() == heap.length){
			resize();
		}
		
		//place new data in unoccupied index in array
		heap[nelems] = data;
		// increase number of elements
		nelems++;
		
		//bubbleUp the data in the elem I stored in
		bubbleUp(nelems-1);
		
		doneBubbling = false;
	    
	}

	
	/**
     * Removes and returns the element at the root. If the 
     * heap is empty, then this method throws a NoSuchElementException.
     * 
     * @return The element at the root stored in the heap.
     * @throws java.util.NoSuchElementException if the heap is empty
     */
	@Override
	public T remove() throws NoSuchElementException {
	    
		T rootElem = heap[0];
		
	    if (nelems == 0){
			throw new NoSuchElementException();
		}
		
		heap[0] = heap[nelems-1];
		heap[nelems-1] = null;
		nelems--;
		
		trickleDown(0);
		
		doneBubbling = false;
		
		return rootElem;
		
	}
	
	
	/**
     * Clears all the items in the heap
     * Heap will be empty after this call returns
     */
	public void clear() {
		
		for (int i=0; i<nelems; i++){
			
			heap[i] = null;
			
		}
		
		nelems = 0;
	}
	
	
	/**
     * Retrieves, but does not remove, the element at the root.
     * @return item at the root of the heap
     * @throws NoSuchElementException - if this heap is empty
     */
	public T element() throws NoSuchElementException {
		
		if (size() == 0){
			throw new NoSuchElementException();
		}
		
		return heap[0]; 
	}
	
	
	private void trickleDown(int index){
		
		if ( index == size()){
			return;
		}
		
		// compare than hold the index of the bigger child
		int biggerOfTwoChildsIndex = leftChild(index);
		int smallerOfTwoChildsIndex = leftChild(index);
		// hold the elem in the bigger child
		T holdChildElem;
		
		if (!isDHeap){
		    if (isMaxHeap){
			    if ( heap[leftChild(index)] == null || compareTo(index, leftChild(index)) >= 0){
			    	
			    	return;
			    	
			    }
			    if  ( heap[rightChild(index)] == null || compareTo(index, rightChild(index)) >= 0){
			    	
				    return;
				    
			    }
			    // to know which child is bigger
			    biggerOfTwoChildsIndex = compareTo(leftChild(index), rightChild(index));
			
			    // if left > right, hold its elem and store the index
			    if (biggerOfTwoChildsIndex > 0){
				    holdChildElem = heap[leftChild(index)];
				    biggerOfTwoChildsIndex = leftChild(index);
			    }
			    else{
				    holdChildElem = heap[rightChild(index)];
				    biggerOfTwoChildsIndex = rightChild(index);
			    }
			
			    // store value of index(elem) in child index
		        heap[biggerOfTwoChildsIndex] = heap[index];
			    // store elem in parent node
			    heap[index] = holdChildElem;
			    
			    // recursive
				trickleDown(biggerOfTwoChildsIndex);
		    }
		    //min heap algo for binary heap
		    else{

		    	if ( heap[leftChild(index)] == null || compareTo(index, leftChild(index)) <= 0){
			    	
			    	return;
			    	
			    }
			    if  ( heap[rightChild(index)] == null || compareTo(index, rightChild(index)) <= 0){
			    	
				    return;
				    
			    }
			    // to know which child is smaller
			    smallerOfTwoChildsIndex = compareTo(leftChild(index), rightChild(index));
			
			    // if left > right, hold right elem and store the index
			    if (smallerOfTwoChildsIndex > 0){
				    holdChildElem = heap[rightChild(index)];
				    smallerOfTwoChildsIndex = rightChild(index);
			    }
			    else{
				    holdChildElem = heap[leftChild(index)];
				    smallerOfTwoChildsIndex = leftChild(index);
			    }
			
			    // store value of index(elem) in child index
		        heap[smallerOfTwoChildsIndex] = heap[index];
			    // store elem in parent node
			    heap[index] = holdChildElem;
			    
			    // recursive
				trickleDown(smallerOfTwoChildsIndex);
		    }
	    }
		else{
			
		    if (isMaxHeap){
			
                if ( heap[leftChild(index)] == null){
			        return;
                }
	            // initial set as left-most child
				holdChildElem = heap[leftChild(index)];
				
			    // to know which child is bigger
			    for (int startLeft=leftChild(index); startLeft<= 
				    rightChild(index); startLeft++){
			    	
			    	if (startLeft == size()-1){
			    		return;
			    	}
				    if (compareTo(startLeft,rightChild(index)) < 0){
						holdChildElem = heap[rightChild(index)];
						biggerOfTwoChildsIndex = rightChild(index);
					}  		
				}
			
			    if (compareTo(index,biggerOfTwoChildsIndex) == 0 || 
				    compareTo(index, biggerOfTwoChildsIndex) > 0){
						return;
					}
				else{
					// store value of index(elem) in child index
		            heap[biggerOfTwoChildsIndex] = heap[index];
			        // store elem in parent node
			        heap[index] = holdChildElem;
				}
			    
			    // recursive
				trickleDown(biggerOfTwoChildsIndex);
						    
		    }
		    // min-heap algo for dHeap
		    else{
		    	
		    	try{
		    		
		    		if ( heap[leftChild(index)] == null || index == size()){
				        return;
	                }
		    		
				    holdChildElem = heap[leftChild(index)];
				
				    // to know which child is smaller
				    for (int startLeft=leftChild(index); startLeft<=rightChild(index)
						; startLeft++){
					
					    if (startLeft == size()-1){
						    return;
					    }
					    if (compareTo(startLeft,rightChild(index)) > 0){
						    holdChildElem = heap[rightChild(index)];
						    smallerOfTwoChildsIndex = rightChild(index);
					    }
				    }
				
				    if (compareTo(index, smallerOfTwoChildsIndex) == 0 ||
						compareTo(index, smallerOfTwoChildsIndex) < 0){
					    return;
				    }
				    else{
					    heap[smallerOfTwoChildsIndex] = heap[index];
					    heap[index] = holdChildElem;
				    }
				
				    // recursive
				    trickleDown(smallerOfTwoChildsIndex);
			    }catch(ArrayIndexOutOfBoundsException e){};
		    }
		
	    }
		}
	
	
	private void bubbleUp(int index){
		
		//local var to hold Parent's element
		T holdParentElem;
		
		// if reached 1st index, change flag to true
		// 1st base case
		if ( index == 0 ){
			return;
		}
		
			
		if (isMaxHeap){
			
			//2nd base case
	        if (compareTo(index,parent(index)) <= 0)  {
		        
	        	return;
	        }
	        	        
		    holdParentElem = heap[parent(index)];
		
		    heap[parent(index)] = heap[index];
		
		    heap[index] = holdParentElem;
		    
		}
		// min heap's bubble up
		else{
			//2nd base case
	        if (compareTo(index,parent(index)) >= 0)  {
		        
	        	return;
	        }
	        	        
		    holdParentElem = heap[parent(index)];
		
		    heap[parent(index)] = heap[index];
		
		    heap[index] = holdParentElem;
		}
		
		bubbleUp(parent(index));
			
	}
	
	
	private void resize(){
		
		T[] tempHeap = (T[]) new Comparable[nelems*2];
		
		for (int i=0; i<nelems; i++){
			
			tempHeap[i] = heap[i];
		}
		
		heap = tempHeap;
		
	}
	
	
	private int leftChild(int index){
		
		if(!isDHeap){
		    return (2*index+1);
		}
		else{
			return (d*index+1);
		}
	}
	
	
	private int rightChild(int index){
		
		int i;
		
		if(!isDHeap){
		    i = (2*index+2);
		    
		    return i;
		}
		else{
			
			i = (d*index+d);
			
			return i;
		}
	}
	
	
	private int parent(int index){
		
		int i;
		
		if(!isDHeap){
		    i = ((index-1)/2);
		    
		    return i;
		}
		else{
		    i = ((index-1)/d);
		    
		    return i;
		}
	}
	
	

	private int compareTo(int index, int parentIndex){
		
		return heap[index].compareTo(heap[parentIndex]);
	}
	
	
} // End of public class dHeap<T extends Comparable<? super T>> 
  // implements dHeapInterface<T>.
