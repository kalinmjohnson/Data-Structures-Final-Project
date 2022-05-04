package finalproject;

public class Messages extends ListNode {

    protected ListNode head;
    protected int size;

    public void addItem(ListNode newNode) {
        newNode.prev = this;
        newNode.nxt = this.nxt;
        this.nxt.prev = newNode;
        this.nxt = newNode;
        size++;
    }

    public void deleteItem() {
        this.prev.nxt = this.nxt;
        this.nxt.prev = this.prev;
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