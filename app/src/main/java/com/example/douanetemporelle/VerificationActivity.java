package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerificationActivity extends Activity {

    private String nomVoyageur;
    private Button btnApprouverAh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 1: njibou les ma3loumet eli b3athhom el activité loula
        nomVoyageur = getIntent().getStringExtra(
                ExtraKeys.key(StudentConfig.MATRICULE, "NOM_VOYAGEUR")
        );
        int anneeNaissance = getIntent().getIntExtra(
                ExtraKeys.key(StudentConfig.MATRICULE, "ANNEE_NAISSANCE"), 0
        );
        int anneeDestination = getIntent().getIntExtra(
                ExtraKeys.key(StudentConfig.MATRICULE, "ANNEE_DESTINATION"), 0
        );
        String motif = getIntent().getStringExtra(
                ExtraKeys.key(StudentConfig.MATRICULE, "MOTIF_VOYAGE")
        );

        // 2: net2akdou eli les ma3loumet lkol wsolou bel s7i7
        if (nomVoyageur == null || motif == null || anneeNaissance == 0 || anneeDestination == 0) {
            Toast.makeText(this, "Données du voyageur manquantes.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // 3: نحسبو el age temporel w nwarriw ma3loumet el voyageur
        int ageTemporel = anneeDestination - anneeNaissance;
        TextView tvNomAh = findViewById(R.id.tv_nom_verif_ah);
        TextView tvNaissanceAh = findViewById(R.id.tv_naissance_verif_ah);
        TextView tvDestinationAh = findViewById(R.id.tv_destination_verif_ah);
        TextView tvAgeAh = findViewById(R.id.tv_age_verif_ah);
        TextView tvMotifAh = findViewById(R.id.tv_motif_verif_ah);

        tvNomAh.setText("Voyageur : " + nomVoyageur);
        tvNaissanceAh.setText("Naissance : " + anneeNaissance);
        tvDestinationAh.setText("Destination : " + anneeDestination);
        tvAgeAh.setText("Âge temporel : " + ageTemporel + " ans");
        tvMotifAh.setText("Motif : " + motif);

        // 4: nربطو les boutons w nطبقو condition mta3 nombre premier
        btnApprouverAh = findViewById(R.id.btn_approuver_ah);
        Button btnRefuserAh = findViewById(R.id.btn_refuser_ah);

        int difference = anneeDestination - anneeNaissance;
        boolean conditionParadoxe =
                "Modification du passé".equals(motif) && estPremier(difference);

        // 5: ken condition mta3 paradoxe t7a9a9et, nsakrou bouton approbation direct
        if (conditionParadoxe) {
            btnApprouverAh.setEnabled(false);
            btnApprouverAh.setText("Approbation désactivée (paradoxe)");
        }

        // 6: nab3thou décision mta3 approbation wala refus lel activité thenya
        btnApprouverAh.setOnClickListener(view -> envoyerVerdict(true));
        btnRefuserAh.setOnClickListener(view -> envoyerVerdict(false));
    }

    private boolean estPremier(int nombre) {
        // 7: ken el nombre asghar men 2, moch nombre premier
        if (nombre < 2) {
            return false;
        }

        // 8: njarbou ken fama nombre ya9smou mel 2 7atta lel racine carrée
        for (int i = 2; i * i <= nombre; i++) {
            if (nombre % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void envoyerVerdict(boolean approuve) {
        // 9: nab3thou el décision w esm el voyageur lel activité thenya
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(ExtraKeys.key(StudentConfig.MATRICULE, "VERDICT"), approuve);
        intent.putExtra(ExtraKeys.key(StudentConfig.MATRICULE, "NOM_VOYAGEUR"), nomVoyageur);
        startActivity(intent);
    }
}
