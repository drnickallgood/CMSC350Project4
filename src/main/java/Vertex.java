import java.util.*;
import java.io.*;

/**
 * Created by nallgood on 3/3/17.
 */
public class Vertex {

    // End of DFS search
    private boolean visited = false;
    private String name;
    private int id;

    // Is a neighbor node
    private boolean neighbor = false;

    //Is currently found but not at the end of DFS search
    private boolean discovered = false;

    // List of neighbors
    LinkedList<Vertex> neighbors = new LinkedList<Vertex>();

    public Vertex() {

    }

    public Vertex(String name) {

        this.name = name;
    }

    public Vertex(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public boolean isVisited() {

        return visited;
    }

    public void setVisited() {

        visited = true;
    }

    public int getID() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setName(String s) {

        name = s;

    }

    public void setID(int i ) {

        id = i;
    }

    public void addNeighbor(Vertex v) {

        neighbors.add(v);
    }

    public LinkedList<Vertex> getNeighbors() {

        return neighbors;
    }

    public void setNeighbor() {

         neighbor = true;
    }

    public boolean isNeighbor() {

        return neighbor;
    }

    public boolean hasNeighbors() {

        if(neighbors.isEmpty()) {

            return false;
        }

        return true;
    }

    public boolean isDiscovered() {

        return discovered;
    }

    public void setDiscovered() {

        discovered = true;
    }

}

