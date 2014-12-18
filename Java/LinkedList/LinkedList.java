import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

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

    public static void main(String[] args) {
        LinkedList linkedlist = new LinkedList(5);
        linkedlist.addNode(1);
        linkedlist.addNode(3);
        linkedlist.addNode(5);
        linkedlist.addNode(10);

        linkedlist.deleteNode(1);
        linkedlist.deleteNode(3);
        linkedlist.deleteNode(10);
        linkedlist.deleteNode(5);
        linkedlist.deleteNode(5);

        System.out.println(linkedlist.getSize());
    }
}
