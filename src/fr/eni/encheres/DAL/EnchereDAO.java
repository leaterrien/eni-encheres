package fr.eni.encheres.DAL;

import java.util.List;

import fr.eni.encheres.BO.Enchere;
import fr.eni.encheres.exceptions.BusinessException;

public interface EnchereDAO {

	public List<Enchere> selectAllByNoArticle(int noArticle) throws BusinessException;

	public Enchere insert(Enchere enchere, int noArticle) throws BusinessException;

	public Enchere update(Enchere enchere, int noArticle) throws BusinessException;

	public int delete(int noArticle) throws BusinessException;

}
