/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12wamf <<< --- Use your cs11f course-specific account name
 * Date: January 26, 2017
 * File: DoublyLinkedList.java
 * Sources of Help: stackoverflow, piazza, misc websites describing 
 * 					linkedlists.
 *
 * This program shows the skeleton of what a doublylinkedlist is
 * by implementing the various classes - Node, Iterator, LinkedList.
 * By implementing these classes, we create a doubly linkedlist that
 * can not only traverse starting from the head or tail but also
 * a iterator that can keep track of previous actions so iterator
 * can iterate from the previous index to the next index rather than
 * looping through the list again.
 *
*/

package hw2;

import java.util.*;

public class DoublyLinkedList<E> extends AbstractList<E> {

	// No. of items in the list
	private int nelems;   
	
	//Nodes that represent the dummy(sentinel) nodes
	//at the front and back of the linkedlist
	private Node head;
	private Node tail;

/**
 * Purpose: To store data input by user and to link up the nodes together
 * to form a linked list. Contains methods to set which nodes current
 * node points to, element to set in the index, removal of current node,
 * getting the next node, previous node and the element in the current node.
 * @author Jingyi Tay
 * @version 1.0
 * @since January 26, 2017
 */
	protected class Node {

		/* these are the variables that exist in the node
		 * data stores the element while prev is a reference to the previous
		 * node object and next is a reference to the next node object.
		 */
		E data;
		Node prev;
		Node next;

		
	   /**
		* Purpose: creates a simpleton Node to store element. Node doesnt
		* points to any other nodes.
		* @param element of any type that user wants to store
		*/
		public Node(E element)
		{
			// initialize node to store element in data
			data = element;
			next = null;
			prev = null;
			
		}
		
		
		/** 
		 * Constructor to create singleton link it between previous and next 
		 * @param element Element is to add, can be null
		 * @param prevNode is predecessor Node, can be null
		 * @param nextNode is successor Node, can be null 
		 */
		public Node(E element, Node prevNode, Node nextNode)
		{
			// use setPrev(Node p) and setNext(Node n) to set Node's
			// Predecessor to PrevNode and successor to nextNode
			data = element;
			setNext(nextNode);
			setPrev(prevNode);
			
		}
		
		
		/**
		 *  Remove this node from the list. Update previous and next nodes 
		 *  
		 */
		public void remove()
		{
			// set previous node to point to the next node of node to be
			// removed
			this.getPrev().setNext(this.getNext());
			
			// set next node to point to the previous node of node to be
			// removed
			this.getNext().setPrev(this.getPrev());
		}
		
		
		/** 
		 * Set the previous node in the list
		 * @param p is the new previous node
		 */
		public void setPrev(Node p)
		{
			// point prev to node p
			prev = p;
		}
		
		
		/** 
		 * Set the next node in the list
		 * @param n new next node
		 */
		public void setNext(Node n)
		{
			// point next to node n
			next = n;
		}

		
		/** 
		 * Set the element 
		 * @param e new element 
		 */
		public void setElement(E e)
		{
			// point data to element e
			data = e;
		}
		
		
		/** 
		 * Accessor to get the next Node in the list 
		 * @return the Node that object next references
		 */
		public Node getNext()
		{
			return next; 
		}
		
		
		/** 
		 * Accessor to get the previous Node in the list 
		 * @return the Node that prev object references
		 */
		public Node getPrev()
		{
			return prev; 
		} 
		
		
		/** 
		 * Accessor to get the Node's Element.
		 * @return the element stored in object data 
		 */
		public E getElement()
		{
			return this.data;
		} 
	}

	/**
	 * Purpose of this class is to implement a Iterator to
	 * iterate through the class so it is more convenient to traverse
	 * the LinkedList. The Node object that iterator traversed to is indexed
	 * hence if user wants to get the previous or next node from the list,
	 * user can just use the iterator's method to shift the iterator one node
	 * up or down to access, change, remove, or set element. User can also
	 * remove node from the specific index. All these methods are done without
	 * looping through the list each time. 
	 * @author Jingyi Tay
	 * @version 1.0
	 * @since 26 January, 2017
     */ 
	protected class MyListIterator implements ListIterator<E> {

