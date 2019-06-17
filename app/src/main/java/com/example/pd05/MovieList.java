package com.example.pd05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {

    Button btnadd;
    Button btnEdit;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Movie> Movieal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        btnadd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);

        lv = findViewById(R.id.lvMovelist);
        Movieal = new ArrayList<Movie>();
        aa = new MovieAdapter(this,R.layout.row,Movieal);
        DBHelper db = new DBHelper(MovieList.this);
        final ArrayList<Movie> data = db.getAllMovies();
        aa = new MovieAdapter(this,R.layout.row,data);
        lv.setAdapter(aa);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MovieList.this,AddMovie.class);
                startActivity(i);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MovieList.this,EditMovie.class);
                startActivity(i);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(MovieList.this,
                        EditMovie.class);
                int dataID = data.get(position).getId();
                String dataTitle = data.get(position).getTitle();
                int dataRelease = data.get(position).getRelease();
                String dataYear = data.get(position).getYear();
                int dataStar = data.get(position).getStars();

                Movie target = new Movie(dataID, dataTitle, dataYear, dataRelease, dataStar);
                i.putExtra("data", target);
                startActivity(i);

            }
        });

    }
}
