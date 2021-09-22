package fr.eni.encheres.test.bll;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BLL.CodesResultatBLL;
import fr.eni.encheres.BLL.UtilisateurManager;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

class UtilisateurManagerTest {

	private static UtilisateurManager utilisateurManager;
	private BusinessException businessException;
	private Utilisateur utilisateur;

	@BeforeAll
	static void getUtilisateurManager() {
		utilisateurManager = UtilisateurManager.getInstance();
	}

	@BeforeEach
	void createUser() {
		utilisateur = new Utilisateur(1, "floCountry", "Pays", "Florian", "florian.pays2021@campus-eni.fr",
				"0676662728", "78B avenue Albert Premier", "92500", "Rueil", "Pa$$w0rd", 150, false);
		businessException = new BusinessException();
	}

	@Test
	void checkNoUtilisateur_noNull_addErrorCode_UTILISATEUR_NO_UTILISATEUR_NOT_VALID() {
		utilisateur.setNoUtilisateur(0);
		utilisateurManager.checkNoUtilisateur(utilisateur.getNoUtilisateur(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_NO_UTILISATEUR_NOT_VALID));
	}

	@Test
	void checkPseudo_pseudoNull_addErrorCode_UTILISATEUR_PSEUDO_NOT_VALID() {
		utilisateur.setPseudo(null);
		utilisateurManager.checkPseudo(utilisateur.getPseudo(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_PSEUDO_NOT_VALID));
	}

	@Test
	void checkPseudo_pseudoTooLong_addErrorCode_UTILISATEUR_PSEUDO_NOT_VALID() {
		// Choix d'un pseudo de plus de 20 caractères
		utilisateur.setPseudo("pseudoDePlusDe20Caracteres");
		utilisateurManager.checkPseudo(utilisateur.getPseudo(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_PSEUDO_NOT_VALID));
	}

	@Test
	void checkPseudo_pseudoWithForbiddenCharacter_addErrorCode_UTILISATEUR_PSEUDO_NOT_VALID() {
		// Choix d'un pseudo avec des caractères autres qu'alphanumériques
		utilisateur.setPseudo("@pseudo1234%");
		utilisateurManager.checkPseudo(utilisateur.getPseudo(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_PSEUDO_NOT_VALID));
	}

	@Test
	void checkPrenom_prenomNull_addErrorCode_UTILISATEUR_PRENOM_NOT_VALID() {
		utilisateur.setPrenom(null);
		utilisateurManager.checkPrenom(utilisateur.getPrenom(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_PRENOM_NOT_VALID));
	}

	@Test
	void checkPrenom_prenomTooLong_addErrorCode_UTILISATEUR_PRENOM_NOT_VALID() {
		// Choix d'un nom de plus de 50 caractères
		utilisateur.setPrenom("prenomdeplusdecinquantecaracteresetprenomdeplusdecinquantecaracteres");
		utilisateurManager.checkPrenom(utilisateur.getPrenom(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_PRENOM_NOT_VALID));
	}

	@Test
	void checkPrenom_prenomWithForbiddenCharacter_addErrorCode_UTILISATEUR_PRENOM_NOT_VALID() {
		// Choix d'un nom avec des caractères non autorisés
		utilisateur.setPrenom("prenom123");
		utilisateurManager.checkPrenom(utilisateur.getPrenom(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_PRENOM_NOT_VALID));
	}

