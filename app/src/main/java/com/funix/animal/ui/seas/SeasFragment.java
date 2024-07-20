package com.funix.animal.ui.seas;

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
import com.funix.animal.databinding.FragmentSeasBinding;
import com.funix.animal.model.Animal;
import com.funix.animal.model.AnimalViewModel;

import java.util.ArrayList;

public class SeasFragment extends Fragment implements AnimalAdapter.OnItemClickListener {
    private FragmentSeasBinding binding;
    private AnimalDao animalDao;
    private AnimalViewModel animalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSeasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Initialize AnimalDao
        animalDao = new AnimalDao(requireContext());

        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
        renderListItems();

        return root;
    }

    public void renderListItems() {
        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerViewSeas;  // Corrected RecyclerView reference
        ArrayList<Animal> listSeas = new ArrayList<>(animalDao.getAnimalsByType("sea"));

        // Set up the RecyclerView with GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4); // 2 columns
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set adapter
        AnimalAdapter adapter = new AnimalAdapter(listSeas, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Animal animal) {
        navigateToDetail(animal.getName());
    }

    private void navigateToDetail(String detailText) {

            NavDirections action = SeasFragmentDirections.actionNavSeasToDetailFragment(detailText);
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(action);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}