package com.example.assignement2;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.Serializable;
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
    private ActivityResultLauncher<Intent> ExperienceLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        List<Experience> experienceList = (List<Experience>) data.getSerializableExtra("experienceDetail");
                        if (experienceList == null) {
                            experienceList = new ArrayList<>();
                        }
                        person.Addexperience(experienceList);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> CertificationLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        List<Certification> certificationList = (List<Certification>) data.getSerializableExtra("certificationDetail");
                        if (certificationList == null) {
                            certificationList = new ArrayList<>();
                        }
                        person.Addcertification(certificationList);
                    }
                }
            }
    );
    private ActivityResultLauncher<Intent> ReferenceLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        List<Reference> referenceList = (List<Reference>) data.getSerializableExtra("referenceDetail");
                        if (referenceList == null) {
                            referenceList = new ArrayList<>();
                        }
                        person.Addreference(referenceList);
                    }
                }
            }
    );
    private ActivityResultLauncher<Intent> profilePicLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    String imageUri = result.getData().getStringExtra("imageUri");
                    if (imageUri != null) {
                        person.setImageSrc(imageUri);
                    }
                }
            });

    private void init(){

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
        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.CVpreviewFrag));
        fragmentTransaction.show(fragmentManager.findFragmentById(R.id.DiscarddataFrag));

        fragmentTransaction.commit();
        ButtonFrag profilePicFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.ProfilePicFrag);
        ButtonFrag personalDetailFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.PersonalDetailFrag);
        ButtonFrag summaryFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.SummaryFrag);
        ButtonFrag educationFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.EducationFrag);
        ButtonFrag experienceFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.ExperienceFrag);
        ButtonFrag certificationFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.CertificationFrag);
        ButtonFrag referencesFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.ReferencesFrag);
        ButtonFrag previewFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.CVpreviewFrag);
        ButtonFrag discardDataFrag = (ButtonFrag) fragmentManager.findFragmentById(R.id.DiscarddataFrag);

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
        previewFrag.setButtonText("Preview Data");
        discardDataFrag.setButtonText("Discard Data");

        discardDataFrag.setconfiguration(-2,-1, "#8B0000","#FFFFFF",50);
        // Set click listeners for each fragment
        profilePicFrag.setButtonClickListener(v -> {
            Intent intent = new Intent(Home.this, ProfilePicture.class);
            Toast.makeText(this, "Clicked on profile button", Toast.LENGTH_LONG).show();
            profilePicLauncher.launch(intent);
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

        experienceFrag.setButtonClickListener(v->{
            Intent intent = new Intent(this, ExperienceDetail.class);
            ExperienceLauncher.launch(intent);
        });

        referencesFrag.setButtonClickListener(v->{
            Intent intent = new Intent(this, ReferenceDetail.class);
            ReferenceLauncher.launch(intent);
        });
        certificationFrag.setButtonClickListener(v->{
            Intent intent = new Intent(this, CertificationDetail.class);
            CertificationLauncher.launch(intent);
        });

        previewFrag.setButtonClickListener(v->{
            Intent intent = new Intent(this,PreviewDataActivity.class);
            intent.putExtra("Person",(Serializable) person);
            startActivity(intent);
            onPause();
        });
        discardDataFrag.setButtonClickListener(v -> {
            // Show a confirmation dialog
            new AlertDialog.Builder(this)
                    .setTitle("Discard Data") // Dialog title
                    .setMessage("Are you sure you want to discard all data?") // Dialog message
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // User confirmed, reset the person object
                        person = new Person();
                        Toast.makeText(this, "All data discarded", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // User canceled, do nothing
                        dialog.dismiss();
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert) // Optional: Set an icon
                    .show(); // Show the dialog
        });

    }

}