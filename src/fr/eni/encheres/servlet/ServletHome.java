package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("")
public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO : récupération des articles à vendre

		// On vérifie si l'utilisateur est connecté
		if (request.getSession().getAttribute("utilisateur") == null) {
			// TODO : si il y a des articles à vendre, on reste sur la page d'accueil (avec
			// accès mode déconnecté)
			// Non connecté et aucun article à vendre : redirection vers la page de
			// connexion
			response.sendRedirect(request.getContextPath() + "/Connection");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
