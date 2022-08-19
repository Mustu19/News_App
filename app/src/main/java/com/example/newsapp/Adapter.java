package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context ;
    ArrayList<ModalClass>  modalClassArrayList;

    public Adapter(Context context, ArrayList<ModalClass> modalClassArrayList) {
        this.context = context;
        this.modalClassArrayList = modalClassArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item , null , false) ;
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder , int position) {

        // we have simply set our data as per our selected item

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , webview.class) ;
                intent.putExtra("url" , modalClassArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.mtime.setText("Published At:-" + modalClassArrayList.get(position).getPublishedAt());
        holder.mauthor.setText( modalClassArrayList.get(position).getAuthor());
        holder.mcontent.setText( modalClassArrayList.get(position).getDescription());
        holder.mheading.setText( modalClassArrayList.get(position).getTitle());

        Glide.with(context).load(modalClassArrayList.get(position).getUrlToImage()).into(holder.imageView) ;

    }

    @Override
    public int getItemCount() {
        return modalClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mheading , mauthor , mcontent , mtime;
        ImageView imageView ;
        CardView cardView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mheading = itemView.findViewById(R.id.mainheading);
            mauthor = itemView.findViewById(R.id.author);
            mcontent = itemView.findViewById(R.id.content);
            mtime = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageview);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
