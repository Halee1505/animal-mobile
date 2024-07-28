package com.funix.animal.adapder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.funix.animal.R;
import com.funix.animal.util.AssetsHelper;
import com.funix.animal.util.SharedPreferencesController;

import java.util.ArrayList;

public class AnimalItemAdapter extends RecyclerView.Adapter<AnimalItemAdapter.ViewHolder> {
    private ArrayList<String> animalList;
    private OnItemClickListener listener;
    private AssetsHelper AssetsHelper;
    private Context context;
    private SharedPreferencesController sharedPreferencesController;

    public AnimalItemAdapter(Context context, ArrayList<String> animalList, OnItemClickListener listener) {
        this.context = context;
        this.animalList = animalList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String animalPath);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView animalImageView;
        public TextView animalNameTextView;
        public ImageView animalIconView;

        public ViewHolder(View itemView) {
            super(itemView);

            AssetsHelper = new AssetsHelper();
            sharedPreferencesController = new SharedPreferencesController(context);

            animalImageView = itemView.findViewById(R.id.item_view_image);
            animalNameTextView = itemView.findViewById(R.id.item_view_title);
            animalIconView = itemView.findViewById(R.id.icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(animalList.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public AnimalItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalItemAdapter.ViewHolder holder, int position) {

        String animal = animalList.get(position);
        holder.animalNameTextView.setText(AssetsHelper.getNameFromPath(animal));
        Boolean isFav = sharedPreferencesController.getBoolean(animal + "_fav", false);
        holder.animalIconView.setVisibility( isFav ? View.VISIBLE : View.INVISIBLE);
        holder.animalImageView.setImageBitmap(AssetsHelper.getBitmapFromPath(context,animal));

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
