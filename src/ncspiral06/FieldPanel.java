package ncspiral06;

import java.awt.*;
import javax.swing.*;

/**
 * @version 6
 * @author NullCoding
 * Original version 15 August 2012
 * Last Updated: 14 March 2014
 * This class is responsible for the spiral generation logic and rendering. 
 * Do NOT modify this file in any way unless you are POSITIVE it will not
 * interfere with creation of the spiral!!
 */

public class FieldPanel extends JPanel {
    private Color DotColor;
    
    public FieldPanel(Color DotColor) {
        this.DotColor = DotColor;
    }
    
    @Override
        public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        Graphics g2 = g;
        
        Dimension size = getSize();
        Insets insets = getInsets();

        /**
         * w and h = bounded area (actual drawable area for spiral)
         */
        int w =  size.width - insets.left - insets.right;
        int h =  size.height - insets.top - insets.bottom;

        int n = 1;
        int trial; // forward declaration
        
        /**
         * x and y = current (x,y) position of the spiral
         */
        
        int x = (w/2)-1; 
        int y = (h/2)-1;
        
        /**
         * This creates an array - the array itself actually holds spiral 
         * logic for the purposes of drawing points on the actual panel.
         * The array is filled first; the drawing comes afterwards.
         */
        
        int SpiralArray[][] = new int[size.width][size.height]; 
        SpiralArray[y][x] = n;
  
        g2.setColor(DotColor);
        
        
        int length = 3; // initial spiral leg length
        int pos; // forward declarations

/**
 * length of each 'arm' of the spiral must be less than the user-specified 
 * maximum window size (it's a square, so one dimension = width and height)
*/
while (length <= w) {

int GoUp = length - 2;
int GoLeft = length - 1;
int GoDown = length -1;
int GoRight = length -1;

x++;
n++;
SpiralArray[y][x] = n;

for (pos=0; pos < GoUp; pos++) { // going up
    y--;
    n++;
    SpiralArray[y][x] = n; 
}

for (pos=0; pos < GoLeft; pos++) { // going left
    x--;
    n++;
    SpiralArray[y][x] = n;
}

for (pos=0; pos < GoDown; pos++) { // going down
    y++;
    n++;
    SpiralArray[y][x] = n; 
}

for (pos=0; pos < GoRight; pos++) { // going right
    x++;
    n++;
    SpiralArray[y][x] = n;
}

length+=2; // increment overall length of 'leg' - this affects the NEXT LEG!

}

    for (y = 1; y < h; y++) {
        for (x = 2; x < w; x++) {
            for (trial = 2; trial < n+1; trial++) {	
                /**
                 * if it is ONE, DRAWS A POINT
                 * This happens only once, but is necessary to include
                 * because otherwise, this won't work properly
                 * as one (1) is both prime and composite
                 * for the purposes of Java programming, generally speaking.
                 * Don't think too hard about this, just accept it...
                 */
		if (SpiralArray[y][x] == 1) {
                    g2.drawLine(x,y,x,y);
		}
                /**
                 * if it is COMPOSITE and NOT the current 'trial'
                 * DRAWS A POINT
                 */
		if (SpiralArray[y][x]%trial == 0 && SpiralArray[y][x] != trial) {
                    break;
		}
                /**
                 *  if it is actually the current trial number 
                 * (meaning it seems 'composite' but that's because 
                 * you divided it by itself)
                 * DRAWS A POINT
                 */
		else if (SpiralArray[y][x]%trial == 0 && SpiralArray[y][x] == trial) {
                    g2.drawLine(x,y,x,y);
                    break;
		}
            }
        }
    }    
}
}