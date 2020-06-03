package com.demo.interview.hybrid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：WebViewTestActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/6/3 21:50
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class WebViewTestActivity extends BaseActivity {

    private WebView webView;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        //设置原生与js可以互相调用
        settings.setJavaScriptEnabled(true);
        //允许js弹框
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


        //拦截WebView弹框
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WebViewTestActivity.this);

                builder.setTitle("alert");
                builder.setMessage(message);
                builder.setPositiveButton(android.R.string.ok, (dialog, which)
                        -> result.confirm());
                builder.setCancelable(false);
                builder.create().show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });
    }

    @Override
    public void initData() {

    }

    private void loadUrl(String url) {
        webView.loadUrl(url);
    }

    private void callJs(String method) {
        if (Build.VERSION.SDK_INT < 18) {
            webView.loadUrl("javascript:" + method);
        } else {
            webView.evaluateJavascript("javascript:" + method, value ->
                    LogUtils.d("onReceiveValue:" + value));
        }
    }


    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
