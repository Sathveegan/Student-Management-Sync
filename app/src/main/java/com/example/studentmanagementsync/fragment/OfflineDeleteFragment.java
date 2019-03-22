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
import com.example.studentmanagementsync.core.DeleteStudentAdapter;
import com.example.studentmanagementsync.core.Student;
import com.example.studentmanagementsync.core.StudentDAO;

import java.util.ArrayList;

public class OfflineDeleteFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView no_data_id;

    private StudentDAO studentDAO;
    private ArrayList<Student> students;

    public OfflineDeleteFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offline_delete, container, false);

        studentDAO = new StudentDAO(view.getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);

        no_data_id = (TextView) view.findViewById(R.id.no_data_id);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        students = studentDAO.getTempStudents("delete");

        if (students.size() == 0)
            no_data_id.setVisibility(View.VISIBLE);
        else
            no_data_id.setVisibility(View.GONE);

        mAdapter = new DeleteStudentAdapter(view.getContext(), students, no_data_id, "StudentTemp");
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        return view;
    }

}
