/**
 * @version 6
 * Main form
 * @author Jaska Börner
 * 28 Jan 2014
 * Last updated: 14 March 2014
 */
package ncspiral06;

//import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;

public class Face extends JFrame {
    
    private JButton goButton, bgColBut, dotColBut, sshotBut;
    private JTextField SizeInput;
    private JSlider SizeSlider;
    private JLabel l1, l2;
    private JFileChooser chooser;
    private FileSystemView fsv;
    private File dir;
    private Pane pane;
    //int m_height, m_width;
    
    public Face() {
        //m_width = getSize().width;
        //m_height = getSize().height;
        //System.out.println("Width: " + m_width + " Height: " + m_height);
        this.setSize(450, 200);
        this.add(makeControlPanel());
        this.setName("NCSpiral 0.6");
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Face.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
    @Override
    public void init() {
        m_width = getSize().width;
        m_height = getSize().height;
        this.add(makeControlPanel());
        this.setName("NCSpiral 0.6");
        System.out.println("Width: " + m_width + " Height: " + m_height);
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Face.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    private JPanel makeControlPanel() {
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        JPanel choosePanel = new JPanel();
        choosePanel.setLayout(new GridLayout(2,2));
        JPanel paramPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        goButton = new JButton("Generate!");
        goButton.setVerticalTextPosition(AbstractButton.CENTER);
        goButton.setHorizontalTextPosition(AbstractButton.LEADING);
        goButton.setActionCommand("go");
        goButton.addActionListener(new listener());
        goButton.setToolTipText("Generate a prime spiral!");  
        sshotBut = new JButton("Save Spiral");
        sshotBut.setVerticalTextPosition(AbstractButton.CENTER);
        sshotBut.setHorizontalTextPosition(AbstractButton.LEADING);
        sshotBut.addActionListener(new captureListener());
        sshotBut.setToolTipText("Save a screenshot of the prime spiral.");
        
        l1 = new JLabel("Background Colour");
        l2 = new JLabel("Dot Colour");
        
        bgColBut = new JButton("Select...");
        bgColBut.setBackground(Color.BLACK);
        bgColBut.addActionListener(new bgColorListener());
        dotColBut = new JButton("Select...");
        dotColBut.setBackground(Color.WHITE);
        dotColBut.addActionListener(new dotColorListener());
        
        JLabel SizeLabel = new JLabel("Spiral Size:");
        SizeInput = new JTextField(5);
        SizeInput.addActionListener(new textActionListener());
        SizeSlider = new JSlider(150,1000,150);
        SizeSlider.setMajorTickSpacing(50);
        SizeSlider.setMinorTickSpacing(10);
        SizeSlider.setPaintTicks(true);
        SizeSlider.addChangeListener(new sliderListener());
        SizeInput.setText(Integer.toString(SizeSlider.getValue()));

        choosePanel.add(l1);
        choosePanel.add(Box.createHorizontalStrut(1));
        choosePanel.add(bgColBut);
        choosePanel.add(l2);
        choosePanel.add(Box.createHorizontalStrut(1));
        choosePanel.add(dotColBut);
        controlPanel.add(Box.createVerticalStrut(10));
        paramPanel.add(SizeLabel);
        paramPanel.add(SizeInput);
        paramPanel.add(SizeSlider);
        buttonPanel.add(sshotBut);
        buttonPanel.add(goButton);
        controlPanel.add(choosePanel, BorderLayout.NORTH);
        controlPanel.add(paramPanel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
        return controlPanel;
    }

    private class listener implements ActionListener {
        @Override
    public void actionPerformed(ActionEvent e) {
        makeTheDamnSpiralForCryingOutLoudWillYa();
        }
    }
    
    private class captureListener implements ActionListener {
        @Override
    public void actionPerformed(ActionEvent e) {
        captureSpiral(pane, makeSaveName());
        }
    }
    
    private class bgColorListener implements ActionListener {
        @Override
    public void actionPerformed(ActionEvent e) {
        Color newbg = getNewBackgroundColor();
        l1.setForeground(newbg);
        bgColBut.setText("Change...");
        bgColBut.setForeground(newbg);
        bgColBut.setBackground(newbg);
        bgColBut.repaint();
        } 
    }   
    
    private class dotColorListener implements ActionListener {
        @Override
    public void actionPerformed(ActionEvent e) {
        Color newdc = getNewDotColor();
        l2.setForeground(newdc);
        dotColBut.setText("Change...");
        dotColBut.setForeground(newdc);
        dotColBut.setBackground(newdc);
        bgColBut.repaint();
        }
    }    
    
    private class sliderListener implements ChangeListener { 
        @Override
    public void stateChanged(ChangeEvent ce) {
        int ok = SizeSlider.getValue();
        if (ok%2 != 0) {
            ok = ok + 1;
        }        
        SizeInput.setText(Integer.toString(ok));
    }
    }  
    
    private class textActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {    
        int ok = Integer.parseInt(SizeInput.getText());
        if (ok%2 != 0) {
            ok = ok + 1;
        }
        if (ok > 150 && ok < 1000) {
          SizeSlider.setValue(ok);
        }
        else {
            JOptionPane.showMessageDialog(null,"Must be between 150 and 1000...");
        }
     }
    }
    
    public Color getNewDotColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose a new dot color...", Color.WHITE);
        return newColor;
    }
    
    public Color getNewBackgroundColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose a new background color...", Color.BLACK);
        return newColor;
    }    
    
    public void makeTheDamnSpiralForCryingOutLoudWillYa() {
        int size = SizeSlider.getValue();
        if (size%2 != 0) {
            size = size + 1;
        }
        Dimension dim = new Dimension(size, size);
        pane = new Pane(dim, bgColBut.getBackground(), dotColBut.getBackground());
        pane.setLocationRelativeTo(null);
        pane.pack();
        pane.setVisible(true); 
    }
    
    public void captureSpiral(JFrame argFrame, String fileName) {
        Rectangle rec = argFrame.getBounds();
        BufferedImage capture = new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_ARGB);
        argFrame.paint(capture.getGraphics());
        try {
            ImageIO.write(capture, "png", new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(Face.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String makeSaveName() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy h.mm.ss");
        String formattedDate = sdf.format(date);
        chooser = new JFileChooser();
        fsv = chooser.getFileSystemView();
        dir = new File(fsv.getHomeDirectory() + "/NCSpiral Images");
        if (dir.exists() == false) {
            dir.mkdir();
        }
        System.out.println("Directory for saving screenshots is " + dir);
        int dim = SizeSlider.getValue();
        String filename = dim + "x" + dim + " " + formattedDate + ".png";
        String fullfilename = dir + "/" + filename;
        System.out.println("Generated filename is " + filename);
        System.out.println("Generated full filename is " + fullfilename);
        return fullfilename;
    }
}
