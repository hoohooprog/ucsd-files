package hw7;
/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12wamf <<< --- Use your cs11f course-specific account name
 * Date: February 26, 2017
 * File: BSTreeTester.java
 * Sources of Help: stackoverflow, piazza
 *
 * This program uses test cases to test each methods of the 
 * implementation of the BST methods in 
 * BSTree.java
 *
*/

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

public class BSTreeTester {
	

	BSTree<String> emptyTree = new BSTree();
	BSTree<String> completeTree = new BSTree();
	BSTree<String> halfCompleteTree = new BSTree();
	Iterator<String> iter;
	
	

	@Before
	public void setUp(){
		
		halfCompleteTree.insert("caramel");
		halfCompleteTree.insert("san-diego");
		
		completeTree.insert("caramel");
		completeTree.insert("beach");
		completeTree.insert("san-diego");
		completeTree.insert("butter");
		
		
	}
	/*
	@Test
	public void testFindHeight(){
		assertEquals("height is 2",2,myTree.findHeight());
	}
	*/
	
	@Test
	public void testFindKey(){
		assertTrue("tree does not have butter",
				!halfCompleteTree.findKey("butter"));
		assertTrue("tree has san-diego",halfCompleteTree.findKey("san-diego"));
		
		assertTrue("tree has beach",completeTree.findKey("beach"));
		assertTrue("tree has caramel", completeTree.findKey("caramel"));
		assertTrue("tree does not have chocolate", !completeTree.findKey("chocolate"));
		
		assertTrue("tree has no elems", !emptyTree.findKey("caramel"));
		
	}
	
	
	//@Test
	//public void testFindMoreInfo(){
		
		LinkedList<String> storeLL = null;
		/*
		try {
			myTree.findMoreInformation("scotch");
			fail("should have generated exception");
		} catch(IllegalArgumentException e){
			
		}
		*/
 
	 
	/*
		try{
			myTree.findMoreInformation(null);
			fail("should have generated exception");
		}catch (NullPointerException e){
			
		}
		
		assertTrue("tree has caramel", myTree.findKey("caramel"));
		
		storeLL = myTree.findMoreInformation("caramel");
	
		assertTrue("storeLL contains caramel's starbucks",storeLL.contains("starbucks"));
		assertEquals("1st info in san-diego is california","starbucks",storeLL.getLast());	
		
	}
	*/
	
	@Test
	public void testGetRoot(){
		
		halfCompleteTree.insert("beach");
		halfCompleteTree.insert("butter");
		
		
		
		
		assertEquals("root is null",null, emptyTree.getRoot());
		assertEquals("root is caramel","caramel", 
				completeTree.getRoot().getKey());
		assertEquals("root is caramel","caramel", 
				halfCompleteTree.getRoot().getKey());
	}
	
	@Test
	public void testGetSize(){
		
		assertEquals("siz is 0", 0, emptyTree.getSize());
		assertEquals("siz is 4", 4, completeTree.getSize());
		assertEquals("siz is 2", 2, halfCompleteTree.getSize());
		
	}
	
	
	@Test
	public void testInsert(){
		
		halfCompleteTree.insert("san-diego");
		halfCompleteTree.insert("butter");
		halfCompleteTree.insert("beach");
		halfCompleteTree.insert("caramel");
		
	}
	
	
	@Test
	public void testInsertInfo(){
		completeTree.insertInformation("caramel", "starbucks");
		completeTree.insertInformation("beach", "california");
		completeTree.insertInformation("butter", "bread");
		completeTree.insertInformation("san-diego", "california");
		
		assertTrue("caramel now has starbucks", 
				completeTree.findMoreInformation("caramel").contains("starbucks"));
		
		assertTrue("butter now has bread", 
				completeTree.findMoreInformation("butter").contains("bread"));
		
		assertTrue("beach does not have party", 
				!completeTree.findMoreInformation("beach").contains("party"));
		
		try{
			emptyTree.insertInformation("beach", "florida");
		}catch(IllegalArgumentException e){
		
		}
		
	}
	
	/*
	@Test
	public void testLeafCount(){
		assertEquals("leaves in tree are 2",2, myTree.leafCount());
	}
	
	@Test
	public void testHastNext(){
		assertTrue("iter should have next",iter.hasNext());
	}
	
	@Test
	public void testNext(){
		assertEquals("1st next is beach", "beach",iter.next());
		assertEquals("2nd next is butter","butter",iter.next());
		assertEquals("3rd next is caramel","caramel",iter.next());
		assertEquals("4th next is san-diego","san-diego",iter.next());
	}
	*/
}
