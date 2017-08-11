package com.stefano.gioda.mytournament.activity;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.stefano.gioda.mytournament.R;
import com.stefano.gioda.mytournament.classi.Squadra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefano Gioda on 8/11/17.
 */

public class SquadraSpinnerAdapter extends ArrayAdapter<Squadra> implements SpinnerAdapter
{
    private Context mContext;
    private ArrayList<Squadra> data;
    private LayoutInflater inflater;

    public SquadraSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Squadra> objects) {
        super(context, resource, objects);
        objects.add(0,null);

        /********** Take passed values **********/
        mContext = context;
        data = objects;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        Squadra tempValues = data.get(position);

        convertView = inflater.inflate(R.layout.spinner_row, parent, false);

        TextView squadraName = (TextView) convertView.findViewById(R.id.squadra_spinner_nome);

        if (position == 0) {
            squadraName.setText("Scegliere...");
        } else {
            squadraName.setText(tempValues.getNome());

        }

        return convertView;
    }
}
