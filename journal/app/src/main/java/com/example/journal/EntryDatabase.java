package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class EntryDatabase extends SQLiteOpenHelper {

    private static final String TAG = "EntryDatabase";

    private static final String TABLE_NAME = "entries1";
    private static final String COL1 = "id";
    private static final String COL2 = "title";
    private static final String COL3 = "content";
    private static final String COL4 = "mood";
    private static final String COL5 = "time";


    public EntryDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE entries1 (id INTEGER PRIMARY KEY AUTOINCREMENT, time STRING, title STRING, content STRING, mood INTEGER)";
//        String createTable = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL5 + " String, "+ COL2 + " String, " + COL3 + " String, " + COL4 + " String) " ;
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        System.out.println(db);
        onCreate(db);
    }

    public boolean addData(String title, String content, String mood, String time ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, title);
        contentValues.put(COL3, content);
        contentValues.put(COL4, mood);
        contentValues.put(COL5, time);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
