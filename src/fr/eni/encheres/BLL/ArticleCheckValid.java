package fr.eni.encheres.BLL;

import java.time.LocalDate;

import fr.eni.encheres.exceptions.BusinessException;

public class ArticleCheckValid {
	
	
	public static LocalDate startDateAuctionCheck(LocalDate dateDebutEncheres, BusinessException businessException) throws
	BusinessException{
		
			LocalDate today = java.time.LocalDate.now();
			if(dateDebutEncheres.isBefore(today)) {
				System.out.println(today);
				businessException.addError(CodesResultatBLL.AUCTION_START_DATE_INVALID);
				}
			for (int errors:businessException.getListErrors()) {
				System.out.println(errors);
			}
			return dateDebutEncheres;
			
			}
		
	public static LocalDate endDateAuctionCheck(LocalDate dateDebutEncheres, LocalDate dateFinEncheres, BusinessException businessException) throws
	BusinessException{
			
			if(dateFinEncheres.isBefore(dateDebutEncheres)) {
				businessException.addError(CodesResultatBLL.AUCTION_END_DATE_INVALID);
				}
			return dateFinEncheres ;
			}
	
	

}
