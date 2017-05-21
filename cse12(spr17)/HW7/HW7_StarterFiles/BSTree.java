package hw7;

import java.util.ArrayList;

public class BSTree<T extends Comparable<? super T>>{

	private int nelems;
	private BSTNode root;

	 protected class BSTNode{

		T key;
		ArrayList<T> relatedInfo;
		BSTNode left;
		BSTNode right;
		
		//TODO BSTNode constructors and methods here
	}

	//BSTree methods here
	//BSTree_Iterator here
}
