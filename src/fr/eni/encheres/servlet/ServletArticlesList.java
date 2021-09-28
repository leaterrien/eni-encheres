package fr.eni.encheres.servlet;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BLL.ArticleManager;
import fr.eni.encheres.BLL.CategorieManager;
import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletArticlesList
 */
@WebServlet("/Encheres")
public class ServletArticlesList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Récupération des paramètres de recherche
			int noCategorie = 0;
			if (request.getParameter("category") != null) {
				noCategorie = Integer.parseInt(request.getParameter("category"));
			}
			String rechercheNom = request.getParameter("barreRecherche");

			request.setAttribute("selectedCategory", noCategorie);
			request.setAttribute("nomRecherche", request.getParameter("barreRecherche"));

			// Récupération des articles
			List<Article> listeArticles = ArticleManager.getInstance().getArticlesDeconnected(noCategorie,
					rechercheNom);
			request.setAttribute("listeArticles", listeArticles);
			// Formatter pour l'affichage des dates
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
			request.setAttribute("dateFormat", dateFormat);
			// Récupération des catégories
			List<Categorie> listeCategories = CategorieManager.getInstance().getCategories();
			request.setAttribute("listeCategories", listeCategories);
		} catch (BusinessException e) {
			request.setAttribute("errors", e.getListErrors());
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/encheres-list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
