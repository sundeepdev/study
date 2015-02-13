/*
    Print a binary tree in level order
    
    Print a binary tree in level order. It means that all the nodes
    of the tree at the same vertical level are to be printed in one
    line. Insert a line end after one level.

    Solution: This problem can be solved easily using two queues. 

    one called currentLevel and the other one called nextLevel.

    You poll from queue currentLevel and print the node's value. After that You add
    the node's leftChild and rightChild to the queue nextLevel. 

    When the currentLevel is empty, output a "line end". Now check the nextLevel queue.
    If it's not empty, swap(currentLevel, nextLevel) and continue. Otherwise, return.
    
    Time Complexity: O(n)

    Space Complexity: O(n)

    Question: Can we reduce the space complexity?

    
    
*/

import java.util.*;
import java.lang.*;

class Node<E> {
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;
    E value;

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getParent() {
        return parent;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }

    public E getValue() {
        return value;
    }

    public Node(E value) {  
        this.value = value;
    }
}

class BinaryTree<E> {
    Node<E> root;
    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    public BinaryTree() {
        this.root = null;
    }

    public void printLevelOrder() {
        Queue<Node<E>> currentLevel = new LinkedList<Node<E>>();
        Queue<Node<E>> nextLevel = new LinkedList<Node<E>>();

        currentLevel.add(root);
        
        while(!currentLevel.isEmpty()) {
            Node<E> currNode = currentLevel.poll();
            
            System.out.print(currNode.getValue() + " ");
            
            if (currNode.getLeft() != null) {
                nextLevel.add(currNode.getLeft());
            }

            if (currNode.getRight() != null) {
                nextLevel.add(currNode.getRight());
            }

            if (currentLevel.isEmpty()) {
                System.out.println();
                if (!nextLevel.isEmpty()) {
                    Queue<Node<E>> temp = currentLevel;
                    currentLevel = nextLevel;
                    nextLevel = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Node<Integer> node_0 = new Node<Integer>(3);       
        Node<Integer> node_1 = new Node<Integer>(9);       
        Node<Integer> node_2 = new Node<Integer>(20);       
        Node<Integer> node_3 = new Node<Integer>(15);       
        Node<Integer> node_4 = new Node<Integer>(7);

        node_0.setLeft(node_1);
        node_0.setRight(node_2);
        node_2.setLeft(node_3);
        node_2.setRight(node_4);

        BinaryTree<Integer> tree = new BinaryTree<Integer>(node_0);

        tree.printLevelOrder();
    }
}
