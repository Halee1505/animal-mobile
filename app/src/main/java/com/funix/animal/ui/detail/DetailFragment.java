package com.funix.animal.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.funix.animal.database.AnimalDao;
import com.funix.animal.databinding.FragmentDetailBinding;
import com.funix.animal.model.Animal;
import com.funix.animal.util.AssetsHelper;

import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private AnimalDao animalDao;
    private List<Animal> animalList;
    private ViewPager2 viewPager;
    private AssetsHelper AssetsHelper;

    private DetailPagerAdapter adapter;
    final String ANIMAL_NAME = "animal_name";
    final String ANIMAL_TYPE = "animal_type";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        AssetsHelper assetsHelper = new AssetsHelper();
        if (getArguments() != null) {
            String animalType = getArguments().getString(ANIMAL_TYPE);
            List<String> animalPaths = assetsHelper.getAnimalImages(getContext(), animalType);

            viewPager = binding.viewPager;
            adapter = new DetailPagerAdapter(this, animalPaths, animalType);
            viewPager.setAdapter(adapter);

            // Retrieve the passed argument
            String animalName = getArguments().getString(ANIMAL_NAME);
            int initialPosition = animalPaths.indexOf(animalName);
            if (initialPosition >= 0) {
                viewPager.setCurrentItem(initialPosition, false);
            }
        }

        return root;
    }
}
