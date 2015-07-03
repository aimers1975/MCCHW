// TODO
// Implement the MCS Lock 

import java.util.concurrent.atomic.*;

public class MCSLock implements MyLock {

	volatile AtomicReference<Node> tailNode;	  
	volatile ThreadLocal<Node> myNode;


	public class Node {
		boolean locked = false;
		Node next = null;
	}

    public MCSLock(int numThread) {
    	tailNode = new AtomicReference<Node>();
    	myNode = new ThreadLocal<Node>(){ protected Node initialValue() { return new Node();}};
    }

    @Override
    public void lock(int myId) {
    	Node thisNode = myNode.get();
		Node predecessor = tailNode.getAndSet(thisNode);
		if(predecessor != null) {
			thisNode.locked = true;
			predecessor.next = thisNode;
			while(thisNode.locked) { 
				try {
					Thread.sleep(1000);
				} catch(Exception e) {
					e.printStackTrace();
				}
			} 
		} 
    }

    @Override
    public void unlock(int myId) {
    	Node thisNode = myNode.get();
		if(thisNode.next == null) {
			if(tailNode.compareAndSet(thisNode,null))
				return;
			while(thisNode.next == null) { 
				try {
					Thread.sleep(1000);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}  
		}
		thisNode.next.locked = false;
		thisNode.next = null;
    }
}
