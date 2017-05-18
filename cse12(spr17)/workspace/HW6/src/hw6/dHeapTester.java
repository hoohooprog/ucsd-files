package hw6;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException; 

public class dHeapTester {

	dHeap emptyConst = new dHeap<Integer>();
	dHeap oneArg = new dHeap<Integer>(10);
	dHeap dHeapArr = new dHeap<Integer>(3,12, true);
	dHeap dHeapMin = new dHeap<Integer>(4,15,false);
	
	@Before
	public void setup(){
		
		emptyConst.add(new Integer(0));
		emptyConst.add(new Integer(1));
		emptyConst.add(new Integer(2));
		
		for (int i=0; i<10; i++){
			oneArg.add(i);
		}
		
		for (int i=0; i<12; i++){
			dHeapArr.add(i);
		}		
		
		for (int i=0; i<15; i++){
			dHeapMin.add(i);
		}
		
	}
	
	@Test
	public void testAdd(){
		
		try {
		    // test null data
		    emptyConst.add(null);
		    oneArg.add(null);
		    dHeapArr.add(null);
		    dHeapMin.add(null);;
		    
		    fail("Should have gen null exception");
		} catch (NullPointerException e){
			
		}
		
		assertEquals("num of elem in empty is 3", 3, emptyConst.size());
		System.out.println("size of empty const = " + emptyConst.size());
		System.out.println("size of oneArg is 10 = " + oneArg.size());
		assertEquals("num of elem in oneArg is 10", 10, oneArg.size());
		assertEquals("num of elem in dHeapArr is 12", 12, dHeapArr.size());
		
	}
	
	
	@Test
	public void testClear(){
		
		emptyConst.clear();
		oneArg.clear();
		dHeapArr.clear();
		
		assertEquals("num of elem in empty is 0",0,emptyConst.size());
		assertEquals("num of elems in oneArg is 0", 0, oneArg.size());
		assertEquals("num of elems in dHeapArr is 0", 0, dHeapArr.size());
		
	}
	
	
	@Test
	public void testElement(){
		
		assertEquals("value at top of heap is 2", 2, emptyConst.element());
		assertEquals("value at top of heap is 9", 9, oneArg.element());
		assertEquals("value at top of heap is 11", 11, dHeapArr.element());
		
		
		// test nosuchelementexception
		try{
		    emptyConst.clear();
		    emptyConst.element();
		    
		    fail("should have gen no such element exception");
		} catch(NoSuchElementException e){
			
		}
 		
		
	}
	
	
	/**
     * Removes and returns the element at the root. If the 
     * heap is empty, then this method throws a NoSuchElementException.
     * 
     * @return The element at the root stored in the heap.
     * @throws java.util.NoSuchElementException if the heap is empty
     */
	@Test
	public void testRemove(){
		
		assertEquals("element removed is 2",2,emptyConst.remove());
		assertEquals("element removed is 11", 11, dHeapArr.remove());
		assertEquals("element removed is 0",0,dHeapMin.remove());
		assertEquals("element at top is 1",1,dHeapMin.element());
		
		for (int i=0; i<dHeapMin.size(); i++){
			System.out.println("element at index " + i + ": " + dHeapMin.heap[i]);
		}
		assertEquals("element removed is 9", 9, oneArg.remove());
	}
	
	
	
	@Test
	public void testSize(){
		
		assertEquals("num of elem in empty is 0",3,emptyConst.size());
		assertEquals("num of elems in oneArg is 0", 10, oneArg.size());
		assertEquals("num of elems in dHeapArr is 0", 12, dHeapArr.size());
	}
	
	
	
	
	
}
