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
import fr.eni.encheres.exceptions.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String selectAll = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, a.no_vendeur, a.no_acheteur, a.no_categorie FROM articles_vendus as a";
	private static final String selectById = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, a.no_vendeur, a.no_acheteur, a.no_categorie FROM articles_vendus as a WHERE no_article=?";
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
	public List<Article> selectAllConnected(int noCategorie, String rechercheNom, boolean achatSelected,
			List<String> conditions, int noUtilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Article> listeArticles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			String sqlRequest = selectAll + selectConditionsConnectedBuilder(noCategorie, rechercheNom, achatSelected,
					conditions, noUtilisateur);
			statement = cnx.prepareStatement(sqlRequest);
			rs = statement.executeQuery();
			while (rs.next()) {
				Article article = selectElementsForArticle(rs);
				listeArticles.add(article);
			}
		} catch (SQLException e) {
			businessException.addError(CodesResultatDAL.ARTICLE_SELECT_ALL_CONNECTED_FAIL);
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
	public Article insert(Article article, int noCategorie) throws BusinessException {
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
			statement.setInt(9, noCategorie);
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
			DAOJdbcTools.closeResultSet(rs);
			DAOJdbcTools.closeStatement(statement);
			// Insertion du retrait
			if (article.getRetrait() != null) {
				RetraitDAOJdbcImpl retraitDAO = new RetraitDAOJdbcImpl();
				statement = retraitDAO.insertStatementBuilder(cnx, article.getRetrait(), article.getNoArticle());
				// retraitDAO.insert(article.getRetrait(), article.getNoArticle());
				DAOJdbcTools.closeStatement(statement);
			}
			// Insertion des enchères
			if (article.getListEncheres() != null && article.getListEncheres().size() > 0) {
				EnchereDAOJdbcImpl enchereDAO = new EnchereDAOJdbcImpl();
				for (Enchere enchere : article.getListEncheres()) {
					statement = enchereDAO.insertStatementBuilder(cnx, enchere, article.getNoArticle());
					DAOJdbcTools.closeStatement(statement);
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

	/**
	 * Création d'une nouvelle instance d'article
	 * 
	 * @param rs
	 * @param retrait
	 * @param listeEncheres
	 * @param categorie
	 * @param vendeur
	 * @param acheteur
	 * @return
	 * @throws SQLException
	 */
	private Article articleBuilder(ResultSet rs, Retrait retrait, List<Enchere> listeEncheres, Categorie categorie,
			Utilisateur vendeur, Utilisateur acheteur) throws SQLException {
		Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
				rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
				rs.getInt("prix_initial"), rs.getInt("prix_vente"), null, // EtatVente
				vendeur, acheteur, categorie, retrait, listeEncheres);

		return article;
	}

	/**
	 * Création d'un article à partir d'un ResultSet sur la table Article et
	 * récupération des éléments liés à l'article sur d'autres tables
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
		StringBuilder sb = new StringBuilder();
		// Condition sur la catégorie
		if (noCategorie != 0) {
			sb.append("a.no_categorie = " + noCategorie);
		}
		// Condition sur le nom de l'article
		if (rechercheNom != null && !rechercheNom.trim().equals("")) {
			sb = addAndToStringBuilder(sb);
			sb.append("a.nom_article LIKE '%" + rechercheNom + "%'");
		}
		sb = addAndToStringBuilder(sb);
		sb.append("a.date_fin_encheres >= GETDATE()");

		// Ajout du where à la condition si sb n'est pas vide
		String condition = null;
		if (sb != null) {
			condition = " WHERE " + sb.toString();
		}

		return condition;
	}

	/**
	 * Création des conditions de requête sur un SELECT
	 * 
	 * @param noCategorie
	 * @param rechercheNom
	 * @param achatSelected
	 * @param conditions
	 * @param noUtilisateur
	 * @return
	 */
	private String selectConditionsConnectedBuilder(int noCategorie, String rechercheNom, boolean achatSelected,
			List<String> conditions, int noUtilisateur) {
		StringBuilder sbAchatsVentes = new StringBuilder();
		StringBuilder sb = new StringBuilder();

		// Conditions : Achats et ventes
		sbAchatsVentes.append(addConditionsAchatsVentes(conditions, achatSelected, noUtilisateur));

		// Autres conditions :
		sb = addConditionCategorie(sb, noCategorie);
		sb = addConditionArticleName(sb, rechercheNom);

		// Ajout de sbAchatsVentes à sb
		if (!sbAchatsVentes.toString().equals("")) {
			if (!sb.toString().equals("")) {
				sb.insert(0, sbAchatsVentes + " AND ");
			} else {
				sb.insert(0, sbAchatsVentes);
			}
		}

		// Ajout du where à la condition si sb n'est pas vide
		if (!sb.toString().equals("")) {
			sb.insert(0, " WHERE ");
		}

		return sb.toString();
	}

	/**
	 * Création d'une condition de requête sur la catégorie de l'article
	 * 
	 * @param sb
	 * @param noCategorie
	 * @return
	 */
	private StringBuilder addConditionCategorie(StringBuilder sb, int noCategorie) {
		if (noCategorie != 0) {
			sb = addAndToStringBuilder(sb);
			sb.append("a.no_categorie = " + noCategorie);
		}
		return sb;
	}

	/**
	 * Création d'une condition de requête sur le nom de l'article
	 * 
	 * @param sb
	 * @param rechercheNom
	 * @return
	 */
	private StringBuilder addConditionArticleName(StringBuilder sb, String rechercheNom) {
		if (rechercheNom != null && !rechercheNom.trim().equals("")) {
			sb = addAndToStringBuilder(sb);
			sb.append("a.nom_article LIKE '%" + rechercheNom + "%'");
		}
		return sb;
	}

	/**
	 * Création d'une condition de requête sur les achats et ventes de l'utilisateur
	 * 
	 * @param conditions
	 * @param achatSelected
	 * @param noUtilisateur
	 * @return
	 */
	private StringBuilder addConditionsAchatsVentes(List<String> conditions, boolean achatSelected, int noUtilisateur) {
		StringBuilder sbAchatsVentes = new StringBuilder();
		// Achats
		if (achatSelected && conditions.size() > 0) {
			// Condition sur les enchères ouvertes
			sbAchatsVentes = addConditionEncheresOuvertes(sbAchatsVentes, conditions);
			// Condition sur les enchères en cours
			sbAchatsVentes = addConditionEncheresEnCours(sbAchatsVentes, conditions, noUtilisateur);
			// Condition sur les enchères remportées
			sbAchatsVentes = addConditionEncheresRemportees(sbAchatsVentes, conditions, noUtilisateur);
			// Condition générale sur les achats
			String achatCondition = "a.no_vendeur != " + noUtilisateur;
			if (!sbAchatsVentes.toString().equals("")) {
				sbAchatsVentes.insert(0, achatCondition + " AND (");
				sbAchatsVentes.append(")");
			} else {
				sbAchatsVentes.insert(0, achatCondition);
			}
			// Ventes
		} else if (!achatSelected && conditions.size() > 0) {
			// Condition sur les ventes en cours
			sbAchatsVentes = addConditionVentesEnCours(sbAchatsVentes, conditions, noUtilisateur);
			// Condition sur les ventes non débutées
			sbAchatsVentes = addConditionVentesNonDebutees(sbAchatsVentes, conditions, noUtilisateur);
			// Condition sur les ventes terminées
			sbAchatsVentes = addConditionVentesTerminees(sbAchatsVentes, conditions, noUtilisateur);
			// Condition générale sur les ventes
			String venteCondition = "a.no_vendeur = " + noUtilisateur;
			if (!sbAchatsVentes.toString().equals("")) {
				sbAchatsVentes.insert(0, venteCondition + " AND (");
				sbAchatsVentes.append(")");
			} else {
				sbAchatsVentes.insert(0, venteCondition);
			}
		}
		// Ajout de parenthèses sur les conditions de achat / vente
		if (!sbAchatsVentes.toString().equals("")) {
			sbAchatsVentes.insert(0, "(");
			sbAchatsVentes.append(")");
		}
		return sbAchatsVentes;
	}

	/**
	 * Création d'une condition de requête sur les enchères en cours
	 * 
	 * @param sbAchatsVentes
	 * @param conditions
	 * @return
	 */
	private StringBuilder addConditionEncheresOuvertes(StringBuilder sbAchatsVentes, List<String> conditions) {
		if (conditions.contains("encheresOuvertes")) {
			sbAchatsVentes = addOrToStringBuilder(sbAchatsVentes);
			sbAchatsVentes.append("a.date_fin_encheres >= GETDATE()");
		}
		return sbAchatsVentes;
	}

	/**
	 * Création d'une condition de requête sur les enchères en cours auxquelles
	 * l'utilisateur participe
	 * 
	 * @param sbAchatsVentes
	 * @param conditions
	 * @param noUtilisateur
	 * @return
	 */
	private StringBuilder addConditionEncheresEnCours(StringBuilder sbAchatsVentes, List<String> conditions,
			int noUtilisateur) {
		if (conditions.contains("encheresEnCours")) {
			if (!conditions.contains("encheresOuvertes")) {
				sbAchatsVentes = addOrToStringBuilder(sbAchatsVentes);
				sbAchatsVentes
						.append("a.no_article in (SELECT e.no_article FROM ENCHERES as e WHERE e.no_utilisateur = "
								+ noUtilisateur + " AND a.date_fin_encheres >= GETDATE())");
			}
		}
		return sbAchatsVentes;
	}

	/**
	 * Création d'une condition de requête sur les enchères remportées par
	 * l'utilisateur
	 * 
	 * @param sbAchatsVentes
	 * @param conditions
	 * @param noUtilisateur
	 * @return
	 */
	private StringBuilder addConditionEncheresRemportees(StringBuilder sbAchatsVentes, List<String> conditions,
			int noUtilisateur) {
		if (conditions.contains("encheresRemportees")) {
			sbAchatsVentes = addOrToStringBuilder(sbAchatsVentes);
			sbAchatsVentes.append("a.no_acheteur = " + noUtilisateur);
		}
		return sbAchatsVentes;
	}

	/**
	 * Création d'une condition de requête sur les ventes en cours de l'utilisateur
	 * 
	 * @param sbAchatsVentes
	 * @param conditions
	 * @param noUtilisateur
	 * @return
	 */
	private StringBuilder addConditionVentesEnCours(StringBuilder sbAchatsVentes, List<String> conditions,
			int noUtilisateur) {
		if (conditions.contains("ventesEnCours")) {
			sbAchatsVentes = addOrToStringBuilder(sbAchatsVentes);
			sbAchatsVentes.append("(a.date_debut_encheres <= GETDATE() AND a.date_fin_encheres >= GETDATE())");
		}
		return sbAchatsVentes;
	}

	/**
	 * Création d'une condition de requête sur les ventes créées par l'utilisateur
	 * mais non débutées
	 * 
	 * @param sbAchatsVentes
	 * @param conditions
	 * @param noUtilisateur
	 * @return
	 */
	private StringBuilder addConditionVentesNonDebutees(StringBuilder sbAchatsVentes, List<String> conditions,
			int noUtilisateur) {
		if (conditions.contains("ventesNonDebutees")) {
			sbAchatsVentes = addOrToStringBuilder(sbAchatsVentes);
			sbAchatsVentes.append("a.date_debut_encheres > GETDATE()");
		}
		return sbAchatsVentes;
	}

	/**
	 * Création d'une condition de requête sur les ventes terminées créées par
	 * l'utilisateur
	 * 
	 * @param sbAchatsVentes
	 * @param conditions
	 * @param noUtilisateur
	 * @return
	 */
	private StringBuilder addConditionVentesTerminees(StringBuilder sbAchatsVentes, List<String> conditions,
			int noUtilisateur) {
		if (conditions.contains("ventesTerminees")) {
			sbAchatsVentes = addOrToStringBuilder(sbAchatsVentes);
			sbAchatsVentes.append("a.date_fin_encheres < GETDATE()");
		}
		return sbAchatsVentes;
	}

	/**
	 * Ajout de "AND" à un StringBuffer si celui-ci n'est pas vide
	 * 
	 * @param sb
	 * @return
	 */
	private StringBuilder addAndToStringBuilder(StringBuilder sb) {
		if (sb != null && !sb.toString().trim().equals("")) {
			sb.append(" AND ");
		}
		return sb;
	}

	/**
	 * Ajout de "OR" à un StringBuffer si celui-ci n'est pas vide
	 * 
	 * @param sb
	 * @return
	 */
	private StringBuilder addOrToStringBuilder(StringBuilder sb) {
		if (sb != null && !sb.toString().trim().equals("")) {
			sb.append(" OR ");
		}
		return sb;
	}

}
