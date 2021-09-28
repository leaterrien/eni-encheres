package fr.eni.encheres.DAL;

import java.util.List;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.exceptions.BusinessException;

public interface ArticleDAO {

	public Article selectById(int noArticle) throws BusinessException;

	public List<Article> selectAll() throws BusinessException;

	public Article insert(Article article) throws BusinessException;

	public Article update(Article article) throws BusinessException;

	public int delete(int noArticle) throws BusinessException;

}
