package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.text.NumberFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;


import characterStuff.Character;
import characterStuff.Stat;
import characterStuff.StatName;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;

public class MainGUI {

	private JFrame frmCharacterCreator;
	private JTextField tfName;
	private JTextField tfSpecies;
	private JTextField tfAlignment;
	
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem mntmNew;
	JMenuItem mntmLoad;
	JMenuItem mntmSave;
	JMenu helpMenu;
	
	JLabel lblName;
	JLabel lblGender;
	JLabel lblAge;
	JLabel lblSpecies;
	JLabel lblAlignment;
	JLabel lblDescription;
	JLabel lblWil;
	JLabel lblMagic;
	JLabel lblAgil;
	JLabel lblPer;
	JLabel lblCon;
	JLabel lblOtherDesc;
	
	
	JTextArea tfDescription;
	JComboBox cBoxGender;
	JFormattedTextField ftfAge;
	
	JButton btnEditCharPowers;
	
	JTextArea tfOtherDesc;
	JLabel lblStr;
	
	JFormattedTextField ftfStr;
	JFormattedTextField ftfWil;
	JFormattedTextField ftfMagic;
	JFormattedTextField ftfAgil;
	JFormattedTextField ftfCon;
	JFormattedTextField ftfPer;
	Font labelFont = new Font("Tahoma", Font.PLAIN, 14);
	Font textFieldFont = new Font("Tahoma", Font.PLAIN, 11);
	
	Character character = new Character();
	private JMenuItem mntmNewMenuItem;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmCharacterCreator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public int convertToInt(String text) {
		text = text.replaceAll(",", "");
		
		int number = Integer.parseInt(text);
		return number;
	}
	
