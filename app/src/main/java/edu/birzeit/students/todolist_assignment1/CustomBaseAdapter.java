package edu.birzeit.students.todolist_assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<Task> items;
    LayoutInflater inflater;
    private final Map<Integer, Boolean> checkBoxStates;
//private TaskActivity taskactivity;

    public CustomBaseAdapter(Context con,  ArrayList<Task> Task) {
        this.context=con;
        this.items=Task;
        this.inflater=LayoutInflater.from(con);
        this.checkBoxStates = new HashMap<>();


    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView text=(TextView) view.findViewById(R.id.textv);
        CheckBox check=(CheckBox) view.findViewById(R.id.chbox);
        text.setText( items.get(i).getTitle());
        check.setChecked(items.get(i).isDone());
        check.setChecked(checkBoxStates.getOrDefault(i, false));



        return view;
    }
    public void setChecked(int position, boolean isChecked) {
        checkBoxStates.put(position, isChecked);
        notifyDataSetChanged();
    }
}


