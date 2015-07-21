import java.util.concurrent.atomic.*;

public class LockFreeListSet<T> implements ListSet<T> {
  AtomicMarkableReference<LLNode> list;
  AtomicMarkableReference<LLNode> end;

  public LockFreeListSet () {
  	LLNode first = new LLNode();
  	first.next = new AtomicMarkableReference<LLNode>(null,false);
  	list = new AtomicMarkableReference<LLNode>(first,false);
  	end = new AtomicMarkableReference<LLNode>(first,false);
  }

  public boolean add(T value) {
  	LLNode newNode = new LLNode();
  	newNode.value = value;
  	LLNode currentEnd;
  	LLNode currentNext;
  	boolean[] mark;
  	boolean[] mark2;
  	while(true) {
	  	mark = new boolean[1];
	  	mark2 = new boolean[1];
	  	currentEnd = end.get(mark);
        currentNext = end.getReference().next.get(mark2);
	  	if(currentEnd == end.getReference()) {
	  		if(currentNext == null) {
	  			end.getReference().next.compareAndSet(null,newNode,mark2[0],!mark2[0]);
	  			break;
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
  	boolean[] mark = new boolean[1];
  	AtomicMarkableReference<LLNode> previous = findNode(value);



    return false;
  }
  /*To remove a node curr, two actions need to be taken. First, the mark of AtomicMarkableReference
curr.next needs to be set true. This corresponds to setting isDeleted flag true in the lock based Linked
List. We can use a CAS to perform this action. If the CAS does not succeed, then the remove operation
can be retried. The second action requires a check that pred is not deleted, and pred.next points to curr. If
the condition is true, then pred.next should be set to curr. The check and update can be done atomically
in a CAS operation.*/
  public boolean contains(T value) {
  	boolean[] mark = new boolean[1];
  	if(findNode(value) != null) {
  		return true;
  	}
    return false;
  }

  public AtomicMarkableReference<LLNode> findNode(T value) {
      LLNode current = list.getReference().next.getReference();
      AtomicMarkableReference<LLNode> previous = list;
      while((current != null)) {
          if(current.value.equals(value)) {
          	return previous;
          }
          previous = previous.getReference().next;
          current = current.next.getReference();           
      }
      return null;
  }

  public String toString() {
      StringBuilder sb = new StringBuilder();
      LLNode current = list.getReference().next.getReference();
      while((current != null)) {
          sb.append("Val: " + current.value.toString() + "\n");
          current = current.next.getReference();           
      }
      return sb.toString();
  }

  public class LLNode{
  	 T value;
  	 AtomicMarkableReference<LLNode> next = new AtomicMarkableReference<LLNode>(null,false);
  }

}



