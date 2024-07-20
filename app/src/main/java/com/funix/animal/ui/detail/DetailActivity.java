package com.funix.animal.ui.detail;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.funix.animal.R;
import com.funix.animal.database.AnimalDao;
import com.funix.animal.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ViewPager2 viewPager = findViewById(R.id.viewPager);

        AnimalDao animalDao = new AnimalDao(this);
        ArrayList<Animal> animalList = animalDao.getAllAnimals();

        List<String> animalNames ;
        if (animalList != null && !animalList.isEmpty()) {
            animalNames = new ArrayList<>();
            for (Animal animal : animalList) {
                animalNames.add(animal.getName());
            }
            } else {
            animalNames = new ArrayList<>();
        }

        if (animalNames != null && !animalNames.isEmpty()) {
            // Set up the ViewPager with the adapter
            DetailPagerAdapter adapter = new DetailPagerAdapter(this, animalNames);
            viewPager.setAdapter(adapter);
        } else {
            // Handle case where animal names list is null or empty
            // You can show a message or a placeholder fragment
        }
    }
}
