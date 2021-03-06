package models;

// represents a power a character has. Used for Talents and Techniques, which may have differences later on
public class CharPower {
	String name;
	Rank rank;
	String description;
	PowerType type;


	
	
	public CharPower (String _name, Rank _rank, String _description, PowerType _type) {
		name = _name;
		rank = _rank;
		description = _description;
		type = _type;
	}
	
	public CharPower() {
		// TODO Auto-generated constructor stub
		this.reset();
	}

	public String toString() {
		return name + " (" + rank.toString() + "): " + description;
	}
	

	// resets the fields of the power
	public void reset() {
		name = "";
		rank = Rank.E;
		description = "";
		type = PowerType.BLANK;

	}
	
	public void setFieldsByRawText(String rawText) {
		
		if (rawText.matches("[\\w\\s]+ \\(\\w\\): [\\w\\s]+")) {
			String[] parts = rawText.split("\\(");
			String powerName = parts[0];
			parts = parts[1].split("\\): ");
			String rankName = parts[0];
			String desc = parts[1];
			
			setName(powerName);
			setRankByString(rankName);
			setDescription(desc);
		}
		
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public void setRankByString(String newRank) {
		this.rank = Rank.valueOf(newRank.toUpperCase());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void appendDescription(String extra) {
		this.description = this.description += extra;
	}

	public void setType(PowerType type) {
		this.type = type;
	}
	
	public void setTypeByString(String newType) {
		this.type = PowerType.valueOf(newType.toUpperCase());
	}
	
	public PowerType getType() {
		return type;
	}
	
	
}
