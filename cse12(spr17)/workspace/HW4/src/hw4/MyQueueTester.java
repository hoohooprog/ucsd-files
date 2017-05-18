package hw4;

import static org.junit.Assert.*;
import org.junit.*;

public class MyQueueTester {
    private MyQueue<Integer> empty;
    private MyQueue<Integer> one;
    private MyQueue<Integer> many;
    
    private final int CAP = 5;
    private final int N = 3;
    
    @Before
	public void setUp()
	{
		empty = new MyQueue<Integer>(CAP);
		one = new MyQueue<Integer>(CAP);
		one.enqueue(new Integer(15));
		many = new MyQueue<Integer>(CAP);
		for ( int i = 0; i < N; i++ ) {
		    many.enqueue(new Integer(i));
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
	public void testEnqueue()
	{
	    assertTrue(empty.enqueue(new Integer(10)));
	    assertEquals(1, empty.size());
	    assertTrue(one.enqueue(new Integer(10)));
	    assertEquals(2, one.size());
	}
	
    @Test
	public void testEnqueueFull()
    {
        for (int i=0; i<CAP-N; i++) {
            assertTrue(many.enqueue(new Integer(10)));
        }
        assertFalse(many.enqueue(new Integer(1)));
    }
	
    @Test
	public void testEnqueueNull()
    {
	    try {
	        one.enqueue(null);
	        fail("Should not insert null");
	    }
	    catch (NullPointerException e) {
	        // Pass
	    }
    }
    
    @Test
	public void testDequeue()
    {
        assertEquals(15, one.dequeue().intValue());
        assertEquals(0, one.size());
        assertEquals(0, many.dequeue().intValue());
        assertEquals(N-1, many.size());
    }
    
    @Test
	public void testDequeueEmpty()
    {
        assertEquals(null, empty.dequeue());
    }
	
    @Test
	public void testPeek()
    {
	    assertEquals(15, one.peek().intValue());
        assertEquals(1, one.size());
        assertEquals(0, many.peek().intValue());
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
		//Stack12<Integer> many_clone = new Stack12<Integer>(CAP);
		//for ( int i = 0; i < N; i++ ) {
		//	many_clone.push(new Integer(i));
		//}
		//assertTrue( many.equals(many_clone));
		//assertTrue( many_clone.equals(many));
	}
}
