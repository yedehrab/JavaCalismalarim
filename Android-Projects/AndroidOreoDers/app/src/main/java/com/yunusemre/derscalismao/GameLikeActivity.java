package com.yunusemre.derscalismao;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReferenceArray;

import static com.yunusemre.derscalismao.R.mipmap.breakinglike256;

public class GameLikeActivity extends AppCompatActivity implements Base, View.OnClickListener {

    private TextView tvRemainingTime;
    private TextView tvGameScore;
    private ImageButton ibGoMain;
    private ImageView[][] ivGameLike;

    private CountDownTimer countDownTimer;
    private Handler ivHandler;
    private Runnable ivRunnable;

    private HashSet<int[]> breakingHeartsKoordinates;
    private final int MS = 1000;
    private final int SPEED = 250;
    private int gameScore;
    private long timeMs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_like);

        initViews();
        bindEvents();
        defineObjects();
        setProperties();
    }

    @Override
    public void initViews() {
        tvRemainingTime = findViewById(R.id.tvRemainingTime);
        ibGoMain = findViewById(R.id.ibGoMain);
        tvGameScore = findViewById(R.id.tvGameScore);

        setIvGameLike();
    }

    @Override
    public void bindEvents() {
        ibGoMain.setOnClickListener(this);
    }

    @Override
    public void defineObjects() {
        timeMs = this.getResources().getInteger(R.integer.game_like_tvRemainingTime) * MS;
        breakingHeartsKoordinates = new HashSet<>();
        gameScore = 0;
        countDownTimer = getCountDownTimer();
    }

    @Override
    public void setProperties() {
        showRandomImageView();

        countDownTimer.start();
    }

    public void setIvGameLike() {
        ivGameLike = new ImageView[][]{
                {findViewById(R.id.ivGameLike00), findViewById(R.id.ivGameLike01), findViewById(R.id.ivGameLike02)},
                {findViewById(R.id.ivGameLike10), findViewById(R.id.ivGameLike11), findViewById(R.id.ivGameLike12)},
                {findViewById(R.id.ivGameLike20), findViewById(R.id.ivGameLike21), findViewById(R.id.ivGameLike22)}
        };

    }

    public void hideImageViews() {
        for (ImageView[] images : ivGameLike) {
            for (ImageView image : images) {
                image.setVisibility(View.INVISIBLE);
                image.setOnClickListener(this);
            }
        }
    }

    public void showRandomImageView() {
        hideImageViews();
        ivHandler = new Handler();
        ivRunnable = new Runnable() {
            @Override
            public void run() {
                hideImageViews();

                ivGameLike[new Random().nextInt(3)][new Random().nextInt(3)].setVisibility(View.VISIBLE);

                ivHandler.postDelayed(this, SPEED);
            }
        };

        ivHandler.post(ivRunnable);

    }

    public void showBreakingIvGameLike() {

    }


    public CountDownTimer getCountDownTimer() {
        return new CountDownTimer(timeMs, MS) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeMs = millisUntilFinished;

                String text = getString(R.string.game_like_tvRemainingTime) + " " + (timeMs / 1000 + 1);

                tvRemainingTime.setText(text);
            }

            @Override
            public void onFinish() {
                timeMs = 0;

                String text = getString(R.string.game_like_tvRemainingTime) + " " + timeMs;

                tvRemainingTime.setText(text);

                ivHandler.removeCallbacks(ivRunnable);
                hideImageViews();

                AlertDialog.Builder builder = new AlertDialog.Builder(GameLikeActivity.this);
                builder.setTitle(R.string.game_like_tvRemainingTime_alert_title)
                        .setMessage(R.string.game_like_tvRemainingTime_alert_message)
                        .show();


            }
        };
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        if (v == ibGoMain) {
            countDownTimer.cancel();
            ivHandler.removeCallbacks(ivRunnable);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.kro_alert_title)
                    .setMessage(R.string.kro_alert_message)
                    .setPositiveButton(R.string.kro_alert_positive_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), R.string.kro_alert_toast_positive, Toast.LENGTH_SHORT).show();

                            startActivity(
                                    new Intent(GameLikeActivity.this, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                        .putExtra("new", false)
                            );
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.kro_alert_negative_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), R.string.kro_alert_toast_negative, Toast.LENGTH_SHORT).show();
                            if (timeMs != 0) {
                                countDownTimer = getCountDownTimer();
                                countDownTimer.start();

                                ivHandler.post(ivRunnable);
                            }
                        }
                    })
                    .show();
        } else if (isIvGameLike(v)) {
            String text = getString(R.string.game_like_tvGameScore) + " " + ++gameScore;
            tvGameScore.setText(text);

            findIvGameLikeByView(v).setImageResource(R.mipmap.breakinglike256);
            breakingHeartsKoordinates.add(findKoordinateByView(v));

        }
    }

    public boolean isIvGameLike(View v) {
        for (ImageView[] images : ivGameLike) {
            for (ImageView image : images) {
                if (image == v) {
                    return true;
                }
            }
        }
        return false;
    }

    public ImageView findIvGameLikeByView(View v) {
        for (ImageView[] images : ivGameLike) {
            for (ImageView image : images) {
                if (image == v) {
                    return image;
                }
            }
        }
        return null;
    }

    public int[] findKoordinateByView(View v) {
        int i = 0;
        int j = 0;

        for (ImageView[] images : ivGameLike) {
            for (ImageView image : images) {
                if (image == v) {
                    return new int[]{i, j};
                }
                j++;
            }
            i++;
        }
        return new int[]{-1, -1};
    }
}
