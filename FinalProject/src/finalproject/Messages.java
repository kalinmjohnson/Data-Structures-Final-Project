package finalproject;

public class Messages extends ListNode {

    protected ListNode head;
    protected int size;

    public void addItem(ListNode newNode, ListNode currentNode) {
        newNode.prev = currentNode;
        newNode.nxt = currentNode.nxt;
        currentNode.nxt.prev = newNode;
        currentNode.nxt = newNode;
        size++;
    }

    public void deleteItem(ListNode currentNode) {
    	if (currentNode == head) {
    		head = currentNode.nxt;
    	}
    	currentNode.prev.nxt = currentNode.nxt;
    	currentNode.nxt.prev = currentNode.prev;
    }

    public Messages (String data) {
        head = new ListNode(data, null, null);
        head.nxt = head;
        head.prev = head;
        size = 1;
    }

    public Messages () {
        head = new ListNode(null, null, null);
        size = 0;
    }

}
