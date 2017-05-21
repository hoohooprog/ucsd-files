package hw7;
/**
 * Name: Jingyi Tay <<< --- Replace with Your Name
 * Login: cs12wamf <<< --- Use your cs11f course-specific account name
 * Date: February 26, 2017
 * File: SearchEngine.java
 * Sources of Help: stackoverflow, piazza
 *
 *
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class SearchEngine {

	BSTree searchTree = new BSTree();
	Iterator<String> iter = searchTree.iterator();
	
	/*Populate a BST from a file
	 * @param searchTree - BST to be populated
	 * @param fileName - name of the input file
	 * @returns false if file not found, true otherwise
	 */
	public static boolean populateSearchTree(BSTree<String> searchTree, String fileName) {
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				//read two lines - one for document and the next line for the list of keywords
				String document = scanner.nextLine().trim();
				//string array stores var-length argument list
				String keywords[] = scanner.nextLine().split(" ");
				
				//create BSTNodes for each key from keywords array
				for (int i=0; i<keywords.length; i++){
					// change keyword to lower case before inserting
					String lowerCasedKey = keywords[i].toLowerCase();
					// change document name to lower case before inserting
					String lowerCasedDoc = document.toLowerCase();
					searchTree.insert(lowerCasedKey);
					
				    searchTree.insertInformation(lowerCasedKey
								, lowerCasedDoc);
					
				}			
				
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.out.println("\nFile not found!!");
			return false;
		}
		return true;
	}
	
	
	/**
	 * Search a query in a BST
	 * @param searchTree - BST to be searched
	 * @param fileName - query string
	 * @returns LinkedList of documents in which the query string is found
	 */
	public static void searchMyQuery(BSTree<String> searchTree, String query) {
		
		// create arr by using numOfWS+1, &store words 
		String queryKeyArr[];
		String queries = query.toLowerCase();
		// store all the docs that all the queries share
		LinkedList<String>documents = new LinkedList<String>();
		// store all the docs of query
		LinkedList<String> totalDocs = new LinkedList<String>();
		// store docs of separate keys
		LinkedList<String> tempInfo=null;
		
		// store any docs that might not have already appeared, 
		// so to print for each individual word
		LinkedList<String> tempDoc = new LinkedList<String>();
		
		//String split fn stores each element in array[]
		queryKeyArr = queries.split(" ");
		
		// print full query
		System.out.println("Query = \"" + queries + "\"");
		
		//find the 1st key that exists and populate documents List
		for (int i=0; i<(queryKeyArr.length); i++){
			if (searchTree.findKey(queryKeyArr[i])){
				documents.addAll(searchTree
						.findMoreInformation(queryKeyArr[i]));
				break;
			}
		}
		
		// search for additional documents by other keys that aren't inside
		// documents List and populate it
		for (int i=0; i<(queryKeyArr.length); i++){
			
			if (searchTree.findKey(queryKeyArr[i])){
				
				// iterate thru key's documents 
				for (int j=0; j<documents.size();j++){
					
					// if key's doc already inside documentList, don't add
					if  (searchTree.findMoreInformation(queryKeyArr[i]).contains(documents.get(j))){
					}else{
						documents.remove(j);
					}
				}
			}
			
		}
		
		// check one last time that word in document List is contained in all the keys
		// if not remove, then print
		for (int i=0;i<queryKeyArr.length; i++){
			
			// if one of the keys in array is nonexistent in tree, it means
			// none of the keys are shared - quit loop
			if (!searchTree.findKey(queryKeyArr[i])){
				
				for (int j=0; j<documents.size();j++){
					documents.remove();
				}
				break;
			}
			else if (searchTree.findKey(queryKeyArr[i])){
				for (int j=0; j<documents.size();j++){
					if(searchTree.findMoreInformation(queryKeyArr[i]).contains(documents.get(j))){
					} else{
						documents.remove(j);
					}
				}
			}
		}
		print(query,documents);
		
		
		for (int i=0; i<(queryKeyArr.length);i++){
			//remove all elements before iteration
			//tempDoc is used to print docs for individual Key(if any)
			tempDoc.clear();
			
			// do sth if key isn't found
			if (!searchTree.findKey(queryKeyArr[i])){
				print(queryKeyArr[i],null);
			}
			// do sth if key's doc matches all documents
			if (!documents.isEmpty() && searchTree.findKey(queryKeyArr[i])){
			    if (documents.containsAll(searchTree.findMoreInformation(queryKeyArr[i]))){
				    return;
			    }
			}
			
			if (searchTree.findKey(queryKeyArr[i])){
			    // do sth if documents only have some of queryKeyArr[i]'s docs
			    for (int j=0; j<(searchTree.findMoreInformation(queryKeyArr[i])).size();j++){
				
				    //init another LL to make typing convenient
				    // relatedInfo of the particular key
				    tempInfo = searchTree.findMoreInformation(queryKeyArr[i]);
				
				    if ( !documents.isEmpty()){
				        if (documents.contains(tempInfo.get(j))){
					        //don't do anything
				        }
				        else{
				    	    tempDoc.add(tempInfo.get(j));
				        }
				    }
				    else{
					    tempDoc.add(tempInfo.get(j));
				    }
				
				    documents.add(tempInfo.get(j));
				    				
			    }
			    // print docs for the individual key
			    print( queryKeyArr[i] , tempDoc );
			}
		}
		
	}
	
	
	/*Print method 
	 * @param query input
	 * @param documents - result of SearchMyQuery
	 */
	 
	public static void print(String query, LinkedList<String> documents) {
		if(documents==null || documents.isEmpty())
			System.out.println("The search yielded no results for "+query);
		else {
			Object[] converted = documents.toArray();
			Arrays.sort(converted);
			System.out.println("Documents related to "+ query +" are: "+Arrays.toString(converted));
		}
	}
	
	public static void main( String[] args ) {
		
		if(args.length < 2) {
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		BSTree<String> searchTree = new BSTree<>();
		
		String fileName = args[0];
		String query = args[1];
		
		//Create my BST from file
		boolean check = populateSearchTree(searchTree, fileName);
		if(check == false) {
			System.out.println("\nUnable to create search tree");
		}
		
		searchMyQuery(searchTree, query);
	}
}
