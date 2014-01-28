package ncspiral05;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author NullCoding
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

        int w =  size.width - insets.left - insets.right;
        int h =  size.height - insets.top - insets.bottom;

        int n = 1;
        int trial;// pos, length;
        
        int x = (w/2)-1;
        int y = (h/2)-1;
        
        int SpiralArray[][] = new int[size.width][size.height]; // creates array 
        SpiralArray[y][x] = n;
  
        g2.setColor(DotColor);
        
        
int length = 3;
int pos;

while (length <= w)
		{

int GoUp = length - 2;
int GoLeft = length - 1;
int GoDown = length -1;
int GoRight = length -1;

x++;
n++;
SpiralArray[y][x] = n;

for (pos=0; pos < GoUp; pos++) // going up
	{

y--;
n++;
SpiralArray[y][x] = n; 

	}

for (pos=0; pos < GoLeft; pos++) // going left
	{

x--;
n++;
SpiralArray[y][x] = n; 

	}

for (pos=0; pos < GoDown; pos++) // going down
	{

y++;
n++;
SpiralArray[y][x] = n; 

	}

for (pos=0; pos < GoRight; pos++) // going right
	{

x++;
n++;
SpiralArray[y][x] = n; 

	}

length+=2;

		}
        
        for (y = 1; y < h; y++)
{

    for (x = 2; x < w; x++)
	{
	for (trial = 2; trial < n+1; trial++)
        {
					
		if (SpiralArray[y][x] == 1) // if it is ONE
		{
                    g2.drawLine(x,y,x,y);
		}

		if (SpiralArray[y][x]%trial == 0 && SpiralArray[y][x] != trial) 
			{

		break;

			}

		else if (SpiralArray[y][x]%trial == 0 && SpiralArray[y][x] == trial) 
			{

                 g2.drawLine(x,y,x,y);
		break;

			}

		}
                                }
} 
       
    }
}