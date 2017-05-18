/**
 *  Name:Jingyi Tay
 *  Login: cs12wamf
 *  Date: 2/4/2017
 *  File: Square.java
 *  Source of help: piazza, javadocs
 *  
 *  The Square objects form the parts of the maze; parts of the maze are:
 *  space, wall, start, or exit. The functions included allow us to 
 *  determine what the location of each Square is, what type of Square
 *  it is, if the Square has been visited and where the finish and 
 *  start Squares are. 
 * 
 */

package hw4;

public class Square {

	// variables to represent where Square
	// is at
	private int row;
	private int col;
	
	//to mark if Square was visited
	private boolean visited;
	
	private int typeOfSquare;
	
	private Square previous;
	
	
	/**
	 * Creates a new Square object and assign a row, col
	 * and type to it so program will know where to place it
	 * and what type of Square it is for the maze.
	 * @param row
	 * @param col
	 * @param type
	 */
	public Square(int row, int col, int type){
	    
		this.row = row;
	    this.col = col;
	    this.typeOfSquare = type;
	    this.previous = null;
		
	}
	
	
	public void setPrevious(Square sq){
		
		previous = sq;
	}
	
	
	public Square getPrevious(){
		
		return previous;
	}
	
	
	/**
	 * Method to return the character corresponding to
	 * the type of Square
	 */
	public String toString(){
		
		String char2Return = null;
		/* use switch statements
		 * _ for empty space (0)
		 * # for wall (1)
		 * S for Start (2)
		 * E for Exit (3)
		 */
		switch (typeOfSquare) {
		
		case 0: char2Return = "_";
		        break;
		        
		case 1: char2Return = "#";
		        break;
		        
		case 2: char2Return = "S";
		        break;
		        
		case 3: char2Return = "E";
		        break;
		
		}
		
		if (isVisited()){
			char2Return = ".";
		}
		
		
		return char2Return;
	}
	
	
	/**
	 * method that returns the row value of the Square
	 * @return the row value of the Square
	 */
	public int getRow(){
		
		return row;
	}
	
	
	/**
	 * method that returns the column value of the Square
	 * @return the col value of the Square
	 */
	public int getCol(){
		
		return col;
	}
	
	/**
	 * method that returns the type of Square to its
	 * corresponding number.
	 * @return the type of Square by translating
	 * the character to numbers
	 */
	public int getType(){
		
		return typeOfSquare;
	}
	
	
	/**
	 * returns value to program to decide whether maze
	 * was completed.
	 * @return true if the Exit of the maze is found
	 */
	public boolean isFinish(){
		
		return ( this.visited && this.getType()==3);
	}
	
	
	/**
	 * returns true to program if Square is Starting
	 * Square
	 * @return true to program if Square is Starting
	 * Square
	 */
	public boolean isStart(){
		
		return (visited && getType()==2);
	}
	
	
	/**
	 * method returns truth values to indicate
	 * if bot is able to move to the Square.
	 * @return true if Square is not a wall or Start 
	 * location so bot can move to it.
	 */
	public boolean isValid(){
		
		return (getType() != 2 || getType() != 1);
		
	}
	
	
	/**
	 * method returns values to show if Square
	 * has been visited by bots or not
	 * @return true if Square has been visited
	 */
	public boolean isVisited(){
		
		return visited;
	}
	
	
	/**
	 * method sets visited flag to true once bots
	 * have arrived
	 */
	void setVisited(){
		
		visited = true;
	}
	
	
	/**
	 * method sets visited flag to false maybe if game
	 * resets
	 */
	void clearVisited(){
		
		visited = false;
		this.previous = null;
	}
	
}
