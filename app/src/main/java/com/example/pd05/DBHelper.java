package com.example.pd05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_MOVIE = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MOVIE_TITLE = "movie_title";
    private static final String COLUMN_MOVIE_YEAR = "movie_year";
    private static final String COLUMN_MOVIE_RELEASE = "movie_release";
    private static final String COLUMN_MOVIE_STARS = "movie_stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql =  "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MOVIE_TITLE + " TEXT ," + COLUMN_MOVIE_YEAR + " TEXT, " + COLUMN_MOVIE_RELEASE + " INTEGER, " + COLUMN_MOVIE_STARS + " INTEGER )";

        db.execSQL(createSongTableSql);
        Log.i("info","created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "SONG");
        onCreate(db);
    }

    public long insertMovie(String title, String year, int release, int stars) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_MOVIE_TITLE, title);
        values.put(COLUMN_MOVIE_YEAR, year);
        values.put(COLUMN_MOVIE_RELEASE, release);
        values.put(COLUMN_MOVIE_STARS, stars);

        long result = db.insert(TABLE_MOVIE, null, values);

        //check if record is inserted successfully
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }

        db.close();
        Log.d("SQL Insert ", "" + result);
        //id returned, shouldn’t be -1

        return result;

    }

    public int updateMovie(Movie data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_MOVIE_TITLE, data.getTitle());
        values.put(COLUMN_MOVIE_YEAR, data.getYear());
        values.put(COLUMN_MOVIE_RELEASE, data.getRelease());
        values.put(COLUMN_MOVIE_STARS, data.getStars());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};

        int result = db.update(TABLE_MOVIE, values, condition, args);

        //check if record is updated successfully if the affected record is 1
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;

    }

    public int deleteMovie(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};

        int result = db.delete(TABLE_MOVIE, condition, args);

        //check if record is successfully updated if the affected record is 1
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;

    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        String selectQuery = "SELECT " + COLUMN_ID + "," + COLUMN_MOVIE_TITLE +
                "," + COLUMN_MOVIE_YEAR + "," + COLUMN_MOVIE_RELEASE + "," + COLUMN_MOVIE_STARS +
                " FROM " + TABLE_MOVIE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String year = cursor.getString(2);
                int release = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Movie obj = new Movie(id, title, year, release, stars);
                movies.add(obj);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;

    }
}