		/**
		 * objects and variables that iterator needs in order to move.
		 * Forward stores boolean value: if true, iterator is moving forward;
		 * if false, iterator is moving backwards.
		 * 
		 * canRemove stores boolean value: if true, iterator will remove the 
		 * left node if Forward is true, else, the iterator will remove the 
		 * right node if Forward is false.
		 * 
		 * Node objects, left and right, points to the dummy header node 
		 * and tailer nodes in the beginning if list is empty; if not empty,
		 * before 1st iteration, left node points to dummy header while right
		 * node points to Node indexed 0.Left and right nodes then change 
		 * nodes depending on movement of iterator.
		 * 
		 * Index keeps track of which node iterator is at. We can always
		 * assume that index counts starting from the header Node, 0, to
		 * the node that left points to.
		 */
		private boolean forward;
		private boolean canRemove;
		private Node left,right; 
		private int index;   

		
		/**
		 * Constructor sets index at 0 and points left Node to dummy head
		 * right Node to object indexed at 0. And set forward boolean to true.
		 */
		public MyListIterator()
		{
			index = 0;
			left = head;
			right = head.getNext();
			forward = true;
		}

		
		/**
		 * Method adds new element into the list. The element is
		 * inserted immediately before the element that would be returned
		 * by next(), if any, and after the element that would be returned
		 * by previous(), if any; this is done regardless of the iterator's
		 * direction.(If the list contains no elements, the new element 
		 * becomes the sole element on the list.) A subsequent call
		 * to next would be unaffected, and a subsequent call to previous
		 * would return the new element.(This call increases by one the
		 * value that would be returned by a call to nextIndex or 
		 * previousIndex.
		 * @param the element e that user wants to add to list
		 * @throws NullPointerException if user wants to add
		 * null to the list.
		 */
		@Override
		public void add(E e) throws NullPointerException
		{
			//check if element is null, if null, throws
			//NullPointerException.
			if (e == null) {
				throw new NullPointerException();
			}
			
			//connect changeNode to left and right
			Node changeNode = new Node(e,left,right);
			
			// connect left node to changeNode
			left.setNext(changeNode);
			// connect right node to changeNode
			right.setPrev(changeNode);
			// make right ref to changeNode;
			left = changeNode;
			
			//prevent removal of elements
			canRemove = false;
			
			//increase list size
			nelems++;
			//increase index
			index++;
		}
		
		
		/**
		 * Returns true if this list iterator has more elements when 
		 * traversing the list in the forward direction. (In other 
		 * words, returns true if next() would return an element rather than 
		 * throwing an exception.)
		 * @return boolean value that tells user if there's a next element
		 */
		@Override
		public boolean hasNext()
		{
			// local boolean variable to store decision if there's
			// a next element.
			boolean hasNext = false;
			
			// check if list is empty, if it is not, set value to true.
			if (right == tail) {
			    hasNext = false;	
			}
			else {
				hasNext = true;
			}
			
			// return value of the boolean variable
			return hasNext; 
		}

