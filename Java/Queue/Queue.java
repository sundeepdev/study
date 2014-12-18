import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

//A linkedlist based stack
class Queue {
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

    private Node first = null;
    private Node last = null;
    
    public Queue(int d) {
        Node n = new Node(d);
        first = last = n;
    }

    public void enqueue(int d) {
        if (first == null) {
            first = last = new Node(d);
        } else {
            last.setNext(new Node(d));
            last = last.getNext();
        }
    }

    public Node dequeue() {
        if (first == null) {
            return null;
        } else {
            Node result = first;
            first = result.getNext();
            if (first == null) {
                last = null;
            }

            return result;
        }
    }

    public int size() {
        int result = 0;
        Node cur = first;

        while(cur != null) {
            result++;
            cur = cur.getNext();
        }

        return result;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    @Test
    public static void testQueue() {
        //Create a Queue
        Queue queue = new Queue(5);

        //Enqueueu some elements
        queue.enqueue(10);
        queue.enqueue(7);

        //Verify the test result
        assertEquals("the size must be equal to 3", 3, queue.size());
        assertEquals("the first must be equal to 5", 5, queue.getFirst().getData());
        assertEquals("the last must be equal to 7", 7, queue.getLast().getData());

        queue.dequeue();
        assertEquals("the size must be equal to 2", 2, queue.size());
        assertEquals("the first must be equal to 10", 10, queue.getFirst().getData());
        assertEquals("the last must be equal to 7", 7, queue.getLast().getData());

        queue.dequeue();
        assertEquals("the size must be equal to 1", 1, queue.size());
        assertEquals("the last must be equal to 7", 7, queue.getLast().getData());
        assertEquals("the last must be equal to 7", 7, queue.getFirst().getData());
        
        queue.dequeue();
        assertEquals("the size must be equal to 0", 0, queue.size());
        assertEquals("the last must be equal to null", null, queue.getLast());
        assertEquals("the last must be equal to null", null, queue.getFirst());

        queue.dequeue();
        assertEquals("the size must be equal to 0", 0, queue.size());

        queue.enqueue(100);
        assertEquals("the size must be equal to 1", 1, queue.size());
        assertEquals("the last must be equal to 100", 100, queue.getLast().getData());
        assertEquals("the last must be equal to 100", 100, queue.getFirst().getData());

        queue.enqueue(10);
        assertEquals("the size must be equal to 2", 2, queue.size());
        assertEquals("the last must be equal to 10", 10, queue.getLast().getData());
        assertEquals("the last must be equal to 100", 100, queue.getFirst().getData());
    }

    public static void main(String[] args) {
        testQueue();
    }
}
