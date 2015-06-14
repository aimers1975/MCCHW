import java.util.concurrent.*;
import java.util.*;

class ParallelSearch {

	public static ExecutorService threadPool = Executors.newCachedThreadPool();


	public static int parallelSearch(int x, int[] A, int numThreads) {
		 

		if(A == null) return -1; 
		ArrayList<Future<Integer>> threadVals = new ArrayList<Future<Integer>>();
		if(numThreads > A.length) {
			System.out.println("Array is too small for the number of threads");
		}
		int eachPartSize = A.length/numThreads;
		int lastPartSize = A.length%numThreads;
		int currentStart = 0;
		int currentSize = 0;
		System.out.println("each: " + eachPartSize + "\n" + "leftover: " + lastPartSize + "\n");
		for(int i=0; i< numThreads; i++) {
			if(lastPartSize >0) {
				currentSize = eachPartSize + 1;
			} else {
				currentSize = eachPartSize;
			}
			SearchThread thisThread = new SearchThread(A,x,currentStart,currentSize);

        	Future<Integer> f1 = threadPool.submit(thisThread);
        	threadVals.add(f1);
    	    
			if(lastPartSize > 0) {
				currentStart = currentStart + eachPartSize + 1;
				lastPartSize--;
			} else {
				currentStart = currentStart + eachPartSize;
			}
		}
		for(int i=0; i<threadVals.size(); i++) {
			try {
				int returnVal = (threadVals.get(i)).get();
				if(returnVal != -1) {
					threadPool.shutdown();
					return returnVal;
				}
			}
			catch(Exception e) { System.err.println (e); }
		}
		threadPool.shutdown();	
		return -1;
	}


  	public static void main(String[] args) {
  	    int[] mySearchArray = {0,1,5,2,10,23,4,6,12,17,100,22,66,34};
  	    int myResult = ParallelSearch.parallelSearch(34,mySearchArray,3);
  	    System.out.println("The result is: " + myResult);

  	}
}