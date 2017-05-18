package hw5;

public class partition {

	int output;
	
	static int part(int[] arr, int p, int r){
		int store=0;
		int x = arr[r];
		int i = p-1;
		
		for (int j=p; j<=(r-1); j++){
			if (arr[j] <= x){
				i = i + 1;
				System.out.println("exchange " + arr[i] + " with " + arr[j]);
				store = arr[i];
				arr[i] = arr[j];
				arr[j] = store;
				
				for (int z=0; z<arr.length; z++){
				
					System.out.print(arr[z] + " ");
				}
			}
			System.out.println();
		}
		store = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = store;
		
		return i+1;
	};
	
	public static void main( String[] args )
	{
		int returnNum;
		int arr[] = {9, 5, 11, 3, 2, 8, 4, 6};
		
		returnNum = part(arr, 0 ,7);
		
		System.out.println("num returned is: " + returnNum);
	
	}
}

