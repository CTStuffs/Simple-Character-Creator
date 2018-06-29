package characterStuff;

import java.util.ArrayList;
import java.util.HashMap;

public class StatCollection {
	HashMap<StatName, Integer> stats = new HashMap<StatName, Integer>();
	
	public StatCollection() {
		
		// initialize stats with their names
		for (StatName sn: StatName.values()) {
			stats.put(sn, 0);
		}
	}
	
	
	public void updateStat(StatName sname, int val) {
		stats.put(sname, val);
		
	}
	
	public void resetStat(StatName sname) {
		stats.put(sname, 0);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// check that all stat names have been placed inside the hashmap before string conversion
		if (StatName.values().length == stats.keySet().size()) {
			for (StatName sn: StatName.values()) {
				sb.append(sn.toString() + " " + stats.get(sn));
				sb.append(", ");
			}
			
			String result = sb.toString();
			if (result != null && result.length() > 0) {
				result = result.substring(0, result.length() - 2);
			}
			
			return result;
		}
		else {
			return "STAT ERROR PLEASE CHECK toString CODE";
		}
		
		
	}
}
