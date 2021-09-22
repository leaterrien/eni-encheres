package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("cookieEnchInitL")) {
					request.setAttribute("cookieEnchInitL", cookie.getValue());
				}
				if(cookie.getName().equals("cookieHcneTiniP")) {
					request.setAttribute("cookieHcneTiniP", cookie.getValue());
				}
				if(cookie.getName().equals("seSouvenirDeMoi")) {
					request.setAttribute("seSouvenirDeMoi", cookie.getValue());
				}
				
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/formulaireConnexion.jsp").forward(request, response);
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		System.out.println("Login servlet");
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String seSouvenirDeMoi = request.getParameter("seSouvenirDeMoi");
		
		System.out.println("login = " + login);
		System.out.println("password = " + password);
		System.out.println("se souvenir de moi = " + seSouvenirDeMoi);
		
		if(login!=null && password!=null /*&& login lea && password lea*/)
				 {
			System.out.println("login ok");
		}else {
			System.out.println("login error");
		}
		
		//Création de la session
		HttpSession session = request.getSession();
		
		session.setAttribute("login", login);
		session.setAttribute("password", password);
		
		
		//Création des cookies de connexion, Coche checkbox seSouvenirDeMoi
		if(seSouvenirDeMoi != null && seSouvenirDeMoi.contentEquals("on")) {
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
			cookie3.setValue("true");
			request.setAttribute("ischecked", true);
		}else {
			Cookie cookie1 = new Cookie ("cookieHcneTiniP",password);
			//durée du cookie de an 
			cookie1.setMaxAge(-1);
			response.addCookie(cookie1);
			
			Cookie cookie2 = new Cookie("cookieEnchInitL",login);
			//durée du cookie de an 
			cookie2.setMaxAge(-1);
			response.addCookie(cookie2);
			
			Cookie cookie3 = new Cookie ("seSouvenirDeMoi","");
			//durée du cookie de an 
			cookie3.setMaxAge(-1);
			response.addCookie(cookie3);
			cookie3.setValue("false");
			request.setAttribute("ischecked", false);
		}
		
		request.setAttribute("cookieEnchInitL", login);
		request.setAttribute("cookieHcneTiniP", password);
		request.setAttribute("seSouvenirDeMoi", seSouvenirDeMoi);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/formulaireConnexion.jsp").forward(request, response);
		
	}
	

}
