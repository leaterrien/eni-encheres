package fr.eni.encheres.BLL;

import java.util.List;

import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.DAL.CategorieDAO;
import fr.eni.encheres.DAL.DAOFactory;
import fr.eni.encheres.exceptions.BusinessException;

public class CategorieManager {
	private static CategorieManager instance;
	private CategorieDAO categorieDAO;

	private CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}

	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}

	public List<Categorie> getCategories() throws BusinessException {
		BusinessException businessException = new BusinessException();
		List<Categorie> listeCategories = categorieDAO.selectAll();

		// TODO : check des données reçues

		// Throw de businessException si les données reçues ne sont pas correctes
		if (businessException.hasErrors()) {
			throw businessException;
		}

		return listeCategories;

	}
	
	public void checkCategorie(Categorie categorie, BusinessException businessException) {
		checkNoCategorie(categorie.getNoCategorie(), businessException);
		checkLibelle(categorie.getLibelle(), businessException);
	}
	
	public void checkNoCategorie(int noCategorie, BusinessException businessException) {
		if(noCategorie == 0) {
			businessException.addError(CodesResultatBLL.NO_CATEGORIE_NOT_VALID);
		}
	}
	
	public void checkLibelle(String libelle, BusinessException businessException) {
		if(libelle == null) {
			businessException.addError(CodesResultatBLL.LIBELLE_CATEGORIE_NOT_VALID);
		}
		
	}
	

}
