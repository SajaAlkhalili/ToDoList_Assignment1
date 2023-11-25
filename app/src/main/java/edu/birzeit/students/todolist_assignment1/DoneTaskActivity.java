package edu.birzeit.students.todolist_assignment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DoneTaskActivity extends AppCompatActivity {
    private ListView listView;
    static ArrayList<String> taskList;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_task);

        taskList = new ArrayList<>();
        listView = findViewById(R.id.lview_Dtasks);

        // Load only completed tasks
        LoadCompletedTasks();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);

        Intent intent = getIntent();

        if (intent != null) {
            String taskTitle = intent.getStringExtra("data");


            taskList.add(taskTitle);


            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
            LoadCompletedTasks();

        }
    }

    private void LoadCompletedTasks() {
        prefs = getSharedPreferences("DATA", MODE_PRIVATE);
        gson = new Gson();
        String json = prefs.getString("Tasks", null);

        Type type = new TypeToken<ArrayList<Task>>() {}.getType();

        try {
            ArrayList<String> allTasks = gson.fromJson(json, type);

            if (allTasks != null) {
                // Clear the existing list before adding completed tasks
                taskList.clear();

                // Filter completed tasks
                for (String task : allTasks) {
                    if (task != null ) {
                        taskList.add(task);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
