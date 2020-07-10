package com.gestor.jonny.red.SharedActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.gestor.jonny.red.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatePickerActivity extends AppCompatActivity {
    @BindView(R.id.picker) DatePicker datePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker_layout);
        ButterKnife.bind(this);
        getDatePickerIntent();
    }

    @Override
    public void onBackPressed() {
        Intent i = getIntent();
        i.putExtra("timestamp", getTime());
        setResult(RESULT_OK, i);
        super.onBackPressed();
    }

    private void getDatePickerIntent() {
        long timestamp = getIntent().getLongExtra("timestamp", 0);

        Date date = new Date();
        if (timestamp != 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp * 1000);
            date = calendar.getTime();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private long getTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

        return cal.getTimeInMillis() / 1000;
    }
}
