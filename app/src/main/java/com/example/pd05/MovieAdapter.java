package com.example.pd05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private ArrayList<Movie> movie;
    private Context context;
    private TextView tvTitle, tvYear, tvRelease;
    private ImageView ivPoster, ivStar, ivStar2, ivStar3, ivStar4, ivStar5;

    public MovieAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        movie = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvTitle = rowView.findViewById(R.id.textViewTitle);
        tvYear = rowView.findViewById(R.id.textViewDate);
        tvRelease = rowView.findViewById(R.id.textViewRelease);

        ivPoster = rowView.findViewById(R.id.ivLogo);
        ivStar = rowView.findViewById(R.id.iv1);
        ivStar2 = rowView.findViewById(R.id.iv2);
        ivStar3 = rowView.findViewById(R.id.iv3);
        ivStar4 = rowView.findViewById(R.id.iv4);
        ivStar5 = rowView.findViewById(R.id.iv5);

        Movie currentMovie = movie.get(position);

        tvTitle.setText(currentMovie.getTitle());
        tvYear.setText(currentMovie.getYear());
        String release = Integer.toString(currentMovie.getRelease());
        tvRelease.setText(release);

        int stars = currentMovie.getStars();

        if(stars == 5) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.star);
            ivStar3.setImageResource(R.drawable.star);
            ivStar4.setImageResource(R.drawable.star);
            ivStar5.setImageResource(R.drawable.star);

        }
        else if(stars == 4) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.star);
            ivStar3.setImageResource(R.drawable.star);
            ivStar4.setImageResource(R.drawable.star);
            ivStar5.setImageResource(R.drawable.nostar);
        }

        else if(stars == 3) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.star);
            ivStar3.setImageResource(R.drawable.star);
            ivStar4.setImageResource(R.drawable.nostar);
            ivStar5.setImageResource(R.drawable.nostar);
        }

        else if(stars == 2) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.star);
            ivStar3.setImageResource(R.drawable.nostar);
            ivStar4.setImageResource(R.drawable.nostar);
            ivStar5.setImageResource(R.drawable.nostar);
        }

        else if(stars == 1) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.nostar);
            ivStar3.setImageResource(R.drawable.nostar);
            ivStar4.setImageResource(R.drawable.nostar);
            ivStar5.setImageResource(R.drawable.nostar);
        }

        else {
            ivStar.setImageResource(R.drawable.nostar);
            ivStar2.setImageResource(R.drawable.nostar);
            ivStar3.setImageResource(R.drawable.nostar);
            ivStar4.setImageResource(R.drawable.nostar);
            ivStar5.setImageResource(R.drawable.nostar);
        }


        return rowView;
    }
}
