package it.danieltrosko.task;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TaskListAdapter extends ArrayAdapter<Task> {


    private Context myContext;
    private int myResource;
    private ArrayList<Task> taskList;
    private int accept;
    private boolean acc = false;


    public TaskListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Task> objects) {
        super(context, resource, objects);
        this.myContext = context;
        this.myResource = resource;
        this.taskList = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = taskList.get(position).getName();
        String buttons = taskList.get(position).getButton();
        String id = taskList.get(position).getId();

        LayoutInflater inflater = LayoutInflater.from(myContext);
        convertView = inflater.inflate(myResource, parent, false);

        TextView textName = convertView.findViewById(R.id.textView1);
        final TextView textId = convertView.findViewById(R.id.textView2);
        final Button button = convertView.findViewById(R.id.button1);
        textName.setText(name);
        textId.setText(id);
        button.setText(buttons);
        final View finalConvertView = convertView;

        switch (id) {
            case "TRAVELLING":
                finalConvertView.setBackgroundColor(Color.BLUE);
                break;
            case "WORKING":
                finalConvertView.setBackgroundColor(Color.GREEN);
                break;
            case "OPEN":
                finalConvertView.setBackgroundColor(Color.WHITE);
                break;
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (accept != position & acc) {
                    Toast.makeText(myContext, "Only one task can have a status different than OPEN", Toast.LENGTH_LONG).show();
                } else {

                    if (textId.getText().equals("OPEN")) {
                        textId.setText("TRAVELLING");
                        button.setText("START WORK");
                        finalConvertView.setBackgroundColor(Color.BLUE);
                        taskList.get(position).setId("TRAVELLING");
                        taskList.get(position).setButton("START WORK");
                        accept = position;
                        acc = true;

                    } else if (textId.getText().equals("TRAVELLING")) {
                        textId.setText("WORKING");
                        button.setText("STOP");
                        finalConvertView.setBackgroundColor(Color.GREEN);
                        taskList.get(position).setId("WORKING");
                        taskList.get(position).setButton("STOP");
                        accept = position;
                        acc = true;

                    } else if (textId.getText().equals("WORKING")) {
                        textId.setText("OPEN");
                        button.setText("START TRAVEL");
                        finalConvertView.setBackgroundColor(Color.WHITE);
                        taskList.get(position).setId("OPEN");
                        taskList.get(position).setButton("START TRAVEL");
                        accept = -1;
                        acc = false;

                    }
                }
            }

        });


        return convertView;
    }
}
