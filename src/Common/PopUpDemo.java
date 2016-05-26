package Common;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpDemo extends JPopupMenu{
    public static JMenuItem performAnalysis;
    public static JMenuItem view;
    public PopUpDemo(){
        performAnalysis = new JMenuItem("Perform Analysis");
        add(performAnalysis);
        view = new JMenuItem("View");
        add(view);
    }
}
