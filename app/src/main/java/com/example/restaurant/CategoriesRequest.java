package com.example.restaurant;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

//    variables
    Context context;
    String url = "https://resto.mprog.nl/categories";
    Callback activity1;
    ArrayList<String> list = new ArrayList<String>();

//    interface for functions
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

//    if JSONObject isn't received properly
    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error);
    }

//    if JSONObject is received properly
    @Override
    public void onResponse(JSONObject response) {
        JSONArray array;
//        get the JSONArray's if possible
        try{
            array = response.getJSONArray("categories");
            for (int i = 0; i < array.length(); i++ ){
                String object1 = array.get(i).toString();
                list.add(object1);
            }
            activity1.gotCategories(list);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

//    constructor for CategoriesRequest
    public CategoriesRequest(Context context1){
        context = context1;
    }

//    callable function for Categories
    public void getCategories(Callback activity){
        activity1 = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null, this, this);
        queue.add(jsonObjectRequest);

    }
}

