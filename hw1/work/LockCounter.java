// TODO
// Use MyLock to protect the count
// This counter can use either BakeryLock or FastMutexLock to protect the count

public class LockCounter extends Counter {

	MyLock counterlock;
	int threadId;
	boolean debug = true;

    public LockCounter(MyLock lock) {
    	this.counterlock = lock;
    }

    @Override
    public void increment() {
    	debug("Trying to lock" + threadId);
    	counterlock.lock(threadId);
    	count++;
    	debug("Trying to unlock" + threadId);
    	counterlock.unlock(threadId);
    }

    private void debug(String msg) {
        if(debug) {
            System.out.println(this.getClass().getSimpleName() + ": " + msg);
        }
    }
}
