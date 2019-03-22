package com.example.studentmanagementsync.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;


import com.example.studentmanagementsync.R;
import com.example.studentmanagementsync.core.Student;
import com.example.studentmanagementsync.core.StudentDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private TextInputEditText editText_firstName, editText_lastName, editText_dob, editText_nic, editText_address, editText_email, editText_contactNumber;
    private Button btn_submit;
    private StudentDAO studentDAO;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editText_firstName = (TextInputEditText) view.findViewById(R.id.editText_firstName);
        editText_lastName = (TextInputEditText) view.findViewById(R.id.editText_lastName);
        editText_dob = (TextInputEditText) view.findViewById(R.id.editText_dob);
        editText_nic = (TextInputEditText) view.findViewById(R.id.editText_nic);
        editText_address = (TextInputEditText) view.findViewById(R.id.editText_address);
        editText_email = (TextInputEditText) view.findViewById(R.id.editText_email);
        editText_contactNumber = (TextInputEditText) view.findViewById(R.id.editText_contactNumber);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);

        studentDAO = new StudentDAO(view.getContext());

        final Calendar myCalendar = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                editText_dob.setText(sdf.format(myCalendar.getTime()));
            }

        };

        editText_dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student();
                student.setFirstname(editText_firstName.getText().toString());
                student.setLastName(editText_lastName.getText().toString());
                student.setDOB(editText_dob.getText().toString());
                student.setNic(editText_nic.getText().toString());
                student.setAddress(editText_address.getText().toString());
                student.setEmail(editText_email.getText().toString());
                student.setContactNumber(editText_contactNumber.getText().toString());

                studentDAO.insertTempStudent(student);

                editText_firstName.setText("");
                editText_lastName.setText("");
                editText_dob.setText("");
                editText_nic.setText("");
                editText_address.setText("");
                editText_email.setText("");
                editText_contactNumber.setText("");

            }
        });

        return view;
    }
}
