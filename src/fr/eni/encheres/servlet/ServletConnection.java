package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BLL.UtilisateurManager;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet("/Connection")
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Récupération de la session
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		//Déconnexion de la session
		//méthode à placer dans servlet deconnexion = session.invalidate();
		
		//Récupération des cookies de connexion
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getMaxAge() != 0) {
					if(cookie.getName().equals("cookieEnchInitL")) {
						request.setAttribute("cookieEnchInitL", cookie.getValue());
					}
					if(cookie.getName().equals("cookieHcneTiniP")) {
						request.setAttribute("cookieHcneTiniP", cookie.getValue());
					}
					if(cookie.getName().equals("seSouvenirDeMoi")) {
					request.setAttribute("seSouvenirDeMoi", Boolean.parseBoolean(cookie.getValue()));
					}
				}
				
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/formulaireConnexion.jsp").forward(request, response);
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = null ;
		String password = null ;
		Boolean seSouvenirDeMoi = false ;
		
		// Récupération de la saisie utilisateur
				
		try
		{
			login = request.getParameter("login");
			password = request.getParameter("password");
				if(request.getParameter("seSouvenirDeMoi").equals("on")) {
					seSouvenirDeMoi = true;
				}
			
			Utilisateur utilisateur = UtilisateurManager.getInstance().checkValidConnection(login,password);
		
			//Création de la session
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			
			//Création des cookies de connexion, Coche checkbox seSouvenirDeMoi
			if(seSouvenirDeMoi != null && seSouvenirDeMoi) {
				Cookie cookie1 = new Cookie ("cookieHcneTiniP",password);
				//durée du cookie de an 
				cookie1.setMaxAge(60 * 60 * 24 * 30 * 12);
				response.addCookie(cookie1);
				
				Cookie cookie2 = new Cookie("cookieEnchInitL",login);
				//durée du cookie de an 
				cookie2.setMaxAge(60 * 60 * 24 * 30 * 12);
				response.addCookie(cookie2);
				
				Cookie cookie3 = new Cookie ("seSouvenirDeMoi","true");
				//durée du cookie de an 
				cookie3.setMaxAge(60 * 60 * 24 * 30 * 12);
				response.addCookie(cookie3);
				
			}else {
				Cookie cookie1 = new Cookie ("cookieHcneTiniP",password);
				//durée du cookie = supprimé à la fermeture du navigateur
				cookie1.setMaxAge(0);
				response.addCookie(cookie1);
				
				Cookie cookie2 = new Cookie("cookieEnchInitL",login);
				//durée du cookie = supprimé à la fermeture du navigateur
				cookie2.setMaxAge(0);
				response.addCookie(cookie2);
				
				Cookie cookie3 = new Cookie ("seSouvenirDeMoi","");
				//durée du cookie = supprimé à la fermeture du navigateur
				cookie3.setMaxAge(0);
				response.addCookie(cookie3);
				
			}
			
			request.setAttribute("cookieEnchInitL", login);
			request.setAttribute("cookieHcneTiniP", password);
			request.setAttribute("seSouvenirDeMoi", seSouvenirDeMoi);
		
		} catch (BusinessException e) {
			request.setAttribute("listErrors", e.getListErrors());
			doGet(request, response);
		}
		this.getServletContext().getRequestDispatcher(request.getContextPath()).forward(request, response);
		
	}
	

}
