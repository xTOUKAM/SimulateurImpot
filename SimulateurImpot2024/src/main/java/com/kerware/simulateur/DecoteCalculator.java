package com.kerware.simulateur;

/**
 * Classe utilitaire pour calculer la décote de l'impôt.
 */
public final class DecoteCalculator {

    /**
     * Seuil de décote pour un déclarant seul.
     */
    private static final double SEUIL_DECOTE_DECLARANT_SEUL = 1929.0;

    /**
     * Seuil de décote pour un couple déclarant.
     */
    private static final double SEUIL_DECOTE_DECLARANT_COUPLE = 3191.0;

    /**
     * Montant maximal de la décote pour un déclarant seul.
     */
    private static final double DECOTE_MAX_DECLARANT_SEUL = 873.0;

    /**
     * Montant maximal de la décote pour un couple déclarant.
     */
    private static final double DECOTE_MAX_DECLARANT_COUPLE = 1444.0;

    /**
     * Taux appliqué pour le calcul de la décote.
     */
    private static final double TAUX_DECOTE = 0.4525;

    /**
     * Constructeur privé pour empêcher l'instanciation.
     */
    private DecoteCalculator() {
        throw new UnsupportedOperationException("Cette classe "
                + "utilitaire ne peut pas être instanciée.");
    }

    /**
     * Calcule la décote.
     *
     * @param impotBrut        Le montant brut de l'impôt.
     * @param situationFamiliale La situation familiale.
     * @return Le montant de la décote.
     */
    public static double calculerDecote(
            final double impotBrut,
            final SituationFamiliale situationFamiliale
    ) {
        double decote = 0.0;

        if (situationFamiliale == SituationFamiliale.CELIBATAIRE
                || situationFamiliale == SituationFamiliale.DIVORCE
                || situationFamiliale == SituationFamiliale.VEUF) {
            if (impotBrut < SEUIL_DECOTE_DECLARANT_SEUL) {
                decote = DECOTE_MAX_DECLARANT_SEUL
                        - (impotBrut * TAUX_DECOTE);
            }
        } else if (situationFamiliale == SituationFamiliale.MARIE
                || situationFamiliale == SituationFamiliale.PACSE) {
            if (impotBrut < SEUIL_DECOTE_DECLARANT_COUPLE) {
                decote = DECOTE_MAX_DECLARANT_COUPLE
                        - (impotBrut * TAUX_DECOTE);
            }
        }

        return Math.max(0, Math.min(decote, impotBrut));
    }
}
