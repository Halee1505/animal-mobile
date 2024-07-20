// ViewPagerAdapter.java
package com.funix.animal.ui.detail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private List<String> animalDetails;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<String> animalDetails) {
        super(fragmentActivity);
        this.animalDetails = animalDetails;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
    return DetailFragment.newInstance(animalDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return animalDetails.size();
    }
}
