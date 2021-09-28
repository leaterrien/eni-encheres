/**
 * 
 */
package fr.eni.encheres.BLL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.eni.encheres.exceptions.BusinessException;

/**
 * @author ptruchot2021
 *
 */
public class MdpHash {

	private static String hashpass="";
	
	
	public static String getHashPass(String password, BusinessException businessException) throws
		BusinessException {
		
			String plainText = password;
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				businessException.addError(CodesResultatBLL.UTILISATEUR_PASSWORD_FAIL_HASHED);
				e.printStackTrace();
				throw businessException;
			}
			md.update(plainText.getBytes());

			byte byteData[] = md.digest();

	        //convertir le tableau de bits en une format hexadécimal
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
        hashpass = sb.toString();
		return hashpass;

		}
}



