package com.example.restaurant;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {

//    variables
    ListView nListView;
    CategoriesRequest data;
    ArrayList<String> list;

//    oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        define all the data
        data = new CategoriesRequest(this);
        data.getCategories(this);
//        define ListView and set OnClickListener
        nListView = findViewById(R.id.listview);
        nListView.setOnItemClickListener(new GridItemClickListener());
    }

//    function from interface from CategoriesRequest
    @Override
    public void gotCategories(ArrayList<String> categories) {
        list = categories;
        nListView.setAdapter(new ListItemAdapter(this, 0, categories));
    }

//    function from interface from CategoriesRequest
    @Override
    public void gotCategoriesError(String message) {
        System.out.println(message);
    }

//    GridItemClicklistener for ListView
    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = list.get(position);
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
//            put category with intent
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }

}
