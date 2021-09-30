package fr.eni.encheres.BLL;

import java.security.NoSuchAlgorithmException;

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

	public Utilisateur getUser(int noUtilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();

		// Vérification de la validité du noUtilisateur
		checkNoUtilisateur(noUtilisateur, businessException);
		if (businessException.hasErrors()) {
			throw businessException;
		}

		// Récupération de l'utilisateur et vérification des données reçues
		Utilisateur utilisateur = utilisateurDAO.selectById(noUtilisateur);
		if (utilisateur == null) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_GET_USER_RECEIVE_NULL);
		} else {
			checkUserFromDb(utilisateur, businessException);
		}

		// Throw de businessException si les données reçues ne sont pas correctes
		if (businessException.hasErrors()) {
			throw businessException;
		}

		return utilisateur;
	}

	/**
	 * Vérifie les données de connexion fournies par l'utilisateur
	 * 
	 * @param login
	 * @param password
	 * @return un Utilisateur si la connexion est valide, null sinon
	 * @throws BusinessException
	 * @throws NoSuchAlgorithmException
	 */
	public Utilisateur checkValidConnection(String login, String password) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = null;
		String newPass = null;

		// On vérifie si le user et le mot de passe ne sont pas null
		if (login == null || password == null) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_CONNEXION_NULL);
			throw businessException;
		}

		// On hash le mot de passe
		newPass = MdpHash.getHashPass(password, businessException);

		// Récupération d'un utilisateur en fonction du login
		// Si le login est un email
		if (login.contains("@")) {
			utilisateur = utilisateurDAO.selectByEmail(login);
			// Si le login est un pseudo
		} else {
			utilisateur = utilisateurDAO.selectByNickname(login);
		}

		// utilisateur = null : l'utilisateur n'a pas été trouvé dans la base
		if (utilisateur == null) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_CONNECTION_WRONG_LOGIN_OR_PASSWORD);
			throw businessException;

		} else {
			// Comparaison des mots de passe
			if (!utilisateur.getMotDePasse().equals(newPass)) {
				businessException.addError(CodesResultatBLL.UTILISATEUR_CONNECTION_WRONG_LOGIN_OR_PASSWORD);
				throw businessException;
			}
		}

		// si la connexion est correcte, check des valeurs de l'utilisateur
		checkUserFromDb(utilisateur, businessException);
		if (businessException.hasErrors()) {
			throw businessException;
		}

		return utilisateur;
	}
		//vérifie si le mot de passe renseigné correspond bien à celui en BDD
	public Utilisateur checkValidPassword(String password, int noUtilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = utilisateurDAO.selectById(noUtilisateur);
		String newPass = null;

		// On vérifie si le user et le mot de passe ne sont pas null
		if (password == null) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_CONNEXION_NULL);
			throw businessException;
		}

		// On hash le mot de passe
		newPass = MdpHash.getHashPass(password, businessException);

			// Comparaison des mots de passe
		if (!utilisateur.getMotDePasse().equals(newPass)) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_CONNECTION_WRONG_PASSWORD);
			throw businessException;
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
	
	public void checkUserFromDb(Utilisateur utilisateur, BusinessException businessException) {
		checkNoUtilisateur(utilisateur.getNoUtilisateur(), businessException);
		checkPseudo(utilisateur.getPseudo(), businessException);
		checkNom(utilisateur.getNom(), businessException);
		checkPrenom(utilisateur.getPrenom(), businessException);
		checkEmail(utilisateur.getEmail(), businessException);
		checkTelephone(utilisateur.getTelephone(), businessException);
		checkRue(utilisateur.getRue(), businessException);
		checkCodePostal(utilisateur.getCodePostal(), businessException);
		checkVille(utilisateur.getVille(), businessException);
		checkMotDePasseDb(utilisateur.getMotDePasse(), businessException);
		checkCredit(utilisateur.getCredit(), businessException);
		// TODO : ajouter administrateur
	}

	public Utilisateur checkNewUser(Utilisateur utilisateur, BusinessException businessException) {
		checkPseudo(utilisateur.getPseudo(), businessException);
		checkNom(utilisateur.getNom(), businessException);
		checkPrenom(utilisateur.getPrenom(), businessException);
		checkEmail(utilisateur.getEmail(), businessException);
		checkTelephone(utilisateur.getTelephone(), businessException);
		checkRue(utilisateur.getRue(), businessException);
		checkCodePostal(utilisateur.getCodePostal(), businessException);
		checkVille(utilisateur.getVille(), businessException);
		checkMotDePasse(utilisateur.getMotDePasse(), businessException);

		return utilisateur;
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
	
	public void checkMotDePasseDb(String motDePasse, BusinessException businessException) {
		boolean valid = true;
		// mot de passe null
		if (motDePasse == null) {
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

		//Vérifie si les deux mots de passe du formulaire sont similaires
	public void checkPasswordMatch(String password, String confirmPassword) throws BusinessException {
		boolean passwordMatch = false;
		BusinessException businessException = new BusinessException();
		if (password.equals(confirmPassword)) {
			passwordMatch = true;
		}
		if (passwordMatch == false) {
			businessException.addError(CodesResultatBLL.PASSWORDS_NOT_MATCHING);
		}
		if (businessException.hasErrors()) {
			throw businessException;
		}
	}
		//Vérifie en BDD si le pseudo choisi est déjà utilisé
	public void checkExistingPseudo(String pseudo) throws BusinessException {
		boolean valid = true;
		BusinessException businessException = new BusinessException();
		String pseudoDb;
		pseudoDb = utilisateurDAO.selectByNickname(pseudo).getPseudo();
		if (pseudoDb != null) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.PSEUDO_ALREADY_EXISTS);
		}
		if (businessException.hasErrors()) {
			throw businessException;
		}
	}
		//Vérifie en BDD si l'email choisi est déjà utilisé
	public void checkExistingEmail(String email) throws BusinessException {
		boolean valid = true;
		BusinessException businessException = new BusinessException();
		String emailDb;
		emailDb = utilisateurDAO.selectByEmail(email).getEmail();
		if (emailDb != null) {
			valid = false;
		}
		if (valid == false) {
			businessException.addError(CodesResultatBLL.EMAIL_ALREADY_EXISTS);
		}
		if (businessException.hasErrors()) {
			throw businessException;
		}
	}

	public Utilisateur addUtilisateur(Utilisateur utilisateur, String pseudo, String email, String password,
			String confirmPassword) throws BusinessException {
		BusinessException businessException = new BusinessException();
		checkExistingPseudo(pseudo);
		checkExistingEmail(email);
		checkPasswordMatch(password, confirmPassword);
		checkNewUser(utilisateur, businessException);
		if (!businessException.hasErrors()) {
			// hash password
			utilisateur.setMotDePasse(MdpHash.getHashPass(password, businessException));
			this.utilisateurDAO.insert(utilisateur);
		}
		return utilisateur;
	}
	
	public Utilisateur updateUtilisateur(Utilisateur utilisateur, String pseudo, String email, String newPassword, String confirmPassword) throws BusinessException{
		BusinessException businessException = new BusinessException();
		int noUtilisateur = utilisateur.getNoUtilisateur();
		if(pseudo != utilisateurDAO.selectById(noUtilisateur).getPseudo()) {
			checkExistingPseudo(pseudo);
		}
		if(email != utilisateurDAO.selectById(noUtilisateur).getEmail()) {
			checkExistingEmail(email);
		}
		if(!newPassword.isEmpty()) {
			checkValidPassword(utilisateur.getMotDePasse(), noUtilisateur);
			checkPasswordMatch(newPassword, confirmPassword);
			if (!businessException.hasErrors()) {
				// hash password
				utilisateur.setMotDePasse(MdpHash.getHashPass(newPassword, businessException));
				this.utilisateurDAO.updatePassword(utilisateur);				
			}
		}
		checkNewUser(utilisateur, businessException);
			
		this.utilisateurDAO.update(utilisateur);
		return utilisateur;
	}
	
	public void deleteUtilisateur(Utilisateur utilisateur,String password) throws BusinessException {
		BusinessException businessException = new BusinessException();
		int noUtilisateur = utilisateur.getNoUtilisateur();
		checkValidPassword(password, noUtilisateur);
		if(!businessException.hasErrors()) {
			this.utilisateurDAO.delete(noUtilisateur);
		}
	}

}
