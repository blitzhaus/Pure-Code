package view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FindReplaceHandler extends JFrame{

	public static FindReplaceHandler FIND_REPLACE = null;

	public static FindReplaceHandler createFindReplaceHandInst(){
		if(FIND_REPLACE == null){
			FIND_REPLACE = new FindReplaceHandler("just object creation");
		}
		return FIND_REPLACE;
	}
	
	public FindReplaceHandler(String string) {
		new JFrame("Find And Replace");
		setLocationRelativeTo(null);
		setSize(400, 300);
		setVisible(true);
		createUI();
	}
	
	
	private void createUI() {
		
		JTextField find = new JTextField();
		find.setBorder(BorderFactory.createTitledBorder("Find"));
		setLayout(new FlowLayout());
		
		
	}

	public void handler(){
		
		this.setVisible(true);
		
	}
}
