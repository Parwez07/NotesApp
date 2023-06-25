package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {


    EditText etTitle;
    EditText etNotes;
    Button btnCancel;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add note");
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etTitle = findViewById(R.id.etTitle);
        etNotes = findViewById(R.id.etNotes);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave  = findViewById(R.id.btnSave);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnSave .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveNote();
            }
        });
    }

    public void saveNote(){

        String noteTitle = etTitle.getText().toString();
        String description = etNotes.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("title",noteTitle);
        intent.putExtra("description",description);
        setResult(RESULT_OK,intent);
        finish();
    }
}