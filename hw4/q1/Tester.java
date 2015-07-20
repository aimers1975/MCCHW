public class Tester extends Thread {
	
	int id;
	LockFreeQueue<Integer> queueing;
	int range;

	public Tester(int id, int range, LockFreeQueue<Integer> queueing) {
        this.id = id;
        this.queueing = queueing;
        this.range = range;
	}

	public void run() {
		for(int i=id; i<range; i++) {
			System.out.println("Enqueing: " + i);
			queueing.enq(new Integer(i));
		}
		System.out.println("Dequeued: " + queueing.deq().toString());

	}

	public static void main(String[] args) {
		LockFreeQueue<Integer> testqueue = new LockFreeQueue<Integer>();
		for(int i=0; i<20; i=i+5) {
			Tester thisThread = new Tester(i, i+5, testqueue);
			thisThread.start();
		}
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Final queue: \n"  + testqueue.toString());

	}
}