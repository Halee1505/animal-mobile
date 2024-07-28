package com.funix.animal.ui.detail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class DetailPagerAdapter extends FragmentStateAdapter {
    private List<String> animalNames;
    private String animalType;
    public DetailPagerAdapter(@NonNull Fragment fragment, List<String> animalNames, String animalType) {
        super(fragment);
        this.animalNames = animalNames;
        this.animalType = animalType;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return DetailPageFragment.newInstance(animalNames.get(position), animalType);
    }

    @Override
    public int getItemCount() {
        return animalNames.size();
    }
}
