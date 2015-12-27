package com.aman.deardiary;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PreviousEntries extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_entries);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recList = (RecyclerView) findViewById(R.id.listentries);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new org.solovyev.android.views.llm.
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recList.setLayoutManager(llm);

        List<ListItemInfo> listItemInfos = new ArrayList<>();
        listItemInfos.add(new ListItemInfo("16-01-1994", "my bday"));
        listItemInfos.add(new ListItemInfo("23-05-1998", "bro's bday"));
        listItemInfos.add(new ListItemInfo("04-06-1966", "dad's bday"));
        listItemInfos.add(new ListItemInfo("05-05-1966", "mom's bday"));
        listItemInfos.add(new ListItemInfo("26-10-1995", "ghissu's bday"));
        listItemInfos.add(new ListItemInfo("17-03-1995", "pitthu's bday"));
        recList.setAdapter(new ListEntriesAdapter(listItemInfos));
        recList.setNestedScrollingEnabled(false);
    }
}
