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

/**
 * The GUI for add new character powers 
 *
 */

// TODO: Figure out how to edit and delete already added character powers


public class CharPowerGUI {

	private JDialog charPowerMainpanel;
	private JTextField textfieldPowerName;
	private static CharPowerGUI instance = null;
	
	JLabel lblName;
	JComboBox comboxBoxRank;
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
					window.charPowerMainpanel.setVisible(true);
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
		this.charPowerMainpanel.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		charPowerMainpanel = new JDialog();
		charPowerMainpanel.setTitle("Edit Character Powers");
		charPowerMainpanel.setBounds(100, 100, 418, 482);
		charPowerMainpanel.getContentPane().setLayout(null);
		addLabels();
		addInteractives();
		
		this.charPowerMainpanel.setModal(true);
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
		newPower.setRankByString(comboxBoxRank.getSelectedItem().toString());
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
		textfieldPowerName.setBounds(42, 280, 121, 20);
		textfieldPowerName.setColumns(10);
		
		comboxBoxRank = new JComboBox();
		comboxBoxRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRankToCharPower();
			}
		});
		comboxBoxRank.setModel(new DefaultComboBoxModel(new String[] {"E", "D", "C", "B", "A", "S"}));
		comboxBoxRank.setBounds(313, 280, 48, 20);
		comboxBoxRank.setSelectedIndex(0);
		
		comboBoxPowerType = new JComboBox();
		comboBoxPowerType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTypeToCharPower();
			}
		});
		comboBoxPowerType.setModel(new DefaultComboBoxModel(new String[] {"Talent", "Technique"}));
		comboBoxPowerType.setBounds(205, 280, 91, 20);
		comboBoxPowerType.setSelectedIndex(0);
		
		textfieldPowerDesc = new JTextArea();
		textfieldPowerDesc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				setDescToCharPower();
				
			}
		});
		textfieldPowerDesc.setBounds(42, 331, 317, 60);
		textfieldPowerDesc.setBorder(etchedBorder);
	
		// TODO: Add scroll bars to these text panes
		// text panes for displaying the talents and techniques. They cannot be edited
		textfieldTalents = new JTextPane();
		textfieldTalents.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textfieldTalents.setEditable(false);
		textfieldTalents.setBounds(42, 37, 317, 86);
		textfieldTalents.setBorder(etchedBorder);

		textfieldTechniques = new JTextPane();
		textfieldTechniques.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textfieldTechniques.setEditable(false);
		textfieldTechniques.setBounds(42, 158, 317, 86);
		textfieldTechniques.setBorder(etchedBorder);
		
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
		btnAddPower.setBounds(42, 402, 121, 23);
		
		charPowerMainpanel.getContentPane().add(textfieldPowerName);
		charPowerMainpanel.getContentPane().add(comboxBoxRank);
		charPowerMainpanel.getContentPane().add(comboBoxPowerType);
		charPowerMainpanel.getContentPane().add(textfieldPowerDesc);
		charPowerMainpanel.getContentPane().add(textfieldTalents);
		charPowerMainpanel.getContentPane().add(textfieldTechniques);
		charPowerMainpanel.getContentPane().add(btnAddPower);
	}
	
	
	// initializes the labels
	public void addLabels() {
		lblTalents = new JLabel("Talents:");
		lblTalents.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTalents.setBounds(42, 11, 61, 14);
		
		lblTechniques = new JLabel("Techniques:");
		lblTechniques.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTechniques.setBounds(38, 134, 83, 14);
		
		lblPowerType = new JLabel("Power Type:");
		lblPowerType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPowerType.setBounds(205, 255, 91, 14);
		
		lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(42, 255, 61, 14);
		
		lblPowerDescription = new JLabel("Description:");
		lblPowerDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPowerDescription.setBounds(42, 311, 77, 14);
		
		lblRank = new JLabel("Rank:");
		lblRank.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRank.setBounds(313, 255, 61, 14);
		
		charPowerMainpanel.getContentPane().add(lblTalents);
		charPowerMainpanel.getContentPane().add(lblTechniques);
		charPowerMainpanel.getContentPane().add(lblPowerType);
		charPowerMainpanel.getContentPane().add(lblName);
		charPowerMainpanel.getContentPane().add(lblPowerDescription);
		charPowerMainpanel.getContentPane().add(lblRank);
		
		
	}
	
	public ArrayList<CharPower> getTalents(){
		return talentList;
	}
	
	public ArrayList<CharPower> getTechniques(){
		return techniqueList;
	}
	
}
