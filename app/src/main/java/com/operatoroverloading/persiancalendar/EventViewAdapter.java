package com.operatoroverloading.persiancalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import CalendarCore.EventCore.Event;

/**
 * Created by sina on 1/26/18.
 */

public class EventViewAdapter extends RecyclerView.Adapter<EventViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Event> events;
    private LayoutInflater inflater;
    private boolean showDate;

    public EventViewAdapter (Context context,ArrayList<Event> events,boolean showDate) {
        this.events = events;
        this.showDate = showDate;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public EventViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.event_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventViewAdapter.ViewHolder holder, final int position) {
        holder.title.setText(events.get(position).getTitle());
        holder.description.setText(events.get(position).getDescription());
        holder.time.setText(events.get(position).getHour() + ":" + events.get(position).getMin());
        holder.type.setText(events.get(position).getType().getEventTitle());
        holder.location.setText(events.get(position).getLocation());

        // change date font
        Typeface tf = Typeface.createFromAsset(context.getResources().getAssets(),"mt.ttf");
        holder.date.setTypeface(tf);

        // get the event date and make a string out of it
        if (showDate){
            String dateString = events.get(position).getDate().getYear() + "/" +
                                events.get(position).getDate().getMonth() + "/" +
                                events.get(position).getDate().getDay();
            holder.date.setText(dateString);
        } else {
            holder.date.setVisibility(View.GONE);
        }

        // set click listener for deleting an event
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = Event.getIndexById(events.get(position).getId());
                Event.events.remove(index);
                Toast.makeText(context,"رویداد انتخاب شده پاک شد",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ViewAllEvents.class);
                ((Activity)context).finish();
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView location;
        public TextView type;
        public TextView time;
        public TextView date;
        public ImageButton delete;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.viewEventTitle);
            this.description = itemView.findViewById(R.id.viewEventDescription);
            this.type = itemView.findViewById(R.id.viewEventType);
            this.time = itemView.findViewById(R.id.viewEventTime);
            this.location = itemView.findViewById(R.id.viewEventLocation);
            this.date = itemView.findViewById(R.id.viewEventDate);
            this.delete = itemView.findViewById(R.id.viewDeleteEvent);
        }
    }
}
