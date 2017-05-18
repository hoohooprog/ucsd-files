package hw4;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class BoundedDequeTester{
	
	static final int DIM = 5;
	private Deque12 empty;
	private Deque12 several;
	

	@Before
	public void setUp()
	{
	        
		empty = new Deque12(0);
	    
	    several = new Deque12(12);
	   
	}
	
	@Test
	public void testCapacity(){
		
		assertEquals("several has a capacity of 12",12, several.capacity());
		assertEquals("empty has a capacity of 0", 0, empty.capacity());
		
	}
	
	@Test
	public void testSize(){
		assertEquals("several has a size of 0", 0, several.size());
		assertEquals("empty has a size of 0", 0, empty.size());
	}
	
	
	
	@Test
	public void addFront2Deque(){
		assertEquals("empty asserts null pointer", false, 
				empty.addFront(null) );
		
		try{
			several.addFront(null);
		}catch( NullPointerException e){
			
		}
		
		assertEquals("empty returns false",false, empty.addFront(10));
		
		for (int i=0; i<several.capacity(); i++){
			several.addFront(i);
		}
		
		assertTrue("several returns false", !several.addFront(3));
		assertTrue("several returns false", !several.addFront(null));
		
		
	}
	
	
	@Test
	public void addBack2Deque(){
		
		try{
			empty.addBack(null);
		}catch(NullPointerException e){
			
		}
		
		assertTrue("returns false, size==capacity", !empty.addBack(3));
		
		for (int i=0; i<several.capacity(); i++){
			
			assertTrue( "element at index i is i", several.addBack(i) );
		}
		
		assertTrue("addback returns false, size == cap", !several.addBack(10));
		assertTrue("addback returns false, size == cap", 
				!several.addBack(null));
	}
	
	
	@Test
	public void removeBackDeque(){
		
		assertEquals("empty returns null upon removeBack", null, 
				empty.removeBack());
		
		for (int i=several.capacity()-1; i>=0; i--){
			several.addFront(i);
		}
		
		for (int i=several.size()-1; i>= 0; i--){
			Object removed = several.removeBack();
			
			//System.out.println(removed);
			assertEquals("several returns i elem upon removeback", i, removed);
		}
		
		assertEquals("several returns null as size = 0", null, several.removeBack());
		
	}
	
	
	@Test
	public void removeFront2Deque(){
		
		Object frontElem;
		
		assertEquals("removefront returns null for empty", null,
				empty.removeFront());
		
		for (int i=several.capacity()-1; i>=0; i--){
			several.addFront(i);
			frontElem = several.peekFront();
			System.out.println(frontElem);
		}
		System.out.println();
		
		System.out.println("size of several " + several.size());
		
		System.out.println();
		
		//can't use size as condition because size keeps decreasing
		for (int i=0; i<several.capacity(); i++){
			Object removed = several.removeFront();
			
			System.out.println(removed);
			assertEquals("several returns i elem upon removeback", i, (int)removed);
		}
		
		several.removeFront();
		
		assertEquals("removefront returns null for several", null,
				several.removeFront());
	}
	
	
	@Test
	public void peekFront2Deque(){
		assertEquals("removefront returns null for empty", null,
				empty.peekFront());
		
		for (int i=several.capacity()-1; i>=0; i--){
			several.addFront(i);
			assertEquals("front elem is i ", i, several.peekFront());
		}
		
		for (int i=0; i<several.capacity(); i++){
			
			assertEquals("several returns i elem upon removeback", i, several.peekFront());
			Object removed = several.removeFront();
			
			System.out.println("several has size " + several.size());
			
		}
	}
	
	
	@Test
	public void peekBack2Deque(){
		assertEquals("removefront returns null for empty", null,
				empty.peekBack());

		for (int i=0; i<several.capacity(); i++){
			several.addBack(i);
			
			assertEquals( "last is i", i, several.peekBack() );
		}
		
		for (int i=several.capacity()-1; i>=0; i--){
			
			assertEquals("back elem is i ", i, several.peekBack());
			several.removeBack();
			
		}
	}
	 
}
