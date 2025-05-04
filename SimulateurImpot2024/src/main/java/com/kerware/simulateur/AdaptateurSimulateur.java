package com.kerware.simulateur;

/**
 * Adaptateur entre l'interface ICalculateurImpot et la classe Simulateur.
 */
public class AdaptateurSimulateur implements ICalculateurImpot {

    /**
     * Instance de la classe Simulateur utilisée pour effectuer les calculs.
     */
    private final Simulateur simulateur = new Simulateur();

    /**
     * Revenu net du déclarant 1.
     */
    private int revenusNetDecl1 = 0;

    /**
     * Revenu net du déclarant 2.
     */
    private int revenusNetDecl2 = 0;

    /**
     * Situation familiale du foyer fiscal.
     */
    private SituationFamiliale situationFamiliale;

    /**
     * Nombre d'enfants à charge.
     */
    private int nbEnfantsACharge;

    /**
     * Nombre d'enfants en situation de handicap.
     */
    private int nbEnfantsSituationHandicap;

    /**
     * Indique si le foyer est un parent isolé.
     */
    private boolean parentIsole;

    /**
     * Définit le revenu net du déclarant 1.
     *
     * @param revenuNet Revenu net du déclarant 1.
     */
    @Override
    public void setRevenusNetDeclarant1(final int revenuNet) {
        this.revenusNetDecl1 = revenuNet;
    }

    /**
     * Définit le revenu net du déclarant 2.
     *
     * @param revenuNet Revenu net du déclarant 2.
     */
    @Override
    public void setRevenusNetDeclarant2(final int revenuNet) {
        this.revenusNetDecl2 = revenuNet;
    }

    /**
     * Définit la situation familiale.
     *
     * @param situationFamiliale1 La situation familiale.
     */
    @Override
    public void setSituationFamiliale(
            final SituationFamiliale situationFamiliale1
    ) {
        this.situationFamiliale = situationFamiliale1;
    }

    /**
     * Définit le nombre d'enfants à charge.
     *
     * @param nombreEnfants Nombre d'enfants à charge.
     */
    @Override
    public void setNbEnfantsACharge(final int nombreEnfants) {
        this.nbEnfantsACharge = nombreEnfants;
    }

    /**
     * Définit le nombre d'enfants en situation de handicap.
     *
     * @param nombreEnfantsHandicapes Nombre d'enfants en situation de handicap.
     */
    @Override
    public void setNbEnfantsSituationHandicap(
            final int nombreEnfantsHandicapes
    ) {
        this.nbEnfantsSituationHandicap = nombreEnfantsHandicapes;
    }

    /**
     * Définit si le déclarant est un parent isolé.
     *
     * @param parentIsol Vrai si le déclarant est un parent isolé, faux sinon.
     */
    @Override
    public void setParentIsole(final boolean parentIsol) {
        this.parentIsole = parentIsol;
    }

    /**
     * Calcule l'impôt sur le revenu net en utilisant les données fournies.
     */
    @Override
    public void calculImpotSurRevenuNet() {
        simulateur.calculImpot(
                revenusNetDecl1,
                revenusNetDecl2,
                situationFamiliale,
                nbEnfantsACharge,
                nbEnfantsSituationHandicap,
                parentIsole
        );
    }

    /**
     * Retourne le revenu net du déclarant 1.
     *
     * @return Revenu net du déclarant 1.
     */
    @Override
    public int getRevenuNetDeclarant1() {
        return revenusNetDecl1;
    }

    /**
     * Retourne le revenu net du déclarant 2.
     *
     * @return Revenu net du déclarant 2.
     */
    @Override
    public int getRevenuNetDeclarant2() {
        return revenusNetDecl2;
    }

    /**
     * Retourne la contribution exceptionnelle.
     *
     * @return Contribution exceptionnelle.
     */
    @Override
    public double getContribExceptionnelle() {
        return simulateur.getContribExceptionnelle();
    }

    /**
     * Retourne le revenu fiscal de référence.
     *
     * @return Revenu fiscal de référence.
     */
    @Override
    public int getRevenuFiscalReference() {
        return (int) simulateur.getRevenuReference();
    }

    /**
     * Retourne le montant de l'abattement.
     *
     * @return Montant de l'abattement.
     */
    @Override
    public int getAbattement() {
        return (int) simulateur.getAbattement();
    }

    /**
     * Retourne le nombre de parts du foyer fiscal.
     *
     * @return Nombre de parts du foyer fiscal.
     */
    @Override
    public double getNbPartsFoyerFiscal() {
        return simulateur.getNbParts();
    }

    /**
     * Retourne le montant de l'impôt avant décote.
     *
     * @return Montant de l'impôt avant décote.
     */
    @Override
    public int getImpotAvantDecote() {
        return (int) simulateur.getImpotAvantDecote();
    }

    /**
     * Retourne le montant de la décote.
     *
     * @return Montant de la décote.
     */
    @Override
    public int getDecote() {
        return (int) simulateur.getDecote();
    }

    /**
     * Retourne le montant de l'impôt sur le revenu net.
     *
     * @return Montant de l'impôt sur le revenu net.
     */
    @Override
    public int getImpotSurRevenuNet() {
        return (int) simulateur.getImpotNet();
    }
}
