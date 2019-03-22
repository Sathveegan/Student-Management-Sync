package com.example.studentmanagementsync.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentmanagementsync.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class DeleteStudentAdapter extends RecyclerView.Adapter<DeleteStudentAdapter.MyViewHolder> {

    private ArrayList<Student> studentList;
    private TextView no_data_id;
    private StudentDAO studentDAO;
    private String type;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView student_list_id;

        public MyViewHolder(View view) {
            super(view);
            student_list_id = (TextView) view.findViewById(R.id.student_list_id);
        }
    }


    public DeleteStudentAdapter(Context context, ArrayList<Student> studentList, TextView no_data_id, String type) {
        this.studentList = studentList;
        this.no_data_id = no_data_id;
        this.type = type;
        studentDAO = new StudentDAO(context);
    }

    @Override
    public DeleteStudentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_student_delete_list, parent, false);

        return new DeleteStudentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeleteStudentAdapter.MyViewHolder holder, final int position) {
        final Student student = studentList.get(position);
        holder.student_list_id.setText(student.getFirstName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

}
