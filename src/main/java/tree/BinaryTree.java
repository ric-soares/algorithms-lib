package tree;

import node.BinaryTreeNode;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree {
    private BinaryTreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void add(int item) {
        BinaryTreeNode newNode = new BinaryTreeNode(item);

        if (this.root == null) {
            this.root = newNode;
        } else {
            addNodeRecursively(newNode, this.root);
        }
    }

    public void add(List<BinaryTreeNode> items) {
        for (BinaryTreeNode item : items) {
            add(item.getItem());
        }
    }

    public void remove(int item) {
        BinaryTreeNode node = getNodeRecursively(item, this.root);

        if (node == null) {
            return;
        }

        if (node == this.root) {
            this.root = null;
            return;
        }

        if (getNumberOfChildren(node) == 0) {
            BinaryTreeNode parent = node.getParent();

            if (parent.getRight() == node) {
                parent.setRight(null);
            }

            if (parent.getLeft() == node) {
                parent.setLeft(null);
            }
        }
    }

    public void remove(List<BinaryTreeNode> items) {
        for (BinaryTreeNode item : items) {
            remove(item.getItem());
        }
    }

    private void addNodeRecursively(BinaryTreeNode newNode, BinaryTreeNode parentNode) {
        if (newNode.getItem() > parentNode.getItem()) {
            if (parentNode.getRight() == null) {
                parentNode.setRight(newNode);
                newNode.setParent(parentNode);
            } else {
                addNodeRecursively(newNode, parentNode.getRight());
            }
        } else {
            if (parentNode.getLeft() == null) {
                parentNode.setLeft(newNode);
                newNode.setParent(parentNode);
            } else {
                addNodeRecursively(newNode, parentNode.getLeft());
            }
        }
    }

    public void mergeBinaryTree(BinaryTree other) {
        traversePreOrder(other.getRoot(), node -> this.add(node.getItem()));
    }

    public BinaryTreeNode getNode(int item) {
        return getNodeRecursively(item, this.root);
    }

    private BinaryTreeNode getNodeRecursively(int item, BinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        if (item == node.getItem()) {
            return node;
        }

        if (item > node.getItem()) {
            return getNodeRecursively(item, node.getRight());
        } else {
            return getNodeRecursively(item, node.getLeft());
        }
    }

    private void traversePreOrder(BinaryTreeNode node, Consumer<BinaryTreeNode> callback) {
        if (node == null) {
            return;
        }

        callback.accept(node);
        traversePreOrder(node.getRight(), callback);
        traversePreOrder(node.getLeft(), callback);
    }

    private void traversePostOrder(BinaryTreeNode node, Consumer<BinaryTreeNode> callback) {
        if (node == null) {
            return;
        }

        traversePostOrder(node.getRight(), callback);
        traversePostOrder(node.getLeft(), callback);
        callback.accept(node);
    }

    private void traverseCentralOrder(BinaryTreeNode node, Consumer<BinaryTreeNode> callback) {
        if (node == null) {
            return;
        }

        traverseCentralOrder(node.getRight(), callback);
        callback.accept(node);
        traverseCentralOrder(node.getLeft(), callback);
    }

    private void traverseLevelOrder(Consumer<BinaryTreeNode> callback) {
        if (this.root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            BinaryTreeNode currentNode = queue.poll();
            callback.accept(currentNode);

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public List<BinaryTreeNode> getItems(TraversalOrder traversalOrder) {
        List<BinaryTreeNode> result = new ArrayList<>();

        switch (traversalOrder) {
            case PRE_ORDER -> traversePreOrder(this.root, result::add);
            case POST_ORDER -> traversePostOrder(this.root, result::add);
            case CENTRAL_ORDER -> traverseCentralOrder(this.root, result::add);
            case LEVEL_ORDER -> traverseLevelOrder(result::add);
        }

        return result;
    }

    public int getHighestValue() {
        return getHighestValueRecursively(this.root);
    }

    public int getLowestValue() {
        return getLowestValueRecursively(this.root);
    }

    private int getHighestValueRecursively(BinaryTreeNode node) {
        if (node == null) {
            return -1;
        }

        int max = node.getItem();

        if (node.getRight() != null) {
            max = Math.max(max, getHighestValueRecursively(node.getRight()));
        }

        if (node.getLeft() != null) {
            max = Math.max(max, getHighestValueRecursively(node.getLeft()));
        }

        return max;
    }

    private int getLowestValueRecursively(BinaryTreeNode node) {
        if (node == null) {
            return -1;
        }

        int min = node.getItem();

        if (node.getRight() != null) {
            min = Math.min(min, getLowestValueRecursively(node.getRight()));
        }

        if (node.getLeft() != null) {
            min = Math.min(min, getLowestValueRecursively(node.getLeft()));
        }

        return min;
    }

    private int getSize() {
        List<BinaryTreeNode> result = getItems(TraversalOrder.PRE_ORDER);
        return result.size();
    }

    public int getTreeHeight() {
        return getHeight(this.root);
    }

    private int getHeight(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int getLevel(int item) {
        return getLevelRecursively(item, this.root, 0);
    }

    private int getLevelRecursively(int item, BinaryTreeNode node, int level) {
        if (node == null) {
            return -1;
        }

        if (item == node.getItem()) {
            return level;
        }

        if (item > node.getItem()) {
            return getLevelRecursively(item, node.getRight(), level + 1);
        } else {
            return getLevelRecursively(item, node.getLeft(), level + 1);
        }
    }

    private int countNonLeafNodes(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            return 0;
        }

        int count = 1;

        count += countNonLeafNodes(node.getLeft());
        count += countNonLeafNodes(node.getRight());

        return count;
    }

    private int countLeafNodes(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }

        return countLeafNodes(node.getLeft()) + countLeafNodes(node.getRight());
    }

    private int countEvenItems(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        int count = (node.getItem() % 2 == 0) ? 1 : 0;

        count += countEvenItems(node.getRight());
        count += countEvenItems(node.getLeft());

        return count;
    }

    private int countOddItems(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        int count = (node.getItem() % 2 != 0) ? 1 : 0;

        count += countOddItems(node.getRight());
        count += countOddItems(node.getLeft());

        return count;
    }

    private boolean isInternal(int item) {
        if (item == this.root.getItem()) {
            if (!hasChildren(this.root)) {
                return false;
            }
        }

        BinaryTreeNode itemNode = getNodeRecursively(item, this.root);

        return hasChildren(itemNode);
    }

    private boolean isExternal(int item) {
        if (item == this.root.getItem()) {
            if (hasChildren(this.root)) {
                return false;
            }
        }

        BinaryTreeNode itemNode = getNodeRecursively(item, this.root);

        return !hasChildren(itemNode);
    }

    private BinaryTreeNode getParent(int item) {
        if (isEmpty()) {
            return null;
        }

        if (item == this.root.getItem()) {
            return null;
        }

        BinaryTreeNode node = getNodeRecursively(item, this.root);

        return node.getParent();
    }

    private int getNumberOfChildren(BinaryTreeNode node) {
        if (node == null) {
            return -1;
        }

        if (node.getRight() == null && node.getLeft() == null) {
            return 0;
        } else if (node.getRight() != null && node.getLeft() != null) {
            return 2;
        } else {
            return 1;
        }
    }

    private boolean hasChildren(BinaryTreeNode node) {
        if (node == null) {
            return false;
        }

        if (node.getRight() == null && node.getLeft() == null) {
            return false;
        }

        return true;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void clean() {
        this.root = null;
    }

    public BinaryTreeNode getRoot() {
        return (this.root != null)? this.root : null;
    }

    public void generateRandomBinaryTree(int nNodes) {
        for (int i = 0; i < nNodes; i++) {
            int num = new Random().nextInt();
            add(num);
        }
    }

    public void printBinaryTreeRecursively(BinaryTreeNode root, int level) {
        if (this.root == null) return;

        level += 5;

        if (root.getRight() != null) {
            printBinaryTreeRecursively(root.getRight(), level);
        }

        System.out.print("\n");

        for (int i = 5; i < level; i++) {
            System.out.print(" ");
        }

        System.out.print(root.getItem() + "\n");

        for (int i = 5; i < level; i++) {
            System.out.print(" ");
        }

        if (root.getLeft() != null ) {
            printBinaryTreeRecursively(root.getLeft(), level);
        }
    }

    public enum TraversalOrder {
        PRE_ORDER, POST_ORDER, CENTRAL_ORDER, LEVEL_ORDER
    }
}
