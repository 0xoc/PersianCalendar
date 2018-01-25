package com.operatoroverloading.persiancalendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sina on 1/25/18.
 */

public class DayTimesAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private ArrayList<String> data;

    public DayTimesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v  = inflater.inflate(R.layout.day_time_view,parent,false);
        TextView time = v.findViewById(R.id.dayTime);
        time.setText(data.get(position));
        return v;
    }
}
