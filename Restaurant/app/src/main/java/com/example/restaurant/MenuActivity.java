package com.example.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

//    variables
    ListView nListView;
    MenuRequest data;
    ArrayList<String> lines;

//    function from interface from MenuRequest
    @Override
    public void gotMenues(ArrayList<String> categories) {
        lines = categories;
//        define ListView and set OnClickListener
        nListView = findViewById(R.id.ListView1);
        nListView.setOnItemClickListener(new GridItemClickListener());
        nListView.setAdapter(new MenuListAdapter(this, 0, lines));
    }

//    function from interface from MenuRequest
    @Override
    public void gotMenuesError(String message) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        String category = intent.getSerializableExtra("category").toString();
//        define all the data
        data = new MenuRequest(this, category);
        data.getMenues(this);
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
            intent.putExtra("item", lines.get(position));
            startActivity(intent);
        }
    }
}
