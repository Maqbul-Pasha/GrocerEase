package com.example.groceryapp.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grocery_db";
    private static final int DATABASE_VERSION = 1;
    public AppDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_GROCERY_TABLE = "CREATE TABLE groceries (id TEXT PRIMARY KEY,name TEXT,category TEXT)";
        sqLiteDatabase.execSQL(CREATE_GROCERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS groceries");
            onCreate(sqLiteDatabase);


        }
    }
}