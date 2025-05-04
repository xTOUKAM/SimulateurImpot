package com.kerware.simulateur;

/**
 * Classe utilitaire pour calculer l'impôt brut avant décote.
 */
public final class TaxCalculator {

    /**
     * Limites des tranches d'imposition.
     */
    private static final int[] LIMITES_TRANCHES = {
            0,
            11294,
            28797,
            82341,
            177106,
            Integer.MAX_VALUE
    };

    /**
     * Taux appliqués aux différentes tranches d'imposition.
     */
    private static final double[] TAUX_TRANCHES = {
            0.0,
            0.11,
            0.30,
            0.41,
            0.45
    };

    /**
     * Constructeur privé pour empêcher l'instanciation de
     * cette classe utilitaire.
     */
    private TaxCalculator() {
        // Empêche l'instanciation
    }

    /**
     * Calcule l'impôt brut avant décote.
     *
     * @param revenuFiscal Le revenu fiscal de référence.
     * @param nombreParts  Le nombre de parts fiscales.
     * @return Le montant brut de l'impôt.
     */
    public static double calculerImpotBrut(final double revenuFiscal,
                                           final double nombreParts) {
        final double revenuImposableParPart = revenuFiscal / nombreParts;
        double montantImpot = 0.0;

        for (int i = 0; i < LIMITES_TRANCHES.length - 1; i++) {
            if (revenuImposableParPart > LIMITES_TRANCHES[i]) {
                final double revenuDansTranche = Math.min(
                        revenuImposableParPart, LIMITES_TRANCHES[i + 1]
                ) - LIMITES_TRANCHES[i];
                montantImpot += revenuDansTranche * TAUX_TRANCHES[i];
            } else {
                break;
            }
        }

        return Math.round(montantImpot * nombreParts);
    }
}
