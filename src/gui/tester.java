package gui;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import models.CharPower;
import models.Character;
import models.PowerType;
import models.Rank;
import models.StatName;

// temporary class for testing code
// will likely be deleted later

public class tester {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Character c = new Character();
//		c.setAge(18);
//		c.setGender("Male");
//		c.setAlignment("Neutral Neutral");
//		c.setDescription("This is a test character.");
//		c.setSpecies("This is a test species");
//		c.setName("Mr. Test Name");
//		c.setOtherDesc("This is the part where miscellaneous stuff goes");
//		
//		c.addTalent("Test Talent", Rank.A, "This is a test talent");
//		c.addTalent("Test Talent 2", Rank.B, "This is another test talent");
//		
//		c.addTechnique("Test Technique", Rank.A, "This is a test technique");
//		c.addTechnique("Test Technique 2", Rank.B, "This is another test technique");
//		
//		c.updateStat(StatName.STRENGTH, 1);
//		c.updateStat(StatName.MAGIC_POTENTIAL, 1);
//		c.updateStat(StatName.AGILITY, 1);
//		c.updateStat(StatName.CONSTITUTION, 1);
//		c.updateStat(StatName.PERCEPTION, 1);
//		c.updateStat(StatName.WILLPOWER, 1);
//		
//		System.out.println(c.toString());
		Character tempCharacter = new Character();
		try (BufferedReader br = new BufferedReader(new FileReader("testchar.txt"))) 
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
						
						//squareBracketsFound = true;
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
						
						//System.out.println("Square pattern found in " + categoryName);
						
						// read line by line until something else is found
					}
					else if (line.matches(charPowerPattern)) {
						
						// for the previous character power
						// this part should be avoided on the first run
						if (sb.length() > 0) {
							if (talentHeaderFound || techniqueHeaderFound) {
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
							//tempCharacter.addTalent
						}
						else if (techniqueHeaderFound) {
							newPower.setType(PowerType.TECHNIQUE);
							newPower.setFieldsByRawText(line);
							tempTechniques.add(newPower);
						}
						
					}
					else {
						
						// add it to the other description / talent / technique
						if (otherDescHeaderFound) {
							sb.append(line + "\n");
						}
						else if (talentHeaderFound || techniqueHeaderFound) {
							sb.append("\n" + line + "\n");
						}
						// for all the other fields
						else {
							
							String[] parts = line.split(":");
							String fieldName = parts[0].trim();
							String fieldData = parts[1].trim();
							System.out.println("Name -> " + fieldName + " , Data -> " + fieldData);
							
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
								String[] statParts = fieldData.split(",");
								if (statParts.length != 6) {
									System.err.println("Could not split the stat string into 6 parts! Something has gone wrong!");
								}
								else {
									for (String statString: statParts) {
										String[] singleStatParts = statString.split("(?<=\\D)(?=\\d)");
										tempCharacter.updateStatByString(singleStatParts[0].trim(), Integer.parseInt(singleStatParts[1]));
									}
								}
								break;
							default:
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
		}
		catch (Exception e2) {
			e2.printStackTrace();	
		}
		System.out.println(tempCharacter.toString());
		
	}

}
