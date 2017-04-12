/*
 * Name: Jingyi Tay
 * PID: A99014740
 * Login: cs12wamf
 */

package hw1;

import java.util.Scanner;
import java.util.Random;

/*
 * $ class creates a tic-tac-toe game on commandline that uses a simple
 *   AI that plays against human. Stats object is created from stats class
 *   so to use its methods to keep track and/or reset score.
 *
 * @author jingyi tay
 * @version 1.0
 * @ since 1/17/2017
 */
public class HW1 {

	static char[] [] board = new char[3][3];
	static Scanner input=new Scanner(System.in);

        // static constant variables for loop index & row/col
        private static final int STARTINDEX = 0;
        private static final int ENDINDEX = 3;

        // static constant variable for middle row/col
        private static final int MIDINDEX = 1;
        // static constant variable for last row/col & for number generator
        private static final int LASTINDEX = 2, MAX_NUM = 2;

        // static constant variable for comp/human turn to end
        private static final int END_BEFORE_FOUR = 4;

	//Object of Stats class to maintain statistics
	static Stats stat = new Stats();  

	
	/**
	 * Prints the TicTacToe board
	 * @param arr: The board so far
	 */
	public static void printBoard(char [][] arr){
		System.out.println();
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				
				System.out.print(arr[i][j]);
				
				if(j!=2) {
			            //Print the | for readable output
				    System.out.print(" " + "|" + " ");    
			    }
			}
			System.out.println();
			if(i!=2) {
                                // Print _ for readability
				System.out.print("_   _   _ ");   
				System.out.println();;
			}
		}
	}

	
	/**
	 * Clear the TicTacToe board before starting a new game
	 * @param arr: The board so far
	 */
	public static void clearBoard(char [][] arr){
		/**
                 * for loop that loops by going thru all the columns
                 * of row 1 1st before proceeding to the subsequent rows.
                 * The indexes are cleared.
                 */
		for (int i = STARTINDEX; i < ENDINDEX; i++) {
			for (int j = STARTINDEX; j < ENDINDEX; j++) {
				arr[i][j] = '\u0000';
			}
		}
			
		
	}

	
	/** Determines if the player with the specified token wins
	 * 
	 * @param symbol: Specifies whether the player is X or O
	 * @return true if player has won, false otherwise
	 */
	  public static boolean isWon(char symbol) {
	
          // stores the boolean value and returns it to determine
          // if comp/human won	
	  boolean wonOrNot = false;


          /*
           * The for loop goes thru each row to check if there are
           * any horizontal rows of 'X's or 'O's.
           * if so, human or computer wins.
	   */	  
	  for (int i = 0; i < 3; i++) //horizontal
	      if (board[i][0] == symbol 
	          && board[i][1] == symbol
	          && board[i][2] == symbol) {
	        wonOrNot = true;
	      }


	  /*
           * The for loop goes through each column to check if there are
           * any vertical rows of 'X's or 'O's.
           * If so, human or computer wins.
           */
	  for (int j = STARTINDEX; j < ENDINDEX; j++) //vertical
	    	if (board[STARTINDEX][j] == symbol
	    	    && board[MIDINDEX][j] == symbol
	    	    && board[LASTINDEX][j] == symbol) {
	    		wonOrNot = true;
	    	}

	    
          /*
           * If-else if loop that checks for left and right diagonal rows
           * of 'X's or 'O's. If so, human or computer wins.
           */
	  if (board[STARTINDEX][STARTINDEX] == symbol 
	    	&& board[MIDINDEX][MIDINDEX] == symbol 
	    	&& board[LASTINDEX][LASTINDEX] == symbol) { //diagonal
	    	wonOrNot = true;
	    }
	  else if (board[STARTINDEX][LASTINDEX] == symbol  // diagonal
	    		 && board[MIDINDEX][MIDINDEX] == symbol
	    		 && board[LASTINDEX][STARTINDEX] == symbol) {
	    	wonOrNot = true;
	    }
	    
          return wonOrNot;
	   
	  }


	 /*
          * Returns a boolean variable that indicates if the element in 
          * the array is already occupied or not
          * @return boolean variable
          */
	  public static boolean isOccupied(int row, int col){
		  
		  boolean occupiedBoolean = false;
		  
                  if (board[row][col] == 'X' || board[row][col] == 'O') {
			  System.out.println("Sorry that cell is already" 
                          + "occupied.Please try again.");
			  
			  occupiedBoolean = true;
		  }
		  
		  return occupiedBoolean;
	  }

	  
         /*
          * Returns a int which is either 0 or 1 to decide if human
          * or computer starts first.
          * @return int variable
          */
	  public static int whoStarts(){
                  
		  Random numberGen  = new Random();
		  int numberChosen = numberGen.nextInt(MAX_NUM);
		  
		  return numberChosen;
	  }

	  
	  /*
           * takes care of the human's move
	   * 1. Prompt for a cell, then column
	   * 2. Puts a symbol (X or O) on the board
	   * 3. Prints the updated board
	   * 4. If a human wins: prints, updates stats and returns true
	   * 5. If not a win yet, returns false
           * @return boolean variable 
           */
	  
	  public static boolean humanTurn(char symbol){
		  
		  System.out.print("\n\nEnter your move: (row column): " );
			int row = input.nextInt();
			int col = input.nextInt();
			
			boolean forOccupied = false;
			boolean forDone = false;
			
			forOccupied = isOccupied(row,col);
		
                        // user input is checked and user told to reenter move
                        // until empty index is found.	
			while (!isWon('X') && forOccupied) {
				System.out.print("\n\nEnter your move: "
                                +"(row column): ");
				
				row = input.nextInt();
				col = input.nextInt();
				
				forOccupied = isOccupied(row,col);
			}
			board[row][col] = 'X';
			printBoard(board);
			
			forDone = isWon('X');
			
			return forDone;
	  } 

	  
	  /* 
           *takes care of the computer's move
	   * 1. Generates numbers until finds an empty cell
	   * 2. Puts a symbol (X or O) on the board
	   * 3. Prints the updated board
	   * 4. If a comp wins: prints, updates stats and returns true
	   * 5. If not a win yet, returns false 
           * @return boolean variable
           */	  
	  public static boolean compTurn(char symbol) {
		  
		    int row = 0, col = 0;
		    boolean forDone = false;
		    boolean storedO = false;
		   
                   /*
                    * loop goes through each row and finds out which index
                    * is empty. If empty, stores 'O' in element.
                    * Then breaks out of both inner and outer loop
                    */
		    for (int i = STARTINDEX; i < ENDINDEX; i++) {
		    	for (int j = STARTINDEX; j < ENDINDEX; j++) {
		    		if (board[i][j] == '\u0000') {
		    			board[i][j] ='O';
		    			row = i;
		    			col = j;
		    			
		    			storedO = true;
		    			break;
		    		}
		    	
		    	}
		    	if (storedO) {
		    		break;
		    	}
		    }
	        
			System.out.println("\n\nMy Move is: "+row+" "+ col);
			
			printBoard(board);
			
			forDone = isWon('O');
			
	        return forDone;
			
	  }
	  
	  /*
           * If human goes first:
	   * We have 9 moves in total (max). 8 moves will be in a loop
	   * and the last human move is outside of the loop:
	   * 1. human goes first, with a X
	   * 2. If the returned value is true (human won), then boolean 
           * flag=trueand we break out of the loop. done indicates that 
           * the game is over.
	   * 3. If the game is not over, then it is computer's turn. 
	   * 4. If the returned value is true (comp won), then boolean 
           * flag=true and we break out of the loop. done indicates that 
           * the game is over
	   * 5. Repeat the two steps above 3 more times. 
	   * 6. If the done is still false, then a human performs one 
           * more move and we check if the move led to the win or tie.
           * @return void    
	   * */	  
	  public static void humanFirst(){
			boolean done=false;

		  for (int i = 0; i < END_BEFORE_FOUR; i++) {	
				if (humanTurn('X')) {
					done=true;
					System.out.println("You won!!!");
					stat.incrementUserWins();
					break;
				}
				if (compTurn('O')){
					done=true;
					System.out.println("I won");
					stat.incrementComputerWins();
					break;
				}
			  }  //end of for loop;
		    if (!done){
		    	if (!humanTurn('X')) {
		    		System.out.println("\n\nA tie!");
		    		stat.incrementTies();
		    	}
		    	else {
		    		System.out.println("You won!!!");
		    		stat.incrementUserWins();
		    	}
			}
	  }
	  
	  /**
	   * Same logic as above, only the first computer's move happens before
	   * the loop. We do not need to check for winning combination here, 
           * since  comp can't win after one move. After the loop we check if 
           * the game is done. If not, report a tie and
	   * update statistics.
           * @return void.
	   */
	  
	  public static void compFirst(){
	    
		  boolean done = false;
		  
		  for (int i = 0; i < END_BEFORE_FOUR; i++) {
			  if (compTurn('O')){
				  done=true;
				  System.out.println("I won");
				  stat.incrementComputerWins();
				  break;
			  }
			  if (humanTurn('X')) {
				  done=true;
				  System.out.println("You won!!!");
				  stat.incrementUserWins();
				  break;
			  }
		  } //end of for loop;
		  if (!done){
			  if (!compTurn('0')) {
				  System.out.println("\n\nA tie!");
				  stat.incrementTies();
			  }
			  else {
				  System.out.println("I won");
				  stat.incrementComputerWins();
			  }
		  }
		  
	  }
	  
	public static void main(String[] args) {
		
		// input from the user, if he wants to play another game
		String playAgain=""; 

		// input from the user, if he wants to clear stats
		String clearStats =""; 
		
		do {      //play until 'n' is pressed
		
		    clearBoard(board);   //clear the baord

		    //Generate Random Assignment, determines who goes first;
		    int move = whoStarts();
		    if (move == 0) {
			    System.out.println("\nI start first. " +
                            "I choose X and you get 0");
			    compFirst();
		    }
		    else{ 
			    System.out.println("\nYou start first. " +
                            "You get X and I get 0");
			    humanFirst(); 
		    }
		
		    //Print statistics and ask if a user wants to repeat a game
		    stat.printStats();
		
		    System.out.print("Play again? ");	  
		    playAgain = input.next();
		
		
		    //if user enters 'y', clear statistics and restart the game
		    //If user enters 'n', quit the game
		    if(playAgain.equals("y")) {
			
			System.out.print("clear stats? ");
			
			clearStats = input.next();

                        if (clearStats.equals("y")) {
			        stat.reset();
			        clearStats = "";
                            System.out.println("stat got reset");
                        }
		    }
			
		    } while(playAgain.charAt(0)!='n'); 
	    

	}
}
