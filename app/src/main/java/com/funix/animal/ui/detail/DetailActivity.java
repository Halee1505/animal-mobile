// DetailActivity.java
package com.funix.animal.ui.detail;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.funix.animal.R;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        viewPager = findViewById(R.id.view_pager);

        // Dummy data, replace with actual animal details
        List<String> animalDetails = List.of("Animal 1", "Animal 2", "Animal 3");

        adapter = new ViewPagerAdapter(this, animalDetails);
        viewPager.setAdapter(adapter);

        // Set the current item to the selected animal
        int currentItem = getIntent().getIntExtra("current_item", 0);
        viewPager.setCurrentItem(currentItem, false);
    }
}
