/*
    You have two very large binary trees: T1, with million of nodes, and T2, with hundred of nodes. 
    Create an algorithm to decide if T2 is a subtree of T1.
    
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

    //return true if t2 is a subtree of t1; return false otherwise
    public static boolean isSubTree(BinTree t1, BinTree t2) {
        //step 1: find if t2's root is one of t1's nodes
        //Iterate through all the nodes in t1
        Node rt1 = search(t1.getRoot(), t2.getRoot());

        if (rt1 != null) {
            return match(rt1, t2.getRoot());
        } else {
            return false;
        }
    }

    public static boolean match(Node rt1, Node rt2) {

        if ( rt1 == null || rt2 == null) {
            if (rt1 == null && rt2 == null) {
                System.out.println("AAA");
                return true;
            } else {
                System.out.println("BBB");
                return false;
            }
        }

        if (rt1.getValue() != rt2.getValue()) {
            return false;
        }

        boolean l = match(rt1.getLeft(), rt2.getLeft());
        boolean r = match(rt1.getRight(), rt2.getRight());

        if (l == true && r == true) {
            System.out.println("CCC");
            return true;
        } else {
            System.out.println("DDD");
            return false;
        }
    }

    //return true if n one of the node in the tree rooted at rt
    public static Node search(Node rt, Node n) {
        if (rt == null) {
            return null;
        }

        if (rt.getValue() == n.getValue()) {
            return rt;
        }

        Node left = search(rt.getLeft(), n);
        Node right = search(rt.getRight(), n);

        if (left != null) {
            return right;
        }

        if (right != null) {
            return right;
        }

        return null;
    }

    public static void testIsSubTree() {
      Node node_0 = new Node(15);
      Node node_1= new Node(6);
      Node node_2 = new Node(18);
      Node node_3 = new Node(3);
      Node node_4 = new Node(8);
      Node node_5 = new Node(7);
      Node node_6 = new Node(17);
      Node node_7 = new Node(20);
      Node node_8 = new Node(2);
      Node node_9 = new Node(4);
      Node node_10 = new Node(13);
      Node node_11 = new Node(9);
      Node node_12 = new Node(10);


      node_0.setLeft(node_1);
      node_0.setRight(node_2);
      node_1.setParent(node_0);
      node_2.setParent(node_0);

      node_1.setLeft(node_3);
      node_1.setRight(node_4);
      node_3.setParent(node_1);
      node_4.setParent(node_1);

      node_2.setLeft(node_6);
      node_2.setRight(node_7);
      node_6.setParent(node_2);
      node_7.setParent(node_2);

      node_3.setLeft(node_8);
      node_3.setRight(node_9);
      node_8.setParent(node_3);
      node_9.setParent(node_3);

      node_4.setLeft(node_5);
      node_4.setRight(node_10);
      node_5.setParent(node_4);
      node_10.setParent(node_4);


      node_10.setLeft(node_11);
      node_11.setParent(node_10);

      node_11.setRight(node_12);
      node_12.setParent(node_11);

      Node node_13 = new Node(8);
      Node node_14 = new Node(7);
      Node node_15 = new Node(13);

      node_13.setLeft(node_14);
      node_13.setRight(node_15);
      node_14.setParent(node_13);
      node_15.setParent(node_13);

      BinTree bTree = new BinTree(node_0);
      BinTree bTree2 = new BinTree(node_13);

      boolean result = isSubTree(bTree, bTree2);

      System.out.println(result);

    }

    public static void main(String[] args) {
        testIsSubTree();
    }
}

