package com.associate.sbmfa.Utils;

import android.app.ProgressDialog;
import android.content.Context;

public class GoogleProgressDialog {
    Context context;
    ProgressDialog dialog;
    public GoogleProgressDialog(Context context) {
        this.context = context;
    }
    public void show1(String title) {
        try {
            if(dialog!=null&&dialog.isShowing()){

            }else {
                dialog = new ProgressDialog(context);
                dialog.setMessage(title);
                dialog.show();
                dialog.setCancelable(false);
            }
        } catch (Exception e) {
            dismiss();
        }
    }
    public void show() {
        try {
            if(dialog!=null&&dialog.isShowing()){

            }else {
                dialog = new ProgressDialog(context);
                dialog.setMessage("Loading");
                dialog.show();
                dialog.setCancelable(false);
            }
        } catch (Exception e) {
            dismiss();
        }
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


}
