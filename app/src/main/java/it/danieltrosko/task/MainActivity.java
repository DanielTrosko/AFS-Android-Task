package it.danieltrosko.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = findViewById(R.id.listView);

        final ArrayList<Task> taskList = new ArrayList<>();

        for (int i = 1; i < 21; i++) {
            Task task = new Task("Task" + i, StatusType.OPEN, "START TRAVEL");
            taskList.add(task);
        }

        final TaskListAdapter adapter = new TaskListAdapter(this, R.layout.adapter_list_layout, taskList);

        listView.setAdapter(adapter);


    }
}
