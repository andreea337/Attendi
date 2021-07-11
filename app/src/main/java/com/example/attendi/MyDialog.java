package com.example.attendi;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


public class MyDialog {

    private static Dialog ProgressDialog;

    public static void showCompleteDialog(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_secceed, null);

        ProgressDialog = new Dialog(context, R.style.MyDialogStyle);
        ProgressDialog.setCancelable(false);
        ProgressDialog.setCanceledOnTouchOutside(true);
        ProgressDialog.setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        Window window = ProgressDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width =1100;
        lp.height = 700;

        window.setGravity(Gravity.CENTER_HORIZONTAL);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.Widget_AppCompat_PopupWindow);
        ProgressDialog.show();
    }

    public static void showFailedDialog(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_failed, null);

        ProgressDialog = new Dialog(context, R.style.MyDialogStyle);
        ProgressDialog.setCancelable(false);
        ProgressDialog.setCanceledOnTouchOutside(true);
        ProgressDialog.setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        Window window = ProgressDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width =1100;
        lp.height = 700;

        window.setGravity(Gravity.CENTER_HORIZONTAL);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.Widget_AppCompat_PopupWindow);
        ProgressDialog.show();
    }
}
