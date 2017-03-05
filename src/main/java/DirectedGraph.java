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
     private ArrayList<LinkedList<Vertex>> adjList = new ArrayList<LinkedList<Vertex>>();
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

            // Add the list of parsed words to the list of word lsits
            listOfWordLists.add(wordList);


                //tempL.add(new Vertex());
               // tempL.get(i).setName(wordList.get(i));


               // System.out.println("Adding vertex: " + tempL.get(i).getName() + " to list position: " +i);


            //adjList.add(tempL);

        }

       // adjList.add(tempL);
        // Close Scanner Object
        wordScan.close();

        for(int i = 0; i < listOfWordLists.size(); i++) {

            // Add a new linked list to our list of lists
            adjList.add(new LinkedList<Vertex>());

            for(int j = 0; j < listOfWordLists.get(i).size(); j++ ) {

                adjList.get(i).add(new Vertex());
                String name = listOfWordLists.get(i).get(j);
                adjList.get(i).get(j).setName(name);

                // do a depth first search
                dfs(adjList.get(i).get(j));

            }
        }
        //System.out.println(newMap);



    }

    // Adds new edge between the src/dest vertex
    public void addEdge(int src, String dst) {

       // adjList.get(src).add(dst);

    }


    public void sortTopOrder() {

        ArrayList<Vertex> vList  = new ArrayList<Vertex>();

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


    public int dfs(Vertex s) {

        // Have we visited this vertex before?
        if(s.isVisited()) {

            //throw new Exception("Cycle detected for Vertex");
            System.out.println("Cycle detected!\n");
            //System.exit(-1);
        }

        if(adjList.isEmpty()) {

            return 1;
        }

        // Set vertex as visited
        s.setVisited();

        // Push vertex to stack
        vStack.push(s);

        return 0;
    }


    private void printAdjList() {

       //System.out.println(adjList.get(0).get(0).getName());
        for(int i = 0; i < listOfWordLists.size(); i++) {

            for(int j = 0; j < listOfWordLists.get(i).size(); j++) {

                System.out.print(adjList.get(i).get(j).getName() + " ");
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

        graph.printAdjList();

        graph.sortTopOrder();


    }


}

