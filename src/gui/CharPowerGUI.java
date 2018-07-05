package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import models.CharPower;
import models.PowerType;
import models.Character;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

/**
 * The GUI for add new character powers 
 *
 */

// TODO: Figure out how to edit and delete already added character powers


public class CharPowerGUI extends JDialog {

	//private JDialog this;
	private JTextField textfieldPowerName;
	private static CharPowerGUI instance = null;
	
	JLabel lblName;
	JComboBox comboBoxRank;
	JComboBox comboBoxPowerType;
	JTextArea textfieldPowerDesc;
	JLabel lblPowerDescription;
	JTextPane textfieldTalents;
	JTextPane textfieldTechniques;
	JLabel lblTalents;
	JLabel lblTechniques;
	JLabel lblPowerType;
	JLabel lblRank;
	JButton btnAddPower;
	
	CharPower newPower = new CharPower();
	ArrayList<CharPower> talentList = new ArrayList<CharPower>();
	ArrayList<CharPower> techniqueList = new ArrayList<CharPower>();
	
	/**
	 * Create the application.
	 */
	private CharPowerGUI() {
		initialize();
	}
	
	// create singleton object instance
	public static CharPowerGUI getInstance() {
		if(instance == null)
		{
			instance = new CharPowerGUI();
		}
		return instance;
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharPowerGUI window = new CharPowerGUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// closes the window
	 private static WindowListener closeWindow = new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	            e.getWindow().dispose();
	        }
	 };
	
	public void run() {
		this.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Edit Character Powers");
		this.setBounds(100, 100, 384, 445);
		addLabels();
		addInteractives();
		
		this.setModal(true);
	}
	
	
	// sets a name to the character object
	// similar methods for the other fields are shown below
	private void setNameToCharacter() {
		if(!textfieldPowerName.getText().isEmpty()) {
			newPower.setName(textfieldPowerName.getText());
		}
	}
	
	// TODO: Add validation for these two
	private void setRankToCharPower() {
		newPower.setRankByString(comboBoxRank.getSelectedItem().toString());
	}
	private void setTypeToCharPower() {
		newPower.setTypeByString(comboBoxPowerType.getSelectedItem().toString());
	}
	private void setDescToCharPower() {
		if (!textfieldPowerDesc.getText().isEmpty()) {
			newPower.setDescription(textfieldPowerDesc.getText());
		}
	}
	
	
	// sets the fields and their respective interactables depending on an inputted character
	// activates upon selecting the load option
	public void setInteractivesByCharacter(Character character) {
		ArrayList<CharPower> tempTalents = character.getTalents();
		ArrayList<CharPower> tempTechniques = character.getTechniques();
		
		// reset the two big text fields
		textfieldTalents.setText("");
		textfieldTechniques.setText("");
		
		
		StringBuilder sb = new StringBuilder();
		
		// iterate through the loading powers and set the combined strings to the respective text fields
		for(CharPower talent: tempTalents) {
			sb.append(talent.toString());
		}
		textfieldTalents.setText(sb.toString());
		sb.setLength(0); // reset the string builder
		
		for(CharPower technique: tempTechniques) {
			sb.append(technique.toString());
		}
		textfieldTechniques.setText(sb.toString());
		
		// set the two arraylists with the new lists
		talentList = tempTalents;
		techniqueList = tempTechniques;
		
	}
	
	// add interactive objects to the GUI
	public void addInteractives() {
		
		Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		textfieldPowerName = new JTextField();
		textfieldPowerName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				setNameToCharacter();
			}
		});
		textfieldPowerName.setColumns(10);
		
		comboBoxRank = new JComboBox();
		comboBoxRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRankToCharPower();
			}
		});
		comboBoxRank.setModel(new DefaultComboBoxModel(new String[] {"E", "D", "C", "B", "A", "S"}));
		comboBoxRank.setSelectedIndex(0);
		
		comboBoxPowerType = new JComboBox();
		comboBoxPowerType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTypeToCharPower();
			}
		});
		comboBoxPowerType.setModel(new DefaultComboBoxModel(new String[] {"Talent", "Technique"}));
		comboBoxPowerType.setSelectedIndex(0);
		
		textfieldPowerDesc = new JTextArea();
		textfieldPowerDesc.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textfieldPowerDesc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				setDescToCharPower();
				
			}
		});
		textfieldPowerDesc.setBorder(etchedBorder);
		JScrollPane powerDescScroll = new JScrollPane(textfieldPowerDesc);
		powerDescScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
		// TODO: Add scroll bars to these text panes
		// text panes for displaying the talents and techniques. They cannot be edited
		textfieldTalents = new JTextPane();
		textfieldTalents.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textfieldTalents.setEditable(false);
		textfieldTalents.setBorder(etchedBorder);

		JScrollPane talentTextScroll = new JScrollPane(textfieldTalents);
		talentTextScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		textfieldTechniques = new JTextPane();
		textfieldTechniques.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textfieldTechniques.setEditable(false);
		textfieldTechniques.setBorder(etchedBorder);
		
		JScrollPane techniqueTextScroll = new JScrollPane(textfieldTechniques);
		techniqueTextScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		btnAddPower = new JButton("Add Power");
		btnAddPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// set fields to the new CharPower
				setDescToCharPower();
				setTypeToCharPower();
				setRankToCharPower();
				setNameToCharacter();
				
				// validation
				if (newPower.getName().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Cannot add a power with an empty name!");
					return;
				}
				
				String existingText = "";
				PowerType currentType = newPower.getType();
				
				// add the new talent/technique to the text field and the according arraylist
				if (currentType == PowerType.TALENT) {
					existingText = textfieldTalents.getText();
					existingText += (newPower.toString() + "\n");
					textfieldTalents.setText(existingText);
					talentList.add(newPower);
				}
				else if (currentType == PowerType.TECHNIQUE) {
					existingText = textfieldTechniques.getText();
					existingText += (newPower.toString() + "\n");
					textfieldTechniques.setText(existingText);
					techniqueList.add(newPower);

				}
				
				// validation
				else {
					JOptionPane.showMessageDialog(null, "Please make sure you have actually selected a type of power!");
					System.out.println("ERROR! Please check type of the new power!");
				}
				
				// reset the variable
				newPower = new CharPower();
				
				// for debugging purposes
				System.out.println("Talents: ");
				for (CharPower t: talentList) {
					System.out.println(t.toString());
				}
				System.out.println("Techniques: ");
				for (CharPower t: techniqueList) {
					System.out.println(t.toString());
				}
			}
		});
		
		this.getContentPane().add(textfieldPowerName, "cell 0 5,growx,aligny top");
		this.getContentPane().add(comboBoxRank, "cell 4 5,growx,aligny top");
		this.getContentPane().add(comboBoxPowerType, "cell 2 5,growx,aligny top");
		this.getContentPane().add(powerDescScroll, "cell 0 7 5 1,grow");
		this.getContentPane().add(talentTextScroll, "cell 0 1 5 1,grow");
		this.getContentPane().add(techniqueTextScroll, "cell 0 3 5 1,grow");
		this.getContentPane().add(btnAddPower, "cell 0 8,growx,aligny center");
	}
	
	
	// initializes the labels
	public void addLabels() {
		lblTalents = new JLabel("Talents:");
		lblTalents.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblTechniques = new JLabel("Techniques:");
		lblTechniques.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblPowerType = new JLabel("Power Type:");
		lblPowerType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblPowerDescription = new JLabel("Description:");
		lblPowerDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblRank = new JLabel("Rank:");
		lblRank.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.getContentPane().setLayout(new MigLayout("", "[125px][42px][91px][17px][61px]", "[14px][86px][14px][86px][14px][20px][14px][75.00px][44.00px]"));
		
		this.getContentPane().add(lblTalents, "cell 0 0,alignx left,growy");
		this.getContentPane().add(lblTechniques, "cell 0 2,alignx left,growy");
		this.getContentPane().add(lblPowerType, "cell 2 4,grow");
		this.getContentPane().add(lblName, "cell 0 4,alignx left,growy");
		this.getContentPane().add(lblPowerDescription, "cell 0 6,alignx left,growy");
		this.getContentPane().add(lblRank, "cell 4 4,grow");
		
		
	}
	
	public void resetFields() {
		newPower = new CharPower();
		talentList.clear();
		techniqueList.clear();
		
		textfieldTalents.setText("");
		textfieldTechniques.setText("");
		textfieldPowerDesc.setText("");
		comboBoxRank.setSelectedIndex(0);
		comboBoxPowerType.setSelectedIndex(0);
		
	}
	
	public ArrayList<CharPower> getTalents(){
		return talentList;
	}
	
	public ArrayList<CharPower> getTechniques(){
		return techniqueList;
	}
	
}
