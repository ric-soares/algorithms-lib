package list;

import node.Node;

public class DoubleEdgeLinkedList {
    private Node start;
    private Node end;
    private int size;

    public DoubleEdgeLinkedList() {
        this.start = null;
        this.end = null;
        this.size = 0;
    }

    public void add(String item, Position position) {
        Node newNode = new Node(item);

        if (this.start == null) {
            this.start = newNode;
            this.end = newNode;
            this.size++;
            return;
        }

        switch (position) {
            case START -> {
                newNode.setNext(this.start);
                this.start.setPrevious(newNode);
                this.start = newNode;
            }
            case END -> {
                newNode.setPrevious(this.end);
                this.end.setNext(newNode);
                this.end = newNode;
            }
        }
        this.size++;
    }

    public void add(String item, int pos) {
        if (pos < 0 || pos > this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node newNode = new Node(item);

        if (pos == 0) {
            add(item, Position.START);
        } else if (pos == this.size) {
            add(item, Position.END);
        } else {
            Node current = this.start;
            for (int i = 0; i < pos - 1; i++) {
                current = current.getNext();
            }

            Node nextNode = current.getNext();
            newNode.setNext(nextNode);
            newNode.setPrevious(current);
            current.setNext(newNode);

            if (nextNode != null) {
                nextNode.setPrevious(newNode);
            }

            this.size++;
        }
    }

    public void remove(int pos) {
        if (pos < 0 || pos >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (pos == 0) {
            this.start = this.start.getNext();
            if (this.start != null) {
                this.start.setPrevious(null);
            }
        } else if (pos == this.size - 1) {
            this.end = this.end.getPrevious();
            if (this.end != null) {
                this.end.setNext(null);
            }
        } else {
            Node current = this.start;
            for (int i = 0; i < pos - 1; i++) {
                current = current.getNext();
            }
            Node nodeToRemove = current.getNext();
            Node nextNode = nodeToRemove.getNext();

            current.setNext(nextNode);
            if (nextNode != null) {
                nextNode.setPrevious(current);
            }
        }
        this.size--;
    }

    public int getSize() {
        return this.size;
    }

    private enum Position {
        START, END
    }
}
