package queue;

import node.Node;

public class DynamicLinearQueue {
    Node start;
    Node end;
    int size;

    public DynamicLinearQueue() {
        this.start = null;
        this.end = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void enqueue(String item) {
        Node newNode = new Node(item);

        if (size == 0) {
            this.start = newNode;
            this.end = newNode;
        } else {
            this.end.setNext(newNode);
            this.end = newNode;
        }

        size++;
    }

    public String dequeue() {
        if (this.size == 1) {
            Node node = this.start;

            this.start = null;
            this.end = null;
            size--;

            return node.getItem();
        } else if (this.size > 1) {
            Node node = this.start;

            this.start = this.start.getNext();
            size--;

            return node.getItem();
        } else {
            throw new IllegalStateException("Queue is empty");
        }
    }
}
