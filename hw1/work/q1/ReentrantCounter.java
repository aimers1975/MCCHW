import java.util.concurrent.locks.ReentrantLock;
// TODO
// Use ReentrantLock to protect the count
public class ReentrantCounter extends Counter {

	ReentrantLock relock;

	public ReentrantCounter() {
		relock = new ReentrantLock();
	}
    @Override
    public void increment() { 		
		relock.lock();
		count++;
		relock.unlock();  
    }
}
