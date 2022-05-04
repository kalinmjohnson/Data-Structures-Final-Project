package finalproject;

public abstract class ListNode {
	String data;
	ListNode nxt;
	ListNode prev;
	
	public ListNode(String data, ListNode nxt, ListNode prev) {
		this.data = data;
		this.nxt = nxt;
		this.prev = prev;
	}
}
