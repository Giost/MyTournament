package com.stefano.gioda.mytournament.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stefano.gioda.mytournament.R;

public class InserisciRisultato extends AppCompatActivity {
    private boolean eliminazione;
    private int indexSquadraCasa;
    private int indexSquadraFuoriCasa;
    private int indexTorneo;
    private TextView squadraCasa;
    private TextView squadraFuoriCasa;
    private EditText goalCasa;
    private EditText goalFuoriCasa;
    private Data holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci_risultato);

        holder = Data.getInstance();

        indexTorneo = getIntent().getIntExtra("INDICE",0);
        eliminazione = getIntent().getBooleanExtra("ELIMINAZIONE",false);
        indexSquadraCasa = getIntent().getIntExtra("INDICE_CASA",0);
        indexSquadraFuoriCasa = getIntent().getIntExtra("INDICE_FUORI_CASA",0);

        squadraCasa = (TextView) findViewById(R.id.squadra_casa);
        squadraFuoriCasa = (TextView) findViewById(R.id.squadra_fuori_casa);
        goalCasa = (EditText) findViewById(R.id.goal_squadra_casa);
        goalFuoriCasa = (EditText) findViewById(R.id.goal_squadra_fuori_casa);

        squadraCasa.setText((eliminazione ?
                holder.getTorneiEliminazione().get(indexTorneo).getSquadre().get(indexSquadraCasa).getNome() :
                holder.getTorneiItaliana().get(indexTorneo).getSquadre().get(indexSquadraCasa).getNome())
        );

        squadraFuoriCasa.setText((eliminazione ?
                holder.getTorneiEliminazione().get(indexTorneo).getSquadre().get(indexSquadraFuoriCasa).getNome() :
                holder.getTorneiItaliana().get(indexTorneo).getSquadre().get(indexSquadraFuoriCasa).getNome())
        );

        Button conferma = (Button) findViewById(R.id.conferma_risultato);
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    int goal1 = Integer.parseInt(goalCasa.getText().toString());
                    int goal2 = Integer.parseInt(goalFuoriCasa.getText().toString());

                    if (goal1>=0 && goal2>=0 )
                    {
                        if (!eliminazione || goal1 != goal2)
                        {
                            (eliminazione ?
                                    holder.getTorneiEliminazione().get(indexTorneo) :
                                    holder.getTorneiItaliana().get(indexTorneo)).updateRisultato(indexSquadraCasa, indexSquadraFuoriCasa, goal1, goal2);

                            holder.salva();

                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
                            dialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });

                            AlertDialog dialog = dialogBuilder.create();
                            dialog.setTitle("Successo!");
                            dialog.setMessage("Operazione avvenuto con successo");
                            dialog.show();
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), "Goal inseriti non validi, non può finire in parità", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(view.getContext(), "Goal inserito non valido, deve essere maggiore di zero", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Goal inserito non valido", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }
            }
        });

        Button annulla = (Button) findViewById(R.id.annulla_risultato);
        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
