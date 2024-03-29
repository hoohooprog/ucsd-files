package hw1;
import org.junit.*;
import static org.junit.Assert.*;

/*
 * A class that tests the methods in Stats.class.
 * @version 1.0
 * @author jingyi tay
 * ID: A99014740
 * login: cs12wamf
 * @since 2017-01-09
 */
public class StatsTester {

	/* TODO: Add your name, login, and ID as specified in the instructions */
		private Stats stat;

		/* this sets up the test fixture. JUnit invokes this method before
	 	   every testXXX method.  
	 	   The @Before tag tells JUnit to run this method 
		   before each test */
		@Before
		public void setUp() throws Exception {
			stat = new Stats(1, 2, 3);
		}
		
		/* The @Test tag tells JUnit this is a test to run */
		@Test
		public void testgetTotalGames() {
			System.out.println("Checking getTotalGames");
			assertEquals(6, stat.getTotalGames());
		}

		@Test
		public void testIncrements() {
			System.out.println("Checking Proper Increment");
			stat.incrementComputerWins();
			assertEquals(7, stat.getTotalGames());
			stat.incrementUserWins();
			assertEquals(8, stat.getTotalGames());
			stat.incrementTies();
			assertEquals(9, stat.getTotalGames()); 
		}
	

		@Test
		public void testReset() {
			System.out.println("Checking Reset");
			
			/* TODO: finish a test that verifies Reset */
			stat.reset();
			assertEquals(0, stat.getTotalGames());
			
		}

		/* TODO: write a test that verifies the proper calculation of the average for all games  
		 * make sure to test a case where no games were played.  
		 */
		@Test
		public void testAverageGames() {
			System.out.println("Checking Average Games");
			// give new values to each player or tied games
			stat = new Stats(4,2,4);
			//check if user average is correct
			assertEquals("user average is wrong",4/10*100, stat.averageGames(0));
			assertEquals("computer average is wrong", 2 / 10 * 100, stat.averageGames(1));
			assertEquals("tied average is wrong", 4 / 10 * 100, stat.averageGames(2));
			
		}
		
		/*TODO: write a test that verifies the resetWrong method. This test must FAIL*/
		@Test
		public void testResetWrong() {
			System.out.println("Checking Reset Wrong");
			stat.resetWrong();
			assertEquals("ResetWrong is Wrong!",0, stat.getTotalGames());
			
		}
		

}

