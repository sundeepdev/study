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
    private int distance
    private LinkedList<Vertex> adjacents //the adjacent nodes

    Vertex() {
        key = -1;
        color = Color.WHITE;
        dist = -1;
        adj = new LinkedList<Vertex>();
    }

    Vertex(Vertex v) {
        key = v.getKey();
        color = v.getColor();
        distance = v.getDistance();
        predecessor = v.getPredecessor();
        adjacents = v.getAdjacents();
    }

    Vertex(int k, Vertex p, Color c, int d, Linkedlist<Vertex> a) {
        key = k;
        predecessor = p;
        color = c;
        distance = d;
        adjacents = a;
    }

    public void setKey(int k) {
        key = k;
    }

    public void getKey() {
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
}

/*
    Assume the graph is undirected graph and we use adjacent-list
    to store the vertexes and edges.
*/

class Graph {
    private Vertex[] vertices;  //the vertices
    private Vertex source;      //the source vertex

    Graph(Vertex[] vertices) {
        this.vertices = vertices;
        source = vertices[0];   //we set the first node as the source vertex
                                //by default
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
        Queue<Vertex> queue = new LinkedList<Vertex>(); //Create a queue
        queue.add(source);  //add the source vertex to the queue
    }

    Vertex u, v;
    while(Q.peek != null) {
        u = Q.poll(); //Retrieve one node from the queue.
    }
}


