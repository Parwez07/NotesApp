package com.example.notesapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NotesViewModel notesViewModel ;
    RecyclerView recyclerView;

    ActivityResultLauncher<Intent> activityResultLauncherUpdate;
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // register the activityResultLuncher here
        registerActivity();

        //registerActivity for the Update
        registerActivityUpdate();

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NotesAdapter adapter = new NotesAdapter();

        recyclerView.setAdapter(adapter);

        notesViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(NotesViewModel.class);

        notesViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {

                // update the recyclerView
                adapter.setNotes(notes);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

               notesViewModel.delete(adapter.getNotes(viewHolder.getAdapterPosition()));

                Toast.makeText(getApplication(),"deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClicked(new NotesAdapter.OnitemClicked() {
            @Override
            public void OnItemClicked(Notes notes) {
                Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("title",notes.getTitle());
                intent.putExtra("description",notes.getDescription());
                intent.putExtra("id",notes.getId());

                activityResultLauncherUpdate.launch(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                //startActivityForResult(intent,1);
                // ActivityResult Luncher class for  this depricated function
                activityResultLauncher.launch(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

 //   @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==1 && resultCode == RESULT_OK && data !=null){
//
//            String title = data.getStringExtra("title");
//            String description = data.getStringExtra("description");
//
//            Notes notes = new Notes(title,description);
//            notesViewModel.insert(notes);
//        }
//
//    }

    public void registerActivity(){

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if(resultCode == RESULT_OK && data !=null ){

                            String title = data.getStringExtra("title");
                            String description = data.getStringExtra("description");

                            Notes notes = new Notes(title,description);
                            notesViewModel.insert(notes);
                        }
                    }
                });
    }

    public void registerActivityUpdate(){
        activityResultLauncherUpdate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if(resultCode == RESULT_OK && data !=null ){
                            String title = data.getStringExtra("title");
                            String description = data.getStringExtra("description");
                            int id = data.getIntExtra("id",-1);
                            Notes notes = new Notes(title,description);
                            notes.setId(id);
                            notesViewModel.update(notes);
                        }
                    }
                });
    }
}