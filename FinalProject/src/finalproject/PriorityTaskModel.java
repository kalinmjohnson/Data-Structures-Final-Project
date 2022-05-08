package finalproject;

/**
 * Priority Queue Implementation
 * @author Nicholas
 * @version May 8, 2022
 */

public class PriorityTaskModel {
	
	MinHeapNode root;
	
	int size;
	
	MinHeapNode lastOne;
	
	/** Constructor for PriorityTaskModel*/
	public PriorityTaskModel() {
		root = new MinHeapNode( null, 0, null, null, null);
		size = 0;
		lastOne = root;
	}
	
	public void insertLeft( int prio, String task) {
		// make the new task to insert into the tree
		MinHeapNode newTask = new MinHeapNode( task, prio, null, null, lastOne);
		lastOne.left = newTask;
	}
	
	public void insertRight( int prio, String task) {
		MinHeapNode newTask = new MinHeapNode( task, prio, null, null, lastOne);
		lastOne.right = newTask;
	}
	/**
	 * Inserts a new element/task to the min heap.
	 * @param task - the new value of the task in the heap
	 * @param prio - the priority of this new task
	 */
	public void insert( String task, int prio) {
		
		if (lastOne.left == null) { insertLeft( prio, task);}
		else if (lastOne.right == null) { insertRight( prio, task);}
		else {
			lastOne = lastOne;
		}
		/*// if the tree is empty
		if ( root.rdata == null) { 
			root = newTask;
			size++;
			return;
		}
		
		// find the node where you need to insert
		if ( root.priority > prio) {
			if ( root.left == null) {
				newTask.left = root;
			} else {
				newTask.right = root;
			}
			
			root = newTask;
		} else {
			
		}*/
		size++;
	} // insert
	
	/** To get the height. */
	public int height() {
		return height(root);
	}
	
	protected int height(MinHeapNode root) {
		if (root == null) { return 0; }
		return (1 + Math.max(height(root.left), height(root.right)));
	} // height	
	
} // PriorityTaskModel
