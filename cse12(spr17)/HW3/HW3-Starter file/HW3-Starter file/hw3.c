/*
 * Filename: 
 * Author: 
 * Userid: <your PID>
 * Login: cs12sxx
 * Description: 
 * Date:
 * Source of Help:
 */

#include <stdio.h>
 
/* function declaration */
/* Returns the location(index+1) of a given element if found*/
int linear_search(int[], int, int); 

/*
 * Function prototype: int main()
 * Description: Main driver function.
 * Parameters: None.
 * Side Effects: Read from stdin and print to stdout
 * Error Conditions: None.
 * Return Value: 0 indicating successful termination
 */
int main() {
  int location; //you will need more variables
  int UserInput;  
  int numInput;
  int searchNum;

  printf("Enter the number of elements that will be in the array\n");
  
  /* TODO: use scanf to get an input (size of the array) from a user */
  scanf("%d", &UserInput);
 
  int array[UserInput];
  printf("Enter %d integers\n", UserInput);
  getchar();  

  /* TODO: Now prompt for the numbers and add them to an array */
  for (int i=0; i<UserInput; i++){
    
    numInput = getchar() - '0';
    getchar();
    array[i] = numInput;
  }
 
  printf("Enter the element to be searched\n");
  /* TODO: use scanf to get the element */
  scanf("%d", &searchNum); 
 
  location = linear_search(array, UserInput, searchNum);
  if(!location){
	printf("Element not found.\n");
  }
  else{
  	printf("Element found at location = %d\n",location );
  }
  return 0;
}
 
/*
 * Function prototype: int linear_search(int a[], int n, int ele)
 * Description: function to get the location (index+1) of the given element in the array
 * Parameters: arg1 - int a[] -- the input array to be searched
 *             arg2 - int n   -- the size (length) of array
 *             arg3 - int ele -- the element to be searched
 * Side Effects: None.
 * Error Conditions: None.
 * Return Value: the location (index+1) of the element in the array
 */
int linear_search(int a[], int n, int ele) {
  // TODO
  int location=0;
  int i;
  for (i=0; i<n; i++){
    if (ele == a[i]){
      location = i; 
    }
  }
  
  if (location != 0){
  
    location +=1;

  }
  else{
    location = 0;
  }
  return location;

}







