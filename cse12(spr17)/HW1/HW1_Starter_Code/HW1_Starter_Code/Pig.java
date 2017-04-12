package hw1;
import java.util.*;

class Pig {


    //Counter to maintain the human's score
    private int counterH = 0;

    //Counter to maintain the computer's score
    private int counterC = 0;

    //This variable specifies the minimum score required to win the game
    private int GAME_OVER = 20;

    //Object of Random class to get a random value
    Random rand = new Random();

    //Object of Stats class to maintain statistics
    static Stats stats = new Stats();

    // initialize random num generator
    static Random rand = new Random();

    // initialize dices
    static int die1 = 0;
    static int die2 = 0;

    // initialize turn value
    static int turnValue = 0;

    // constant that represents num of sides of die
    static int SIDES_OF_DIE = 6;

    // scanner to read user input
    Scanner reader = new Scanner(System.in);


    /**
     * This function models the Human's turn. Two dies are rolled in each turn.
     * If both the rolls result in 1, the entire score is reset to 0 and the control is passed to the computer
     * If either of the rolls is 1, the control is passed to the computer
     * If the rolls aren't equal to 1, but are identical, the score is added and the Human is forced to continue
     * If the rolls aren't equal to 1, but aren't identical, the score is added and the Human is asked 
     * for his choice between coninue and hold
     */
    void humanMove(){

        int passChoice = 0;
        
        // if passChoice == 2, exit method return to main
        while (passChoice!=2){

            die1 = rand.nextInt();
            die2 = rand.nextInt();
 
            die1 %= SIDES_OF_DIE;
            die2 %= SIDES_OF_DIE;
            
            // if both are 1, action, entire player score resets to 0, and pass turn
            if ( die1 == 1 && die2 == 1){
                resetCounterH();
                System.out.println("This turn reset computer's entire score");

                passChoice = 2;
                
            }
            // if either dice are 1, turn score resets to 0 and pass turn
            else if ( die1 == 1 || die2 == 1){
                turnValue = 0;
                System.out.println("Your total score in this turn: " + turnValue);

                passChoice = 2;   
            }    
            // if rolls are a pair, add to turn value human has to continue roll
            else if ( die1 == die2 ){
                turnValue = die1+die2;
                System.out.println("Your score in this turn: " + turnValue);
                System.out.println("You have to continue");
                //recursively enter into same function again
                humanMove();     
            }
            // else add to turn value and let human choice to roll or pass
            else {
                counterH = die1 + die2;
                System.out.println("Your score in this turn: " + turnValue);

                // let user choose whether to continue or pass
                System.out.println("Enter a number: 1 to continue, 2 to stop/hold");
                passChoice = reader.nextInt();
                
            }
        }
        
        // print score at end of turn
        System.out.println("Overall total score for you: " + counterH);
        //resets die and turnValue after player choose to end
        die1=0;
        die2=0;
        turnValue=0;

    }


    /**
     * This function models the Computer's turn. Two dies are rolled in each turn.
     * If both the rolls result in 1, the entire score is reset to 0 and the control is passed to the computer
     * If either of the rolls is 1, the control is passed to the computer
     * If the rolls aren't equal to 1, but are identical, the score is added and the Human is forced to continue
     * If the rolls aren't equal to 1, but aren't identical, the score is added and the Human is asked for his choice between coninue and hold
     */
    void  computerMove(){

       /* TODO: complete the method */
    }


    /**
     * This method displays the overall scores
     */
    private void getScores()
    {
        System.out.println("-----------------------------------");
        System.out.println("Scores.  You: " + counterH + ", Computer: " + counterC );
        System.out.println("-----------------------------------");
    }


    /**
     * This method models the game by passing control between the human and the computer.
     * Whoever succeeds in scoring at least GAME_OVER number of points first, wins the game.
     * Ensure that Human always starts first
     * @return 1: if the human wins, 2: if the computer wins
     */
    int game() {

        int winnerDecided=0;

		while (!winnerDecided)
        {
            humanMove();
            if ( counterH >= 100 ){

                winnerDecided = 1;
  
                return winnerDecided;
            }
            computerMove();
            if ( counterC >= 100 ){

                winnerDecided = 2;

                return winnerDecided;;
            }
            
        }

    }


    /**
     * This method is used to reset the Human's score before a new game.
     */
    void resetCounterH()
    {
        counterH = 0;
    }


    /**
     * This method is used to reset the Computer's score before a new game.
     */
    void resetCounterC()
    {
        counterC = 0;
    }


    public static void main(String [] args) {

        // value for program to know whether to replay or exit
        int replay=1;

        // value for program to know who win
        int whoWins;

        // initialize pig ref to pig object
        Pig pig = new Pig();

        do {
            // reset or make sure counter is reset before game is played
            resetCounterH();
            resetCounterC();
           
            // start the game and store result 
            whoWins = game();

            // if whoWins is 1, increment player score, else increment comp
            // score
            if (whoWins == 1){
                stats.incrementUserWins();
                System.out.println("You won this game!");
            } 
            else {
                System.out.println("Computer won game this game!"); 
                stats.incrementComputerWins(); 
            }

            // ask for user input and store in replay to let
            // program know exit or repeat game
            System.out.print("Do you want to play again?"); 
            System.out.println(" Enter 1 for Yes, 2 for No");

            replay = reader.nextInt();

        } while(replay==1);

        System.out.println("Thank You for playing!");
    }

}













