package com.operatoroverloading.persiancalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

// is never used in the app
public class ConvertDateTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_date_test);
    }

    public void btn123_clicked(View v){
        RadioButton rd1 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton rd2 = (RadioButton) findViewById(R.id.radioButton3);
        if(rd1.isChecked() && rd2.isChecked()){
            Toast.makeText(this,"Error.Correct the radio buttons then push again",Toast.LENGTH_SHORT);

        }
        else if(!(rd1.isChecked() && rd2.isChecked())){
            Toast.makeText(this,"Error.Correct the radio buttons then push again",Toast.LENGTH_SHORT);
        }
        else{
            if(rd1.isChecked()){

            }
        }
    }

}

