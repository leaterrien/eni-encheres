package fr.eni.encheres.BO;

public class Retrait implements Comparable{

	private String rue;
	private String codePostal;
	private String ville;

	/**
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue
	 *            the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal
	 *            the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville
	 *            the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Retrait [" + (rue != null ? "rue=" + rue + ", " : "")
				+ (codePostal != null ? "codePostal=" + codePostal + ", " : "")
				+ (ville != null ? "ville=" + ville : "") + "]";
	}

	@Override
	public int compareTo(Object temp) {
		Retrait other = (Retrait) temp;
		System.out.println(this.toString());
		System.out.println(other);
		if(this.getRue().equals(other.getRue()) && this.getCodePostal().equals(other.getCodePostal()) && this.getVille().equals(other.getVille()))
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}

}
