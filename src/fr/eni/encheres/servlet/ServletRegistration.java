package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableInterceptor.ForwardRequest;

import fr.eni.encheres.BLL.UtilisateurManager;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;


/**
 * Servlet implementation class ServletRegistration
 */
@WebServlet("/ServletRegistration")
public class ServletRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessException businessException;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/registration.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username;
		String email;
		String password;
		String lastName;
		String firstName;
		String phone;
		String street;
		String postcode;
		String city;
		try
		{
			username = request.getParameter("usnermae");
			email = request.getParameter("email");
			password = request.getParameter("password");
			lastName = request.getParameter("last_name");
			firstName = request.getParameter("first_name");
			phone = request.getParameter("phone");
			street = request.getParameter("street");
			postcode = request.getParameter("postcode");
			city = request.getParameter("city");
			
			Utilisateur utilisateur = new Utilisateur();
			UtilisateurManager.getInstance().checkUser(utilisateur, businessException);
			
		}catch (BusinessException e) {
			request.setAttribute("listErrors", e.getListErrors());
			
		}
		
	}
	
	private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