		/**
		 * Returns true if this list iterator has more elements when 
		 * traversing the list in the reverse direction. (In other words,
		 * returns true if previous() would return an element rather than
		 * throwing an exception.)
		 * @return boolean value that shows to user if iterator has 
		 * element in the reverse direction.
		 */
		@Override
		public boolean hasPrevious()
		{
			// boolean var to store value of decision
			boolean hasPrevious = false;
			
			// if is empty list, stores false, else stores true
			if ( left == head ) {
				hasPrevious = false;
			}
			else {
				hasPrevious = true;
			}
			
			// returns value of variable to method call.
			return hasPrevious; 
		}
		
		
		/**
		 * Returns the next element in the list and advances the cursor 
		 * position. This method may be called repeatedly to iterate through
		 * the list, or intermixed with calls to previous() to go back and 
		 * forth.(Note that alternating calls to next and previous will 
		 * return the same element repeatedly.)
		 * @return the next element
		 * @throws NoSuchElementException if there's no next element to 
		 * return.
		 */
		@Override
		public E next() throws NoSuchElementException
		{
			// if hasNext method determines that there is no next element
			// throws NoSuchElementException().
			if (hasNext() == false) {
				throw new NoSuchElementException();
			}
			
			/*
			 * Since there's a next element, right node will point to 
			 * the next Node, while left points to the right Node.
			 * We add to the index to keep track of movement forward
			 */
			right = right.getNext();
			left = left.getNext();
			index++;
			
			/*
			 * change forward boolean to true because
			 * iterator is moving forward and change canRemove
			 * to true because iterator now knows which
			 * element to remove if user wants to.
			 */
			forward = true;
			canRemove = true;
			
			// returns the reference to element to method call
			return left.getElement();  
		}
		
		
		/**
		 * Returns the index of the element that would be returned by a 
		 * subsequent call to next(). (Returns list size if the list iterator
		 * is at the end of the list.)
		 * @return the index of the element if next method is called; returns
		 * list size if at end of list.
		 */
		@Override
		public int nextIndex()
		{
			int storeIndex;
			
			// if there is no next element, store size of
			// list in local variable storeIndex and return the value
			// to method call; else returns the next index.
			if (hasNext() == false) {
				storeIndex = size();
			}
			else {
				storeIndex = index;
			}
			return storeIndex; 
		}
		
		
		/**
		 * Returns the previous element in the list and moves the cursor 
		 * position backwards. This method may be called repeatedly to iterate
		 * through the list backwards, or intermixed with calls to next() to 
		 * go back and forth. (Note that alternating calls to next and previous
		 * will return the same element repeatedly.)
		 * @return element pointed by previous Node
		 * @throws NoSuchElementException if previous Node is pointing to
		 * dummy header node
		 */
		@Override
		public E previous() throws NoSuchElementException
		{
			// if there's no previous element, throws NoSuchElementException
			if (hasPrevious() == false) {
				throw new NoSuchElementException();
			}
			
			/**
			 * Since there is a previous element, left Node is assigned
			 * to it's previous Node while right node references the original
			 * Node object of left node. Index is decremented to follow
			 * iterator movement
			 */
			left = left.getPrev();
			right = right.getPrev();
			index--;
			
			/*
			 * forward is changed to false since iterator is moving backwards
			 * canRemove is changed to true since iterator has moved.
			 */
			forward = false;
			canRemove = true;
			
			return right.getElement(); 
		}

		
		/**
		 * Returns the index of the element that would be returned by a 
		 * subsequent call to previous(). (Returns -1 if the list iterator
		 * is at the beginning of the list.)
		 * @return the previous index of the element if previousIndex is 
		 * called. If no element, returns -1.
		 */
		@Override
		public int previousIndex()
		{
			int storeIndex;
			
			// if there is no previous Index, return -1
			// else return that index
			if (hasPrevious() == false) {
			    storeIndex = -1;	
			}
			else {
				storeIndex = index;
			}
			return storeIndex;  
		}
		
		
		/**
		 * Removes from the list the last element that was returned by next()
		 * or previous() (optional operation). This call can only be made 
		 * once per call to next or previous. It can be made only if add(E)
		 * has not been called after the last call to next or previous.
		 * @throws IllegalStateException if add method was called or
		 * remove method was called before.
		 */
		@Override
		public void remove() throws IllegalStateException
		{
			if (canRemove == false) {
				throw new IllegalStateException();
			}
			
			/*
			 * If iterator is moving forward, we check first if there is a 
			 * previous element; 
			 * If there is a previous element, the right Node's previous Node
			 * will reference left node's previous node and left node's 
			 * previous node will set it's next node to reference
			 * right Node.
			 * we then set left Node to reference left's previous node
			 * to disconnect left node and decrease the number of 
			 * elements and index to reflect the removal.
			 * We then change the canRemove flag to false to indicate that 
			 * removal has already occurred
			 */
			if (forward == true) {
				
				if (hasPrevious() == false){
					
				}
				else{
					right.setPrev(left.getPrev());
					left.getPrev().setNext(right);
					left = left.getPrev();
					
					nelems--;
				    index--;
				    canRemove = false;
				}
			}
			/**
			 * If iterator is moving backwards, we check first if there is a 
			 * next element; if there is we remove the node that right is 
			 * pointing to by setting left's next node to right and
			 * referencing right's next node's previous node to left.
			 * we then remove right by making right node reference to
			 * it's next node.
			 * We finally decrease the number of elements and switch
			 * canRemove flag to indicate that removal has already been 
			 * made.
			 */
			else {				
				if (hasNext() ==  false){

				}
				else{
					left.setNext(right.getNext());
					right.getNext().setPrev(left);
					right = right.getNext();
					nelems--;
					canRemove = false;
				}
				
			}
			
		}
		
