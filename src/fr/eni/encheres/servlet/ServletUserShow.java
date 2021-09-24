package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BLL.CodesResultatBLL;
import fr.eni.encheres.BLL.UtilisateurManager;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletUserShow
 */
@WebServlet("/Utilisateur/*")
public class ServletUserShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessException businessException = new BusinessException();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur = null;
		int noUtilisateur = 0;
		// Récupération de l'id de l'utilisateur depuis l'url
		try {
			noUtilisateur = Integer.parseInt(request.getPathInfo().substring(1));
		} catch (Exception e) {
			businessException.addError(CodesResultatServlets.USER_SHOW_INCORRECT_NO_UTILISATEUR);
			request.setAttribute("errors", businessException.getListErrors());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		// Récupération de l'utilisateur si le noUtilisateur a été correctement récupéré
		// Si l'utilisateur n'existe pas, renvoie une 404
		if (!businessException.hasErrors()) {
			try {
				utilisateur = UtilisateurManager.getInstance().getUser(noUtilisateur);
				request.setAttribute("utilisateur", utilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/user-show.jsp");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("errors", e.getListErrors());
				if (e.getListErrors().contains(CodesResultatBLL.UTILISATEUR_GET_USER_RECEIVE_NULL)) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
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
