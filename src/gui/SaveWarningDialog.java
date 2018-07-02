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
		setBounds(100, 100, 319, 214);
		getContentPane().setLayout(null);
		
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = true;
				end();
			}
		});
		
		btnOk.setBounds(32, 140, 89, 23);
		getContentPane().add(btnOk);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = false;
				end();
			}
		});
		
		btnCancel.setBounds(180, 140, 89, 23);
		getContentPane().add(btnCancel);
		
		lblWarning.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWarning.setBounds(10, 11, 283, 118);
		getContentPane().add(lblWarning);
//		this.setModal(true);
//		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public boolean getResult() {
		return result;
	}
}
