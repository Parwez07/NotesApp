package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etNotes;
    Button btnCancel;
    int id;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update note");
        setContentView(R.layout.activity_update);


        etTitle = findViewById(R.id.etTitle);
        etNotes = findViewById(R.id.etNotes);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate  = findViewById(R.id.btnUpdate);

        getData();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nothing Updated",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnUpdate .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateNotes();
            }
        });
    }

    private void  updateNotes(){

        String title = etTitle.getText().toString();
        String description = etNotes.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("title",title);
        intent.putExtra("description",description);

        if(id != -1){
            intent.putExtra("id",id);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    public void getData(){
        Intent intent = getIntent();
      String title =  intent.getStringExtra("title");
      String description = intent.getStringExtra("description");
      id = intent.getIntExtra("id",-1);

      etNotes.setText(description);
      etTitle.setText(title);
    }
}