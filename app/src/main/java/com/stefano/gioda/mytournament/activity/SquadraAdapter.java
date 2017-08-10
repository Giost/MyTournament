package com.stefano.gioda.mytournament.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Squadra;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Stefano Gioda on 8/10/17.
 */

public class SquadraAdapter extends RecyclerView.Adapter<SquadraAdapter.MyViewHolder> {

    private ArrayList<Squadra> items;
    private HashMap<Character, Integer> mAlphabeticIndex = new HashMap<>();

    public SquadraAdapter(ArrayList<Squadra> items) {
        this.items = items;
    }

    public void clearAlphabeticIndex() {
        mAlphabeticIndex.clear();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_squadra,
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
        private TextView header, nome;

        public MyViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.squadre_item_header);
            nome = (TextView) itemView.findViewById(R.id.squadre_item_nome);
        }

        public void bind(final Squadra squadra, int position) {
            Character firstChar = squadra.getNome().toUpperCase().charAt(0);

            if (!mAlphabeticIndex.containsKey(firstChar) || mAlphabeticIndex.get(firstChar) == position) {
                header.setText(String.valueOf(firstChar));
                header.setVisibility(View.VISIBLE);
                if (mAlphabeticIndex.get(firstChar) == null)
                    mAlphabeticIndex.put(firstChar, position);
            } else
                header.setVisibility(View.GONE);

            nome.setText(squadra.getNome());

        }
    }
}