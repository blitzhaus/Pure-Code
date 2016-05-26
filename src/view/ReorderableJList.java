

package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import Common.EventCodes;

import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("serial")
public class ReorderableJList extends JFrame implements ActionListener, EventCodes {
	
	/*	public static ReorderableJList CopyPasteFindReplace = null;
		public static ReorderableJList createCopyPasteFindReplaceInstance(){
			if(CopyPasteFindReplace == null){
				CopyPasteFindReplace = new ReorderableJList(jTable1 );
			}
			return CopyPasteFindReplace;
		}*/
		
		
		private String rowstring, value;
		private Clipboard system;
		private StringSelection stsel;
		private JTable jTable1;
		int rowNumber;
		

		public ReorderableJList(JTable myJTable) {
			jTable1 = myJTable;
			jTable1.setCellSelectionEnabled(true);
			KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C,
					ActionEvent.CTRL_MASK, false);

			KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V,
					ActionEvent.CTRL_MASK, false);

			KeyStroke Find = KeyStroke.getKeyStroke(KeyEvent.VK_F,
					ActionEvent.CTRL_MASK, false);
			KeyStroke selectALL = KeyStroke.getKeyStroke(KeyEvent.VK_A,
					ActionEvent.CTRL_MASK, false);
			KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
					0, false);
			KeyStroke Cut = KeyStroke.getKeyStroke(KeyEvent.VK_X,
					ActionEvent.CTRL_MASK, false);
			
			jTable1.registerKeyboardAction(this, "Copy", copy,
					JComponent.WHEN_FOCUSED);
			jTable1.registerKeyboardAction(this, "Paste", paste,
					JComponent.WHEN_FOCUSED);
			jTable1.registerKeyboardAction(this, "Find", Find,
					JComponent.WHEN_FOCUSED);
			jTable1.registerKeyboardAction(this, "Cut", Cut,
					JComponent.WHEN_FOCUSED);
			jTable1.registerKeyboardAction(this, "SelectAll", selectALL,
					JComponent.WHEN_FOCUSED);
			jTable1.registerKeyboardAction(this, "Delete", delete,
					JComponent.WHEN_FOCUSED);
			
			system = Toolkit.getDefaultToolkit().getSystemClipboard();
			
			

			class PopUpForRightClick extends JPopupMenu {
			    JMenuItem rightAlign;JMenuItem leftAlign;JMenuItem centerAlign;
			    JMenuItem copy; JMenuItem paste; JMenuItem delete;
			    JMenuItem fillDown;
			    public PopUpForRightClick(){
			    	rightAlign = new JMenuItem("Column Right Align");
			    	leftAlign = new JMenuItem("Column Left Align");
			    	centerAlign = new JMenuItem("Column Center Align");
			    	copy = new JMenuItem("Copy");
			    	paste =  new JMenuItem("Paste");
			    	delete = new JMenuItem("Delete");
			    	fillDown = new JMenuItem("Fill Down");
			    	
			    	copy.addActionListener(DDViewLayer.createViewLayerInstance());
			    	copy.setActionCommand(COPY);
			    	copy.setEnabled(false);
			    	
			    	paste.addActionListener(DDViewLayer.createViewLayerInstance());
			    	paste.setActionCommand(PASTE);
			    	paste.setEnabled(false);
			    	
			    	
			    	delete.addActionListener(DDViewLayer.createViewLayerInstance());
			    	delete.setActionCommand(DELETE);
			    	delete.setEnabled(false);
			    	
			    	
			    	fillDown.addActionListener(DDViewLayer.createViewLayerInstance());
			    	fillDown.setActionCommand(FILLDOWN);
			    	fillDown.setEnabled(false);
			    	
			    	if(jTable1.isFocusOwner() == true){
			    		copy.setEnabled(true);
			    		paste.setEnabled(true);
			    		delete.setEnabled(true);
			    		fillDown.setEnabled(true);
			    	}
			    	add(copy);
			    	add(paste);
			    	add(delete);
			    	add(fillDown);
			    	
			    	
			    	leftAlign.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							System.out.println("row on clicked "+rowNumber);
							
							alignColumnLeft( rowNumber);
							jTable1.repaint(); 
							jTable1.validate(); 
						}
					});
			    	rightAlign.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							alignColumnRight( rowNumber);
							jTable1.repaint(); 
							jTable1.validate(); 
						}
					});
			    	centerAlign.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							alignColumnCenter( rowNumber);
							jTable1.repaint(); 
							jTable1.validate(); 
						}
					});
			    	
			    	
			        add(rightAlign);
			        add(leftAlign);add(centerAlign);
			    }
			}
		
		
			class PopClickListener extends MouseAdapter {
			    public void mousePressed(MouseEvent e){
			        if (e.isPopupTrigger())
			            doPop(e);
			    }

			    public void mouseReleased(MouseEvent e){
			        if (e.isPopupTrigger())
			            doPop(e);
			    }
			/*	public void mouseClicked( MouseEvent e )
				{
					// Left mouse click
					if ( SwingUtilities.isLeftMouseButton( e ) )
					{
						System.out.println("ban gayi gal");
					}
					// Right mouse click
					else if ( SwingUtilities.isRightMouseButton( e )){
						
						doPop(e);
						
					}}*/

			    private void doPop(MouseEvent e){
			    	PopUpForRightClick menu = new PopUpForRightClick();
			    	Point p = e.getPoint();
			    	 rowNumber = jTable1.columnAtPoint( p );
					 
			        menu.show(e.getComponent(), e.getX(), e.getY());
			    }
			}
		
		
		
			jTable1.addMouseListener(new PopClickListener());
		
			
			
		}
		//////////////////////
		
		
		/*{final UndoManager undo = new UndoManager();
		TableModel	MODEL = jTable1.getModel();

		// Listen for undo and redo events
		(MODEL))MODEL.addUndoableEditListener(new UndoableEditListener() {
		    public void undoableEditHappened(UndoableEditEvent evt) {
		        undo.addEdit(evt.getEdit());
		    }
		});

		// Create an undo action and add it to the text component
		jTable1.getActionMap().put("Undo",
		    new AbstractAction("Undo") {
		        public void actionPerformed(ActionEvent evt) {
		            try {
		                if (undo.canUndo()) {
		                    undo.undo();
		                }
		            } catch (CannotUndoException e) {
		            }
		        }
		   });

		// Bind the undo action to ctl-Z
		jTable1.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

		// Create a redo action and add it to the text component
		jTable1.getActionMap().put("Redo",
		    new AbstractAction("Redo") {
		        public void actionPerformed(ActionEvent evt) {
		        	 try {
		                if (undo.canRedo()) {
		                    undo.redo();
		               
		            }  } catch (CannotUndoException e) {
		            
		        }}
		    });
		jTable1.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");}*/
		// Bind the redo action to ctl-Y
		
