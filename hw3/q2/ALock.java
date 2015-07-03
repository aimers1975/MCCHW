import java.util.concurrent.atomic.AtomicInteger;
// TODO 
// Implement Anderson’s array-based lock

public class ALock implements MyLock {
    ThreadLocal<Integer> mySlot = new ThreadLocal<Integer>(){
          protected Integer initialValue() { return 0;}
     };
     AtomicInteger tailSlot;
     boolean[] available;
     int size;
    public ALock(int numThread) {
      // TODO: initialize your algorithm
      size = numThread;
      tailSlot = new AtomicInteger(0);
      available = new boolean[size];
      available[0] = true;
    }

    @Override
    public void lock(int myId) {
          int slot = tailSlot.getAndIncrement() % size;
          mySlot.set(slot);
          while (!available[slot]) {};
    }

    @Override
    public void unlock(int myId) {
          int slot = mySlot.get();
          available[slot] = false;
          available[(slot + 1) % size] = true;      
    }
}

