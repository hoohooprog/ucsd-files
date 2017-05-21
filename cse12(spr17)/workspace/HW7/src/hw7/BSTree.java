/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12wamf <<< --- Use your cs11f course-specific account name
 * Date: February 26, 2017
 * File: BSTree.java
 * Sources of Help: stackoverflow, piazza, misc websites describing 
 * 					BST.
 *
 * This program shows the functions of a BST and how the functions
 * work by replicating the methods that make the functions of BST work.
 * I also used plenty of recursive and helper methods that "iterates"
 * through the Binary Search Tree to find what Key or Information
 * that I want.
 *
*/

package hw7;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.lang.Math;
import java.util.Iterator;

public class BSTree<T extends Comparable<? super T>>{

	// number of nodes the tree has
	private int nelems;
	//to store the root which is connected to the rest of the tree
	private BSTNode root;

	
	/**
	 * Purpose: To store data in LinkedList called relatedInfo pertaining
	 * to a specific key. Contains public methods that allow users to
	 * get the key and/or data, left node or right node of key.
	 * Data can also be removed.
	 * @author Jingyi Tay
	 * @version 1.0
	 * @since February 26, 2017
	 */
	 public class BSTNode{

		/*
		 * variables that store the identifier to the Node(key),
		 * datas that Node has(relatedInfo) and nodes(if any) connected
		 * to the pertaining Node.
		 */
		T key;
		ArrayList<T> relatedInfo;
		BSTNode left;
		BSTNode right;
		
		
		/**
		* Purpose: Constructor that creates a Node to store key and LinkedList
		* containing data. Also points to child nodes
		* @param left is left child node
		* @param right is right child node
		* @param relatedInfo is LinkedList that has data
		* @param key is a Type of element that identifies this node
		* 
		*/
		public BSTNode( BSTNode left, BSTNode
				right, ArrayList<T> relatedInfo, T key){
			
			this.left=left;
			this.right=right;
			this.relatedInfo=relatedInfo;
			this.key=key;
		}
		
		
		/**
		 * A constructor that initializes BSTNode
		 * variables. Note: This constructor is used when you want
		 * to add a key with no related information yet. In
		 * this case, you must create an empty
		 * LinkedList for the node.
		 * @param left connects pertaining node to left child
		 * @param right connects pertaining node to right child
		 * @param key acts as identifier to pertaining node
		 */
		public BSTNode( BSTNode left, BSTNode
				right,T key){
			
			this.left=left;
			this.right=right;
			this.key=key;
			this.relatedInfo = new ArrayList<T>();
		}
		
		
		/**
		 * Returns the key
		 */
		public T getKey(){
			return key;
		}
		
		
		/**
		 * Returns the left child of the node
		 */
		public BSTNode getLeft(){
			return this.left;
		}
		
		
		/**
		 * Returns the right child of the node
		 */
		public BSTNode getRight(){
			return this.right;
		}
		
		
		/**
		 * Returns the LinkedList of the node
		 */
		public ArrayList<T> getRelatedInfo(){
			
			return relatedInfo;
		}
		
		
		/**
		 * Setter for left pointer of the node
		 */
		public void setLeft( BSTNode newLeft){
			left = newLeft;
		}
		
		
		/**
		 * Setter for right pointer of the node
		 */
		public void setRight( BSTNode newRight){
			right = newRight;
		}
		
		
		/**
		 * Setter for the LinkedList of the node
		 */
		public void setRelatedInfo( ArrayList<T>newInfo){
			relatedInfo = newInfo;
		}
		
		
		/**
		 * Append new info to the end of the existing
		 * ArrayList of the node
		 * You may use the ArrayListList APIs add method here.
		 */
		public void addNewInfo(T info){
			relatedInfo.add(info);
		}
		
		
		/**
		 * Remove info from the ArrayList of the node and return true.
		 * If the ArrayList does not contain the value info, return false
		 * You may use the ArrayList APIs remove method here.
		 * @param info is data that user wants to remove from key
		 * @return boolean is value that shows if data is removed
		 * from List of data.
		 */
		public boolean removeInfo(T info){
			// if arraylist doesn't contain info return false
			// else search through Node to find info, remove info and return
			// true
			
			if (!this.relatedInfo.contains(info)){
				return false;
			}
			else{
				for (int i=0; i<this.relatedInfo.size(); i++){
					if (relatedInfo.get(i) == info){
						relatedInfo.remove(i);
						break;
					}
				}
				return true;
			}
		}
		
	}

	 
	/**
	 * Constructor that makes a empty BST
	 */
	public BSTree(){
		root = null;
		nelems = 0;
	}
	
	
	/**
	 * returns the first node of BST or null if tree is empty
	 * @return the root node of type BSTNode
	 */
	public BSTNode getRoot(){
		if (nelems==0){
			return null;
		}
		else{
			return root;
		}
	}
	
	
	/**
	 * Returns the number of Nodes in BST
	 * @return number of Nodes in BST
	 */
	public int getSize(){
		return nelems;
	}
	
	
	/**
	 * addHelper is a recursive method that iterates through BST
	 * until leaf Node is found and adds a Node to either left or 
	 * right of leaf Node. If value of Key is bigger than 
	 * or equal to current Node's key, addHelper iterates to right of 
	 * current Node, else addHelper iterates to left of current Node until
	 * leaf node is found. 
	 * @param currRoot is the node that is being recursively set, starting
	 * from root
	 * @param key is the key that is to be added to a new leaf Node
	 */
	private void addHelper(BSTNode currRoot, T key){
		
		int value = key.compareTo(currRoot.getKey());
		
		// So no duplicate key is added
		if (value==0){
			return;
		}
		
		//if key to insert is smaller than currRoot's key
		if (value<0){
			if (currRoot.getLeft()==null){
				currRoot.setLeft(new BSTNode(null,null,key));
				//System.out.println(currRoot.getLeft().getKey());
				nelems++;
			}
			else{
				//continue leftwards recursively
				addHelper(currRoot.getLeft(),key);
				
			}
		}
		else{
			if (currRoot.getRight()==null){
				currRoot.setRight(new BSTNode(null,null,key));
				//System.out.println(currRoot.getRight().getKey());
				nelems++;
			}
			else{
				//continue rightwards recursively
				addHelper(currRoot.getRight(),key);
			}
		}
	}
	
	
	/**
	 * Inserts a key into the BST.Throws NullPointerException if key is null.
	 * Note: This method would insert a node with the key and an empty 
	 * LinkedList into the tree.
	 * @param key serves as identifier of new leaf node
	 * @throws NullPointerException
	 */
	public void insert(T key) throws NullPointerException{
		
		if (key == null){
			throw new NullPointerException();
		}
		
		/*
		 * if tree is empty, create root node
		 */
		if (nelems==0){
			
		    BSTNode left=null;
		    BSTNode right=null;
		
		    root = new BSTNode(left,right,key);
		    nelems++;
		    
		}
		else{
			
			addHelper(root,key);
			
		}
		
	}
	

