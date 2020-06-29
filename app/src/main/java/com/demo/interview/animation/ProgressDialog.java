package com.demo.interview.animation;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.demo.interview.R;


public class ProgressDialog {

    private LinearLayout layout_item;
    private TextView mTipsTxt;
    private String tipStr = "";
    AlertDialog dialog;
    int backresid = 0;
    private boolean showImg = true;

    private LottieAnimationView lo_ainm1;
    private Context context;

    public ProgressDialog(Context context) {
        this.context = context;
    }


    private AlertDialog getmDialog() {
        dialog = null;
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            dialog = new AlertDialog.Builder(context, R.style
                    .progress_dialog)
                    .create();
        } else {
            dialog = new AlertDialog.Builder(context).create();
        }

        dialog.show();
        dialog.dismiss();
        Window window = dialog.getWindow();
        window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        View view = null;
        if (TextUtils.isEmpty(tipStr)) {
            view = LayoutInflater.from(context).inflate(R.layout
                    .progress_dialog, null);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout
                    .progress_dialog2, null);
        }
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        mTipsTxt = (TextView) view.findViewById(R.id.tips);
        mTipsTxt.setVisibility(TextUtils.isEmpty(tipStr) ? View.GONE : View.VISIBLE);
        layout_item = view.findViewById(R.id.layout_item);
        layout_item.setBackgroundResource(backresid);
        lo_ainm1 = view.findViewById(R.id.lottie_loading);
        return dialog;
    }

    public void setTips(boolean showImg, String tips, int backresid) {
        this.tipStr = tips;
        this.showImg = showImg;
        this.backresid = backresid;
    }


    public boolean isShow() {
        if (dialog != null)
            return dialog.isShowing();
        return false;
    }

    public void show() {
        if (null == context) {
            return;
        }
        if (dialog == null) {
            dialog = getmDialog();
            mTipsTxt.setText(tipStr);
        }
        if (!dialog.isShowing()) {
//            DebugLog.e("ylli101", "3");
            dialog.show();
        }
    }

    public void setTips(String text) {
        this.tipStr = text;
    }

    public void dismiss() {
        setTips("");
        if (dialog == null) return;
//        if (animationDrawable.isRunning()) {
//            animationDrawable.stop();
//        }
        if (lo_ainm1.isAnimating()) {
            lo_ainm1.pauseAnimation();
            lo_ainm1.cancelAnimation();
        }
        if (dialog.isShowing()) {
//            DebugLog.e("ylli101", "4");
            dialog.dismiss();
        }
    }
}
