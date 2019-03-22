package com.example.studentmanagementsync.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentmanagementsync.R;
import com.example.studentmanagementsync.core.Student;
import com.example.studentmanagementsync.core.StudentAdapter;
import com.example.studentmanagementsync.core.StudentDAO;
import com.example.studentmanagementsync.core.Sync;
import com.example.studentmanagementsync.service.BackgroundAsynTask;
import com.example.studentmanagementsync.service.Callback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class OnlineFragment extends Fragment {

    private String url = "http://speedhost4u.com/api/getStudents.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView no_data_id;

    private StudentDAO studentDAO;
    private ArrayList<Student> students;

    private Sync sync;

    public OnlineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_online, container, false);

        studentDAO = new StudentDAO(view.getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);

        sync = new Sync(view.getContext());

        no_data_id = (TextView) view.findViewById(R.id.no_data_id);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        if(sync.isNetworkAvailable()){

            sync.getSync();

            try {
                BackgroundAsynTask backgroundAsynTask = new BackgroundAsynTask("", url, new Callback() {
                    @Override
                    public void onTaskCompleted(String s) {
                        studentDAO.deleteStudentTable();
                        Gson gson = new GsonBuilder().create();
                        ArrayList<Student> students = gson.fromJson(s, new TypeToken<ArrayList<Student>>(){}.getType());

                        for (Student student: students) {
                            studentDAO.insertStudent(student);
                        }

                        students = studentDAO.getStudents();

                        if (students.size() == 0)
                            no_data_id.setVisibility(View.VISIBLE);
                        else
                            no_data_id.setVisibility(View.GONE);

                        mAdapter = new StudentAdapter(view.getContext(), students, no_data_id, "Student");
                        recyclerView.setAdapter(mAdapter);

                        mAdapter.notifyDataSetChanged();
                    }
                });
                backgroundAsynTask.execute();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else {
            students = studentDAO.getStudents();

            if (students.size() == 0)
                no_data_id.setVisibility(View.VISIBLE);
            else
                no_data_id.setVisibility(View.GONE);

            mAdapter = new StudentAdapter(view.getContext(), students, no_data_id, "Student");
            recyclerView.setAdapter(mAdapter);

            mAdapter.notifyDataSetChanged();
        }

        return view;
    }
}
