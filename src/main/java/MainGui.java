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
        pack();
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
    private JLabel exprLbl = new JLabel("Input File Name", JLabel.CENTER);

    private JLabel resultLbl = new JLabel("Class to Recompile", JLabel.CENTER);

    private JTextField exprTxt = new JTextField("");
    private JTextField resultTxt = new JTextField("");
    private JTextArea resultArea = new JTextArea();


    public InputOutputPanel(PostfixTreePanel pPanel) {
        setLayout(new GridLayout(2, 2));
        resultTxt.setBackground(Color.lightGray);
        resultTxt.setEditable(false);
        add(exprLbl);
        add(exprTxt);

        add(resultLbl);
        add(resultTxt);
        add(resultArea);
    }

    String getExprTxt() {
        return exprTxt.getText();
    }

    void setResultTxt(String result) {
        resultTxt.setText(result);
    }


}

// Panel for the buttons to attach to, then we attach to the input/output panel
class ButtonsPanel extends JPanel {

    private PostfixTreePanel pPanel;

    private JButton buildGraphBtn = new JButton("Build Directional Graph");
    private JButton topOrderBtn = new JButton("Topological Order");


    // Add to button groups





    private ExpressionTree cTree;
    private Node inOrder;

    public ButtonsPanel (PostfixTreePanel pPanel) {
        this.pPanel = pPanel;
        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(expBtn);

        expBtn.addActionListener(new ActionListener(){

                                     public void actionPerformed(ActionEvent e){

                                         cTree = new ExpressionTree();
                                         btnBinaryOprAction();
                                     }
                                 }

        );

    }

    // Action to call the class to tokenize and parse
    // Input is a string, output will also be a string
    public void btnBinaryOprAction() {
        String expr = pPanel.ioPanel.getExprTxt();  // Get the input text/forula
        // Sets the result txt to the output of our expression evaluator

        // Calls to create the actual tree
        // Stores the final node
        inOrder = cTree.createTree(expr);

        pPanel.ioPanel.setResultTxt(inOrder.inOrderWalk());
    }
}

/* This is the panel that goes into the frame to attach the GUI components */
class PostfixTreePanel extends JPanel {
    public InputOutputPanel ioPanel = new InputOutputPanel(this);
    public ButtonsPanel btnPanel = new ButtonsPanel(this);
    public PostfixTreePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        add(ioPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }


}

public class MainGui extends NiceFrame {

    public PostfixTreeGui() {
        super("Class Dependency Graph", 450, 400);
        add(new PostfixTreePanel());
    }

    static public void main(String[] args) {
        PostfixTreeGui pTree = new PostfixTreeGui();
        pTree.display();
    }

}
