package com.stefano.gioda.mytournament.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Squadra;
import com.stefano.gioda.mytournament.classi.TorneoEliminazione;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

import java.util.ArrayList;

public class CreaTorneo extends AppCompatActivity {

    private ArrayList<Squadra> squadreRegistrate;
    private Data holder;
    private Spinner tipoTorneo;
    private EditText numeroSquadre;
    private EditText nomeTorneo;
    private LinearLayout layoutSquadre;
    private LinearLayout layoutBtn;
    private TextView textNumSquad;
    private LayoutInflater inflater;
    private ArrayList<LinearLayout> layoutSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_torneo);

        holder = Data.getInstance();

        squadreRegistrate = holder.getSquadre();

        layoutSpinner = new ArrayList<>();
        layoutSquadre = (LinearLayout) findViewById(R.id.layout_spinner);
        layoutBtn = (LinearLayout) findViewById(R.id.layout_btn);
        textNumSquad = (TextView) findViewById(R.id.text_num_squad);
        inflater = LayoutInflater.from(getApplicationContext());

        //LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        /*LinearLayout layoutSpinner = (LinearLayout) inflater.inflate(R.layout.spinner_inflater,null,false);
        LinearLayout layoutSpinner2 = (LinearLayout) inflater.inflate(R.layout.spinner_inflater,null,false);

        ((Spinner)layoutSpinner.findViewById(R.id.spinner)).setAdapter(new SquadraSpinnerAdapter(this,R.layout.spinner_row,new ArrayList<>(squadreRegistrate)));
        ((Spinner)layoutSpinner2.findViewById(R.id.spinner)).setAdapter(new SquadraSpinnerAdapter(this,R.layout.spinner_row,new ArrayList<>(squadreRegistrate)));

        layoutSquadre.addView(layoutSpinner);
        layoutSquadre.addView(layoutSpinner2);*/

        tipoTorneo = (Spinner) findViewById(R.id.spinner_tipo);

        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this,R.array.tipi_torneo,android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipoTorneo.setAdapter(adapterTipo);

        nomeTorneo = (EditText) findViewById(R.id.nome_torneo);
        numeroSquadre = (EditText) findViewById(R.id.numero_squadre);

        final Button btnTipo = (Button) findViewById(R.id.btn_tipo);

        btnTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    int numero = Integer.parseInt(numeroSquadre.getText().toString());
                    if (nomeTorneo.getText().toString().isEmpty())
                    {
                        nomeTorneo.setError("Campo obbligatorio");
                    }
                    else if (numeroSquadre.getText().toString().isEmpty() || numero < 2 || numero % 2 != 0)
                    {
                        numeroSquadre.setError("Non sono state rispettate le regole per il numero di squadre");
                    }
                    else if (tipoTorneo.getSelectedItemPosition() == 1 && (numero & (numero-1))!=0)
                    {
                        numeroSquadre.setError("Non sono state rispettate le regole per il numero di squadre");
                    }
                    else
                    {
                        btnTipo.setVisibility(View.GONE);
                        tipoTorneo.setEnabled(false);
                        tipoTorneo.setFocusable(false);
                        numeroSquadre.setEnabled(false);
                        numeroSquadre.setFocusable(false);
                        nomeTorneo.setEnabled(false);
                        nomeTorneo.setFocusable(false);
                        textNumSquad.setVisibility(View.GONE);

                        layoutSquadre.setVisibility(View.VISIBLE);
                        for (int i=0;i<numero;i++)
                        {
                            layoutSpinner.add((LinearLayout) inflater.inflate(R.layout.spinner_inflater,null,false));

                            ((Spinner)layoutSpinner.get(i).findViewById(R.id.spinner)).setAdapter(new SquadraSpinnerAdapter(view.getContext(),R.layout.spinner_row,new ArrayList<>(squadreRegistrate)));

                            layoutSquadre.addView(layoutSpinner.get(i));
                            //layoutSquadre.addView(inflater.inflate(R.layout.spinner_inflater,null,false));
                        }
                        layoutBtn.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e)
                {
                    numeroSquadre.setError("Non sono state rispettate le regole per il numero di squadre");
                    System.out.println("!!!!"+e);
                }
            }
        });

        Button btnCrea = (Button) findViewById(R.id.crea_nuovo_torneo);
        btnCrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> indiciSquadre = new ArrayList<Integer>();
                int flag = 0; // 0=tutto bene, non ci sono ripetizioni nè spinner vuoti, 1=spinner vuoto, 2=ripetizione squadra

                for (int i=0;i<layoutSpinner.size() && flag==0;i++)
                {
                    int index = ((Spinner)layoutSpinner.get(i).findViewById(R.id.spinner)).getSelectedItemPosition();

                    if (index!=0)
                    {
                        for (int j : indiciSquadre)
                        {
                            if (index==j)
                            {
                                flag=2;
                            }
                        }
                        indiciSquadre.add(index);
                    }
                    else
                    {
                        flag=1;
                    }
                }

                if (flag==1)
                {
                    Toast.makeText(view.getContext(),"Una squadra non è stata selezionata", Toast.LENGTH_SHORT).show();
                }
                else if (flag==2)
                {
                    Toast.makeText(view.getContext(),"Una squadra è stata selezionata più volte", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Squadra> squadre = new ArrayList<Squadra>();

                    for (int i : indiciSquadre)
                    {
                        i--;
                        squadre.add(squadreRegistrate.get(i));
                    }

                    if (tipoTorneo.getSelectedItemPosition()==0)
                    {
                        holder.addTorneoItaliana(new TorneoItaliana(nomeTorneo.getText().toString(),squadre));
                    }
                    else
                    {
                        holder.addTorneoEliminazione(new TorneoEliminazione(nomeTorneo.getText().toString(),squadre));
                    }

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
            }
        });

        Button btnAnnulla = (Button) findViewById(R.id.annulla_nuovo_torneo);
        btnAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
