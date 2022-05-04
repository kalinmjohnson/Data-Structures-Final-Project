package finalproject;

public class ListNode {
	String data;
	ListNode nxt;
	ListNode prev;
	
	public ListNode(String data, ListNode nxt, ListNode prev) {
		this.data = data;
		this.nxt = nxt;
		this.prev = prev;
	}

	public ListNode() {
		this.data = null;
		this.nxt = null;
		this.prev = null;
	}
}
