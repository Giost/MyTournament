package com.stefano.gioda.mytournament.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Stefano Gioda on 9/4/17.
 */

public class ClassificaAdapter extends RecyclerView.Adapter<ClassificaAdapter.MyViewHolder> {

    private ArrayList<ArrayList<Integer>> items; // 0=indice squadra, 1=punti, 2=goal fatti, 3=goal subiti, 4=differenza reti
    private TorneoItaliana torneo;

    public ClassificaAdapter(TorneoItaliana torneo) {
        this.torneo = torneo;
        items = new ArrayList<>();
        for (int i=0;i<torneo.getSquadre().size();i++)
        {
            items.add(new ArrayList<Integer>(Arrays.asList(i,torneo.getPunti().get(i),torneo.getGoalFatti().get(i),torneo.getGoalSubiti().get(i),torneo.getDifferenzaReti().get(i))));
        }
        Collections.sort(items, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> i1, ArrayList<Integer> i2) {
                // prima controlla i punti, chi ne ha di più viene spostato prima, se sono pari si confronta la differenza reti
                return (i1.get(1)<i2.get(1) ? 1 : i1.get(1)>i2.get(1) ? -1 : i1.get(4)<i2.get(4) ? 1 : i1.get(4)>i2.get(4) ? -1 : 0);
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_classifica,
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
        private TextView nome, punti, goalFatti, goalSubiti, differenzaReti;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.classifica_item_nome);
            punti = (TextView) itemView.findViewById(R.id.classifica_item_punti);
            goalFatti = (TextView) itemView.findViewById(R.id.classifica_item_gf);
            goalSubiti = (TextView) itemView.findViewById(R.id.classifica_item_gs);
            differenzaReti = (TextView) itemView.findViewById(R.id.classifica_item_dr);
        }

        public void bind(final ArrayList<Integer> item, int position) {

            nome.setText((position+1)+" "+torneo.getSquadre().get(item.get(0)).getNome());
            punti.setText(String.valueOf(item.get(1)));
            goalFatti.setText(String.valueOf(item.get(2)));
            goalSubiti.setText(String.valueOf(item.get(3)));
            differenzaReti.setText(String.valueOf(item.get(4)));
            //System.out.println(item.get(1)+"#"+item.get(2)+"#"+item.get(3)+"#"+item.get(4));
        }
    }
}