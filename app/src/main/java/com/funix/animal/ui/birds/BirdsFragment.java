package com.funix.animal.ui.birds;

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
import com.funix.animal.databinding.FragmentBirdsBinding;
import com.funix.animal.model.Animal;
import com.funix.animal.model.AnimalViewModel;

import java.util.ArrayList;
import java.util.List;

public class BirdsFragment extends Fragment implements AnimalAdapter.OnItemClickListener {

    private FragmentBirdsBinding binding;
    private AnimalDao animalDao;
    private AnimalViewModel animalViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBirdsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize AnimalDao
        animalDao = new AnimalDao(requireContext());

        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);

        renderListItems();
        return root;
    }

    public void renderListItems() {
        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerViewBirds;  // Corrected RecyclerView reference
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4); // 2 columns
        recyclerView.setLayoutManager(gridLayoutManager);

        // Fetch the list of birds
        List<Animal> birdList = animalDao.getAnimalsByType("bird");

        // Convert List to ArrayList
        ArrayList<Animal> birdArrayList = new ArrayList<>(birdList);

        // Set up the RecyclerView with the AnimalAdapter
        AnimalAdapter adapter = new AnimalAdapter(birdArrayList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Animal item) {
        navigateToDetail(item.getName(), item.getType());  // Assuming Animal has a getType() method
    }

    private void navigateToDetail(String animalName, String animalType) {
        NavDirections action = BirdsFragmentDirections.actionNavBirdsToDetailFragment(animalName, animalType);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(action);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (animalDao != null) {
            animalDao.close();
        }
        binding = null;
    }
}
