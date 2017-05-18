/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: May 3, 2017
 * File: MazeSolverQueue.java
 * Sources of Help: Piazza
 *
 * This class is used to solve the Maze using ADT Queue;
 * the variable is stored in its abstract class - MazeSolver.java.
 * It contains additional methods that makes it a queue solver.
*/
package hw4;

/**
 * This class is used to solve the Maze using ADT Queue;
 * the variable is stored in its abstract class - MazeSolver.java.
 * It contains additional methods that makes it a queue solver.
 * @author jon
 *
 */
public class MazeSolverQueue extends MazeSolver {

	
	/**
	 * Constructor of class that uses MazeSolver's constructor
	 * to initialize the maze.
	 * It also initializes the Queue by giving it capacity
	 * as large as the maze is.
	 * @param maze
	 */
	public MazeSolverQueue(Maze maze){
		super(maze);
		solveUsingQueue = new MyQueue(maze.numCols * maze.numRows);	
		
		
	}
	
	
	/**
	 * empties the Queue if it is not empty
	 */
	@Override
	void makeEmpty(){
		
		if (solveUsingQueue.size() != 0){
			
			for (int i=0; i<solveUsingQueue.size();i++){
				solveUsingQueue.dequeue();
			}
		}
		
	}
	
	
	/**
	 * returns the Queue that has the squares that will solve the maze
	 * @return the Queue that has the squares that will solve the maze
	 */
	public MyQueue<Square> getQueue(){
		
		return solveUsingQueue;
	}
	
	
	/**
	 * method returns values that show if Queue is empty
	 * @return values that show if Queue is empty or not
	 */
	@Override
	boolean isEmpty(){
		
		if (solveUsingQueue.size() == 0)
			return true;
		else
			return false;
		
	}
	
	
	/**
	 * Add the given Square to the worklist
	 * @param sq
	 */
	@Override
	void add(Square sq){
		
		solveUsingQueue.enqueue(sq);

	}
	
	
	/**
	 * method returns Square at the front of the Queue
	 * @return Square object at the front of the Queue
	 */
	@Override
	Square next(){
		
		return solveUsingQueue.peek();
	}
	
	public static void main( String[] args )
	{
		Maze myMaze = new Maze();
		boolean load = myMaze.loadMaze(args[0]);
		if(!load) {
			System.out.println("Oops!! Could not load the Maze");
		} else {
			MazeSolverQueue queueSolver = new MazeSolverQueue(myMaze);
			queueSolver.solve();
			System.out.println(queueSolver.getPath() +"\n");
			System.out.println(queueSolver.getMaze().toString());
			System.out.println("Number of squares remaining in the worklist = "+ queueSolver.getQueue().size() );
		}
	 }
}
