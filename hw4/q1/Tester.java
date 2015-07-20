public class Tester {
	
	public Tester() {

	}

	public static void main(String[] args) {
		LockFreeQueue<Integer> testqueue = new LockFreeQueue<Integer>();
		testqueue.enq(new Integer(3));
		testqueue.enq(new Integer(5));
		testqueue.enq(new Integer(7));
		Integer thisNode = testqueue.deq();
		System.out.println("Dequeued node is: " + thisNode.toString());
		thisNode = testqueue.deq();
		System.out.println("Dequeued node is: " + thisNode.toString());
		System.out.println("The Queue: \n" + testqueue.toString());
	}
}