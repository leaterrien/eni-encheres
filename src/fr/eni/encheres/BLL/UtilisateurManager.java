package fr.eni.encheres.BLL;

import fr.eni.encheres.exceptions.BusinessException;

public class UtilisateurManager {

	private UtilisateurManager instance;

	private UtilisateurManager() {
		// TODO : instancier DAO
	}

	public UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	public boolean checkValidConnection(String login, String password) throws BusinessException {
		BusinessException businessException = new BusinessException();
		boolean validConnexion = false;

		// On vérifie si le user et le mot de passe ne sont pas null
		if (login == null || password == null) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_CONNEXION_NULL);
			throw businessException;
		}

		// Vérification des informations de connexion
		// TODO : check pseudo ou email
		// TODO : interaction avec la DAL

		return validConnexion;
	}

}
