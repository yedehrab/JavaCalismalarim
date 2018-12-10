package com.yunusemre.derscalismao;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavBookActivity extends AppCompatActivity implements Base, View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageButton ibHome;
    private ListView lvNames;

    private ArrayList<String> arrNames;
    private ArrayAdapter<String> adpNames;
    public static ArrayList<Bitmap> arrImages;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav_book, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_addNote) {
            Globals.getInstance().showShortToastText(this, "Adding menu is opening...");

            startActivity(
                    new Intent(this, FavBookMenuAddNoteActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            );
        } else if (item.getItemId() == R.id.menu_donate) {
            Globals.getInstance().showShortToastText(this, "Thank you for your donation :)");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_book);

        initViews();
        bindEvents();
        defineObjects();
        setProperties();
    }

    @Override
    public void initViews() {
        ibHome = findViewById(R.id.ibGoMain);
        lvNames = findViewById(R.id.lvFavBook);
    }

    @Override
    public void bindEvents() {
        ibHome.setOnClickListener(this);
        lvNames.setOnItemClickListener(this);
    }

    @Override
    public void defineObjects() {
        arrNames = new ArrayList<>();
        arrImages = new ArrayList<>();
        adpNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrNames);
        lvNames.setAdapter(adpNames);
    }

    @Override
    public void setProperties() {

        try {
            FavBookMenuAddNoteActivity.database = openOrCreateDatabase(FavBookMenuAddNoteActivity.NAME_DATABASE, MODE_PRIVATE, null);
            FavBookMenuAddNoteActivity.database.execSQL(
                    "CREATE TABLE IF NOT EXISTS " + FavBookMenuAddNoteActivity.NAME_TABLE + "(name VARCHAR, image BLOB)"
            );

            Cursor cursor = FavBookMenuAddNoteActivity.database.rawQuery(
                    "SELECT * FROM " + FavBookMenuAddNoteActivity.NAME_TABLE,
                    null
            );
            cursor.moveToFirst();

            while (cursor != null) {
                arrNames.add(cursor.getString(cursor.getColumnIndex("name")));
                arrImages.add(
                        BitmapFactory.decodeByteArray(
                                cursor.getBlob(cursor.getColumnIndex("image")),
                                0,
                                cursor.getBlob(cursor.getColumnIndex("image")).length
                        )
                );

                // Refreshing list views
                adpNames.notifyDataSetChanged();

                cursor.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ibHome) {
            Globals.getInstance().ibGoMainWithoutErasingOnClick(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(
                new Intent(this, FavBookMenuAddNoteActivity.class)
                    .putExtra("new", false)
                    .putExtra("name", arrNames.get(i))
                    .putExtra("position", i)
        );
    }
}

