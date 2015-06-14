// TODO
// Implement the bakery algorithm

public class BakeryLock implements MyLock {

	int processes;
	int[] number;
	boolean[] choosing;
    boolean debug = true;

    public BakeryLock(int numThread) {
    	processes = numThread;
        //debug("Number of processes is: " + numThread);
    	choosing = new boolean[processes];
    	number = new int[processes];
    	for(int i=0; i<processes; i++) {
    		choosing[i] = false;
    		number[i] = 0;
    	}
    }

    @Override
    public void lock(int myId) {
        if(myId >= choosing.length) {
            //debug("Trying to access choosing w/ID " + myId);
        }
    	choosing[myId] = true;
    	for(int j=0; j<processes; j++) {
    		if(number[j] > number[myId]) {
    			number[myId] = number[j];
    		}
    	}
    	number[myId]++;
    	choosing[myId] = false;
     	for(int j=0; j<processes; j++) {
    		while(choosing[j]) {
    			try {
    				Thread.sleep(5);
    			} catch (Exception e) { 
                    System.out.println("There was an exception in sleep: ");
                    e.printStackTrace();
                }
    		}

    		while((number[j] != 0) && 
    			((number[j] < number[myId]) || 
    			((number[j] == number[myId]) && j < myId))) {
    			try {
    				Thread.sleep(5);
    			}catch(Exception e) { 
                    System.out.println("There was an exception in sleep: ");
                    e.printStackTrace();
                }
    		}
    	}
    }

    @Override
    public void unlock(int myId) {
    	number[myId] = 0;
    }

    private void debug(String msg) {
        if(debug) {
            System.out.println(this.getClass().getSimpleName() + ": " + msg);
        }
    }
}
