package project_kassenbon_hsin.model.sortiment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import project_kassenbon.model.sortiment.ISortimentSpeicher;
import project_kassenbon.model.sortiment.Produkt;


public class DB_Storage implements ISortimentSpeicher {

	private Connection db_conn;
//	private String db_server;
//	private String db_database;
//	private String db_host;

	public DB_Storage(String server, String host, String database, String user, String pw) {
		String jdbc = "jdbc:" + server + "://" + host + "/" + database;
		try {
			this.db_conn = DriverManager.getConnection(jdbc, user, pw);
			if (db_conn != null) {
				System.out.println("Connected to Database");
			} else {
				System.out.println("Connection failed");
			}
		} catch (SQLException e) {
			System.out.println("Exception catched in database constructor:");
			e.printStackTrace();
		}
	}
	
	public DB_Storage() {
		this("mariadb", "127.0.0.1:3306", "kassenbon_projekt", "root", null);
	}
	

	public void truncateSortiment() {
		String sql = "truncate sortiment";
		try {
			PreparedStatement statement = this.db_conn.prepareStatement(sql);
			int rowsAffected = statement.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println("Exception catched during sql query in truncateSortiment");
			e.printStackTrace();
		}
	}
	
	
	public int getNextID() {
		int counter = 1;
		Produkt produkt;
		ArrayList<Produkt> tmpProduktList = new ArrayList<Produkt>(Arrays.asList(getSortiment()));
		Iterator<Produkt> produktIterator = tmpProduktList.iterator();
		while (produktIterator.hasNext()) {
			produkt = (Produkt) produktIterator.next();
			if (produkt.getId() == counter) {
				counter++;
				produktIterator = tmpProduktList.iterator();
			}
		}
		return counter;

	}

	// Interface Methoden

	@Override
	public Produkt[] getSortiment() {
		String sql = "SELECT * FROM sortiment";
		List<Produkt> produktList = new ArrayList<>();
		PreparedStatement query;
		ResultSet resultSet;
		try {
			query = this.db_conn.prepareStatement(sql);
			resultSet = query.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String bezeichnung = resultSet.getString("bezeichnung");
				double preis = resultSet.getDouble("preis");
				produktList.add(new Produkt(bezeichnung, preis, id));
			}
		} catch (SQLException e) {
			System.out.println("Exception catched during sql query in getSortiment");
			e.printStackTrace();
		}
		return produktList.toArray(new Produkt[0]);
	}

	@Override
	public void sortimentSpeichern(Produkt[] produktArray) {
			truncateSortiment();
			for (Produkt produkt : produktArray) {
				produktHinzufuegen(produkt);
			}
	}

	@Override
	public void produktHinzufuegen(Produkt produkt) {
		String sql = "INSERT INTO sortiment (id, bezeichnung, preis) VALUES(?,?,?)";
		try {
			PreparedStatement statement = db_conn.prepareStatement(sql);
			statement.setInt(1, produkt.getId());
			statement.setString(2, produkt.getBezeichnung());
			statement.setDouble(3, produkt.getPreis());

			int rowsAffected = statement.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println("Exception catched during sql query - produktHinzufuegen");
			e.printStackTrace();
		}
	}

	@Override
	public void produktAktualisieren(Produkt produkt) {
		String sql = 
				"UPDATE sortiment "
				+ "SET bezeichnung = ?,"
				+ "preis = ? "
				+ "WHERE id = ?";
		
		try {
			PreparedStatement statement = db_conn.prepareStatement(sql);
			statement.setInt(3, produkt.getId());
			statement.setString(1, produkt.getBezeichnung());
			statement.setDouble(2, produkt.getPreis());

			int rowsAffected = statement.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println("Exception catched during sql query - produktAktualisieren");
			e.printStackTrace();
		}
	}

	@Override
	public void produktEntfernen(Produkt produkt) {
		String sql = "DELETE FROM sortiment "
				+ "WHERE id = ?";
		try {
			PreparedStatement statement = db_conn.prepareStatement(sql);
			statement.setInt(1, produkt.getId());
			int rowsAffected = statement.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println("Exception catched during sql query - produktEntfernen");
			e.printStackTrace();
		}
		
	}

	@Override
	public int getLastId() {
		ResultSet resultSet;
		int lastId = 0;
		String sql = "SELECT MAX(id) FROM sortiment";
		try {
			PreparedStatement statement = db_conn.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet);
				lastId = resultSet.getInt(1);
			}
			System.out.println("lastId " + lastId);
		} catch (SQLException e) {
			System.out.println("Exception catched during sql query - getLastId");
			e.printStackTrace();
		}
		return lastId + 1;
	}

//	@Override
//	public int getLastId() {
//		ArrayList<Produkt> tmpProduktList = new ArrayList<Produkt>(Arrays.asList(getSortiment()));
//		int lastId = 0;
//		for (Produkt produkt : tmpProduktList) {
//			if (lastId < produkt.getId()) {
//				lastId = produkt.getId();
//			}
//		}
//		return lastId + 1;
//	}

}
