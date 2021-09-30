package fr.eni.encheres.BLL;

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
	
	public void checkNoArticle(int idArticle, BusinessException businessException) {
		//noarticle null
		if(idArticle == 0) {
			businessException.addError(CodesResultatBLL.ARTICLE_NO_ARTICLE_NOT_VALID);
		}
	}
	
	
}
