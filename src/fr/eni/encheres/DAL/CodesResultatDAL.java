package fr.eni.encheres.DAL;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {

	// Utilisateur
	public static final int UTILISATEUR_NICKNAME_NULL = 10000;
	public static final int UTILISATEUR_EMAIL_NULL = 10001;
	public static final int UTILISATEUR_ID_NULL = 10002;
	public static final int UTILISATEUR_NULL = 10003;
	public static final int SELECT_NICKNAME_FAIL = 10010;
	public static final int SELECT_EMAIL_FAIL = 10011;
	//public static final int SELECT_PASSWORD_FAIL = 10012;
	public static final int SELECT_UTILISATEUR_FAIL = 10013;
	public static final int INSERT_UTILISATEUR_FAIL = 10020;
	public static final int UPDATE_UTILISATEUR_FAIL = 10021;
	public static final int DELETE_UTILISATEUR_FAIL = 10030;

	// Categorie
	public static final int CATEGORIE_SELECT_ALL_FAIL = 10040;
	public static final int CATEGORIE_SELECT_BY_ID_FAIL = 10041;

	// Retrait
	public static final int RETRAIT_SELECT_BY_NO_ARTICLE = 10050;
	public static final int RETRAIT_INSERT_FAIL = 10051;
	public static final int RETRAIT_UPDATE_FAIL = 10052;
	public static final int RETRAIT_DELETE_FAIL = 10053;
	public static final int RETRAIT_NULL = 10054;
	public static final int RETRAIT_NO_ARTICLE_NULL = 10055;

	// Enchere
	public static final int ENCHERE_SELECT_ALL_BY_NO_ARTICLE_FAIL = 10060;
	public static final int ENCHERE_INSERT_FAIL = 10061;
	public static final int ENCHERE_UPDATE_FAIL = 10062;
	public static final int ENCHERE_DELETE_FAIL = 10063;
	public static final int ENCHERE_NULL = 10064;
	public static final int ENCHERE_NO_ARTICLE_NULL = 10065;

	// Article
	public static final int ARTICLE_SELECT_ALL_FAIL = 10070;
	public static final int ARTICLE_SELECT_BY_ID_FAIL = 10071;
	public static final int ARTICLE_INSERT_FAIL = 10072;
	public static final int ARTICLE_UPDATE_FAIL = 10073;
	public static final int ARTICLE_DELETE_FAIL = 10074;
	public static final int ARTICLE_NULL = 10075;
}
