package finalproject;

/**
 * Model of Message part of the Gui
 * 
 * @author Kalin Johnson
 * @author Nicholas Pederson
 * @version Spring 2022
 * 
 */

public class Messages {

	protected ListNode current;
	protected int size;

	/**
	 * Add a node to circular linked list
	 * 
	 * @param newNode to be added
	 * @return nothing
	 */

	public void addItem(ListNode newNode) {
		newNode.prev = current;
		newNode.nxt = current.nxt;
		current.nxt = newNode;
		current.nxt.prev = newNode;

	}

	/**
	 * Delete a node from circular linked list
	 * 
	 * @param nothing
	 * @return nothing
	 */

	public void deleteItem() {
		if (size == 0) {
			System.out.println("there is nothing to delete");
		} else if (size == 1) {
			current.data = "";
			current.prev = current;
			current.nxt = current;
		} else {
			current.prev.nxt = current.nxt;
			current.nxt.prev = current.prev;

			current = current.nxt;

			size--;
			System.out.println("del: " + this.toString());
		}
	}

	/**
	 * Make a string from linked list data
	 * 
	 * @param nothing
	 * @return a string of all data
	 */

	public String toString() {
		ListNode printone;
		String output = "";
		if (size > 0) {
			printone = current;
			output = printone.data;
			printone = printone.nxt;
			while (printone != current) {
				output = output + " - " + printone.data;
				printone = printone.nxt;
			}
		}
		return output;

	}

	/**
	 * Constructor with data given
	 * 
	 * @param nothing
	 * @return nothing
	 */

	public Messages(String data) {
		current = new ListNode(data, current, current);
		size = 1;
	}

	/**
	 * Constructor with no data given
	 * 
	 * @param nothing
	 * @return nothing
	 */

	public Messages() {
		current = new ListNode(null, null, null);
		current.nxt = current;
		current.prev = current;
		size = 0;
	}

}


