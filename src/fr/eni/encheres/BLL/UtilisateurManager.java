package fr.eni.encheres.BLL;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.DAL.DAOFactory;
import fr.eni.encheres.DAL.UtilisateurDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class UtilisateurManager {

	private static UtilisateurManager instance;
	private UtilisateurDAO utilisateurDAO;

	private UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	/**
	 * Vérifie les données de connexion fournies par l'utilisateur
	 * 
	 * @param login
	 * @param password
	 * @return un Utilisateur si la connexion est valide, null sinon
	 * @throws BusinessException
	 */
	public Utilisateur checkValidConnection(String login, String password) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = null;

		// On vérifie si le user et le mot de passe ne sont pas null
		if (login == null || password == null) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_CONNEXION_NULL);
			throw businessException;
		}

		// Récupération d'un utilisateur en fonction du login
		// Si le login est un email
		if (login.contains("@")) {
			utilisateur = utilisateurDAO.selectByEmail(login);
			// Si le login est un pseudo
		} else {
			utilisateur = utilisateurDAO.selectByNickname(login);
		}

		// utilisateur = null : l'utilisateur n'a pas été trouvé dans la base
		if (utilisateur != null) {
			// Comparaison des mots de passe
			if (!utilisateur.getMotDePasse().equals(password)) {
				utilisateur = null;
			}
		}

		// si la connexion est correcte, check des valeurs de l'utilisateur
		if (utilisateur != null) {
			checkUser(utilisateur, businessException);
			if (businessException.hasErrors()) {
				throw businessException;
			}
		}

		return utilisateur;
	}

	/*
	 * Vérification de l'intégrité des données
	 */

	/*
	 * Vérification de l'intégralité des attributs d'un utilisateur
	 */
	public void checkUser(Utilisateur utilisateur, BusinessException businessException) {
		checkNoUtilisateur(utilisateur.getNoUtilisateur(), businessException);
		checkPseudo(utilisateur.getPseudo(), businessException);
		checkNom(utilisateur.getNom(), businessException);
		checkPrenom(utilisateur.getPrenom(), businessException);
		checkEmail(utilisateur.getEmail(), businessException);
		checkTelephone(utilisateur.getTelephone(), businessException);
		checkRue(utilisateur.getRue(), businessException);
		checkCodePostal(utilisateur.getCodePostal(), businessException);
		checkVille(utilisateur.getVille(), businessException);
		checkMotDePasse(utilisateur.getMotDePasse(), businessException);
		checkCredit(utilisateur.getCredit(), businessException);
		// TODO : ajouter administrateur
	}

	public void checkNoUtilisateur(int id, BusinessException businessException) {
		// noUtilisateur null
		if (id == 0) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_NO_UTILISATEUR_NOT_VALID);
		}
	}

	public void checkPseudo(String pseudo, BusinessException businessException) {
		boolean valid = true;
		// pseudo null
		if (pseudo == null) {
			valid = false;
			// pseudo dépasse 20 caractères
		} else if (pseudo.length() > 20) {
			valid = false;
			// pseudo contient des caractères non alphanumériques
		} else if (!pseudo.matches("^[a-zA-Z0-9]*$")) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_PSEUDO_NOT_VALID);
		}
	}

	public void checkPrenom(String prenom, BusinessException businessException) {
		boolean valid = true;
		// prenom null
		if (prenom == null) {
			valid = false;
			// prenom dépasse 50 caractères
		} else if (prenom.length() > 50) {
			valid = false;
			// prenom avec caractères autres que des lettres
		} else if (!prenom.matches("^[a-zA-Zàáâãäåçèéêëìíîïðòóôõöùúûüýÿ]*$")) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_PRENOM_NOT_VALID);
		}
	}

	public void checkNom(String nom, BusinessException businessException) {
		boolean valid = true;
		// nom null
		if (nom == null) {
			valid = false;
			// nom dépasse 50 caractères
		} else if (nom.length() > 50) {
			valid = false;
			// nom avec caractères autres que des lettres
		} else if (!nom.matches("^[a-zA-Zàáâãäåçèéêëìíîïðòóôõöùúûüýÿ]*$")) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_NOM_NOT_VALID);
		}
	}

	public void checkEmail(String email, BusinessException businessException) {
		boolean valid = true;
		// email null
		if (email == null) {
			valid = false;
			// email dépasse 50 caractères
		} else if (email.length() > 50) {
			valid = false;
			// email sans @
		} else if (!email.matches("^(.+)@(.+)$")) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_EMAIL_NOT_VALID);
		}
	}

	public void checkTelephone(String telephone, BusinessException businessException) {
		boolean valid = true;
		// telephone null
		if (telephone == null) {
			valid = false;
			// telephone ne fait pas exactement 10 caractères
		} else if (telephone.length() != 10) {
			valid = false;
			// telephone avec des caractères non numériques
		} else if (!telephone.matches("^[0-9]*$")) {
			valid = false;
			// telephone ne commence pas par un 0
		} else if (telephone.charAt(0) != '0') {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_TELEPHONE_NOT_VALID);
		}
	}

	public void checkRue(String rue, BusinessException businessException) {
		boolean valid = true;
		// rue null
		if (rue == null) {
			valid = false;
			// rue de plus de 80 caractères
		} else if (rue.length() > 80) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_RUE_NOT_VALID);
		}
	}

	public void checkCodePostal(String codePostal, BusinessException businessException) {
		boolean valid = true;
		// code postal null
		if (codePostal == null) {
			valid = false;
			// code postal ne fait pas exactement 5 caractères
		} else if (codePostal.length() != 5) {
			valid = false;
			// code postal avec des caractères non numériques
		} else if (!codePostal.matches("^[0-9]*$")) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_CODE_POSTAL_NOT_VALID);
		}
	}

	public void checkVille(String ville, BusinessException businessException) {
		boolean valid = true;
		// ville null
		if (ville == null) {
			valid = false;
			// ville de plus de 80 caractères
		} else if (ville.length() > 50) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_VILLE_NOT_VALID);
		}
	}

	public void checkMotDePasse(String motDePasse, BusinessException businessException) {
		boolean valid = true;
		// mot de passe null
		if (motDePasse == null) {
			valid = false;
			// mot de passe de plus de 30 caractères
		} else if (motDePasse.length() > 30) {
			valid = false;
			// mot de passe contient des espaces
		} else if (motDePasse.contains(" ")) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_MOT_DE_PASSE_NOT_VALID);
		}
	}

	public void checkCredit(int credit, BusinessException businessException) {
		// crédit négatif
		if (credit < 0) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_CREDIT_NOT_VALID);
		}
	}

	public void checkAdministrateur(boolean administrateur, BusinessException businessException) {
		// TODO : ajouter des checks si besoin
	}

}