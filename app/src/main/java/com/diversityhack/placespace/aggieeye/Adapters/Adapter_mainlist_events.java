package com.diversityhack.placespace.aggieeye.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diversityhack.placespace.aggieeye.Models.EventDetails;
import com.diversityhack.placespace.aggieeye.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Srujan on 6/9/2017.
 */

public class Adapter_mainlist_events extends RecyclerView.Adapter<Adapter_mainlist_events.MyViewHolder>{

    private final LayoutInflater inflater;
    List<EventDetails> data = Collections.emptyList();
    Context context;

    public Adapter_mainlist_events(Context context, List<EventDetails> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Font.setAllTextView(parent,context);
        View view = inflater.inflate(R.layout.customrow_mainlist_events,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EventDetails hospital = data.get(position);
        holder.hospitalName.setText(hospital.getName());

        //Setting up random color
        int n = position%6;
        switch (n){
            case 0:case 1:
                holder.coloredPenal.setBackgroundColor(Color.parseColor("#e53935"));
                break;
            case 2:case 3:
                holder.coloredPenal.setBackgroundColor(Color.parseColor("#3949AB"));
                break;
            default:
                holder.coloredPenal.setBackgroundColor(Color.parseColor("#43A047"));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView hospitalName;
        View coloredPenal;
        public MyViewHolder(View itemView) {
            super(itemView);
            hospitalName = (TextView) itemView.findViewById(R.id.hospital_name);
            coloredPenal = itemView.findViewById(R.id.colored_penal);
        }
    }
}
