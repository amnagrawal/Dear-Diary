package com.aman.deardiary;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.aman.deardiary.Databasehandler.DatabaseHelper;
import com.aman.deardiary.Databasehandler.DiaryEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewEntry extends AppCompatActivity {

    TextView date;
    DatePickerDialog datePickerDialog;
    EditText content;
    DiaryEntry newEntry;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DatabaseHelper(getApplicationContext());

        setContentView(R.layout.activity_new_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        newEntry = new DiaryEntry();
        date = (TextView) findViewById(R.id.date_new_entry);

        newEntry.setCreatedAt(new Date(Calendar.getInstance().getTimeInMillis()));
        newEntry.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        newEntry.setContent("FTW");

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(NewEntry.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);

                        //    Log.i("CHECK: ", String.valueOf(newDate.get(dayOfMonth)));

                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, EEEE");
                        date.setText(sdf.format(newDate.getTimeInMillis()));
                        sdf = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            newEntry.setCreatedAt(sdf.parse(sdf.format(Calendar.getInstance().getTimeInMillis())));
                            newEntry.setDate(sdf.parse(newDate.get(Calendar.YEAR)+"-"+
                                    (newDate.get(Calendar.MONTH)+1)+"-"+newDate.get(Calendar.DAY_OF_MONTH)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();
            }
        });

        content = (EditText) findViewById(R.id.content_new_entry);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
*/

                newEntry.setContent(content.getText().toString());
                dbHelper.createNewEntry(newEntry);
            }
        });
        dbHelper.closeDB();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        date.setText(savedInstanceState.getString("Date"));
        content.setText(savedInstanceState.getString("Content"));
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Date", date.getText().toString());
        savedInstanceState.putString("Content", content.getText().toString());
    }
}
