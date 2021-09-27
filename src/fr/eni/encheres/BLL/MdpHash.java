/**
 * 
 */
package fr.eni.encheres.BLL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ptruchot2021
 *
 */
public class MdpHash {

	private String hashpass="";
	
	public String getHashPass(String password) throws
		NoSuchAlgorithmException {
		
			String plainText = password;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(plainText.getBytes());

			byte byteData[] = md.digest();

	        //convertir le tableau de bits en une format hexadécimal
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
        
		return hashpass;

		}
}



