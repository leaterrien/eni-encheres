package fr.eni.encheres.BLL;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {

	/*
	 * Erreurs Utilisateur
	 */

	// Check data :

	// Check noUtilisateur
	public static final int UTILISATEUR_NO_UTILISATEUR_NOT_VALID = 20001;

	// Check pseudo
	public static final int UTILISATEUR_PSEUDO_NOT_VALID = 20002;

	// Check prenom
	public static final int UTILISATEUR_PRENOM_NOT_VALID = 20003;

	// Check nom
	public static final int UTILISATEUR_NOM_NOT_VALID = 20004;

	// Check email
	public static final int UTILISATEUR_EMAIL_NOT_VALID = 20005;

	// Check telephone
	public static final int UTILISATEUR_TELEPHONE_NOT_VALID = 20006;

	// Check rue
	public static final int RUE_NOT_VALID = 20007;

	// Check codePostal
	public static final int CODE_POSTAL_NOT_VALID = 20008;

	// Check ville
	public static final int VILLE_NOT_VALID = 20009;

	// Check motDePasse
	public static final int UTILISATEUR_MOT_DE_PASSE_NOT_VALID = 20010;

	// Check credit
	public static final int UTILISATEUR_CREDIT_NOT_VALID = 20011;

	// Check administrateur
	public static final int UTILISATEUR_ADMINISTRATEUR_NOT_VALID = 20012;
	
	// passwords qui ne sont pas similaires
	public static final int PASSWORDS_NOT_MATCHING = 20030;
	

	/*
	 * Erreurs Articles
	 */

	//check noArticle
	public static final int ARTICLE_NO_ARTICLE_NOT_VALID = 20100;
	
	//check nom article
	public static final int ARTICLE_NAME_NOT_VALID = 20101;
	
	//check description article
	public static final int ARTICLE_DESCRIPTION_NOT_VALID = 20102;

	//check prix initial article
	public static final int ARTICLE_INITIAL_PRICE_NOT_VALID = 20103;
	
	//check prix final article
	public static final int ARTICLE_SALE_PRICE_NOT_VALID = 20104;
	
	//check noVendeur 
	public static final int NO_VENDEUR_NOT_VALID = 20105;
	
	/*
	 * Erreurs Catégories
	 */
	
	//check noCategorie 
	public static final int	NO_CATEGORIE_NOT_VALID = 20106;
	
	//check libelle 
	public static final int	LIBELLE_CATEGORIE_NOT_VALID = 20107;
	
	/*
	 * Erreurs Enchères
	 */
	//check date enchère
	public static final int	ENCHERE_DATE_NOT_VALID = 20108;
	
	// Interactions DAL :

	// GetUser : utilisateur null
	public static final int UTILISATEUR_GET_USER_RECEIVE_NULL = 20020;	
	
	//getArticle : article null
	public static final int ARTICLE_GET_ARTICLE_RECEIVE_NULL = 20021;

	//pseudo déjà en base
	public static final int PSEUDO_ALREADY_EXISTS = 20031;
	
	//email déjà en base
	public static final int EMAIL_ALREADY_EXISTS = 20032;


	// Connexion utilisateur : login ou mot de passe null
	public static final int UTILISATEUR_CONNEXION_NULL = 20040;

	// Connexion utilisateur : login inconnu ou mot de passe incorrect
	public static final int UTILISATEUR_CONNECTION_WRONG_LOGIN_OR_PASSWORD = 20041;

	// Connexion utilisateur : mot de passe incorrect
	public static final int UTILISATEUR_CONNECTION_WRONG_PASSWORD = 20042;
	
	// Hash du password : echec
	public static final int UTILISATEUR_PASSWORD_FAIL_HASHED = 20050;
	
	// Date de mise en vente antérieure à la date d'aujourd'hui
	public static final int AUCTION_START_DATE_INVALID = 20160;
	
	// Date de fin de vente antérieure à la date de mise en vente
	public static final int AUCTION_END_DATE_INVALID = 20161;
	
	

}
