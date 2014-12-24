/*
    Given a directed graph, design an algorithm to find out whether there
    is a route between two nodes
*/

import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

enum Color {
    WHITE, GRAY, BLACK;
}

class Vertex {
    private int key;
    private Vertex predecessor;  //predecessor (used in BFS)
    private Color color; //the color (used in BFS)
    private int distance;
    private LinkedList<Vertex> adjacents; //the adjacent nodes

    private int d;  //the timestamp right before u is discovered(used in DFS)
    private int f;  //the timestamp right after u is discovered(used in DFS)

    Vertex() {
        key = -1;
        color = Color.WHITE;
        distance = -1;
        adjacents = new LinkedList<Vertex>();
    }

    Vertex(Vertex v) {
        key = v.getKey();
        color = v.getColor();
        distance = v.getDistance();
        predecessor = v.getPredecessor();
        adjacents = v.getAdjacents();
    }

    Vertex(int k, Vertex p, Color c, int d, LinkedList<Vertex> a) {
        key = k;
        predecessor = p;
        color = c;
        distance = d;
        adjacents = a;
    }

    public void setKey(int k) {
        key = k;
    }

    public int getKey() {
        return key;
    }

    public void setPredecessor(Vertex p) {
        predecessor = p;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public void setDistance(int d) {
        distance = d;
    }

    public int getDistance() {
        return distance;
    }

    public void setAdjacents(LinkedList<Vertex> a) {
        adjacents = a;
    }

    public LinkedList<Vertex> getAdjacents() {
        return adjacents;
    }

    public void addAdjacent(Vertex n) {
        adjacents.add(n);
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getF() {
        return f;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getD() {
        return d;
    }
}

/*
    Assume the graph is undirected graph and we use adjacent-list
    to store the vertexes and edges.
*/

class Graph {
    private Vertex[] vertices;  //the vertices (used in both BFS and DFS)
    private Vertex source;      //the source vertex (used in BFS only)
    private int time = 0;

    Graph(Vertex[] vertices) {
        this.vertices = vertices;
        source = vertices[0];   //we set the first node as the source vertex
                                //by default
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getSource() {
        return source;
    }

    //Breadth First Search
    public void BFS() {
        //Step1: Initialization
        for (Vertex vertex : vertices) {
            if (vertex == source) {
                continue;
            }

            vertex.setColor(Color.WHITE);
            vertex.setDistance(-1);
            vertex.setPredecessor(null);
        }

        source.setColor(Color.GRAY); //set source's color as GRAY
        source.setDistance(0); //set the distance as 0
        Queue<Vertex> queue = new LinkedList<Vertex>(); //Create a queue, which will be parsed in step 2
        queue.add(source);  //add the source vertex to the queue

       //Step 2: process the vertices in the queue. Add the "new GRAY" vertices in the queue
       Vertex u, v;
       while(queue.peek() != null) {
           u = queue.poll(); //Retrieve one node u from the queue.
           //Iterate through all u's adjacent nodes
           for (int i = 0; i < u.getAdjacents().size(); i++) {
               v = u.getAdjacents().get(i);
               if (v.getColor() == Color.WHITE) {
                   v.setColor(Color.GRAY);
                   v.setDistance(u.getDistance() + 1);
                   v.setPredecessor(u);
                   queue.add(v); //Add the vertex to the queueu if it's "new GRAY"
               }
           }
           u.setColor(Color.BLACK);
           System.out.println("the key = " + u.getKey() + " the distance = " + u.getDistance());
       }
    }
    public void printPath(Vertex v) {
        if (v == source) {
            System.out.println(source.getKey());
        } else if (v.getPredecessor() == null) {
            System.out.println("no path from \"s\" to \"v\" exists");
        } else {
            printPath(v.getPredecessor());
            System.out.println(v.getKey());
        }
    }

    //Depth First Search
    public void DFS() {
        //Step 1: Initialization
        for (Vertex vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
        }

        time = 0;
        for (Vertex vertex : vertices) {
            if (vertex.getColor() == Color.WHITE ) {
                dfsVisit(vertex);
            }
        }
    }

    public void dfsVisit(Vertex u) {

        System.out.println("Start processing vertex " + u.getKey());

        time = time + 1; //this white vertex has just been discovered
        u.setD(time);
        u.setColor(Color.GRAY);
        
        Vertex v;
        for (int i = 0; i < u.getAdjacents().size(); i++) {
            v = u.getAdjacents().get(i);
            if (v.getColor() == Color.WHITE) {
                v.setPredecessor(u);
                dfsVisit(v);
            }
        }

        System.out.println("finish processing vertex " + u.getKey());

        u.setColor(Color.BLACK);
        time = time + 1;
        u.setF(time);
    }

    @Test
    public static void testBFS() {
      Vertex v1 = new Vertex();
      Vertex v2 = new Vertex();
      Vertex v3 = new Vertex();
      Vertex v4 = new Vertex();
      Vertex v5 = new Vertex();

      v1.setKey(1);
      v2.setKey(2);
      v3.setKey(3);
      v4.setKey(4);
      v5.setKey(5);

      //Create the connections
      v1.addAdjacent(v2);
      v1.addAdjacent(v5);

      v2.addAdjacent(v1);
      v2.addAdjacent(v5);
      v2.addAdjacent(v3);
      v2.addAdjacent(v4);

      v3.addAdjacent(v2);
      v3.addAdjacent(v4);

      v4.addAdjacent(v2);
      v4.addAdjacent(v5);
      v4.addAdjacent(v3);

      v5.addAdjacent(v4);
      v5.addAdjacent(v1);
      v5.addAdjacent(v2);

      Vertex[] vs = new Vertex[]{v1, v2, v3, v4, v5};

      //Create a graph
      Graph gr = new Graph(vs);

      //gr.BFS();
      //gr.printPath(v4);

      gr.DFS();

      //boolean result = gr.hasRouteBetween(v1, v3, gr);

      //System.out.println(result);

      return;
    }

    //Return true if there is a route between v1 and v2; return false otherwise. 
    //This problem can be solved by just simple graph traversal, such as depth first
    //search or breadth first search. We first start one of the two nodes and, during traversal,
    //check if the other node is found. We should mark any node found in the course of the algorithm
    //as already visited to avoid cycles repetition of the nodes
    public boolean search(Vertex v1, Vertex v2, Graph g) {
        //Step 1: run BFS
        Vertex[] vertices = g.getVertices();
        for (int i = 0; i < vertices.length; i++) {
            vertices[i].setColor(Color.WHITE);
        }

        v1.setColor(Color.GRAY);
        Queue<Vertex> queue = new LinkedList<Vertex>(); //Create a queue, which will be parsed in step 2
        queue.add(v1);

        while(queue.peek() != null) {
            Vertex u = queue.poll();
            for (Vertex v : u.getAdjacents()) {
                if (v.getColor() == Color.WHITE) {
                    if (v == v2) {
                        return true;
                    } else {
                        v.setColor(Color.GRAY);
                        queue.add(v);
                    }
                }
            }

            u.setColor(Color.BLACK);
        }

        return false;
    }

    public boolean hasRouteBetween(Vertex v1, Vertex v2, Graph g) {
        return search(v1, v2, g);

        //!!note: if the graph g is directed graph, we need to call the search function twice
        //return (search(v1, v2, g) || search(v2, v1, g));
    }

    public static void main(String[] args) {
        testBFS();
    }
}
