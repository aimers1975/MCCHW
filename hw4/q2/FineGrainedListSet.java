import java.util.concurrent.locks.*;

public class FineGrainedListSet<T> implements ListSet<T> {

  LLNode list;
  LLNode end;

  public FineGrainedListSet() {
    LLNode start = new LLNode();
    start.next = null;
    start.value = null;
    list = start;
    end = start;
  }

  public boolean add(T value) {
  	LLNode newNode = new LLNode();
    newNode.next = null;
    newNode.value = value;
  	end.lock();
    end.next = newNode;
    end = end.next;
  	end.unlock();
    return true;
  }
  public boolean remove(T value) {
  	while(true) {
	  	LLNode previous = findNode(value);
	  	//System.out.println("Before list:\n" + toString() + "\n" + "Searching for: " + value.toString()  + "The previous node is: " + previous.value.toString());
	  	if(previous != null) {
	  		previous.lock();
	  		if(previous.next.value.equals(value)) {
	            previous.next = previous.next.next;
	            previous.unlock();
	            return true;
	  		} else {
	  			previous.unlock();
	  		}
	  	} else {
	  		return false;
	  	}
    }
  }
  public boolean contains(T value) {
    if(findNode(value) != null) {
    	return true;
    }
    return false;
  }

    public LLNode findNode(T value) {
  	LLNode current = list.next;
  	LLNode previous = list;
  	while(current != null) {
  		if (current.value.equals(value)) {
  			return previous;
  		} else {
  			previous = previous.next;
  			current = current.next;
  		}
  	}
  	return null;
  }

  public String toString() {
      StringBuilder sb = new StringBuilder();
      LLNode current = list.next;
      while((current != null)) {
          sb.append("Val: " + current.value.toString() + "\n");          
      	  current = current.next;
      }
      return sb.toString();
  }


  public class LLNode {
  	final ReentrantLock nodeLock = new ReentrantLock(); 
  	T value;
  	LLNode next;
  	public void lock() {
  		nodeLock.lock();
  	}

  	public void unlock() {
  		nodeLock.unlock();
  	}
  }
}