		/*
		 * Replaces the last element returned by next() or previous() 
		 * with the specified element (optional operation). This call 
		 * can be made only if neither remove() nor add(E) have been 
		 * called after the last call to next or previous.
		 * @throws NullPointerException when null is used as a element
		 * @throws IllegalStateException if remove or add methods
		 * were the last called.
		 */
		@Override
		public void set(E e) 
				throws NullPointerException,IllegalStateException
		{
			if (e == null) {
				throw new NullPointerException();
			}
			
			if (canRemove == false){
				throw new IllegalStateException();
			}
			
			/*
			 * if iterator is moving forward, put element in left node
			 * else put element in right node
			 */
			if (forward) {
				left.setElement(e);
			}
			else {
				right.setElement(e);
			}
			
		}

	}

	//  Implementation of the DoublyLinkedList Class
	/**
	 * Creates a new, empty doubly-linked list.
	 */
	public DoublyLinkedList()
	{
		head = new Node(null);
		tail = new Node(null);
		
		head.setNext(tail);
		tail.setPrev(head);
		
		nelems = 0;
				
	}

	
	/**
	 * Retrieves the amount of elements that are currently on the list.
	 * 
	 * @return Number of elements currently on the list
	 */
	@Override
	public int size()
	{
		return nelems; 
	}

	
	/**
	 * Adds an element to a certain index in the list, shifting exist elements
	 * to the right create room. Does not accept null values.
	 * 
	 * @param index Where in the list to add the element.
	 * @param data Data to be added.
	 * @throws IndexOutOfBoundsException if index received is out of bounds for 
	 *             the current list. 
	 * @throws NullPointerException if data received is null.
	 */
	@Override
	public void add(int index, E data) 
			throws IndexOutOfBoundsException,NullPointerException
	{
		// use size instead of size - 1 because we want to increase the size
		if (index > size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (data == null) {
			throw new NullPointerException();
		}
	 
		/*
		 * if list is empty, add new node between head and tail node
		 */
		if (isEmpty()){
			Node toAddData = new Node(data, head, tail);
			head.next = toAddData;
			tail.prev = toAddData;
			nelems++;
		}
		/*
		 * if index chosen is the one next to head node, connect head
		 * to new node and new node to the one at index
		 */
		else if(index-1 == -1){
			Node toAddData = new Node(data);
			Node atIndex = getNth(index);
			
			head.next = toAddData;
			toAddData.prev = head;
			
			toAddData.next = atIndex;
			atIndex.prev = toAddData;
			
			nelems++;
		}
		/*
		 * if index chosen is at one next to tail node, connect tail
		 * to new node and new node to the one at index
		 */
		else if(index == size()){
			Node toAddData = new Node(data);
			
			Node beforeIndex = getNth(index-1);
			Node afterIndex = tail;
			
			beforeIndex.next = toAddData;
			toAddData.prev = beforeIndex;
			
			toAddData.next = afterIndex;;
			afterIndex.prev = toAddData;
			
			nelems++;
		}
		/*
		 * if index chosen is between two elements, connect
		 * the node before index to new node and the new node to
		 * the one at the index.
		 */
		else {
			//put toAddData between atIndex and afterIndex
			Node toAddData = new Node(data);
			Node atIndex = getNth(index);
			Node beforeIndex = getNth(index-1);
			
			toAddData.next = atIndex;
			
			atIndex.prev = toAddData;
			
			toAddData.prev = beforeIndex;
			beforeIndex.next = toAddData;
			
			nelems++;
		}
		
	}

	
	/**
	 * Appends the specified element to the end of this list.
	 * Does not accept null elements.
	 * Discards previous Node's reference no matter what direction
	 * iterator is going and points previous Node to the new Node
	 * then linking up the nodes.
	 * @throws NullPointerException if null element is detected
	 * @return boolean value to indicate adding is a success.
	 */
	@Override
	public boolean add(E data) throws NullPointerException
	{
		if (data == null) {
			throw new NullPointerException();
		}
		
		// get the reference to the last elem
		Node prevLastElem = tail.getPrev();
		
		// initialize node and set prev point to ex-lastElem
		// and next point to tail
		Node lastElem = new Node(data, prevLastElem, tail );
		
		// set ex-lastElem's next to lastElem
		prevLastElem.setNext(lastElem);
		// set tail's prev to lastElem
		tail.setPrev(lastElem);
		
		// increase elements
		nelems++;
	
		return true; 
	}

	
	/**
	 * Retrieves the element stored with a given index on the list.
	 * 
	 * @param index The index of the desired element.
	 * @return The element stored in the Node with the desired index.
	 * @throws IndexOutOfBoundsException if index received is out of bounds for 
	 *             the current list. 
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		/*
		 * searches for the node at that index and returns the element
		 * contained in the index to local var elem.
		 */
		Node nodeWE = getNth(index);
		E elem = nodeWE.getElement();
		
		return elem;  
	}

	
	/**
	 * Sets the value of an element at a certain index in the list.
	 * 
	 * @param index Where in the list the data should be added.
	 * @param data Data to add.
	 * @return Element that was previously at this index.
	 * @throws IndexOutOfBoundsException if index received is out of bounds for 
	 *             the current list. 
	 * @throws NullPointerException if data received is null.
	 */
	@Override
	public E set(int index, E data) 
			throws IndexOutOfBoundsException,NullPointerException
	{
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (data == null) {
			throw new NullPointerException();
		}
		
		E prevElem = get(index);
		
		Node changeElem = getNth(index);
		
		changeElem.setElement(data);
		
		
		return prevElem; 
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices). 
	 * @throws IndexOutOfBoundsException if negative values or number equal
	 * or larger than size is found.
	 * @return the element that was removed from the list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		E storePrevElem;
		
