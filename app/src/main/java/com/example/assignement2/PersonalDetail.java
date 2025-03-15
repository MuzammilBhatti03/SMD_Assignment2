package com.example.assignement2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

public class PersonalDetail extends AppCompatActivity {
    TextInputEditText name, email, phone;
    RadioGroup radioGroup;
    RadioButton male,female;
    ButtonFrag cancelbtn , savebtn;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    public void init(){
        name = findViewById(R.id.ETname);
        email = findViewById(R.id.ETemail);
        phone = findViewById(R.id.ETnumber);
        radioGroup = findViewById(R.id.radiogroup);
        male = findViewById(R.id.maleRadio);
        female = findViewById(R.id.femaleRadio);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().show(fragmentManager.findFragmentById(R.id.Savefrag))
                .show(fragmentManager.findFragmentById(R.id.Cancelfrag))
                .commit();

        savebtn = (ButtonFrag) fragmentManager.findFragmentById(R.id.Savefrag);
        cancelbtn = (ButtonFrag) fragmentManager.findFragmentById(R.id.Cancelfrag);

        if(savebtn != null && cancelbtn != null){
            savebtn.setButtonText("Save");
            cancelbtn.setButtonText("Cancel");
            savebtn.setButtonClickListener(v -> {
                String nam = name.getText().toString();
                String emal = email.getText().toString();
                String phon = phone.getText().toString();
                int checkid = radioGroup.getCheckedRadioButtonId();
                String gender="";
                if(checkid!=-1){
                    RadioButton selectedRadioButton = findViewById(checkid);
                    // Get the text of the selected radio button
                    gender = selectedRadioButton.getText().toString();
                }
                if(!nam.isEmpty()&&!emal.isEmpty()&&!phon.isEmpty()&&!gender.isEmpty()){
                    person=new Person(nam,emal,phon,gender);
                    Intent intent= new Intent(this, Home.class);
                    intent.putExtra("personDetail",  person);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    if(nam.isEmpty()){
                        name.setError("Enter Name");
                    }
                    if(emal.isEmpty()){
                        email.setError("Enter Email");
                    }
                    if(phon.isEmpty()){
                        phone.setError("Enter Phone");
                    }
                    if(gender.isEmpty()){
                        Toast.makeText(this,"Enter Gender",Toast.LENGTH_SHORT).show();
                        male.setTextColor(Color.RED);
                        female.setTextColor(Color.RED);
                    }
                }
            });
            //match partent -1 and wrap content -2
            savebtn.setconfiguration(-2,400, "#5856d6","#FFFFFF",80);
            cancelbtn.setconfiguration(-2,400, "#8B0000","#FFFFFF",80);
            cancelbtn.setButtonClickListener(v -> {
                Intent intent= new Intent(this, Home.class);
                setResult(RESULT_CANCELED,intent);
                finish();
            });
        }
    }

}