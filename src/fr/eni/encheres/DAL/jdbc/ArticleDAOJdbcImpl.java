package fr.eni.encheres.DAL.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.BO.Enchere;
import fr.eni.encheres.BO.Retrait;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.DAL.ArticleDAO;
import fr.eni.encheres.DAL.CategorieDAO;
import fr.eni.encheres.DAL.CodesResultatDAL;
import fr.eni.encheres.DAL.ConnectionProvider;
import fr.eni.encheres.DAL.EnchereDAO;
import fr.eni.encheres.DAL.RetraitDAO;
import fr.eni.encheres.DAL.UtilisateurDAO;
import fr.eni.encheres.DAL.UtilisateurDAOJdbcImpl;
import fr.eni.encheres.exceptions.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String selectAll = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_acheteur, no_categorie FROM articles_vendus";
	private static final String selectById = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_acheteur, no_categorie FROM articles_vendus WHERE no_article=?";
	private static final String insert = "INSERT INTO articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_acheteur, no_categorie) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String update = "";
	private static final String delete = "";

	@Override
	public Article selectById(int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Article article = null;
		try {
			cnx = ConnectionProvider.getConnection();
			statement = cnx.prepareStatement(selectById);
			statement.setInt(1, noArticle);
			rs = statement.executeQuery();
			if (rs.next()) {
				article = selectElementsForArticle(rs);
			}
		} catch (SQLException e) {
			businessException.addError(CodesResultatDAL.ARTICLE_SELECT_BY_ID_FAIL);
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

		return article;
	}

	@Override
	public List<Article> selectAllDeconnected(int noCategorie, String rechercheNom) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Article> listeArticles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			String sqlRequest = selectAll + selectConditionsBuilder(noCategorie, rechercheNom);
			statement = cnx.prepareStatement(sqlRequest);
			rs = statement.executeQuery();
			while (rs.next()) {
				Article article = selectElementsForArticle(rs);
				listeArticles.add(article);
			}
		} catch (SQLException e) {
			businessException.addError(CodesResultatDAL.ARTICLE_SELECT_ALL_DECONNECTED_FAIL);
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

		return listeArticles;
	}

	@Override
	public List<Article> selectAllConnected() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article insert(Article article) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			// Insertion de l'article
			statement = cnx.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, article.getNom());
			statement.setString(2, article.getDescription());
			statement.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			statement.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			statement.setInt(5, article.getMiseAPrix());
			statement.setInt(6, article.getPrixVente());
			statement.setInt(7, article.getVendeur().getNoUtilisateur());
			if (article.getAcheteur() != null) {
				statement.setInt(8, article.getAcheteur().getNoUtilisateur());
			} else {
				statement.setNull(8, Types.INTEGER);
			}
			statement.setInt(9, article.getCategorie().getNoCategorie());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
			// Insertion du retrait
			if (article.getRetrait() != null) {
				RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
				retraitDAO.insert(article.getRetrait(), article.getNoArticle());
			}
			// Insertion des enchères
			if (article.getListEncheres().size() > 0) {
				EnchereDAO enchereDAO = new EnchereDAOJdbcImpl();
				for (Enchere enchere : article.getListEncheres()) {
					enchereDAO.insert(enchere, article.getNoArticle());
				}
			}
			cnx.commit();
		} catch (SQLException e) {
			// Rollback de la transaction
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// Gestion de l'erreur
			businessException.addError(CodesResultatDAL.ARTICLE_INSERT_FAIL);
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
		return article;
	}

	@Override
	public Article update(Article article) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	private Article articleBuilder(ResultSet rs, Retrait retrait, List<Enchere> listeEncheres, Categorie categorie,
			Utilisateur vendeur, Utilisateur acheteur) throws SQLException {
		Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
				rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
				rs.getInt("prix_initial"), rs.getInt("prix_vente"), null, // EtatVente
				vendeur, acheteur, categorie, retrait, listeEncheres);

		return article;
	}

	/**
	 * Création d'un article à partir d'un ResultSet sur la table Article
	 * Récupération des éléments liés à l'article sur d'autres tables
	 * 
	 * @param rs
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
	 */
	private Article selectElementsForArticle(ResultSet rs) throws BusinessException, SQLException {
		Article article = null;

		int noArticle = rs.getInt("no_article");
		// Récupération du retrait associé à l'article
		Retrait retrait = null;
		RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
		retrait = retraitDAO.selectByNoArticle(noArticle);
		// Récupération des enchères liées à l'article
		List<Enchere> listeEncheres;
		EnchereDAO enchereDAO = new EnchereDAOJdbcImpl();
		listeEncheres = enchereDAO.selectAllByNoArticle(noArticle);
		// Récupération de la catégorie liée à l'article
		Categorie categorie = null;
		CategorieDAO categorieDAO = new CategorieDAOJdbcImpl();
		categorie = categorieDAO.selectById(rs.getInt("no_categorie"));
		// Récupération du vendeur lié à l'article
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
		Utilisateur vendeur = utilisateurDAO.selectById(rs.getInt("no_vendeur"));
		// Récupération de l'acheteur lié à l'article
		Utilisateur acheteur = null;
		if (rs.getInt("no_acheteur") != 0) {
			acheteur = utilisateurDAO.selectById(rs.getInt("no_acheteur"));
		}
		// Construction de l'article
		article = articleBuilder(rs, retrait, listeEncheres, categorie, vendeur, acheteur);

		return article;
	}

	/**
	 * Création des conditions de requête de select d'un article pour un utilisateur
	 * déconnecté
	 * 
	 * @param noCategorie
	 * @param rechercheNom
	 * @return
	 */
	private String selectConditionsBuilder(int noCategorie, String rechercheNom) {
		StringBuffer sb = new StringBuffer();
		// Condition sur la catégorie
		if (noCategorie != 0) {
			sb.append("no_categorie = " + noCategorie);
		}
		// Condition sur le nom de l'article
		if (rechercheNom != null && !rechercheNom.trim().equals("")) {
			sb = addAndToStringBuffer(sb);
			sb.append("nom_article LIKE '%" + rechercheNom + "%'");
		}
		sb = addAndToStringBuffer(sb);
		sb.append("date_fin_encheres > GETDATE()");

		// Ajout du where à la condition si sb n'est pas vide
		String condition = null;
		if (sb != null) {
			condition = " WHERE " + sb.toString();
		}

		return condition;
	}

	/**
	 * Ajout de "AND" à un StringBuffer si celui-ci n'est pas vide
	 * 
	 * @param sb
	 * @return
	 */
	private StringBuffer addAndToStringBuffer(StringBuffer sb) {
		if (sb != null && !sb.toString().trim().equals("")) {
			sb.append(" AND ");
		}
		return sb;
	}

}
