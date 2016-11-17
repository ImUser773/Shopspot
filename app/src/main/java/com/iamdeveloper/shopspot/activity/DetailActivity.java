package com.iamdeveloper.shopspot.activity;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iamdeveloper.shopspot.R;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    private ImageView imageView, imageClose;
    private TextView textName, textDate;
    private String image;
    private String name;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bindView();

        if(savedInstanceState != null){
            Log.i(TAG, "not null");
        }else {

            Bundle b = getIntent().getExtras();
            if (b != null) {
                image = b.getString("image");
                name = b.getString("name");
                date = b.getString("date");
                Log.i("name", name);
                Picasso.with(this).load(image).into(imageView);
                textName.setText(name);
                Log.i("Data", date);
                textDate.setText(date.substring(0, 10));
        }

        }


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("image", image);
        outState.putString("name", name);
        outState.putString("date", date);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        image = savedInstanceState.getString("image");
        name = savedInstanceState.getString("name");
        date = savedInstanceState.getString("date");

        if(!image.isEmpty()&&!name.isEmpty()&&!date.isEmpty()) {
            Log.i("name", name);
            Picasso.with(this).load(image).into(imageView);
            textName.setText(name);
            Log.i("Data", date);
            textDate.setText(date.substring(0, 10));
        }
    }

    private void bindView() {
        imageView = (ImageView) findViewById(R.id.image);
        imageClose = (ImageView) findViewById(R.id.image_close);
        textName = (TextView) findViewById(R.id.txt_name);
        textDate = (TextView) findViewById(R.id.txt_date);
    }


}
