package com.example.studentmanagementsync.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StudentDB.db";

    private static final String SQL_Student_Table =
            "CREATE TABLE Student (" +
                    "studentID varchar(100) PRIMARY KEY," +
                    "firstName varchar(255)," +
                    "lastName varchar(255)," +
                    "DOB date," +
                    "nic char(10)," +
                    "address varchar(255)," +
                    "email varchar(255)," +
                    "contactNumber varchar(20)" +
                    ");";

    private static final String SQL_Temp_Student_Table =
            "CREATE TABLE StudentTemp (" +
                    "studentID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "firstName varchar(255)," +
                    "lastName varchar(255)," +
                    "DOB date," +
                    "nic char(10)," +
                    "address varchar(255)," +
                    "email varchar(255)," +
                    "contactNumber varchar(20)," +
                    "status varchar(100)" +
                    ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_Student_Table);
        db.execSQL(SQL_Temp_Student_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}

