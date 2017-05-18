package hw4;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyStackTester {

	private MyStack<Integer> stackOfInts = new MyStack();
	
	
	@Before
	public void setUp(){
		
		stackOfInts.addElement(new Integer(4));
		stackOfInts.addElement(new Integer(0));
		stackOfInts.addElement(new Integer(1));
	}
	
	
	@Test
	public void testAddElement(){
		
		assertEquals("next element in stack should be 0", new Integer(0), 
				stackOfInts.peek());
		
		assertEquals("size of stack should be 3", 3, stackOfInts.size());
		
		
	}
	
	
	@Test
	public void testIsEmpty(){
		
		assertTrue("stack is not empty now", !stackOfInts.isEmpty());
		
		stackOfInts.removeElement();
		assertTrue("stack is not empty now", !stackOfInts.isEmpty());
		stackOfInts.removeElement();
		assertTrue("stack is not empty now", !stackOfInts.isEmpty());
		
		stackOfInts.removeElement();
		assertTrue("stack is empty now", stackOfInts.isEmpty());
	}
	
	
	@Test
	public void testPeek(){
		
		assertEquals("next element in stack should be 0", new Integer(0), 
				stackOfInts.peek());
		
	    assertEquals("element removed is 1", new Integer(1), 
	    		stackOfInts.removeElement());
	    
	    assertEquals("next element in stack should be 4",
	    		new Integer(4), stackOfInts.peek());
	    
	    assertEquals("element removed is 0", new Integer(0),
	    		stackOfInts.removeElement());
		
	}
	
	
	@Test
	public void testRemoveElement(){
		
		assertEquals("next element in stack should be 0", new Integer(0), 
				stackOfInts.peek());
		
	    assertEquals("element removed is 1", new Integer(1), 
	    		stackOfInts.removeElement());
	    
	    assertEquals("next element in stack should be 4",
	    		new Integer(4), stackOfInts.peek());
	    
	    assertEquals("element removed is 0", new Integer(0),
	    		stackOfInts.removeElement());
	}
	
	
	@Test
	public void testSize(){
		
		assertEquals("next element in stack should be 0", new Integer(0), 
				stackOfInts.peek());
		
		assertEquals("size should be 3", 3, stackOfInts.size());
		
	    assertEquals("element removed is 1", new Integer(1), 
	    		stackOfInts.removeElement());
	    
	    assertEquals("size should be 2", 2, stackOfInts.size());
	    
	    assertEquals("next element in stack should be 4",
	    		new Integer(4), stackOfInts.peek());
	    
	    assertEquals("element removed is 0", new Integer(0),
	    		stackOfInts.removeElement());
	    
	    assertEquals("size should be 1", 1, stackOfInts.size());
	    
	    assertEquals("Element removed should be 4", new Integer(4),
	    		stackOfInts.removeElement());
	    
	    assertEquals("size should be 0", 0, stackOfInts.size());
	}
	
	
}
