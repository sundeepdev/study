
/*
    Given a binary search tree, design an algorithm which creates a linked
    list of all the nodes at each depth(i.e. if you have a tree with
    depth D, you'll have D linked lists.)
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

class BST {
    private Node root;
    
    public BST() {
        root = null;
    }

    public BST(int value) {
        root = new Node(value);
    }

    public BST(Node node) {
        root = node;
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

    public void setLinkedList(Node rt, LinkedList<LinkedList<Node>> linkedlist, int level) {
        if (rt != null) {

            LinkedList<Node> levellist;

            try {
                levellist = linkedlist.get(level);
            } catch (IndexOutOfBoundsException e ){
                
                levellist = new LinkedList<Node>();
                linkedlist.add(levellist);
            }

            levellist.add(rt);
        
            Node left = rt.getLeft();
            Node right = rt.getRight();

            if (left != null ) {
                setLinkedList(left, linkedlist, level+1);
            }
            
            if (right != null ) {
                setLinkedList(right, linkedlist, level+1);
            }

            return;
        }
    }

    public static void testSetLinkedList() {
        LinkedList<LinkedList<Node>> linkedlist = new LinkedList<LinkedList<Node>>();
        
        Node[] nodes = new Node[8];
        for (int i = 0; i < 8; i++) {
            nodes[i] = new Node(i);
        }

        nodes[0].setLeft(nodes[1]);
        nodes[0].setRight(nodes[2]);
        
        nodes[1].setLeft(nodes[3]);
        nodes[1].setRight(nodes[4]);
        
        nodes[2].setLeft(nodes[5]);
        nodes[2].setRight(nodes[6]);

        nodes[3].setLeft(nodes[7]);

        BST tree = new BST(nodes[0]);

        tree.setLinkedList(tree.getRoot(), linkedlist, 0);

        //System.out.println("size of the linkedlist = " + linkedlist.size());
        for (int i = 0; i < linkedlist.size(); i++) {
            System.out.println("level: " + i + " contains the following nodes:");
            LinkedList<Node> levellist = linkedlist.get(i);

            for (int j = 0; j < levellist.size(); j++) {
                System.out.print(levellist.get(j).getValue() + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        testSetLinkedList();
    }
}

