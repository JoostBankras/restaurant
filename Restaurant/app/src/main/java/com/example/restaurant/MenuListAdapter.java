package com.example.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MenuListAdapter extends ArrayAdapter<String> {
//    items for listview
    public ArrayList<String> ListItems;

//    puts everything in the adapterview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if there is no view, make a view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menulistitem, parent, false);
        }

//        get variables for list item
        String line = ListItems.get(position);
        String [] parts = line.split("~");
        for (int i=0;i<parts.length;i++){
            System.out.println(parts[i]);
        }
        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);
        name.setText(parts[5]);
        price.setText(parts[2]);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        String url = parts[3];
        Picasso.get().load(url).into(imageView);

        return convertView;
    }

//    call this function for adapting everything for the list
    public MenuListAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        ListItems = objects;
    }
}
