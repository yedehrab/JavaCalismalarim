package com.yunusemre.derscalismao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener, Base {

    private ImageButton ibStartKro;
    private ImageButton ibStartGameLike;
    private ImageButton ibStartLandmarkCatalog;
    private ImageButton ibGoMain;
    private ImageButton ibStartDataBase;
    private ImageButton ibStartFavBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (getIntent().getBooleanExtra("new", true))
            Globals.getInstance().showAlertDialog(this, "Introduction",
                    "Firstly, Thank you for downloading my app :)\n" +
                            "-> Press any button to open what exist in itself\n" +
                            "-> Hold any button to download its source code\n" +
                            "-> Share this app with your friends to help me :)\n\n" +
                            "~~ Have Fun ;) ~~\n"
            );


        initViews();
        bindEvents();
        defineObjects();
        setProperties();
    }

    @Override
    public void initViews() {
        ibStartKro = findViewById(R.id.ibStartKro);
        ibStartGameLike = findViewById(R.id.ibStartLikeGame);
        ibStartLandmarkCatalog = findViewById(R.id.ibStartLandmarkCatalog);
        ibGoMain = findViewById(R.id.ibGoMain);
        ibStartDataBase = findViewById(R.id.ibStartDataBase);
        ibStartFavBook = findViewById(R.id.ibStartFavBook);
    }

    @Override
    public void bindEvents() {
        ibStartKro.setOnClickListener(this);
        ibStartGameLike.setOnClickListener(this);
        ibStartLandmarkCatalog.setOnClickListener(this);
        ibGoMain.setOnClickListener(this);
        ibStartDataBase.setOnClickListener(this);
        ibStartFavBook.setOnClickListener(this);

        ibStartKro.setOnLongClickListener(this);
        ibStartGameLike.setOnLongClickListener(this);
        ibStartLandmarkCatalog.setOnLongClickListener(this);
        ibStartDataBase.setOnLongClickListener(this);
        ibStartFavBook.setOnLongClickListener(this);
    }

    @Override
    public void defineObjects() {

    }

    @Override
    public void setProperties() {

    }

    @Override
    public void onClick(View v) {
        if (v == ibStartKro) {
            Toast.makeText(this, R.string.ibStartKro, Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, KronometerActivity.class));

        } else if (v == ibStartGameLike) {
            Toast.makeText(this, R.string.ibStartGameLike, Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, GameLikeActivity.class));

        } else if (v == ibStartLandmarkCatalog) {
            Toast.makeText(this, R.string.ibStartLandmarkCatalog, Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, LandmarkCatalogActivity.class));


        } else if (v == ibGoMain)
            Globals.getInstance().showShortToastText(this, R.string.you_are_already_in_main);

        else if (v == ibStartDataBase) {
            Globals.getInstance().showShortToastText(this, R.string.ibStartDataBase);

            startActivity(new Intent(this, DataBaseActivity.class));

        } else if (v == ibStartFavBook) {
            Globals.getInstance().showShortToastText(this, R.string.ibStartFavBook);

            startActivity(new Intent(this, FavBookActivity.class));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == ibStartKro || v == ibStartGameLike || v == ibStartLandmarkCatalog || v == ibStartDataBase || v == ibStartFavBook) {
            Globals.getInstance().showOldDownloadAlertDialogWithButton(this);
            return true;
        }
        return false;
    }
}
