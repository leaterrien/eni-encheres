package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BLL.UtilisateurManager;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletDeleteUser
 */
@WebServlet("/SuppressionUtilisateur")
public class ServletDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/user-delete.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String motDePasse;
		try {
			motDePasse = request.getParameter("password");
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			int noUtilisateur = utilisateur.getNoUtilisateur();
			utilisateur = UtilisateurManager.getInstance().getUser(noUtilisateur);
			utilisateur = new Utilisateur(noUtilisateur,utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getTelephone(),
					utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(), motDePasse, utilisateur.getCredit(), utilisateur.isAdministrateur());
			UtilisateurManager.getInstance().deleteUtilisateur(utilisateur, motDePasse);
		}
		catch (BusinessException e){
			e.printStackTrace();
			request.setAttribute("errors", e.getListErrors());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/user-delete.jsp");
			rd.forward(request, response);
		}
	}

}
