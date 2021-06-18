package databasetestpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Global {
	public static int competitorid;

	public static void delete() {

		Connection conn;

		try {
			String url = "jdbc:sqlite:/Users/anvayvats/Documents/sqlite_databases/Competitions.db";

			conn = DriverManager.getConnection(url);

			// do the select
		String sql = "DELETE FROM Competitors WHERE Competitors.competitorid = " + Global.competitorid + "; "
					+ "DELETE FROM [Math Competitions] WHERE [Math Competitions].competitorid = " + Global.competitorid + "; "
					+ "DELETE FROM [Computing Competitions] WHERE [Computing Competitions].competitorid = " + Global.competitorid + "; "
					+ "DELETE FROM Winners WHERE Winners.competitorid = " + Global.competitorid + "; ";

			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.execute();

			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
