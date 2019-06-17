package com.example.pd05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {

    Button btnInsert;
    Button btnShow;

    EditText etTitle;
    EditText etDate;
    EditText etYear;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShowList);

        etTitle = findViewById(R.id.etTitle);
        etDate = findViewById(R.id.etDate);
        etYear = findViewById(R.id.etYear);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String date = etDate.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                RadioGroup rg = findViewById(R.id.rg);
                int selectedButtonid = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedButtonid);
                int stars = Integer.parseInt(rb.getText().toString());

                DBHelper db = new DBHelper(AddMovie.this);
                long row_affected = db.insertMovie(title,date, year, stars);
                db.close();

                if (row_affected != -1) {
                    Toast.makeText(AddMovie.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddMovie.this,
                        MovieList.class);
                startActivity(i);
            }


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            btnShow.performClick();
        }
    }
}
