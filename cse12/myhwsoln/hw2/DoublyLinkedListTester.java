/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12wamf <<< --- Use your cs11f course-specific account name
 * Date: January 26, 2017
 * File: DoublyLinkedListTester.java
 * Sources of Help: stackoverflow, piazza, misc websites describing 
 * 					linkedlists.
 *
 * This program uses test cases to test each methods of the 
 * implementation of the doubly linked list methods in 
 * DoublyLinkedList.class
 *
*/

package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedListTester
{
  private DoublyLinkedList<Integer> empty ;
  private DoublyLinkedList<Integer> one ;
  private DoublyLinkedList<Integer> several ;
  private DoublyLinkedList<String>  slist ;
 
  static final int DIM = 5;
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new DoublyLinkedList<Integer>();
    one = new DoublyLinkedList<Integer>();
    one.add(0,new Integer(0));
    several = new DoublyLinkedList<Integer>() ;
    
    // List: 1,2,3,...,Dim
    for (int i = DIM; i > 0; i--) {
      several.add(0,new Integer(i));
    }
    
    // List: "First","Last"
    slist = new DoublyLinkedList<String>();
    slist.add("First");
    slist.add("Last");      
  }
  
  
  /** Test if heads of the lists are correct */
  @Test
  public void testGetHead()
  {
    assertEquals("Check 0",new Integer(0),one.get(0)) ;
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
    assertEquals("Check 0",new String("First"), slist.get(0));
  }
  
  
  /** Test if size of lists are correct */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size",0,empty.size()) ;
    assertEquals("Check One Size",1,one.size()) ;
    assertEquals("Check Several Size",DIM,several.size()) ;
  }
  
  
  /** Test setting a specific entry */
  @Test
  public void testSet()
  {
    slist.set(1,"Final");
    assertEquals("Setting specific value", "Final",slist.get(1));
    
  }
  
  /** test of removing Node and catching exceptions occurred*/
  @Test
  public void testRemove()
  {  
	  assertEquals("got Last", "Last", slist.remove(1));
	  
	  try {
		  several.remove(-1);
		  
		  fail("Should have generated an exception");
	  }
	  catch(IndexOutOfBoundsException e){
		  
	  }
	  
	  try {
		  several.remove(5);
		  
		  fail("Should have generated an exception");
		 
	  }
	  catch(IndexOutOfBoundsException e){
		  
	  }
	  
	  assertTrue("3 got removed", several.removeLastOccurrence(2));
  }
  
  /** test the removing of last occurring element and the exceptions
   * that occurs with it.
   */
  @Test
  public void TestRemoveLastOccurrence() {
	  several.add(2);
	  several.add(0,2);
	  
	  assertEquals("list doesn't contain 10",several.contains(10), 
			  several.removeLastOccurrence(10));
	  
	  try {
		  several.removeLastOccurrence(null);
		  
		  fail("Should have generated an exception");
	  }
	  catch(NullPointerException e){
		  
	  }  
	  several.removeLastOccurrence(2);
	  
	  Integer a = new Integer(2);
	  
	  assertEquals("last 2 was removed", a, several.get(several.size()-1));
	  
	  
  }
  
  /** test if iterator's set method and its exceptions are working */
  @Test
  public void testIterSet(){
	  ListIterator iter4Several = several.listIterator();
	  ListIterator iter4Empty = empty.listIterator();
	  
	  try {
		  iter4Several.set(null);
		  
		  fail("Should have generated an exception");
	  }
	  catch(NullPointerException e){
		  
	  }
	  
	  try {
		  iter4Empty.set(10);
		  
		  fail("Should have generated an exception");
	  }
	  catch(IllegalStateException e){
		  
	  }
	  
	  try {
		  iter4Several.add(4);
		  
		  iter4Several.set(8);
		  
		  fail("Should have generated an exception");
	  }
	  catch(IllegalStateException e){
		  
	  }
	  
	  try{
		  iter4Several.next();
		  iter4Several.remove();
		  
		  iter4Several.set(0);
		  
		  fail("Should have generated an exception");
	  }
	  catch(IllegalStateException e){
		  
	  }
  }
  
  
  /** test iterator's add and add's exception */
  @Test
  public void testIterAdd(){
	  ListIterator iter4Several = several.listIterator();
	  
	  try {
		  iter4Several.add(null);
		  
		  fail("Shoud have generated an exception");
	  }
	  catch(NullPointerException e){
		  
	  }
	  
	  iter4Several.add(3);
	  
	  assertEquals("2nd element, index 1 in Several is 1", 
			  1, iter4Several.next());
	  assertEquals("2nd element, index 1 in Several is 1",
			  1, iter4Several.previous());
	  assertEquals("1st element, index 0 in Several is 3",
			  3, iter4Several.previous());
	  
	  iter4Several.add(5);
	  
	 
	  
	  Integer a = new Integer(1);
	  
	  assertEquals("2nd element in Several is 1", a, several.get(2) );
	  
	  ListIterator iter4Empty =  empty.listIterator();
	  
	  iter4Empty.add(8);
	  
	  assertTrue("empty now contains value 8", empty.contains(8));  
	  
  }
  
  /** test iterator's hasNext method */
  @Test
  public void testIterHasNext() {
	  
	  ListIterator iter4Empty = empty.listIterator();
	  
	  assertTrue("empty does not have next", !iter4Empty.hasNext());
	  
	  ListIterator iter4Several = several.listIterator();
	  
	  assertTrue("several has next", iter4Several.hasNext());
	  
  }
  
  
  /** test iterator's hasPrev method and its exceptions */
  @Test
  public void testIterHasPrev() {
    
	  ListIterator iter4Empty = empty.listIterator();
	  
	  assertTrue("empty does not have previious", !iter4Empty.hasPrevious());
	  
	  ListIterator iter4Several = several.listIterator();
	  
	  assertTrue("several does not have previous", !iter4Several.hasPrevious());
	  
	  Integer a = (Integer) iter4Several.next();
	  
	  assertTrue("several has previous", iter4Several.hasPrevious());
  }
  
  
  /** test iterator's next method and its exceptions */
  @Test
  public void testIterNext() {
	  ListIterator iter4Empty = empty.listIterator();
	  
	  try {
		  iter4Empty.next();
		  
		  fail("Should have generated an exception");
	  }
	  catch(NoSuchElementException e){
		  
	  }
	  
	  ListIterator iter4Several = several.listIterator();
	  
	  assertEquals("several's next element is 1",1 ,iter4Several.next());
  }
  
  
  /** test iterator's previous method and its exceptions */
  @Test
  public void testIterPrevious(){
	  ListIterator iter4Empty = empty.listIterator();
	  
	  try{
		  iter4Empty.previous();
		  
		  fail("Should have generated an exception");
	  }
	  catch(NoSuchElementException e){
		  
	  }
	  
	  ListIterator iter4Several = several.listIterator();
	  Integer storeElem;
	  
	  storeElem = (Integer)iter4Several.next();
	  
	  assertEquals("several has a previous element of 1", 1, iter4Several.previous());
	  
  }
  
   
  /** test iterator's finding of previous index method and its exceptions*/
  @Test
  public void testIterPrevIndex(){
	  ListIterator iter4Empty = empty.listIterator();
	  
	  int prevIndex;
	  
	  assertEquals("empty doesn't have prev index, hence displays -1",
			  -1, iter4Empty.previousIndex());
	  
	  ListIterator iter4SList = slist.listIterator();
	  
	  // move to next indexes first before finding if prev exists
	  // move to index containing "First"
	  prevIndex = iter4SList.nextIndex();
	 
	  assertEquals("next will be index 0", 0, 
			  iter4SList.nextIndex());
	  //now get previous index
	  assertEquals("no previous index, hence displays -1",
			  -1, iter4SList.previousIndex());
	  
  }
  
  
  /* test iterator's finding of next index and its exceptions */
  @Test
  public void testIterNextIndex(){
	  ListIterator iter4Empty = empty.listIterator();
	  
	  int nextIndex;
	  
      nextIndex = iter4Empty.nextIndex();
      
      assertEquals("empty does not have next index, hence displays size 0",
    		  0, nextIndex);
      
      ListIterator iter4SList = slist.listIterator();
      
      assertEquals("Slist has 2 elements, hence at index 0",0, 
    		  nextIndex = iter4SList.nextIndex());
      
  }
  
  
  /** test iterator's removal of elements and its exceptions */
  @Test
  public void testIterRemove() {
	  
	ListIterator iter4Several = several.listIterator();
	Integer ElemIndex;
	
	try{
		iter4Several.remove();
		
		fail("Should have generated an exception");
	}
	catch(IllegalStateException e){
		
	}
	//Point to next index
	ElemIndex = (Integer)iter4Several.next();
	//remove element
	iter4Several.remove();
	
	assertTrue("since element at index 0 is removed, hasPrev returns false",
			!iter4Several.hasPrevious());
	
	//move forward*2;
	ElemIndex = (Integer)iter4Several.next();
	ElemIndex = (Integer)iter4Several.next();
	
	//move backward
	ElemIndex = (Integer)iter4Several.previous();
	iter4Several.remove();
	
	assertEquals("Next Index is 1", 1, iter4Several.nextIndex());
	
  }
  
  /** test if list can be cleared */
  @Test
  public void testClear(){
	  slist.clear();
	  
	  assertEquals("slist has size 0", 0, slist.size());
  }
  
  
  /** Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
  }
  
  
  /** test contains method */
  @Test
  public void testContains(){
	  
	  assertTrue("several contains 2", several.contains(2));
	  
	  assertTrue("several contains 4", several.contains(4));
	  
	  try {
		  several.contains(null);
		  
		  fail("Should have generated an exception");
	  }
	  catch(NullPointerException e){
		  
	  }
	  
	  assertTrue("several does not contain 0", !several.contains(0));
  }
  

  /** test list's add(index,data) method and its exceptions*/
  @Test
  public void testLLAdd2Param() {
	  
	  several.add(1,5);
	  Integer a = new Integer(5);
	  assertEquals("elem @ index 1 is 5", a, several.get(1));
	  
	   try {
	      several.add(100,5);
	      // This is how you can test when an exception is supposed 
	      // to be thrown
	      fail("Should have generated an exception");  
	   }
	   catch(IndexOutOfBoundsException e) {
	      //  normal
	   }
	   
	   try {
		   several.add(-10,5);
		   
		   fail("Should have generated an exception");
	   }
	   catch(IndexOutOfBoundsException e) {
		   
	   }
	  
	   try {
		   several.add(1,null);
		   
		   fail("Should have generated an exception");
	   }
	   catch(NullPointerException e) {
		   
	   }
	  
	  
	  several.add(3,5);
	  a = 3;
	  assertEquals("elem @ index 4 is 3",a, several.get(4));
	  
	  for(int i=0; i<25; i++){
			several.add(i,i);
			
		}
	  
  }
  
  
  /** test list's add(element) method to add to end of list
   * and its exceptions.
   */
  @Test
  public void testAdd2End() {
	  // test for null data
	  try {
		  several.add(null);
		  
		  fail("Should have generated an exception");
	  }
	  catch(NullPointerException e) {
		  
	  }
	  
	  // test for negative num
	  several.add(-1);
	  Integer a = new Integer(-1);
	  Integer lastElem = several.get((several.size()-1));
	  
	  assertEquals("last index is -1", a, lastElem);
  }
  
  
  /** test indexOf method */
  @Test
  public void testIndexOf() {
	   
	 assertEquals("index is right", 0, slist.indexOf("First"));
  }
  
  
  /** Test out of bounds exception on get */
  @Test
  public void testGetException()
  {
    try 
    {
      empty.get(0);
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }

  
  /** Test iterator on empty list and several list */
  @Test
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  }
}
