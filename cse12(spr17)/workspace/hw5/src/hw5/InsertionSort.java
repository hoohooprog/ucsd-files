package hw5;

import java.util.Arrays;

public class InsertionSort {
	   public static void insertionSort(int numbers [], int numbersSize) {
	      int i = 0;
	      int j = 0;
	      int temp = 0;  // Temporary variable for swap

	      for (i = 1; i < numbersSize; ++i) {
	         j = i;
	         // Insert numbers[i] into sorted part 
	         // stopping once numbers[i] in correct position
	         while (j > 0 && numbers[j] > numbers[j - 1]) {

	            // Swap numbers[j] and numbers[j - 1]
	            temp = numbers[j];
	            numbers[j] = numbers[j - 1];
	            numbers[j - 1] = temp;
	            --j;
	         }
	         System.out.println(i+"th iteration: "+ Arrays.toString(numbers));
	      }
	   }

	   public static void main(String [] args) {
	      int numbers [] = { 5,7, 1, 9,45,23,7 };
	      final int NUMBERS_SIZE = 7;
	      int i = 0;

	      System.out.print("UNSORTED: ");
	      for (i = 0; i < NUMBERS_SIZE; ++i) {
	         System.out.print(numbers[i] + " ");
	      }
	      System.out.println();

	      /* initial call to quicksort with index */
	      insertionSort(numbers, NUMBERS_SIZE);

	      System.out.print("SORTED: ");
	      for (i = 0; i < NUMBERS_SIZE; ++i) {
	         System.out.print(numbers[i] + " ");
	      }
	      System.out.println();

	      return;
	   }
	}