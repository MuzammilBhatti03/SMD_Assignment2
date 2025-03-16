package com.example.assignement2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReferenceDetail extends AppCompatActivity {
    TextView result;
    TextInputEditText refName, refContact, refRelation;
    ButtonFrag Addbtn, Savebtn, Cancelbtn;
    FragmentManager fragmentManager;
    List<Reference> referenceList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reference_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }
    
    private void init(){
        result = findViewById(R.id.TVresult);
        refName = findViewById(R.id.ETname);
        refContact = findViewById(R.id.ETcontact);
        refRelation = findViewById(R.id.ETrelation);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .show(fragmentManager.findFragmentById(R.id.Addbtn))
                .show(fragmentManager.findFragmentById(R.id.Savefrag))
                .show(fragmentManager.findFragmentById(R.id.Cancelfrag))
                .commit();

        Addbtn = (ButtonFrag) fragmentManager.findFragmentById(R.id.Addbtn);
        Savebtn = (ButtonFrag) fragmentManager.findFragmentById(R.id.Savefrag);
        Cancelbtn = (ButtonFrag) fragmentManager.findFragmentById(R.id.Cancelfrag);

        if(Savebtn != null && Cancelbtn != null && Addbtn != null) {
            Savebtn.setButtonText("Save");
            Cancelbtn.setButtonText("Cancel");
            Addbtn.setButtonText("ADD Reference Detail");
            Addbtn.setButtonClickListener(v -> {
                String refname = refName.getText().toString();
                String refcontact = refContact.getText().toString();
                String refrelation = refRelation.getText().toString();

                if (!refname.isEmpty() && !refcontact.isEmpty() ) {
                    if(refrelation.isEmpty()){
                        refrelation="NA";
                    }

                    // Add to experience list
                    Reference reference = new Reference(refname, refcontact, refrelation);
                    referenceList.add(reference);
                    result.setText(result.getText().toString() + "\n" + reference.getName());

                    // Clear input fields
                    refName.setText("");
                    refContact.setText("");
                    refRelation.setText("");
                } else {
                    // Set field errors
                    if (refname.isEmpty()) refName.setError("Enter refrence Name");
                    if (refcontact.isEmpty()) refContact.setError("Enter contact number");
                }
            });


            Savebtn.setButtonClickListener(v->{
                Intent intent = new Intent(this, Home.class);
                intent.putExtra("referenceDetail", (Serializable) referenceList);
                setResult(RESULT_OK,intent);
                finish();
            });

            //match partent -1 and wrap content -2
            Savebtn.setconfiguration(-2, 400, "#5856d6", "#FFFFFF", 80);
            Cancelbtn.setconfiguration(-2, 400, "#8B0000", "#FFFFFF", 80);
            Addbtn.setconfiguration(-2, -1, "#008000", "#FFFFFF", 50);
            Cancelbtn.setButtonClickListener(v -> {
                Intent intent = new Intent(this, Home.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            });
        }
    }
    
}