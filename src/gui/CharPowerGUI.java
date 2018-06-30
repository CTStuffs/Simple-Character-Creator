package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import characterStuff.CharPower;
import characterStuff.PowerType;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CharPowerGUI {

	private JFrame charPowerMainpanel;
	private JTextField tfPowerName;

	
	JLabel lblName;
	JComboBox cBoxRank;
	JComboBox cBoxPowerType;
	JTextArea tfPowerDesc;
	JLabel lblPowerDescription;
	JTextPane tfTalents;
	JTextPane tfTechniques;
	JLabel lblTalents;
	JLabel lblTechniques;
	JLabel lblPowerType;
	JLabel lblRank;
	JButton btnAddPower;
	
	CharPower newPower = new CharPower();
	
	
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
	
	public void run() {
		this.charPowerMainpanel.setVisible(true);
		this.charPowerMainpanel.toFront();
		this.charPowerMainpanel.requestFocus();
		//this.charPowerMainpanel.setAlwaysOnTop(true); 
	}

	/**
	 * Create the application.
	 */
	public CharPowerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	
		
		charPowerMainpanel = new JFrame();
		charPowerMainpanel.setTitle("Edit Character Powers");
		charPowerMainpanel.setBounds(100, 100, 418, 482);
		charPowerMainpanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		charPowerMainpanel.getContentPane().setLayout(null);
		addLabels();
		addInteractives();
		
	}
	
	public void addInteractives() {
		tfPowerName = new JTextField();
		tfPowerName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!tfPowerName.getText().isEmpty()) {
					newPower.setName(tfPowerName.getText());
				}
			}
		});
		tfPowerName.setBounds(42, 290, 121, 20);
		charPowerMainpanel.getContentPane().add(tfPowerName);
		tfPowerName.setColumns(10);
		
		cBoxRank = new JComboBox();
		cBoxRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPower.setRankByString(cBoxRank.getSelectedItem().toString());
			}
		});
		cBoxRank.setModel(new DefaultComboBoxModel(new String[] {"E", "D", "C", "B", "A", "S"}));
		cBoxRank.setBounds(311, 290, 48, 20);
		charPowerMainpanel.getContentPane().add(cBoxRank);
		
		cBoxPowerType = new JComboBox();
		cBoxPowerType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newPower.setTypeByString(cBoxPowerType.getSelectedItem().toString());
			}
		});
		cBoxPowerType.setModel(new DefaultComboBoxModel(new String[] {"Talent", "Technique"}));
		cBoxPowerType.setBounds(205, 290, 91, 20);
		charPowerMainpanel.getContentPane().add(cBoxPowerType);
		
		tfPowerDesc = new JTextArea();
		tfPowerDesc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!tfPowerDesc.getText().isEmpty()) {
					newPower.setDescription(tfPowerDesc.getText());
				}
				
			}
		});
		tfPowerDesc.setBounds(42, 339, 317, 52);
		charPowerMainpanel.getContentPane().add(tfPowerDesc);

		tfTalents = new JTextPane();
		tfTalents.setEditable(false);
		tfTalents.setBounds(42, 37, 317, 86);
		
		charPowerMainpanel.getContentPane().add(tfTalents);
		
		
//		JScrollPane jspTalent = new JScrollPane(tfTalents);
//		jspTalent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		charPowerMainpanel.add(jspTalent);
	   // frame.setSize(300, 200);
		
		tfTechniques = new JTextPane();
		tfTechniques.setEditable(false);
		tfTechniques.setBounds(42, 158, 317, 86);
		charPowerMainpanel.getContentPane().add(tfTechniques);
		
//		JScrollPane jspTechnique = new JScrollPane(tfTechniques);
//		jspTechnique.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		charPowerMainpanel.add(jspTechnique);
	   // frame.setSize(300, 200);
		
		
		btnAddPower = new JButton("Add Power");
		btnAddPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String existingText = "";
				if (newPower.getType() == PowerType.TALENT) {
					existingText = tfTalents.getText();
					existingText += (newPower.toString() + "\n");
					tfTalents.setText(existingText);
				}
				else if (newPower.getType() == PowerType.TECHNIQUE) {
					existingText = tfTechniques.getText();
					existingText += (newPower.toString() + "\n");
					tfTechniques.setText(existingText);
				}
				else {
					System.out.println("ERROR! Please check type of the new power!");
				}
				
			}
		});
		btnAddPower.setBounds(52, 409, 89, 23);
		charPowerMainpanel.getContentPane().add(btnAddPower);
	}
	
	public void addLabels() {
		lblTalents = new JLabel("Talents:");
		lblTalents.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTalents.setBounds(42, 11, 61, 14);
		charPowerMainpanel.getContentPane().add(lblTalents);
		
		lblTechniques = new JLabel("Techniques:");
		lblTechniques.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTechniques.setBounds(38, 134, 83, 14);
		charPowerMainpanel.getContentPane().add(lblTechniques);
		
		lblPowerType = new JLabel("Power Type:");
		lblPowerType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPowerType.setBounds(205, 266, 91, 14);
		charPowerMainpanel.getContentPane().add(lblPowerType);
		
		
		lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(42, 265, 61, 14);
		charPowerMainpanel.getContentPane().add(lblName);
		
		lblPowerDescription = new JLabel("Description:");
		lblPowerDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPowerDescription.setBounds(42, 321, 77, 14);
		charPowerMainpanel.getContentPane().add(lblPowerDescription);
		
		lblRank = new JLabel("Rank:");
		lblRank.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRank.setBounds(311, 266, 61, 14);
		charPowerMainpanel.getContentPane().add(lblRank);
		
		
	}
}
