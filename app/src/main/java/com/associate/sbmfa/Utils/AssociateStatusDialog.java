package com.associate.sbmfa.Utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.associate.sbmfa.Activity.login.PinActivity;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Session.SessionManager;

public class AssociateStatusDialog  {
    Context context;
    AlertDialog.Builder builder;
    SessionManager sessionManager;
    public AssociateStatusDialog(Context context) {
        this.context = context;
        sessionManager = new SessionManager(context);
    }
    public void checkStatus() {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setCancelable(false);
        // builder.setIcon(R.drawable.logo_new2);
        builder.setMessage("You don't have permission to access this app. Please contact admin")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sessionManager.checkStatus();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
