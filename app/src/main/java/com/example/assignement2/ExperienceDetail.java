package com.example.assignement2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExperienceDetail extends AppCompatActivity {

    List<Experience> experienceList=new ArrayList<>();
    TextInputEditText ETcompanyname, ETRole, ETstartdate, Etenddate;
    ButtonFrag Addbtn, Savebtn, Cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

    }

    public void init(){
        ETcompanyname=findViewById(R.id.ETcompanyname);
        ETRole=findViewById(R.id.ETrolename);
        ETstartdate=findViewById(R.id.ETstart_date);
        Etenddate=findViewById(R.id.ETend_date);
    }

}