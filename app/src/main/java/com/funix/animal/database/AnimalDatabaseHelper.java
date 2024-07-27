package com.funix.animal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "animals.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ANIMALS = "animals";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_PHOTO_BG = "photoBg";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_IS_FAV = "isFav";
    public static final String COLUMN_PHONE = "phone";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_ANIMALS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_PHOTO + " TEXT, " +
                    COLUMN_PHOTO_BG + " TEXT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_CONTENT + " TEXT, " +
                    COLUMN_IS_FAV + " INTEGER,"  +
                    COLUMN_PHONE + " TEXT);";

    public AnimalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMALS);
        onCreate(db);
    }
}
