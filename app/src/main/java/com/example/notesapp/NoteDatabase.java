package com.example.notesapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// table is being addd here
@Database(entities = {Notes.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public  abstract NotesDao notesDao();

    public static synchronized NoteDatabase getInstance(Context context){

        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database").
                    fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            NotesDao notesDao = instance.notesDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    notesDao.insert(new Notes("Title 1","Description 1"));
                    notesDao.insert(new Notes("Title 2","Description 1"));
                    notesDao.insert(new Notes("Title 3","Description 3"));
                }
            });
            //new PopulateDbAsynTask(instance).execute();
        }
    };

//    private static class PopulateDbAsynTask extends AsyncTask<Void,Void,Void>{
//
//        private NotesDao notesDao;
//
//        private PopulateDbAsynTask(NoteDatabase database){
//
//            notesDao = database.notesDao();
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            notesDao.insert(new Notes("Title 1","Description 1"));
//            notesDao.insert(new Notes("Title 2","Description 1"));
//            notesDao.insert(new Notes("Title 3","Description 3"));
//
//
//
//            return null;
//        }
//    }
}
