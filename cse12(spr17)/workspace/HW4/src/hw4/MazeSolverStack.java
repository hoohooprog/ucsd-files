/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: May 3, 2017
 * File: MazeSolverStack.java
 * Sources of Help: Piazza
 *
 * This class is used to solve the Maze using ADT Stack;
 * the variable is stored in its abstract class - MazeSolver.java.
 * It contains additional methods that makes it a stack solver.
*/
package hw4;

/**
 * This class is used to solve the Maze using ADT Stack;
 * the variable is stored in its abstract class - MazeSolver.java.
 * It contains additional methods that makes it a stack solver.
 * @author jingyi
 *
 */
public class MazeSolverStack extends MazeSolver {
	
	
	/**
	 * a constructor for MazeSolverStack class that initializes
	 * maze variable from the superconstructor;
	 * also allocates a certain capacity(size of maze) for the stack variable
	 */
	public MazeSolverStack(Maze maze){
		super(maze);
		solveUsingStack = new MyStack(maze.numCols * maze.numRows);
	}
	
	
	/**
	 * method that clears the squares stored in the stack
	 */
	void makeEmpty(){
		
		// if this is the 1st step then iterate through the stack
		// and pop all the squares contained then insert
	    // starting square
		if (retAfter1stStep() == false){
			for (int i=0; i<solveUsingStack.size();i++){
				solveUsingStack.pop();
			}
			solveUsingStack.push(getMaze().getStart());
		}
		// if not 1st step and contains square, clear all its contents
		else{
		
		    if (isEmpty() == false){
			
			    for (int i=0; i<solveUsingStack.size(); i++){
				    solveUsingStack.pop();
			    }
		    }
		}
		
	}
	
	
	/**
	 * method returns the stack that stores the worklist for 
	 * solving the maze.
	 * @return MyStack<Square> type object that has the worklist
	 * to solve the maze.
	 */
	public MyStack<Square> getStack(){
		return solveUsingStack;
	}
	
	
	/**
	 * return boolean value that indicates if stack is empty
	 * @return value that indicates if stack is empty
	 */
	boolean isEmpty(){
		
        if (solveUsingStack.size() == 0){
        	
        	return true;
        }
        else{ return false;}
	}
	
	
	/**
	 * Add the given Square to the worklist
	 * @param sq
	 */
	void add(Square sq){
		
		solveUsingStack.push(sq);
		
	}
	
	
	/**
	 * returns the next square that Stack will return
	 * @return Square object that is at the top of the Stack
	 */
	Square next(){
		
		return solveUsingStack.peek();
	}
	
	
	public static void main( String[] args )
	{
		Maze myMaze = new Maze();
		boolean load = myMaze.loadMaze(args[0]);
		if(!load) {
			System.out.println("Oops!! Could not load the Maze");
		} else {
			MazeSolverStack stackSolver = new MazeSolverStack(myMaze);
			stackSolver.solve();
			System.out.println(stackSolver.getPath() +"\n");
			System.out.println(stackSolver.getMaze().toString());
			System.out.println("Number of squares remaining in the worklist = "+ stackSolver.getStack().size() );
		}
	}
}
