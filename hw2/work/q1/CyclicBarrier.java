import java.util.concurrent.Semaphore;

public class CyclicBarrier {
  // TODO: Declare variables and the constructor for CyclicBarrier
  // Note that you can use only semaphores but not synchronized blocks and
  // locks 

  Semaphore barrier;




  public CyclicBarrier(int parties) {
    // TODO: The constructor for this CyclicBarrier
    barrier = new Semaphore(parties);

  }

  public int await() throws InterruptedException {
    // Waits until all parties have invoked await on this barrier.
    // If the current thread is not the last to arrive then it is
    // disabled for thread scheduling purposes and lies dormant until
    // the last thread arrives.
    // Returns: the arrival index of the current thread, where index
    // (parties - 1) indicates the first to arrive and zero indicates
    // the last to arrive.
    barrier.acquire();
    int arrivalIndex = barrier.availablePermits();
    while(barrier.availablePermits() > 0) {
        Thread.sleep(5);
    }
    return arrivalIndex;
  }

}
