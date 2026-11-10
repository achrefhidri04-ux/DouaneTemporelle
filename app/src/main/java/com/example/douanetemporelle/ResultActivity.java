package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 1: njibou el verdict w esm el voyageur mel Intent
        boolean verdict = getIntent().getBooleanExtra(
                ExtraKeys.key(StudentConfig.MATRICULE, "VERDICT"), false
        );
        String nom = getIntent().getStringExtra(
                ExtraKeys.key(StudentConfig.MATRICULE, "NOM_VOYAGEUR")
        );

        if (nom == null) {
            nom = "Voyageur";
        }

        // 2: nربطو les éléments mta3 interface résultat
        LinearLayout layoutResultat = findViewById(R.id.layout_resultat_ah);
        TextView tvResultatAh = findViewById(R.id.tv_resultat_ah);
        Button btnNouveauAh = findViewById(R.id.btn_nouveau_ah);

        // 3: nbaddlou لون el fond w el message 7asb el décision
        if (verdict) {
            layoutResultat.setBackgroundColor(Color.rgb(46, 125, 50));
            tvResultatAh.setText("Bon voyage " + nom + " !");
        } else {
            layoutResultat.setBackgroundColor(Color.rgb(183, 28, 28));
            tvResultatAh.setText(nom + " est en état d'arrestation temporelle.");
        }

        // 4: ki yenzel 3al bouton nouveau voyageur, narj3ou lel MainActivity w nsakrou les pages l9dom
        btnNouveauAh.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
