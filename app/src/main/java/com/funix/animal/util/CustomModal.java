package com.funix.animal.util;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.funix.animal.R;
import com.funix.animal.model.Animal;

public class CustomModal extends DialogFragment {
    private static final String ARG_ANIMAL = "animal";
    private OnPhoneUpdatedListener listener;
    private Animal animal;
    private EditText editTextPhone;
    private AssetsHelper assetsHelper;

    public interface OnPhoneUpdatedListener {
        void onPhoneUpdated(String newPhone);
    }
    public void setOnPhoneUpdatedListener(OnPhoneUpdatedListener listener) {
        this.listener = listener;
    }

    public static CustomModal newInstance(  Animal animal) {
        CustomModal fragment = new CustomModal();
        Bundle args = new Bundle();

        args.putSerializable(ARG_ANIMAL, animal);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal, container, false);
        assetsHelper = new AssetsHelper();
        ImageView dialogImage = view.findViewById(R.id.dialog_image);
         editTextPhone = view.findViewById(R.id.editTextPhone);
        Button saveButton = view.findViewById(R.id.button_save);
        Button deleteButton = view.findViewById(R.id.button_delete);

        if (getArguments() != null) {
            animal = (Animal) getArguments().getSerializable(ARG_ANIMAL);
            editTextPhone.setText(animal.getPhone());
            String imageUrl = animal.getPhoto();
            Bitmap bitmap = assetsHelper.getBitmapFromPath(getContext(), imageUrl);
            dialogImage.setImageBitmap(bitmap);

        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animal != null) {
                    String newPhone = editTextPhone.getText().toString();
                    animal.setPhone(newPhone);
                    if (listener != null) {
                        listener.onPhoneUpdated(newPhone);
                    }
                    Toast.makeText(getActivity(), "Phone number updated", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animal != null) {
                    animal.setPhone("");
                    if (listener != null) {
                        listener.onPhoneUpdated("");
                    }
                    Toast.makeText(getActivity(), "Delete clicked", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
