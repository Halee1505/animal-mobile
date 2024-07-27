package com.funix.animal;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
    private static final int REQUEST_READ_PHONE_STATE = 101;
    private static final int PERMISSIONS_REQUEST_READ_CALL_LOG = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        checkPermissions();

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_seas, R.id.nav_mammals, R.id.nav_birds)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setupNavigationView(navigationView, navController);
    }

    private void checkPermissions() {
        // First, check for READ_PHONE_STATE permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            // If READ_PHONE_STATE is already granted, check for READ_CALL_LOG
            checkCallLogPermission();
        }
    }

    private void checkCallLogPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, PERMISSIONS_REQUEST_READ_CALL_LOG);
        } else {
            // Permissions are already granted, you can proceed with the logic that requires these permissions
            handlePermissionsGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Phone state permission granted, now check for call log permission
                checkCallLogPermission();
            } else {
                // Handle the case where permission is denied
                handlePermissionDenied(Manifest.permission.READ_PHONE_STATE);
            }
        } else if (requestCode == PERMISSIONS_REQUEST_READ_CALL_LOG) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Call log permission granted
                handlePermissionsGranted();
            } else {
                // Handle the case where permission is denied
                handlePermissionDenied(Manifest.permission.READ_CALL_LOG);
            }
        }
    }

    private void handlePermissionsGranted() {
        // Implement what to do when permissions are granted
        Toast.makeText(this, "All required permissions granted!", Toast.LENGTH_SHORT).show();
    }

    private void handlePermissionDenied(String permission) {
        // Implement what to do when permissions are denied, e.g., show a dialog or a toast
        Toast.makeText(this, "Please allow this permission to use features of the app", Toast.LENGTH_LONG).show();
        finishAndRemoveTask();
    }

    private void setupNavigationView(NavigationView navigationView, NavController navController) {
        View headerView = navigationView.getHeaderView(0);
        LinearLayout headerLayout = headerView.findViewById(R.id.nav_header_main);

        headerLayout.setOnClickListener(view -> {
            navController.navigate(R.id.nav_home);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawers();
        });

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
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}
