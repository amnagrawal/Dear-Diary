package com.aman.deardiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aman.deardiary.Databasehandler.DatabaseHelper;
import com.aman.deardiary.Databasehandler.DiaryEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PreviousEntries extends AppCompatActivity {

    List<DiaryEntry> diaryEntries;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbHelper = new DatabaseHelper(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_entries);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewEntry.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recList = (RecyclerView) findViewById(R.id.listentries);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new org.solovyev.android.views.llm.
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recList.setLayoutManager(llm);

        List<ListItemInfo> listItemInfos = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        diaryEntries = dbHelper.getAllEntries();
        for(DiaryEntry entry : diaryEntries) {
            listItemInfos.add(new ListItemInfo(sdf.format(entry.getDate()), entry.getContent()));
        }
        recList.setAdapter(new ListEntriesAdapter(listItemInfos));
        recList.setNestedScrollingEnabled(false);

        dbHelper.close();
    }
}