    /**
     * findHelper is a method that recursively iterates through the tree
     * to find the right key; if key is not right and smaller than current
     * Node's key, tree recursively iterates to the left, else iterates
     * to right until Node is found.
     * @param currRoot is the Node that has key to be compared with.
     * Starting from root Node
     * @param key is a Type of identifier that is compared with
     * currRoot's Key
     * @return boolean value to show if node with key is found
     */
	private boolean findHelper(BSTNode currRoot, T key){
		
		int value = key.compareTo(currRoot.getKey());
		boolean keyvalue;
		
		if (value==0){
			keyvalue = true;
			return keyvalue;
		}
		
		if (value<0){
			
			if (currRoot.getLeft()==null){
				return false;
			} 
			else if (currRoot.getLeft().getKey() == key){	
				return true;	
			}
			else{
				keyvalue = findHelper(currRoot.getLeft(),key);
				
				return keyvalue;
			}
		}
		else{
			if (currRoot.getRight()==null){
				return false;
			}
			else if (currRoot.getRight().getKey() == key) {
				return true;
			}
			else{
				keyvalue = findHelper(currRoot.getRight(),key);
				
				return keyvalue;
			}
		}
	}
	
	
	/**
	 * Return true if the key is found in the tree, false otherwise.
	 * Throw NullPointerException if key is null
	 * @param key is key that user wants to find
	 * @return true or false values to indicate if key is found
	 * @throws NullPointerException
	 */
	public boolean findKey(T key) throws NullPointerException{
		
		boolean keyFound = false;
		
		if (key==null){
			throw new NullPointerException();
		}
		
		if (getSize()==0){
			return false;
		}
		else{
			
			if (root.getKey() == key){
				return true;
			}
			else{
			
				// enters recursive helper method and stores
				// result in keyFound
				keyFound = findHelper(root,key);
				return keyFound;
			}	
		}
		
	}
	
