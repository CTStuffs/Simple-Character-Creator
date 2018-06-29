package gui;
import characterStuff.Character;
import characterStuff.CharPower;
import characterStuff.Rank;
import characterStuff.StatName;
public class tester {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Character c = new Character();
		c.setAge(18);
		c.setGender("Male");
		c.setAlignment("Neutral Neutral");
		c.setDescription("This is a test character.");
		c.setSpecies("This is a test species");
		c.setName("Mr. Test Name");
		c.setOtherDesc("This is the part where miscellaneous stuff goes");
		
		c.addTalent("Test Talent", Rank.A, "This is a test talent");
		c.addTalent("Test Talent 2", Rank.B, "This is another test talent");
		
		c.addTechnique("Test Technique", Rank.A, "This is a test technique");
		c.addTechnique("Test Technique 2", Rank.B, "This is another test technique");
		
		c.updateStat(StatName.STRENGTH, 1);
		c.updateStat(StatName.MAGIC_POTENTIAL, 1);
		c.updateStat(StatName.AGILITY, 1);
		c.updateStat(StatName.CONSTITUTION, 1);
		c.updateStat(StatName.PERCEPTION, 1);
		c.updateStat(StatName.WILLPOWER, 1);
		
		System.out.println(c.toString());
		
	}

}
