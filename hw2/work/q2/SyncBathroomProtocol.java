// TODO
// Use synchronized, wait(), notify(), and notifyAll() to implement this
// bathroom protocol

public class SyncBathroomProtocol implements BathroomProtocol {


  	boolean doorClosed;
  	int occupantCount;
  	volatile boolean entering;
  	int femaleCheck;
  	int maleCheck;

	public SyncBathroomProtocol() {
  		doorClosed = false;
  		occupantCount = 0;  // positive for femails/negative for males
  		entering = false;
  		femaleCheck = 0;
  		maleCheck = 0;		
	}



  public void enterMale() {
  		closeDoor(false);

  }

  public void leaveMale() {
  		openDoor(false);

  }

  public void enterFemale() {
  		closeDoor(true);
  }

  public void leaveFemale() {
  		openDoor(true);
  }

  public synchronized void closeDoor(boolean female) {
  	try {
  		if(doorClosed) {
  			System.out.println("The door is closed");
  		} else {
  			System.out.println("The door is opened");
  		}

  		if(female) {
  			while(doorClosed && (occupantCount < 0)) { 
  				System.out.println("Female is waiting.");
  				wait();
  			}
  			System.out.println("Female door is open, trying to enter.");
  			while(entering) { 
  				System.out.println("Female is waiting for someone to finish entering.");
  				wait();
  			}
  			entering = true;
  			System.out.println("Female got into the bathroom.");
  			occupantCount++;
  			femaleCheck++;
  			System.out.println("Male check is: " + maleCheck + "Female check is: " + femaleCheck);
  			doorClosed = true;
  			entering = false;
  			notifyAll();
  		} else {
  			while(doorClosed && (occupantCount > 0)) { 
  				System.out.println("Male is waiting for the door to open.");
  				wait();
  			}
  			System.out.println("Male door is open, trying to enter.");
  			while(entering) { 
  				System.out.println("Male is waiting for someone to finish entering.");
  				wait();
  			}
  			entering = true;
  			System.out.println("Male got into the bathroom.");
  			occupantCount--;
  			maleCheck++;
  			System.out.println("Male check is: " + maleCheck + "Female check is: " + femaleCheck);
  			doorClosed = true;
  			entering = false;
  			notifyAll();  			
  		}
  	} catch (InterruptedException e) {
  		e.printStackTrace();
  	}
  }

  public synchronized void openDoor(boolean female) {
  	try {
  		if(female && (occupantCount > 0)) {
  			while(entering) { wait();}
  			entering = true;
  			occupantCount--;
  			femaleCheck--;
  			System.out.println("Male check is: " + maleCheck + "Female check is: " + femaleCheck);
  			if(occupantCount == 0) {
  				doorClosed = false;
  			}
  			entering = false;
  			notify();
  		} else if(!female && occupantCount < 0) {
  			while(entering) { wait();}
  			entering = true;
  			occupantCount ++;
  			maleCheck--;
  			System.out.println("Male check is: " + maleCheck + "Female check is: " + femaleCheck);
  			if(occupantCount == 0) {
  				doorClosed = false;
  			}
  			entering = false;
  			notify();
  		}
  	} catch (InterruptedException e) {
  		e.printStackTrace();
  	}
  }

}
