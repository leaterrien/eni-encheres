package fr.eni.encheres.DAL.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BO.Retrait;
import fr.eni.encheres.DAL.CodesResultatDAL;
import fr.eni.encheres.DAL.ConnectionProvider;
import fr.eni.encheres.DAL.RetraitDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String selectByNoArticle = "SELECT rue, code_postal, ville FROM RETRAITS WHERE no_article=?";
	private static final String insert = "INSERT INTO retraits (no_article, rue, code_postal, ville) VALUES (?, ?, ?, ?)";
	private static final String update = "UPDATE retraits SET rue=?, code_postal=?, ville=? WHERE no_article=?";
	private static final String delete = "DELETE FROM retraits WHERE no_article=?";

	@Override
	public Retrait selectByNoArticle(int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Retrait retrait = null;
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (noArticle == 0) {
			businessException.addError(CodesResultatDAL.RETRAIT_NO_ARTICLE_NULL);
			throw businessException;
		} else {
			try {
				cnx = ConnectionProvider.getConnection();
				statement = cnx.prepareStatement(selectByNoArticle);
				statement.setInt(1, noArticle);
				rs = statement.executeQuery();
				if (rs.next()) {
					retrait = retraitBuilder(rs);
				}
			} catch (SQLException e) {
				businessException.addError(CodesResultatDAL.RETRAIT_SELECT_BY_NO_ARTICLE);
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
		}

		return retrait;
	}

	@Override
	public Retrait insert(Retrait retrait, int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		if (retrait == null) {
			businessException.addError(CodesResultatDAL.RETRAIT_NULL);
		}
		if (noArticle == 0) {
			businessException.addError(CodesResultatDAL.RETRAIT_NO_ARTICLE_NULL);
		}
		if (businessException.hasErrors()) {
			throw businessException;
		} else {
			try {
				cnx = ConnectionProvider.getConnection();
				statement = cnx.prepareStatement(insert);
				statement.setInt(1, noArticle);
				statement.setString(2, retrait.getRue());
				statement.setString(3, retrait.getCodePostal());
				statement.setString(4, retrait.getVille());
				statement.executeUpdate();
			} catch (SQLException e) {
				businessException.addError(CodesResultatDAL.RETRAIT_INSERT_FAIL);
				e.printStackTrace();
				throw businessException;
			} finally {
				try {
					DAOJdbcTools.closeStatement(statement);
					DAOJdbcTools.closeConnection(cnx);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return retrait;
	}

	@Override
	public Retrait update(Retrait retrait, int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		if (retrait == null) {
			businessException.addError(CodesResultatDAL.RETRAIT_NULL);
		}
		if (noArticle == 0) {
			businessException.addError(CodesResultatDAL.RETRAIT_NO_ARTICLE_NULL);
		}
		if (businessException.hasErrors()) {
			throw businessException;
		} else {
			try {
				cnx = ConnectionProvider.getConnection();
				statement = cnx.prepareStatement(update);
				statement.setString(1, retrait.getRue());
				statement.setString(2, retrait.getCodePostal());
				statement.setString(3, retrait.getVille());
				statement.setInt(4, noArticle);
				statement.executeUpdate();
			} catch (SQLException e) {
				businessException.addError(CodesResultatDAL.RETRAIT_UPDATE_FAIL);
				e.printStackTrace();
				throw businessException;
			} finally {
				try {
					DAOJdbcTools.closeStatement(statement);
					DAOJdbcTools.closeConnection(cnx);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return retrait;
	}

	@Override
	public int delete(int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		if (noArticle == 0) {
			businessException.addError(CodesResultatDAL.RETRAIT_NO_ARTICLE_NULL);
			throw businessException;
		} else {
			try {
				cnx = ConnectionProvider.getConnection();
				statement = cnx.prepareStatement(delete);
				statement.setInt(1, noArticle);
				statement.executeUpdate();
			} catch (SQLException e) {
				businessException.addError(CodesResultatDAL.RETRAIT_DELETE_FAIL);
				e.printStackTrace();
				throw businessException;
			} finally {
				try {
					DAOJdbcTools.closeStatement(statement);
					DAOJdbcTools.closeConnection(cnx);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return noArticle;
	}

	/**
	 * Création d'une nouvelle instance de retrait à partir d'un ResultSet
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Retrait retraitBuilder(ResultSet rs) throws SQLException {
		return new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
	}
}