    /**
     * Is a helper method that inserts data by recursively iterates
     * through BST until correct Key is found then data is inserted
     * @param currRoot is the current Node that is being compared to;
     * starts from root and moves down the tree until correct Node
     * is found.
     * @param key is the key that user wants to find
     * @param info is data that user wants to insert
     */

	private void insertInfoHelper(BSTNode currRoot, T key, T info){
		
		int value = key.compareTo(currRoot.getKey());
		
		if (value==0){
			
			currRoot.addNewInfo(info);
			return;
		}
		
		if (value<0){
			if (currRoot.getLeft()==null){
				return;
			}
			else{
				insertInfoHelper(currRoot.getLeft(),key, info);
			}
		}
		else{
			if (currRoot.getRight()==null){
				return;
			}
			else{
				insertInfoHelper(currRoot.getRight(),key,info);
			}
		}
	}
	
	/**
	 * Inserts info into the LinkedList of the node whose key is key.
	 * Throw NullPointerException if key or info is null
	 * Throw IllegalArgumentException if key is not found in the BST
	 */
	public void insertInformation(T key, T info) throws NullPointerException, 
	    IllegalArgumentException{
		
		if (key==null || info==null){
			throw new NullPointerException();
		}
		
		if (!findKey(key)){
			throw new IllegalArgumentException();
		}
		else if (findMoreInformation(key).contains(info)){
			return;
		}
		else{
			insertInfoHelper(root,key,info);
		}
		
	}
	
	/**
	 * Helper method that finds the List of data for the particular key
	 * by comparing the key to currRoot's key. If is same, returns
	 * the List of data, else recursively iterates to the left if 
	 * key is smaller than current Node's key; else iterates right.
	 * @param currRoot is the Node whose key is being compared to
	 * @param key is the identity of the Node user wants to find
	 * @return List of data associated with the key of the Node
	 */
	private ArrayList<T> findMoreInfoHelper(BSTNode currRoot, T key){
		
	    ArrayList<T> storeInfo = null;
		int value = key.compareTo(currRoot.getKey());
		
		if (value==0){
			storeInfo = currRoot.getRelatedInfo();
			return storeInfo;
		}
		
		if (value<0){
			if (currRoot.getLeft()==null){
				return storeInfo;
			}
			else{
				storeInfo = findMoreInfoHelper(currRoot.getLeft(),key);
				
				return storeInfo;
			}
		}
		else{
			if (currRoot.getRight()==null){
				return storeInfo;
			}
			else{
				storeInfo = findMoreInfoHelper(currRoot.getRight(),key);
				
				return storeInfo;
			}
		}
		
	}
	
