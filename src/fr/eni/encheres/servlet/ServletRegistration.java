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
 * Servlet implementation class ServletRegistration
 */
@WebServlet("/Inscription")
public class ServletRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessException businessException;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/registration.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pseudo;
		String email;
		String motDePasse;
		String confirmerMotDePasse;
		String nom;
		String prenom;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		Utilisateur utilisateur = null;

		try {
			pseudo = request.getParameter("username");
			email = request.getParameter("email");
			motDePasse = request.getParameter("password");
			confirmerMotDePasse = request.getParameter("confirm_password");
			nom = request.getParameter("last_name");
			prenom = request.getParameter("first_name");
			telephone = request.getParameter("phone");
			rue = request.getParameter("street");
			codePostal = request.getParameter("postcode");
			ville = request.getParameter("city");

			utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			UtilisateurManager.getInstance().addUtilisateur(utilisateur, pseudo, email, motDePasse,
					confirmerMotDePasse);
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect(request.getContextPath());

		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("errors", e.getListErrors());
			request.setAttribute("creationUtilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/registration.jsp");
			rd.forward(request, response);
			//
			// if (e.getListErrors().contains(CodesResultatBLL.PSEUDO_ALREADY_EXISTS)) {
			// System.out.println("pseudo déjà utilisé");
			// }
			// if (e.getListErrors().contains(CodesResultatBLL.EMAIL_ALREADY_EXISTS)) {
			// System.out.println("email déjà utilisé");
			// }
			// if (e.getListErrors().contains(CodesResultatBLL.PASSWORDS_NOT_MATCHING)) {
			// System.out.println("mots de passe ne correspondent pas");
			// }
		}

	}
}