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
	public static final int SELECT_PASSWORD_FAIL = 10012;
	public static final int SELECT_UTILISATEUR_FAIL = 10013;
	public static final int INSERT_UTILISATEUR_FAIL = 10020;
	public static final int UPDATE_UTILISATEUR_FAIL = 10021;
	public static final int DELETE_UTILISATEUR_FAIL = 10030;

	// Categorie
	public static final int CATEGORIE_SELECT_ALL_FAIL = 10040;
	public static final int CATEGORIE_SELECT_BY_ID_FAIL = 10041;

	// Retrait
	public static final int RETRAIT_SELECT_ALL_FAIL = 10050;
	public static final int RETRAIT_SELECT_BY_ID_FAIL = 10051;
	public static final int RETRAIT_INSERT_FAIL = 10052;
	public static final int RETRAIT_UPDATE_FAIL = 10053;
	public static final int RETRAIT_DELETE_FAIL = 10054;
	public static final int RETRAIT_NULL = 10055;
	public static final int RETRAIT_NO_ARTICLE_NULL = 10056;

	// Enchere
	public static final int ENCHERE_SELECT_ALL_FAIL = 10060;
	public static final int ENCHERE_SELECT_BY_ID_FAIL = 10061;
	public static final int ENCHERE_INSERT_FAIL = 10062;
	public static final int ENCHERE_UPDATE_FAIL = 10063;
	public static final int ENCHERE_DELETE_FAIL = 10064;
	public static final int ENCHERE_NULL = 10065;
	public static final int ENCHERE_NO_ARTICLE_NULL = 10066;

}
