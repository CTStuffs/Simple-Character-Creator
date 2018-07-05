package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import javax.swing.ScrollPaneConstants;
import javax.swing.text.NumberFormatter;

import models.CharPower;
import models.Character;
import models.PowerType;

import javax.swing.JLabel;
import java.awt.Font;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import net.miginfocom.swing.MigLayout;

/**
 * 
 * Main file for the GUI.
 * 
 * **/

public class MainGUI extends JFrame {

	//private JFrame this;
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
	private JLabel lblOtherDesc;
	
	// combo box and buttons
	private JComboBox comboBoxGender;
	private JButton btnEditCharPowers;
	
	// text fields
	private JTextArea textfieldDescription;
	private JFormattedTextField formatTextfieldAge;
	private JTextArea textfieldOtherDesc;
	private JButton btnEditStats;

	// other components 
	private Font labelFont = new Font("Tahoma", Font.PLAIN, 14);
	private Font textFieldFont = new Font("Tahoma", Font.PLAIN, 11);
	GUIUtility util = new GUIUtility();
	
	// seperate gui for the character powers
	private CharPowerGUI powerGui = CharPowerGUI.getInstance();
	private StatGUI statGui = StatGUI.getInstance();
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
					window.setVisible(true);
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
	
	// program that initializes the gui components, including the frame
	private void initialize() {
		this.getContentPane().setFont(labelFont);
		this.setTitle("Character Creator");
		this.setBounds(100, 100, 488, 471);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializeMenu();
		initializeTextFields();
		initializeInteractables();
		initializeLabels();
		createGUIRecords();
		
		
	}
	
	// record all the text fields in the gui
	public void createGUIRecords() {
		guiComponents.add(new GUIComponentRecord(textfieldName, "Name"));
		guiComponents.add(new GUIComponentRecord(textfieldSpecies, "Species"));
		guiComponents.add(new GUIComponentRecord(textfieldAlignment, "Alignment"));
		guiComponents.add(new GUIComponentRecord(textfieldDescription, "Description"));
		guiComponents.add(new GUIComponentRecord(formatTextfieldAge, "Age"));
		guiComponents.add(new GUIComponentRecord(textfieldOtherDesc, "Other Description"));
	}


