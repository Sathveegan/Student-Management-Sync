package com.example.studentmanagementsync.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.widget.Toast;

import com.example.studentmanagementsync.service.BackgroundAsynTask;
import com.example.studentmanagementsync.service.Callback;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class Sync {

    private String url = "http://speedhost4u.com/api/insertStudent.php";
    private String iurl = "http://speedhost4u.com/api/deleteStudent.php";

    private StudentDAO studentDAO;
    private Context context;

    public Sync(Context context) {
        this.context = context;
        studentDAO = new StudentDAO(context);
    }

    public void getSync() {
        ArrayList<Student> students = studentDAO.getTempStudents("insert");

        if (students.size() > 0) {
            try {
                for (Student student : students) {
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("firstName", student.getFirstName())
                            .appendQueryParameter("lastName", student.getLastName())
                            .appendQueryParameter("DOB", student.getDOB())
                            .appendQueryParameter("nic", student.getNic())
                            .appendQueryParameter("address", student.getAddress())
                            .appendQueryParameter("email", student.getEmail())
                            .appendQueryParameter("contactNumber", student.getContactNumber());

                    String query = builder.build().getEncodedQuery();
                    BackgroundAsynTask backgroundAsynTask = new BackgroundAsynTask(query, url, new Callback() {
                        @Override
                        public void onTaskCompleted(String s) {
                            studentDAO.deleteStudentTempType("insert");
                        }
                    });
                    backgroundAsynTask.execute();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        ArrayList<Student> deleteStudents = studentDAO.getTempStudents("delete");

        if (deleteStudents.size() > 0) {
            try {
                for (Student student : deleteStudents) {
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("studentID", student.getFirstName());

                    String query = builder.build().getEncodedQuery();
                    BackgroundAsynTask backgroundAsynTask = new BackgroundAsynTask(query, iurl, new Callback() {
                        @Override
                        public void onTaskCompleted(String s) {
                            studentDAO.deleteStudentTempType("delete");
                        }
                    });
                    backgroundAsynTask.execute();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if(students.size() == 0 && deleteStudents.size() ==0)
            Toast.makeText(context, "NO DATA TO SYNC", Toast.LENGTH_SHORT).show();
        else if(students.size() == 0)
            Toast.makeText(context, "NO NEW DATA TO SYNC", Toast.LENGTH_SHORT).show();
        else if(deleteStudents.size() == 0)
            Toast.makeText(context, "NO DELETED DATA TO SYNC", Toast.LENGTH_SHORT).show();
    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
