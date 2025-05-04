package com.kerware.simulateur;

/**
 * Classe principale pour simuler le calcul de l'impôt sur le revenu.
 */
public class Simulateur {

    /**
     * Nombre d'enfants maximal.
     */
    private static final int MAX_ENFANTS = 7;

    /**
     * Revenu net du déclarant 1.
     */
    private int revenuNetDeclarant1;

    /**
     * Revenu net du déclarant 2.
     */
    private int revenuNetDeclarant2;

    /**
     * Nombre d'enfants à charge.
     */
    private int nombreEnfant;

    /**
     * Nombre d'enfants en situation de handicap.
     */
    private int nombreEnfantHandicapes;

    /**
     * Revenu fiscal de référence.
     */
    private double revenuFiscalReference;

    /**
     * Abattement appliqué.
     */
    private double abattement;

    /**
     * Nombre de parts fiscales.
     */
    private double nombreDeParts;

    /**
     * Décote appliquée.
     */
    private double decote;

    /**
     * Impôt avant décote.
     */
    private double impotAvantDecote;

    /**
     * Impôt net après calcul.
     */
    private double impotNet;

    /**
     * Contribution exceptionnelle.
     */
    private double contribExceptionnelle;

    /**
     * Indique si le parent est isolé.
     */
    private boolean parentIsole;

    /**
     * Calcule l'impôt sur le revenu net.
     *
     * @param rnDeclarant1          Revenu net du déclarant 1.
     * @param rnDeclarant2          Revenu net du déclarant 2.
     * @param situation             Situation familiale du foyer.
     * @param nbEnfants             Nombre d'enfants à charge.
     * @param nbEnfantsHandicapes   Nombre d'enfants handicapés.
     * @param isolParent            Indique si le parent est isolé.
     * @return Le montant total de l'impôt sur le revenu.
     */
    public long calculImpot(final int rnDeclarant1, final int rnDeclarant2,
                            final SituationFamiliale situation,
                            final int nbEnfants, final int nbEnfantsHandicapes,
                            final boolean isolParent) {

        verifierPreconditions(rnDeclarant1, rnDeclarant2, situation,
                nbEnfants, nbEnfantsHandicapes, isolParent);

        initialiserVariables(rnDeclarant1, rnDeclarant2, nbEnfants,
                nbEnfantsHandicapes, isolParent);

        calculerAbattementEtRevenuFiscal(situation);
        calculerNombreDeParts(situation);
        calculerImpotAvantDecote();
        calculerDecote(situation);
        calculerContributionExceptionnelle(situation);

        return calculerImpotTotal();
    }

    private void verifierPreconditions(final int rnDeclarant1,
                                       final int rnDeclarant2,
                                       final SituationFamiliale situation,
                                       final int nbEnfants,
                                       final int nbEnfantsHandicapes,
                                       final boolean isolParent) {
        if (rnDeclarant1 < 0 || rnDeclarant2 < 0) {
            throw new IllegalArgumentException(
                    "Le revenu net ne peut pas être négatif."
            );
        }
        if (nbEnfants < 0) {
            throw new IllegalArgumentException(
                    "Le nombre d'enfants ne peut pas être négatif."
            );
        }
        if (nbEnfantsHandicapes < 0) {
            throw new IllegalArgumentException(
                    "Le nombre d'enfants handicapés ne peut pas être négatif."
            );
        }
        if (situation == null) {
            throw new IllegalArgumentException(
                    "La situation familiale ne peut pas être null."
            );
        }
        if (nbEnfantsHandicapes > nbEnfants) {
            throw new IllegalArgumentException(
                    "Le nombre d'enfants handicapés ne peut pas dépasser "
                            + "le nombre total d'enfants."
            );
        }
        if (nbEnfants > MAX_ENFANTS) {
            throw new IllegalArgumentException(
                    "Le nombre d'enfants ne peut pas dépasser "
                            + MAX_ENFANTS + "."
            );
        }
        if (isolParent && (situation == SituationFamiliale.MARIE
                || situation == SituationFamiliale.PACSE)) {
            throw new IllegalArgumentException(
                    "Un parent isolé ne peut pas être marié ou pacsé."
            );
        }
        if ((situation == SituationFamiliale.CELIBATAIRE
                || situation == SituationFamiliale.DIVORCE
                || situation == SituationFamiliale.VEUF)
                && rnDeclarant2 > 0) {
            throw new IllegalArgumentException(
                    "Un célibataire, divorcé ou veuf ne peut pas "
                            + "avoir de revenu "
                            + "pour le déclarant 2."
            );
        }
    }

    private void initialiserVariables(final int rnDeclarant1,
                                      final int rnDeclarant2,
                                      final int nbEnfants,
                                      final int nbEnfantsHandicapes,
                                      final boolean isolParent) {
        this.revenuNetDeclarant1 = rnDeclarant1;
        this.revenuNetDeclarant2 = rnDeclarant2;
        this.nombreEnfant = nbEnfants;
        this.nombreEnfantHandicapes = nbEnfantsHandicapes;
        this.parentIsole = isolParent;
    }

    private void calculerAbattementEtRevenuFiscal(
            final SituationFamiliale situation) {
        abattement = AbattementCalculator.calculerAbattement(
                revenuNetDeclarant1, revenuNetDeclarant2, situation);
        revenuFiscalReference = AbattementCalculator.calculerRevenuFiscal(
                revenuNetDeclarant1, revenuNetDeclarant2, abattement);
    }

    private void calculerNombreDeParts(final SituationFamiliale situation) {
        nombreDeParts = PartCalculator.calculerNombreDeParts(
                situation, nombreEnfant, nombreEnfantHandicapes, parentIsole);
    }

    private void calculerImpotAvantDecote() {
        impotAvantDecote = TaxCalculator.calculerImpotBrut(
                revenuFiscalReference, nombreDeParts);
    }

    private void calculerDecote(final SituationFamiliale situation) {
        decote = DecoteCalculator.calculerDecote(impotAvantDecote, situation);
        impotNet = Math.round(impotAvantDecote - decote);
    }

    private void calculerContributionExceptionnelle(
            final SituationFamiliale situation) {
        contribExceptionnelle = ContributionCalculator.calculerContribution(
                revenuFiscalReference, situation);
    }

    private long calculerImpotTotal() {
        impotNet += contribExceptionnelle;
        return Math.round(impotNet);
    }

    // Getters pour les tests unitaires

    /**
     * Retourne le revenu fiscal de référence.
     *
     * @return Revenu fiscal de référence.
     */
    public double getRevenuReference() {
        return revenuFiscalReference;
    }

    /**
     * Retourne le montant de l'abattement.
     *
     * @return Montant de l'abattement.
     */
    public double getAbattement() {
        return abattement;
    }

    /**
     * Retourne le nombre de parts fiscales.
     *
     * @return Nombre de parts fiscales.
     */
    public double getNbParts() {
        return nombreDeParts;
    }

    /**
     * Retourne le montant de l'impôt avant décote.
     *
     * @return Montant de l'impôt avant décote.
     */
    public double getImpotAvantDecote() {
        return impotAvantDecote;
    }

    /**
     * Retourne le montant de la décote.
     *
     * @return Montant de la décote.
     */
    public double getDecote() {
        return decote;
    }

    /**
     * Retourne le montant de l'impôt net.
     *
     * @return Montant de l'impôt net.
     */
    public double getImpotNet() {
        return impotNet;
    }

    /**
     * Retourne la contribution exceptionnelle.
     *
     * @return Contribution exceptionnelle.
     */
    public double getContribExceptionnelle() {
        return contribExceptionnelle;
    }
}
