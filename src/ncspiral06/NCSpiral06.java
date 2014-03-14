/**
 * @version 6
 * Main class
 * @author Jaska Börner
 * 28 Jan 2014
 * Last updated: 14 March 2014
 */

package ncspiral06;

/**
 *
 * @author jaska
 */
public class NCSpiral06 {
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Face().setVisible(true);
            }
        });
    }
    
}
