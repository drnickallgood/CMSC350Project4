
/**
 * Created by nallgood on 1/11/17.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/* This class sets up a nicer frame for display */
class NiceFrame extends JFrame {
    static final int WIDTH = 300, HEIGHT = 300;
    public NiceFrame() {
        super("Nice Frame");
        setFrame(WIDTH, HEIGHT);
    }

    public NiceFrame(String title) {
        super(title);
        setFrame(WIDTH, HEIGHT);
    }

    public NiceFrame(String title, int width, int height) {
        super(title);
        setFrame(width, height);
    }

    public void display() {

        setVisible(true);
        //pack();
    }

    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// This is a input/output panel that houses the input/output boxes
class InputOutputPanel extends JPanel {
    //create input/output components
    private JLabel fileLbl = new JLabel("Input File Name", JLabel.CENTER);

    private JLabel classLbl = new JLabel("Class to Recompile", JLabel.CENTER);

    private JTextField fileTxt = new JTextField("");
    private JTextField classTxt = new JTextField("");
    private JTextArea resultArea = new JTextArea();


    public InputOutputPanel(DFSPanel dPanel) {
        setLayout(new GridLayout(3, 3));
        resultArea.setBackground(Color.lightGray);
        resultArea.setEditable(false);
        add(fileLbl);
        add(classLbl);

        add(fileTxt);
        add(classTxt);
        add(resultArea);
    }

    String getFileNameTxt() {
        return fileTxt.getText();
    }

    String getClassName() {

        return classTxt.getText();
    }

    void setResultTxt(String result) {
        resultArea.setText(result);
    }


}

// Panel for the buttons to attach to, then we attach to the input/output panel
class ButtonsPanel extends JPanel {

    private DFSPanel dPanel;
    private JButton buildGraphBtn = new JButton("Build Directional Graph");
    private JButton topOrderBtn = new JButton("Topological Order");
    private DirectedGraph graph;
    String filename;

    public ButtonsPanel (DFSPanel dPanel) {
        this.dPanel = dPanel;
        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(buildGraphBtn);
        add(topOrderBtn);

        buildGraphBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnGraphAction();
            }
        });

        topOrderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnTopOrderAction();
            }
        });

    }

    // This is the class that will open the file and build the graph
    public void btnGraphAction() {

        filename = dPanel.ioPanel.getFileNameTxt();
        graph = new DirectedGraph();
        graph.initGraph(filename);
    }

    // This is the class that sets the results in the text area
    public void btnTopOrderAction() {

        String className = dPanel.ioPanel.getClassName();
        //dPanel.ioPanel.setResultTxt(className);
        graph.setVertexToGet(className);
        graph.sortTopOrder();
        dPanel.ioPanel.setResultTxt(graph.getSortedResult());
    }
}

/* This is the panel that goes into the frame to attach the GUI components */
class DFSPanel extends JPanel {
    public InputOutputPanel ioPanel = new InputOutputPanel(this);
    public ButtonsPanel btnPanel = new ButtonsPanel(this);
    public DFSPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        add(ioPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

}

public class MainGui extends NiceFrame {

    public MainGui() {
        super("Class Dependency Graph", 450, 400);
        add(new DFSPanel());
    }

    static public void main(String[] args) {
        MainGui mgui = new MainGui();
        mgui.display();
    }
}