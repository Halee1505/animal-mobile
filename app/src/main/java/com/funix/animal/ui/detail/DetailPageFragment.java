package com.funix.animal.ui.detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.funix.animal.R;
import com.funix.animal.model.Animal;
import com.funix.animal.util.AssetsHelper;
import com.funix.animal.util.CustomModal;
import com.funix.animal.util.SharedPreferencesController;

public class DetailPageFragment extends Fragment implements CustomModal.OnPhoneUpdatedListener {
    private static final String ARG_ANIMAL_NAME = "animal_name";
    private static final String ARG_ANIMAL_TYPE = "animal_type";
    private String animalName;
    private String animalPath;
    private AssetsHelper assetsHelper;
    private SharedPreferencesController sharedPreferencesController;
    private TextView phoneTextView;
    public static DetailPageFragment newInstance(String animalName, String animalType) {
        DetailPageFragment fragment = new DetailPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ANIMAL_NAME, animalName);
        args.putString(ARG_ANIMAL_TYPE, animalType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        assetsHelper = new AssetsHelper();
        sharedPreferencesController = new SharedPreferencesController(getContext());
        View view = inflater.inflate(R.layout.fragment_detail_page, container, false);
        TextView textView = view.findViewById(R.id.detailTextView);
        ImageView imageView = view.findViewById(R.id.detailImageView);
        ImageView iconView = view.findViewById(R.id.icon);
        TextView titleView = view.findViewById(R.id.text_title);
        ImageView phoneIconView = view.findViewById(R.id.phone_icon);
        phoneTextView = view.findViewById(R.id.phone_text);


        if (getArguments() != null) {
            animalPath = getArguments().getString(ARG_ANIMAL_NAME);
            String animalType  = getArguments().getString(ARG_ANIMAL_TYPE);
            animalName = assetsHelper.getNameFromPath(animalPath);
            String phone = sharedPreferencesController.getString(animalPath + "_phone", "");
            Boolean isFav = sharedPreferencesController.getBoolean(animalPath + "_fav", false);
            String content = assetsHelper.getContent(getContext(), animalType, animalName);
            titleView.setText(animalName);
            textView.setText(content);
            Bitmap animalImage = assetsHelper.getBitmapFromPath(getContext(), animalPath);
            imageView.setImageBitmap(animalImage);
            Animal animal = new Animal(animalType, animalPath, "", animalName, content, isFav, phone);
                iconView.setImageResource(animal.isFav() ? R.drawable.ic_heart_full : R.drawable.ic_heart);
                phoneTextView.setText(animal.getPhone());
                iconView.setOnClickListener(v -> {
                    animal.setFav(!animal.isFav());
                    sharedPreferencesController.putBoolean(animalPath + "_fav", animal.isFav());
                    iconView.setImageResource(animal.isFav() ? R.drawable.ic_heart_full : R.drawable.ic_heart);
                });

            phoneIconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomModal dialog = CustomModal.newInstance(animal);
                    dialog.setOnPhoneUpdatedListener(DetailPageFragment.this); // Set the listener

                    dialog.show(getParentFragmentManager(), "CustomDialog");
                }
            });

        }
        return view;
    }

    @Override
    public void onPhoneUpdated(String newPhone) {
        if (phoneTextView != null) {
            sharedPreferencesController.putString(animalPath + "_phone" , newPhone);
            phoneTextView.setText(newPhone);
        }
    }

}