/////////////////////////////////////////////////////////////////////////////////////
		public  JTable getJTable() {
			return jTable1;
		}

		public void setJTable(JTable jTable1) {
			this.jTable1 = jTable1;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().compareTo("Copy") == 0) {
				copyingValue();
			}
			if (e.getActionCommand().compareTo("Paste") == 0) {
				pastValue();
			}
			if (e.getActionCommand().compareTo("Delete") == 0) {
				removeValue();
			}
			if (e.getActionCommand().compareTo("SelectAll") == 0) {
				SelectAllValues();
			}
			if (e.getActionCommand().compareTo("Cut") == 0) {
				cutValues();
			}
			if (e.getActionCommand().compareTo("Find") == 0) {
				Container container = getContentPane();
				container.removeAll();
				container.setLayout(new FlowLayout());
				final JTextField jtfInput;
				final JTextField jtfInput2;
				JButton Find = new JButton("Search in table");
				JButton Replace = new JButton("Replace With");
				jtfInput = new JTextField(10);
				jtfInput.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void changedUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				jtfInput2 = new JTextField(10);
				container.add(jtfInput);
				container.add(Find);
				container.add(jtfInput2);
				container.add(Replace);
				setSize(325, 100);
				setVisible(true);
				

				Find.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String target = jtfInput.getText();
					
						Search(target);
					}

					public void Search(String tar) {

						int numRows = jTable1.getRowCount();
						int numCols = jTable1.getColumnCount();
						
						for (int i = 0; i < numRows; i++) {
							
							for (int j = 0; j < numCols; j++) {
								String data = "" + jTable1.getValueAt(i, j);

								if (data.contains(tar)) {
									System.out.println("matched" + tar);
									int index = data.indexOf(tar);
									System.out.println("value palced at " + index);
									//showSearchResults(i, j);  
									//jTable1.getCellRect(i, j, true).reshape(., arg1, arg2, arg3);
				                    return;  
								}

							}

						}

					}

					

				});
				Replace.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String target = jtfInput.getText();
						System.out.println("done");
						String replace = jtfInput2.getText();
						Search(target, replace);

					}

					public void Search(String tar, String repl) {

						int numRows = jTable1.getRowCount();
						int numCols = jTable1.getColumnCount();

						for (int i = 0; i < numRows; i++) {
							
							for (int j = 0; j < numCols; j++) {
								String data = "" + jTable1.getValueAt(i, j);

								if (data.contains(tar)) {
									
									data.replace(repl, tar);
									
									jTable1.setValueAt(repl,i,j);
									
								}

							}

						}

					}

				});

			}

		}
		private void cutValues() {
		copyingValue();
		removeValue();
			
		}

		public void SelectAllValues() {
			jTable1.selectAll();
			
		}
		public void removeValue(){
			int numrows = jTable1.getSelectedRowCount();
			int numcols = jTable1.getSelectedColumnCount();
			
			int[] rowsselected = jTable1.getSelectedRows();
			int[] colsselected = jTable1.getSelectedColumns();
			for(int i=0;i<rowsselected.length;i++){
				System.out.println("selected rows = "+(rowsselected[i]+1));
			}
			if (!((numrows - 1 == rowsselected[rowsselected.length - 1]
					- rowsselected[0] && numrows == rowsselected.length) && (numcols - 1 == colsselected[colsselected.length - 1]
					- colsselected[0] && numcols == colsselected.length))) {
				JOptionPane.showMessageDialog(null, "Invalid Copy Selection",
						"Invalid Copy Selection", JOptionPane.ERROR_MESSAGE);
				return;
			}
	int rowdeleted =0;
	int coldeleted =0;
	String str="";
			for (int i = 0; i < numrows; i++) {
			for (int j = 0; j < numcols; j++) {
				//String data = "" + jTable1.getValueAt(j, i);

				rowdeleted=rowsselected[i];
				coldeleted=colsselected[j];
			jTable1.setValueAt(str,rowdeleted,coldeleted);
					
					
					
					
					System.out.println("deleted at row value "+rowdeleted);
					System.out.println("deleted at colvalue "+coldeleted);}	

			
			}
		
			
			
				//jTable1.removeRowSelectionInterval((rowsselected[0]+1),(rowsselected[rowsselected.length]+1) );
				//}}
		}
		public void pastValue(){

			System.out.println("Trying to Paste");
			int startRow = (jTable1.getSelectedRows())[0];
			int startCol = (jTable1.getSelectedColumns())[0];
		
			try {
				String trstring = (String) (system.getContents(this)
						.getTransferData(DataFlavor.stringFlavor));
				System.out.println("String is:" + trstring);
				StringTokenizer st1 = new StringTokenizer(trstring, "\n");
				for (int i = 0; st1.hasMoreTokens(); i++) {
					rowstring = st1.nextToken();
					StringTokenizer st2 = new StringTokenizer(rowstring, "\t");
					System.out.println("rows to paste = "+(startRow+i+1));
					if(startRow+i+1 < jTable1.getRowCount())
						jTable1.setRowSelectionInterval(startRow+i+1, startRow+i+1);
					for (int j = 0; st2.hasMoreTokens(); j++) {
						value = (String) st2.nextToken();
						if (startRow + i < jTable1.getRowCount()
								&& startCol + j < jTable1.getColumnCount())
							jTable1.setValueAt(value, startRow + i, startCol
									+ j);
						
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
		}
		
		public void copyingValue(){
			StringBuffer sbf = new StringBuffer();

			int numcols = jTable1.getSelectedColumnCount();
			int numrows = jTable1.getSelectedRowCount();
			int[] rowsselected = jTable1.getSelectedRows();
			int[] colsselected = jTable1.getSelectedColumns();
			for(int i=0;i<rowsselected.length;i++){
				System.out.println("copied rows = "+(rowsselected[i]+1));
			}
			if (!((numrows - 1 == rowsselected[rowsselected.length - 1]
					- rowsselected[0] && numrows == rowsselected.length) && (numcols - 1 == colsselected[colsselected.length - 1]
					- colsselected[0] && numcols == colsselected.length))) {
				JOptionPane.showMessageDialog(null, "Invalid Copy Selection",
						"Invalid Copy Selection", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (int i = 0; i < numrows; i++) {
				for (int j = 0; j < numcols; j++) {
					sbf.append(jTable1.getValueAt(rowsselected[i],
							colsselected[j]));
					if (j < numcols - 1)
						sbf.append("\t");
				}
				sbf.append("\n");
			}
			stsel = new StringSelection(sbf.toString());
			system = Toolkit.getDefaultToolkit().getSystemClipboard();
			system.setContents(stsel, stsel);
		
			
		}

	public void fillDown() {
		StringBuffer sbf = new StringBuffer();

		int numcols = jTable1.getSelectedColumnCount();
		int numrows = jTable1.getSelectedRowCount();
		int[] rowsselected = jTable1.getSelectedRows();
		for (int i = 0; i < rowsselected.length; i++) {
			System.out.println("selected row =  " + (rowsselected[i] + 1));
		}
		;
		int[] colsselected = jTable1.getSelectedColumns();
		if (!((numrows - 1 == rowsselected[rowsselected.length - 1]
				- rowsselected[0] && numrows == rowsselected.length) && (numcols - 1 == colsselected[colsselected.length - 1]
				- colsselected[0] && numcols == colsselected.length))) {
			JOptionPane.showMessageDialog(null, "Invalid Copy Selection",
					"Invalid Copy Selection", JOptionPane.ERROR_MESSAGE);
			return;
		}

		sbf.append(jTable1.getValueAt(rowsselected[0], colsselected[0]));

		stsel = new StringSelection(sbf.toString());
		system = Toolkit.getDefaultToolkit().getSystemClipboard();
		system.setContents(stsel, stsel);
		try {
			String trstring = (String) (system.getContents(this)
					.getTransferData(DataFlavor.stringFlavor));

			for (int i = 0; i < numrows; i++) {
				for (int j = 0; j < numcols; j++) {
					jTable1.setValueAt(trstring, rowsselected[i],
							colsselected[j]);

				}
			}

		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
private void alignColumnCenter(int columnNumber) {
		
		
		
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	    r.setHorizontalAlignment(JLabel.CENTER);
		jTable1.getColumnModel().getColumn(columnNumber).setCellRenderer(r);		
	
}

private void alignColumnLeft(int columnNumber) {
	
	
	
	
	DefaultTableCellRenderer r = new DefaultTableCellRenderer();
    r.setHorizontalAlignment(JLabel.LEFT);
	jTable1.getColumnModel().getColumn(columnNumber).setCellRenderer(r);		

}private void alignColumnRight(int columnNumber) {




DefaultTableCellRenderer r = new DefaultTableCellRenderer();
r.setHorizontalAlignment(JLabel.RIGHT);
jTable1.getColumnModel().getColumn(columnNumber).setCellRenderer(r);		

}
	
}
 
