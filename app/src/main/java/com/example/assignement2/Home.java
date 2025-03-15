package com.example.assignement2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    Person person=new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


init();

    }
    private ActivityResultLauncher<Intent> personalDetailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        person= (Person) data.getSerializableExtra("personDetail");
                        if (person != null) {
                            // Do something with the received Person object
                            Toast.makeText(this, "Received: " + person.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
    private ActivityResultLauncher<Intent> SummaryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
       if(result.getResultCode()==RESULT_OK){
           Intent data = result.getData();
           if(data!=null){
               String summary = data.getStringExtra("summary");
               Toast.makeText(this,"sumaary is "+summary,Toast.LENGTH_LONG).show();
               person.setSummary(summary);
           }
       } else{
           person.setSummary("");
       }
    });

    private ActivityResultLauncher<Intent> EducationLaunher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
        if(result.getResultCode()==RESULT_OK){
            Intent data = result.getData();
            if(data!=null){
                List<Education> educationList = (List<Education>) data.getSerializableExtra("educationDetail");
                if (educationList == null) {
                    educationList = new ArrayList<>();
                }
                person.Adddegree(educationList);
            }
        }
    });

    private void init(){
        // Add more click listeners as needed...

        // Add fragments to the layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.ProfilePicFrag));
        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.PersonalDetailFrag));
        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.SummaryFrag));
        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.EducationFrag));
        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.ExperienceFrag));
        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.CertificationFrag));
        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.ReferencesFrag));

        fragmentTransaction.commit();
        ButtonFrag profilePicFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.ProfilePicFrag);
        ButtonFrag personalDetailFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.PersonalDetailFrag);
        ButtonFrag summaryFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.SummaryFrag);
        ButtonFrag educationFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.EducationFrag);
        ButtonFrag experienceFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.ExperienceFrag);
        ButtonFrag certificationFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.CertificationFrag);
        ButtonFrag referencesFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.ReferencesFrag);

        // Set button text for each fragment
        if(profilePicFrag!=null){
            profilePicFrag.setButtonText("Profile Picture");
        }
        personalDetailFrag.setButtonText("Personal Details");
        summaryFrag.setButtonText("Summary");
        educationFrag.setButtonText("Education");
        experienceFrag.setButtonText("Experience");
        certificationFrag.setButtonText("Certifications");
        referencesFrag.setButtonText("References");

        // Set click listeners for each fragment
        profilePicFrag.setButtonClickListener(v -> {
            // Handle Profile Picture button click
            Intent intent = new Intent(Home.this, ProfilePicture.class);
            Toast.makeText(this,"clicked on profile btn ",Toast.LENGTH_LONG).show();
            startActivity(intent);
        });

        personalDetailFrag.setButtonClickListener(v -> {
            Intent intent = new Intent(Home.this, PersonalDetail.class);
            personalDetailLauncher.launch(intent);
        });

        summaryFrag.setButtonClickListener(v->{
            Intent intent = new Intent(this, Summary.class);
            SummaryLauncher.launch(intent);
        });

        educationFrag.setButtonClickListener(v->{
            Intent intent = new Intent(this, EducationDetail.class);
            EducationLaunher.launch(intent);
        });
    }

}