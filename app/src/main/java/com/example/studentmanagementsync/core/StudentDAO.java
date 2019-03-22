package com.example.studentmanagementsync.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.studentmanagementsync.service.DBHelper;

import java.util.ArrayList;

public class StudentDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public StudentDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                "Student",
                null,
                null,
                null,
                null,
                null,
                "studentID DESC"
        );

        while(cursor.moveToNext()) {
            Student s = new Student();
            s.setStudentID(cursor.getString(cursor.getColumnIndex("studentID")));
            s.setFirstname(cursor.getString(cursor.getColumnIndex("firstName")));
            s.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
            s.setDOB(cursor.getString(cursor.getColumnIndex("DOB")));
            s.setNic(cursor.getString(cursor.getColumnIndex("nic")));
            s.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            s.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            s.setContactNumber(cursor.getString(cursor.getColumnIndex("contactNumber")));
            students.add(s);
        }
        cursor.close();

        return students;
    }

    public ArrayList<Student> getTempStudents(String type){
        ArrayList<Student> students = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                "StudentTemp",
                null,
                "status = '"+ type +"'",
                null,
                null,
                null,
                "studentID DESC"
        );

        while(cursor.moveToNext()) {
            Student s = new Student();
            s.setStudentID(cursor.getString(cursor.getColumnIndex("studentID")));
            s.setFirstname(cursor.getString(cursor.getColumnIndex("firstName")));
            s.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
            s.setDOB(cursor.getString(cursor.getColumnIndex("DOB")));
            s.setNic(cursor.getString(cursor.getColumnIndex("nic")));
            s.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            s.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            s.setContactNumber(cursor.getString(cursor.getColumnIndex("contactNumber")));
            students.add(s);
        }
        cursor.close();

        return students;
    }

    public void insertTempStudent(Student student){
        ContentValues values = new ContentValues();
        values.put("firstName", student.getFirstName());
        values.put("lastName", student.getLastName());
        values.put("DOB", student.getDOB());
        values.put("nic", student.getNic());
        values.put("address", student.getAddress());
        values.put("email", student.getEmail());
        values.put("contactNumber", student.getContactNumber());
        values.put("status", "insert");

        db = dbHelper.getWritableDatabase();
        db.insert("StudentTemp", null, values);
    }

    public void insertStudent(Student student){
        ContentValues values = new ContentValues();
        values.put("studentID", student.getStudentID());
        values.put("firstName", student.getFirstName());
        values.put("lastName", student.getLastName());
        values.put("DOB", student.getDOB());
        values.put("nic", student.getNic());
        values.put("address", student.getAddress());
        values.put("email", student.getEmail());
        values.put("contactNumber", student.getContactNumber());

        db = dbHelper.getWritableDatabase();
        db.insert("Student", null, values);
    }

    public void deleteStudentTemp(String studentID){
        db = dbHelper.getWritableDatabase();
        db.delete("StudentTemp", "studentID = " + studentID, null);
    }

    public void deleteStudentTempType(String type){
        db = dbHelper.getWritableDatabase();
        db.delete("StudentTemp", "status = '" + type + "'", null);
    }

    public void deleteStudent(String studentID){
        db = dbHelper.getWritableDatabase();
        db.delete("Student", "studentID = '" + studentID + "'", null);

        ContentValues values = new ContentValues();
        values.put("firstName", studentID);
        values.put("status", "delete");

        db.insert("StudentTemp", null, values);
    }

    public void deleteStudentTable(){
        db = dbHelper.getWritableDatabase();
        db.delete("Student", null, null);
    }

    public void deleteStudentTempTable(){
        db = dbHelper.getWritableDatabase();
        db.delete("StudentTemp", null, null);
    }

}
