package com.yunusemre.derscalismao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class LandmarkCatalogActivity extends AppCompatActivity implements Base, View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageButton ibGoMain;
    private ListView lvLandmark;

    private ArrayList<String> landMarkNames;
    private ArrayList<Bitmap> landMarkImages;
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_catalog);

        initViews();
        bindEvents();
        defineObjects();
        setProperties();
    }

    @Override
    public void initViews() {
        ibGoMain = findViewById(R.id.ibGoMain);
        lvLandmark = findViewById(R.id.lvLandmark);
    }

    @Override
    public void bindEvents() {
        ibGoMain.setOnClickListener(this);
        lvLandmark.setOnItemClickListener(this);
    }

    @Override
    public void defineObjects() {
        landMarkNames = new ArrayList<>();
        landMarkImages = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, landMarkNames);
    }

    @Override
    public void setProperties() {
        setLandMarkNames();
        setLandMarkImages();
        lvLandmark.setAdapter(arrayAdapter);
    }

    public void setLandMarkNames() {
        landMarkNames.add("Pisa");
        landMarkNames.add("Colosseum");
        landMarkNames.add("Eiffel");
        landMarkNames.add("Kız Kulesi");
    }

    public void setLandMarkImages(){
        landMarkImages.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.pisa_tower));
        landMarkImages.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.colesseum));
        landMarkImages.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.eiffel_tower));
        landMarkImages.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kiz_kulesi));
    }

    @Override
    public void onClick(View v) {
        if(v == ibGoMain)
            Globals.getInstance().defaultIbGoMainOnClick(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Globals.getInstance().setLandmarkSelectedImage(landMarkImages.get(position));

        startActivity(
                new Intent(
                        LandmarkCatalogActivity.this, LandmarkCatalogDetailActivity.class)
                        .putExtra("name", landMarkNames.get(position)
                        ));
    }
}
