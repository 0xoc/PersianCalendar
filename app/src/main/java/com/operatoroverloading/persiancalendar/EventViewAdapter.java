package com.operatoroverloading.persiancalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import CalendarCore.EventCore.Event;

/**
 * Created by sina on 1/26/18.
 */

public class EventViewAdapter extends RecyclerView.Adapter<EventViewAdapter.ViewHolder> {
    private ArrayList<Event> events;
    private LayoutInflater inflater;


    public EventViewAdapter (Context context,ArrayList<Event> events) {
        this.events = events;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public EventViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.event_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventViewAdapter.ViewHolder holder, int position) {
        holder.title.setText(events.get(position).getTitle());
        holder.description.setText(events.get(position).getDescription());
        holder.time.setText(events.get(position).getHour() + ":" + events.get(position).getMin());
        holder.type.setText(events.get(position).getType().getEventTitle());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView type;
        public TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.viewEventTitle);
            this.description = itemView.findViewById(R.id.viewEventDescription);
            this.type = itemView.findViewById(R.id.viewEventType);
            this.time = itemView.findViewById(R.id.viewEventTime);
        }
    }
}
