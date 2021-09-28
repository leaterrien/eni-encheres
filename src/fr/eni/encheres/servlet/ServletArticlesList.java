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
import fr.eni.encheres.BO.Article;
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
			List<Article> listeArticles = ArticleManager.getInstance().getArticles();
			request.setAttribute("listeArticles", listeArticles);
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
			request.setAttribute("dateFormat", dateFormat);
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
