package com.kerware.simulateur;

/**
 * Classe utilitaire pour calculer les abattements fiscaux et le revenu fiscal
 * de référence.
 */
public final class AbattementCalculator {

    /**
     * Montant maximal de l'abattement.
     */
    private static final int ABATTEMENT_MAXIMAL = 14171;

    /**
     * Montant minimal de l'abattement.
     */
    private static final int ABATTEMENT_MINIMAL = 495;

    /**
     * Taux d'abattement appliqué sur le revenu net.
     */
    private static final double TAUX_ABATTEMENT = 0.1;

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe utilitaire.
     */
    private AbattementCalculator() {
        // Empêche l'instanciation
    }

    /**
     * Calcule l'abattement total pour les deux déclarants.
     *
     * @param revenuNetDeclarant1 Revenu net du déclarant 1.
     * @param revenuNetDeclarant2 Revenu net du déclarant 2.
     * @param situationFamiliale  La situation familiale (marié, pacsé, etc.).
     * @return Le montant total de l'abattement.
     */
    public static double calculerAbattement(
            final int revenuNetDeclarant1,
            final int revenuNetDeclarant2,
            final SituationFamiliale situationFamiliale) {
        // Abattement pour le déclarant 1
        final double abattement1 = Math.min(
                Math.max(revenuNetDeclarant1 * TAUX_ABATTEMENT,
                        ABATTEMENT_MINIMAL),
                ABATTEMENT_MAXIMAL
        );

        // Abattement pour le déclarant 2 (si applicable)
        double abattement2 = 0;
        if (situationFamiliale == SituationFamiliale.MARIE
                || situationFamiliale == SituationFamiliale.PACSE) {
            abattement2 = Math.min(
                    Math.max(revenuNetDeclarant2 * TAUX_ABATTEMENT,
                            ABATTEMENT_MINIMAL),
                    ABATTEMENT_MAXIMAL
            );
        }

        return abattement1 + abattement2;
    }

    /**
     * Calcule le revenu fiscal de référence après abattement.
     *
     * @param revenuNetDeclarant1 Revenu net du déclarant 1.
     * @param revenuNetDeclarant2 Revenu net du déclarant 2.
     * @param abattement          Total des abattements calculés.
     * @return Revenu fiscal de référence.
     */
    public static double calculerRevenuFiscal(
            final int revenuNetDeclarant1,
            final int revenuNetDeclarant2,
            final double abattement) {
        final double revenuFiscal = revenuNetDeclarant1
                + revenuNetDeclarant2
                - abattement;
        return Math.max(revenuFiscal, 0);
    }
}
