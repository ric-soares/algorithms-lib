package node;

public class Node {
    private final String item;
    private Node next;
    private Node previous;

    public Node(String item) {
        this.item = item;
        this.next = null;
        this.previous = null;
    }

    public Node getNext() {
        return next;
    }

    public String getItem() {
        return item;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}