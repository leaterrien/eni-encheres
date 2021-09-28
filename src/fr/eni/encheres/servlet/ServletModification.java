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
 * Servlet implementation class ServletModification
 */
@WebServlet("/ModificationUtilisateur")
public class ServletModification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/user-modification.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Utilisateur utilisateur = null;
		int noUtilisateur = 0;
		String pseudo;
		String email;
		String motDePasse;
		String nouveauMotDePasse;
		String confirmerMotDePasse;
		String nom;
		String prenom;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		
		try
		{
			pseudo = request.getParameter("username");
			email = request.getParameter("email");
			motDePasse = request.getParameter("password");
			nouveauMotDePasse = request.getParameter("new_password");
			confirmerMotDePasse = request.getParameter("confirm_password");
			nom = request.getParameter("last_name");
			prenom = request.getParameter("first_name");
			telephone = request.getParameter("phone");
			rue = request.getParameter("street");
			codePostal = request.getParameter("postcode");
			ville = request.getParameter("city");
			
			utilisateur = UtilisateurManager.getInstance().getUser(noUtilisateur);
			UtilisateurManager.getInstance().updateUtilisateur(utilisateur, email, pseudo, nouveauMotDePasse, confirmerMotDePasse);
			response.sendRedirect(request.getContextPath());
			
		}catch(BusinessException e){
			e.printStackTrace();
			request.setAttribute("errors", e.getListErrors());
			System.out.println(e.getListErrors());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/user-modification.jsp");
			rd.forward(request, response);
			
		}
	}
}
