package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.text.NumberFormatter;

import models.CharPower;
import models.Character;
import models.PowerType;
import models.Stat;
import models.StatName;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import java.awt.SystemColor;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;

/**
 * 
 * Main file for the GUI.
 * 
 * **/

public class MainGUI {

	private JFrame frmCharacterCreator;
	private JTextField textfieldName;
	private JTextField textfieldSpecies;
	private JTextField textfieldAlignment;
	
	// menu and menu choices
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem menuChoiceNew;
	private JMenuItem menuChoiceLoad;
	private JMenuItem menuChoiceSave;
	private JMenu helpMenu;
	
	// labels
	private JLabel lblName;
	private JLabel lblGender;
	private JLabel lblAge;
	private JLabel lblSpecies;
	private JLabel lblAlignment;
	private JLabel lblDescription;
	private JLabel lblWil;
	private JLabel lblMagic;
	private JLabel lblAgil;
	private JLabel lblPer;
	private JLabel lblCon;
	private JLabel lblOtherDesc;
	private JLabel lblStr;
	
	// combo box and buttons
	private JComboBox comboBoxGender;
	private JButton btnEditCharPowers;
	
	// text fields
	private JTextArea textfieldDescription;
	private JFormattedTextField formatTextfieldAge;
	private JTextArea textfieldOtherDesc;
	private JFormattedTextField formatTextfieldStr;
	private JFormattedTextField formatTextfieldWil;
	private JFormattedTextField formatTextfieldMagic;
	private JFormattedTextField formatTextfieldAgl;
	private JFormattedTextField formatTextfieldCon;
	private JFormattedTextField formatTextfieldPer;
	
	
	
	// other components 
	private Font labelFont = new Font("Tahoma", Font.PLAIN, 14);
	private Font textFieldFont = new Font("Tahoma", Font.PLAIN, 11);
	
	// seperate gui for the character powers
	private CharPowerGUI cpgui = CharPowerGUI.getInstance();
	private JFileChooser jfc;
	
