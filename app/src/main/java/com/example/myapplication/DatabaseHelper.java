package com.example.myapplication;

// Import necessary classes for database handling and Android functionality
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

// DatabaseHelper class to manage SQLite database creation, upgrade, and data operations
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database file name constant
    public static final String databaseName = "SignLog.db";

    // Constructor for DatabaseHelper, calls the superclass constructor with database details
    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 1); // version 1
    }

    // onCreate method, called when the database is first created
    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        // Create a table called "users" with "email" as primary key and "password" columns
        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");
    }

    // onUpgrade method, called when the database version is updated
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        // Drop the "users" table if it exists, allowing recreation of the table
        MyDB.execSQL("drop Table if exists users");
    }

    // Method to insert a new user's data into the "users" table
    public Boolean insertData(String email, String password) {
        // Get a writable instance of the database
        SQLiteDatabase MyDatabase = this.getWritableDatabase();

        // Use ContentValues to map column names to values
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);

        // Insert data and return success status
        long result = MyDatabase.insert("users", null, contentValues);
        return result != -1;
    }

    // Method to check if a user exists in the "users" table based on email
    public Boolean checkEmail(String email) {
        // Get a writable instance of the database
        SQLiteDatabase MyDatabase = this.getWritableDatabase();

        // Query the database for a record with the specified email
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        // Return true if a record exists, false otherwise
        return cursor.getCount() > 0;
    }

    // Method to check if a user's email and password combination exists in the "users" table
    public Boolean checkEmailPassword(String email, String password) {
        // Get a writable instance of the database
        SQLiteDatabase MyDatabase = this.getWritableDatabase();

        // Query the database for a record matching the specified email and password
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        // Return true if a matching record exists, false otherwise
        return cursor.getCount() > 0;
    }
}
