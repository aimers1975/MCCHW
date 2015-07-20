//AtomicMarkedReference
import java.util.concurrent.atomic.*;

public class LockFreeQueue<T> implements MyQueue<T> {
  
  AtomicReference<QNode> head;
  AtomicReference<QNode> tail;

  public LockFreeQueue () {
  	  QNode temp = new QNode();
  	  head = new AtomicReference<QNode>(temp);
  	  tail = new AtomicReference<QNode>(temp);

  }

  public boolean enq(T value) {

    QNode temp = new QNode();
    temp.value = value;
    QNode tailNode;
    QNode nextNode=null;

    while(true) {
    	tailNode = tail.get(); //get current tail
 	    nextNode = tailNode.next.get(); //get current tail next, should be null
    	if(tailNode == tail.get()) { //if we still have the end node
      		if (nextNode == null) { // and no one is modify
      			//compareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark)           
              if(tailNode.next.compareAndSet(nextNode, temp)) {
                  break;
              }
          } else {
              tail.compareAndSet(tailNode, nextNode);
      		}
    	}
    }
    tail.compareAndSet(tailNode,temp);
    return true;
  }

  public T deq() {
  	/*D1: loop // Keep trying until Dequeue is done
D2: head = Q->Head // Read Head
D3: tail = Q->Tail // Read Tail
D4: next = head.ptr->next // Read Head.ptr->next
D5: if head == Q->Head // Are head, tail, and next consistent?
D6: if head.ptr == tail.ptr // Is queue empty or Tail falling behind?
D7: if next.ptr == NULL // Is queue empty?
D8: return FALSE // Queue is empty, couldn’t dequeue
D9: endif
// Tail is falling behind. Try to advance it
D10: CAS(&Q->Tail, tail, <next.ptr, tail.count+1>)
D11: else // No need to deal with Tail
// Read value before CAS
// Otherwise, another dequeue might free the next node
D12: *pvalue = next.ptr->value
// Try to swing Head to the next node
D13: if CAS(&Q->Head, head, <next.ptr, head.count+1>)
D14: break // Dequeue is done. Exit loop
D15: endif
D16: endif
D17: endif
D18: endloop
D19: free(head.ptr) // It is safe now to free the old node
D20: return TRUE // Queue was not empty, dequeue succeeded*/
    while(true) {
    	QNode headNode = head.get();
    	QNode tailNode = tail.get();
    	AtomicReference<QNode> nextNode = headNode.next;
    	if(headNode == head.get()) {
    	    if(head == tail) {
      		    //Is queue empty or tail falling behind
      		    if(nextNode == null) {
      		    	  return null;
      		    }
      		    tail.compareAndSet(tailNode,nextNode.get());
              break;
    	    } else {
      	    	T thisVal = nextNode.get().value;
              if(head.compareAndSet(headNode,nextNode.get())) {
                  System.out.println("Dequeuing: " + thisVal); 
                  return thisVal;              
              }
    	    }
    	}
    }
    return null;
  }

  public String toString() {
      StringBuilder sb = new StringBuilder();
      QNode current = head.get().next.get();
      while((current != null)) {
          sb.append("Val: " + current.value.toString() + "\n");
          current = current.next.get();           
      }
      return sb.toString();
  }

  public class QNode {
  	T value;
  	AtomicReference<QNode> next = new AtomicReference<QNode>(null);
  }

}
