package com.kerrel.datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kerrel.datapickerlib.DatePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.picker).setOnClickListener(onDatePickListener);
    }


    private View.OnClickListener onDatePickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePicker datePicker = new DatePicker.Builder(MainActivity.this)
                    .setDate(Calendar.getInstance())
                    .create();
            datePicker.show(getSupportFragmentManager(), onDatePickListener2);
        }
    };

    private DatePicker.OnDatePickListener onDatePickListener2 = new DatePicker.OnDatePickListener() {
        @Override
        public void onDatePick(Calendar calendar) {
            TextView date = (TextView) findViewById(R.id.date);
            date.setText(calendar.toString());
        }
    };
}
