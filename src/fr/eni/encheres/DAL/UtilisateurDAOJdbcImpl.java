package fr.eni.encheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	private static final String SELECT_BY_NICKNAME = "SELECT * FROM UTILISATEURS where pseudo = ?"; 
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS where email = ?"; 
	private static final String INSERT_USER = "INSERT INTO (pseudo, nom, prenom, email, telephone, rue, code_postal, "
			+ "ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?); ";
	
	private static Utilisateur getUtilisateur() {
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			utilisateur = new Utilisateur(rs.getString("no_utilisateur"), rs.getString("pseudo") , rs.getString("nom")
					,rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal")
					, rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return utilisateur;
	}
	
	@Override
	public Utilisateur selectByNickname(String pseudo) throws BusinessException  {
		if (pseudo == null) 
		{
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_NICKNAME_NULL);
			throw businessException;
		}
		Utilisateur utilisateur = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection cnx = null;
	
			try	{
				cnx = ConnectionProvider.getConnection();
				pstmt = cnx.prepareStatement(SELECT_BY_NICKNAME);
				pstmt.setString(1, pseudo);
				rs = pstmt.executeQuery();
				if(rs.next()) 
				{
					utilisateur = new Utilisateur(rs.getString("no_utilisateur"), rs.getString("pseudo") , rs.getString("nom")
							,rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal")
							, rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
				}
								
			}
			catch (Exception e) 
			{
				BusinessException businessException = new BusinessException();
				businessException.addError(CodesResultatDAL.SELECT_NICKNAME_FAIL);				
				e.printStackTrace();
				throw businessException;
		}
			
		return utilisateur;
	}
	
	@Override
	public Utilisateur selectByEmail(String email) throws BusinessException  {
		if (email == null) 
		{
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_NICKNAME_NULL);
			throw businessException;
		}
		Utilisateur utilisateur = new Utilisateur();
		String motDePasse = null;		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection cnx = null;
	
			try	{
				cnx = ConnectionProvider.getConnection();
				pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					utilisateur = new Utilisateur(rs.getString("no_utilisateur"), rs.getString("pseudo") , rs.getString("nom")
							,rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal")
							, rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));				
				}
				
				
				
			}
			catch (Exception e) {
		e.printStackTrace();
		}
		return utilisateur;
	}
	
	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Connection cnx = null;
		try 
		{
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			pstmt.close();
			cnx.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		
		}
	}
}
