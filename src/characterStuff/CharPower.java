package characterStuff;

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
	
	public String toString() {
		return name + " (" + rank.toString() + "): " + description;
	}
	
	public PowerType getType() {
		return type;
	}
	
}
