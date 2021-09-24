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
	public static final int UTILISATEUR_RUE_NOT_VALID = 20007;

	// Check codePostal
	public static final int UTILISATEUR_CODE_POSTAL_NOT_VALID = 20008;

	// Check ville
	public static final int UTILISATEUR_VILLE_NOT_VALID = 20009;

	// Check motDePasse
	public static final int UTILISATEUR_MOT_DE_PASSE_NOT_VALID = 20010;

	// Check credit
	public static final int UTILISATEUR_CREDIT_NOT_VALID = 20011;

	// Check administrateur
	public static final int UTILISATEUR_ADMINISTRATEUR_NOT_VALID = 20012;
	
	// passwords qui ne sont pas similaires
	public static final int PASSWORDS_NOT_MATCHING = 20030;

	// Interactions DAL :

	// GetUser : utilisateur null
	public static final int UTILISATEUR_GET_USER_RECEIVE_NULL = 20020;

	// Connexion utilisateur : login ou mot de passe null
	public static final int UTILISATEUR_CONNEXION_NULL = 20040;

	// Connexion utilisateur : login inconnu
	public static final int UTILISATEUR_CONNECTION_WRONG_LOGIN = 20041;

	// Connexion utilisateur : mot de passe incorrect
	public static final int UTILISATEUR_CONNECTION_WRONG_PASSWORD = 20042;

}
