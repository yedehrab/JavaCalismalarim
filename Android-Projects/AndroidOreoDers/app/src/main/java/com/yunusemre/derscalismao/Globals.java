package com.yunusemre.derscalismao;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObservable;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Yunus Emre on 15.11.2017.
 */

public class Globals {

    private static Globals instance;

    private Bitmap landmarkSelectedImage;
    private String inputTextAlertDialog;
    // private static SQLiteDatabase database;
    private boolean isTextSaved;

    private Globals() {
        isTextSaved = false;
    }

    static Globals getInstance() {
        if (instance == null)
            instance = new Globals();
        return instance;
    }

    void setLandmarkSelectedImage(Bitmap landmarkSelectedImage) {
        this.landmarkSelectedImage = landmarkSelectedImage;
    }

    boolean getIsTextSaved() {
        return isTextSaved;
    }

    String getInputTextAlertDialog() {
        return inputTextAlertDialog;
    }

    Bitmap getLandmarkSelectedImage() {
        return this.landmarkSelectedImage;
    }

    /*
    static SQLiteDatabase getDatabase(){
        return database;
    }*/

    /**
     * Goes main with button alert and without data and finishing activity
     *
     * @param context The context of activity
     */
    void defaultIbGoMainOnClick(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.kro_alert_title)
                .setMessage(R.string.kro_alert_message)
                .setPositiveButton(R.string.kro_alert_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, R.string.kro_alert_toast_positive, Toast.LENGTH_SHORT).show();

                        context.startActivity(
                                new Intent(context, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                        .putExtra("new", false)
                        );

                        if (context instanceof Activity)
                            ((Activity) context).finish();

                    }
                })
                .setNegativeButton(R.string.kro_alert_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, R.string.kro_alert_toast_negative, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    void ibGoMainWithoutErasingOnClick(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.alert_title_withoutErasing)
                .setMessage(R.string.alert_message_withoutErasing)
                .setPositiveButton(R.string.kro_alert_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, R.string.alert_toast_positive_withoutErasing, Toast.LENGTH_SHORT).show();

                        context.startActivity(
                                new Intent(context, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                        .putExtra("new", false)
                        );

                        if (context instanceof Activity)
                            ((Activity) context).finish();

                    }
                })
                .setNegativeButton(R.string.kro_alert_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, R.string.kro_alert_toast_negative, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    void showTextAlertDialog(final Context context, final String title, final String positiveToastText, final String negativeToastText) {
        isTextSaved = false;

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setView(input)
                .setPositiveButton(R.string.kro_alert_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, positiveToastText, Toast.LENGTH_SHORT).show();

                        inputTextAlertDialog = input.getText().toString();

                        isTextSaved = true;
                    }
                })
                .setNegativeButton(R.string.kro_alert_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, negativeToastText, Toast.LENGTH_SHORT).show();

                        dialog.cancel();
                    }
                })
                .show();

    }

    /**
     * To give download link with alert diaolog.
     * If positive, show toast "This feature will be added"
     *
     * @param context App context
     */
    void showOldDownloadAlertDialogWithButton(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Hey !")
                .setMessage("Are you want to download its source files?")
                .setPositiveButton("Yes :)", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showShortToastText(context, "This feature will be added");
                    }
                })
                .setNegativeButton("No !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showShortToastText(context, "However u want -_-");
                    }
                })
                .show();
    }

    void showAlertDialog(final Context context, final String title, final String message, final String positiveToastText, final String negativeToastText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.kro_alert_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showShortToastText(context, positiveToastText);
                    }
                })
                .setNegativeButton(R.string.kro_alert_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showShortToastText(context, negativeToastText);

                        dialog.cancel();
                    }
                })
                .show();
    }

    void goMainActivityWithFinishing(Context context) {
        ((Activity) context).startActivity(new Intent(context, MainActivity.class));
        ((Activity) context).finish();
    }

    void eventIbGoMainWithoutErasing(Context context, String text) {
        showShortToastText(context, text);
        goMainActivityWithFinishing(context);
    }

    /**
     * Showing alert dialog with text (without butons)
     *
     * @param context The context of alert dialog
     * @param title   The title of alert dialog
     * @param message The message of Alert dialog
     *                *
     */
    void showAlertDialog(final Context context, final String title, final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .show();
    }

    /**
     * To show text on the bottom of screen
     *
     * @param context Activity context
     * @param resId   The id of text from R
     */
    void showShortToastText(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * To show text on the bottom of screen
     *
     * @param context Activity context
     * @param text    The text what you want
     */
    void showShortToastText(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
