/*Write a Java class PLock that provides priority based locks. 
The class provides the following methods requestCS(int priority)
and releaseCS(). The priority is either 0 denoting low priority or 
1 denoting high priority. Whenever the critical section becomes available, 
a thread that is waiting with the highest priority is given the access. 
If there are multiple threads with the same priority level waiting for the critical section, 
then any one of them may enter the critical section. You should guarantee that a low priority 
thread does not enter the critical section if a thread with a higher priority is waiting.*/

import java.util.concurrent.locks.*;

public class PLock2 {

	final ReentrantLock lock = new ReentrantLock(); 
   	Condition noHigh = lock.newCondition();
   	int numHigh;
   	boolean locked;


	public PLock() {	
		numHigh = 0;
		locked = false;
	}

	public void requestCS(int priority) {
		//is there a higher priority thread waiting, if so wait
		try {
			lock.lock();
			if(priority == 1) {
				numHigh++;
				System.out.println("Numhigh is now: " + numHigh);
				if(locked) {
					System.out.println("Pri " + priority + " Waiting for unlock.");
				} else {
					System.out.println("Pri: " + priority + " found lock unlocked.  Proceeding." );
				}
				while(locked) {
					noHigh.await();
				}
				System.out.println("High priority got the lock.");
				locked = true;
				numHigh--;
			} else {
				System.out.println("Low pri waiting for signal");
				while(numHigh > 0) { 
					noHigh.await(); 
				}
				System.out.println("Low pri got the signal, waiting for lock");
				while(locked) {noHigh.await();}
				locked = true;
				System.out.println("Low pri locked.");
				lock.lock();
			}
			lock.unlock();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void releaseCS() {
		try {
			lock.lock();
			System.out.println("Unlocking.");
			locked = false;
			noHigh.signal();
			lock.unlock();
		} catch (Exception e){
			lock.unlock();
		}
	}

}