	// creates and initializes the labels on the primary gui
	public void initializeLabels() {
		lblName = new JLabel("Name:");
		lblName.setFont(labelFont);
		this.getContentPane().add(lblName, "cell 0 0,growx,aligny center");
		
		lblGender = new JLabel("Gender:");
		lblGender.setFont(labelFont);
		this.getContentPane().add(lblGender, "cell 0 1 3 1,alignx left,aligny top");
		
		lblAge = new JLabel("Age:");
		lblAge.setFont(labelFont);
		this.getContentPane().add(lblAge, "cell 0 3 3 1,grow");
		
		lblSpecies = new JLabel("Species:");
		lblSpecies.setFont(labelFont);
		this.getContentPane().add(lblSpecies, "cell 0 5 3 1,grow");
		
		lblAlignment = new JLabel("Alignment:");
		lblAlignment.setFont(labelFont);
		this.getContentPane().add(lblAlignment, "cell 0 7 3 1,grow");
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(labelFont);
		this.getContentPane().add(lblDescription, "cell 6 0,alignx left,growy");
		
		lblOtherDesc = new JLabel("Other: ");
		lblOtherDesc.setFont(labelFont);
		this.getContentPane().add(lblOtherDesc, "cell 0 10,growx,aligny top");
		
		
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
		
		// add the empty fields from the stat gui
		sb.append(statGui.getEmptyFieldsAsString());
		
		// do the same for the talents and technique arraylists
		if (powerGui.getTalents().size() <= 0) {
			sb.append("Talents, ");
		}
		if (powerGui.getTechniques().size() <= 0) {
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
		textfieldName.setColumns(10);
		
		textfieldSpecies = new JTextField();
		textfieldSpecies.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setSpecies(textfieldSpecies.getText());
			}
		});
		textfieldSpecies.setColumns(10);
		
		textfieldAlignment = new JTextField();
		textfieldAlignment.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setAlignment(textfieldAlignment.getText());
			}
		});
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
		textfieldDescription.setBorder(etchedBorder);
		
		JScrollPane descriptionTextScroll = new JScrollPane(textfieldDescription);
		descriptionTextScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		formatTextfieldAge = new JFormattedTextField(numFormatter);
		formatTextfieldAge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setAge(util.convertToInt(formatTextfieldAge.getText()));
			}
		});
		formatTextfieldAge.setValue(new Integer(0));

		textfieldOtherDesc = new JTextArea();
		textfieldOtherDesc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				character.setOtherDesc(textfieldOtherDesc.getText());
			}
		});
		textfieldOtherDesc.setBorder(etchedBorder);
		textfieldOtherDesc.setLineWrap(true);
		textfieldOtherDesc.setWrapStyleWord(true);
		
		JScrollPane otherDescTextScroll = new JScrollPane(textfieldOtherDesc);
		otherDescTextScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		getContentPane().setLayout(new MigLayout("", "[46px][7px][18px][11px][115.00px:85.00px][43px][215px]", "[23px][20px][16px][23px][2px][23px][2px][23px][23px][27.00px][97.00px]"));

		this.getContentPane().add(textfieldSpecies, "cell 4 5,growx,aligny top");
		this.getContentPane().add(textfieldName, "cell 4 0,growx,aligny bottom");
		this.getContentPane().add(textfieldAlignment, "cell 4 7,growx,aligny top");
		this.getContentPane().add(descriptionTextScroll, "cell 6 1 1 5,grow");
		this.getContentPane().add(formatTextfieldAge, "cell 4 3,growx,aligny top");
		
		JButton btnEditStats_1 = new JButton("Edit Stats");
		btnEditStats_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statGui.run();
			}
		});
		getContentPane().add(btnEditStats_1, "cell 2 8 4 1,growx,aligny center");
		this.getContentPane().add(otherDescTextScroll, "cell 2 10 5 1,grow");
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
	
		btnEditCharPowers = new JButton("Edit Talents and Techniques");
		btnEditCharPowers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				powerGui.run();
			}
		});
		
		this.getContentPane().add(comboBoxGender, "cell 4 1,growx,aligny top");
		this.getContentPane().add(btnEditCharPowers, "cell 2 9 4 1,growx,aligny center");
	}
	

	
	// initializes the menu parts
	public void initializeMenu() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		menuChoiceNew = new JMenuItem("New");
		menuChoiceNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				character = new Character();
				resetFields();
				statGui.resetFields();
				powerGui.resetFields();
				
			}
		});
		fileMenu.add(menuChoiceNew);
		
		menuChoiceLoad = new JMenuItem("Load");
		
		// loading function
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
						powerGui.setInteractivesByCharacter(tempCharacter);
						statGui.setFieldsByCharacter(tempCharacter);
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
				if (powerGui.getTalents().size() > 0) {
					character.setTalents(powerGui.getTalents());
				}
				
				if (powerGui.getTechniques().size() > 0) {
					character.setTechniques(powerGui.getTechniques());
				}
				
				character.setStats(statGui.getStats());

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
		
	}
	
	// re-updates all the text fields in the GUI based on the current Character object
	public void reupdateTextFields() {
		textfieldDescription.setText(character.getDescription());
		formatTextfieldAge.setText(String.valueOf(character.getAge()));
		textfieldOtherDesc.setText(character.getOtherDesc());
		textfieldName.setText(character.getName());
		textfieldAlignment.setText(character.getAlignment());
		textfieldSpecies.setText(character.getAlignment());
	}
	
	// resets all the fields in the gui (including the single combo box)
	public void resetFields() {
		comboBoxGender.setSelectedIndex(0);
		for (GUIComponentRecord record: guiComponents) {
			if (!record.getText().isEmpty()) {
				record.setText("");
			}
			// above code doesn't work on the age text field
			formatTextfieldAge.setText("0");
		}
	}
}
