#include <stdio.h>

/*
 * Filename: hw5_C.c
 * Author: Jingyi Tay
 * Userid: A99014740
 * Login: cs12wamf
 * Description: A program that creates an array of integers and then
 *              find the largest integer and the index it is in.
 * Date: 2/13/2017
 * Source of Help: piazza, www.cprogramming.com
 */


/* function declaration */
/* Returns the index of max element */
int max_elem_location(int[], int); 


/*
 * Function prototype: int main()
 * Description: Main driver function.
 * Parameters: None.
 * Side Effects: Read from stdin and print to stdout
 * Error Conditions: None.
 * Return Value: 0 indicating successful termination
 */
int main(){

    // var to store the num of elem in array
    int numOfElem;
  
    // var to store the index of largest elem
    int location;

    printf("Enter the number of elements that will be in the array\n");

    // store user-specified size of array
    scanf("%d", &numOfElem);

    // output what size user chose
    printf("Enter %d integers\n", numOfElem);

    // if user chose 0, this will be the output
    if (numOfElem == 0){
        printf("No max value because the array has nothing in it!\n");
        return;
    }

    // declare user specified size of array
    int arrayInt[numOfElem];

    // go into fn and get location of max element
    location = max_elem_location(arrayInt, numOfElem);
    
    printf("Max element location = %d and value = %d.\n", location+1, arrayInt[location] );


    return 0;

}


/*
 * Function prototype: int int max_elem_location(int a[], int n)
 * Description: function to get the location (index) of the maximum element
 *              in an array
 * Parameters: arg1 - int a[] -- the input array to be searched
 *             arg2 - int n   -- the size (length) of array
 * Side Effects: None.
 * Error Conditions: None.
 * Return Value: the location (index) of the maximum element in such array
 */
int max_elem_location(int a[], int n) {
  
    // iterator for loop
    int iter;
    // var to store largestElem for comparison
    int largestElem;
    // var to store index of largestElem if comparison return true
    int largestElemIndex;

    // get 1st input of user first and use it as comparison
    scanf("%d", &a[0]);

    largestElem = a[0];

    // store the rest of the elements in the rest of the index
    // and update index of largest elem and largest elem
    for (iter=1; iter<n; iter++){

        scanf("%d", &a[iter]);

        if ( a[iter] >= largestElem ){

            largestElemIndex = iter;
            largestElem = a[iter];
                
        }
    }

    // return index of largest element
    return largestElemIndex;
}








