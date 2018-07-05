package models;

// represents a stat (unused)

public class Stat {
	private StatName name;
	private int val;
	
	public Stat(StatName _name, int _val) {
		name = _name;
		val = _val;
	}
	
	public int getValue() {
		return val;
	}
	
}
