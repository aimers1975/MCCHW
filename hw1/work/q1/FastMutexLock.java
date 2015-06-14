// TODO 
// Implement Fast Mutex Algorithm

public class FastMutexLock implements MyLock {

    int x,y = -1;
    boolean[] flagup;
    int processes;
    boolean debug = true;
    public FastMutexLock(int numThread) {
      // TODO: initialize your algorithm
      flagup = new boolean[numThread];
      processes = numThread;
    }

    @Override
    public void lock(int myId) {
      // TODO: the locking algorithm
        while(true) {
            flagup[myId] = true;
            x = myId;
            
            if(y != -1) {
                flagup[myId] = false;
                while(y != -1) { wait(myId);}
                continue;
            } else {
                y = myId;
                if(x == myId) {
                    return;
                } else {
                    flagup[myId] = false;
                    for(int j = 0; j<processes; j++) {
                        while(flagup[j] == true) { wait(myId); }
                        if(y == myId) return;
                        else {
                            while(y != -1) { wait(myId); }
                            continue;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void unlock(int myId) {
      // TODO: the unlocking algorithm
        y = -1;
        flagup[myId] = false;
    }

    public void wait(int myId) {
      try {
        Thread.sleep(5);
      }catch(Exception e) {
        debug("Exception during sleep for thread: " + myId);
        e.printStackTrace();
      }
    }

    private void debug(String msg) {
        if(debug) {
            System.out.println(this.getClass().getSimpleName() + ": " + msg);
        }
    }
}

