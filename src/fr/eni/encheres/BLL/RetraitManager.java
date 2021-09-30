package fr.eni.encheres.BLL;

import fr.eni.encheres.BO.Retrait;
import fr.eni.encheres.DAL.DAOFactory;
import fr.eni.encheres.DAL.RetraitDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class RetraitManager {
	private static RetraitManager instance;
	private RetraitDAO retraitDAO;
	
	private RetraitManager() {
		retraitDAO = DAOFactory.getretraitDAO();
	}
	
	private static RetraitManager getInstance() {
		if(instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	public void checkRetrait(Retrait retrait, BusinessException businessException) {

		checkRue(retrait.getRue(), businessException);
		checkCodePostal(retrait.getCodePostal(), businessException);
		checkVille(retrait.getVille(), businessException);
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
			businessException.addError(CodesResultatBLL.RUE_NOT_VALID);
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
			businessException.addError(CodesResultatBLL.CODE_POSTAL_NOT_VALID);
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
			businessException.addError(CodesResultatBLL.VILLE_NOT_VALID);
		}
	}
	
	
}
