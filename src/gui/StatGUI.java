package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import models.CharPower;
import models.Character;
import models.StatCollection;
import models.StatName;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatGUI extends JDialog {

	private static StatGUI instance = null;
	private StatCollection stats = new StatCollection();
	GUIUtility util = new GUIUtility();
	
	JLabel labelSstr;
	JLabel labelPer;
	JLabel labelCon;
	JLabel labelWil;
	JLabel labelMagic;
	JLabel labelAgil;
	
	JFormattedTextField formattedTextFieldCon;
	JFormattedTextField formattedTextFieldPer;
	JFormattedTextField formattedTextFieldStr;
	JFormattedTextField formattedTextFieldWil;
	JFormattedTextField formattedTextFieldMagic;
	JFormattedTextField formattedTextFieldAgil;
	JButton btnOk;	
	ArrayList<GUIComponentRecord> guiComponents = new ArrayList<GUIComponentRecord>();
	
	// create singleton object instance
	public static StatGUI getInstance() {
		if(instance == null)
		{
			instance = new StatGUI();
		}
		return instance;
	}

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatGUI dialog = new StatGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public StatGUI() {
		setTitle("Edit Stsats");
		setBounds(100, 100, 221, 240);
		getContentPane().setLayout(new MigLayout("", "[130px][61px]", "[20px][20px][20px][20px][20px][20px][][][]"));
	
		initializeLabels();
		initializeFields();
		createGUIRecords();
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		getContentPane().add(btnOk, "cell 0 7,growx,aligny center");
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}
	
	public void run() {
		this.setVisible(true);
	}
	
	public void initializeLabels() {
		
		labelSstr = new JLabel("STRENGTH:");
		labelSstr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(labelSstr, "cell 0 0,alignx left,aligny top");
		
		labelPer = new JLabel("PERCEPTION:");
		labelPer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(labelPer, "cell 0 1,growx,aligny top");
		
		labelCon = new JLabel("CONSTITUTION:");
		labelCon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(labelCon, "cell 0 2,growx,aligny top");

		labelWil = new JLabel("WILLPOWER:");
		labelWil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(labelWil, "cell 0 3,alignx left,aligny top");
		
		labelMagic = new JLabel("MAGIC POTENTIAL:");
		labelMagic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(labelMagic, "cell 0 4,growx,aligny top");
		
		labelAgil = new JLabel("AGILITY:");
		labelAgil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(labelAgil, "cell 0 5,alignx left,aligny top");
	}
	
	// initializes all the fields
	public void initializeFields() {
		// number formatter for the stat fields
		NumberFormat statFormat = NumberFormat.getInstance();
		NumberFormatter statFormatter = new NumberFormatter(statFormat);
		statFormatter.setValueClass(Integer.class);
		statFormatter.setMinimum(0);
		statFormatter.setMaximum(Integer.MAX_VALUE);
		
		// user can only input numerical characters into the fields
		statFormatter.setAllowsInvalid(false);
		statFormatter.setCommitsOnValidEdit(true);
		
	
		formattedTextFieldCon = new JFormattedTextField(statFormatter);
		formattedTextFieldCon.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formattedTextFieldCon.getText().isEmpty()) {
					stats.updateStat(StatName.CONSTITUTION, util.convertToInt(formattedTextFieldCon.getText()));
				}
			}
		});
		formattedTextFieldCon.setColumns(10);
		getContentPane().add(formattedTextFieldCon, "cell 1 2,growx,aligny top");
		
		formattedTextFieldPer = new JFormattedTextField(statFormatter);
		formattedTextFieldPer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formattedTextFieldPer.getText().isEmpty()) {
					stats.updateStat(StatName.PERCEPTION, util.convertToInt(formattedTextFieldPer.getText()));
				}
			}
		});
		formattedTextFieldPer.setColumns(10);
		getContentPane().add(formattedTextFieldPer, "cell 1 1,growx,aligny top");
		
		formattedTextFieldStr = new JFormattedTextField(statFormatter);
		formattedTextFieldStr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!formattedTextFieldStr.getText().isEmpty()) {
					stats.updateStat(StatName.STRENGTH, util.convertToInt(formattedTextFieldStr.getText()));
				}
			}
		});
		formattedTextFieldStr.setColumns(10);
		getContentPane().add(formattedTextFieldStr, "cell 1 0,growx,aligny top");
		
		formattedTextFieldWil = new JFormattedTextField(statFormatter);
		formattedTextFieldWil.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formattedTextFieldWil.getText().isEmpty()) {
					stats.updateStat(StatName.WILLPOWER, util.convertToInt(formattedTextFieldWil.getText()));
				}
			}
		});
		formattedTextFieldWil.setColumns(10);
		getContentPane().add(formattedTextFieldWil, "cell 1 3,growx,aligny top");
		
		formattedTextFieldMagic = new JFormattedTextField(statFormatter);
		formattedTextFieldMagic.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formattedTextFieldMagic.getText().isEmpty()) {
					stats.updateStat(StatName.MAGIC_POTENTIAL, util.convertToInt(formattedTextFieldMagic.getText()));
				}
			}
		});
		formattedTextFieldMagic.setColumns(10);
		getContentPane().add(formattedTextFieldMagic, "cell 1 4,growx,aligny top");
		
		formattedTextFieldAgil = new JFormattedTextField(statFormatter);
		formattedTextFieldAgil.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formattedTextFieldAgil.getText().isEmpty()) {
					stats.updateStat(StatName.AGILITY, util.convertToInt(formattedTextFieldAgil.getText()));
				}
			}
		});
		formattedTextFieldAgil.setColumns(10);
		getContentPane().add(formattedTextFieldAgil, "cell 1 5,growx,aligny top");
	}
	
	// sets the contents of the fields depending on a Character object
	public void setFieldsByCharacter(Character character) {
		StatCollection tempStats = character.getStats();
		formattedTextFieldStr.setText(String.valueOf(tempStats.getStat(StatName.STRENGTH)));
		formattedTextFieldPer.setText(String.valueOf(tempStats.getStat(StatName.PERCEPTION)));
		formattedTextFieldCon.setText(String.valueOf(tempStats.getStat(StatName.CONSTITUTION)));
		formattedTextFieldMagic.setText(String.valueOf(tempStats.getStat(StatName.MAGIC_POTENTIAL)));
		formattedTextFieldAgil.setText(String.valueOf(tempStats.getStat(StatName.AGILITY)));
		formattedTextFieldWil.setText(String.valueOf(tempStats.getStat(StatName.WILLPOWER)));
		
		// reset the StatCollection object
		stats = tempStats;
		
	}
	
	public StatCollection getStats() {
		return stats;
	}
	
	// make a record of all the text fields in the gui
	public void createGUIRecords() {
		guiComponents.add(new GUIComponentRecord(formattedTextFieldStr, "Strength Stat"));
		guiComponents.add(new GUIComponentRecord(formattedTextFieldPer, "Perception Stat"));
		guiComponents.add(new GUIComponentRecord(formattedTextFieldCon, "Constitution Stat"));
		guiComponents.add(new GUIComponentRecord(formattedTextFieldMagic, "Magic Potential Stat"));
		guiComponents.add(new GUIComponentRecord(formattedTextFieldAgil, "Agility Stat"));
		guiComponents.add(new GUIComponentRecord(formattedTextFieldWil, "Willpower Stat"));
	}

	
	// returns the names of the empty fields as string
	// used in the save process
	public String getEmptyFieldsAsString() {
		
		StringBuilder sb = new StringBuilder();
		
		for (GUIComponentRecord record: guiComponents) {
			if (!record.getText().isEmpty()) {
				sb.append(record.getName() + ", ");
			}
		}
		return sb.toString();
	}
	
	
	// resets all the stat fields
	public void resetFields() {
		stats = new StatCollection();
		for (GUIComponentRecord record: guiComponents) {
			if (record.getText().isEmpty()) {
				record.setText("0");
			}
		}
	}
	
}
