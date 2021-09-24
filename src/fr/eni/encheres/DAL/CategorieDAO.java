package fr.eni.encheres.DAL;

import java.util.List;

import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.exceptions.BusinessException;

public interface CategorieDAO {

	public Categorie selectById(int noCategorie) throws BusinessException;

	public List<Categorie> selectAll() throws BusinessException;

}
