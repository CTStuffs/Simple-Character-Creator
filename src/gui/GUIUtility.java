package gui;

// utility class for important functions that can be shared across all gui files
public class GUIUtility {
	public int convertToInt(String text) {
		text = text.replaceAll(",", "");
		
		int number = Integer.parseInt(text);
		return number;
	}
}
