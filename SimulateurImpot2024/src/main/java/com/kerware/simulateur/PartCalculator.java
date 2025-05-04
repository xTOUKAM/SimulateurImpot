package com.kerware.simulateur;

/**
 * Classe utilitaire pour calculer le nombre de parts fiscales pour un foyer.
 */
public final class PartCalculator {

    /**
     * Constante pour la valeur de demi-part fiscale (0.5).
     */
    private static final double DEMI_PART = 0.5;

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe utilitaire.
     */
    private PartCalculator() {
        // Empêche l'instanciation
    }

    /**
     * Calcule le nombre de parts fiscales pour un foyer.
     *
     * @param situationFamiliale     Situation familiale du foyer.
     * @param nbEnfants              Nombre d'enfants à charge.
     * @param nbEnfantsHandicapes    Nombre d'enfants en situation de handicap.
     * @param parentIsole            Indique si le foyer est un parent isolé.
     * @return Le nombre de parts fiscales.
     */
    public static double calculerNombreDeParts(
            final SituationFamiliale situationFamiliale,
            final int nbEnfants,
            final int nbEnfantsHandicapes,
            final boolean parentIsole) {

        double nombreParts = 0.0;

        switch (situationFamiliale) {
            case CELIBATAIRE:
            case DIVORCE:
            case VEUF:
                nombreParts = 1.0;
                break;
            case MARIE:
            case PACSE:
                nombreParts = 2.0;
                break;
            default:
                break;
        }

        if (nbEnfants <= 2) {
            nombreParts += nbEnfants * DEMI_PART;
        } else {
            nombreParts += 1.0 + (nbEnfants - 2);
        }

        if (parentIsole && nbEnfants > 0) {
            nombreParts += DEMI_PART;
        }

        if (situationFamiliale == SituationFamiliale.VEUF && nbEnfants > 0) {
            nombreParts += 1.0;
        }

        nombreParts += nbEnfantsHandicapes * DEMI_PART;
        return nombreParts;
    }
}
