package fr.eni.encheres.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BLL.ArticleManager;
import fr.eni.encheres.BLL.CodesResultatBLL;
import fr.eni.encheres.BLL.UtilisateurManager;
import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.BO.Enchere;
import fr.eni.encheres.BO.Retrait;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletEnchereShow
 */
@WebServlet("/Enchere/*")
public class ServletEnchereShow extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException businessException = new BusinessException();
		request.setCharacterEncoding("UTF-8");
		
		Article article = null;
		int noArticle = 0;
		try {
			noArticle = Integer.parseInt(request.getPathInfo().substring(1));
		} catch (Exception e) {
			businessException.addError(CodesResultatServlets.ENCHERE_SHOW_INCORRECT_NO_ARTICLE);
			request.setAttribute("errors", businessException.getListErrors());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		if (!businessException.hasErrors()) {
			try {
			article = ArticleManager.getInstance().getArticle(noArticle);
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			Utilisateur vendeur = article.getVendeur();
			Categorie categorie = article.getCategorie();
			Retrait retrait = article.getRetrait();
			Enchere enchere = ArticleManager.getInstance().getMaxEnchere(article);
			
				request.setAttribute("article", article);
				request.setAttribute("vendeur", vendeur);
				request.setAttribute("categorie", categorie);
				request.setAttribute("retrait", retrait);
				request.setAttribute("enchere", enchere);
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/enchere-show.jsp");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("errors", e.getListErrors());
				if (e.getListErrors().contains(CodesResultatBLL.ARTICLE_GET_ARTICLE_RECEIVE_NULL)) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
