package com.example.assignement2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    String name;
    String email;
    String phone;
    String gender;
    String Summary;
    List<Education> educationList=new ArrayList<>();
    List<Experience> experienceList=new ArrayList<>();

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    public Person() {
        name=null;
        email=null;
        phone=null;
        gender=null;
        Summary=null;
        educationList=new ArrayList<>();
        experienceList=new ArrayList<>();
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public Person(String name, String email, String phone, String gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }
    public void Adddegree(List<Education> educationList){
        for(int i=0; i<educationList.size();i++){
            this.educationList.add(educationList.get(i));
        }
    }
    public void Addexperience(List<Experience> experienceList){
        for(int i=0; i<experienceList.size();i++){
            this.experienceList.add(experienceList.get(i));
        }
    }
}
