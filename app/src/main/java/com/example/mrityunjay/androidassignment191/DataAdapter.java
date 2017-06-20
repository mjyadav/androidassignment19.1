package com.example.mrityunjay.androidassignment191;

/**
 * Created by Mrityunjay on 19-06-2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    Context context;
    List<DataHandler> data;
    ClickListener clickListener;

    public DataAdapter(Context context, List<DataHandler> data){
        this.context=context;
        this.data=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.id.setText(data.get(position).getId());
        holder.main.setText(data.get(position).getMain()+"");
        holder.name.setText(data.get(position).getName());
        holder.discription.setText(data.get(position).getDiscription());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener!=null){
                    clickListener.ItemClicked(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, main, name,discription;
        RelativeLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            id= (TextView)itemView.findViewById(R.id.text1);
            main= (TextView)itemView.findViewById(R.id.text2);
            name= (TextView)itemView.findViewById(R.id.text3);
            discription=(TextView)itemView.findViewById(R.id.text4);
            parent= (RelativeLayout)itemView.findViewById(R.id.parent);
        }
    }

    public interface ClickListener{
        void ItemClicked(View v, int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }
}
