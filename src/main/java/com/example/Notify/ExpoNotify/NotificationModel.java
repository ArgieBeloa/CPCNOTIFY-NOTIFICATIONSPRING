package com.example.Notify.ExpoNotify;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "studentToken")
public class NotificationModel {

    @Id
    private String id;
    private  String title;
    private String message;
    private  String department ;
   private List<StudentExpoData> studentExpoData;

    public String getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StudentExpoData> getStudentExpoData() {
        return studentExpoData;
    }

    public void setStudentExpoData(List<StudentExpoData> studentExpoData) {
        this.studentExpoData = studentExpoData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
