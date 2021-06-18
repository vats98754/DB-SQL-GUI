package databasetestpackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JTextField;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddData extends JFrame {

	private JPanel contentPane;
	private JTextField txt;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txt5;
	int total = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddData frame = new AddData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Connection conn;
		try {

			// open a database connection

			String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

			conn = DriverManager.getConnection(url);

			// do the select

			String sql = "SELECT MAX(competitorid) FROM Competitors GROUP BY competitorid";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				total = rs.getInt("MAX(competitorid)");
			}

			conn.close();
			stmt.close();

		} catch (Exception e) {

		}

		loadGUI();
	}

	void loadGUI() {
		setBounds(100, 100, 615, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChoice = new JLabel("Choose a table to edit:");
		lblChoice.setBounds(41, 18, 154, 16);
		contentPane.add(lblChoice);

		JLabel lblCompetitorid = new JLabel("Competitorid");
		lblCompetitorid.setBounds(41, 73, 97, 16);
		contentPane.add(lblCompetitorid);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(290, 73, 97, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(41, 132, 199, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(290, 132, 97, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(41, 195, 97, 16);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(290, 195, 97, 16);
		contentPane.add(lblNewLabel_5);

		txt = new JTextField();
		txt.setBounds(41, 94, 219, 26);
		contentPane.add(txt);
		txt.setColumns(10);

		txt1 = new JTextField();
		txt1.setColumns(10);
		txt1.setBounds(290, 94, 219, 26);
		contentPane.add(txt1);

		txt2 = new JTextField();
		txt2.setColumns(10);
		txt2.setBounds(41, 160, 219, 26);
		contentPane.add(txt2);

		txt3 = new JTextField();
		txt3.setColumns(10);
		txt3.setBounds(290, 157, 219, 26);
		contentPane.add(txt3);

		txt4 = new JTextField();
		txt4.setColumns(10);
		txt4.setBounds(41, 218, 219, 26);
		contentPane.add(txt4);

		txt5 = new JTextField();
		txt5.setColumns(10);
		txt5.setBounds(290, 218, 219, 26);
		contentPane.add(txt5);

		lblNewLabel_1.setText("first name");
		lblNewLabel_2.setText("last name");
		lblNewLabel_3.setText("age");
		lblNewLabel_4.setText("gender");
		lblNewLabel_5.setText("school");
		txt.setText((total + 1) + "");
		txt.setEnabled(false);
		txt.setEditable(false);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Competitors", "Computing Competitions", "Math Competitions", "Winners" }));
		comboBox.setBounds(208, 14, 259, 27);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Competitors")) {
					lblCompetitorid.setText("competitorid");
					lblNewLabel_1.setText("first name");
					lblNewLabel_2.setText("last name");
					lblNewLabel_3.setText("age");
					lblNewLabel_4.setText("gender");
					lblNewLabel_5.setText("school");
					txt.setText((total + 1) + "");
					txt1.setText("");
					txt2.setText("");
					txt3.setText("");
					txt4.setText("");
					txt5.setText("");

					txt.setEnabled(false);
					txt.setEditable(false);
					txt1.setEnabled(true);
					txt1.setEditable(true);

				} else if (comboBox.getSelectedItem().toString().equals("Computing Competitions")) {
					lblCompetitorid.setText("competitorid");
					lblNewLabel_1.setText("competitionid");
					lblNewLabel_2.setText("competition name");
					lblNewLabel_3.setText("venue");
					lblNewLabel_4.setText("starttime");
					lblNewLabel_5.setText("endtime");
					txt.setText((total + 1) + "");
					txt1.setText("");
					txt2.setText("");
					txt3.setText("");
					txt4.setText("");
					txt5.setText("");

					txt.setEnabled(false);
					txt.setEditable(false);
					txt1.setEnabled(true);
					txt1.setEditable(true);

				} else if (comboBox.getSelectedItem().toString().equals("Math Competitions")) {
					lblCompetitorid.setText("competitorid");
					lblNewLabel_1.setText("competitionid");
					lblNewLabel_2.setText("competition name");
					lblNewLabel_3.setText("venue");
					lblNewLabel_4.setText("starttime");
					lblNewLabel_5.setText("endtime");
					txt.setText((total + 1) + "");
					txt1.setText("");
					txt2.setText("");
					txt3.setText("");
					txt4.setText("");
					txt5.setText("");

					txt.setEnabled(false);
					txt.setEditable(false);
					txt1.setEnabled(true);
					txt1.setEditable(true);

				} else if (comboBox.getSelectedItem().toString().equals("Winners")) {
					lblCompetitorid.setText("winnerid");
					lblNewLabel_1.setText("competitorid");
					lblNewLabel_2.setText("competitionid");
					lblNewLabel_3.setText("winnername");
					lblNewLabel_4.setText("winnerschool");
					lblNewLabel_5.setText("winnerage");
					txt.setText("");
					txt1.setText((total + 1) + "");
					txt2.setText("");
					txt3.setText("");
					txt4.setText("");
					txt5.setText("");

					txt1.setEnabled(false);
					txt1.setEditable(false);
					txt.setEnabled(true);
					txt.setEditable(true);
				}
			}
		});
		contentPane.add(comboBox);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add((String) comboBox.getSelectedItem());
			}
		});
		btnSave.setBackground(Color.BLUE);
		btnSave.setBounds(349, 334, 117, 29);
		contentPane.add(btnSave);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableFormDB d = new TableFormDB();
				d.setVisible(true);
				close();
			}
		});
		btnClose.setBounds(143, 334, 117, 29);
		contentPane.add(btnClose);

		JLabel lblWarning = new JLabel(
				"If you want to add to multiple tables, please click save before leaving this page.");
		lblWarning.setForeground(Color.RED);
		lblWarning.setBounds(41, 275, 511, 29);
		contentPane.add(lblWarning);
	}

	void add(String tableName) {

		Connection conn;

		try {
			String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();

			// do the select
			String sql = "";

			if (tableName.equals("Winners")) {
				sql = "INSERT INTO [" + tableName + "] VALUES (" + txt.getText() + ", " + (total + 1) + ", "
						+ txt2.getText() + ", '" + txt3.getText() + "', '" + txt4.getText() + "', '" + txt5.getText()
						+ "') ;";

			} else if (tableName.equals("Competitors")) {
				sql = "INSERT INTO [" + tableName + "] VALUES (" + (total + 1) + ", '" + txt1.getText() + "', '"
						+ txt2.getText() + "', " + txt3.getText() + ", '" + txt4.getText() + "', '" + txt5.getText()
						+ "') ;";

			} else {
				sql = "INSERT INTO [" + tableName + "] VALUES (" + txt1.getText() + ", " + (total + 1) + ", '"
						+ txt2.getText() + "', '" + txt3.getText() + "', '" + txt4.getText() + "', '" + txt5.getText()
						+ "') ;";
			}

			st.executeUpdate(sql);
			st.close();
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void close() {
		this.dispose();
	}
}
