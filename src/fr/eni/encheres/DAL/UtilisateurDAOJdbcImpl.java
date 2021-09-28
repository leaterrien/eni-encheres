package fr.eni.encheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_BY_NICKNAME = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";
	private static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, "
			+ "ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?); ";
	private static final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?"
			+ "email=?, telephone=?, ville=?, mot_de_passe=? WHERE no_utilisateur = ?";
	private static final String UPDATE_CREDIT_UTILISATEUR = "UPDATE UTILISATEURS SET credit= ? WHERE no_utilisateur = ?";
	private static final String UPDATE_ADMIN_UTILISATEUR = "UPDATE UTILISATEURS SET administrateur= ? WHERE no_utilisateur = ?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";

	public static Utilisateur getUtilisateur(ResultSet rs) throws SQLException {
		Utilisateur utilisateur = null;
		utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
				rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
				rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
				rs.getBoolean("administrateur"));

		return utilisateur;
	}

	@Override
	public Utilisateur selectByNickname(String pseudo) throws BusinessException {
		if (pseudo == null) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_NICKNAME_NULL);
			throw businessException;
		}
		Utilisateur utilisateur = new Utilisateur();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection cnx = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_NICKNAME);
			pstmt.setString(1, pseudo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = getUtilisateur(rs);
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.SELECT_NICKNAME_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

	@Override
	public Utilisateur selectByEmail(String email) throws BusinessException {
		if (email == null) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_EMAIL_NULL);
			throw businessException;
		}
		Utilisateur utilisateur = new Utilisateur();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection cnx = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = getUtilisateur(rs);
			}

		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.SELECT_EMAIL_FAIL);
			e.printStackTrace();
			throw businessException;
		}

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

	@Override
	public Utilisateur selectById(int noUtilisateur) throws BusinessException {
		if (noUtilisateur == 0) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_ID_NULL);
			throw businessException;
		}
		Utilisateur utilisateur = new Utilisateur();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection cnx = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, noUtilisateur);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = getUtilisateur(rs);
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.SELECT_NICKNAME_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws BusinessException {

		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			Utilisateur utilisateur = null;
			while (rs.next()) {
				utilisateur = getUtilisateur(rs);
				listeUtilisateurs.add(utilisateur);
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.SELECT_UTILISATEUR_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeUtilisateurs;
	}

	@Override
	public Utilisateur insert(Utilisateur utilisateur) throws BusinessException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, Statement.RETURN_GENERATED_KEYS);
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
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.INSERT_UTILISATEUR_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

	@Override
	public Utilisateur update(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_NULL);
			throw businessException;
		}
		PreparedStatement pstmt = null;
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());

			pstmt.executeUpdate();
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UPDATE_UTILISATEUR_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

	@Override
	public Utilisateur updateCredit(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_NULL);
			throw businessException;
		}
		PreparedStatement pstmt = null;
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_CREDIT_UTILISATEUR);
			pstmt.setInt(1, utilisateur.getCredit());
			pstmt.executeUpdate();
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UPDATE_UTILISATEUR_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

	@Override
	public Utilisateur updateAdmin(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_NULL);
			throw businessException;
		}
		PreparedStatement pstmt = null;
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_ADMIN_UTILISATEUR);
			pstmt.setBoolean(1, utilisateur.isAdministrateur());
			pstmt.executeUpdate();
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UPDATE_UTILISATEUR_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

	public void delete(int noUtilisateur) throws BusinessException {
		if (noUtilisateur == 0) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.UTILISATEUR_ID_NULL);
			throw businessException;
		}
		PreparedStatement pstmt = null;
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, noUtilisateur);
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodesResultatDAL.DELETE_UTILISATEUR_FAIL);
			e.printStackTrace();
			throw businessException;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
