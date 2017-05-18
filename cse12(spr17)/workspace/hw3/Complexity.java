/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12sie <<< --- Use your cs11f course-specific account name
 * Date: April 24, 2017
 * File: Complexity.java
 * Sources of Help: Piazza
 *
 * This program is used to estimate and then measure two delete methods,
 * one on the linked lists and the second one on the arrays by running
 * different sizes of array/linkedlist a fixed number of times to find the 
 * average time for each size.
 *
*/

package HW3;
import java.util.*;


/**
 * Purpose: contains array and LinkedList variables that has random numbers 
 * so methods that relate to these two variables can remove the 1st element
 * of each variable repeatedly in different sizes. The time for each size
 * is averaged over a constant.
 * @author Jingyi Tay
 * @version 1.0
 * @since April 14, 2017
 */
public class Complexity {
	
	static int ArrSizes[] = { 11000, 12000, 13000, 14000, 15000, 16000, 17000, 
	    18000, 19000};
	//repeat deletion to get average for each set size
	final static int REPEATDELETION = 10000;
	
	// initialize random num generator
    static Random rand = new Random();
    
    
	/*
	 * Method removes the 1st element in the array by iterating through the
	 * entire array and shifting all elements after the 1st array to the left
	 * @param arr the array that has elements stored to be deleted
	 */
	static void RemoveAllFront(int arr[]){
		int i=0;
		
		for (i=0; i<arr.length; i++){
			// push elements to left
			//starting from 1 to 0
			if ( i != (arr.length-1)){
			
				arr[i] = arr[i+1];
			}
		}
		
	}
	
	
	/*
	 * Method removes the 1st element in the LL using LinkedList Java method
	 * remove.
	 * @param LL is the LinkedList that contains the elements to be deleted
	 */
	static void RemoveAllFront(LinkedList LL){
		// removes and shifts elements to left
		LL.remove(1);
	}
	
	
	/*
	 * Method executes the program 
	 * @param args 
	 */
	public static void main(String [] args){
		
		LinkedList<Integer> LL = new LinkedList<Integer>();
		int intArr[];
		long AverageForIRun=0;
		
		int i;
		
		// test elements of both LL and Array
		// get test results by using each of the sizes by iteration
		for (i=0; i<ArrSizes.length; i++){
			
			ArrayList<Long> Time4IRun4Arr = new ArrayList();
			ArrayList<Long> Time4IRun4LL = new ArrayList();
			
			// intArr references new array for each length
			intArr = new int[ArrSizes[i]];
			
			// loop stores random number for each LL and array
			// for particular size
			for (int j=0; j<ArrSizes[i]; j++){
				
				LL.add(rand.nextInt());
				intArr[j] = rand.nextInt();
					
			}
			
			for (int k=0; k<REPEATDELETION; k++ ){
			    //remove 1st element of LinkedList
				// and store the time needed
				long startTime = System.nanoTime();
			    RemoveAllFront(LL);
			    long stopTime = System.nanoTime();
			    long estimatedTime = stopTime - startTime;
			    
			    // store time taken into LList
			    Time4IRun4LL.add(estimatedTime);
			    
				// remove 1st element of Array
				// and store the time needed
			    startTime = System.nanoTime();
			    RemoveAllFront(intArr);
			    stopTime = System.nanoTime();
			    estimatedTime = stopTime - startTime;
			    
			    // store time taken into array
			    Time4IRun4Arr.add(estimatedTime);
			}
			
			//print out the averages of time for linkedList to delete
			// first element at each specific size
			for (int l=0; l<Time4IRun4LL.size(); l++){
			
				AverageForIRun += Time4IRun4LL.get(l);
				
			}
			
			AverageForIRun /= Time4IRun4LL.size();
			
			System.out.println("time taken for " + i +
					"run for LL is: "+ AverageForIRun );
			
			
			//reset watch
			AverageForIRun=0;
			
			//print out the averages of time for linkedList to delete
			// first element at each specific size
			for (int l=0; l<Time4IRun4Arr.size(); l++){
				
				AverageForIRun += Time4IRun4Arr.get(l);
				
			}
			
			AverageForIRun /= Time4IRun4Arr.size();
			
			System.out.println("time taken for " + i +
					"run for Array is: "+ AverageForIRun );
		}
		
	}
	
}
