package com.example.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

//    variables
    Context context;
    Callback activity1;
    ArrayList<String> list = new ArrayList<>();
    String category;

//    interface for functions
    public interface Callback {
        void gotMenues(ArrayList<String> categories);
        void gotMenuesError(String message);
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
            array = response.getJSONArray("items");
            for (int i = 0; i < array.length(); i++ ){
                ArrayList list1 = new ArrayList();
                JSONObject object = array.getJSONObject(i);
                String Category = object.getString("category");
                String Description = object.getString("description");
                String Price = object.getString("price");
                String Image_URL = object.getString("image_url");
                Integer id = object.getInt("id");
                String name = object.getString("name");
                String text = Category + "~" + Description + "~" + Price + "~" + Image_URL + "~" + id + "~" + name + "~";
                list.add(text);
            }
            activity1.gotMenues(list);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

//    constructor for MenuRequest
    public MenuRequest(Context context1, String category1){
        category = category1;
        context = context1;
    }

//    callable function for Menu's
    public void getMenues(Callback activity){
        activity1 = activity;
        String url = "https://resto.mprog.nl/menu?category=" + category;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null, this, this);
        queue.add(jsonObjectRequest);

    }
}

