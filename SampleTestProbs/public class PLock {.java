import java.util.concurrent.locks.*;

// TODO
// Use locks and condition variables to implement this bathroom protocol
public class PLock {
  // declare the lock and conditions here

   	final ReentrantLock protLock = new ReentrantLock(); 
   	Condition numHigh = protLock.newCondition();
   	Condition locked = protLock.newCondition();
   	boolean locklock;
  	int numhighpri;

  	public LockBathroomProtocol() {
  	    locklock = false;
        numhighpri = 0;
  	}


	public void requestCS(int priority) {
		
		protLock.lock();
		System.out.println("");
	   	try {
	   		if(priority == 1 ) {
	   			numhighpri++;
		   		while (numhighpri > 1 ) { numHigh.await();}
		   		while (locked) { locked.await();}
		   		locked = true;
		   		numhighpri--;
		   		numHigh.signal();
		   	} else {
		   		while (numhighpri > 0 ) { numHigh.await();}
		   		while (locked) { locked.await();}
		   		locked = true;
		   	}
		}

		       
		} catch(InterruptedException e) {
			e.printStackTrace();

		} finally {
			protLock.unlock();
		}
	  }

	  public void leaveMale() {
	  	  protLock.lock();
	  	  locked = false;
	  	  locked.signal();
	  	  protLock.unlock();
	  	  

	  }

}
