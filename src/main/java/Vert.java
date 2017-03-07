/**
 * Created by nallgood on 3/6/17.
 */
import java.lang.*;
import java.io.*;

public class Vert {

    String name;
    Neighbor adjList;

   public Vert(String name, Neighbor nbrs) {

        this.name = name;
        this.adjList = nbrs;
    }
}
