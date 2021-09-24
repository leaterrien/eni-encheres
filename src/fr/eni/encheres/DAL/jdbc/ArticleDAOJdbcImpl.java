package fr.eni.encheres.DAL.jdbc;

import java.util.List;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.DAL.ArticleDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String selectAll = "";
	private static final String selectById = "";
	private static final String insert = "";
	private static final String update = "";
	private static final String delete = "";

	@Override
	public Article selectById(int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article insert(Article article) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article update(Article article) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
