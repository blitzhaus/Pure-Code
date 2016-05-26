package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;


public class NcaSetupDispCompGui {


	public NcaSetupDispCompGui(String dummy){
		
	}
	
	public static NcaSetupDispCompGui SETUP_DISP_COMP_GUI = null;
	public static NcaSetupDispCompGui createNcaMainScrSetupDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(SETUP_DISP_COMP_GUI == null){
			SETUP_DISP_COMP_GUI = new NcaSetupDispCompGui("just object creation");
		}
		return SETUP_DISP_COMP_GUI;
	}
	
	
	
	NcaMappingDispGui ncaMapDispGui;
	NcaSlopeDispGui ncaSlopeDispGui;
	NcaLambdaZDispGui ncaLambdaDispGui;
	NcaParameterNamesDispGui ncaParameterNamesDispGui;
	NcaParameterUnitsDispGui ncaParameterUnitsDispGui;
	NcaSubAreasDispGui ncaSubAreasDispGui;
	NcaTherapeuticDispGui ncaTherapeuticDispGui;
	NcaDosingDispGui ncaDosingDispGui;
	
	
	
	public void NcaSetupDispCompGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
	
		// This internal frame will contain many internal frames corresponding
		// to each of the components available
		ncaMapDispGui = NcaMappingDispGui.createMappingInstance();
		ncaMapDispGui.NcaMappingDispGuiCreation();
		ncaSlopeDispGui = NcaSlopeDispGui.createNcaSlopeDispInstance();
		ncaSlopeDispGui.NcaSlopeDispGuiCreation();
		ncaLambdaDispGui = NcaLambdaZDispGui.createLambDispGuiInstance();
		ncaLambdaDispGui.NcaLambdaZDispGuiCreation();
		ncaSubAreasDispGui = NcaSubAreasDispGui.createNcaSubAreasDispGuiInst();
		ncaSubAreasDispGui.NcaSubAreasDispGuiCreation();
		ncaTherapeuticDispGui = NcaTherapeuticDispGui.createNcaTherapeuticGuiInstance();
		ncaTherapeuticDispGui.NcaTherapeuticDispGuiCreation();
		ncaParameterNamesDispGui = NcaParameterNamesDispGui.createParameterNamesInstance();
		ncaParameterNamesDispGui.NcaParameterNamesDispGuiCreation();
		ncaDosingDispGui = NcaDosingDispGui.createNcaDosingGuiInst();
		ncaDosingDispGui.NcaDosingDispGuiCreation();
		ncaParameterUnitsDispGui = NcaParameterUnitsDispGui.createNcaParUnitsDisInst();
		ncaParameterUnitsDispGui.NcaParameterUnitsDispGuiCreation();
		
	}







}
