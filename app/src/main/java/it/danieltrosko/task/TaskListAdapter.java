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
    private int acceptPosition;
    private boolean inProgressTask = false;


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
        String buttons = taskList.get(position).getChangeStatus();
        StatusType id = taskList.get(position).getStatus();

        LayoutInflater inflater = LayoutInflater.from(myContext);
        convertView = inflater.inflate(myResource, parent, false);

        TextView textName = convertView.findViewById(R.id.task_name);
        final TextView textId = convertView.findViewById(R.id.status);
        final Button button = convertView.findViewById(R.id.change_status_btn);
        textName.setText(name);
        textId.setText(id.toString());
        button.setText(buttons);
        final View finalConvertView = convertView;

        switch (id) {
            case TRAVELLING:
                finalConvertView.setBackgroundColor(Color.YELLOW);
                break;
            case WORKING:
                finalConvertView.setBackgroundColor(Color.GREEN);
                break;
            case OPEN:
                finalConvertView.setBackgroundColor(Color.WHITE);
                break;
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(StatusType.OPEN);
                System.out.println(StatusType.TRAVELLING);
                System.out.println(StatusType.WORKING);
                if (acceptPosition != position & inProgressTask) {
                    Toast.makeText(myContext, "Only one task can have a status different than OPEN", Toast.LENGTH_LONG).show();
                } else {

                    if (textId.getText().equals(StatusType.OPEN.toString())) {
                        textId.setText(StatusType.TRAVELLING.toString());
                        button.setText("START WORK");
                        finalConvertView.setBackgroundColor(Color.YELLOW);
                        taskList.get(position).setStatus(StatusType.TRAVELLING);
                        taskList.get(position).setChangeStatus("START WORK");
                        acceptPosition = position;
                        inProgressTask = true;

                    } else if (textId.getText().equals(StatusType.TRAVELLING.toString())) {
                        textId.setText(StatusType.WORKING.toString());
                        button.setText("STOP");
                        finalConvertView.setBackgroundColor(Color.GREEN);
                        taskList.get(position).setStatus(StatusType.WORKING);
                        taskList.get(position).setChangeStatus("STOP");
                        acceptPosition = position;
                        inProgressTask = true;

                    } else if (textId.getText().equals(StatusType.WORKING.toString())) {
                        textId.setText(StatusType.OPEN.toString());
                        button.setText("START TRAVEL");
                        finalConvertView.setBackgroundColor(Color.WHITE);
                        taskList.get(position).setStatus(StatusType.OPEN);
                        taskList.get(position).setChangeStatus("START TRAVEL");
                        acceptPosition = -1;
                        inProgressTask = false;

                    }
                }
            }

        });


        return convertView;
    }
}
