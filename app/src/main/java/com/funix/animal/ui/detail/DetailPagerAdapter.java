package com.funix.animal.ui.detail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class DetailPagerAdapter extends FragmentStateAdapter {
    private List<String> animalNames;

    public DetailPagerAdapter(@NonNull Fragment fragment, List<String> animalNames) {
        super(fragment);
        this.animalNames = animalNames;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        System.out.println("position:" + position);
        return DetailPageFragment.newInstance(animalNames.get(position));
    }

    @Override
    public int getItemCount() {
        return animalNames.size();
    }
}
