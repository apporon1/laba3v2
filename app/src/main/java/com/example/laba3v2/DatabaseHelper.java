package com.example.laba3v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "Одногруппники";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FULL_NAME = "ФИО";
    public static final String COLUMN_TIMESTAMP = "Время";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FULL_NAME + " TEXT NOT NULL, " +
                COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(createTableQuery);
        resetDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void resetDatabase(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME);
        for (int i = 1; i <= 5; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FULL_NAME, "ФИО " + i);
            db.insert(TABLE_NAME, null, values);
        }
    }

    public void addStudent(String fullName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, fullName);
        db.insert(TABLE_NAME, null, values);
    }

    public void updateLastStudent(String newFullName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_FULL_NAME + " = ? WHERE " +
                COLUMN_ID + " = (SELECT MAX(" + COLUMN_ID + ") FROM " + TABLE_NAME + ")";
        db.execSQL(query, new String[]{newFullName});
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
