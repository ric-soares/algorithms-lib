package stack;

import node.Node;

public class Stack {
    Node top;
    int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Node getTop() {
        return this.top;
    }

    public int getSize() {
        return this.size;
    }

    public void clean() {
        this.top = null;
        this.size = 0;
    }

    public void push(String item) {
        Node newNode = new Node(item);

        if (!isEmpty()) {
            newNode.setPrevious(this.top);
        }
        this.top = newNode;

        this.size++;
    }

    public String pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        Node item = this.top;
        this.top = this.top.getPrevious();
        this.size--;

        return item.getItem();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node aux = top;

        if (aux == null) {
            sb.append("stack.Stack is empty.");
        } else {
            while (aux != null) {
                sb.append(aux.getItem()).append(System.lineSeparator());
                aux = aux.getPrevious();
            }
        }

        return sb.toString();
    }
}
