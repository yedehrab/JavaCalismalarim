package com.yunusemre.derscalismao;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FavBookMenuAddNoteActivity extends AppCompatActivity implements Base, View.OnClickListener {

    private TextView tvText;
    private EditText etText;
    private ImageView ivImage;
    private Button btnSave;
    private ImageButton ibMain;

    public static SQLiteDatabase database;
    private Bitmap bmImage;
    private byte[] byteImage;
    private boolean imageChanged;

    public static final String NAME_DATABASE = "FavBook";
    public static final String NAME_TABLE = "favBook";
    private final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    private final int PICKING_IMAGE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_book_menu_add_note);

        initViews();
        bindEvents();
        defineObjects();
        setProperties();
    }

    @Override
    public void initViews() {
        tvText = findViewById(R.id.tvAddNoteText);
        etText = findViewById(R.id.etAddNoteName);
        ivImage = findViewById(R.id.ivAddNoteImage);
        btnSave = findViewById(R.id.btnAddNoteSave);
        ibMain = findViewById(R.id.ibGoMain);
    }

    @Override
    public void bindEvents() {
        ivImage.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        ibMain.setOnClickListener(this);
    }

    @Override
    public void defineObjects() {
        try {
            database = this.openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS " + NAME_TABLE + " (name VARCHAR, image BLOB)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setProperties() {
        if (!getIntent().getBooleanExtra("new", true)) {
            etText.setText(getIntent().getStringExtra("name"));
            ivImage.setImageBitmap(FavBookActivity.arrImages.get(getIntent().getIntExtra("position", 0)));
            tvText.setVisibility(View.INVISIBLE);
            btnSave.setVisibility(View.INVISIBLE);

        } else {
            tvText.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);
            imageChanged = false;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ivImage) {
            if (
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    ) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);

            } else {
                startActivityForResult(
                        new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                        PICKING_IMAGE
                );
            }
        } else if (v == btnSave) {
            if (etText.getText().toString().equalsIgnoreCase("")) {
                Globals.getInstance().showAlertDialog(this, "Missing info", "The name of the image is necessary");

            } else if (!imageChanged) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure?")
                        .setMessage("You forget to select image. Did you wanna save without it?")
                        .setPositiveButton("Im sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                saveDataToDatabaseGoBack();
                            }
                        })
                        .setNegativeButton("No, Im not", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Globals.getInstance().showShortToastText(FavBookMenuAddNoteActivity.this, "Tap to image to select image");
                            }
                        })
                        .show();

            } else {
                saveDataToDatabaseGoBack();
            }

        } else if (v == ibMain) {
            Globals.getInstance().defaultIbGoMainOnClick(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(
                        new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                        PICKING_IMAGE
                );
            } else {
                Globals.getInstance().showShortToastText(this, "I need your permission :/");
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICKING_IMAGE && resultCode == RESULT_OK && data != null) {
            try {
                bmImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                ivImage.setImageBitmap(bmImage);
                btnSave.setVisibility(View.VISIBLE);
                imageChanged = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Saving data to the database which name is favBook
     */
    public void saveDataToDatabaseGoBack() {
        try {
            // We compress bitmap to byteArray
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if (bmImage != null)
                bmImage.compress(Bitmap.CompressFormat.PNG, 1, outputStream);
            else
                ((BitmapDrawable)ivImage.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 1, outputStream);
            byteImage = outputStream.toByteArray();
            SQLiteStatement statement;
            // To set java values we need SQLiteStatement (index 1 = name | index 2 = image)
            if (getIntent().getBooleanExtra("new", true)) {
                statement = database.compileStatement("INSERT INTO favBook (name, image) VALUES(?, ?)");
                statement.bindString(1, etText.getText().toString());
                statement.bindBlob(2, byteImage);
            } else {
                statement = database.compileStatement("UPDATE favBook SET image = ? WHERE name ='" + etText.getText().toString() + "'");
                statement.bindBlob(1, byteImage);
            }
            statement.execute();

            Globals.getInstance().showShortToastText(this, "Data saved.");

            startActivity(
                    new Intent(FavBookMenuAddNoteActivity.this, FavBookActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
