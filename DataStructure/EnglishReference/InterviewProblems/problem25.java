/*
    Design an algorithm and write code to find the first common ancestor
    of two nodes in a binary tree. Avoid storing additional nodes in a
    data structure. Note: This is not necessarily a binary search tree.(!!)

    Book's solution:

    Attempt#1:  If each node has a link to its parent, we could trace p and q's path up until intersect.

    Attempt#2:  Alternatively, you could follow a chain in which p and q are on the same side. That is, if p
                and q are on the left of the node, branch left to look for the common ancestor. When p and q
                are no longer on the same side, you must have found the first common ancestor.

    Attempt#3:  For any node r, we know the following:

                1.  If p is on one side and q is on the other, r is the first common ancestor.
                2.  Else, the first common ancestor is on the left or the right side.

    Questions:

    1) What if the tree is BST??

    
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

    //My solution seems breaking the requirements: Avoid storing additional nodes in a
    //data structure.
    public Node findFirstAncestor(Node n1, Node n2) {
        
        //Create two hash
        HashSet<Node> set1 = new HashSet<Node>();
        HashSet<Node> set2 = new HashSet<Node>();

        Node cur1 = n1;
        Node cur2 = n2;
        
        //Step 1: this takes O(max(depth(n1), depth(n2)))
        while(cur1 != null || cur2 != null) {
            if (cur1 != null) {
                set1.add(cur1);
                cur1 = cur1.getParent();
            }
            
            if (cur2 != null) {
                set2.add(cur2);
                cur2 = cur2.getParent();
            }
        }

        //Step 2: this takes O(min(depth(n1), depth(n2)))
        if (set1.size() < set2.size()) {
            cur2 = n2;
            
            while(cur2 != null) {
                if (set1.contains(cur2)) {
                    return cur2;
                }
            }

            cur2 = cur2.getParent();
        } else {
            cur1 = n1;
            while(cur1 != null) {
                    
                if (set2.contains(cur1)) {
                    return cur1;
                }
                
                cur1 = cur1.getParent();
            }
        }

        return null;
    }

     
    /*  !!
        This idea is using the bottom-up approach (worst case O(n)): 
        We traverse from the bottom, and once we reach a node which 
        matches one of the two nodes. If yes, then the parent must be 
        the LCA and we pass its parent up to the root. If not, we pass
        the lower node which contains either one of the two nodes (if 
        the left or right subtree contains either p or q), or NULL (if
        both the left and right subtree does not contain either p or q) up.
    */
    
    public static Node LCA(Node rt, Node p, Node q) {
        if (rt == null) {
            return null;
        }

        if (rt == p || rt == q) {
            return rt;
        }

        Node L = LCA(rt.getLeft(), p, q);
        Node R = LCA(rt.getRight(), p, q);

        if (L != null && R != null) { //if p and q are on both sides
            return rt;
        }

        return L != null? L:R; //either one of p, q is on one side OR p, q is not in L&R subtrees.
    }

    public static void testFindFirstAncestor() {
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

      BinTree bTree = new BinTree(node_0);

      //Node result = bTree.findFirstAncestor(node_0, node_0);

      Node result = LCA(bTree.getRoot(), node_3, node_6);
    
      System.out.println(result.getValue());

      
    }

    public static void main(String[] args) {

        testFindFirstAncestor();
    }
}

