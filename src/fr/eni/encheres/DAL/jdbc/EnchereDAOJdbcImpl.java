package fr.eni.encheres.DAL.jdbc;

import java.util.List;

import fr.eni.encheres.BO.Enchere;
import fr.eni.encheres.DAL.EnchereDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String selectAllByNoArticle = "SELECT no_utilisateur, date_enchere, montant_enchere FROM ENCHERES WHERE no_article=?";
	private static final String insert = "";
	private static final String update = "";
	private static final String delete = "";

	@Override
	public List<Enchere> selectAllByNoArticle() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere insert(Enchere enchere, int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere update(Enchere enchere, int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int no_article) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
