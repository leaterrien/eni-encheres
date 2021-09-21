package fr.eni.encheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BO.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	private static final String SELECT_BY_NICKNAME = "SELECT mot_de_passe FROM UTILISATEURS where pseudo = ?"; 
	private static final String SELECT_BY_EMAIL = "SELECT mot_de_passe FROM UTILISATEURS where email = ?"; 
	private static final String INSERT_USER = "INSERT INTO (pseudo, nom, prenom, email, telephone, rue, code_postal, "
			+ "ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?); ";
	
	@Override
	public Utilisateur selectByNickname(String pseudo, String motDePasse) throws SQLException  {
		String motDePasse = null;		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection cnx = null;
	
			try	{
				cnx = ConnectionProvider.getConnection();
				pstmt = cnx.prepareStatement(SELECT_BY_NICKNAME);
				pstmt.setString(1, pseudo);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					motDePasse = rs.getString("pseudo");
				
				}
				// Vérifier si le pseudo existe et si le mot de passe correspond à celui en base
				
				
			}
			catch (SQLException e) {
		e.printStackTrace();
		}
		return motDePasse;
	}
	
	@Override
	public void insert(Utilisateur utilisateur) throws SQLException {
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
		catch (SQLException e) {
			e.printStackTrace();
		
		}
	}
}
