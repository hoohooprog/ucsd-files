/**
 * Name: Jingyi Tay
 * Login: cs12sie
 * Date: 5/32017
 * File: Maze.java
 * Sources of Help: piazza, write-up
 * 
 * contains a 2-d array of Squares, that loads mazes and returns the 
 * neighbors of particular squares and also find start and finish
 * squares of mazes. Also converts natural numbers to symbols of the
 * maze.
 * 
 */
package hw4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * class contains a 2-d array of Squares, that loads mazes and returns the 
 * neighbors of particular squares and also find start and finish
 * squares of mazes. Also converts natural numbers to symbols of the
 * maze.
 * @author jingyi
 *
 */
public class Maze {
	public Square[][] maze; // a 2D Maze 
	protected int numRows; // No of rows of maze(initialized in loadMaze)
	protected int numCols; // No of cols of maze(initialized in loadMaze)
	
	
	public int getRows(){
		return numRows;
	}
	
	
	public int getCol(){
		return numCols;
	}
	
	
	/**
	 * Loads the maze that is contained in the file named fname.
	 * The text file, fname, contains the visual layout of the maze
	 * @param fname
	 * @return boolean values to show if Maze was loaded.
	 */
	public boolean loadMaze(String fname){

		String line;
		BufferedReader inputStrem;
		StringTokenizer st;


		try {
			int currentRow = 0;

			// read file fname
			inputStrem = new BufferedReader(new FileReader(fname));
			
			// read line from file fname
			line = inputStrem.readLine();
			
			/* if first line is not empty, then it contains the number of 
             * rows and columns of the file representing the maze;
             * we parse the first line and create a Square array
             */			
			if(line != null)
			{
				// initialize stringtokenizer for the specific line
				st = new StringTokenizer(line);
				
				// returns the integer value rep by the arguments in decimal
				numRows = Integer.parseInt(st.nextToken());
				numCols = Integer.parseInt(st.nextToken());
				
				// references maze var to Square object
				maze = new Square[numRows][numCols];
			}
			else {
				return false;
			}

			// if there are still lines to be read in the file(row?)
			while ((line = inputStrem.readLine()) != null) {
				
				// true if reading first line in file, containing numRows
				// numColums
				if (numRows == 0) {  
					st = new StringTokenizer(line);
					numRows = Integer.parseInt(st.nextToken());
					numCols = Integer.parseInt(st.nextToken());
					maze = new Square[numRows][numCols];
				} else if (line.length() == 1)
					break; 
				else {
					int col=0;
					for (int c = 0; c < line.length(); c++) {

						if(line.charAt(c) == ' ') 
							continue;
						maze[currentRow][col] = new Square(currentRow, col, Character.getNumericValue(line.charAt(c)));
						col++;
					}
					currentRow ++;
				}
			}
		}
		catch (IOException e) {
			System.out.println (e.toString());
			System.out.println("Could not find file " + fname);
		} 

		return true;
	}


	/**
	 * method returns an Array of at most 4 squares
	 * that bot can travel to(North,East,South & West respectively)
	 * from the square the bot is at now;
	 * skipping certain squares depending on which bordered square
	 * the bot is on.
	 * @param sq
	 * @return only valid squares that only the bot at
	 * current square can travel to.
	 */
	public ArrayList<Square> getNeighbors(Square sq){
		
		//access the sq's getter methods to know its coordinates
		int row = sq.getRow();
		int col = sq.getCol();
		
		// declare square methods to store potential neighbors
		Square westNeighbor;
		Square southNeighbor;
		Square eastNeighbor;
		Square northNeighbor;
		
		// list of squares(max 4)
		ArrayList<Square> neighbors = new ArrayList<Square>(4);
		
		//if square were to have top neighbor, then square must
		// not be the top
		if (row > 0){
					
			northNeighbor = maze[row-1][col];
						
			if (northNeighbor.getType() != 1){
				neighbors.add(northNeighbor);
						
			}
		}
		
		
		// any square on the last col(numcol-1) will not have e.neighbor
		// thus any square that has e.neighbor will not be on last col
		if (col < (numCols-1)){
					
			eastNeighbor = maze[row][col+1];
						
			if (eastNeighbor.getType() != 1){
				neighbors.add(eastNeighbor);
			}	
					
		}
		
		// if square were to have south neighbor then square
		// cannot be at the last row (index numRows-1)
		if (row < numRows-1){
							
			southNeighbor = maze[row+1][col];
								
			if (southNeighbor.getType() != 1){
				neighbors.add(southNeighbor);
			}
		}
				
		
		// any squares on 1st col(indexed 0) will not have w.neighbor
		if (col > 0){ 
			
			westNeighbor = maze[row][col-1];
		        
		    // if square to the west isn't a wall then add it as neighbor
		    if (westNeighbor.getType() != 1){
			    neighbors.add(westNeighbor);
			}
		    
		}
		
		return neighbors; 
	}

	
	/**
	 * method returns the Start Square in the maze
	 * @return Start Square in the maze
	 */
	public Square getStart(){
		Square startSq = null;
		
		for (int row=0; row<numRows; row++){
			for (int col=0; col<numCols; col++){
				if (maze[row][col].getType() == 2){
					startSq = maze[row][col];
					
				}
			}
		}
		
		return startSq;
	}
	
	
	/**
	 * method that finds and returns the Exit Square in the maze
	 * @return Exit Square in the maze
	 */
	public Square getFinish(){
		
		Square exitSq = null;
		
		for (int row = 0; row < numRows; row++){
			for (int col = 0; col < numCols; col++){
				if (maze[row][col].getType() == 3){
				    
					exitSq = maze[row][col];
	  
				}			
			}
		}
		
		return exitSq;
	}

	
	/**
	 * Sets the current Square as visited
	 * @param row where Square is located
	 * @param col where Square is located
	 */
	public void setVisit(int row, int col)
	{
		maze[row][col].setVisited();
		maze[row][col].toString();
	}

	
	/**
	 * method clears the visited flags so to
	 * restart game.
	 */
	public void clearMaze() {
		
		for (int i=0; i<numRows; i++){
			for (int j=0; j<numCols; j++){
				maze[i][j].clearVisited();
			}
		}
	}
	
	
	/**
	 * accessor Method that returns the entire maze in 2-D form;
	 * can be used to make sure that the maze is loaded correctly
	 * @return the entire maze in 2-d form
	 */
	public Square[][] getMaze() {
		
		return maze;
	}

	
	/**
	 * method returns a String representation of the maze.
	 * @return String representation of the maze.
	 */
	@Override
	public String toString() {

		String s="";
		for (int r = 0; r < numRows; r++) 
		{
			for (int c = 0; c < numCols; c++) 
				s=s+maze[r][c].toString();
			s=s+"\n";
		}
		return s;  
	}


}
