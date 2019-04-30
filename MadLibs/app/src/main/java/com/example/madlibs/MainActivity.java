package com.example.madlibs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Story first;
    private String joe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (load() == null){
            setContentView(R.layout.activity_main);
            List<String> List = new ArrayList<>();
            List.add("madlib0_simple");
            List.add("madlib1_tarzan");
            List.add("madlib2_university");
            List.add("madlib3_clothes");
            List.add("madlib4_dance");

            Random rand = new Random();
            int version = rand.nextInt(List.size());
            joe = List.get(version);
            first = get_story(joe);
            System.out.println(first.text);
            save();
        }
        else{
            first = load();
            System.out.println(first.text);
            if (first.getPlaceholderRemainingCount() == 0){
                setContentView(R.layout.story);
                TextView one = findViewById(R.id.textView2);
                String haha = first.toString();
                one.setText(haha);
            }
            else{
                View button = findViewById(R.id.button);
                onItemClick(button);
            }
            save();
        }
//        System.out.println(first.text);
    }

    private Story get_story(String joe) {

        if (joe == "madlib0_simple") {
            InputStream story = getResources().openRawResource(R.raw.madlib0_simple);
            Story hey = new Story(story);
            return hey;
        }
        else if (joe == "madlib1_tarzan") {
            InputStream story = getResources().openRawResource(R.raw.madlib1_tarzan);
            Story hey = new Story(story);
            return hey;
        }
        else if (joe == "madlib2_university") {
            InputStream story = getResources().openRawResource(R.raw.madlib2_university);
            Story hey = new Story(story);
            return hey;
        }
        else if (joe == "madlib3_clothes") {
            InputStream story = getResources().openRawResource(R.raw.madlib3_clothes);
            Story hey = new Story(story);
            return hey;
        }
        else{
            InputStream story = getResources().openRawResource(R.raw.madlib4_dance);
            Story hey = new Story(story);
            return hey;
        }

    }

    public void onItemClick(View view) {
        setContentView(R.layout.main_fillin);
        TextView remaining = findViewById(R.id.textView4);
        int count = first.getPlaceholderRemainingCount();
        String count1 = Integer.toString(count);
        remaining.setText(count1 + " word(s) left");
        TextView next = findViewById(R.id.textView6);
        String next1 = first.getNextPlaceholder();
        System.out.println(next1);
        next.setText("please type a " + next1);
        TextView input = findViewById(R.id.editText);
        input.setHint(next1);
        save();
    }

    public void onItemClick1(View view){
        TextView input = findViewById(R.id.editText);
        String input1 = input.getText().toString();
        first.fillInPlaceholder(input1);
        String next1 = first.getNextPlaceholder();
        input.setText("");
        input.setHint(next1);
        TextView remaining = findViewById(R.id.textView4);
        int count = first.getPlaceholderRemainingCount();
        String count1 = Integer.toString(count);
        remaining.setText(count1 + " word(s) left");
        TextView next = findViewById(R.id.textView6);

        System.out.println(next1);
        next.setText("please type a " + next1);


        if (first.getPlaceholderRemainingCount() == 0) {
            setContentView(R.layout.story);
            TextView one = findViewById(R.id.textView2);
            String haha = first.toString();
            one.setText(haha);
        }
        save();
    }
    public void onItemClick2(View view){
        first.clear();
        setContentView(R.layout.main_fillin);
        List<String> List = new ArrayList<>();
        List.add("madlib0_simple");
        List.add("madlib1_tarzan");
        List.add("madlib2_university");
        List.add("madlib3_clothes");
        List.add("madlib4_dance");

        Random rand = new Random();
        int version = rand.nextInt(List.size());
        joe = List.get(version);
        first = get_story(joe);

        onItemClick(view);
        save();
    }

    public void save(){

//        save SharedPreference correct with editor
        SharedPreferences sharedPreferences=getSharedPreferences("first",MODE_PRIVATE);
        SharedPreferences sharedPreferences1=getSharedPreferences("second",MODE_PRIVATE);
        SharedPreferences sharedPreferences2=getSharedPreferences("third",MODE_PRIVATE);
        SharedPreferences sharedPreferences3=getSharedPreferences("fourth",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        editor.putString("first",joe);
        editor1.putString("second",first.text);
        editor2.putInt("third",first.filledIn);
        editor3.putBoolean("fourth",first.htmlMode);
        editor.commit();
        editor1.commit();
        editor2.commit();
        editor3.commit();
    }
    public Story load(){
//        load the SharedPreference if already saved
        SharedPreferences sharedPreferences=getSharedPreferences("first",MODE_PRIVATE);
        SharedPreferences sharedPreferences1=getSharedPreferences("second",MODE_PRIVATE);
        SharedPreferences sharedPreferences2=getSharedPreferences("third",MODE_PRIVATE);
        SharedPreferences sharedPreferences3=getSharedPreferences("fourth",MODE_PRIVATE);
        String set =sharedPreferences.getString("first",null);
        String set1 =sharedPreferences1.getString("second",null);
        int set2 =sharedPreferences2.getInt("third",0);
        Boolean set3 =sharedPreferences3.getBoolean("fourth",false);
        Story story = get_story(set);
        story.text = set1;
        story.filledIn = set2;
        story.htmlMode = set3;
        if (set == null || set1 == null || set2 == 0){
            return null;
        }
        else{
            joe = set;
            first = story;
            return story;
        }

    }
}
