public class CounterThread extends Thread {
	
	LockCounter counter;
	int increments;
	int threadId;
	boolean debug = true;

	public CounterThread(LockCounter counter,int increments,int threadId) {
		this.counter = counter;
		this.increments = increments;
		this.threadId = threadId;
	}

	public void run() {
		//debug("Thread " + threadId + "has " + increments + " counts");
		for (int i=0; i<increments; i++) {
			((MyLock)counter.counterlock).lock(threadId);
			//debug("Thread: " + threadId + " has the lock.");
			//debug("Thread " + threadId + " incrementing " + i + "count");
			counter.increment();
			//debug("Thread: " + threadId + " releasing the lock.");
			((MyLock)counter.counterlock).unlock(threadId);
		}
	}

	private void debug(String msg) {
        if(debug) {
            System.out.println(this.getClass().getSimpleName() + ": " + msg);
        }
    }
}