	/**
	 * Return the LinkedList of the node with key value key
	 * Throw NullPointerException if key is null
	 * Throw IllegalArgumentException if key is not
	 * found in the BST 
	 * @param key of Node to be found
	 * @return List of data
	 * @throws NullPointerException if key entered is null
	 * @throws IllegalArgumentException if key is not in list
	 */
	public ArrayList<T> findMoreInformation(T key) throws NullPointerException, 
	IllegalArgumentException{
		
		ArrayList<T> storeInfo = null;
		
		if (key == null){
			throw new NullPointerException();
		}
		
		
		 if (!findKey(key)){
			throw new IllegalArgumentException();
		 }
		
		
	    storeInfo = findMoreInfoHelper(root, key);
			
		return storeInfo;
		
		
	}
	
	
	/**
	 * Returns the height of the tree. The height of a
	 * tree is the length of the longest downward path
	 * to a leaf from the root.
	 * By convention, height of an empty tree is -1
	 * and the height of the root is 0. 
	 * @return a integer type which indicates empty tree(-1),
	 * tree of 1 node(1) or height of tree
	 */
	/*
	public int findHeight(){
		
		int height;
		
		if (getSize() == 0){
			return -1;
		}
		else if (getSize() == 1){
			return 0;
		}
		else{
			height = (int)(Math.log10((double)getSize()) 
					/ Math.log10((double)2));
			
			return height;
		}
	}
	
	*/
	/**
	 * Helper method that finds the leaves of a tree by traversing down
	 * a tree and adding to the count if a leaf is spotted
	 * @param currRoot is normally the root node of tree
	 * @return the number of leafs in the Tree
	 */
	/*
	private int findLeafHelper(BSTNode currRoot){
	
	
		int leafCount=0;
		
		if (getSize()==0){
			return leafCount;
		}
		if (getSize()==1){
			return leafCount++;
		}
	
		if (currRoot.getLeft()==null){
			leafCount++;
			return leafCount;
		}
		else{
			leafCount = findLeafHelper(currRoot.getLeft())+1;
		}
		
		if (currRoot.getRight()==null){
			leafCount++;
			return leafCount;
		
		}
		else{
			leafCount = findLeafHelper(currRoot.getRight())+1;
		}

		return leafCount;
	}
	
	*/
	/**
	 * method that returns the number of leaves that the tree has
	 * @return the number of leaves that a tree has
	 */
	/*
	public int leafCount(){
		
		return findLeafHelper(root);
		
	}
	*/
	/**
	 * Iterator that iterates through the tree to find Key.
	 * Uses a Stack to store the number of nodes it has,
	 * from the biggest at the bottom to the smallest at the top.
	 * So removal is in sorted order.
	 * 
	 * @author Jingyi Tay
	 * @version 1.0
	 * @since February 26,2017
	 */
/*
	public class BSTree_Iterator implements Iterator{
		
		Stack<BSTNode> storeBSTNode;
		int numOfNodes = getSize();
*/		
		
		/**
		 * Constructor that initializes the Stack with the
		 * leftPath of the root
		 */
	/*
		public BSTree_Iterator(){
			
			storeBSTNode = new Stack<BSTNode>();
			
			BSTNode currNode = getRoot();
			
			
			if (getSize()!=0){
				getLeftHelper(currNode);
			}
			
		}
		
		*/
		/**
		 * Helper method that gets the left node of the node
		 * in the parameter.
		 * @param node that left node is gotten from
		 */
/*
		private void getLeftHelper(BSTNode node){
			
						
			if (node !=null){
				storeBSTNode.push(node);
				System.out.println("stack contains " + node.getKey() 
				+ storeBSTNode.contains(node));
								
				node = node.getLeft();
				getLeftHelper(node);
				
			}
			else{ 	
				return; }
		}
		
	*/	
		/**
		 * Returns false if the Stack is empty i.e. no more
		 * nodes left to iterate, true otherwise
		 * @return boolean values that indicate if there is
		 * still Nodes to be popped from stack.
		 */
	/*
		public boolean hasNext(){
			boolean stackEmpty=false;
			if (storeBSTNode.empty() == false){
				stackEmpty = true;
			}
			return stackEmpty;
		}
		
	*/	
		/**
		 * Returns the next item in the BST. Throws
		 * NoSuchElementException if there is no next item
		 * @return key of next Node
		 * @throws NoSuchElementException if there's no more
		 * elements left in the stack.
		 */
	/*
		public T next() throws NoSuchElementException{
			
			if (!hasNext()){
				throw new NoSuchElementException();
			}
			
			BSTNode currNode = null;
			BSTNode parentNode = null;
			
			System.out.println(storeBSTNode.peek().getKey());
			
			currNode = storeBSTNode.pop();
			getLeftHelper(currNode.getRight());
			
			return currNode.getKey();
		}
					
		
	}
	
	public Iterator<T> iterator() {
		return new BSTree_Iterator();
		}
		*/
}
