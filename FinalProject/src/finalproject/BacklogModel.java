package finalproject;

import java.util.EmptyStackException;

/**
 * The Linked List Stack used for the Backlog of Tasks.
 * @author Nicholas
 * @version May 8, 2022
 */
public class BacklogModel {

	/** The first node in the list */
	ListNode head;

	/** The last node in the list */
	ListNode tail;
	
	/** The number of items in the list */
	int size;

	/** 
	 * Constructor? it just clears the way I guess
	 * @param items
	 */
	public BacklogModel() {
		clear();
	}
	
	public void clear() {
		size = 0;
		tail = new ListNode( null, null, head);
		head = new ListNode( null, tail, null);
	};	
	
	public boolean isEmpty() {
		if (head.nxt == tail) {
			return true;
		} else { return false;}
	}
	
	public void push(String element) {
			ListNode topOfStack = new ListNode( element, head.nxt, head);
			head.nxt.prev = topOfStack;
			head.nxt = topOfStack;
			size++;
	}
	
	/**
	 * This method pops the top string off.
	 * @return thePopped
	 * @throws EmptyStackException
	 */
	public String pop() throws EmptyStackException{
		if (head.nxt == tail) {
			throw new EmptyStackException();
		}
		
		String thePopped = head.nxt.data;
		head.nxt = head.nxt.nxt;
		head.nxt.prev = head;
		size--;
		return thePopped;
	}
	
	public String top() throws EmptyStackException{
		if (head.nxt == tail) {
			throw new EmptyStackException();
		}
		return head.nxt.data;
	}

	/**
	 * A special peek method that returns the value of the the ith string in the stack
	 * @param i - the index of the desired string
	 * @return current 
	 */
	public String peek(int i) {
		ListNode current = head;
		for ( int j = 0; j <= i; j++) {
			current = current.nxt;
		}
		return current.data;
	}
}
