package com.kerrel.datapickerlib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import java.util.Calendar;

/**
 * Created by 이주영 on 2017-06-01.
 */

public class DatePicker extends AppCompatDialogFragment {

    public interface OnDatePickListener {
        void onDatePick(Calendar calendar);
    }

    private Builder mBuilder;
    private OnDatePickListener mOnDatePickListener;

    private android.widget.DatePicker mDatePicker;

    public DatePicker(Builder builder) {
        mBuilder = builder;
    }

    public void show(FragmentManager fragmentManager, OnDatePickListener onDatePickListener) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(this, getTag());
        ft.commitAllowingStateLoss();

        mOnDatePickListener = onDatePickListener;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mDatePicker = (android.widget.DatePicker) LayoutInflater.from(mBuilder.mContext).inflate(R.layout.activity_date_picker, null, false);

        dialog.setContentView(mDatePicker);

        mDatePicker.init(mBuilder.year, mBuilder.month, mBuilder.day, onDateChangedListener);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private android.widget.DatePicker.OnDateChangedListener onDateChangedListener = new android.widget.DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            mOnDatePickListener.onDatePick(calendar);
            dismiss();
        }
    };

    public static class Builder {
        public Context mContext;
        int year;
        int month;
        int day;

        public Builder(Context context) {
            this.mContext = context;
        }

        public DatePicker create() {
            DatePicker picker = new DatePicker(this);
            return picker;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setMonth(int month) {
            this.month = month;
            return this;
        }

        public Builder setDay(int day) {
            this.day = day;
            return this;
        }

        public Builder setDate(Calendar calendar) {
            setYear(calendar.get(Calendar.YEAR));
            setMonth(calendar.get(Calendar.MONTH));
            setDay(calendar.get(Calendar.DAY_OF_MONTH));

            return this;
        }
    }
}
