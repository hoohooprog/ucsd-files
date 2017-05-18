package hw4;

import static org.junit.Assert.*;

import org.junit.*;


public class DoubleEndedLLTester {

	// different types of LL declarations
	private DoubleEndedLL<Integer> list4Ints;
	private DoubleEndedLL<Integer> emptyList;
	private DoubleEndedLL<Integer> listW1Int;
	private DoubleEndedLL<String> listWStrings;
	
	static final int DIM = 3;
	
	
	/**
	   * Standard Test Fixture. An empty list, a list with one entry (0) and 
	   * a list with several entries
	   */ 
	@Before
	public void setUp(){
		
		list4Ints = new DoubleEndedLL<Integer>();
	    listW1Int = new DoubleEndedLL<Integer>();
	    listWStrings = new DoubleEndedLL<String>();
	    emptyList = new DoubleEndedLL<Integer>();
	    
	    
	    listW1Int.addFirst(new Integer(0));
	    
	    list4Ints.addFirst(new Integer(3));
	    list4Ints.addLast(new Integer(1));
	    list4Ints.addLast(new Integer(0));
	    
	    listWStrings.addFirst(new String("added from first"));
        listWStrings.addLast(new String("added from last"));	    
		
	}
	
	
	//create tests by putting @Test before each test method
	/**
	 * testSize by checking whether each of the lists output the correct
	 * no. of elems
	 */
	@Test
	public void testSize(){
        
		assertEquals("List4Ints should have 3 elems", 3, 
				list4Ints.size());
		assertEquals("listW1Int should have 1 elem",1, listW1Int.size());
		assertEquals("emptyList should have 0 elem",0, emptyList.size());
		assertEquals("listWStrings should have 2 elems",2,listWStrings.size());
		
		// test size again upon alteration of list
		list4Ints.removeFirst();
		list4Ints.removeLast();
		assertEquals("list4Ints should only have 1 elem",1, list4Ints.size());
		
	}
	
	
	@Test
	public void testRemoveFirst(){
		
		//test if the first elem removed was the right element
		assertEquals("1st elem removed from listW1Int is 0", new Integer(0),
				listW1Int.removeFirst());
		assertEquals("1st elem removed from list4Ints is 3", new Integer(3),
				list4Ints.removeFirst());
		assertEquals("1st elem removed from listWStrings is added from first",
				new String("added from first"), listWStrings.removeFirst());
		
		// test exception
		try {
			emptyList.removeFirst();
			fail("should have generated exception");
		}
		catch(NullPointerException e){
			
		}
		
		
	}
	
	
	@Test
	public void testRemoveLast(){
		
		assertEquals("last elem removed from listW1Int is 0", new Integer(0),
				listW1Int.removeLast());
		
		assertEquals("last elem removed from list4Ints is 0", new Integer(0),
				list4Ints.removeLast());
		assertEquals("last elem removed from listWStrings is added from last",
				new String("added from last"), listWStrings.removeLast());
		
		//test exception
		try {
			emptyList.removeLast();
			
			fail("should have generated exception");
		}
		catch (NullPointerException e){
			
		}
		
	}

	
	// test if isEmpty() method returns right boolean value for
	// empty and non-empty lists
	@Test
	public void isEmpty(){
		
		assertTrue("emptyList shouldn have any elems",emptyList.isEmpty());
		assertTrue("listW1Int shdln be empty", !listW1Int.isEmpty());
		
	}
	
	
	
	@Test
	public void testAddFirst(){
		
		list4Ints.addFirst(10);
		
		assertEquals("added from front is 10", new Integer(10), 
				list4Ints.getHeadElement());
		
		emptyList.addFirst(3);
		
		assertEquals("added from front is 3", new Integer(3),
				emptyList.getHeadElement());
		
	}
	
	
	
	@Test
	public void testAddLast(){
        
		list4Ints.addLast(10);
		
		assertEquals("added from front is 10", new Integer(10), 
				list4Ints.getTailElement());
		
		emptyList.addLast(3);
		
		assertEquals("added from front is 3", new Integer(3),
				emptyList.getTailElement());
		
		
	}
	
}

