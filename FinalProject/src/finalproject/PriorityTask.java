package finalproject;

/**
 * The model for our Priority Task.
 * 
 * @author Nicholas 
 * @author Kalin
 * @version 5/16/2022
 */

public class PriorityTask implements Comparable<PriorityTask>{
	// The data to store in this node:
	String rdata;
	
	// The priority
	int priority;

	/**
	 * Constructor
	 * @param data - the item to store
	 * @param pri - the priority of the new item / task
	 */
	PriorityTask( String data, int pri) {
		rdata = data;
		this.priority = pri;
	}
	
	/**
	 * Overrides compareTo( other object) so that I can use a Priority Queue
	 * @param o - the other object it is comparing to.
	 */
	@Override
	public int compareTo(PriorityTask o) {		
		int prio1 = this.priority, prio2 = o.priority;
		
		return prio1 < prio2? -1 : ( prio1 > prio2? 1 : 0);
		
	}
	
	/**
	 * Overrides toString() so that it knows how to display the Priority task.
	 */
	@Override
	public String toString() {
		return this.rdata;
	}
	
} // MinHeapNode
