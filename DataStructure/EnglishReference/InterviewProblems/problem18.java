import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/*

problem 18:Write code to remove duplicates from an unsorted linkedlist.
FOLLOWUP: How would you solve this problem if a temporary buffer is not allowed?

problem 19: Implement an algorithm to find the nth to last element of a singly linked list.

*/

class LinkedList {
    private int size = 0;
    private Node head;

    class Node {
    
        private Node next = null;
        private int data;
        public Node(int d) {
            data = d;
        }

        public void setData(int d) {
            data = d;
        }

        public int getData() {
            return data;
        }

        public void setNext(Node n) {
            next = n;
        }

        public Node getNext() {
            return next;
        }

        public void appendToTail(int d) {
            Node end = new Node(d);
            Node n = this;
            while(n.next != null) {
                n = n.next;
            }

            n.next = end;
        }
    }

    public LinkedList(Node n) {
        size = 1;
        head = n;
    }

    public LinkedList(int d) {
        size = 1;
        head = new Node(d);
    }

    public int getSize() {
        return size;
    }

    public void addNode(int d) {
        Node n = head;
        while(n.next != null) {
            n = n.next;
        }

        Node newNode = new Node(d);
        n.next = newNode;
        size++;
    }

    public Node deleteNode(int d) {
        Node n = head;
        Node parent = null;  

        while (n != null) {
            if (n.getData() == d) {
                if (n == head) {
                    head = n.next;   
                } else {
                    parent.next = n.next;
                }
                size--;
                return n;
            }

            parent = n;
            n = n.next;
        }

        return n;
    }

    public void removeDuplicates() {
        //Use buffer to achieve this. Here I use set since it doesn't allow duplicate elements.
        //This method takes O(n) but requires an extra buffer
        Set<Integer> set = new HashSet<Integer>();
        Node n = head;
        Node parent = null;
    
        while(n != null) {
            Integer v = n.getData();
            if (set.contains(v)) {
                if (n == head) {
                    head = n.next;
                } else {
                    parent.next = n.next;
                }
                
                size--;
            } else {
                set.add(v);
            }

            parent = n;
            n = n.next;
        }
    }

    public void removeDuplicates2() {
        //Do not use buffer to achieve this. but it takes O(n.^2)
        Node m = head.next;
        Node parent = head;

        while( m != null) {
            Node n = head;
            while(n != m) {
                if (n.getData() == m.getData()) {
                    parent.next = m.next;
                    size--;
                    break;
                }

                n = n.next;
            }
            
            parent = m;
            m = m.next;
        }
    }

    public Node findNthElem(int n) {
        if (n < 0) {
            return null;
        }

        if (n > (size-1)) {
            return null;
        }

        Node node = head;
        while(n != 0) {
            node = node.next;
            n--;
        }

        return node;
    }

    public Node findNthElem2(int n) {
        if (n < 0) {
            return null;
        }

        Node node = head;
        while(n != 0 && node != null) {
            node = node.next;
            n--;
        }

        if (node != null) {
            return node;
        } else {
            return null;
        }
    }

    public void print() {
        Node n = head;
        while(n != null) {
            System.out.println(n.getData() + " ");
            n = n.next;
        }
    }

    public static void main(String[] args) {
        LinkedList linkedlist = new LinkedList(5);
        linkedlist.addNode(5);
        linkedlist.addNode(1);
        linkedlist.addNode(3);
        linkedlist.addNode(2);
        linkedlist.addNode(5);
        linkedlist.addNode(10);
        linkedlist.addNode(2);

        linkedlist.removeDuplicates2();

        Node n = linkedlist.findNthElem2(0);
        if (n != null) {
            System.out.println(n.getData());
        } else {
            System.out.println("couldn't find this element");
        }

        System.out.println(linkedlist.getSize());
    }
}
