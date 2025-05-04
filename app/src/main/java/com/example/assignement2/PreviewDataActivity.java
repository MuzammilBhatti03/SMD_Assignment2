package com.example.assignement2;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
public class PreviewDataActivity extends AppCompatActivity {
    private TextView nameTextView, emailTextView, phoneTextView;
    private TextView summaryTextView, educationTextView, experienceTextView, referenceTextView, TVcertificates;
    private ImageFilterView profileImageView;
    private Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_data);

        // Initialize views
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        phoneTextView = findViewById(R.id.phone);
        summaryTextView = findViewById(R.id.summary);
        educationTextView = findViewById(R.id.education);
        experienceTextView = findViewById(R.id.experience);
        referenceTextView = findViewById(R.id.references);
        profileImageView = findViewById(R.id.profile_Pic);
        TVcertificates = findViewById(R.id.certifications);
        shareBtn = findViewById(R.id.sharebtn);
        // Get data from Intent
        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("Person");

        if (person != null) {
            nameTextView.setText(person.getName() != null ? person.getName() : "NA");
            emailTextView.setText(person.getEmail() != null ? person.getEmail() : "NA");
            phoneTextView.setText(person.getPhone() != null ? person.getPhone() : "NA");
            summaryTextView.setText(person.getSummary() != null ? person.getSummary() : "NA");

            if (person.getImageSrc() != null && !person.getImageSrc().isEmpty()) {
                try {
                    Uri uri = Uri.parse(person.getImageSrc());
                    Toast.makeText(this, "uri is " + uri, Toast.LENGTH_SHORT).show();
                    if (profileImageView != null) {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        profileImageView.setImageBitmap(bitmap);
                    } else {
                        Toast.makeText(this, "profileImageView is null", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Invalid image URI", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
            }

            // Set education list
            if (!person.getEducationList().isEmpty()) {
                StringBuilder educationText = new StringBuilder();
                for (int i = 0; i < person.getEducationList().size(); i++) {
                    educationText.append(person.getEducationList().get(i).getDegree())
                            .append(" - ")
                            .append(person.getEducationList().get(i).getInstitution())
                            .append("\n");
                }
                educationTextView.setText(educationText.toString());
            } else {
                educationTextView.setText("NA");
            }

            // Set certification list
            if (!person.getCertificationList().isEmpty()) {
                StringBuilder certificationText = new StringBuilder();
                for (int i = 0; i < person.getCertificationList().size(); i++) {
                    certificationText.append(person.getCertificationList().get(i).getTitle())
                            .append(" - ")
                            .append(person.getCertificationList().get(i).getIssuingOrganization())
                            .append("\n");
                }
                TVcertificates.setText(certificationText.toString());
            } else {
                TVcertificates.setText("NA");
            }

            // Set experience list
            if (!person.getExperienceList().isEmpty()) {
                StringBuilder experienceText = new StringBuilder();
                for (int i = 0; i < person.getExperienceList().size(); i++) {
                    experienceText.append(person.getExperienceList().get(i).getCompanyName())
                            .append(" - ")
                            .append(person.getExperienceList().get(i).getStartDate())
                            .append("\n");
                }
                experienceTextView.setText(experienceText.toString());
            } else {
                experienceTextView.setText("NA");
            }

            // Set reference list
            if (!person.getReferenceList().isEmpty()) {
                StringBuilder referenceText = new StringBuilder();
                for (int i = 0; i < person.getReferenceList().size(); i++) {
                    referenceText.append(person.getReferenceList().get(i).getName())
                            .append("\ncontact: ")
                            .append(person.getReferenceList().get(i).getContact())
                            .append("\n");
                }
                referenceTextView.setText(referenceText.toString());
            } else {
                referenceTextView.setText("NA");
            }
        } else {
            nameTextView.setText("NA");
            emailTextView.setText("NA");
            phoneTextView.setText("NA");
            summaryTextView.setText("NA");
            educationTextView.setText("NA");
            experienceTextView.setText("NA");
            referenceTextView.setText("NA");
            TVcertificates.setText("NA");
        }

        shareBtn.setOnClickListener(v -> {
            StringBuilder shareContent = new StringBuilder();

            shareContent.append("Name: ").append(nameTextView.getText().toString()).append("\n")
                    .append("Email: ").append(emailTextView.getText().toString()).append("\n")
                    .append("Phone: ").append(phoneTextView.getText().toString()).append("\n\n")
                    .append("Summary: ").append(summaryTextView.getText().toString()).append("\n\n")
                    .append("Education:\n").append(educationTextView.getText().toString()).append("\n\n")
                    .append("Experience:\n").append(experienceTextView.getText().toString()).append("\n\n")
                    .append("Certifications:\n").append(TVcertificates.getText().toString()).append("\n\n")
                    .append("References:\n").append(referenceTextView.getText().toString()).append("\n");

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Profile Details");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent.toString());

            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}