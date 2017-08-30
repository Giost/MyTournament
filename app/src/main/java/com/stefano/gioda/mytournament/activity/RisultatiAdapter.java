package com.stefano.gioda.mytournament.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Torneo;
import com.stefano.gioda.mytournament.classi.TorneoEliminazione;

import java.util.ArrayList;

/**
 * Created by Stefano Gioda on 8/30/17.
 */

public class RisultatiAdapter extends RecyclerView.Adapter<RisultatiAdapter.MyViewHolder> {
    private Torneo torneo;
    private ArrayList<ArrayList<Integer>> squadre;
    private ArrayList<String> risultati;
    private boolean eliminazione;

    public RisultatiAdapter(Torneo torneo) {
        this.torneo = torneo;
        squadre = new ArrayList<>();
        risultati = new ArrayList<>();
        for  (int i=0;i<torneo.getNumeroFasi();i++)
        {
            squadre.addAll(torneo.getCalendario(i));
            risultati.addAll(torneo.getRisultati(i));
        }
        eliminazione = torneo instanceof TorneoEliminazione;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_risultati,
                parent,
                false);

        MyViewHolder vh = new MyViewHolder(v,eliminazione,torneo.getSquadre().size());
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //System.out.println("Chiamato onBindViewHolder alla posizione: " + position);
        holder.bind(squadre.get(position), risultati.get(position), position);
    }

    @Override
    public int getItemCount() {
        return squadre.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView header, squadraCasa, risultato, squadraFuoriCasa;
        private boolean eliminazione;
        private int numeroSquadre;

        public MyViewHolder(View itemView, boolean eliminazione, int numeroSquadre) {
            super(itemView);
            this.eliminazione = eliminazione;
            this.numeroSquadre = numeroSquadre;
            header = (TextView) itemView.findViewById(R.id.risultati_item_header);
            squadraCasa = (TextView) itemView.findViewById(R.id.risulati_item_casa);
            squadraFuoriCasa = (TextView) itemView.findViewById(R.id.risulati_item_fuori_casa);
            risultato = (TextView) itemView.findViewById(R.id.risulati_item_risultato);
        }

        public void bind(final ArrayList<Integer> squadre,final String stringaRisultato, int position) {
            int num;
            if (eliminazione) {
                num = numeroSquadre-position;
                if ((num & (num-1)) == 0) {
                    num=num/2;
                    header.setText((num>2 ? num+"° di finale" : num==2 ? "Semifinale" : "Finale"));
                    header.setVisibility(View.VISIBLE);
                } else
                    header.setVisibility(View.GONE);
            }
            else
            {
                if (position%(numeroSquadre/2)==0)
                {
                    header.setText((position/(numeroSquadre/2)+1)+"° Giornata");
                    header.setVisibility(View.VISIBLE);
                } else
                    header.setVisibility(View.GONE);
            }

            squadraCasa.setText((squadre.get(0)!=-1 ? torneo.getSquadre().get(squadre.get(0)).getNome() : ""));
            squadraFuoriCasa.setText((squadre.get(1)!=-1 ? torneo.getSquadre().get(squadre.get(1)).getNome() : ""));
            risultato.setText((!stringaRisultato.equals("-1 - -1") ? stringaRisultato : ""));
            /*
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), VisualizzaComponentiSquadra.class);
                    i.putExtra("Squadra", squadra);
                    view.getContext().startActivity(i);
                }
            });*/
        }
    }
}