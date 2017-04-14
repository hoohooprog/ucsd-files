package hw1;

/**
* A class that implements a simple statistic tracking array
* @version 1.0
* @author  jingyi tay
* ID: A99014740
* class login: cs12wamf
* @since   2017-01-09
*/ 



public class Stats {

//private array of size 3
private int[] Stat = new int[3];

/**
 * creates an empty array 
 */
public Stats()
{
// Nothing to do here
}

/**
 * Creates an array with specified values
 * for JUnit testers
 * @param first: assigned to the first cell
 * @param second: assigned to the second cell
 * @param third: assigned to the third cell
 */
public Stats(int first, int second, int third)
{
	Stat[0] = first;
	Stat[1] = second;
	Stat[2] = third;
}

/**
 * Calculates the number of games played
 * @return The total number of played games
 */
public int getTotalGames() {
   int totalScore = Stat[0] +Stat[1] + Stat[2];
   return totalScore;
}

/**
 * Increases the count of the number of games user wins
 *
 */
public void incrementUserWins() {
	Stat[0]++;
}

/**
 * Increases the count of the number of games computer wins
 */
public void incrementComputerWins() {
	Stat[1]++;
}

/**
 * Increases the count of the number of games that are tied
 */
public void incrementTies() {
	Stat[2]++;
}


/**
 * 
 * @param choice: depending on the value of choice 
 * the corresponding average (percent) is returned:
 * if choice is 0, return the average for a user
 * if choice is 1, return the average for a computer
 * if choice is 2, return the average for ties
 * otherwise return -1
 * @return percentage of won games or ties, depending on the 
 * parameter choice.
 */
public int averageGames(int choice)
{
	//TODO: Create a method that calculates the average
    double gamesWonInDec = ((double)Stat[choice] / getTotalGames());

    System.out.println("games won dec " + gamesWonInDec);

    System.out.println("stat choice " + Stat[choice]);
    
	return ((int)(gamesWonInDec * 100));
           
}

/**
 * prints the percentage of games won by user and computer
 * and the percentage of games tied
 */
 public void printStats()
 {
	 System.out.println("  I won: "+averageGames(1) + "%   You won: " + averageGames(0)+ "%    We tied: "+ averageGames(2)+"%\n");
     System.out.println("Total games played: " + getTotalGames());
 }

/**
 * resets the percentage of games won by the user and computer
 * and the percentage of games tied
 */
public void reset() {
	Stat[0]=0;
	Stat[1]=0;
	Stat[2]=0;

}

/**
 * creates an incorrect reset method
 */
public void resetWrong()
{
	// TODO: Create an incorrect reset method
	Stat[0] = 1;
	Stat[1] = 1;
	Stat[2] = 1;
}


}

