import javax.swing.*;
import java.util.*;
import java.io.*;


public class DirectedGraph<T> {

    private ArrayList<Vertex> adjList = new ArrayList<Vertex>();
    private Stack<Vertex> vStack = new Stack<Vertex>();
    private HashMap<String, Vertex> adjHash = new HashMap<String, Vertex>();
    //private HashMap<Integer, Set<Integer>> adjSet = new HashMap<Integer, Set<Integer>>();

    private String vertexToGet;

    public DirectedGraph(String vertexToGet) {

        this.vertexToGet = vertexToGet;
    }

    public void initGraph(String filename) {

        ArrayList<ArrayList<String>> listOfWordLists;
        listOfWordLists = parseFile(filename);

        for(int i = 0; i < listOfWordLists.size(); i++ ) {

            // Check against hash
            Vertex node = checkHash(listOfWordLists.get(i).get(0));
            node.setName(listOfWordLists.get(i).get(0));
           // node.setVertex();

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

        JOptionPane.showMessageDialog(null, "Graph Built Successfully!");
    }

    // Finds vertex in the hashmap via the name
    public Vertex getVertexByName(String name) {

        if (adjHash.containsKey(name)) {

            return adjHash.get(name);
        }

        // else return nothing
        return null;
    }

    // Checks the hashmap for the vertex
    // returns it if found, if not creates a new vertex
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

        ArrayList<String> wordList;
        ArrayList<ArrayList<String>> listOfWordLists = new ArrayList<ArrayList<String>>();
        String wordScan;

        try {
            // Get filename from GUI input
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while ((wordScan = reader.readLine()) != null) {

                wordList = new ArrayList<String>(Arrays.asList(wordScan.split("\\s+")));
                listOfWordLists.add(wordList);
            }

        }
        catch(IOException e) {

            // Error cannot open file
            JOptionPane.showMessageDialog(null, "Cannot open file!", "Error!", JOptionPane.ERROR_MESSAGE);

        }

        return listOfWordLists;
    }

    // Adds new edge between the src/dest vertex
    public void addEdge(int src, Vertex dst) {

        adjList.get(src).addNeighbor(dst);
    }

    private void sortTopOrder() {

        // Get the index if it exists
        int i = adjList.indexOf(getVertexByName(vertexToGet));

        if(i == -1) {

            // Error! Not found in adj list
            JOptionPane.showMessageDialog(null, "Cannot find Class!", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        try {

            for(Vertex x : adjList.get(i).getNeighbors()) {

                if(!x.isVisited()) {

                    depthFirstSearch(x);
                }
            }

            System.out.print(adjList.get(i).getName() + " ");

        } catch (CycleException e) {

            JOptionPane.showMessageDialog(null, "Cycle Detected!", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        while(!vStack.empty()) {

            Vertex v = vStack.pop();
            System.out.print(v.getName() + " ");
        }
    }

    private int depthFirstSearch(Vertex s) throws CycleException {

        // Node has already been discovered
        if(s.isDiscovered()) {

           // System.out.println("\n\n" + s.getName());
           throw new CycleException();
        }

        // We currently have discovered this node in teh chain
        s.setDiscovered();

        // in the recursive call within the neighbors of the node have we discovered it?
        // We have visited the node via DFS
        if(s.isVisited()) {

            return -1;
        }

        if(s.isNeighbor()) {

            // I feel like this is useful to fix the issue
        }
            // Does vertex have neighbors
            if (s.hasNeighbors()) {

                // Go through all the neighbors
                for (Vertex v : s.getNeighbors()) {

                    depthFirstSearch(v);
                }
            }

        // We have finished the DFS search of this node
       s.setVisited();
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

        /*
        String filename = "ClassA ClassC ClassE\n" +
                "ClassB ClassD ClassG\n" +
                "ClassE ClassB ClassF ClassH\n" +
                "ClassI ClassC";
*/
        // Sets the class to do the DFS for
        String input = "ClassA";
        DirectedGraph<String> graph = new DirectedGraph<String>(input);

        //graph.parseFile(filename);

        String filename = "graph.txt";
        graph.initGraph(filename);
       graph.sortTopOrder();

       // to do
        // tie to GUI

        // Set ID's on nodes
        // Convert to using integer to vertex hash and store integer in adjlist

    }
}

