package com.example.notesapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void insert(Notes notes);

    @Update
    void update(Notes notes);

    @Delete
    void  delete (Notes notes);

    @Query("Select * FROM note_table ORDER BY id ASC")
    LiveData<List<Notes>> getAllNotes();
}
