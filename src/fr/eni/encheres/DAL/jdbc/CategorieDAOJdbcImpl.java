package fr.eni.encheres.DAL.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.DAL.CategorieDAO;
import fr.eni.encheres.DAL.CodesResultatDAL;
import fr.eni.encheres.DAL.ConnectionProvider;
import fr.eni.encheres.exceptions.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String selectAll = "SELECT no_categorie, libelle FROM categories";
	private static final String selectById = "SELECT no_categorie, libelle FROM categories WHERE no_categorie=?";

	@Override
	public Categorie selectById(int noCategorie) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Categorie categorie = null;
		try {
			cnx = ConnectionProvider.getConnection();
			statement = cnx.prepareStatement(selectById);
			statement.setInt(1, noCategorie);
			rs = statement.executeQuery();
			if (rs.next()) {
				categorie = categoryBuilder(rs);
			}
		} catch (SQLException e) {
			businessException.addError(CodesResultatDAL.CATEGORIE_SELECT_BY_ID_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				DAOJdbcTools.closeResultSet(rs);
				DAOJdbcTools.closeStatement(statement);
				DAOJdbcTools.closeConnection(cnx);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return categorie;
	}

	@Override
	public List<Categorie> selectAll() throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		try {
			cnx = ConnectionProvider.getConnection();
			statement = cnx.prepareStatement(selectAll);
			rs = statement.executeQuery();
			while (rs.next()) {
				Categorie categorie = categoryBuilder(rs);
				listeCategories.add(categorie);
			}
		} catch (SQLException e) {
			businessException.addError(CodesResultatDAL.CATEGORIE_SELECT_ALL_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				DAOJdbcTools.closeResultSet(rs);
				DAOJdbcTools.closeStatement(statement);
				DAOJdbcTools.closeConnection(cnx);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listeCategories;
	}

	/**
	 * Création d'une nouvelle instance de Categorie à partir d'un ResultSet
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Categorie categoryBuilder(ResultSet rs) throws SQLException {
		return new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
	}

}
