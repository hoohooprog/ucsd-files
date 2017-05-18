/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: May 3, 2017
 * File: MyStackTester.java
 * Sources of Help: Piazza
 *
 * This class is used to test the methods of Maze class
*/
package hw4;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MazeTester {

	Maze testMazed = new Maze();
	
	boolean mazeLoaded = testMazed.loadMaze("maze-4.txt");
	
	
	/*
	 * Test if the coordinates of the Squares are stored
	 * correctly
	 */
	@Test
	public void testGetNeighbors(){
		
		ArrayList<Square> storeNeighbors;
		
		storeNeighbors = testMazed.getNeighbors(testMazed.maze[0][9]);
		
		for (int i=0; i<storeNeighbors.size(); i++){
			
			System.out.println("["+ storeNeighbors.get(i).getRow()+ ","
					+ storeNeighbors.get(i).getCol() + "]");
		}
	}
	
	
	/**
	 * test if the maze's symbols are printed accordingly
	 */
	@Test
	public void testToString(){
		System.out.println(testMazed.toString());
	}
}
