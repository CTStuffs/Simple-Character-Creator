package gui;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class GUIComponentRecord {


	JTextComponent component;
	String name;
	
	public GUIComponentRecord(JTextComponent component, String name) {
		this.component = component;
		this.name = name;
	}
	
	public JComponent getComponent() {
		return component;
	}

	public void setComponent(JTextComponent component) {
		this.component = component;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getText() {
		return component.getText();
	}
}	
