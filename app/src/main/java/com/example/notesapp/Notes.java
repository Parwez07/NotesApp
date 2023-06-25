package com.example.notesapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



// writing this entity that enable to write the required squLite table
@Entity (tableName = "note_table")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public  int id ;

    public String title;

    public  String description;

    public Notes(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
