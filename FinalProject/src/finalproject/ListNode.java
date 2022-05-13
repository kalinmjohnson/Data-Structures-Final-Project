package finalproject;

/**
 * ListNode Model
 * 
 * @author Kalin Johnson
 * @author Nicholas Pederson
 * @version Spring 2022
 * 
 */

public class ListNode {
	String data;
	ListNode nxt;
	ListNode prev;
	
	/**
	 * ListNode Contructor with data
	 * @param data
	 * @param nxt
	 * @param prev
	 */
	
	public ListNode(String data, ListNode nxt, ListNode prev) {
		this.data = data;
		this.nxt = nxt;
		this.prev = prev;
	}
	
	/**
	 * ListNode Contructor without data
	 * @param data
	 * @param nxt
	 * @param prev
	 */

	public ListNode() {
		this.data = null;
		this.nxt = null;
		this.prev = null;
	}
}
