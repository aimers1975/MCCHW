// TODO
// Implement the MCS Lock 

public class MCSLock implements MyLock {

	//(shared) tailNode;
	//(ThreadLocal) myNode;
	//(ThreadLocal) predessor;

	public class Node {
		boolean Locked = false;
		Node next = null;
	}

    public MCSLock(int numThread) {
    }

    @Override
    public void lock(int myId) {
	//predecessor = tailNode.getAndSet(myNode);
	//If(predecessor != null) {
	//	myNode.locked = true;
	//	pred.next = myNode;
	//	while(myNode locked) { wait();}  // wait on local memory vs shared

    }

    @Override
    public void unlock(int myId) {
	//If(myNode.next == null) {
	//	If(tail.compareAndSet(myNode,null))
	//		Return;
	//	While(myNode.next == null) { wait();}  
	//}
	//myNode.next.locked = false;
	//myNode.next = null;

    }
}
