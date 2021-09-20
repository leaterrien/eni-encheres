package fr.eni.encheres.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private List<Integer> errors;

	public BusinessException() {
		this.errors = new ArrayList<Integer>();
	}

	/**
	 * Ajout d'une erreur à la liste des erreurs
	 * 
	 * @param errorCode
	 * @return l'erreur ajoutée
	 */
	public int addError(int errorCode) {
		if (!this.errors.contains(errorCode)) {
			this.errors.add(errorCode);
		}
		return errorCode;
	}

	/**
	 * Getter de la lite des erreurs
	 * 
	 * @return la liste des erreurs
	 */
	public List<Integer> getListErrors() {
		return errors;
	}

	/**
	 * Vérifie si la liste d'erreurs contient des erreurs
	 * 
	 * @return true = erreurs / false = pas d'erreur
	 */
	public boolean hasErrors() {
		return !(errors.size() == 0);
	}

}
