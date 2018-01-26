package com.operatoroverloading.persiancalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by nima on 12/10/2017 AD.
 */

public class Home extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Button calendar = (Button) findViewById(R.id.calendar);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,MainActivity.class));
            }
        });
    }

    private void gotoCalendarActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
