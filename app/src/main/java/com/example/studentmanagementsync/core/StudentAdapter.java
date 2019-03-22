package com.example.studentmanagementsync.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentmanagementsync.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private ArrayList<Student> studentList;
    private TextView no_data_id;
    private StudentDAO studentDAO;
    private String type;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView student_list_id, student_list_firstName, student_list_lastName, student_list_dob, student_list_address, student_list_nic, student_list_email, student_list_contactNumber;
        public Button btn_list_delete;

        public MyViewHolder(View view) {
            super(view);
            student_list_id = (TextView) view.findViewById(R.id.student_list_id);
            student_list_firstName = (TextView) view.findViewById(R.id.student_list_firstName);
            student_list_lastName = (TextView) view.findViewById(R.id.student_list_lastName);
            student_list_dob = (TextView) view.findViewById(R.id.student_list_dob);
            student_list_nic = (TextView) view.findViewById(R.id.student_list_nic);
            student_list_address = (TextView) view.findViewById(R.id.student_list_address);
            student_list_email = (TextView) view.findViewById(R.id.student_list_email);
            student_list_contactNumber = (TextView) view.findViewById(R.id.student_list_contactNumber);

            btn_list_delete = (Button) view.findViewById(R.id.btn_list_delete);
        }
    }


    public StudentAdapter(Context context, ArrayList<Student> studentList, TextView no_data_id, String type) {
        this.studentList = studentList;
        this.no_data_id = no_data_id;
        this.type = type;
        studentDAO = new StudentDAO(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_student_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Student student = studentList.get(position);
        holder.student_list_id.setText(student.getStudentID());
        holder.student_list_firstName.setText(student.getFirstName());
        holder.student_list_lastName.setText(student.getLastName());
        holder.student_list_dob.setText(student.getDOB());
        holder.student_list_nic.setText(student.getNic());
        holder.student_list_address.setText(student.getAddress());
        holder.student_list_email.setText(student.getEmail());
        holder.student_list_contactNumber.setText(student.getContactNumber());

        holder.btn_list_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("StudentTemp"))
                    studentDAO.deleteStudentTemp(student.getStudentID());
                else
                    studentDAO.deleteStudent(student.getStudentID());

                studentList.remove(student);
                notifyItemRemoved(position);

                if (getItemCount() == 0)
                    no_data_id.setVisibility(View.VISIBLE);
                else
                    no_data_id.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

}