	// the character to be edited by the user
	private Character character = new Character();
	private ArrayList<GUIComponentRecord> guiComponents = new ArrayList<GUIComponentRecord>();
	private String txtFileSuffix = ".txt";
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "*" + txtFileSuffix, "txt");

	private JMenuItem menuChoicePrintDebug;
	
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
	
	// program that initializes the gui components, including the frame
	private void initialize() {
		frmCharacterCreator = new JFrame();
		frmCharacterCreator.getContentPane().setFont(labelFont);
		frmCharacterCreator.setTitle("Character Creator");
		frmCharacterCreator.setBounds(100, 100, 488, 470);
		frmCharacterCreator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializeMenu();
		initializeTextFields();
		initializeStatFields();
		initializeInteractables();
		initializeLabels();
		createGUIRecords();
		
		
	}
	
	// adds the gui components to an arraylist for further processing l=
	public void createGUIRecords() {
		guiComponents.add(new GUIComponentRecord(textfieldName, "Name"));
		guiComponents.add(new GUIComponentRecord(textfieldSpecies, "Species"));
		guiComponents.add(new GUIComponentRecord(textfieldAlignment, "Alignment"));
		guiComponents.add(new GUIComponentRecord(textfieldDescription, "Description"));
		guiComponents.add(new GUIComponentRecord(formatTextfieldAge, "Age"));
		guiComponents.add(new GUIComponentRecord(textfieldOtherDesc, "Other Description"));
		guiComponents.add(new GUIComponentRecord(formatTextfieldStr, "Strength Stat"));
		guiComponents.add(new GUIComponentRecord(formatTextfieldWil, "Willpower Stat"));
		guiComponents.add(new GUIComponentRecord(formatTextfieldMagic, "Magic Stat"));
		guiComponents.add(new GUIComponentRecord(formatTextfieldAgl, "Agility Stat"));
		guiComponents.add(new GUIComponentRecord(formatTextfieldCon, "Constitution Stat"));
		guiComponents.add(new GUIComponentRecord(formatTextfieldPer, "Perception Stat"));
	}


	// creates and initializes the labels on the primary gui
	public void initializeLabels() {
		lblName = new JLabel("Name:");
		lblName.setFont(labelFont);
		lblName.setBounds(21, 19, 46, 14);
		frmCharacterCreator.getContentPane().add(lblName);
		
		lblGender = new JLabel("Gender:");
		lblGender.setFont(labelFont);
		lblGender.setBounds(21, 44, 57, 14);
		frmCharacterCreator.getContentPane().add(lblGender);
		
		lblAge = new JLabel("Age:");
		lblAge.setFont(labelFont);
		lblAge.setBounds(21, 80, 46, 23);
		frmCharacterCreator.getContentPane().add(lblAge);
		
		lblSpecies = new JLabel("Species:");
		lblSpecies.setFont(labelFont);
		lblSpecies.setBounds(21, 105, 71, 23);
		frmCharacterCreator.getContentPane().add(lblSpecies);
		
		lblAlignment = new JLabel("Alignment:");
		lblAlignment.setFont(labelFont);
		lblAlignment.setBounds(21, 130, 71, 23);
		frmCharacterCreator.getContentPane().add(lblAlignment);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(labelFont);
		lblDescription.setBounds(232, 16, 94, 23);
		frmCharacterCreator.getContentPane().add(lblDescription);
		
		lblOtherDesc = new JLabel("Other: ");
		lblOtherDesc.setFont(labelFont);
		lblOtherDesc.setBounds(21, 326, 46, 23);
		frmCharacterCreator.getContentPane().add(lblOtherDesc);
		
		lblStr = new JLabel("STRENGTH:");
		lblStr.setFont(labelFont);
		lblStr.setBounds(21, 175, 86, 14);
		frmCharacterCreator.getContentPane().add(lblStr);
		
		lblWil = new JLabel("WILLPOWER:");
		lblWil.setFont(labelFont);
		lblWil.setBounds(235, 175, 89, 14);
		frmCharacterCreator.getContentPane().add(lblWil);
		
		lblMagic = new JLabel("MAGIC POTENTIAL:");
		lblMagic.setFont(labelFont);
		lblMagic.setBounds(235, 200, 130, 14);
		frmCharacterCreator.getContentPane().add(lblMagic);
		
		lblAgil = new JLabel("AGILITY:");
		lblAgil.setFont(labelFont);
		lblAgil.setBounds(235, 225, 89, 14);
		frmCharacterCreator.getContentPane().add(lblAgil);
		
		lblPer = new JLabel("PERCEPTION:");
		lblPer.setFont(labelFont);
		lblPer.setBounds(21, 200, 116, 14);
		frmCharacterCreator.getContentPane().add(lblPer);
		
		lblCon = new JLabel("CONSTITUTION:");
		lblCon.setFont(labelFont);
		lblCon.setBounds(21, 225, 116, 14);
		frmCharacterCreator.getContentPane().add(lblCon);
	}
	
	
	// creates the warning text that appears when not all text fields are filled in and the user clicks on the save option
	public String getSaveWarningText() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>The following fields are blank: ");
		boolean needsError = false;
		
		// go through all the text field components
		for (GUIComponentRecord gc: guiComponents) {
			
			// if the field is empty, add its name to the warning string
			if (gc.getText().isEmpty()) {
				needsError = true;
				sb.append(gc.getName() + ", ");
			}
		}
		
		// do the same for the talents and technique arraylists
		if (cpgui.getTalents().size() <= 0) {
			sb.append("Talents, ");
		}
		if (cpgui.getTechniques().size() <= 0) {
			sb.append("Techniques, ");
		}
		
		// if a blank field has been detected, return the string
		// otherwise return an empty string (that will be ignored)
		if (needsError) {
			sb.setLength(sb.length() - 2);
			sb.append(".\n");
			sb.append("<br>Are you sure you want to continue saving?</html>");
			return sb.toString();
		}
		else {
			return "";
		}		
	}
	
	// initializes the text fields
	public void initializeTextFields() {
		
		// border for visuals
		Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		// numberformatter for fields for numerics only
		// allows for commas, but not fullstops
		NumberFormat numFormat = NumberFormat.getInstance();
		NumberFormatter numFormatter = new NumberFormatter(numFormat);
		numFormatter.setValueClass(Integer.class);
		numFormatter.setMinimum(0);
		numFormatter.setMaximum(Integer.MAX_VALUE);
		numFormatter.setAllowsInvalid(false);
		numFormatter.setCommitsOnValidEdit(true);

		textfieldName = new JTextField();
		textfieldName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setName(textfieldSpecies.getText());
			}
		});
		textfieldName.setBounds(103, 19, 86, 20);
		textfieldName.setColumns(10);
		
		textfieldSpecies = new JTextField();
		textfieldSpecies.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setSpecies(textfieldSpecies.getText());
			}
		});
		textfieldSpecies.setBounds(103, 105, 86, 20);
		textfieldSpecies.setColumns(10);
		
		textfieldAlignment = new JTextField();
		textfieldAlignment.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setAlignment(textfieldAlignment.getText());
			}
		});
		textfieldAlignment.setBounds(103, 130, 86, 20);
		textfieldAlignment.setColumns(10);
		
		textfieldDescription = new JTextArea();
		textfieldDescription.setLineWrap(true);
		textfieldDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				character.setDescription(textfieldDescription.getText());
				
			}
		});
		textfieldDescription.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textfieldDescription.setBounds(233, 45, 214, 105);
		textfieldDescription.setBorder(etchedBorder);
		
		formatTextfieldAge = new JFormattedTextField(numFormatter);
		formatTextfieldAge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setAge(convertToInt(formatTextfieldAge.getText()));
			}
		});
		formatTextfieldAge.setBounds(103, 80, 86, 20);
		formatTextfieldAge.setValue(new Integer(0));
		
		// TODO: add scroll bars to this and the regular Description field
		textfieldOtherDesc = new JTextArea();
		textfieldOtherDesc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setOtherDesc(textfieldOtherDesc.getText());
			}
		});
		textfieldOtherDesc.setBounds(74, 327, 373, 74);
		textfieldOtherDesc.setBorder(etchedBorder);
