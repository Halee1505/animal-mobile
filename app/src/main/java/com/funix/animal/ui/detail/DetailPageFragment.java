package com.funix.animal.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.funix.animal.R;
import com.funix.animal.database.AnimalDao;
import com.funix.animal.model.Animal;

public class DetailPageFragment extends Fragment {
    private static final String ARG_ANIMAL_NAME = "animal_name";

    public static DetailPageFragment newInstance(String animalName) {
        DetailPageFragment fragment = new DetailPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ANIMAL_NAME, animalName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_page, container, false);
        TextView textView = view.findViewById(R.id.detailTextView);
        ImageView imageView = view.findViewById(R.id.detailImageView);
        ImageView iconView = view.findViewById(R.id.icon);
        TextView titleView = view.findViewById(R.id.text_title);

        if (getArguments() != null) {
            String animalName = getArguments().getString(ARG_ANIMAL_NAME);
            AnimalDao animalDao = new AnimalDao(getContext());
            Animal animal = animalDao.getAnimalByName(animalName);

            if (animal != null) {
                titleView.setText(animal.getName());
                textView.setText(animal.getContent());
                Glide.with(this).load(animal.getPhoto()).into(imageView);
                iconView.setOnClickListener(v -> {
                    animal.setFav(!animal.isFav());
                    animalDao.updateAnimal(animal);
                    iconView.setImageResource(animal.isFav() ? R.drawable.ic_heart_full : R.drawable.ic_heart);
                });
            }
        }
        return view;
    }
}
