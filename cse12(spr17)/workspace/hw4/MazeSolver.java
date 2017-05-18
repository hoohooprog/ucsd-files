/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: May 3, 2017
 * File: MazeSolver.java
 * Sources of Help: Piazza
 *
 * MazeSolver is an abstract class that is inherited by MazeSolverStack
 * and MazeSolverQueue. Contains methods that allows solution to be found
 * with either stack or queue worklists 
*/
package hw4;
import java.util.*;

/**
 * MazeSolver is an abstract class that is inherited by MazeSolverStack
 * and MazeSolverQueue. Contains methods that allows solution to be found
 * with either stack or queue worklists 
 * @author jingyi
 *
 */
public abstract class MazeSolver {

	protected MyQueue<Square> solveUsingQueue;
	protected MyStack<Square> solveUsingStack;
	
	private Maze mazeLoaded;
	
	private static boolean gameOver;
	
	private static boolean after1stStep;
	
	protected Square currSq;
	
	/**
	 *  Create an empty worklist
	 */
	abstract void makeEmpty();
		
	
	/**
	 * returns true if the worklist is empty
	 * @return true if the worklist is empty
	 */
	abstract boolean isEmpty();
	
	
	/**
	 * Add the given Square to the worklist
	 * set previous here
	 * @param sq
	 */
	abstract void add(Square sq);
	
	
	/**
	 * returns the "next" item from the worklist
	 * @return the "next" item from the worklist
	 */
	abstract Square next();
	
	
	/**
	 * Create a (non-abstract) constructor that takes a Maze
	 * as a parameter and stores it in a variable that the children
	 * classes can access.
	 * @param maze
	 */
	public MazeSolver(Maze maze){
	
		mazeLoaded = maze;
		// set starting sq as curr sq
		currSq = maze.getStart();
		
		
	}
	
	
	/**
	 * Getter for maze
	 * @return the maze loaded
	 */
	public Maze getMaze(){
		return mazeLoaded;
	}
	
	
	/**
	 * A method that the application program can use to see if a path
	 * exists. HAVE NOT SET BOOLEAN VARIBALE 
	 * @return true value if exit is found, false otherwise.
	 */
	boolean foundExit(){
		
		if (mazeLoaded.getFinish() != null){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * Method is used to keep track of the game progress.
	 * Maze-Solving Algorithm may be terminated when gameOver becomes true.
	 * @return true value if exit is found or when the worklist
	 * becomes empty.
	 */
	public boolean gameOver(){
		
		//System.out.println(currSq.isStart());
		
		
		//System.out.println(currSq.getCol());
		
		if (currSq.isFinish()){
			gameOver = true;
		}
		
		return gameOver;
		
	}
	
	/**
	 * returns boolean value if square bot is at exit
	 * @return boolean value if square bot is at exit
	 */
	public boolean setGameOver(){
		
		if (currSq.isFinish()){
		    gameOver = true;
		}
		return gameOver;
	}
	
	
	/**
	 * @return a message that gives the solution path as a list
	 * of coordinates [i,j] from the start to the exit or a 
	 * message indicating no such path exists if the maze isn't
	 * solved.
	 */
	String getPath(){
		
		String pathStored = "";
		
		Square finishSq = mazeLoaded.getFinish();
		
		LinkedList<Square> arrayOfPath = new <Square>LinkedList();
		
		
		if (currSq == null){
			pathStored += ("Uh Oh!! There's no escape!!");
			return pathStored;
		}
		
		if (currSq.isFinish()){
			
			pathStored +=("Found the Escape!");
			pathStored += ("\n");
			pathStored += ("Path from Start to Exit : ");
			
			// add final square to LinkedList first
			currSq = currSq.getPrevious();
			arrayOfPath.addFirst(currSq);
			
			
			while (currSq.getType() != 2){
				arrayOfPath.addFirst(currSq);
				
				currSq = currSq.getPrevious();
			}
			
			arrayOfPath.addFirst(currSq);
			
			for (int i=0; i<arrayOfPath.size(); i++){
				
				pathStored += ("[" + arrayOfPath.get(i).getRow() + ","
						+ arrayOfPath.get(i).getCol() + "]");
			}
		}
		
		return pathStored;
		
	}
	
	
	/**
	 * Perform one iteration of the maze solving algorithm
	 * (steps 1 thru 5) and return the Square that was just
	 * explored (and null if no such Square exists).
	 * @return either the Square that was just explored
	 * or null if no such Square exists.
	 */
	Square step(){
		
		ArrayList<Square> neighbors = new ArrayList();
		
		if (after1stStep == false){
			
			mazeLoaded.setVisit(currSq.getRow(), currSq.getCol());
			//enqueue into queue solveUsingQueue
			add(currSq);
			
			//set to visited
			currSq.setVisited();
			
			if (currSq.isFinish()){
				//setGameOver();
				return currSq;
			}
			else{
				
				if (solveUsingQueue != null){
				    // transfer neighbors of 1st square to another array list
				    neighbors = mazeLoaded.getNeighbors(solveUsingQueue.dequeue());
				}
				
				if (solveUsingStack != null){
					neighbors = mazeLoaded.getNeighbors(solveUsingStack.pop());
				}
				
				// add each of the squares to queue
			    for ( int i=0; i<neighbors.size(); i++){
				    //transfer neighbor squares to queue
			    	add(neighbors.get(i));
			    	//System.out.println(solveUsingQueue.peek());
				
				    if (neighbors.get(i).getPrevious() == null){
				        neighbors.get(i).setPrevious(currSq);
				        //produces o on map(means in worklist)
				       // System.out.println(solveUsingQueue.peek());
				    }
			    }
			}
			
			after1stStep = true;
			
		}
		else {
			
			
			// if algo can't produce any elements to worklist
			// return null
		    if (isEmpty()){
		    	System.out.println(getPath());
			    return (Square)null;
		    }
		    
		    if (solveUsingQueue != null){
		        //dequeue top of worklist to currSq
		        currSq = solveUsingQueue.dequeue();
		    }
		    
		    if (solveUsingStack != null){
		    	currSq = solveUsingStack.pop();
		    }
		    
		    // if Square obj referenced is exit, terminate
		    // the algo and output the path found
		    if (currSq.isFinish()){
		    	currSq.setVisited();
		        return currSq;
			
		    }
		    else {
			
			    //checked as visit
			    currSq.setVisited();
			    
			    // check if finished line
			    if (currSq.isFinish()){
					setGameOver();
				}
			    else{
					neighbors = mazeLoaded.getNeighbors(currSq);
					
				    for ( int i=0; i<neighbors.size(); i++){
					    
				    	if (!neighbors.get(i).isVisited()){
				    	    add(neighbors.get(i));
				    	}
				    	
					    if (neighbors.get(i).getPrevious() == null){
					        neighbors.get(i).setPrevious(currSq);
					       
					    }
				    }
				}
			
		    }
			
		} 
		
		return currSq;
	}
	
	/**
	 * 
	 * @return boolean object that shows if 1st step is done
	 */
	public boolean retAfter1stStep(){
		return after1stStep;
	}
	
	
	/**
	 * Repeatedly call step() to call the maze solving algorithm
	 * until I get to the exit square or the worklist is empty.
	 */
	void solve(){
	
	    currSq = step();    
	    
	    // while currSq is not the finished square and there is a exit
	    while (!currSq.isFinish() && foundExit()==true){
		       currSq = step();
	    }
	  
	}
}
