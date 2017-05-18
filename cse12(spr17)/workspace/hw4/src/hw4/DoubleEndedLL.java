package hw4;
/**
 * Name: Jingyi Tay
 * Login: cs12wamf
 * Date: 2/4/2017
 * File: DoubleEndedLL.java
 * Sources of Help: piazza, write-up
 * 
 * Double Ended Singly Linked List serves as the data structure for
 * a stack ADT called MyStack for storing its elements and provides
 * functions for it.
 */

//singly LL that includes head and tail pointers
public class DoubleEndedLL<T> implements DoubleEndedLLInterface<T> {


	// No. of items in the list
	private int nelems;   
	
	//Nodes that represent the dummy(sentinel) nodes
	//at the front and back of the linkedlist
	private Node head;
	private Node tail;
	
	
  //Implementation of the SinglyLinkedList Class
	/**
	 * Creates a new, empty singly-linked list.
	 */
	public DoubleEndedLL()
	{
		head = new Node(null);
		tail = new Node(null);
		
		head.setNext(tail);
		
		nelems = 0;
				
	}
	
	
	/** Checks if the list is empty
	   * @return returns true if the list is empty, false otherwise
	   */
	public boolean isEmpty(){
		
		return (size()==0);
	}
	
	
	/** Checks the size of the list
	   * @return returns the number of elements in the list
	   */
	public int size(){
		
		return nelems;
	}
	
	
	/** Adds a new node to the front of the list(==push)
	   * @param newItem - an element to add
	   */
	@Override
	public void addFirst(T newItem){
		
		// assign a node to new element
		Node elem2Add2Head = new Node(newItem);
		
		// if LL is not empty, assign new Node to head
		if (head.getElement() != null){
			
			elem2Add2Head.setNext(head);
			
			head = elem2Add2Head;
			
		}
		else { head = elem2Add2Head; }
		
		// if list only has 1 elem, hence tail elem is null
		// reference tail to new Node
		if (tail.getElement() == null){
			tail = elem2Add2Head;
		}
		
		// update size
		nelems++;
	}
	
	
	/**
	 * Gets the 1st element of LL.
	 * @return The element stored in the 1st node of LL
	 */
	public Node getHead(){
		return head;
	}
	
	
	/**
	 * Gets the last element of LL
	 * @return the element stored in the last node of LL
	 */
	public T getTailElement(){
		return tail.getElement();
	}
	
	
	/** Adds a new node to the end of the list
	   * @param newItem - an element to add
	   */
	@Override
	public void addLast(T newItem){
		
		// store new ELement in new Node
		Node elem2Add2Tail = new Node(newItem);
		// reference head Node with new Node
		Node ptr2OldLast = head;
		
		// set current tail node's next node to point to Node with new elem
		tail.setNext(elem2Add2Tail);
		//then set tail to reference Node with new elem 
		tail = elem2Add2Tail;
		
		// "shuffle" through the indexes and find the previous
		// last elem, starting from head node and searching subsequent
		// nodes for their next nodes and store the node in
		// the local Node that originally referenced head Node.
		// we loop till the 2nd index before the last to get the
		// original last node
		for (int i=0; i<size()-1; i++){
			
			ptr2OldLast = ptr2OldLast.getNext();
		}
		
		// set the original last Node's next Node to Node with new elem
		ptr2OldLast.setNext(elem2Add2Tail);
		
		// but if head's elem is null means LL only has 1 elem,
		// so reference head to Node with new elem also
		if (head.getElement()==null){
			head = elem2Add2Tail;
		}
	
		//update size
		nelems++;
	}
	
	
	/** Removes a node from the beginning of the list(==pop)
	   * @return element the data found
	   * @throws NullPointerException if the list is empty
	   */
	@Override
	public T removeFirst(){
		
		T getHeadElem;
		
		if (!isEmpty()) {
			
			getHeadElem = getHeadElement();
			
			head = head.getNext();
			
			nelems--;
			
			return getHeadElem;
			
		}
		else {
			throw new NullPointerException();
		}
	}
	
	public T getHeadElement(){
		return getHead().getElement();
	}
	
	
	/** Removes a node from the end of the list
	   * @return element the data found
	   * @throws NullPointerException if the list is empty
	   */
	@Override
	public T removeLast(){
		
		T getTailElem;
		Node refLastNode = head;
	
		if (!isEmpty()) {
			
			getTailElem = tail.getElement();
			
			
			for (int i=0; i<size()-2; i++){
				refLastNode = refLastNode.getNext();
			}
			
			tail = refLastNode;
			
			nelems--;
			
			return getTailElem;
			
		}
		else{
			throw new NullPointerException();
		}
		
		
		
	}

	
	/**
	 * Purpose: To store data input by user and to link up the nodes together
	 * to form a singly linked list. Contains methods to set which nodes current
	 * node points to, element to set in the index, removal of current node,
	 * getting the next node and the element in the current node.
	 * @author Jingyi Tay
	 * @version 1.0
	 * @since January 26, 2017
	 */
		protected class Node {
	
			/* these are the variables that exist in the node
			 * data stores the element and next is a reference to the next 
			 * node object. No prev nodes because list is singly linked
			 */
			T data;
			Node next;
	
			
		   /**
			* Purpose: creates a simpleton Node to store element. Node doesnt
			* points to any other nodes.
			* @param element of any type that user wants to store
			*/
			public Node(T element)
			{
				// initialize node to store element in data
				data = element;
				next = null;
				
			}
			
			
			/** 
			 * Constructor to create singleton link it between previous and next 
			 * @param element Element is to add, can be null
			 * @param nextNode is successor Node, can be null 
			 */
			public Node(T element, Node nextNode)
			{
				// use setPrev(Node p) and setNext(Node n) to set Node's
				// Predecessor to PrevNode and successor to nextNode
				data = element;
				setNext(nextNode);
				
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
			public void setElement(T e)
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
			 * Accessor to get the Node's Element.
			 * @return the element stored in object data 
			 */
			public T getElement()
			{
				return this.data;
			} 
		}
}
