package com.kerware.simulateur;

/**
 * Interface pour définir les méthodes liées au calcul de l'impôt.
 */
public interface ICalculateurImpot {

    /**
     * Définit le revenu net du déclarant 1.
     *
     * @param revenusNetDeclarant1 Revenu net du déclarant 1.
     */
    void setRevenusNetDeclarant1(int revenusNetDeclarant1);

    /**
     * Définit le revenu net du déclarant 2.
     *
     * @param revenusNetDeclarant2 Revenu net du déclarant 2.
     */
    void setRevenusNetDeclarant2(int revenusNetDeclarant2);

    /**
     * Définit la situation familiale.
     *
     * @param situationFamiliale La situation familiale.
     */
    void setSituationFamiliale(SituationFamiliale situationFamiliale);

    /**
     * Définit le nombre d'enfants à charge.
     *
     * @param nombreEnfantsACharge Nombre d'enfants à charge.
     */
    void setNbEnfantsACharge(int nombreEnfantsACharge);

    /**
     * Définit le nombre d'enfants en situation de handicap.
     *
     * @param nombreEnfantsSituationHandicap Nombre d'enfants en
     * situation de handicap.
     */
    void setNbEnfantsSituationHandicap(int nombreEnfantsSituationHandicap);

    /**
     * Définit si le déclarant est un parent isolé.
     *
     * @param parentIsole Vrai si le déclarant est un parent isolé, faux sinon.
     */
    void setParentIsole(boolean parentIsole);

    /**
     * Calcule l'impôt sur le revenu net en utilisant les données fournies.
     */
    void calculImpotSurRevenuNet();

    /**
     * Retourne le revenu net du déclarant 1.
     *
     * @return Revenu net du déclarant 1.
     */
    int getRevenuNetDeclarant1();

    /**
     * Retourne le revenu net du déclarant 2.
     *
     * @return Revenu net du déclarant 2.
     */
    int getRevenuNetDeclarant2();

    /**
     * Retourne la contribution exceptionnelle.
     *
     * @return Contribution exceptionnelle.
     */
    double getContribExceptionnelle();

    /**
     * Retourne le revenu fiscal de référence.
     *
     * @return Revenu fiscal de référence.
     */
    int getRevenuFiscalReference();

    /**
     * Retourne le montant de l'abattement.
     *
     * @return Montant de l'abattement.
     */
    int getAbattement();

    /**
     * Retourne le nombre de parts du foyer fiscal.
     *
     * @return Nombre de parts du foyer fiscal.
     */
    double getNbPartsFoyerFiscal();

    /**
     * Retourne le montant de l'impôt avant décote.
     *
     * @return Montant de l'impôt avant décote.
     */
    int getImpotAvantDecote();

    /**
     * Retourne le montant de la décote.
     *
     * @return Montant de la décote.
     */
    int getDecote();

    /**
     * Retourne le montant de l'impôt sur le revenu net.
     *
     * @return Montant de l'impôt sur le revenu net.
     */
    int getImpotSurRevenuNet();
}
