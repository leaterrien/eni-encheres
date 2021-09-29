package fr.eni.encheres.BLL;

import java.time.LocalDate;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

public class ArticleManager {
	
	BusinessException businessException = new BusinessException();
	
	// On vérifie si la date de mise en vente n'est pas inférieure à la date du jour
	public LocalDate checkValidStartDate(LocalDate dateDebutEncheres) throws BusinessException {
		LocalDate debut = null;
		
		debut = DateNewSellValid.startDateAuctionCheck(dateDebutEncheres, businessException);
		return dateDebutEncheres;
	}
	
	// On vérifie si la date de mise en vente n'est pas inférieure à la date de fin de l'enchère
	public LocalDate checkValidEndDate(LocalDate dateDebutEncheres, LocalDate dateFinEncheres) throws BusinessException {
		LocalDate fin = null;
			
		fin = DateNewSellValid.endDateAuctionCheck(dateDebutEncheres, dateFinEncheres, businessException);
		return dateFinEncheres;
	}
	
	
	//methode add article
}
