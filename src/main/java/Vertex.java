import java.util.*;
import java.io.*;

/**
 * Created by nallgood on 3/3/17.
 */
public class Vertex {

    private boolean visited = false;
    private String name;
    private int id;
    private boolean neighbor = false;
    private boolean discovered = false;
    private boolean vertex = false;
    private boolean current = false;
    private String color = "white";

    // List of neighbors
    LinkedList<Vertex> neighbors = new LinkedList<Vertex>();

    public Vertex() {

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

    public void unSetDiscovered(){

        discovered = false;
    }

    public void setVertex() {

        vertex = true;
    }

}

