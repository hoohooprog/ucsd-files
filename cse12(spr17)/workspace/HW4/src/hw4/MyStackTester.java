/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: May 3, 2017
 * File: MyStackTester.java
 * Sources of Help: Piazza
 *
 * This class is used to test the methods of MyStack.java
*/
package hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


public class MyStackTester {
    private MyStack<Integer> empty;
    private MyStack<Integer> one;
    private MyStack<Integer> many;
    
    private final int CAP = 5;
    private final int N = 3;
    
    @Before
	public void setUp()
	{
		empty = new MyStack<Integer>(CAP);
		one = new MyStack<Integer>(CAP);
		one.push(new Integer(15));
		many = new MyStack<Integer>(CAP);
		for ( int i = 0; i < N; i++ ) {
		    many.push(new Integer(i));
		}
	}
	
    @Test
	public void testSize()
	{
		assertEquals(empty.size(), 0);
		assertEquals(one.size(), 1);
		assertEquals(many.size(), N);
	}
	
    @Test
	public void testCapacity()
	{
	    assertEquals(CAP, empty.capacity() );
	    assertEquals(CAP, one.capacity() );
	    assertEquals(CAP, many.capacity() );
	}
	
    @Test
	public void testPush()
	{
	    assertTrue(empty.push(new Integer(10)));
	    assertEquals(1, empty.size());
	    assertTrue(one.push(new Integer(10)));
	    assertEquals(2, one.size());
	}
	
    @Test
	public void testPushFull()
    {
        for (int i=0; i<CAP-N; i++) {
            assertTrue(many.push(new Integer(10)));
        }
        assertFalse(many.push(new Integer(1)));
    }
	
    @Test
	public void testPushNull()
    {
	    try {
	        one.push(null);
	        fail("Should not insert null");
	    }
	    catch (NullPointerException e) {
	        // Pass
	    }
    }
    
    @Test
	public void testPop()
    {
        assertEquals(15, one.pop().intValue());
        assertEquals(0, one.size());
        assertEquals(N-1, many.pop().intValue());
        assertEquals(N-1, many.size());
    }
    
    @Test
	public void testPopEmpty()
    {
        assertEquals(null, empty.pop());
    }
	
    @Test
	public void testPeek()
    {
        assertEquals(15, one.peek().intValue());
        assertEquals(1, one.size());
        assertEquals(N-1, many.peek().intValue());
        assertEquals(N, many.size());
    }
    
    @Test
	public void testPeekEmpty()
    {
        assertEquals(null, empty.peek());
    }
	
    @Test
	public void testEquals()
	{
		//Queue12<Integer> many_clone = new Queue12<Integer>(CAP);
		//for ( int i = 0; i < N; i++ ) {
		//	many_clone.push(new Integer(i));
		//}
		//assertTrue( many.equals(many_clone));
		//assertTrue( many_clone.equals(many));
	}
}
