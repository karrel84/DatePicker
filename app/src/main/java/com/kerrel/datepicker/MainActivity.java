package com.kerrel.datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kerrel.datapickerlib.DatePicker;
import com.kerrel.datapickerlib.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.picker).setOnClickListener(onDatePickListener);
        findViewById(R.id.time).setOnClickListener(onTimePickListener);
    }


    private View.OnClickListener onDatePickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePicker datePicker = new DatePicker.Builder(MainActivity.this)
                    .setDate(Calendar.getInstance())
                    .setMinDate(Calendar.getInstance().getTimeInMillis())
                    .create();
            datePicker.show(getSupportFragmentManager(), onDatePickListener2);
        }
    };

    private DatePicker.OnDatePickListener onDatePickListener2 = new DatePicker.OnDatePickListener() {
        @Override
        public void onDatePick(Calendar calendar) {
            TextView date = (TextView) findViewById(R.id.date);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date.setText(format.format(calendar.getTime()));
        }
    };

    private View.OnClickListener onTimePickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new TimePicker.Builder(MainActivity.this)
                    .create()
                    .show(getSupportFragmentManager(), onTimePickListener2);
        }
    };

    private TimePicker.OnTimePickListener onTimePickListener2 = new TimePicker.OnTimePickListener() {
        @Override
        public void onTimePick(int hourOfDay, int minute) {
            Log.d("TAG", String.format("%s:%s", hourOfDay, minute));
            TextView date = (TextView) findViewById(R.id.date);
            date.setText(String.format("%s:%s", hourOfDay, minute));
        }
    };
}
