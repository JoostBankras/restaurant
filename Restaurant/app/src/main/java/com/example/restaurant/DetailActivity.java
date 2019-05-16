package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String category = intent.getSerializableExtra("item").toString();
        String [] parts = category.split("~");
        TextView one = findViewById(R.id.textView2);
        one.setText(parts[5]);
        TextView two = findViewById(R.id.textView4);
        two.setText(parts[2]);
        TextView three = findViewById(R.id.textView3);
        three.setText(parts[1]);
        ImageView imageView = findViewById(R.id.imageView2);
        Picasso.get().load(parts[3]).into(imageView);
    }

}
