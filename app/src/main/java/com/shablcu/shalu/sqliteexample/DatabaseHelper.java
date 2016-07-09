package com.shablcu.shalu.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "student.db";
    private static final String TABLE_NAME= "student_table";
    private static final String COL_1= "ID";
    private static final String COL_2= "NAME";
    private static final String COL_3= "SURNAME";
    private static final String COL_4= "MARKS";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);

    }


    public boolean insertData(String name, String surname, Integer marks){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4, marks);
      long result=  db.insert(TABLE_NAME,null,contentValues);

        if (result==-1){
            return  true;
        }
        else
        {
            return false;
        }
    }
}
