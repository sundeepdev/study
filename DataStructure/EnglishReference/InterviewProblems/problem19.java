/*
    How would you design a stack which, in addition to push and pop, also has a function min which returns the minimum element?
    Push, pop, and min should all operate in O(1) time.
*/


import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

//A linkedlist based stack
class Stack {
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

    private Node top;
    public Node pop() {
        if (top != null) {
            Node result = top;
            top = top.next;
            return result;
        }

        return null;
    }

    public boolean isEmpty() {
        return (top == null) ;
    }

    public void push(int d) {
        Node n = new Node(d);
        n.setNext(top);
        top = n;
    }

    public Stack(int d) {
        Node n = new Node(d);
        top = n;
    }

    public Stack() {
        top = null;
    }

    public Node getTop() {
        return top;
    }

    public int size() {
        int result = 0;
        Node cur = top;
        while(cur != null) {
            result++;
            cur = cur.getNext();
        }

        return result;
    }

    public int peek() {
        return top.getData();
    }

    @Test
    public static void testStack() {
        //Create a stack
        Stack stack = new Stack(7);
        //push some nodes
        stack.push(5);
        stack.push(10);

        //Verify the results
        assertEquals("the size must be equal to 3", 3, stack.size());
        assertEquals("the popped element must be equal to 10", 10, stack.pop().getData());
        assertEquals("the size must be equal to 2", 2, stack.size());
        assertEquals("the popped element must be equal to 5", 5, stack.pop().getData());
        assertEquals("the size must be equal to 1", 1, stack.size());
        assertEquals("the popped element must be equal to 7", 7, stack.pop().getData());
        assertEquals("the size must be equal to 0", 0, stack.size());

        assertEquals("the popped element must be null", null, stack.pop());        
        stack.push(3);
        assertEquals("the size must be equal to 1", 1, stack.size());
        stack.push(10);
        stack.push(20);
        assertEquals("the size must be equal to 3", 3, stack.size());

        assertEquals("the popped element must be equal to 20", 20, stack.pop().getData());
        assertEquals("the size must be equal to 2", 2, stack.size());
    }
}

class StackWithMin extends Stack {
    Stack s2;
    public StackWithMin() {
        s2 = new Stack();
    }

    public StackWithMin(int d) {
        super(d);
        s2 = new Stack(d);
    }

    @Override
    public void push(int value) {
        if (value <= min()) {
            s2.push(value);
        }

        super.push(value);
    }

    @Override
    public Node pop() {
        Node n_super = super.pop();

        if (n_super != null) {
            if (n_super.getData() == min()) {
                s2.pop();
            }
        }

        return n_super;
    }

    public int min() {
        if (s2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return s2.peek();
        }
    }
}

public class problem19 {

    @Test
    public static void testStackWithMin() {
        
        //Create a stack
        StackWithMin stack = new StackWithMin(7);
        //push some nodes
        stack.push(5);
        stack.push(10);

        assertEquals("the min element must be 5", 5, stack.min());
        //Verify the results
        assertEquals("the size must be equal to 3", 3, stack.size());
        assertEquals("the popped element must be equal to 10", 10, stack.pop().getData());
        assertEquals("the size must be equal to 2", 2, stack.size());
        assertEquals("the popped element must be equal to 5", 5, stack.pop().getData());
        assertEquals("the size must be equal to 1", 1, stack.size());
        assertEquals("the popped element must be equal to 7", 7, stack.pop().getData());
        assertEquals("the size must be equal to 0", 0, stack.size());

        assertEquals("the popped element must be null", null, stack.pop());        
        stack.push(3);
        assertEquals("the size must be equal to 1", 1, stack.size());
        stack.push(10);
        stack.push(20);
        assertEquals("the size must be equal to 3", 3, stack.size());

        assertEquals("the popped element must be equal to 20", 20, stack.pop().getData());
        assertEquals("the size must be equal to 2", 2, stack.size());

        
        assertEquals("the min element must be 3", 3, stack.min());
    }

    public static void main(String[] args) {
        testStackWithMin();
    }
}
