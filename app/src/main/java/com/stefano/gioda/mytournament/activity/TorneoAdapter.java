package com.stefano.gioda.mytournament.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Torneo;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mint on 8/30/17.
 */

public class TorneoAdapter extends RecyclerView.Adapter<TorneoAdapter.MyViewHolder> {

    private ArrayList<Torneo> items;
    private HashMap<Character, Integer> mAlphabeticIndex = new HashMap<>();

    public TorneoAdapter(ArrayList<Torneo> items) {
        this.items = items;
    }

    public void clearAlphabeticIndex() {
        mAlphabeticIndex.clear();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_torneo,
                parent,
                false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //System.out.println("Chiamato onBindViewHolder alla posizione: " + position);
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView header, nome, tipo, finito;

        public MyViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.torneo_item_header);
            nome = (TextView) itemView.findViewById(R.id.torneo_item_nome);
            tipo = (TextView) itemView.findViewById(R.id.torneo_item_tipo);
            finito = (TextView) itemView.findViewById(R.id.torneo_item_finito);
        }

        public void bind(final Torneo torneo, int position) {
            Character firstChar = torneo.getNome().toUpperCase().charAt(0);

            if (!mAlphabeticIndex.containsKey(firstChar) || mAlphabeticIndex.get(firstChar) == position) {
                header.setText(String.valueOf(firstChar));
                header.setVisibility(View.VISIBLE);
                if (mAlphabeticIndex.get(firstChar) == null)
                    mAlphabeticIndex.put(firstChar, position);
            } else
                header.setVisibility(View.GONE);

            nome.setText(torneo.getNome());

            if (torneo instanceof TorneoItaliana)
            {
                tipo.setText("All'italiana");
            }
            else
            {
                tipo.setText("Eliminazione diretta");
            }

            finito.setText((torneo.isFinito() ? "SI" : "NO"));

            /* IMPLEMENTAZIONE VISUALIZZAZIONE TORNEO
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