package com.funix.animal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.funix.animal.model.Animal;

import java.util.ArrayList;

public class AnimalDao {

    private SQLiteDatabase database;
    private AnimalDatabaseHelper dbHelper;

    public AnimalDao(Context context) {
        dbHelper = new AnimalDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(AnimalDatabaseHelper.COLUMN_TYPE, animal.getType());
        values.put(AnimalDatabaseHelper.COLUMN_PHOTO, animal.getPhoto());
        values.put(AnimalDatabaseHelper.COLUMN_PHOTO_BG, animal.getPhotoBg());
        values.put(AnimalDatabaseHelper.COLUMN_NAME, animal.getName());
        values.put(AnimalDatabaseHelper.COLUMN_CONTENT, animal.getContent());
        values.put(AnimalDatabaseHelper.COLUMN_IS_FAV, animal.isFav() ? 1 : 0);

        return database.insert(AnimalDatabaseHelper.TABLE_ANIMALS, null, values);
    }

    public int updateAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(AnimalDatabaseHelper.COLUMN_TYPE, animal.getType());
        values.put(AnimalDatabaseHelper.COLUMN_PHOTO, animal.getPhoto());
        values.put(AnimalDatabaseHelper.COLUMN_PHOTO_BG, animal.getPhotoBg());
        values.put(AnimalDatabaseHelper.COLUMN_NAME, animal.getName());
        values.put(AnimalDatabaseHelper.COLUMN_CONTENT, animal.getContent());
        values.put(AnimalDatabaseHelper.COLUMN_IS_FAV, animal.isFav() ? 1 : 0);

        return database.update(AnimalDatabaseHelper.TABLE_ANIMALS, values,
                AnimalDatabaseHelper.COLUMN_NAME + " = ?", new String[]{animal.getName()});
    }

    public int deleteAnimal(String name) {
        return database.delete(AnimalDatabaseHelper.TABLE_ANIMALS,
                AnimalDatabaseHelper.COLUMN_NAME + " = ?", new String[]{name});
    }

    public ArrayList<Animal> getAllAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();

        Cursor cursor = database.query(AnimalDatabaseHelper.TABLE_ANIMALS,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Animal animal = cursorToAnimal(cursor);
            animals.add(animal);
            cursor.moveToNext();
        }
        cursor.close();
        return animals;
    }

    public ArrayList<Animal> getAnimalsByType(String type) {
        ArrayList<Animal> animals = new ArrayList<>();
        Cursor cursor = database.query(AnimalDatabaseHelper.TABLE_ANIMALS,
                null, AnimalDatabaseHelper.COLUMN_TYPE + " = ?", new String[]{type}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Animal animal = cursorToAnimal(cursor);
            animals.add(animal);
            cursor.moveToNext();
            }
        cursor.close();
        return animals;
    }
    public Animal getAnimalByName(String name) {
        Animal animal = null;
        Cursor cursor = database.query(AnimalDatabaseHelper.TABLE_ANIMALS,
                null, AnimalDatabaseHelper.COLUMN_NAME + " = ?", new String[]{name}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            animal = cursorToAnimal(cursor);
            cursor.close();
        }
        return animal;
    }
    private Animal cursorToAnimal(Cursor cursor) {
        String type = cursor.getString(cursor.getColumnIndexOrThrow(AnimalDatabaseHelper.COLUMN_TYPE));
        String photo = cursor.getString(cursor.getColumnIndexOrThrow(AnimalDatabaseHelper.COLUMN_PHOTO));
        String photoBg = cursor.getString(cursor.getColumnIndexOrThrow(AnimalDatabaseHelper.COLUMN_PHOTO_BG));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(AnimalDatabaseHelper.COLUMN_NAME));
        String content = cursor.getString(cursor.getColumnIndexOrThrow(AnimalDatabaseHelper.COLUMN_CONTENT));
        boolean isFav = cursor.getInt(cursor.getColumnIndexOrThrow(AnimalDatabaseHelper.COLUMN_IS_FAV)) == 1;

        return new Animal(type,photo, photoBg, name, content, isFav);
    }
}
