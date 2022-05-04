package finalproject;

public class ListNode<T> {
	T data;
	ListNode nxt;
	ListNode prev;
	
	public ListNode(T data, ListNode<T> nxt, ListNode<T> prev) {
		this.data = data;
		this.nxt = nxt;
		this.prev = prev;
	}
}
