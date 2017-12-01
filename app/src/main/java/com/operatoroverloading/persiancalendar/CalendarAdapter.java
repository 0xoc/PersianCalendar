package com.operatoroverloading.persiancalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import SinaPersianCalendar.PersianDate;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder>  {
    PersianDate data[];
    private LayoutInflater inflater;
    private Context context;
    private String daysToPrint[];
    private int startDay;

    public CalendarAdapter(Context context, PersianDate[] data) {
        this.context = context;
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
        if(position < startDay)
            holder.title.setBackgroundColor(0);
        holder.title.setText(daysToPrint[position]);

    }

    @Override
    public int getItemCount() {
        return daysToPrint.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RelativeLayout rl;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            rl = (RelativeLayout) itemView.findViewById(R.id.item);

        }
    }

}

