package com.funix.animal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.funix.animal.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_seas, R.id.nav_mammals, R.id.nav_birds)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Set up the click listener for the header layout
        View headerView = navigationView.getHeaderView(0);
        LinearLayout headerLayout = headerView.findViewById(R.id.nav_header_main); // Ensure this ID matches your nav_header_main.xml

        headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.nav_home); // Adjust this ID to your HomeFragment's ID
                drawer.closeDrawer(navigationView); // Close the drawer after clicking
            }
        });

        // Inflate custom layout for menu items
        int[] menuItemIds = {R.id.nav_seas, R.id.nav_mammals, R.id.nav_birds};
        int[] menuItemIcons = {R.drawable.icon_seas, R.drawable.icon_mammals, R.drawable.icon_birds};
        String[] menuItemTitles = {getString(R.string.menu_seas), getString(R.string.menu_mammals), getString(R.string.menu_birds)};

        for (int i = 0; i < menuItemIds.length; i++) {
            MenuItem menuItem = navigationView.getMenu().findItem(menuItemIds[i]);
            if (menuItem != null) {
                View customView = LayoutInflater.from(this).inflate(R.layout.menu_item_with_image, null);
                MenuItemCompat.setActionView(menuItem, customView);

                ImageView imageView = customView.findViewById(R.id.menu_item_image);
                TextView textView = customView.findViewById(R.id.menu_item_title);

                imageView.setImageResource(menuItemIcons[i]);
                textView.setText(menuItemTitles[i]);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
