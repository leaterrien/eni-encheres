package fr.eni.encheres.DAL.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BO.Enchere;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.DAL.CodesResultatDAL;
import fr.eni.encheres.DAL.ConnectionProvider;
import fr.eni.encheres.DAL.EnchereDAO;
import fr.eni.encheres.DAL.UtilisateurDAOJdbcImpl;
import fr.eni.encheres.exceptions.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String selectAllByNoArticle = "SELECT u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, e.date_enchere, e.montant_enchere FROM ENCHERES as e INNER JOIN UTILISATEURS as u ON u.no_utilisateur = e.no_utilisateur WHERE no_article=?";
	private static final String insert = "INSERT INTO encheres (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
	private static final String update = "";
	private static final String delete = "";

	@Override
	public List<Enchere> selectAllByNoArticle(int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			// Requête SQL avec jointure sur la table utilisateurs
			statement = cnx.prepareStatement(selectAllByNoArticle);
			statement.setInt(1, noArticle);
			rs = statement.executeQuery();
			while (rs.next()) {
				Enchere enchere = enchereBuilder(rs);
				listeEncheres.add(enchere);
			}
		} catch (SQLException e) {
			businessException.addError(CodesResultatDAL.ENCHERE_SELECT_ALL_BY_NO_ARTICLE_FAIL);
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
		return listeEncheres;
	}

	@Override
	public Enchere insert(Enchere enchere, int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		try {
			cnx = ConnectionProvider.getConnection();
			statement = insertStatementBuilder(cnx, enchere, noArticle);
		} catch (SQLException e) {
			businessException.addError(CodesResultatDAL.ENCHERE_INSERT_FAIL);
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
		return enchere;
	}

	@Override
	public Enchere update(Enchere enchere, int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int no_article) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	public PreparedStatement insertStatementBuilder(Connection cnx, Enchere enchere, int noArticle)
			throws SQLException {
		PreparedStatement statement = cnx.prepareStatement(insert);
		statement.setInt(1, enchere.getEncherisseur().getNoUtilisateur());
		statement.setInt(2, noArticle);
		statement.setDate(3, Date.valueOf(enchere.getDate()));
		statement.setInt(4, enchere.getMontant());
		statement.executeUpdate();
		return statement;
	}

	/**
	 * Création d'une nouvelle instance de Enchere à partir d'un ResultSet
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Enchere enchereBuilder(ResultSet rs) throws SQLException {
		// Création de l'utilisateur
		Utilisateur encherisseur = UtilisateurDAOJdbcImpl.getUtilisateur(rs);
		// Création de l'enchère
		return new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"), encherisseur);
	}

}
