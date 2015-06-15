public class CounterThread extends Thread {
	
	Counter counter;
	int increments;
	int threadId;
	boolean debug = true;

	public CounterThread(Counter counter,int increments,int threadId) {
		this.counter = counter;
		this.increments = increments;
		this.threadId = threadId;
	}

	public void run() {
		//debug("Thread " + threadId + "has " + increments + " counts");
		for (int i=0; i<increments; i++) {
			try {
				((MyLock)((LockCounter)counter).counterlock).lock(threadId);
			} catch (Exception e) { /*debug("No lock, must be synchronized or re-entrant.");*/}
			//debug("Thread: " + threadId + " has the lock.");
			//debug("Thread " + threadId + " incrementing " + i + "count");
			counter.increment();
			//debug("Thread: " + threadId + " releasing the lock.");
			try {
				((MyLock)((LockCounter)counter).counterlock).unlock(threadId);
			} catch (Exception e) { /*debug("No lock, must be synchronized or re-entrant.");*/}
		}
	}

	private void debug(String msg) {
        if(debug) {
            System.out.println(this.getClass().getSimpleName() + ": " + msg);
        }
    }
}