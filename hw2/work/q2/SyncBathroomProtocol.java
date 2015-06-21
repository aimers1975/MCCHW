// TODO
// Use synchronized, wait(), notify(), and notifyAll() to implement this
// bathroom protocol

public class SyncBathroomProtocol implements BathroomProtocol {

  int femaleCount;
  int maleCount;
  int maleWait;
  int femaleWait;


  public SyncBathroomProtocol() {
      femaleCount = 0;
      femaleWait = 0;
      maleCount = 0;
      maleWait = 0;
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
      boolean setWait = false;
      try {
        if(female) {
            System.out.println("Female trying to enter. Female count: " + femaleCount + " Female wait: " + femaleWait + " Male count: " + maleCount + " Male wait: " + maleWait);
            while(maleCount > 0 || maleWait > 0) {
                while(maleWait > 0) { wait();}
                if(!setWait) { 
                    femaleWait++; 
                    setWait=true;
                    System.out.println("Female waiting. Female count: " + femaleCount + " Female wait: " + femaleWait + " Male count: " + maleCount + " Male wait: " + maleWait);
                }
                wait();
            }
            femaleCount++;
            if(setWait) {femaleWait--;}
            System.out.println("Female entered. Female count: " + femaleCount + " Female wait: " + femaleWait + " Male count: " + maleCount + " Male wait: " + maleWait);
            notify();

        } else {
            System.out.println("Male trying to enter. Female count: " + femaleCount + " Female wait: " + femaleWait + " Male count: " + maleCount + " Male wait: " + maleWait);
            while(femaleCount > 0 || femaleWait > 0) {
                while(femaleWait > 0) { wait();}
                if(!setWait) {
                    setWait=true;
                    System.out.println("Male waiting. Female count: " + femaleCount + " Female wait: " + femaleWait + " Male count: " + maleCount + " Male wait: " + maleWait); 
                    maleWait++; 
                }
                wait();
            }
            maleCount++;
            if(setWait) {maleWait--;}
            System.out.println("Male entered. Female count: " + femaleCount + " Female wait: " + femaleWait + " Male count: " + maleCount + " Male wait: " + maleWait);
            notify();
        }
      } catch(InterruptedException e) {
          e.printStackTrace();

      }

  }

  public synchronized void openDoor(boolean female) {
      if(female) {
          femaleCount--;
          System.out.println("Female exited. Female count: " + femaleCount + " Female wait: " + femaleWait + " Male count: " + maleCount + "Male wait: " + maleWait);
          notify();
      } else {
          maleCount--;
          System.out.println("Male exited. Female count: " + femaleCount + " Female wait: " + femaleWait + " Male count: " + maleCount + "Male wait: " + maleWait);
          notify();
      }
  }


}
