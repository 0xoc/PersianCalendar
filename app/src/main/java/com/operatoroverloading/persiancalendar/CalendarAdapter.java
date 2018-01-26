package com.operatoroverloading.persiancalendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import SinaPersianCalendar.PersianDate;

import static android.R.color.holo_blue_bright;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder>  {
    PersianDate data[];
    private PersianDate today;
    private LayoutInflater inflater;
    private Context context;
    private String daysToPrint[];
    private int startDay;

    public CalendarAdapter(Context context, PersianDate[] data,PersianDate today) {
        this.context = context;
        this.today = today;
        inflater = LayoutInflater.from(context);
        this.data = data;
        int initDay = data[0].getDayOfWeek();
        if (initDay == 7)
            initDay = 0;
        startDay = initDay;

        daysToPrint = new String[data.length+initDay];

        // days of the week


        for(int i = 0;i<initDay;i++){
            daysToPrint[i] = "";
        }
        for(int i = 0;i<data.length;i++){
            daysToPrint[i + initDay] = data[i].getDay() + "";
        }

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int actual_position = position - startDay;
        // ignore days that are not
        if(position < startDay) {
            holder.title.setBackgroundColor(0);
            holder.title.setText("");
        }
        // color out the current day
        else if (data[actual_position].getDay() == today.getDay() & data[actual_position].getMonth() == today.getMonth() &  data[actual_position].getYear() == today.getYear()) {
            holder.title.setBackground(context.getResources().getDrawable(R.drawable.current_day));
            holder.title.setText(data[actual_position].getDay() + "");
        }
        else if ((position - startDay) < data.length) {
            holder.title.setText(data[actual_position].getDay() + "");
            if (data[actual_position].getDayOfWeek() == 6) {
                holder.title.setBackground(context.getResources().getDrawable(R.drawable.round_item_holyday));
            }
        }

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DayView.class);
                intent.putExtra("YEAR",data[actual_position].getYear());
                intent.putExtra("MONTH",data[actual_position].getMonth());
                intent.putExtra("DAY",data[actual_position].getDay());
                context.startActivity(intent);
            }
        });
        holder.title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context,DayEventView.class);
                intent.putExtra("DAY",data[actual_position].getDay());
                intent.putExtra("MONTH", data[actual_position].getMonth());
                intent.putExtra("YEAR",data[actual_position].getYear());
                context.startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length + startDay;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RelativeLayout rl;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            rl = (RelativeLayout) itemView.findViewById(R.id.item);
            Typeface tf = Typeface.createFromAsset(context.getAssets(),"mt.ttf");
            title.setTypeface(tf);
        }
    }

}

