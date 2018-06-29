package characterStuff;

import java.util.ArrayList;

public class Character {
	// a character consists of...
	// Name, Age, Alignment, Faction, Description, Stats (STR, MAG, CON, PER, WIL, AGL, LCK), Talents (Rank, info), Techniques (Rank, Info), Other
	
	String name;
	int age;
	String gender;
	String alignment;
	String species;
	String description;
	// TODO: Stat stuff here
	StatCollection stats = new StatCollection();
	ArrayList<CharPower> talents = new ArrayList<CharPower>();
	ArrayList<CharPower> techniques = new ArrayList<CharPower>();
	String otherDesc;
	
	// TODO: Flesh out this
	public Character() {
		name = "";
		age = -1;
		gender = "";
		alignment = "";
		species = "";
		description = "";
		otherDesc = "";
	}
	
	

	// TODO: Flesh out this
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NAME: " + name + "\n");
		sb.append("GENDER: " + gender + "\n");
		sb.append("AGE: " + age + "\n");
		sb.append("ALIGNMENT: " + alignment + "\n");
		sb.append("SPECIES: " + species + "\n");
		sb.append("DESCRIPTION: " + description  + "\n");
		sb.append("\n");
		
		sb.append("STATS: " + stats.toString()  + "\n\n");
		sb.append("[TALENTS]:\n");
		
		for (CharPower tal: talents) {
			if (tal.getType() == PowerType.TALENT) {
				sb.append(tal.toString() + "\n");
			}
		}
		sb.append("\n[TECHNIQUES]:\n");
		for (CharPower tech: techniques) {
			if (tech.getType() == PowerType.TECHNIQUE) {
				sb.append(tech.toString() + "\n");
			}
		}
		
		sb.append("\n[OTHER]:\n" + otherDesc + "\n");
		
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getGender() {
		return gender;
	}
	
	

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void updateStat(StatName sn, int val) {
		stats.updateStat(sn, val);
	}
	
	public void addTalent(String name, Rank rank, String description) {
		CharPower t = new CharPower(name, rank, description, PowerType.TALENT);
		talents.add(t);
	}
	
	public void addTechnique(String name, Rank rank, String description) {
		CharPower tech = new CharPower(name, rank, description, PowerType.TECHNIQUE);
		techniques.add(tech);
	}

	/*
	public StatCollection getStats() {
		return stats;
	}

	public void setStats(StatCollection stats) {
		this.stats = stats;
	}
	
	
	
	
	public ArrayList<Talent> getTalents() {
		return talents;
	}

	public void setTalents(ArrayList<Talent> talents) {
		this.talents = talents;
	}

	
	public ArrayList<Technique> getTechniques() {
		return techniques;
	}

	public void setTechniques(ArrayList<Technique> techniques) {
		this.techniques = techniques;
	}*/

	public String getOtherDesc() {
		return otherDesc;
	}

	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
	}
	
	
	
}
