
public class LockFreeListSet<T> implements ListSet<T> {
  AtomicMarkableReference<LLNode> list = new AtomicMarkableReference<LLNode>();
  AtomicMarkableReference<LLNode> end = new AtomicMarkableReference<LLNode>();

  public LockFreeListSet<T> () {
  	LLNode first = new LLNode();
  	first.next = null;
  	list = new AtomicMarkableReference<LLNode>(first);
  	end = new AtomicMarkableReference<LLNode>(first);
  }

  public boolean add(T value) {
  	LLNode newNode = new LLNode();
  	newNode.value = value;
  	while(true) {
	  	boolean[] mark = new boolean[1];
	  	LLNode currentEnd = end.get(mark);
	  	LLNode currentNext = end.getReference.next.get();
	  	if(currentEnd == end.getReference()) {
	  		if(currentNext == null) {
	  			end.getReference().next.compareAndSet(null,newNode);
	  		} else {
	  			end.compareAndSet(currentEnd,currentNext,mark[0],!mark[0]);
	  		}
	  	}
	}
	end.compareAndSet(currentEnd,newNode,mark[0],!mark[0]);
    return false;
  }
  /*We need a bit with every node to mark if the node has been deleted or not.  
   Similarly, one can CAS with expected (reference, mark) and
update both reference and mark atomically if the current reference and the mark are as expected.
To insert a node x after prev and before curr, we first create a node x such that it points to curr.
Now we need to check that prev is not deleted and prev.next points to curr. If this check succeeds,
then prev.next must be set to x. The check and update of prev.next can be done in one compareAndSet
instruction.*/

  public boolean remove(T value) {

    return false;
  }
  /*To remove a node curr, two actions need to be taken. First, the mark of AtomicMarkableReference
curr.next needs to be set true. This corresponds to setting isDeleted flag true in the lock based Linked
List. We can use a CAS to perform this action. If the CAS does not succeed, then the remove operation
can be retried. The second action requires a check that pred is not deleted, and pred.next points to curr. If
the condition is true, then pred.next should be set to curr. The check and update can be done atomically
in a CAS operation.*/
  public boolean contains(T value) {
    return false;
  }

  public class LLNode{
  	 T value;
  	 AtomicReference<LLNode> next = new AtomicReference<LLNode>(null);
  }
}



