package com.kerware.simulateur;

public final class ContributionCalculator {

    /**
     * Limites de revenu pour les tranches de contribution exceptionnelle.
     */
    private static final int[] LIMITES_CEHR = {
            0, 250000, 500000, 1000000, Integer.MAX_VALUE
    };

    /**
     * Taux appliqués pour les célibataires.
     */
    private static final double[] TAUX_CEHR_CELIBATAIRE = {
            0.0, 0.03, 0.04, 0.04
    };

    /**
     * Taux appliqués pour les couples (mariés ou pacsés).
     */
    private static final double[] TAUX_CEHR_COUPLE = {
            0.0, 0.0, 0.03, 0.04
    };

    /**
     * Constructeur privé pour empêcher l'instanciation.
     */
    private ContributionCalculator() {
        throw new UnsupportedOperationException("Cette classe "
                + "utilitaire ne peut pas être instanciée.");
    }

    /**
     * Calcule la contribution exceptionnelle sur les hauts revenus.
     *
     * @param revenuFiscal      Le revenu fiscal de référence.
     * @param situationFamiliale La situation familiale.
     * @return Le montant de la contribution exceptionnelle,
     * arrondi à l'entier le plus proche.
     */
    public static double calculerContribution(
            final double revenuFiscal,
            final SituationFamiliale situationFamiliale
    ) {
        double contribution = 0.0;
        final double[] tauxAppliques = (situationFamiliale
                == SituationFamiliale.MARIE
                || situationFamiliale == SituationFamiliale.PACSE)
                ? TAUX_CEHR_COUPLE
                : TAUX_CEHR_CELIBATAIRE;

        for (int i = 0; i < LIMITES_CEHR.length - 1; i++) {
            if (revenuFiscal > LIMITES_CEHR[i]) {
                final double revenuDansTranche =
                        Math.min(revenuFiscal,
                                LIMITES_CEHR[i + 1]
                        )
                                - LIMITES_CEHR[i];
                contribution += revenuDansTranche * tauxAppliques[i];
            } else {
                break;
            }
        }

        return Math.round(contribution);
    }
}
