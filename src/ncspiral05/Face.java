/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ncspiral05;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Jaska
 */
public class Face extends Applet {
    
    private JButton goButton, bgColBut, dotColBut;
    private JTextField SizeInput;
    private JSlider SizeSlider;
    int m_height, m_width;
    
    @Override
    public void init() {
        m_width = getSize().width;
        m_height = getSize().height;
        this.add(makeControlPanel());
        this.setName("NCSpiral 0.55");
        System.out.println("Width: " + m_width + " Height: " + m_height);
    }
    
    private JPanel makeControlPanel() {
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        JPanel choosePanel = new JPanel();
        choosePanel.setLayout(new GridLayout(2,2));
        JPanel paramPanel = new JPanel();
       // paramPanel.setLayout(new GridLayout(1,3));
        goButton = new JButton("Generate!");
        goButton.setVerticalTextPosition(AbstractButton.CENTER);
        goButton.setHorizontalTextPosition(AbstractButton.LEADING);
        goButton.setActionCommand("go");
        goButton.addActionListener(new listener());
        goButton.setToolTipText("Generate a prime spiral!");    
        
        JLabel l1 = new JLabel("Background Colour");
        JLabel l2 = new JLabel("Dot Colour");
        
        bgColBut = new JButton();
        bgColBut.setBackground(Color.BLACK);
        bgColBut.addActionListener(new bgColorListener());
        dotColBut = new JButton();
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
        controlPanel.add(choosePanel, BorderLayout.NORTH);
        controlPanel.add(paramPanel, BorderLayout.CENTER);
        controlPanel.add(goButton, BorderLayout.AFTER_LAST_LINE);
        return controlPanel;
    }

    private class listener implements ActionListener {
        
        @Override
    public void actionPerformed(ActionEvent e) {
        makeTheDamnSpiralForCryingOutLoudWillYa();
    }
    
    }
    
    private class bgColorListener implements ActionListener {
        
        @Override
    public void actionPerformed(ActionEvent e) {
        bgColBut.setBackground(getNewBackgroundColor());
    }
    
    }   
    
    private class dotColorListener implements ActionListener {
        
        @Override
    public void actionPerformed(ActionEvent e) {
        dotColBut.setBackground(getNewDotColor());
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
        Pane pane = new Pane(dim, bgColBut.getBackground(), dotColBut.getBackground());
        pane.setLocationRelativeTo(null);
        pane.pack();
        pane.setVisible(true); 
    }
}
