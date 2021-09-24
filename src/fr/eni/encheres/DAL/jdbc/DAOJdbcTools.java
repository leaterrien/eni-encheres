package fr.eni.encheres.DAL.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAOJdbcTools {

	/**
	 * Fermeture d'un statement
	 * 
	 * @param statement
	 * @return
	 * @throws SQLException
	 */
	public static Statement closeStatement(Statement statement) throws SQLException {
		if (statement != null) {
			statement.close();
		}
		statement = null;
		return statement;
	}

	/**
	 * Fermeture d'un ResultSet
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		rs = null;
		return rs;
	}

	/**
	 * Fermeture d'une Connection
	 * 
	 * @param cnx
	 * @return
	 * @throws SQLException
	 */
	public static Connection closeConnection(Connection cnx) throws SQLException {
		if (cnx != null) {
			cnx.close();
		}
		cnx = null;
		return cnx;
	}

}
