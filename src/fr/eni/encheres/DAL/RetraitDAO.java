package fr.eni.encheres.DAL;

import fr.eni.encheres.BO.Retrait;
import fr.eni.encheres.exceptions.BusinessException;

public interface RetraitDAO {

	public Retrait selectByNoArticle(int noArticle) throws BusinessException;

	public Retrait insert(Retrait retrait, int noArticle) throws BusinessException;

	public Retrait update(Retrait retrait, int noArticle) throws BusinessException;

	public int delete(int noArticle) throws BusinessException;

}
