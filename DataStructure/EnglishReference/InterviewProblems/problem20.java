/*
    Implement a function to check if a tree is balanced. For the purposes of this question, a balanced tree is
    defined to be a tree such that no two leaf nodes differ in distance from the root by more than one.

    Note: The depth of a node is the length of the path from the root to the node. E.g. the depth of the root is 0
*/

import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

class Node<E extends Comparable<? super E>> {
    private E value;
    private Node<E> parent;
    public ArrayList<Node<E>> children;

    public Node() {
        parent = null;
        children = new ArrayList<Node<E>>();
    }

    public Node(E value) {
        this.value = value;
        parent = null;
        children = new ArrayList<Node<E>>();
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getParent() {
        return parent;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public ArrayList<Node<E>> getChildren() {
        return children;
    }

    public void addChild(Node<E> child) {
        if (children == null) {
            children = new ArrayList<Node<E>>();
        }

        children.add(child);
    }
}

class Tree<E extends Comparable<? super E>> {
    private Node<E> root;
    boolean balance = true;
    boolean foundRootChild = false;

    public Tree() {
        root = null;
    }
    
    public Tree(E value) {
        root = new Node<E>(value);
    }

    public Tree(Node<E> node) {
        root = node;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public Node<E> getRoot() {
        return root;
    }

    public boolean isLeaf(Node<E> node) {
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void visit(Node<E> node) {
        if (node != null) {
            System.out.println(node.getValue());
        } else {
            System.out.println("This node is empty");
        }
    }

    public void preOrder(Node<E> rt ) {
        if (rt != null) {
            visit(rt);
            for (Node<E> child : rt.getChildren()) {
                preOrder(child);
            }
        }
    }

    public void postOrder(Node<E> rt) {
        if (rt != null) {
            for (Node<E> child : rt.getChildren()) {
                preOrder(child);
            }
            visit(rt);
        }
    }

    public int getHeight(Node<E> rt) {
        if (isLeaf(rt)) {
            return 0;
        } else {
            int maxDepth = 0;
            
            for (Node<E> child : rt.getChildren() ) {
                maxDepth = Math.max(maxDepth, getHeight(child));
            }

            return maxDepth + 1;
        }
    }

    public int getHeight2(Node<E> rt) {
        if (isLeaf(rt)) {
            return 0;
        } else {
            int maxDepth = 0;
            
            boolean started = false;
            for (Node<E> child : rt.getChildren()) {

                int newDepth = getHeight2(child);
                if (started == false) {
                    started = true;
                } else {
                    //if (newDepth != maxDepth) {
                    if (Math.abs(newDepth - maxDepth) > 1) {
                        balance = false;
                    }
                }    

                if (newDepth > maxDepth) {
                    maxDepth = newDepth;
                }
            }

            return maxDepth + 1;
        }
    }

    /*
        The maximum depth is the number of nodes along the longest path from the root node to the
        nearest leaf node.
    */
    public int maxDepth(Node<E> rt) {
        
        if (isLeaf(rt)) {
            return 0;
        } else {
            int maxDepth = Integer.MIN_VALUE;
            
            for (Node<E> child : rt.getChildren() ) {
                maxDepth = Math.max(maxDepth, maxDepth(child));
            }

            return maxDepth + 1;
        }
    }

    /*
        The minimum depth is the number of nodes along the shortest path from the root node to the
        nearest leaf node.
    */
    public int minDepth(Node<E> rt) {

        if (isLeaf(rt)) {
            return 0;
        } else {
            int minDepth = Integer.MAX_VALUE;
            
            for (Node<E> child : rt.getChildren() ) {
                minDepth = Math.min(minDepth, minDepth(child));
            }

            return minDepth + 1;
        }
    }

    //!!This is my solution. While finding the height of the tree(max depth), set the balance to false
    //if any two children's depths are different. This method takes O(n) in the worst case.
    public boolean getBalance() {
        balance = true;
        getHeight2(root);
        return balance;
    }

    //This is the the book's solution: the difference of min depth and max depth should not exceed 1,
    //since the difference of the min and the max depth is the maximum distance difference possible 
    //in the tree. !! I don't think this is correct because a chainning-tree can still have the same
    //maxDepth and minDepth.
    public boolean getBalance2() {
        int maxDepth = maxDepth(root);
        int minDepth = minDepth(root);

        if (maxDepth - minDepth <= 1) {
            return true;
        } else {
            return false;
        }
    }

    /*
        Since getBalance2() cannot solve the chainning-tree problem, here I propose my own solution:
        1) If the root has more than one child, the difference of min depth and max depth should not exceed 1,
        2) If the root has only one child, the difference of max depth and 0 should not exceed 1.
        3) If the root has no child, it is always balanced
    */
    public boolean getBalance3() {
        int maxDepth = maxDepth(root);
        int minDepth = minDepth(root);
        int num_of_children = root.getChildren().size();

        if (num_of_children == 0) {
            return true;
        } else if (num_of_children == 1) {
            return (maxDepth - 0 <= 1);
        } else {
            return (maxDepth - minDepth <= 1);
        }
    }
}

public class problem20 {
    public static void testBalance() {
        //Create some nodes
        Node<Integer> node0 = new Node<Integer>(0);
        
        Node<Integer> node1 = new Node<Integer>(1);
        Node<Integer> node2 = new Node<Integer>(2);
        Node<Integer> node3 = new Node<Integer>(3);
        Node<Integer> node4 = new Node<Integer>(4);
        Node<Integer> node5 = new Node<Integer>(5);
        Node<Integer> node6 = new Node<Integer>(6);
        Node<Integer> node7 = new Node<Integer>(7);
        Node<Integer> node8 = new Node<Integer>(8);
        Node<Integer> node9 = new Node<Integer>(9);
        Node<Integer> node10 = new Node<Integer>(10);
        Node<Integer> node11 = new Node<Integer>(11);
        Node<Integer> node12 = new Node<Integer>(12);
        Node<Integer> node13 = new Node<Integer>(13);
        Node<Integer> node14 = new Node<Integer>(14);
        
        /*
        
        node0.addChild(node1);
        node0.addChild(node2);

        node1.addChild(node3);
        node1.addChild(node4);

        node2.addChild(node5);
        node2.addChild(node6);

        node3.addChild(node7);
        node3.addChild(node8);

        node4.addChild(node9);
        node4.addChild(node10);

        node5.addChild(node11);
        node5.addChild(node12);
        
        node6.addChild(node13);
        node6.addChild(node14);

        */
        node0.addChild(node1);
        node1.addChild(node2);
        
        //Create a tree
        Tree<Integer> tree = new Tree<Integer>(node0);

        //Get the height
        System.out.println(tree.getBalance());
        
        //Get the balance
        System.out.println(tree.getBalance3());
        System.out.println(tree.maxDepth(tree.getRoot()));
        System.out.println(tree.minDepth(tree.getRoot()));
    }

    public static void main(String[] args) {
        testBalance();
    }
}
