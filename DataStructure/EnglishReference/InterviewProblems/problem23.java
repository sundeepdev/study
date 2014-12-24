/*
    Given a sorted (increasing order) array, write an algorithm to create
    a binary tree with minimal height.
*/

import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

class Node {
    private int value;
    private Node parent;
    private Node left;
    private Node right;

    public Node() {
        left = right = parent = null;
    }

    public Node(int value) {
        this.value = value;
        left = right = parent = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    //return index of i's left child. 
    public int getLeftIndex(int i, int size) {
        if ((i * 2 + 1) >= size) {
            return -1;
        } else {
            return i * 2 + 1;
        }
    }

    //return index of i's right child. 
    public int getRightIndex(int i, int size) {
        if ((i * 2 + 2) >= size) {
            return -1;
        } else {
            return i * 2 + 2;
        }
    }
}

class BinTree {
    private Node root;
    
    public BinTree() {
        root = null;
    }

    public BinTree(int value) {
        root = new Node(value);
    }

    public BinTree(Node node) {
        root = node;
    }

    public BinTree(Node[] nodes) {
        if (nodes == null) {
            return;
        }

        int length = nodes.length;
        //Iterate through all the non-leaf nodes
        for (int i = 0; i < length / 2; i++) {
            
            int leftIndex = nodes[i].getLeftIndex(i, length);
            int rightIndex = nodes[i].getRightIndex(i, length);

            if (leftIndex != -1) {
                nodes[i].setLeft(nodes[leftIndex]);
                nodes[leftIndex].setParent(nodes[i]);
            }

            if (rightIndex != -1) {
                nodes[i].setRight(nodes[rightIndex]);
                nodes[rightIndex].setParent(nodes[i]);
            }
        }

        root = nodes[0];
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isLeaf(Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return true;
        } else {
            return false;
        }
    }

    public void visit(Node node) {
        if (node != null) {
            System.out.println(node.getValue());
        } else {
            System.out.println("This node is empty");
        }
    }

    public void preOrder(Node rt) {
        if (rt != null) {
            visit(rt);
            preOrder(rt.getLeft());
            preOrder(rt.getRight());
        }
    }

    public void inOrder(Node rt) {
        if (rt != null) {
            inOrder(rt.getLeft());
            visit(rt);
            inOrder(rt.getRight());
        }
    }

    public void postOrder(Node rt) {
        if (rt != null) {
            postOrder(rt.getLeft());
            postOrder(rt.getRight());
            visit(rt);
        }
    }

    public int maxDepth(Node rt) {

        if (rt == null || isLeaf(rt)) {
            return 0;
        }

        int maxDepth = Integer.MIN_VALUE;
        if (rt.getLeft() != null) {
            maxDepth = Math.max(maxDepth, maxDepth(rt.getLeft()));
        }

        if (rt.getRight() != null) {
            maxDepth = Math.max(maxDepth, maxDepth(rt.getRight()));
        }

        return maxDepth + 1;
    }

    public int minDepth(Node rt) {
        if (rt == null || isLeaf(rt)) {
            return 0;
        }
        
        int minDepth = Integer.MAX_VALUE;

        if (rt.getLeft() != null) {
            minDepth = Math.min(minDepth, minDepth(rt.getLeft()));
        }

        if (rt.getRight() != null) {
            minDepth = Math.min(minDepth, minDepth(rt.getRight()));
        }

        return minDepth + 1;
    }

    public static void testArrayConstructor() {
        
        Node[] nodes = new Node[8];
        for (int i = 0; i < 8; i++) {
            nodes[i] = new Node(i);
        }

        BinTree tree = new BinTree(nodes);
        tree.preOrder(tree.getRoot());

        return;
    }

    public static void main(String[] args) {
        testArrayConstructor();
    }
}

