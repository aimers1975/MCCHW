import java.util.concurrent.atomic.AtomicInteger;
// TODO 
// Implement Anderson’s array-based lock

public class ALock implements MyLock {
    ThreadLocal<Integer> mySlotIndex = new ThreadLocal<Integer>(){
          protected Integer initialValue() {
               return 0;
          }
     };
     AtomicInteger tail;
     boolean[] flag;
     int size;
    public ALock(int numThread) {
      // TODO: initialize your algorithm
      size = numThread;
      tail = new AtomicInteger(0);
      flag = new boolean[size];
      flag[0] = true;
    }

    @Override
    public void lock(int myId) {
          int slot = tail.getAndIncrement() % size;
          mySlotIndex.set(slot);
          while (! flag[slot]) {};
    }

    @Override
    public void unlock(int myId) {
          int slot = mySlotIndex.get();
          flag[slot] = false;
          flag[(slot + 1) % size] = true;      // TODO: the unlocking algorithm
    }
}

