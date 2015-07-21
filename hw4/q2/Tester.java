public class Tester {
	public Tester() {

	}

	public static void main(String[] args) {
  	    LockFreeListSet<Integer> myList = new LockFreeListSet<Integer>();
  	    myList.add(new Integer(3));
  	    myList.add(new Integer(5));
  	    myList.add(new Integer(7));
  	    System.out.println("My list: \n" + myList.toString());
  	    if(myList.contains(new Integer(5))) {
  	    	System.out.println("My list contains 5");
  	    } else {
  	    	System.out.println("List missing 5");
  	    }
    }
}