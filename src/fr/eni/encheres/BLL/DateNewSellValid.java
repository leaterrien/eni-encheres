package fr.eni.encheres.BLL;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import fr.eni.encheres.exceptions.BusinessException;

public class DateNewSellValid {
	
	public static LocalDate startDateAuctionCheck(LocalDate dateDebutEncheres, BusinessException businessException) throws
	BusinessException{
		
			SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
			LocalDate today = java.time.LocalDate.now();
			LocalDate startAuction = java.time.LocalDate.from(dateDebutEncheres);
			dateFormat.format(today);
			dateFormat.format(startAuction);
			if(dateDebutEncheres.isBefore(today)) {
				businessException.addError(CodesResultatBLL.AUCTION_START_DATE_INVALID);
				}
			return dateDebutEncheres;
			
			}
		
	public static LocalDate endDateAuctionCheck(LocalDate dateDebutEncheres, LocalDate dateFinEncheres, BusinessException businessException) throws
	BusinessException{
		
			SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
			LocalDate startAuction = java.time.LocalDate.from(dateDebutEncheres);
			LocalDate endAuction = java.time.LocalDate.from(dateFinEncheres);
			dateFormat.format(dateDebutEncheres);
			dateFormat.format(dateFinEncheres);
			if(dateFinEncheres.isBefore(dateDebutEncheres)) {
				businessException.addError(CodesResultatBLL.AUCTION_END_DATE_INVALID);
				}
			return dateFinEncheres ;
			}		
			
		
		

}
