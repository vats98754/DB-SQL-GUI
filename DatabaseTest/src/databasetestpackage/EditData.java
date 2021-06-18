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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditData extends JFrame {

	private JPanel contentPane;
	private JTextField txtcompetitionid;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txt5;
	private JLabel lblerror;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditData frame = new EditData();
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
	public EditData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadGUI();
		load("Competitors");
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

		lblerror = new JLabel("");
		lblerror.setForeground(Color.BLUE);
		lblerror.setBounds(41, 305, 485, 16);
		contentPane.add(lblerror);

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

		txtcompetitionid = new JTextField();
		txtcompetitionid.setEnabled(false);
		txtcompetitionid.setEditable(false);
		txtcompetitionid.setBounds(41, 94, 219, 26);
		contentPane.add(txtcompetitionid);
		txtcompetitionid.setColumns(10);

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

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Competitors")) {
					lblCompetitorid.setText("competitorid");
					lblNewLabel_1.setText("first name");
					lblNewLabel_2.setText("last name");
					lblNewLabel_3.setText("age");
					lblNewLabel_4.setText("gender");
					lblNewLabel_5.setText("school");

					load("Competitors");

				} else if (comboBox.getSelectedItem().toString().equals("Computing Competitions")) {
					lblCompetitorid.setText("competitorid");
					lblNewLabel_1.setText("competitionid");
					lblNewLabel_2.setText("competition name");
					lblNewLabel_3.setText("venue");
					lblNewLabel_4.setText("starttime");
					lblNewLabel_5.setText("endtime");

					load("Computing Competitions");

				} else if (comboBox.getSelectedItem().toString().equals("Math Competitions")) {
					lblCompetitorid.setText("competitorid");
					lblNewLabel_1.setText("competitionid");
					lblNewLabel_2.setText("competition name");
					lblNewLabel_3.setText("venue");
					lblNewLabel_4.setText("starttime");
					lblNewLabel_5.setText("endtime");

					load("Math Competitions");

				} else if (comboBox.getSelectedItem().toString().equals("Winners")) {
					lblCompetitorid.setText("winnerid");
					lblNewLabel_1.setText("competitorid");
					lblNewLabel_2.setText("competitionid");
					lblNewLabel_3.setText("winnername");
					lblNewLabel_4.setText("winnerschool");
					lblNewLabel_5.setText("winnerage");

					load("Winners");
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Competitors", "Computing Competitions", "Math Competitions", "Winners" }));
		comboBox.setBounds(208, 14, 259, 27);
		contentPane.add(comboBox);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit((String) (comboBox.getSelectedItem()));
			}
		});
		btnSave.setBackground(Color.BLUE);
		btnSave.setBounds(349, 334, 117, 29);
		contentPane.add(btnSave);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnClose.setBounds(143, 334, 117, 29);
		contentPane.add(btnClose);

		JLabel lblWarning = new JLabel(
				"If you want to edit multiple tables, please click save before leaving this page.");
		lblWarning.setForeground(Color.RED);
		lblWarning.setBounds(41, 275, 511, 29);
		contentPane.add(lblWarning);
	}

	void edit(String tableName) {
		Connection conn;
		Connection conn1;
		Connection conn2;
		Connection conn3;

		try {

			// open a database connection

			String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

			conn = DriverManager.getConnection(url);
			conn1 = DriverManager.getConnection(url);
			conn2 = DriverManager.getConnection(url);
			conn3 = DriverManager.getConnection(url);

			// do the select

			String sql = "UPDATE Competitors SET [first name] = '" + txt1.getText() + "', [last name] = '"
					+ txt2.getText() + "', age = " + txt3.getText() + ", gender = '" + txt4.getText() + "', school = '"
					+ txt5.getText() + "' WHERE competitorid = " + Global.competitorid;

			String sql1 = "UPDATE [Computing Competitions] SET competitionid = " + txt1.getText()
					+ ", [competition name] = '" + txt2.getText() + "', venue = '" + txt3.getText() + "', starttime = '"
					+ txt4.getText() + "', endtime = '" + txt5.getText() + "' WHERE competitorid = "
					+ Global.competitorid;

			String sql2 = "UPDATE [Math Competitions] SET competitionid = " + txt1.getText()
					+ ", [competition name] = '" + txt2.getText() + "', venue = '" + txt3.getText() + "', starttime = '"
					+ txt4.getText() + "', endtime = '" + txt5.getText() + "' WHERE competitorid = "
					+ Global.competitorid;

			String sql3 = "UPDATE Winners \n" + "SET winnername = '" + txt3.getText() + "', winnerschool = '"
					+ txt4.getText() + "', winnerage = " + txt5.getText() + " WHERE competitorid = "
					+ Global.competitorid;

			if (tableName == "Competitors") {
				PreparedStatement st = conn.prepareStatement(sql);
				st.executeUpdate();

			} else if (tableName == "Computing Competitions") {
				PreparedStatement st1 = conn1.prepareStatement(sql1);
				st1.executeUpdate();

			} else if (tableName == "Math Competitions") {
				PreparedStatement st2 = conn2.prepareStatement(sql2);
				st2.executeUpdate();

			} else if (tableName == "Winners") {
				PreparedStatement st3 = conn3.prepareStatement(sql3);
				st3.executeUpdate();
			}

			conn.close();
			conn1.close();
			conn2.close();
			conn3.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void load(String tableName) {
		Connection conn;
		Connection conn2;

		try {

			// open a database connection

			String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

			conn = DriverManager.getConnection(url);
			conn2 = DriverManager.getConnection(url);

			// do the select

			String sql = "SELECT * \n" + "FROM " + "[" + tableName + "] \n" + " WHERE competitorid = "
					+ Global.competitorid;

			Statement stmt = conn.createStatement();
			Statement stmt1 = conn2.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			ResultSet rs1 = stmt1.executeQuery(sql);

			txt1.setEnabled(true);
			txt1.setEditable(true);

			if (tableName == "Competitors") {

				while (rs.next()) {

					txtcompetitionid.setText("" + rs.getInt("competitorid"));
					txt1.setText(rs.getString("first name"));
					txt2.setText(rs.getString("last name"));
					txt3.setText("" + rs.getInt("age"));
					txt4.setText(rs.getString("gender"));
					txt5.setText(rs.getString("school"));

				}
			} else if (tableName.equals("Computing Competitions") || tableName.equals("Math Competitions")) {

				while (rs.next()) {

					txtcompetitionid.setText("" + rs.getInt("competitorid"));
					txt1.setText(rs.getInt("competitionid") + "");
					txt2.setText(rs.getString("competition name"));
					txt3.setText(rs.getString("venue"));
					txt4.setText(rs.getString("starttime"));
					txt5.setText(rs.getString("endtime"));

				}
			} else if (tableName == "Winners") {
				txt1.setEnabled(false);
				txt1.setEditable(false);
				
				if (rs1.next() == false) {
					txtcompetitionid.setText("NULL");
					txt1.setText("NULL");
					txt2.setText("NULL");
					txt3.setText("NULL");
					txt4.setText("NULL");
					txt5.setText("NULL");
					lblerror.setText(
							"This candidate is not yet a winner. The admin will add them if they win.");
			      } else {

			        do {
			        	txtcompetitionid.setText("" + rs1.getInt("winnerid"));
						txt1.setText(rs1.getInt("competitorid") + "");
						txt2.setText(rs1.getInt("competitionid") + "");
						txt3.setText(rs1.getString("winnername"));
						txt4.setText(rs1.getString("winnerschool"));
						txt5.setText(rs1.getInt("winnerage") + "");
			        } while (rs1.next());
			      }
			}
			
			stmt.close();
			stmt1.close();

			conn.close();
			conn2.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void close() {
		this.dispose();
		TableFormDB bruh = new TableFormDB();
		bruh.setVisible(true);
	}
}