	@Test
	void checkPrenom_prenomWithAccentCharacter_dontAddErrorCode() {
		// Choix d'un nom avec des caractères non autorisés
		utilisateur.setPrenom("prénomavecdesaccentsàáâãäåçèéêëìíîïðòóôõöùúûüýÿ");
		utilisateurManager.checkPrenom(utilisateur.getPrenom(), businessException);
		assertTrue(!businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_PRENOM_NOT_VALID));
	}

	@Test
	void checkNom_nomNull_addErrorCode_UTILISATEUR_NOM_NOT_VALID() {
		utilisateur.setNom(null);
		utilisateurManager.checkNom(utilisateur.getNom(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_NOM_NOT_VALID));
	}

	@Test
	void checkNom_nomTooLong_addErrorCode_UTILISATEUR_NOM_NOT_VALID() {
		// Choix d'un nom de plus de 50 caractères
		utilisateur.setNom("nomdeplusdecinquantecaracteresetnomdeplusdecinquantecaracteres");
		utilisateurManager.checkNom(utilisateur.getNom(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_NOM_NOT_VALID));
	}

	@Test
	void checkNom_nomWithForbiddenCharacter_addErrorCode_UTILISATEUR_NOM_NOT_VALID() {
		// Choix d'un nom avec des caractères non autorisés
		utilisateur.setNom("nom123");
		utilisateurManager.checkNom(utilisateur.getNom(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_NOM_NOT_VALID));
	}

	@Test
	void checkNom_nomWithAccentCharacter_dontAddErrorCode() {
		// Choix d'un nom avec des caractères non autorisés
		utilisateur.setNom("nomavecdesaccentsàáâãäåçèéêëìíîïðòóôõöùúûüýÿ");
		utilisateurManager.checkNom(utilisateur.getNom(), businessException);
		assertTrue(!businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_NOM_NOT_VALID));
	}

	@Test
	void checkEmail_emailNull_addErrorCode_UTILISATEUR_EMAIL_NOT_VALID() {
		utilisateur.setEmail(null);
		utilisateurManager.checkEmail(utilisateur.getEmail(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_EMAIL_NOT_VALID));
	}

	@Test
	void checkEmail_emailTooLong_addErrorCode_UTILISATEUR_EMAIL_NOT_VALID() {
		// Choix d'un email de plus de 50 caractères
		utilisateur.setEmail("emaildeplusdecinquantecaracteres@emaildeplusdecinquantecaracteres.com");
		utilisateurManager.checkEmail(utilisateur.getEmail(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_EMAIL_NOT_VALID));
	}

	@Test
	void checkEmail_emailNotValidFormat_addErrorCode_UTILISATEUR_EMAIL_NOT_VALID() {
		// Choix d'un email qui ne respecte pas le format d'email : pas d'@
		utilisateur.setEmail("emailsansarobase.com");
		utilisateurManager.checkEmail(utilisateur.getEmail(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_EMAIL_NOT_VALID));
	}

	@Test
	void checkTelephone_telephoneNull_addErrorCode_UTILISATEUR_TELEPHONE_NOT_VALID() {
		utilisateur.setTelephone(null);
		utilisateurManager.checkTelephone(utilisateur.getTelephone(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_TELEPHONE_NOT_VALID));
	}

	@Test
	void checkTelephone_telephoneTooShort_addErrorCode_UTILISATEUR_TELEPHONE_NOT_VALID() {
		// Choix d'un telephone de moins de 10 caractères
		utilisateur.setTelephone("012345678");
		utilisateurManager.checkTelephone(utilisateur.getTelephone(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_TELEPHONE_NOT_VALID));
	}

	@Test
	void checkTelephone_telephoneTooLong_addErrorCode_UTILISATEUR_TELEPHONE_NOT_VALID() {
		// Choix d'un telephone de plus de 10 caractères
		utilisateur.setTelephone("01234567891");
		utilisateurManager.checkTelephone(utilisateur.getTelephone(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_TELEPHONE_NOT_VALID));
	}

	@Test
	void checkTelephone_telephoneWithoutFirst0Character_addErrorCode_UTILISATEUR_TELEPHONE_NOT_VALID() {
		// Choix d'un telephone sans 0 en premier caractère
		utilisateur.setTelephone("1234567891");
		utilisateurManager.checkTelephone(utilisateur.getTelephone(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_TELEPHONE_NOT_VALID));
	}

	@Test
	void checkTelephone_telephoneWithOtherCharactersThanNumbers_addErrorCode_UTILISATEUR_TELEPHONE_NOT_VALID() {
		// Choix d'un telephone avec des caractères non numeriques
		utilisateur.setTelephone("012345678a");
		utilisateurManager.checkTelephone(utilisateur.getTelephone(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_TELEPHONE_NOT_VALID));
	}

	@Test
	void checkRue_rueNull_addErrorCode_UTILISATEUR_RUE_NOT_VALID() {
		utilisateur.setRue(null);
		utilisateurManager.checkRue(utilisateur.getRue(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_RUE_NOT_VALID));
	}

	@Test
	void checkRue_rueTooLong_addErrorCode_UTILISATEUR_RUE_NOT_VALID() {
		// Choix d'une rue de plus de 80 caractères
		String rue = "rue de plus de quatre-vingt caractères";
		utilisateur.setRue(rue + rue + rue + rue);
		utilisateurManager.checkRue(utilisateur.getRue(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_RUE_NOT_VALID));
	}

	@Test
	void checkCodePostal_codePostalNull_addErrorCode_UTILISATEUR_CODE_POSTAL_NOT_VALID() {
		utilisateur.setCodePostal(null);
		utilisateurManager.checkCodePostal(utilisateur.getCodePostal(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_CODE_POSTAL_NOT_VALID));
	}

	@Test
	void checkCodePostal_codePostalTooShort_addErrorCode_UTILISATEUR_CODE_POSTAL_NOT_VALID() {
		// Choix d'un telephone de moins de 5 caractères
		utilisateur.setCodePostal("0123");
		utilisateurManager.checkCodePostal(utilisateur.getCodePostal(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_CODE_POSTAL_NOT_VALID));
	}

	@Test
	void checkCodePostal_codePostalTooLong_addErrorCode_UTILISATEUR_CODE_POSTAL_NOT_VALID() {
		// Choix d'un telephone de plus de 5 caractères
		utilisateur.setCodePostal("012345");
		utilisateurManager.checkCodePostal(utilisateur.getCodePostal(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_CODE_POSTAL_NOT_VALID));
	}

	@Test
	void checkCodePostal_codePostalWithOtherCharactersThanNumbers_addErrorCode_UTILISATEUR_TELEPHONE_NOT_VALID() {
		// Choix d'un telephone avec des caractères non numeriques
		utilisateur.setCodePostal("0123a");
		utilisateurManager.checkCodePostal(utilisateur.getCodePostal(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_CODE_POSTAL_NOT_VALID));
	}

	@Test
	void checkVille_villeNull_addErrorCode_UTILISATEUR_VILLE_NOT_VALID() {
		utilisateur.setVille(null);
		utilisateurManager.checkVille(utilisateur.getVille(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_VILLE_NOT_VALID));
	}

	@Test
	void checkVille_villeTooLong_addErrorCode_UTILISATEUR_VILLE_NOT_VALID() {
		// Choix d'une ville de plus de 50 caractères
		String ville = "rue de plus de cinquante caractères";
		utilisateur.setVille(ville + ville);
		utilisateurManager.checkVille(utilisateur.getVille(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_VILLE_NOT_VALID));
	}

	@Test
	void checkNotDePasse_motDePasseNull_addErrorCode_UTILISATEUR_MOT_DE_PASSE_NOT_VALID() {
		utilisateur.setMotDePasse(null);
		utilisateurManager.checkMotDePasse(utilisateur.getMotDePasse(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_MOT_DE_PASSE_NOT_VALID));
	}

	@Test
	void checkMotDePasse_motDePasseTooLong_addErrorCode_UTILISATEUR_MOT_DE_PASSE_NOT_VALID() {
		// Choix d'un mot de passe de plus de 30 caractères
		String mdp = "motDePasseDePlusDeCinquanteCaractères";
		utilisateur.setMotDePasse(mdp);
		utilisateurManager.checkMotDePasse(utilisateur.getMotDePasse(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_MOT_DE_PASSE_NOT_VALID));
	}

	@Test
	void checkMotDePasse_motDePasseWithSpaces_addErrorCode_UTILISATEUR_MOT_DE_PASSE_NOT_VALID() {
		// Choix d'un mot de passe avec des espaces
		String mdp = "mot de passe";
		utilisateur.setMotDePasse(mdp);
		utilisateurManager.checkMotDePasse(utilisateur.getMotDePasse(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_MOT_DE_PASSE_NOT_VALID));
	}

	@Test
	void checkCredit_creditNegative_addErrorCode_UTILISATEUR_CREDIT_NOT_VALID() {
		utilisateur.setCredit(-1);
		utilisateurManager.checkCredit(utilisateur.getCredit(), businessException);
		assertTrue(businessException.getListErrors().contains(CodesResultatBLL.UTILISATEUR_CREDIT_NOT_VALID));
	}

}
