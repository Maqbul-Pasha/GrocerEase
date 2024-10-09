package com.example.groceryapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.groceryapp.data.database.AppDatabaseHelper;
import com.example.groceryapp.data.model.Grocery;


import java.util.ArrayList;
import java.util.List;

public class GroceryRepository {
    private AppDatabaseHelper dbHelper;


    public GroceryRepository(Context context){
        dbHelper = new AppDatabaseHelper(context);
    }
    public void saveGroceryToLocalDatabase(Grocery grocery){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",grocery.getId());
        values.put("name",grocery.getName());
        values.put("category",grocery.getCategory());

        db.insert("groceries",null,values);
    }


    public List<Grocery> getAllGroceryFromLocalDatabase(){
        List<Grocery> groceries = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM groceries",null);
        try{

            if(cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex("id");
                    int nameIndex = cursor.getColumnIndex("name");
                    int categoryIndex = cursor.getColumnIndex("category");
                    if (idIndex >= 0 && nameIndex >= 0 && categoryIndex >= 0) {
                        String id = cursor.getString(idIndex);
                        String name = cursor.getString(nameIndex);
                        String category = cursor.getString(categoryIndex);
                        groceries.add(new Grocery(id, name, category));
                    } else {
                        Log.e("GroceryRepository", "Missing expected column");
                    }

                } while (cursor.moveToNext());

            }}
        finally{
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return groceries;
    }

    public void updateGroceryInLocalDatabase(Grocery grocery){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",grocery.getName());
        values.put("category",grocery.getCategory());
        db.update("groceries",values,"id=?",new String[]{grocery.getId()});
    }


    public void deleteGroceryFromLocalDB(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("groceries","id=?",new String[]{id});
    }

}
