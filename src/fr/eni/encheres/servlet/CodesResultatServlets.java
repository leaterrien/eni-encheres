package fr.eni.encheres.servlet;

import fr.eni.encheres.BLL.CodesResultatBLL;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	
	//On vérifie si le user et le mot de passe ne sont pas null
	public static final int UTILISATEUR_CONNEXION_FORMULAIRE_NULL = 30000;
	
	// Connexion utilisateur : login ou mot de passe null
	public static final int UTILISATEUR_CONNEXION_RETOUR_BLL_NULL = 30001;
		

	public static final int USER_SHOW_INCORRECT_NO_UTILISATEUR = 30010;
	
	//no d'article incorrect
	public static final int ENCHERE_SHOW_INCORRECT_NO_ARTICLE = 30011;

}
