/*
    Write an algorithm to find the 'next' node (i.e., in-order successor) of a given node in a binary search tree where each node has a link to its parent

    Note: The depth of a node is the length of the path from the root to the node. E.g. the depth of the root is 0
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

    //return the maximum element of the tree rooted at rt (the right most node of rt's right sub tree.)
    public Node maximum(Node rt) {
        Node result = null;
        Node cur = rt;
        
        while (cur != null) {
            result = cur;
            cur = cur.getRight();
        }

        return result;
    }

    //return the minimum element of the tree rooted at rt (the left most node of rt's left sub tree.)
    public Node minimum(Node rt) {
        Node result = null;
        Node cur = rt;

        while (cur != null) {
            result = cur;
            cur = cur.getLeft();
        }

        return result;
    }

    public void insert(Node node) {
        Node cur = root;
        Node parent =  null;

        while(cur != null) {
            parent = cur;
            if (node.getValue() < cur.getValue()) {
                cur = cur.getLeft();
            } else {
                cur = cur.getRight();
            }
        }

        node.setParent(parent);
        //Deal with the special case if the tree is empty
        if (parent == null) {
            root = node;
        } else {
            if (node.getValue() < parent.getValue()) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }
        }
    }

    //The transplant is used to replace one subtree as a child of its parent with another subtree. This method replaces the subtree
    //rooted at node u with another subtree rooted at node v. Node u's parent becomes node v's parent, and u's parent ends up having
    //v as its appropriate child.
    public void transplant(Node u, Node v) {
        //Deal with the special case: u == root (or u.getParent() == null)
        if (u.getParent() == null) {
            root = v;
            v.setParent(null);
        } else {
            if (u == u.getParent().getLeft()) {
                u.getParent().setLeft(v);
            } else {
                u.getParent().setRight(v);
            }

            if (v != null) {
                v.setParent(u.getParent());
            }
        }
    }

    /*
        Delete a node z from the binary search tree:
        
        1)  If z has no left child, then we replace z by its right child, which may or may not be NIL;

        2)  It z has one child which is just its left child, we replace z by its left child;

        3)  Otherwise, z has both a left and right child. We find z's successor y, which lies in z's right subtree and has no left child(since y is the leftmost node in
            z's right subtree). We want to splice y out of its current location and have it replace z in the tree:

            3.1) If y's z's right child, then we replace z by y, leaving y's right child alone;
            3.2) Otherwise, y lies within z's right subtree but is not z's right child. In this case, we first replace y by its own right child(it is impossible that y has left
                 subtree because y is already the leftmost node); then we replace z by y.
    */    

    public void delete(Node z) {
        Node left = z.getLeft();
        Node right = z.getRight();

        if (left == null) {
            transplant(z, right); // 1) z has no left child
        } else if (right == null) { 
            transplant(z, left);//2) z has no right child
        } else {
            Node y = successor(z);
            //3.1 if y is z's right child
            if (y == z.getRight()) {
                transplant(z, y);
                y.setLeft(z.getLeft());
                z.getLeft().setParent(y);
            } else {
                //3.2 y lies within z's right subtree but is not z's right child.
                //first, we need to replace y by its own right child.
                transplant(y, y.getRight());
                                
                //second, we replace z by y
                y.setLeft(z.getLeft());
                z.getLeft().setParent(y);
                y.setRight(z.getLeft());
                z.getRight().setParent(y);
            }
        }
    }

    //return the smallest node that is larger than rt
    public Node successor(Node rt) {
        //condition 1: if rt has a right sub-tree. then the successor is the leftmost node
        //of rt's right subtree.
        if (rt.getRight() != null) {
            return minimum(rt.getRight());
        } else {
            Node child = rt;
            //condition 2: if rt doesn't have a right sub-tree and it has a successor y, then y is the lowest ancestor of rt whose
            //left child is also an ancestor x(noted that x is also the ancestor of itself). To find y, we simply go up the tree from
            //x until we encounter a node that is the left child of its parent. That parent is y.
            Node parent = rt.getParent();

            while (parent != null) {
                if (parent.getLeft() == child) {
                    return parent;
                } else {
                    child = parent;
                    parent = child.getParent();
                }
            }

            return null;
        }
    }

    //return the biggest node that is smaller than rt
    public Node predecessor(Node rt) {
        //condition 1: if rt ahs a left sub-tree, then the predecessor is the rightmost node
        //of rt's left subtree.
        if (rt.getLeft() != null) {
            return maximum(rt.getLeft());
        } else {
            Node child = rt;
            //condition 2: if rt doesn't have a left-sub tree and it has a predecessor y, then y is the lowest ancestor of rt whose
            //right child is also an ancestor of rt(noted that rt is also the ancestor of itself). To find y, we simply go up the tree from
            //rt until we counter a node that is the right child of its parent. The parent is y.
            Node parent = rt.getParent();
                
            while (parent != null) {
                if (parent.getRight() == child) {
                    return parent;
                } else {
                    child = parent;
                    parent = child.getParent();
                }
            }
        }

        return null;
    }

    public static void testSuccessor() {
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

      BST bTree = new BST(node_0);
      bTree.insert(node_1);
      bTree.insert(node_2);
      bTree.insert(node_3);
      bTree.insert(node_4);
      bTree.insert(node_5);
      bTree.insert(node_6);
      bTree.insert(node_7);
      bTree.insert(node_8);
      bTree.insert(node_9);
      bTree.insert(node_10);
      bTree.insert(node_11);
      bTree.insert(node_12);


      System.out.println(bTree.successor(node_2).getValue());
    }

    public static void main(String[] args) {
        testSuccessor();
    }
}

