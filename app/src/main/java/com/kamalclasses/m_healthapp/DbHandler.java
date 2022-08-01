package com.kamalclasses.m_healthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase sqLiteDatabase;

    DbHandler(Context context){
        super(context, "calorie_count.db", null,1);
        this.context = context;
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table food(foodname text, foodcalories text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add(food f){
        ContentValues contentValues = new ContentValues();
        contentValues.put("foodname", f.getFoodname());
        contentValues.put("foodcalories", f.getFoodcalories());
        long id = sqLiteDatabase.insert("food",null, contentValues);
        if (id > 0)
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Issue", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<food> view(){
        ArrayList<food> data = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("food", null, null, null, null, null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            do{
                String foodname = cursor.getString(0);
                String foodcalories = cursor.getString(1);
                food f = new food(foodname, foodcalories);
                data.add(f);

            }while (cursor.moveToNext());
        }
        return data;
    }
}
