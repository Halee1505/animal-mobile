package com.funix.animal.ui.mammals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.funix.animal.adapder.AnimalAdapter;
import com.funix.animal.database.AnimalDao;
import com.funix.animal.databinding.FragmentMammalsBinding;
import com.funix.animal.model.Animal;
import com.funix.animal.model.AnimalViewModel;

import java.util.ArrayList;

public class MammalsFragment extends Fragment implements AnimalAdapter.OnItemClickListener {
    private AnimalDao animalDao;
    private FragmentMammalsBinding binding;
    private AnimalViewModel animalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMammalsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Initialize AnimalDao
        animalDao = new AnimalDao(requireContext());

        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
        renderListItems();
        return root;
    }

    public void renderListItems() {
        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerViewMammals;  // Corrected RecyclerView reference

        // Initialize item list
        ArrayList<Animal> listMammals = new ArrayList<>(animalDao.getAnimalsByType("mammal"));

        // Set up the RecyclerView with GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4); // 2 columns
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set adapter
        AnimalAdapter adapter = new AnimalAdapter(listMammals, this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(Animal item) {
        navigateToDetail(item.getName(), item.getType());  // Assuming Animal has a getType() method
    }
    private void navigateToDetail(String animalName, String animalType) {

        NavDirections action = MammalsFragmentDirections.actionNavMammalsToDetailFragment(animalName, animalType);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(action);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}