package Common;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Common.PopUpDemo;

public class RightClick extends MouseAdapter{
	public static PopUpDemo menu;
	public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        menu = new PopUpDemo();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

}
