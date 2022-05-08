package finalproject;

import java.util.EmptyStackException;

/**
 * The Linked List Stack using for the Backlog of Tasks.
 * @author Nicholas
 * @version May 8, 2022
 */
public class BacklogModel extends ListNode {

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
	
	public String top()throws EmptyStackException{
		if (head.nxt == tail) {
			throw new EmptyStackException();
		}
		
		return head.nxt.data;
	}
}
