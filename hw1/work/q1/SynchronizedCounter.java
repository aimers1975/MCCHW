import java.util.*;
// TODO 
// Use synchronized to protect count
public class SynchronizedCounter extends Counter {
    @Override
    public void increment() {
    	synchIncrement();
    }

    private synchronized void synchIncrement() {
    	count++;
    }
}
