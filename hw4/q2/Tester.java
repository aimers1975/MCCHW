public class Tester extends Thread{
	int id;
	FineGrainedListSet<Integer> listing;
	int range;

	public Tester(int id, int range, FineGrainedListSet<Integer> listing) {
        this.id = id;
        this.listing = listing;
        this.range = range;
	}

	public void run() {
		int i;
		for(i=id; i<range; i++) {
			System.out.println("Adding: " + i);
			listing.add(new Integer(i));
		}
		if(listing.remove(new Integer(i-2))) {
		    System.out.println("Removed: " + (i-2));	
		} else {
			System.out.println("Could not remove: " + (i-2));
		}
	}

	public static void main(String[] args) {
  	    FineGrainedListSet<Integer> myList = new FineGrainedListSet<Integer>();
		for(int i=0; i<20; i=i+5) {
			Tester thisThread = new Tester(i, i+5, myList);
			thisThread.start();
		}
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Final queue: \n"  + myList.toString());
    }
}