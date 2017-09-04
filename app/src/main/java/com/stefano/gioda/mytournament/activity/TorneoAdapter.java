package com.stefano.gioda.mytournament.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Torneo;
import com.stefano.gioda.mytournament.classi.TorneoEliminazione;
import com.stefano.gioda.mytournament.classi.TorneoItaliana;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Stefano Gioda on 8/30/17.
 */

public class TorneoAdapter extends RecyclerView.Adapter<TorneoAdapter.MyViewHolder> {

    private ArrayList<Torneo> items;

    public TorneoAdapter(ArrayList<Torneo> items) {
        this.items = items;
    }

    public void newDataSet (ArrayList<Torneo> newItems)
    {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
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
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView header, nome, tipo, finito;
        private Data holder;

        public MyViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.torneo_item_header);
            nome = (TextView) itemView.findViewById(R.id.torneo_item_nome);
            tipo = (TextView) itemView.findViewById(R.id.torneo_item_tipo);
            finito = (TextView) itemView.findViewById(R.id.torneo_item_finito);
            holder = Data.getInstance();
        }

        public void bind(final Torneo torneo, int position) {
            Character firstChar = torneo.getNome().toUpperCase().charAt(0);

            if (position==0 || firstChar!=items.get(position-1).getNome().toUpperCase().charAt(0)) {
                header.setText(String.valueOf(firstChar));
                header.setVisibility(View.VISIBLE);
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

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), VisualizzaRisultati.class);
                    i.putExtra("ELIMINAZIONE", torneo instanceof TorneoEliminazione );
                    i.putExtra("INDICE",(torneo instanceof TorneoEliminazione ? holder.getTorneiEliminazione().indexOf(torneo) : holder.getTorneiItaliana().indexOf(torneo)));
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}