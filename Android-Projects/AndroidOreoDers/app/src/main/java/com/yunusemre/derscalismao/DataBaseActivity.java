package com.yunusemre.derscalismao;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class DataBaseActivity extends AppCompatActivity implements Base, View.OnClickListener {

    private ImageButton ibGoMain;
    private ListView lvNames;
    private Button btnAddData;
    private Button btnDeleteAll;

    private SQLiteDatabase myDataBase;
    private Cursor musiciansCursor;
    private ArrayList<String> arrayNames;
    private ArrayAdapter<String> adapterNames;

    private final String DATA_BASE_NAME = "Human";
    private final String TABLE_NAME = "human";
    private final String NAME = "name";
    private final String TITLE = "Type Data Name";
    private final String POSITIVE_TEXT = "Data is saving";
    private final String NEGATIVE_TEXT = "No data saved";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        initViews();
        bindEvents();
        defineObjects();
        setProperties();
    }

    @Override
    public void initViews() {
        ibGoMain = findViewById(R.id.ibGoMain);
        lvNames = findViewById(R.id.lvMusicians);
        btnAddData = findViewById(R.id.btnAddData);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
    }

    @Override
    public void bindEvents() {
        ibGoMain.setOnClickListener(this);
        btnAddData.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);
    }

    @Override
    public void defineObjects() {
        defineListView();
    }

    @Override
    public void setProperties() {
        createMyDataBase(DATA_BASE_NAME);
        createTableToMyDataBase(TABLE_NAME);
        readMyDataBase(TABLE_NAME);
        lvNames.setAdapter(adapterNames);
    }

    public void createMyDataBase(String dataBaseName) {
        try {
            myDataBase = this.openOrCreateDatabase(dataBaseName, MODE_PRIVATE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTableToMyDataBase(String tableName) {
        try {
            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (" + NAME + " VARCHAR)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMyDataBase(String tableName, String name) {
        try {
            myDataBase.execSQL("INSERT INTO " + tableName + " (" + NAME +") VALUES ('" + name + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMyDataBase(String tableName) {
        try {
            myDataBase.execSQL("DELETE FROM " + tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readMyDataBase(String tableName) {
        try {
            musiciansCursor = myDataBase.rawQuery("SELECT * FROM " + tableName, null);
            musiciansCursor.moveToFirst();

            while (musiciansCursor != null) {
                arrayNames.add(musiciansCursor.getString(musiciansCursor.getColumnIndex(NAME)));
                musiciansCursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshListView() {
        defineListView();
        readMyDataBase(TABLE_NAME);
        lvNames.setAdapter(adapterNames);
    }

    public void defineListView(){
        arrayNames = new ArrayList<>();
        adapterNames = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayNames);
    }

    @Override
    public void onClick(View v) {
        if (v == ibGoMain)
            Globals.getInstance().ibGoMainWithoutErasingOnClick(this);
        else if (v == btnAddData) {
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(TITLE)
                    .setView(input)
                    .setPositiveButton("Im sure", new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Globals.getInstance().showShortToastText(DataBaseActivity.this, POSITIVE_TEXT);

                            if (!Objects.equals(input.getText().toString(), "")) {
                                addMyDataBase(TABLE_NAME, input.getText().toString());
                                refreshListView();
                            }
                        }
                    })
                    .setNegativeButton("I give up", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Globals.getInstance().showShortToastText(DataBaseActivity.this, NEGATIVE_TEXT);

                            dialog.cancel();
                        }
                    }).show();
        } else if (v == btnDeleteAll) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you Crazy?")
                    .setMessage("All of data will be erased")
                    .setPositiveButton("Im crazy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Globals.getInstance().showShortToastText(DataBaseActivity.this, "All of data erased :o");
                            deleteMyDataBase(TABLE_NAME);
                            refreshListView();

                        }
                    })
                    .setNegativeButton("Im Just Kidding", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Globals.getInstance().showShortToastText(DataBaseActivity.this, "I hope so ;)");

                            dialog.cancel();
                        }
                    }).show();
        }
    }
}
