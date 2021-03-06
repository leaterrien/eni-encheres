package fr.eni.encheres.DAL;

import java.util.List;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.exceptions.BusinessException;

public interface ArticleDAO {

	public Article selectById(int noArticle) throws BusinessException;

	public List<Article> selectAllDeconnected(int noCategorie, String rechercheNom) throws BusinessException;

	public List<Article> selectAllConnected(int noCategorie, String rechercheNom, boolean achatSelected,
			List<String> conditions, int noUtilisateur) throws BusinessException;

	public Article insert(Article article, int noCategorie) throws BusinessException;

	public Article update(Article article) throws BusinessException;

	public int delete(int noArticle) throws BusinessException;

}
