package com.funix.animal.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.funix.animal.model.Animal;

import java.util.ArrayList;

public class AnimalViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<Animal>> animals = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Animal>> animalsByType = new MutableLiveData<>();

    public void setAnimals(ArrayList<Animal> animalList) {
        animals.setValue(animalList);
    }

    public LiveData<ArrayList<Animal>> getAnimals() {
        return animals;
    }

    public void setAnimalsByType(ArrayList<Animal> animalList) {
        animalsByType.setValue(animalList);
    }

    public LiveData<ArrayList<Animal>> getAnimalsByType() {
        return animalsByType;
    }
}
