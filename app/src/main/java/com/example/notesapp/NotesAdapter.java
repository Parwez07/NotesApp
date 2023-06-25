package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView .Adapter<NotesAdapter.MyViewHolder> {

    private List<Notes> notes = new ArrayList<>();

    OnitemClicked listner;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(notes.get(position).getTitle());
        holder.tvDescription.setText(notes.get(position).getDescription());
    }

    public Notes getNotes(int position){
        return notes.get(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Notes>notes){
        this.notes =notes;
        notifyDataSetChanged();
    }
    class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle ;
        TextView tvDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDiscription);
            tvTitle = itemView.findViewById(R.id.tbTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if(listner != null && position != RecyclerView.NO_POSITION){

                        listner.OnItemClicked(notes.get(position));
                    }
                }
            });
        }


    }

    public interface OnitemClicked {

        void OnItemClicked(Notes notes);
    }

    public void setOnItemClicked(OnitemClicked listner){
        this.listner = listner;
    }

}
