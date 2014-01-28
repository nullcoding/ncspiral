
package ncspiral05;

import java.applet.*;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

/**
 *
 * @author Jaska
 */
public class NCSpiral05 extends Applet {
    
    int m_height, m_width;
   /* 
    public NCSpiral05() {
        Face face = new Face();
        this.add(face);
    }
    
    public void main (String[] args) {
        init();
    } */

    @Override
    public void init() {
        
        m_width = getSize().width;
        m_height = getSize().height;
        
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println("fuck, didn't complete successfully. you suck at programming.");
        }
    }
}
