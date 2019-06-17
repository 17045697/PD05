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
import android.widget.TextView;

public class EditMovie extends AppCompatActivity {

    TextView tvID;
    EditText etTitle;
    EditText etWatch;
    EditText etReleased;
    Movie data;
    RadioGroup rg;
    Button btnUpdate;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        tvID = findViewById(R.id.tvID);
        etTitle = findViewById(R.id.etContent);
        etWatch = findViewById(R.id.editTextWatch);
        etReleased = findViewById(R.id.editTextReleased);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        rg = findViewById(R.id.rg);


        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");
        tvID.setText("ID: "+data.getId());
        etTitle.setText(data.getTitle());
        etWatch.setText(data.getYear());
        etReleased.setText(data.getRelease()+"");
        if (data.getStars() == 5){
            RadioButton rb5 = findViewById(R.id.rb5);
            rb5.setChecked(true);
        }else if (data.getStars() == 4){
            RadioButton  rb4 = findViewById(R.id.rb4);
            rb4.setChecked(true);
        }else if (data.getStars() == 3){
            RadioButton  rb3 = findViewById(R.id.rb3);
            rb3.setChecked(true);
        }else if (data.getStars() == 2){
            RadioButton  rb2 = findViewById(R.id.rb2);
            rb2.setChecked(true);
        }else if (data.getStars() == 1){
            RadioButton  rb1 = findViewById(R.id.rb1);
            rb1.setChecked(true);
        }



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(EditMovie.this);
                data.setTitle(etTitle.getText().toString());
                data.setYear(etWatch.getText().toString());
                data.setRelease(Integer.parseInt(etReleased.getText().toString()));
                int radioid = rg.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(radioid) ;
                int radiochecked = Integer.parseInt(radioButton.getText().toString());
                data.setStars(radiochecked);

                dbh.updateMovie(data);
                dbh.close();
                Intent output = new Intent();
                output.putExtra("data",9);
                setResult(RESULT_OK, output);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(EditMovie.this);
                dbh.deleteMovie(data.getId());
                dbh.close();
                Intent output = new Intent();
                output.putExtra("data",9);
                setResult(RESULT_OK, output);
                finish();
            }
        });

    }
}
