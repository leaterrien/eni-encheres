package fr.eni.encheres.BO;

import java.time.LocalDate;

public class Enchere {
	private LocalDate date;
	private int montant;
	private Utilisateur encherisseur;

	/**
	 * @param date
	 * @param montant
	 * @param encherisseur
	 */
	public Enchere(LocalDate date, int montant, Utilisateur encherisseur) {
		this.date = date;
		this.montant = montant;
		this.encherisseur = encherisseur;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the montant
	 */
	public int getMontant() {
		return montant;
	}

	/**
	 * @param montant
	 *            the montant to set
	 */
	public void setMontant(int montant) {
		this.montant = montant;
	}

	/**
	 * @return the encherisseur
	 */
	public Utilisateur getEncherisseur() {
		return encherisseur;
	}

	/**
	 * @param encherisseur
	 *            the encherisseur to set
	 */
	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Enchere [" + (date != null ? "date=" + date + ", " : "") + "montant=" + montant + ", "
				+ (encherisseur != null ? "encherisseur=" + encherisseur : "") + "]";
	}

}
