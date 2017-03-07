import java.util.*;
import java.io.*;


public class DirectedGraph<T> {

    private int numV;
    private ArrayList<Vertex> adjList = new ArrayList<Vertex>();
    private Stack<Vertex> vStack = new Stack<Vertex>();
    private HashMap<String, Vertex> adjHash = new HashMap<String, Vertex>();
    //private HashMap<Integer, Set<Integer>> adjSet = new HashMap<Integer, Set<Integer>>();

    public DirectedGraph() {

    }

    public void initGraph(String filename) {

        Scanner wordScan = new Scanner(filename);
        ArrayList<String> wordList = new ArrayList<String>();
        ArrayList<ArrayList<String>> listOfWordLists;

        listOfWordLists = parseFile(filename);

        for(int i = 0; i < listOfWordLists.size(); i++ ) {

            // Check against hash
            Vertex node = checkHash(listOfWordLists.get(i).get(0));
            node.setName(listOfWordLists.get(i).get(0));

            for(int j = 1; j < listOfWordLists.get(i).size(); j++) {

                //Vertex neighbor = new Vertex();
                Vertex neighbor = checkHash(listOfWordLists.get(i).get(j));
                neighbor.setName(listOfWordLists.get(i).get(j));

                // This is a neighbor node
                neighbor.setNeighbor();

                //Add this as a neighbor list
                node.addNeighbor(neighbor);
            }

            adjList.add(node);
        }
    }

    public Vertex checkHash(String vName) {

        // We already have
        if (adjHash.containsKey(vName)) {

            return adjHash.get(vName);
        }
        else {

            Vertex v = new Vertex();
            adjHash.put(vName, v);

            return v;
        }
    }

    // This simply parses the input file and breaks it into a list
    // containing lists of words
    public ArrayList<ArrayList<String>> parseFile(String filename) {

        Scanner wordScan = new Scanner(filename);
        ArrayList<String> wordList;
        ArrayList<ArrayList<String>> listOfWordLists = new ArrayList<ArrayList<String>>();

        while (wordScan.hasNext()) {

            wordList = new ArrayList<String>(Arrays.asList(wordScan.nextLine().split("\\s+")));
            listOfWordLists.add(wordList);
        }

        return listOfWordLists;
    }

    // Adds new edge between the src/dest vertex
    public void addEdge(int src, Vertex dst) {

       // adjList.get(src).add(dst);
        adjList.get(src).addNeighbor(dst);
    }

    private void testDFSPrint() {

        try {

            for(Vertex x : adjList) {

                //testDFS(x);
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

            // Has v been visited from anotehr branch?
            if(v.isNeighbor()) {

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
        // throw exception if file not found and display msg

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

