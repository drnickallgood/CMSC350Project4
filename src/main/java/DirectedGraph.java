/**
 * Created by nallgood on 2/27/17.
 */


import java.util.*;
import java.io.*;


public class DirectedGraph<T> {

    private int numV;

    //private ArrayList<LinkedList<String>> adjList;

    // adjacency list will have vertex neighbor neighbor
    // ClassA ClassB ClassC
    private ArrayList<Vertex> adjList = new ArrayList<Vertex>();
    ArrayList<ArrayList<String>> listOfWordLists = new ArrayList<ArrayList<String>>();

    private Stack<Vertex> vStack = new Stack<Vertex>();

    public DirectedGraph() {

        //this.numV = numV;
       // adjList = new ArrayList<LinkedList<String>>();


    }

    public void initGraph(String filename) {

        Scanner wordScan = new Scanner(filename);
        ArrayList<String> wordList = new ArrayList<String>();


        LinkedList<Vertex> tempL = new LinkedList<Vertex>();

        while(wordScan.hasNext()) {

            // put words in a word list
            wordList = new ArrayList<String>(Arrays.asList(wordScan.nextLine().split("\\s+")));
            int counter = 0;
            // if wordlist size > 1
            // loop thorugh word list
            // first node is the vertex
            // add neighbors to vertex

            // Ifwe have more than one entry here, then we know we have neighbors

            Vertex V;
            V = new Vertex();
            V.setName(wordList.get(counter));
            V.setID(counter);
           // System.out.println("Creating new Head vertex: " + V.getName());


                for(int i = 1; i < wordList.size(); i++) {

                        //Create a new vertex that is a neighbor
                        // Set ID and name
                       Vertex newV = new Vertex();
                        newV.setName(wordList.get(i));
                        newV.setID(i);

                        // Add new vertex as neighbor to current vertex
                        V.setNeighbors(newV);

                }

                // Add all verticies that have neighbors to the graph list
            adjList.add(V);
            counter++;
        }

       // adjList.add(tempL);
        // Close Scanner Object
        wordScan.close();

    }

    // Adds new edge between the src/dest vertex
    public void addEdge(int src, String dst) {

       // adjList.get(src).add(dst);

    }


    public void sortTopOrder() {

        ArrayList<Vertex> vList  = new ArrayList<Vertex>();

        try {

            for(Vertex v : adjList) {

                depthFirstSearch(v);
            }

        } catch (CycleException e ) {

            System.out.println("Cycle Detected!");

        }
        // Check to see the stacks not empty
      while(!vStack.empty()) {

          // Pop the stack and add to the list
          vList.add(vStack.pop());

      }

     // System.out.println(vList);

        for(int i = 0; i < vList.size(); i++) {

          System.out.print(vList.get(i).getName() + " ");
        }

        /*
        depth_first_search(vertex s)
  if s is discovered
    throw cycle detected exception
  if s is finished
    return
  mark s as discovered
  for all adjacent vertices v
    depth_first_search(v)
  mark s as finished
  push s onto the stack
         */

    }


    public int depthFirstSearch(Vertex s) throws CycleException {

        // Have we visited this vertex before?
        if(s.isVisited()) {

            throw new CycleException();
            //System.out.println("Cycle detected!\n");
            //System.exit(-1);
        }

        // Do we have any neighbors?
        if(s.getNeighbors().isEmpty()) {

            return 0;
        }

        // Set vertex as visited
        s.setVisited();

        // Do a DFS on each neighbor of the vertex
        for(Vertex v : s.getNeighbors()) {

            depthFirstSearch(v);
        }

        // Push vertex to stack
        vStack.push(s);

        return 0;
    }


    private void printAdjList() {

        for(Vertex V : adjList) {

            System.out.println("Vertex: " + V.getName());
            for(Vertex Z : V.getNeighbors()) {

                System.out.println("Neighbors: " + Z.getName());
            }

            System.out.println();

        }

    }

    // For testing
    public static void main(String[] args) {

        // Open File Code

        String filename = "ClassA ClassB ClassC\nClassC ClassD ClassE\nClassF ClassG";

        DirectedGraph<String> graph = new DirectedGraph<String>();

        graph.initGraph(filename);

       // graph.printAdjList();

       graph.sortTopOrder();


    }


}

