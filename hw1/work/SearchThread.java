import java.util.concurrent.*;

class SearchThread implements Callable<Integer> {

	int[] searchArray;
	int searchInt;
	int start;
	int size;

	public SearchThread(int[] A, int searchInt, int start, int size) {
		this.searchArray = A;
		this.searchInt = searchInt;
		this.start = start;
		this.size = size;
	}

	public Integer call() {
        try {
        	for(int i=start; i<start+size; i++) {
				if(searchArray[i] == searchInt) return i;
			}
        	return -1;
    	} catch (Exception e) { System.err.println (e); return -1;}
  	}
}