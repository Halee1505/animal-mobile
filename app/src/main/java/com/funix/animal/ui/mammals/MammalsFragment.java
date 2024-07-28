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

import com.funix.animal.adapder.AnimalItemAdapter;
import com.funix.animal.databinding.FragmentMammalsBinding;
import com.funix.animal.model.AnimalViewModel;
import com.funix.animal.util.AssetsHelper;

import java.util.ArrayList;

public class MammalsFragment extends Fragment implements AnimalItemAdapter.OnItemClickListener {

    private FragmentMammalsBinding binding;
    private AnimalViewModel animalViewModel;
    private AssetsHelper assetsHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMammalsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        assetsHelper = new AssetsHelper();

        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);

        renderListItems();
        return root;
    }

    public void renderListItems() {
        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerViewMammals;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4); // 4 columns
        recyclerView.setLayoutManager(gridLayoutManager);

        // Fetch the list of mammal image URLs from assets
        ArrayList<String> mammalUrls = assetsHelper.getAnimalImages(getContext(), "mammal");

        // Set up the RecyclerView with the AnimalItemAdapter
        AnimalItemAdapter adapter = new AnimalItemAdapter(getContext(), mammalUrls, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String animalPath) {
        navigateToDetail(animalPath, "mammal");  // Assuming the type is mammal for this fragment
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
