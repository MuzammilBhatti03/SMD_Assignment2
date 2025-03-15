package com.example.assignement2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

public class Summary extends AppCompatActivity {

    FragmentManager fragmentManager;
    TextInputEditText ETSummary;
    ButtonFrag savebtn,cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init(){
        ETSummary =findViewById(R.id.ETsummary);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().show(fragmentManager.findFragmentById(R.id.Savefragsummary))
                .show(fragmentManager.findFragmentById(R.id.Cancelfragsummary))
                .commit();

        savebtn = (ButtonFrag) fragmentManager.findFragmentById(R.id.Savefragsummary);
        cancelbtn = (ButtonFrag) fragmentManager.findFragmentById(R.id.Cancelfragsummary);

        if(savebtn != null && cancelbtn != null) {
            savebtn.setButtonText("Save");
            cancelbtn.setButtonText("Cancel");
            savebtn.setButtonClickListener(v -> {
               String summary = ETSummary.getText().toString();
               if(!summary.isEmpty()){
                   Intent intent = new Intent(this,Home.class);
                   intent.putExtra("summary",summary);
                   setResult(RESULT_OK,intent);
                   finish();
               }
               else{
                   ETSummary.setError("Enter Summary");
               }
            });
            //match partent -1 and wrap content -2
            savebtn.setconfiguration(-2, 400, "#5856d6", "#FFFFFF", 80);
            cancelbtn.setconfiguration(-2, 400, "#8B0000", "#FFFFFF", 80);
            cancelbtn.setButtonClickListener(v -> {
                Intent intent = new Intent(this, Home.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            });
        }
        }

}