// TODO
// Use MyLock to protect the count
// This counter can use either BakeryLock or FastMutexLock to protect the count

public class LockCounter extends Counter {

	MyLock counterlock;
	boolean debug = true;

    public LockCounter(MyLock lock) {
    	this.counterlock = lock;
        count = 0;
    }

    @Override
    public void increment() {
    	count++;
        debug("Count is now: " + count);
    }

    private void debug(String msg) {
        if(debug) {
            System.out.println(this.getClass().getSimpleName() + ": " + msg);
        }
    }
}
