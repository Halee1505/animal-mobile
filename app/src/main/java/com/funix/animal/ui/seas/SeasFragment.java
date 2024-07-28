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


import com.funix.animal.adapder.AnimalItemAdapter;
import com.funix.animal.databinding.FragmentSeasBinding;
import com.funix.animal.model.AnimalViewModel;
import com.funix.animal.util.AssetsHelper;

import java.util.ArrayList;

public class SeasFragment extends Fragment implements AnimalItemAdapter.OnItemClickListener {

    private FragmentSeasBinding binding;
    private AnimalViewModel animalViewModel;
    private AssetsHelper assetsHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSeasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        assetsHelper = new AssetsHelper();

        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);

        renderListItems();
        return root;
    }

    public void renderListItems() {
        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerViewSeas;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4); // 4 columns
        recyclerView.setLayoutManager(gridLayoutManager);

        // Fetch the list of sea animal image URLs from assets
        ArrayList<String> seaUrls = assetsHelper.getAnimalImages(getContext(), "sea");

        // Set up the RecyclerView with the AnimalItemAdapter
        AnimalItemAdapter adapter = new AnimalItemAdapter(getContext(), seaUrls, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String animalPath) {
        navigateToDetail(animalPath, "sea");  // Assuming the type is sea for this fragment
    }

    private void navigateToDetail(String animalName, String animalType) {
        NavDirections action = SeasFragmentDirections.actionNavSeasToDetailFragment(animalName, animalType);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(action);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
