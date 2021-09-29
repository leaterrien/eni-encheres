package fr.eni.encheres.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BLL.ArticleManager;
import fr.eni.encheres.BLL.UtilisateurManager;
import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.BO.Enchere;
import fr.eni.encheres.BO.EtatVente;
import fr.eni.encheres.BO.Retrait;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletNewSale
 */
@WebServlet("/ServletNewSale")
public class ServletNewSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/newSale.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nom;
		String description;
		String categorie;
		String photoDeLArticle;
		int miseAPrix;
		LocalDate dateDebutEncheres;
		LocalDate dateFinEncheres;
		String retrait;
		String rue;
		String codePostal;
		String ville;
		
		
		try
		{
			nom = request.getParameter("name");
			description = request.getParameter("description");
			categorie = request.getParameter("category");
			miseAPrix = Integer.parseInt(request.getParameter("price"));
			dateDebutEncheres = LocalDate.parse(request.getParameter("start_auction"),DateTimeFormatter.ofPattern("dd MM yyyy"));
			dateFinEncheres = LocalDate.parse(request.getParameter("end_auction"),DateTimeFormatter.ofPattern("dd MM yyyy"));
			retrait = request.getParameter("withdrawal");
			rue = request.getParameter("street");
			codePostal = request.getParameter("postcode");
			ville = request.getParameter("city");

			Article article = new Article(nom, description, miseAPrix, dateDebutEncheres, dateFinEncheres, categorie, rue, codePostal, ville);
			ArticleManager.getInstance().addArticle(article, nom, description, dateDebutEncheres, dateFinEncheres, miseAPrix);
		
			}catch (BusinessException e){
			e.printStackTrace();
			request.setAttribute("errors", e.getListErrors());
			System.out.println(e.getListErrors());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/newSale.jsp");
			rd.forward(request, response);
			}
		
		

	}
}















