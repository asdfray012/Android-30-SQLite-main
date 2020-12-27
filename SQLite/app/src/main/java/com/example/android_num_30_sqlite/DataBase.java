package com.example.android_num_30_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static final String Data = "data.db";
    public static final String ScpTable = "SCP_table";
    public static final String USERid = "ID";
    public static final String USERname = "NAME";
    public static final String USERlevel = "LEVEL";
    public static final String USERsite = "SITE";

    public DataBase(@Nullable Context context) {
        super(context, Data, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ScpTable + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LEVEL TEXT, SITE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ScpTable);
        onCreate(db);
    }

    public boolean insertData(String name, String level, String site){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERname, name);
        contentValues.put(USERlevel, level);
        contentValues.put(USERsite, site);
        long result = db.insert(ScpTable, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + ScpTable, null);
        return res;
    }

    public boolean updateData(String id, String name, String level, String site){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERid, id);
        contentValues.put(USERname, name);
        contentValues.put(USERlevel, level);
        contentValues.put(USERsite, site);
        db.update(ScpTable, contentValues, "ID = ?", new String[]{ id });
        return  true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ScpTable, "ID = ?", new String[]{ id });
    }
}
