package edu.birzeit.students.todolist_assignment1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TaskDetail extends AppCompatActivity {
    private EditText titletext;
    private EditText destext;

    public static final String Done = "Done";
    private boolean flag = false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String FLAG = "FLAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        setupViews();
        setupSharedPrefs();
        state();

        String title = getIntent().getStringExtra("data");

        titletext.setText(title);


    }

    private void setupViews() {
        titletext = findViewById(R.id.titletext);
        destext = findViewById(R.id.disptext);

    }

    public void enterButton(View view) {

        String done = destext.getText().toString();

       if (flag) {

            editor.putString(Done, done);
            editor.putBoolean(FLAG, true);
            editor.apply();

        }
        finish();
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void state() {
        flag = prefs.getBoolean(FLAG, false);


        if (flag) {

            String state = prefs.getString(Done, "");

            destext.setText(state);

        }
    }
}
