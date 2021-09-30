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
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BLL.ArticleCheckValid;
import fr.eni.encheres.BLL.ArticleManager;
import fr.eni.encheres.BLL.CategorieManager;
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
		
		try {
		List<Categorie> categories = CategorieManager.getInstance().getCategories();
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/newSale.jsp");
		rd.forward(request, response);
		}catch(BusinessException e){
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Utilisateur vendeur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		int noArticle = 0;
		String nom;
		String description;
		int categorie;
		String photoDeLArticle;
		int miseAPrix;
		LocalDate dateDebutEncheres;
		LocalDate dateFinEncheres;
		//Retrait retrait;
		String rue;
		String codePostal;
		String ville;
		
		
		
		try
		{
			
			nom = request.getParameter("name");
			description = request.getParameter("description");
			categorie = Integer.parseInt(request.getParameter("category"));
			miseAPrix = Integer.parseInt(request.getParameter("price"));
			dateDebutEncheres = LocalDate.parse(request.getParameter("start_auction"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			dateFinEncheres = LocalDate.parse(request.getParameter("end_auction"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			rue = request.getParameter("street");
			codePostal = request.getParameter("postcode");
			ville = request.getParameter("city");

			//Récupération adresse retrait formulaire jsp
			Retrait retrait = new Retrait(rue, codePostal, ville);
			retrait.setRue("street");
			retrait.setCodePostal("postcode");
			retrait.setVille("city");
			//recupération adresse utilisateur connecté
			Retrait retraitParDefaut = new Retrait(vendeur.getRue(), vendeur.getCodePostal(), vendeur.getVille());
			HttpSession session = request.getSession();
			response.sendRedirect(request.getContextPath());
			//comparaison des deux adresses
			retrait.compareTo(retraitParDefaut);
			int result = retrait.compareTo(retraitParDefaut);
			
			if(result == 1)
				retrait = null;
			System.out.println(retrait);	
			
			Article article = new Article(vendeur, nom, description, dateDebutEncheres, dateFinEncheres, miseAPrix, retrait);
			ArticleManager.getInstance().addArticle(article, categorie);
		
			}catch (BusinessException e){
			e.printStackTrace();
			request.setAttribute("errors", e.getListErrors());
			System.out.println(e.getListErrors());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/newSale.jsp");
			rd.forward(request, response);
			}
		
		

	}
}















