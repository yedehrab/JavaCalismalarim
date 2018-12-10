package com.yunusemre.derscalismao;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class KronometerActivity extends AppCompatActivity implements Base, View.OnClickListener {

    private ImageButton ibGoMain;
    private TextView tvCount;
    private Button btnStart;
    private Button btnStop;

    private Handler mHandler;
    private Runnable mRunnable;


    private int time;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kronometer);

        initViews();
        defineObjects();
        bindEvents();
    }

    /**
     * Görüntüleri tanımlama
     */
    public void initViews(){
        ibGoMain = findViewById(R.id.ibGoMain);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        tvCount = findViewById(R.id.tvCount);
    }

    /**
     * Olayları oluşturma
     */
    public void bindEvents(){
        ibGoMain.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    /**
     * Objeleri tanımlama
     */
    public void defineObjects(){
        mHandler = new Handler();

        time = getResources().getInteger(R.integer.kro_tvRemainingTime);
        running = false;

        mRunnable = new Runnable() {
            @Override
            public void run() {
                String text = getString(R.string.kro_tvRemainingTime) + " " + ++time;

                tvCount.setText(text);

                mHandler.postDelayed(this, 1000);
            }
        };
    }

    @Override
    public void setProperties() {

    }

    /**
     * Tıklama metodları
     * @param v Buton görünümü
     */
    @Override
    public void onClick(View v) {
        if(v == btnStart){
            if(!running){
                Toast.makeText(this, R.string.btn_start, Toast.LENGTH_SHORT).show();

                mHandler.post(mRunnable);

                running = true;
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder .setTitle(R.string.alert_title_error)
                        .setMessage(R.string.kro_btnStart_alert_message)
                        .show();
            }
        }
        else if(v == btnStop){
            if(running) {
                Toast.makeText(this, R.string.btn_stop, Toast.LENGTH_SHORT).show();

                mHandler.removeCallbacks(mRunnable);

                running = false;
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder .setTitle(R.string.alert_title_error)
                        .setMessage(R.string.kro_btnStop_alert_message)
                        .show();
            }
        }
        else if(v == ibGoMain)
            Globals.getInstance().defaultIbGoMainOnClick(this);

    }
}
