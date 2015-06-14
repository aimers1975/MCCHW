public class CounterThread extends Thread {
	
	LockCounter counter;
	int increments;
	int threadId;
	boolean debug = true;

	public CounterThread(LockCounter counter,int increments,int threadId) {
		this.counter = counter;
		this.increments = increments;
		this.threadId = threadId;
		counter.threadId = threadId;
		debug("This thread is: " + threadId);
	}

	public void run() {
		for (int i=0; i<increments; i++) {
			counter.increment();
		}
	}

	private void debug(String msg) {
        if(debug) {
            System.out.println(this.getClass().getSimpleName() + ": " + msg);
        }
    }
}