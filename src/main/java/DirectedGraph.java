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

    private HashMap<String, Integer> adjHash = new HashMap<String, Integer>();


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

        //System.out.println("Graph Built Successfully!\n");


    }

    // Adds new edge between the src/dest vertex
    public void addEdge(int src, String dst) {

       // adjList.get(src).add(dst);

    }

    public void printTopOrder(Vertex z) {

        System.out.print(z.getName()+ " ");

        System.out.print(z.getNeighbors());

        System.out.println();


    }
    public void sortTopOrder() {

        ArrayList<Vertex> vList  = new ArrayList<Vertex>();

        try {

            for(Vertex v : adjList) {

                depthFirstSearch(v);
            }

        } catch (CycleException e) {

            System.out.println("Cycle Detected!");


        }

        while(!vStack.empty()){

            Vertex current = vStack.pop();

            //System.out.print(current.getName());

            for(Vertex z : current.getNeighbors()) {

                System.out.print(" " + z.getName() + " ");
            }

           // System.out.println("\n");
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

    private void testDFSPrint() {

        //

        try {

            for(Vertex v : adjList) {

                testDFS(v);
            }

        } catch (CycleException e) {

            e.printStackTrace();
        }

        while(!vStack.empty()) {

            System.out.print(vStack.pop().getName() + " ");
        }
    }

    private int testDFS(Vertex s) throws CycleException {



        if(s.isVisited()) {

            throw new CycleException();
        }
        // Mark s visited
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

    public int depthFirstSearch(Vertex s) throws CycleException {


        // If we have visited a node already we have a cycle in the graph
        if(s.isVisited()) {

            throw new CycleException();
        }

        // Do we have any neighbors?
        if(s.getNeighbors().isEmpty()) {

            return 0;
        }

        // Set vertex as visited
        s.setVisited();

        // Do a DFS on each neighbor of the vertex
        for(Vertex v : s.getNeighbors()) {

            if(v.isVisited()) {

                throw new CycleException();
            }

            depthFirstSearch(v);
            v.setVisited();
            vStack.push(v);

        }

        // Push vertex to stack
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

      //graph.sortTopOrder();

        graph.testDFSPrint();




    }


}

