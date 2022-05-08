package finalproject;


public class MinHeapNode {
	// The data to store in this node:
	String rdata;
	
	// The priority
	int priority;
	
	// A "pointer" to the right or left or parent node
	MinHeapNode left;
	MinHeapNode right;
	MinHeapNode parent;
	/**
	 * Constructor
	 * @param data - the item to store
	 * @param left - the object reference to the left thing
	 * @param right - the object reference to the right thing
	 */
	MinHeapNode( String data, int pri, MinHeapNode left, MinHeapNode right, MinHeapNode parent) {
		rdata = data;
		this.priority = pri;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
} // MinHeapNode