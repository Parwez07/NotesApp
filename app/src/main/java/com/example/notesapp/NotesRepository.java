package com.example.notesapp;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesRepository {

    private NotesDao notesDao;
    private LiveData<List<Notes>> notes;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public NotesRepository(Application application){

        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        notesDao = noteDatabase.notesDao();
        notes = notesDao.getAllNotes();
    }

    public void  insert(Notes notes){

        executors.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.insert(notes);
            }
        });
        //new InsertNotesAsyncTask(notesDao).execute(notes);
    }
    public void  update(Notes notes){

        executors.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.update(notes);
            }
        });
        //new UpdateNotesAsyncTask(notesDao).execute(notes);
    }
    public  void delet(Notes notes){

        executors.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.delete(notes);
            }
        });
       // new DeleteNotesAsyncTask(notesDao).execute(notes);

    }

    public LiveData<List<Notes>> getAllNotes(){

        return notes;
    }

//    private static class InsertNotesAsyncTask extends AsyncTask<Notes,Void,Void>{
//
//        private NotesDao notesDao;
//
//        private InsertNotesAsyncTask(NotesDao notesDao){
//            this.notesDao = notesDao;
//        }
//
//        @Override
//        protected Void doInBackground(Notes... notes) {
//            notesDao.insert(notes[0]);
//            return null;
//        }
//
//        //1. parameter for doInBackground method
//        //2. parameter for onProgressUpdate method
//        //3. parameteer return type of deInBackground
//
//    }
//    private static class UpdateNotesAsyncTask extends AsyncTask<Notes,Void,Void>{
//
//        private NotesDao notesDao;
//
//        private UpdateNotesAsyncTask(NotesDao notesDao){
//            this.notesDao = notesDao;
//        }
//
//        @Override
//        protected Void doInBackground(Notes... notes) {
//            notesDao.update(notes[0]);
//            return null;
//        }
//
//        //1. parameter for doInBackground method
//        //2. parameter for onProgressUpdate method
//        //3. parameteer return type of deInBackground
//
//    }
//    private static class DeleteNotesAsyncTask extends AsyncTask<Notes,Void,Void>{
//
//        private NotesDao notesDao;
//
//        private DeleteNotesAsyncTask(NotesDao notesDao){
//            this.notesDao = notesDao;
//        }
//
//        @Override
//        protected Void doInBackground(Notes... notes) {
//            notesDao.delete(notes[0]);
//            return null;
//        }
//
//        //1. parameter for doInBackground method
//        //2. parameter for onProgressUpdate method
//        //3. parameteer return type of deInBackground
//
//    }
}
