package com.funix.animal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.funix.animal.R;
import com.funix.animal.database.AnimalDao;
import com.funix.animal.model.Animal;
import com.funix.animal.databinding.FragmentHomeBinding;
import com.funix.animal.model.AnimalViewModel;
import com.funix.animal.util.DataGenerator;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private AnimalDao animalDao;
    private AnimalViewModel animalViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        animalDao = new AnimalDao(getContext());
        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
        clearData();
        setData();

        return root;
    }

    private void setData() {
        // Add bird data
        ArrayList<Animal> birds = DataGenerator.generateBirds();
        for (Animal bird : birds) {
            animalDao.insertAnimal(bird);
        }

        // Add mammal data
        ArrayList<Animal> mammals = DataGenerator.generateMammals();
        for (Animal mammal : mammals) {
            animalDao.insertAnimal(mammal);
        }

        // Add sea animal data
        ArrayList<Animal> seaAnimals = DataGenerator.generateSeaAnimals();
        for (Animal seaAnimal : seaAnimals) {
            animalDao.insertAnimal(seaAnimal);
        }
        // Set all animals data to the ViewModel
        ArrayList<Animal> allAnimals = animalDao.getAllAnimals();
        animalViewModel.setAnimals(allAnimals);
    }

    private ArrayList<Animal> getData() {
        return animalDao.getAllAnimals();
    }

    private  void clearData() {
        ArrayList<Animal> animals = animalDao.getAllAnimals();
        for (Animal animal : animals) {
            animalDao.deleteAnimal(animal.getName());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        animalDao.close();
        binding = null;
    }
}
