package com.operatoroverloading.persiancalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class DateConverter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_converter);

        // get a handle of all textvies
        final EditText year = (EditText) findViewById(R.id.dateYear);
        final EditText month = (EditText) findViewById(R.id.dateMonth);
        final EditText day = (EditText) findViewById(R.id.dateDay);

        final TextView result = (TextView) findViewById(R.id.dateResult);

        final Roozh dateConverter = new Roozh();
        final Switch s = (Switch) findViewById(R.id.switch1);

        Button b = (Button) findViewById(R.id.submit);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (year.getText().toString() != "" & month.getText().toString() != "" & day.getText().toString() != ""){
                    if (s.isChecked()){
                        dateConverter.GregorianToPersian(Integer.parseInt(year.getText().toString()),Integer.parseInt(month.getText().toString()),Integer.parseInt(day.getText().toString()));
                    }else {
                        dateConverter.PersianToGregorian(Integer.parseInt(year.getText().toString()),Integer.parseInt(month.getText().toString()),Integer.parseInt(day.getText().toString()));
                    }

                    result.setText(dateConverter.toString());
                }
            }
        });

    }
}