//		JScrollPane sp = new JScrollPane(textfieldOtherDesc); 
//		GridBagConstraints c = new GridBagConstraints();
//        c.gridwidth = GridBagConstraints.REMAINDER;
// 
//        c.fill = GridBagConstraints.VERTICAL;
//        c.fill = GridBagConstraints.BOTH;
//        c.weightx = 1.0;
//        c.weighty = 1.0;
//		
		

		frmCharacterCreator.getContentPane().add(textfieldSpecies);
		frmCharacterCreator.getContentPane().add(textfieldName);
		frmCharacterCreator.getContentPane().add(textfieldAlignment);
		frmCharacterCreator.getContentPane().add(textfieldDescription);
		frmCharacterCreator.getContentPane().add(formatTextfieldAge);
		frmCharacterCreator.getContentPane().add(textfieldOtherDesc);
		//frmCharacterCreator.getContentPane().add(sp);
	}
	
	
	
	// initializes other interactables on the gui, such as combox boxes
	public void initializeInteractables() {

		comboBoxGender = new JComboBox<Object>();
		comboBoxGender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				character.setGender(comboBoxGender.getSelectedItem().toString());
				
			}
		});
		comboBoxGender.setModel(new DefaultComboBoxModel<Object>(new String[] {"Male", "Female", "Other"}));
		comboBoxGender.setSelectedIndex(0);
		comboBoxGender.setBounds(103, 44, 86, 20);
		

		btnEditCharPowers = new JButton("Edit Talents and Techniques");
		btnEditCharPowers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpgui.run();
			}
		});
		btnEditCharPowers.setBounds(126, 272, 225, 23);
		
		frmCharacterCreator.getContentPane().add(comboBoxGender);
		frmCharacterCreator.getContentPane().add(btnEditCharPowers);
	}
	
	// initializes the stat fields for the gui
	public void initializeStatFields() {
		
		// number formatter for the stat fields
		NumberFormat statFormat = NumberFormat.getInstance();
		NumberFormatter statFormatter = new NumberFormatter(statFormat);
		statFormatter.setValueClass(Integer.class);
		statFormatter.setMinimum(0);
		statFormatter.setMaximum(Integer.MAX_VALUE);
		statFormatter.setAllowsInvalid(false);
		statFormatter.setCommitsOnValidEdit(true);
		
		formatTextfieldStr = new JFormattedTextField(statFormatter);
		formatTextfieldStr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formatTextfieldStr.getText().isEmpty()) {
					character.updateStat(StatName.STRENGTH, convertToInt(formatTextfieldStr.getText()));
				}
			}
		});
		formatTextfieldStr.setColumns(10);
		formatTextfieldStr.setBounds(149, 174, 61, 20);

		formatTextfieldWil = new JFormattedTextField(statFormatter);
		formatTextfieldWil.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e){
				if (!formatTextfieldWil.getText().isEmpty()) {
					character.updateStat(StatName.WILLPOWER, convertToInt(formatTextfieldWil.getText()));
				}
			}
		});
		formatTextfieldWil.setColumns(10);
		formatTextfieldWil.setBounds(386, 174, 61, 20);
		
		formatTextfieldMagic = new JFormattedTextField(statFormatter);
		formatTextfieldMagic.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formatTextfieldMagic.getText().isEmpty()) {
					character.updateStat(StatName.MAGIC_POTENTIAL, convertToInt(formatTextfieldMagic.getText()));
				}
			}
		});
		formatTextfieldMagic.setColumns(10);
		formatTextfieldMagic.setBounds(386, 199, 61, 20);
	
		formatTextfieldAgl = new JFormattedTextField(statFormatter);
		formatTextfieldAgl.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formatTextfieldAgl.getText().isEmpty()) {
					character.updateStat(StatName.AGILITY, convertToInt(formatTextfieldAgl.getText()));
				}
			}	
		});
		formatTextfieldAgl.setColumns(10);
		formatTextfieldAgl.setBounds(386, 224, 61, 20);

		formatTextfieldCon = new JFormattedTextField(statFormatter);
		formatTextfieldCon.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formatTextfieldCon.getText().isEmpty()) {
					character.updateStat(StatName.CONSTITUTION, convertToInt(formatTextfieldCon.getText()));
				}
			}
		});
		formatTextfieldCon.setColumns(10);
		formatTextfieldCon.setBounds(149, 224, 61, 20);
		
		formatTextfieldPer = new JFormattedTextField(statFormatter);
		formatTextfieldPer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!formatTextfieldPer.getText().isEmpty()) {
					character.updateStat(StatName.PERCEPTION, convertToInt(formatTextfieldPer.getText()));
				}
			}
		});
		formatTextfieldPer.setColumns(10);
		formatTextfieldPer.setBounds(149, 199, 61, 20);
		
		frmCharacterCreator.getContentPane().add(formatTextfieldStr);
		frmCharacterCreator.getContentPane().add(formatTextfieldWil);
		frmCharacterCreator.getContentPane().add(formatTextfieldMagic);
		frmCharacterCreator.getContentPane().add(formatTextfieldAgl);
		frmCharacterCreator.getContentPane().add(formatTextfieldCon);
		frmCharacterCreator.getContentPane().add(formatTextfieldPer);	
	}
	
	// initializes the menu parts
	public void initializeMenu() {
		menuBar = new JMenuBar();
		frmCharacterCreator.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		menuChoiceNew = new JMenuItem("New");
		fileMenu.add(menuChoiceNew);
		
		menuChoiceLoad = new JMenuItem("Load");
		
		// loading function
		// TODO: Finish the loading function
		menuChoiceLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfc = new JFileChooser();
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Open an existing character file: ");
				int returnValue = jfc.showSaveDialog(null);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					System.out.println("Opened file: " + file.getAbsolutePath());
					
					Character tempCharacter = new Character();
					
					// start the file reading process
					try (BufferedReader br = new BufferedReader(new FileReader(file))) 
					{
						String line;
						
						ArrayList<CharPower> tempTalents = new ArrayList<CharPower>();
						ArrayList<CharPower> tempTechniques = new ArrayList<CharPower>();
						String squareBracketsPattern = "\\[\\w+\\]";
						String charPowerPattern = "[\\w\\s]+ \\(\\w\\): [\\w\\s]+";
						
						boolean squareBracketsFound = false;
						boolean talentHeaderFound = false;
						boolean techniqueHeaderFound = false;
						boolean otherDescHeaderFound = false;
						CharPower newPower = null;
						StringBuilder sb = new StringBuilder();
						while ((line = br.readLine()) != null) {
							
						       // process the line.
							if (!line.isEmpty()) {
								// check for talent/technique/other strings
								if (line.matches(squareBracketsPattern)) {
									talentHeaderFound = false;
									techniqueHeaderFound = false;
									otherDescHeaderFound = false;
									
									String categoryName = line.substring(1, line.length() - 1);
									
									if (categoryName.equals("TALENTS")) {
										talentHeaderFound = true;
									}
									else if (categoryName.equals("TECHNIQUES")) {
										techniqueHeaderFound = true;
									}
									else if (categoryName.equals("OTHER")) {
										otherDescHeaderFound = true;
									}
								}
								else if (line.matches(charPowerPattern)) {
									
									// for the previous character power
									// this part should be avoided on the first run
									if (sb.length() > 0) {
										if (talentHeaderFound || techniqueHeaderFound) {
											// append the character power object with the new strings that have been collected
											newPower.appendDescription(sb.toString());
											sb = new StringBuilder();
										}
									}
									
									// re-initialize the new power variable (or initialize it for the first time)
									newPower = new CharPower();
									
									if (talentHeaderFound) {
										newPower.setType(PowerType.TALENT);
										newPower.setFieldsByRawText(line);
										tempTalents.add(newPower);
									}
									else if (techniqueHeaderFound) {
										newPower.setType(PowerType.TECHNIQUE);
										newPower.setFieldsByRawText(line);
										tempTechniques.add(newPower);
									}
									
								}
								else {
									
									// append with the other description text
									if (otherDescHeaderFound) {
										sb.append(line + "\n");
									}
									// append with the talent or technique text
									else if (talentHeaderFound || techniqueHeaderFound) {
										sb.append("\n" + line + "\n");
									}
									// for all the other fields
									else {
										
										String[] parts = line.split(":");
										String fieldName = parts[0].trim();
										String fieldData = parts[1].trim();
										System.out.println("Name -> " + fieldName + " , Data -> " + fieldData);
										
										// switch statement for field names
										switch (fieldName) {
										case "NAME":
											tempCharacter.setName(fieldData);
											break;
										case "GENDER":
											tempCharacter.setGender(fieldData);
											break;
										case "AGE":
											tempCharacter.setAge(Integer.parseInt(fieldData));
											break;
										case "ALIGNMENT":
											tempCharacter.setAlignment(fieldData);
											break;
										case "DESCRIPTION":
											tempCharacter.setDescription(fieldData);
											break;
										case "SPECIES":
											tempCharacter.setSpecies(fieldData);
											break;
										case "STATS":
											// split the text into 6 parts and set them into the character object
											String[] statParts = fieldData.split(",");
											if (statParts.length != 6) {
												System.err.println("Could not split the stat string into 6 parts! Something has gone wrong!");
											}
											else {
												for (String statString: statParts) {
													// seperate text and number
													String[] singleStatParts = statString.split("(?<=\\D)(?=\\d)");
													tempCharacter.updateStatByString(singleStatParts[0].trim(), Integer.parseInt(singleStatParts[1]));
												}
											}
											break;
										default:
											// TODO: write better validation here
											throw new Exception();
										}
									}

								}
								
							}
							
						}
						tempCharacter.setOtherDesc(sb.toString());
						tempCharacter.setTalents(tempTalents);
						tempCharacter.setTechniques(tempTechniques);
						sb = new StringBuilder();
						
						// set the current character into all the interactable fields
						character = tempCharacter;
						cpgui.setInteractivesByCharacter(tempCharacter);
						reupdateTextFields();
						
					}
					catch (Exception e2) {
						e2.printStackTrace();	
					}
					
					
				}
				else {
					System.out.println("Open command cancelled by user.");
				}
				
			}
		});
		
		fileMenu.add(menuChoiceLoad);

		menuChoiceSave = new JMenuItem("Save");
		fileMenu.add(menuChoiceSave);

		// saving function
		menuChoiceSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String warningText = getSaveWarningText();
				
				// check for empty fields. If there are, show a warning message to the user
				if (!warningText.isEmpty()) {
					SaveWarningDialog swd = new SaveWarningDialog();
					swd.setWarningText(warningText);
					swd.run();
					//System.out.println(swd.getResult());
					
					// if the user clicks cancel, return to the regular gui and proceed no further
					if (!swd.getResult()) {
						swd.end();
						return;
					}
				}
				
				// get talent and technique list from the seperate window
				if (cpgui.getTalents().size() > 0) {
					character.setTalents(cpgui.getTalents());
				}
				
				if (cpgui.getTechniques().size() > 0) {
					character.setTechniques(cpgui.getTechniques());
				}

				// create the file chooser
				jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Choose a directory to save your file: ");
				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					
					// insert file stuff here later
					String filePath = jfc.getSelectedFile().getPath();
					String fileName = jfc.getSelectedFile().getName();
					
					// filename is empty
					if (fileName.isEmpty()) {
						fileName = "CHARACTER_" + character.getName() + txtFileSuffix;
						filePath += fileName;
					}
					else {
						
						// if the filename has less than 5 characters or doesn't contain the file suffix
						if (fileName.length() <= 4 || !fileName.substring(fileName.length() - 4).toLowerCase().equals(txtFileSuffix)) {
							filePath += txtFileSuffix;
						}
					}
					
					// write the file
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
		
		// debugging menu option
		menuChoicePrintDebug = new JMenuItem("Print Character to Console (DEBUG)");
		menuChoicePrintDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(character.toString());
			}
		});
		helpMenu.add(menuChoicePrintDebug);
		frmCharacterCreator.getContentPane().setLayout(null);
		
	}
	
	// re-updates all the text fields in the GUI based on the current Character object
	public void reupdateTextFields() {
		textfieldDescription.setText(character.getDescription());
		formatTextfieldAge.setText(String.valueOf(character.getAge()));
		textfieldOtherDesc.setText(character.getOtherDesc());
		textfieldName.setText(character.getName());
		textfieldAlignment.setText(character.getAlignment());
		textfieldSpecies.setText(character.getAlignment());
		formatTextfieldStr.setText(String.valueOf(character.getStatByString("STRENGTH")));
		formatTextfieldWil.setText(String.valueOf(character.getStatByString("WILLPOWER")));
		formatTextfieldMagic.setText(String.valueOf(character.getStatByString("MAGIC POTENTIAL")));
		formatTextfieldAgl.setText(String.valueOf(character.getStatByString("AGILITY")));
		formatTextfieldCon.setText(String.valueOf(character.getStatByString("CONSTITUTION")));
		formatTextfieldPer.setText(String.valueOf(character.getStatByString("PERCEPTION")));
	}
	
	
}
