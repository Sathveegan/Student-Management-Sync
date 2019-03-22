package com.example.studentmanagementsync.core;

public class Student {
    private String studentID;
    private String firstName;
    private String lastName;
    private String DOB;
    private String nic;
    private String address;
    private String email;
    private String contactNumber;

    public Student(String studentID, String firstName, String lastName, String DOB, String nic, String address, String email, String contactNumber) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.nic = nic;
        this.address = address;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public Student() {
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
