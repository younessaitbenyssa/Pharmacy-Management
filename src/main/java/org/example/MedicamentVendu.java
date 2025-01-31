package org.example;

public class MedicamentVendu {
    private int medicamentId;
    private String nom;
    private int quantite;
    private double prixUnitaire;

    public MedicamentVendu(int medicamentId, String nom, int quantite, double prixUnitaire) {
        this.medicamentId = medicamentId;
        this.nom = nom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    // Getters
    public int getMedicamentId() {
        return medicamentId;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public double getTotal() {
        return quantite * prixUnitaire;
    }
}