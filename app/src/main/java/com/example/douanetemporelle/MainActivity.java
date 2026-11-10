package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText edtNomAh;
    private EditText edtNaissanceAh;
    private EditText edtDestinationAh;
    private Spinner spMotifAh;
    private Button btnInspecterAh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1:narbt 3anasr interface bl code
        edtNomAh = findViewById(R.id.edt_nom_ah);
        edtNaissanceAh = findViewById(R.id.edt_naissance_ah);
        edtDestinationAh = findViewById(R.id.edt_destination_ah);
        spMotifAh = findViewById(R.id.sp_motif_ah);
        btnInspecterAh = findViewById(R.id.btn_inspecter_ah);

        // 2: liste te3 asbeb safr
        String[] motifs = {
                "Tourisme",
                "Modification du passé",
                "Fuite"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                motifs
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMotifAh.setAdapter(adapter);

        // 3: ki yenzel 3al bouton mta3 fahs el profil
        btnInspecterAh.setOnClickListener(view -> inspecterProfil());
    }

    private void inspecterProfil() {
        // 4: njibou el ma3loumet mel interface
        String nom = edtNomAh.getText().toString().trim();
        String naissanceTexte = edtNaissanceAh.getText().toString().trim();
        String destinationTexte = edtDestinationAh.getText().toString().trim();
        String motif = spMotifAh.getSelectedItem().toString();

        // 5: net2akdou eli les champs lkol m3abbin
        if (nom.isEmpty() || naissanceTexte.isEmpty() || destinationTexte.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            return;
        }

        int anneeNaissance;
        int anneeDestination;

        // 6: n7awlou les années l ar9am w net2akdou eli s7a7
        try {
            anneeNaissance = Integer.parseInt(naissanceTexte);
            anneeDestination = Integer.parseInt(destinationTexte);

            if (anneeNaissance <= 0 || anneeDestination <= 0) {
                Toast.makeText(this, "Les années doivent être positives.", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez saisir des années valides.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 7: nab3thou les ma3loumet lel activité thenya b Intent explicite
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
        intent.putExtra(ExtraKeys.key(StudentConfig.MATRICULE, "NOM_VOYAGEUR"), nom);
        intent.putExtra(ExtraKeys.key(StudentConfig.MATRICULE, "ANNEE_NAISSANCE"), anneeNaissance);
        intent.putExtra(ExtraKeys.key(StudentConfig.MATRICULE, "ANNEE_DESTINATION"), anneeDestination);
        intent.putExtra(ExtraKeys.key(StudentConfig.MATRICULE, "MOTIF_VOYAGE"), motif);
        startActivity(intent);
    }
}
