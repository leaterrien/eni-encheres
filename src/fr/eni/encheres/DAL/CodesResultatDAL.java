package fr.eni.encheres.DAL;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	public static final int UTILISATEUR_NICKNAME_NULL=10000;
	public static final int UTILISATEUR_EMAIL_NULL=10001;
	public static final int UTILISATEUR_ID_NULL=10002;
	public static final int UTILISATEUR_NULL=10003;
	public static final int SELECT_NICKNAME_FAIL=10010;
	public static final int SELECT_EMAIL_FAIL=10011;
//	public static final int SELECT_PASSWORD_FAIL=10012;
	public static final int SELECT_UTILISATEUR_FAIL=10013;
	public static final int INSERT_UTILISATEUR_FAIL=10020;
	public static final int UPDATE_UTILISATEUR_FAIL=10021;
	public static final int DELETE_UTILISATEUR_FAIL=10030;
}
