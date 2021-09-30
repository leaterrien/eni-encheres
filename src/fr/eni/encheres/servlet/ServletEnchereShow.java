package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BLL.UtilisateurManager;
import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletEnchereShow
 */
@WebServlet("/ServletEnchereShow")
public class ServletEnchereShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessException businessException = new BusinessException();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Article article = null;
		int noArticle = 0;
		try {
			noArticle = Integer.parseInt(request.getPathInfo().substring(1));
		} catch (Exception e) {
			businessException.addError(CodesResultatServlets.USER_SHOW_INCORRECT_NO_UTILISATEUR);
			request.setAttribute("errors", businessException.getListErrors());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
