package com.funix.animal.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.funix.animal.R;
import com.funix.animal.database.AnimalDao;
import com.funix.animal.databinding.FragmentDetailBinding;
import com.funix.animal.model.Animal;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private AnimalDao animalDao;
    private Animal animal;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        animalDao = new AnimalDao(getContext());

        // Retrieve the itemId argument
        if (getArguments() != null) {
            String detailText = DetailFragmentArgs.fromBundle(getArguments()).getDetailText();
            // Query the database to get the animal details by title
            animal = animalDao.getAnimalByName(detailText);
            if (animal != null) {
                // Set the text and image
                binding.textTitle.setText(animal.getName());
                binding.detailTextView.setText(animal.getContent());
                Glide.with(this).load(animal.getPhoto()).into(binding.detailImageView);
                updateFavoriteIcon();

                binding.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleFavorite();
                    }
                });
            }
        }


        return root;
    }
    private void updateFavoriteIcon() {
        if (animal.isFav()) {
            binding.icon.setImageResource(R.drawable.ic_heart_full);
        } else {
            binding.icon.setImageResource(R.drawable.ic_heart);
        }
    }

    private void toggleFavorite() {
        animal.setFav(!animal.isFav());
        animalDao.updateAnimal(animal);
        updateFavoriteIcon();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (animalDao != null) {
            animalDao.close();
        }
        binding = null;
    }
    public static DetailFragment newInstance(String animalDetail) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("animal_detail", animalDetail);
        fragment.setArguments(args);
        return fragment;
    }
}
