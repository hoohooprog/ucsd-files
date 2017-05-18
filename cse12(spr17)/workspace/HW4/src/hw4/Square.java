/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: May 3, 2017
 * File: Square.java
 * Sources of Help: Piazza
 *
 * This class is used to act as Squares of the Maze program.
 * Each square can then be marked as the Starting square, ending square,
 * visited and also to get the square the "bots" that travel the maze are 
 * previously at. On the maze, the toString method will also "convert"
 * the type of Square to its respectively symbol ("S", "E", "o", ".", "x")
*/
package hw4;

/**
 * Purpose: This class is used to act as Squares of the Maze program.
 * Each square can then be marked as the Starting square, ending square,
 * visited and also to get the square the "bots" that travel the maze are 
 * previously at. On the maze, the toString method will also "convert"
 * the type of Square to its respectively symbol ("S", "E", "o", ".", "x")
 * @author Jingyi Tay
 * @version 1.0
 * @since May 3, 2017
 */
public class Square {
	
	// variables to represent where Square
	// is at
	private int row;
	private int col;
	private int typeOfSquare;
	
	//to mark if Square was visited
	private boolean visited;
	
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
		
	}
	
	

	/**
	 * Method to return the character corresponding to
	 * the type of Square
	 * @return String that represents the type of Square
	 * the maze contains by changing the natural numbers
	 * to symbols like # _ S E
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
		
		// o for solver worklist; . for has been explored; 
		// x on final path to exit.
		if (isVisited() && !isStart() & !isFinish()){
			char2Return = ".";
		}
		
		if (getPrevious() != null && getPrevious().isVisited() ){
		//	char2Return = "o";
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
	 * method sets visited flag to true once bots
	 * have arrived
	 * @return reference to Square object that contains data
	 * of the Square that the bot visited before this square
	 */
	public Square getPrevious() {
		// TODO Auto-generated method stub
		return previous;
	}


	/**
	 * method sets visited flag to true once bots
	 * have arrived
	 * @return reference to Square object that contains data
	 * of the Square that the bot visited before this square
	 */
	public void setPrevious(Square currSq) {
		// TODO Auto-generated method stub
		previous = currSq;
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
