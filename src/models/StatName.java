package models;

// enum representing a stat name

public enum StatName {
	STRENGTH("STRENGTH"), MAGIC_POTENTIAL("MAGIC POTENTIAL"), CONSTITUTION("CONSTITUTION"), PERCEPTION("PERCEPTION"), AGILITY("AGILITY"), WILLPOWER("WILLPOWER");
	
	 private final String name;
	
	StatName(String _name) {
		name = _name;
	}
	
	public String toString() {
		return name;
	}
	
	/*
	public String toString() {
		return this.toString().replace("_", " ");
	}
	
	public String toShortString() {
		return this.toString().substring(0, 3);
	}*/
}
