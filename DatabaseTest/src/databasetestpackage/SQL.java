package databasetestpackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;

public class SQL extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public int question = 1;
	public String answer1 = "SELECT winnername, winnerage FROM Winners x WHERE"
			+ " (SELECT winnerage FROM Winners y WHERE (((y.winnerage = x.winnerage + 1) "
			+ "AND (y.competitionid = 3 OR y.competitionid = 4)) OR ((y.winnerage = x.winnerage - 1) "
			+ "AND (y.competitionid = 3 OR y.competitionid = 4))) AND "
			+ "((x.winnerschool = 'United World College of South East Asia') AND "
			+ "(y.winnerschool = 'United World College of South East Asia')))";
	
	public String answer2 = "SELECT winnername, winnerage, gender, winnerschool FROM "
			+ "Winners x JOIN Competitors y ON x.competitorid = y.competitorid WHERE "
			+ "(x.winnerage <= (SELECT MAX(Competitors.age) FROM Competitors)- 10)";
	
	JTextArea textArea = new JTextArea();
	JLabel lblError = new JLabel("");
	DefaultTableModel a1mdl = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SQL frame = new SQL();
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
	public SQL() {
		initGUI();
		formTable("Competitors");
	}

	void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(26, 364, 699, 186);
		contentPane.add(scrollPane);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JLabel lblTitle = new JLabel("Question:");
		lblTitle.setBounds(26, 11, 74, 16);
		contentPane.add(lblTitle);

		JButton btnSQL = new JButton("Execute the SQL statement");
		btnSQL.setBackground(Color.GREEN);
		btnSQL.setOpaque(true);
		btnSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exec();
			}
		});
		btnSQL.setBounds(109, 323, 237, 29);
		contentPane.add(btnSQL);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 176, 699, 135);
		contentPane.add(scrollPane_1);

		scrollPane_1.setViewportView(textArea);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				TableFormDB b = new TableFormDB();
				b.setVisible(true);
			}
		});
		btnClose.setOpaque(true);
		btnClose.setBackground(Color.RED);
		btnClose.setForeground(Color.BLACK);
		btnClose.setBounds(535, 323, 95, 29);
		contentPane.add(btnClose);

		JLabel lblQuestion = new JLabel(
				"<html><body> Find the three winners from UWCSEA who are 1 year apart in age from at least one of the others,"
						+ " with the older student(s) winning at least one math competition each and "
						+ "the other student(s) winning at least one computing competition. Output their "
						+ "winnername and winnerage </body></html>");

		lblQuestion.setBounds(26, 34, 699, 143);
		contentPane.add(lblQuestion);

		JButton btnSolution = new JButton("I want the solution");
		btnSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(answer1);
			}
		});
		btnSolution.setBounds(197, 6, 192, 29);
		contentPane.add(btnSolution);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);

		lblError.setForeground(Color.RED);
		lblError.setBounds(372, 328, 121, 16);
		contentPane.add(lblError);
		lblError.setVisible(false);

		JButton btnNewQ = new JButton("Prodigy example");
		btnNewQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (question == 1) {
					question = 2;
					lblQuestion.setText(
							"<html><body>Output the winnername, age, gender, and school for the set of competitors who have "
									+ "won in at least one math or one computing competition and whose ages are at least 10 below the"
									+ " maximum of the ages of all competitors.</body></html>");
					
					textArea.setText(answer2);
					
					btnNewQ.setText("Go back");
					btnSolution.setEnabled(false);

				} else if (question == 2) {
					question = 1;
					lblQuestion.setText(
							"<html><body>Find the three winners from UWCSEA who are 1 year apart in age from at least one of the others,"
									+ " with the older student(s) winning at least one math competition each and "
									+ "the other student(s) winning at least one computing competition. Output their "
									+ "winnername and winnerage </body></html>");

					btnNewQ.setText("Prodigy example");
					btnSolution.setEnabled(true);
				}
			}
		});

		btnNewQ.setBounds(480, 6, 192, 29);
		contentPane.add(btnNewQ);
	}

	void exec() {

		if (question == 1) {

			Connection conn4;

			try {

				DefaultTableModel mdl4 = new DefaultTableModel();

				// open a database connection

				String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

				conn4 = DriverManager.getConnection(url);

				// do the select
				
				String sql4 = textArea.getText();

				textArea.setText("SELECT winnername, winnerage FROM Winners x WHERE \n"
						+ " (SELECT winnerage FROM Winners y WHERE (((y.winnerage = x.winnerage + 1) \n"
						+ "AND (y.competitionid = 3 OR y.competitionid = 4)) OR ((y.winnerage = x.winnerage - 1) \n"
						+ "AND (y.competitionid = 3 OR y.competitionid = 4))) AND \n"
						+ "((x.winnerschool = 'United World College of South East Asia') AND \n"
						+ "(y.winnerschool = 'United World College of South East Asia')))");

				Statement stmt4 = conn4.createStatement();

				Vector columnTitles4 = new Vector();

				columnTitles4.add("winnername");
				columnTitles4.add("winnerage");

				mdl4.setColumnIdentifiers(columnTitles4);

				table.setModel(mdl4);
				ResultSet rs4 = stmt4.executeQuery(sql4);

				while (rs4.next()) {
					Vector row4 = new Vector();

					row4.add(rs4.getString("winnername"));
					row4.add(rs4.getInt("winnerage"));

					mdl4.addRow(row4);
				}

				if (answer1.equals(sql4)) {
					lblError.setForeground(Color.GREEN);
					lblError.setText("Correct");
					lblError.setVisible(true);
					
				} else {
					lblError.setText("Error");
					lblError.setVisible(true);
					lblError.setForeground(Color.RED);
				}
				
				rs4.close();

				conn4.close();

				stmt4.close();

			} catch (SQLException e) {
				lblError.setVisible(true);
				lblError.setForeground(Color.RED);
			}

		} else if (question == 2) {
			
			textArea.setText(answer2);
			
			Connection conn5;

			try {

				DefaultTableModel mdl5 = new DefaultTableModel();

				// open a database connection

				String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

				conn5 = DriverManager.getConnection(url);

				// do the select

				String sql5 = textArea.getText();
				
				textArea.setText("SELECT winnername, winnerage, gender, winnerschool FROM "
						+ "Winners x JOIN Competitors y \n ON x.competitorid = y.competitorid WHERE "
						+ "(x.winnerage <= (SELECT MAX(Competitors.age) \n FROM Competitors)- 10)");

				Statement stmt5 = conn5.createStatement();

				ResultSet rs5 = stmt5.executeQuery(sql5);

				Vector columnTitles5 = new Vector();

				columnTitles5.add("winnername");
				columnTitles5.add("age");
				columnTitles5.add("gender");
				columnTitles5.add("school");

				mdl5.setColumnIdentifiers(columnTitles5);
				table.setModel(mdl5);

				while (rs5.next()) {
					Vector row5 = new Vector();

					row5.add(rs5.getString("winnername"));
					row5.add(rs5.getInt("winnerage"));
					row5.add(rs5.getString("gender"));
					row5.add(rs5.getString("winnerschool"));

					mdl5.addRow(row5);
				}

				conn5.close();
				stmt5.close();

			} catch (SQLException e) {
				
			}
		}

	}

	void formTable(String tableName) {
		Connection conn;

		try {
			DefaultTableModel mdl = new DefaultTableModel();

			// open a database connection

			String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

			conn = DriverManager.getConnection(url);

			// do the select

			String sql = "SELECT * FROM Competitors JOIN [Computing Competitions] ON [Computing Competitions].competitorid = Competitors.competitorid JOIN [Math Competitions] ON [Math Competitions].competitorid = Competitors.competitorid";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			// put the items in the JList

			Vector columnTitles = new Vector();

			columnTitles.add("competitorid");
			columnTitles.add("first name");
			columnTitles.add("last name");
			columnTitles.add("age");
			columnTitles.add("gender");
			columnTitles.add("school");

			columnTitles.add("competitionid");
			columnTitles.add("competition name");
			columnTitles.add("venue");
			columnTitles.add("starttime");
			columnTitles.add("endtime");

			columnTitles.add("competitionid");
			columnTitles.add("competition name");
			columnTitles.add("venue");
			columnTitles.add("starttime");
			columnTitles.add("endtime");

			mdl.setColumnIdentifiers(columnTitles);

			table.setModel(mdl);

			while (rs.next()) {
				Vector row = new Vector();

				row.add(rs.getInt("competitorid"));
				row.add(rs.getString("first name"));
				row.add(rs.getString("last name"));
				row.add(rs.getInt("age"));
				row.add(rs.getString("gender"));
				row.add(rs.getString("school"));
				row.add(rs.getInt("competitionid"));
				row.add(rs.getString("competition name"));
				row.add(rs.getString("venue"));
				row.add(rs.getString("starttime"));
				row.add(rs.getString("endtime"));
				row.add(rs.getInt("competitorid"));
				row.add(rs.getString("competition name"));
				row.add(rs.getString("venue"));
				row.add(rs.getString("starttime"));
				row.add(rs.getString("endtime"));

				mdl.addRow(row);
			}

			// close the connection

			stmt.close();

			conn.close();

		} catch (Exception ex) {
			lblError.setVisible(true);
			lblError.setForeground(Color.RED);
		}
	}

	void close() {
		this.dispose();
	}
}
