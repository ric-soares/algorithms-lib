package node;

public class BinaryTreeNode {
    private final int item;
    private BinaryTreeNode right;
    private BinaryTreeNode left;
    private BinaryTreeNode parent;

    public BinaryTreeNode(int item) {
        this.item = item;
        this.right = null;
        this.left = null;
        this.parent = null;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public int getItem() {
        return item;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }
}