	private void initialize() {
		frmCharacterCreator = new JFrame();
		frmCharacterCreator.getContentPane().setFont(labelFont);
		frmCharacterCreator.setTitle("Character Creator");
		frmCharacterCreator.setBounds(100, 100, 488, 493);
		frmCharacterCreator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		initializeMenuFields();
		initializeBasicFields();
		initializeStatFields();

		btnEditCharPowers = new JButton("Edit Talents and Techniques");
		btnEditCharPowers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CharPowerGUI cpgui = new CharPowerGUI();
				cpgui.run();
				
			}
		});
		btnEditCharPowers.setBounds(126, 293, 225, 23);
		frmCharacterCreator.getContentPane().add(btnEditCharPowers);
		
		lblOtherDesc = new JLabel("Other: ");
		lblOtherDesc.setFont(labelFont);
		lblOtherDesc.setBounds(21, 347, 46, 23);
		frmCharacterCreator.getContentPane().add(lblOtherDesc);
		
		tfOtherDesc = new JTextArea();
		tfOtherDesc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setOtherDesc(tfOtherDesc.getText());
			}
		});
		tfOtherDesc.setBounds(74, 348, 373, 74);
		frmCharacterCreator.getContentPane().add(tfOtherDesc);
		
		

	}
	


	public void initializeMenuFields() {
		menuBar = new JMenuBar();
		frmCharacterCreator.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		mntmNew = new JMenuItem("New");
		fileMenu.add(mntmNew);
		
		mntmLoad = new JMenuItem("Load");
		
		fileMenu.add(mntmLoad);
		
		mntmSave = new JMenuItem("Save");
		fileMenu.add(mntmSave);
		
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileSuffix = ".txt";
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "*" + fileSuffix, "txt");
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.addChoosableFileFilter(filter);
				jfc.setDialogTitle("Choose a directory to save your file: ");
				//jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					
					// insert file stuff here later
					String filePath = jfc.getSelectedFile().getPath();
					String fileName = jfc.getSelectedFile().getName();
					
					// filename is empty
					if (fileName.isEmpty()) {
						fileName = "CHARACTER_" + character.getName() + fileSuffix;
						filePath += fileName;
					}
					else {
						
						// if the filename has less than 5 characters or doesn't contain the file suffix
						if (fileName.length() <= 4 || !fileName.substring(fileName.length() - 4).toLowerCase().equals(fileSuffix)) {
							filePath += fileSuffix;
						}
					}
					
					try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"))) 
					{
							writer.write(character.toString());
					}
					catch (Exception e) {
						e.printStackTrace();	
					}
					System.out.println("You selected the place: " + filePath + " to save your file in.");
					
					
					
				}
				
			}
		});
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		mntmNewMenuItem = new JMenuItem("Print Character to Console (DEBUG)");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(character.toString());
			}
		});
		helpMenu.add(mntmNewMenuItem);
		frmCharacterCreator.getContentPane().setLayout(null);
		
	}
	
	public void initializeBasicFields() {
		lblName = new JLabel("Name:");
		lblName.setFont(labelFont);
		lblName.setBounds(21, 19, 46, 14);
		frmCharacterCreator.getContentPane().add(lblName);
		
		lblGender = new JLabel("Gender:");
		lblGender.setFont(labelFont);
		lblGender.setBounds(21, 44, 57, 14);
		frmCharacterCreator.getContentPane().add(lblGender);
		
		lblAge = new JLabel("Age: ");
		lblAge.setFont(labelFont);
		lblAge.setBounds(21, 80, 46, 23);
		frmCharacterCreator.getContentPane().add(lblAge);
		
		lblSpecies = new JLabel("Species:");
		lblSpecies.setFont(labelFont);
		lblSpecies.setBounds(21, 105, 71, 23);
		frmCharacterCreator.getContentPane().add(lblSpecies);
		
		lblAlignment = new JLabel("Alignment:");
		lblAlignment.setFont(labelFont);
		lblAlignment.setBounds(21, 134, 71, 23);
		frmCharacterCreator.getContentPane().add(lblAlignment);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(labelFont);
		lblDescription.setBounds(232, 16, 94, 23);
		frmCharacterCreator.getContentPane().add(lblDescription);
		
		tfName = new JTextField();
		
		// sets the character text
		tfName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!tfName.getText().isEmpty()) {
					character.setName(tfName.getText());
				}
			}
		});
		tfName.setBounds(103, 19, 86, 20);
		frmCharacterCreator.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		tfSpecies = new JTextField();
		tfSpecies.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setSpecies(tfSpecies.getText());
			}
		});
		tfSpecies.setBounds(103, 105, 86, 20);
		frmCharacterCreator.getContentPane().add(tfSpecies);
		tfSpecies.setColumns(10);
		
		tfAlignment = new JTextField();
		tfAlignment.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setAlignment(tfAlignment.getText());
			}
		});
		tfAlignment.setBounds(102, 137, 86, 20);
		frmCharacterCreator.getContentPane().add(tfAlignment);
		tfAlignment.setColumns(10);
		
		tfDescription = new JTextArea();
		tfDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				character.setDescription(tfDescription.getText());
				
			}
		});
		tfDescription.setFont(textFieldFont);
		tfDescription.setBounds(233, 45, 214, 112);
		frmCharacterCreator.getContentPane().add(tfDescription);
		
		// 
		cBoxGender = new JComboBox<Object>();
		cBoxGender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				character.setGender(cBoxGender.getSelectedItem().toString());
				
			}
		});
		cBoxGender.setModel(new DefaultComboBoxModel<Object>(new String[] {"Male", "Female", "Other"}));
		cBoxGender.setSelectedIndex(0);
		cBoxGender.setBounds(103, 44, 86, 20);
		frmCharacterCreator.getContentPane().add(cBoxGender);
		
		NumberFormat numFormat = NumberFormat.getInstance();
		NumberFormatter numFormatter = new NumberFormatter(numFormat);
		
		numFormatter.setValueClass(Integer.class);
		numFormatter.setMinimum(0);
		numFormatter.setMaximum(Integer.MAX_VALUE);
		numFormatter.setAllowsInvalid(false);
		numFormatter.setCommitsOnValidEdit(true);
		
		ftfAge = new JFormattedTextField(numFormatter);
		ftfAge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setAge(convertToInt(ftfAge.getText()));
			}
		});
		ftfAge.setBounds(103, 80, 86, 20);
		ftfAge.setValue(new Integer(0));
		
		frmCharacterCreator.getContentPane().add(ftfAge);

		
		
	}
	
	public void initializeStatFields() {
		
		NumberFormat statFormat = NumberFormat.getInstance();
		NumberFormatter statFormatter = new NumberFormatter(statFormat);
		
		statFormatter.setValueClass(Integer.class);
		statFormatter.setMinimum(0);
		statFormatter.setMaximum(Integer.MAX_VALUE);
		statFormatter.setAllowsInvalid(false);
		statFormatter.setCommitsOnValidEdit(true);
		

		lblStr = new JLabel("STRENGTH");
		lblStr.setFont(labelFont);
		lblStr.setBounds(21, 196, 116, 14);
		frmCharacterCreator.getContentPane().add(lblStr);
		
		lblWil = new JLabel("WILLPOWER");
		
		lblWil.setFont(labelFont);
		lblWil.setBounds(235, 196, 89, 14);
		frmCharacterCreator.getContentPane().add(lblWil);
		
		lblMagic = new JLabel("MAGIC POTENTIAL");
		lblMagic.setFont(labelFont);
		lblMagic.setBounds(235, 221, 116, 14);
		frmCharacterCreator.getContentPane().add(lblMagic);
		
		lblAgil = new JLabel("AGILITY");
		lblAgil.setFont(labelFont);
		lblAgil.setBounds(235, 246, 89, 14);
		frmCharacterCreator.getContentPane().add(lblAgil);
		
		lblPer = new JLabel("PERCEPTION");
		lblPer.setFont(labelFont);
		lblPer.setBounds(21, 221, 116, 14);
		frmCharacterCreator.getContentPane().add(lblPer);
		
		lblCon = new JLabel("CONSTITUTION");
		lblCon.setFont(labelFont);
		lblCon.setBounds(21, 246, 116, 14);
		frmCharacterCreator.getContentPane().add(lblCon);
		
		ftfStr = new JFormattedTextField(statFormatter);
		ftfStr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!ftfStr.getText().isEmpty()) {
					character.updateStat(StatName.STRENGTH, convertToInt(ftfStr.getText()));
				}
			}
		});
		ftfStr.setColumns(10);
		ftfStr.setBounds(149, 195, 61, 20);
		frmCharacterCreator.getContentPane().add(ftfStr);
		
		
		
		ftfWil = new JFormattedTextField(statFormatter);
		ftfWil.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e){
				if (!ftfWil.getText().isEmpty()) {
					character.updateStat(StatName.WILLPOWER, convertToInt(ftfWil.getText()));
				}
			}
		});
		ftfWil.setColumns(10);
		ftfWil.setBounds(386, 195, 61, 20);
		frmCharacterCreator.getContentPane().add(ftfWil);
		
		ftfMagic = new JFormattedTextField(statFormatter);
		ftfMagic.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!ftfMagic.getText().isEmpty()) {
					character.updateStat(StatName.MAGIC_POTENTIAL, convertToInt(ftfMagic.getText()));
				}
			}
		});
		ftfMagic.setColumns(10);
		ftfMagic.setBounds(386, 220, 61, 20);
		frmCharacterCreator.getContentPane().add(ftfMagic);
		
		ftfAgil = new JFormattedTextField(statFormatter);
		ftfAgil.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!ftfAgil.getText().isEmpty()) {
					character.updateStat(StatName.AGILITY, convertToInt(ftfAgil.getText()));
				}
			}	
		});
		ftfAgil.setColumns(10);
		ftfAgil.setBounds(386, 245, 61, 20);
		frmCharacterCreator.getContentPane().add(ftfAgil);
		
		
		
		ftfCon = new JFormattedTextField(statFormatter);
		ftfCon.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!ftfCon.getText().isEmpty()) {
					character.updateStat(StatName.CONSTITUTION, convertToInt(ftfCon.getText()));
				}
			}
		});
		ftfCon.setColumns(10);
		ftfCon.setBounds(149, 245, 61, 20);
		frmCharacterCreator.getContentPane().add(ftfCon);
		
		ftfPer = new JFormattedTextField(statFormatter);
		ftfPer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!ftfPer.getText().isEmpty()) {
					character.updateStat(StatName.PERCEPTION, convertToInt(ftfPer.getText()));
				}
			}
		});
		ftfPer.setColumns(10);
		ftfPer.setBounds(149, 220, 61, 20);
		frmCharacterCreator.getContentPane().add(ftfPer);
		
		
	}
}
