import java.util.*;
import java.io.*;

/**
 * Created by nallgood on 3/3/17.
 */
public class Vertex {

    private boolean visited = false;
    private String name;
    private int id;

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

    public void setNeighbors(Vertex v) {

        neighbors.add(v);
    }

    public LinkedList<Vertex> getNeighbors() {

        return neighbors;
    }


}
