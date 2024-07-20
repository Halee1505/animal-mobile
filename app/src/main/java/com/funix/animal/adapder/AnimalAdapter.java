package com.funix.animal.adapder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.funix.animal.R;
import com.funix.animal.model.Animal;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private ArrayList<Animal> animalList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Animal animal);
    }

    public AnimalAdapter(ArrayList<Animal> animalList, OnItemClickListener listener) {
        this.animalList = animalList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal item = animalList.get(position);
        System.out.println("item.getName() = " + position);
        holder.title.setText(item.getName());
        Glide.with(holder.itemView.getContext()).load(item.getPhoto()).into(holder.imageView);
        if (item.isFav()) {
            holder.icon.setVisibility(View.VISIBLE);
        }
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;
        public ImageView icon;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.item_view_title);
            imageView = view.findViewById(R.id.item_view_image);
            icon = view.findViewById(R.id.icon);
        }

        public void bind(final Animal item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