		/*
		 * get the element to be removed and store it in the generic
		 * object storePrevElem
		 */
		Node removeThis = getNth(index);
		storePrevElem = removeThis.getElement();
		
		/*
		 * we then connect the previous and next nodes of the node
		 * to be removed
		 */
		removeThis.getPrev().setNext(removeThis.getNext());
		removeThis.getNext().setPrev(removeThis.getPrev());
		
		nelems--;
		
		return storePrevElem; 
	}

	
	
	/**
	 * Retrieves the index of the item passed as a parameter
	 * 
	 * @param Item whose index is to be retrieved
	 * @return index The index of the desired item, -1 if the item is not found.
	 * @throws NullPointerException if item passed is null
	 */
	@Override
	public int indexOf(Object o) throws NullPointerException {
		
		if (o == null){
			throw new NullPointerException();
		}
		
		int indexForTraverse = 0;
		int indexOfObj = -1;
		Node currNode = head.getNext();
		
		/*
		 * loops through the whole list and find if any element matches
		 * the parameter o. if so, store the index in indexOfObj
		 * and return the value to the method call.
		 * If after loop, no equal value is found, indexOfObj returns
		 * the value -1.
		 */
		while (indexForTraverse < size()) {
	
			if (currNode.getElement().equals(o)){
				indexOfObj = indexForTraverse;
				return indexOfObj;
			}
			else {
				
				currNode = currNode.getNext();
			}
			indexForTraverse++;
		}
		
		return indexOfObj; 
	}


	/**
	 * Returns true if this list contains the specified element,
	 * false otherwise.
	 * @param data to be searched in the list
	 * @return true if the data is in the list, false otherwise
	 * @throws NullPointerException if the data is null
	 */
	@Override
	public boolean contains(Object o) throws NullPointerException {
		
		Node searchForElem;
		boolean truth = false;
		
		if (o.equals(null)){
			throw new NullPointerException();
		}
		
		/*
		 * search through the list to find the element, if found
		 * return the boolean value to method call else return false
		 */
		for (int i=0; i<size(); i++){
			searchForElem = getNth(i);
			
			if (searchForElem.getElement().equals(o)){
				truth = true;
				
				return truth;
			}
		}
        
		return false; 
	}
	
	
	/**
	 * Removes the last occurrence of the specified element in this list,
	 * (when traversing the list from head to tail). 
	 * If the list does not contain the element, it is unchanged.
	 * @param data to be removed from the list
	 * @return true if the data is in the list, false otherwise
	 * @throws NullPointerException if the data is null
	 */
	public boolean removeLastOccurrence(Object o) throws NullPointerException 
	{
	
		if (o == null) {
			throw new NullPointerException();
		}
		
		int storeLastIndex = 0;
		Node getLast;
		boolean foundLast = false;
		int i = 0;
		
		/*
		 * loops through the list and check if there is such element.
		 * If so store true value in boolean variable and the index
		 * in storeLastIndex
		 */
		for (i = 0; i<size(); i++) {
			getLast = getNth(i);
			
			if (getLast.getElement().equals(o)){
				storeLastIndex = i;
				foundLast = true;
			}
			
		}
		
		/*
		 * proceeds to remove the element and return true to the 
		 * method call if the element is in the list
		 */
		if (foundLast){
			remove(storeLastIndex);
			return true;
		}
		
		return false; 
	}
	
	
	/** Clear the linked list */
	public void clear()
	{
		head.setNext(tail);
		tail.setPrev(head);
		
		nelems = 0;
	}

	
	/** 
	 * Determine if the list empty 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
		boolean testEmpty = false;
		
		if (size() == 0) {
			testEmpty = true;
		}
		else {
			testEmpty = false;
		}
		return testEmpty; 
	}

	
	public Iterator<E> QQQiterator()
	{
		return new MyListIterator();
	}
	
	
	public ListIterator<E> QQQlistIterator()
	{
		return new MyListIterator();
	}

	
	/**
	 *  Helper method to get the Node at the Nth index.
	 *  @return the Node that was found to method call.
	 */
	private Node getNth(int index) 
	{
		int indexToGo = index;
		int indexForTraverse = 0;
		Node currNode;
		
		/*
		 * if index is at middle of list or less than middle
		 * we start traversing from dummy header to get to the index.
		 * And when indexed node is found, return the node to method call.
		 */
			
		currNode = head.getNext();
			
		while (indexForTraverse != (indexToGo)) {
			currNode = currNode.getNext();	
			indexForTraverse++;
		}
			return currNode;
		  
	}




	/*  UNCOMMENT the following when you believe your MyListIterator class is
   functioning correctly
   */
   public Iterator<E> iterator()
   {
     return new MyListIterator();
   }
   public ListIterator<E> listIterator()
   {
     return new MyListIterator();
   }
	 
}

