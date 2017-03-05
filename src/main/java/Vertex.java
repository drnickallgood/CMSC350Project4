import java.util.*;
import java.io.*;

/**
 * Created by nallgood on 3/3/17.
 */
public class Vertex {

    private boolean visited = false;
    private String name;
    private int id;


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


}
