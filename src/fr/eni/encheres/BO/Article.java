package fr.eni.encheres.BO;

import java.time.LocalDate;
import java.util.List;

public class Article {

	private int noArticle;
	private String nom;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private EtatVente etatVente;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private Categorie categorie;
	private Retrait retrait;
	private List<Enchere> listEncheres;

	/**
	 * 
	 */
	public Article() {
	}

	/**
	 * @param nom
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param vendeur
	 * @param acheteur
	 * @param categorie
	 * @param retrait
	 * @param listEncheres
	 */
	public Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, EtatVente etatVente, Utilisateur vendeur, Utilisateur acheteur,
			Categorie categorie, Retrait retrait, List<Enchere> listEncheres) {
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.acheteur = acheteur;
		this.categorie = categorie;
		this.retrait = retrait;
		this.listEncheres = listEncheres;
	}

	/**
	 * @param noArticle
	 * @param nom
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param vendeur
	 * @param acheteur
	 * @param categorie
	 * @param retrait
	 * @param listEncheres
	 */
	public Article(int noArticle, String nom, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, EtatVente etatVente, Utilisateur vendeur,
			Utilisateur acheteur, Categorie categorie, Retrait retrait, List<Enchere> listEncheres) {
		this(nom, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, etatVente, vendeur, acheteur,
				categorie, retrait, listEncheres);
		this.noArticle = noArticle;
	}

	/**
	 * @param noArticle
	 * @param nom
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param vendeur
	 * @param acheteur
	 * @param categorie
	 * @param retrait
	 * @param listEncheres
	 */
	public Article(int noArticle, String nom, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, Utilisateur vendeur, Utilisateur acheteur,
			Categorie categorie, Retrait retrait, List<Enchere> listEncheres) {
		super();
		this.noArticle = noArticle;
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
		this.acheteur = acheteur;
		this.categorie = categorie;
		this.retrait = retrait;
		this.listEncheres = listEncheres;
	}

	/**
	 * @param nom
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param vendeur
	 * @param acheteur
	 * @param categorie
	 * @param retrait
	 * @param listEncheres
	 */
	public Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, Utilisateur vendeur, Utilisateur acheteur, Categorie categorie,
			Retrait retrait, List<Enchere> listEncheres) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
		this.acheteur = acheteur;
		this.categorie = categorie;
		this.retrait = retrait;
		this.listEncheres = listEncheres;
	}

	/**
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
	}

	/**
	 * @param noArticle
	 *            the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dateDebutEncheres
	 */
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * @param dateDebutEncheres
	 *            the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	/**
	 * @return the dateFinEncheres
	 */
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * @param dateFinEncheres
	 *            the dateFinEncheres to set
	 */
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 * @return the miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * @param miseAPrix
	 *            the miseAPrix to set
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * @return the prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * @param prixVente
	 *            the prixVente to set
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * @return the etatVente
	 */
	public EtatVente getEtatVente() {
		return etatVente;
	}

	/**
	 * @param etatVente
	 *            the etatVente to set
	 */
	public void setEtatVente(EtatVente etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * @return the vendeur
	 */
	public Utilisateur getVendeur() {
		return vendeur;
	}

	/**
	 * @param vendeur
	 *            the vendeur to set
	 */
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	/**
	 * @return the acheteur
	 */
	public Utilisateur getAcheteur() {
		return acheteur;
	}

	/**
	 * @param acheteur
	 *            the acheteur to set
	 */
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the retrait
	 */
	public Retrait getRetrait() {
		return retrait;
	}

	/**
	 * @param retrait
	 *            the retrait to set
	 */
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	/**
	 * @return the listEncheres
	 */
	public List<Enchere> getListEncheres() {
		return listEncheres;
	}

	/**
	 * @param enchere
	 */
	public void addEnchere(Enchere enchere) {
		this.listEncheres.add(enchere);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "Article [noArticle=" + noArticle + ", " + (nom != null ? "nom=" + nom + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (dateDebutEncheres != null ? "dateDebutEncheres=" + dateDebutEncheres + ", " : "")
				+ (dateFinEncheres != null ? "dateFinEncheres=" + dateFinEncheres + ", " : "") + "miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", "
				+ (etatVente != null ? "etatVente=" + etatVente + ", " : "")
				+ (vendeur != null ? "vendeur=" + vendeur + ", " : "")
				+ (acheteur != null ? "acheteur=" + acheteur + ", " : "")
				+ (categorie != null ? "categorie=" + categorie + ", " : "")
				+ (retrait != null ? "retrait=" + retrait + ", " : "")
				+ (listEncheres != null
						? "listEncheres=" + listEncheres.subList(0, Math.min(listEncheres.size(), maxLen))
						: "")
				+ "]";
	}

}
