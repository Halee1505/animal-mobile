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

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private AnimalDao animalDao;
    private List<Animal> animalList;
    private ViewPager2 viewPager;
    private DetailPagerAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        animalDao = new AnimalDao(getContext());
        animalList = animalDao.getAllAnimals();
        System.out.println("animal:" + animalList.get(0));
        List<String> animalNames = new ArrayList<>();
        for (Animal animal : animalList) {
            animalNames.add(animal.getName());
        }

        viewPager = binding.viewPager;
        adapter = new DetailPagerAdapter(this, animalNames);
        viewPager.setAdapter(adapter);

        return root;
    }
}
