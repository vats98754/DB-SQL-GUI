package databasetestpackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Warning extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Warning dialog = new Warning();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Warning() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel bruh = new JLabel();
		bruh.setForeground(Color.RED);
		bruh.setBounds(33, 46, 387, 45);
		contentPanel.add(bruh);
		
		tF = new JTextField();
		tF.setBounds(154, 134, 139, 37);
		contentPanel.add(tF);
		tF.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tF.getText().toLowerCase().equals("confirm")) {
							Global.delete();
							TableFormDB t = new TableFormDB();
							t.setVisible(true);
							close();
							TableFormDB d = new TableFormDB();
							d.setVisible(true);
						} else {
							close();
							TableFormDB d = new TableFormDB();
							d.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
						TableFormDB d = new TableFormDB();
						d.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		Connection conn;

		try {

			// open a database connection

			String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

			conn = DriverManager.getConnection(url);

			// do the select

			String sql = "SELECT [first name], [last name] FROM Competitors WHERE competitorid = " + Global.competitorid;

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			// put the items in the JList

			while (rs.next()) {
				String fn = (rs.getString("first name"));
				String ln = (rs.getString("last name"));
				
				bruh.setText("Please type 'CONFIRM' if you want to confirm the deletion of the competitor with name " + fn + " " + ln);
			}
			
			stmt.close();
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	void close() {
		this.dispose();
	}
}
