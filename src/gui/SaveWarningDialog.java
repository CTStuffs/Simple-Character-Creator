package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class SaveWarningDialog extends JDialog {
	JButton btnOk = new JButton("OK");
	JButton btnCancel = new JButton("Cancel");
	JLabel lblWarning = new JLabel("Warning!");
	private boolean result = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SaveWarningDialog dialog = new SaveWarningDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// starts the warning dialog
	public void run() {
		this.setModal(true);
		this.setVisible(true);
	}
	
	// ends the warning dialog
	public void end() {
		this.setVisible(false);
	}
	
	public void setWarningText(String text) {
		lblWarning.setText(text);
	}

	/**
	 * Create the dialog.
	 */
	public SaveWarningDialog() {
		setTitle("Warning!");
		setBounds(100, 100, 319, 193);
		
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = true;
				end();
			}
		});
		getContentPane().setLayout(new MigLayout("", "[111px][59px][113px]", "[118px][23px]"));
		getContentPane().add(btnOk, "cell 0 1,growx,aligny top");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = false;
				end();
			}
		});
		getContentPane().add(btnCancel, "cell 2 1,growx,aligny top");
		
		lblWarning.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblWarning, "cell 0 0 3 1,grow");
//		this.setModal(true);
//		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public boolean getResult() {
		return result;
	}
}
