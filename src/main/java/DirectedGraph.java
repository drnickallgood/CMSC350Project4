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

    private Stack<Vertex> vStack = new Stack<Vertex>();

    private HashMap<String, Vertex> adjHash = new HashMap<String, Vertex>();


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
            Vertex V = new Vertex();


            if(!adjHash.containsKey(wordList.get(counter))) {

                // Place into hash
                // For now, string, Vertex
                V.setName(wordList.get(counter));
                V.setID(counter);
                adjHash.put(wordList.get(counter), V);

            }


           // System.out.println("Creating new Head vertex: " + V.getName());


                for(int i = 1; i < wordList.size(); i++) {

                        //Create a new vertex that is a neighbor
                        // Set ID and name
                       Vertex newV = new Vertex();
                        // Place into hash
                        // For now, string, Vertex
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

        //System.out.println("Graph Built Successfully!\n");


    }


    public Vertex addToHash(String name, Vertex v) {

        if(adjHash.containsKey(name)) {

        }
        else {

        }
    }
    public String getVertexByName(Vertex V) {

        return V.getName();

    }

    // Adds new edge between the src/dest vertex
    public void addEdge(int src, String dst) {

       // adjList.get(src).add(dst);

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

    private void testDFSPrint() {

        //

        try {

            for(Vertex x : adjList) {

                testDFS(x);
            }


        } catch (CycleException e) {

            e.printStackTrace();
        }

        while(!vStack.empty()) {

            System.out.print(vStack.pop().getName() + " ");
        }
    }

    private int testDFS(Vertex s) throws CycleException {

        // in the recursive call within the neighbors of the node have we discovered it?

        if(s.isVisited()) {

            throw new CycleException();
        }

        s.setVisited();

        // Go through all the neighbors
        for(Vertex v : s.getNeighbors()) {

            if(!v.isVisited()) {

                testDFS(v);

            }
        }

        vStack.push(s);

        return 0;
    }


    private void printAdjList() {

        for(Vertex V : adjList) {

            System.out.println("Vertex: " + V.getName());

            System.out.print("Neighbors: ");
            for(Vertex Z : V.getNeighbors()) {

                System.out.print(Z.getName() + " ");
            }

            System.out.println();
            System.out.println();

        }

    }

    // For testing
    public static void main(String[] args) {

        // Open File Code

        //String filename = "ClassA ClassB ClassC\nClassC ClassD ClassE\nClassF ClassG";
        //String filename = "ClassA ClassB ClassC\nClassC ClassD ClassE\nClassF ClassB";

        String filename = "ClassA ClassC ClassE\n" +
                "ClassB ClassD ClassG\n" +
                "ClassE ClassB ClassF ClassH\n" +
                "ClassI ClassC";


        //String filename = "ClassA ClassB ClassC";

        DirectedGraph<String> graph = new DirectedGraph<String>();

        graph.initGraph(filename);

      //graph.printAdjList();

        graph.testDFSPrint();




    }


}

