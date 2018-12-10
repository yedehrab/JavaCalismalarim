package com.yunusemre.derscalismao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LandmarkCatalogDetailActivity extends AppCompatActivity implements Base, View.OnClickListener {

    private TextView tvLandMark;
    private ImageView ivLandMark;
    private ImageButton ibGoMain;

    private String landMarkName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_catalog_detail);

        initViews();
        bindEvents();
        defineObjects();
        setProperties();
    }

    @Override
    public void initViews() {
        tvLandMark = findViewById(R.id.tvLandmark);
        ivLandMark = findViewById(R.id.ivLandmark);
        ibGoMain = findViewById(R.id.ibGoMain);
    }

    @Override
    public void bindEvents() {
        ibGoMain.setOnClickListener(this);
    }

    @Override
    public void defineObjects() {
        landMarkName = getIntent().getStringExtra("name");

    }

    @Override
    public void setProperties() {
        tvLandMark.setText(landMarkName);
        ivLandMark.setImageBitmap(Globals.getInstance().getLandmarkSelectedImage());
        setFinishOnTouchOutside(true);
    }

    @Override
    public void onClick(View v) {
        if(v == ibGoMain)
            Globals.getInstance().defaultIbGoMainOnClick(this);

    }
}
