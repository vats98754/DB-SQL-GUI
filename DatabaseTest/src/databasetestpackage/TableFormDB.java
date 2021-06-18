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

public class TableFormDB extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JComboBox comboBox;
	private static String item;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableFormDB frame = new TableFormDB();
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
	public TableFormDB() {
		initGUI();
		formTable("Competitors");
	}

	void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(table);
		scrollPane.setBounds(25, 78, 576, 284);
		contentPane.add(scrollPane);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				search(textField.getText());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				search(textField.getText());
			}
		});
		textField.setBounds(74, 374, 234, 29);
		contentPane.add(textField);
		textField.setColumns(10);

		btnAdd = new JButton("Add Record");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddData ad = new AddData();
				ad.setVisible(true);
				close();
			}
		});
		btnAdd.setBounds(604, 78, 117, 29);
		contentPane.add(btnAdd);

		btnEdit = new JButton("Edit Record");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Competitors")) {
					Global.competitorid = (int) table.getValueAt(table.getSelectedRow(), 0);
				} else
					Global.competitorid = (int) table.getValueAt(table.getSelectedRow(), 1);

				close();
				EditData data = new EditData();
				data.setVisible(true);
			}
		});
		btnEdit.setBounds(604, 156, 117, 29);
		contentPane.add(btnEdit);

		btnDelete = new JButton("Delete Record");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.competitorid = (int) table.getValueAt(table.getSelectedRow(), 0);
				Warning w = new Warning();
				w.setVisible(true);
				close();
			}
		});
		btnDelete.setBounds(604, 237, 117, 29);
		contentPane.add(btnDelete);

		JLabel lblTitle = new JLabel("The default table is below. Choose one of the following for more information:");
		lblTitle.setBounds(26, 11, 516, 16);
		contentPane.add(lblTitle);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(25, 380, 61, 16);
		contentPane.add(lblSearch);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Competitors", "Computing Competitions", "Math Competitions", "Winners" }));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Competitors")) {
					formTable("Competitors");
				} else if (comboBox.getSelectedItem().toString().equals("Computing Competitions")) {
					formTable("Computing Competitions");
				} else if (comboBox.getSelectedItem().toString().equals("Math Competitions")) {
					formTable("Math Competitions");
				} else if (comboBox.getSelectedItem().toString().equals("Winners")) {
					formTable("Winners");
				}
			}
		});
		comboBox.setBounds(99, 35, 146, 27);
		contentPane.add(comboBox);

		JLabel lblPickTable = new JLabel("Pick Table:");
		lblPickTable.setBounds(25, 39, 78, 16);
		contentPane.add(lblPickTable);

		JButton btnSQL = new JButton("Execute your own SQL statement");
		btnSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQL sql = new SQL();
				sql.setVisible(true);
				close();
			}
		});
		btnSQL.setBounds(25, 433, 278, 29);
		contentPane.add(btnSQL);
	}

	void formTable(String tableName) {
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

			String sql = "SELECT * FROM [Computing Competitions]";

			String sql1 = "SELECT * FROM Competitors";

			String sql2 = "SELECT * FROM Winners";

			String sql3 = "SELECT * FROM [Math Competitions]";

			Statement stmt = conn.createStatement();
			Statement stmt1 = conn1.createStatement();
			Statement stmt2 = conn2.createStatement();
			Statement stmt3 = conn3.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			ResultSet rs1 = stmt1.executeQuery(sql1);
			ResultSet rs2 = stmt2.executeQuery(sql2);
			ResultSet rs3 = stmt3.executeQuery(sql3);

			// put the items in the JList

			DefaultTableModel mdl = new DefaultTableModel();

			Vector columnTitles = new Vector();

			if (tableName == "Competitors" || tableName == null) {
				columnTitles.add("competitorid");
				columnTitles.add("first name");
				columnTitles.add("last name");
				columnTitles.add("age");
				columnTitles.add("gender");
				columnTitles.add("school");

			} else if (tableName.equals("Computing Competitions")) {
				columnTitles.add("competitionid");
				columnTitles.add("competitorid");
				columnTitles.add("competition name");
				columnTitles.add("venue");
				columnTitles.add("starttime");
				columnTitles.add("endtime");

			} else if (tableName.equals("Math Competitions")) {
				columnTitles.add("competitionid");
				columnTitles.add("competitorid");
				columnTitles.add("competition name");
				columnTitles.add("venue");
				columnTitles.add("starttime");
				columnTitles.add("endtime");

			} else if (tableName == "Winners") {
				columnTitles.add("winnerid");
				columnTitles.add("competitionid");
				columnTitles.add("competitorid");
				columnTitles.add("winnername");
				columnTitles.add("winnerschool");
				columnTitles.add("winnerage");
			}

			mdl.setColumnIdentifiers(columnTitles);

			table.setModel(mdl);

			if (tableName == "Competitors" || tableName == null) {
				while (rs1.next()) {
					Vector row = new Vector();
					row.add(rs1.getInt("competitorid"));
					row.add(rs1.getString("first name"));
					row.add(rs1.getString("last name"));
					row.add(rs1.getInt("age"));
					row.add(rs1.getString("gender"));
					row.add(rs1.getString("school"));
					mdl.addRow(row);
				}

			} else if (tableName == "Computing Competitions") {
				while (rs.next()) {
					Vector row = new Vector();
					row.add(rs.getInt("competitionid"));
					row.add(rs.getInt("competitorid"));
					row.add(rs.getString("competition name"));
					row.add(rs.getString("venue"));
					row.add(rs.getString("starttime"));
					row.add(rs.getString("endtime"));
					mdl.addRow(row);
				}

			} else if (tableName == "Math Competitions") {
				while (rs3.next()) {
					Vector row = new Vector();
					row.add(rs3.getInt("competitionid"));
					row.add(rs3.getInt("competitorid"));
					row.add(rs3.getString("competition name"));
					row.add(rs3.getString("venue"));
					row.add(rs3.getString("starttime"));
					row.add(rs3.getString("endtime"));
					mdl.addRow(row);
				}

			} else if (tableName == "Winners") {
				while (rs2.next()) {
					Vector row = new Vector();
					row.add(rs2.getInt("winnerid"));
					row.add(rs2.getInt("competitionid"));
					row.add(rs2.getInt("competitorid"));
					row.add(rs2.getString("winnername"));
					row.add(rs2.getString("winnerschool"));
					row.add(rs2.getString("winnerage"));
					mdl.addRow(row);
				}

			}

			// close the connection

			stmt.close();
			stmt1.close();
			stmt2.close();
			stmt3.close();

			conn.close();
			conn1.close();
			conn2.close();
			conn3.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
	}

	void search(String query) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		if (query.trim().length() == 0) {
			sorter.setRowFilter(null);
		} else {
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
		}
	}

	void close() {
		this.dispose();
	}
}
