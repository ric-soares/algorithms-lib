package list;

import node.Node;

public class LinkedList {
    private Node start;
    private Node end;
    private int size;

    public LinkedList() {
        this.start = null;
        this.end = null;
        this.size = 0;
    }

    public void add(String item) {
        Node newNode = new Node(item);

        if (this.size == 0) {
            this.start = newNode;
            this.end = newNode;
        } else {
            this.end.setNext(newNode);
            this.end = newNode;
        }

        this.size++;
    }

    public void add(int pos, String item) {
        if (pos < 0 || pos > this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node newNode = new Node(item);

        if (pos == 0) {
            newNode.setNext(this.start);
            this.start = newNode;
            if (this.size == 0) {
                this.end = newNode;
            }
        } else if (pos == this.size) {
            this.end.setNext(newNode);
            this.end = newNode;
        } else {
            Node aux = this.start;
            int index = 0;

            while (index < pos - 1) {
                aux = aux.getNext();
                index++;
            }
            newNode.setNext(aux.getNext());
            aux.setNext(newNode);
        }

        this.size++;
    }

    public void remove(String item) {
        Node aux = this.start;
        Node previous = null;

        while (aux != null) {
            if (aux.getItem().equals(item)) {
                if (previous == null) {
                    this.start = aux.getNext();
                    if (this.start == null) {
                        this.end = null;
                    }
                } else {
                    previous.setNext(aux.getNext());
                    if (aux == this.end) {
                        this.end = previous;
                    }
                }
                this.size--;
                return;
            }
            previous = aux;
            aux = aux.getNext();
        }
    }

    public boolean exists(String item) {
        return searchNode(item) != null;
    }

    public int search(String item) {
        Node aux = this.start;
        int index = 0;

        while (aux != null) {
            if (aux.getItem().equals(item)) {
                return index;
            }
            aux = aux.getNext();
            index++;
        }

        return -1;
    }

    public String search(int pos) {
        if (pos < 0 || pos > this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node aux = this.start;

        for (int i = 0; i < pos; i++) {
            aux = aux.getNext();
        }

        return aux.getItem();
    }

    private Node searchNode(String item) {
        Node aux = this.start;

        while (aux != null) {
            if (aux.getItem().equals(item)) {
                return aux;
            }
            aux = aux.getNext();
        }

        return null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clean() {
        this.start = null;
        this.end = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        Node aux = this.start;

        while(aux != null) {
            sb.append(aux.getItem()).append(" ");
            aux = aux.getNext();
        }

        sb.append("}");

        if(!isEmpty()) {
            sb.append(" start = ").append(this.start.getItem()).append(" fim = ").append(this.end.getItem());
        }

        sb.append(" size = ").append(this.size);

        return sb.toString();
    }
}
