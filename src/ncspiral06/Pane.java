
package ncspiral06;
import java.awt.*;
import javax.swing.*;

public class Pane extends JFrame {
    private JPanel drawingPanel;

  public Pane(Dimension Dimension, Color bg, Color dot) {
      super("Ulam's Spiral");

    drawingPanel = new FieldPanel(dot);
    drawingPanel.setPreferredSize(new Dimension(Dimension));
    drawingPanel.setBackground(bg);
    add(drawingPanel);
                 }

} //Pane
    