package com.example.notesapp;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private NotesRepository repository;
    private LiveData<List<Notes>> notes;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        notes =repository.getAllNotes();

    }

    public void  insert (Notes notes){

        repository.insert(notes);
    }
    public void  update (Notes notes){

        repository.update(notes);
    }
    public void  delete (Notes notes){

        repository.delet(notes);
    }

    public LiveData<List<Notes>> getAllNotes(){

        return notes;
    }
}
