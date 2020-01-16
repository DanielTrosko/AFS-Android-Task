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

                if (acceptPosition != position & inProgressTask) {
                    Toast.makeText(myContext, "Only one task can have a status different than OPEN", Toast.LENGTH_LONG).show();
                } else {

                    if (textId.getText().equals(StatusType.OPEN.toString())) {
                        changeStatus(textId, button, finalConvertView, position, StatusType.TRAVELLING, "START WORK", Color.YELLOW, true);

                    } else if (textId.getText().equals(StatusType.TRAVELLING.toString())) {
                        changeStatus(textId, button, finalConvertView, position, StatusType.WORKING, "STOP", Color.GREEN, true);

                    } else if (textId.getText().equals(StatusType.WORKING.toString())) {
                        changeStatus(textId, button, finalConvertView, position, StatusType.OPEN, "START TRAVEL", Color.WHITE, false);


                    }
                }
            }

        });


        return convertView;
    }

    private void changeStatus(TextView textId, Button button, View finalConvertView, int position, StatusType status, String btnName, int colour, boolean progressTask) {
        textId.setText(status.toString());
        button.setText(btnName);
        finalConvertView.setBackgroundColor(colour);
        taskList.get(position).setStatus(status);
        taskList.get(position).setChangeStatus(btnName);
        acceptPosition = position;
        inProgressTask = progressTask;
    }
}
