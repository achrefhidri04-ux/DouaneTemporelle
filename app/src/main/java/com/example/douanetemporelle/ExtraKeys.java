package com.example.douanetemporelle;

/**
 * N7adhrou les clés mta3 Intent 7asb ekher 4 chiffres mel matricule
 */
public final class ExtraKeys {

    private ExtraKeys() {
        // 1: el class hedha ma yet3amlouch menou objet
    }

    public static String key(String matricule, String name) {
        // 2: na7iw les lettres w nkhaliw ken les chiffres
        String digits = matricule.replaceAll("\\D", "");

        // 3: lezem fama 4 chiffres 3al a9al, sinon fama mochkel
        if (digits.length() < 4) {
            throw new IllegalArgumentException("Le matricule doit contenir au moins 4 chiffres.");
        }

        // 4: nechdou ekher 4 chiffres w nركبوا bihom el clé
        String prefix = digits.substring(digits.length() - 4);
        return prefix + "_" + name;
    }
}
