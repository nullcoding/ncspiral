/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ncspiral06;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Jaska
 */
public class ColorChooserThing extends JFrame {
    
    private JColorChooser bgColorChooser, dotColorChooser;
    
    public ColorChooserThing() {
    
            
        bgColorChooser = new JColorChooser(Color.BLACK);
        bgColorChooser.setToolTipText("Select a background colour.");
        bgColorChooser.setBorder(BorderFactory.createTitledBorder(
                                             "Choose Background Colour"));
        
        dotColorChooser = new JColorChooser(Color.WHITE);   
        dotColorChooser.setToolTipText("Select a foreground colour.");
        dotColorChooser.setBorder(BorderFactory.createTitledBorder(
                                             "Choose Dot Colour"));
        

    
    }
    
}
