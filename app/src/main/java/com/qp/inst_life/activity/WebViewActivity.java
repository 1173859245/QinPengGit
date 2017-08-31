package com.qp.inst_life.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.qp.inst_life.R;

public class WebViewActivity extends AppCompatActivity {
  private WebView webView;
  private ImageView imageView;
  private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progressBar=(ProgressBar)findViewById(R.id.pdbar);
         webView=(WebView)findViewById(R.id.webview);
        imageView=(ImageView)findViewById(R.id.back);
        initBackListener();
        Intent intent=getIntent();
        intent.getSerializableExtra("url");
        webView.loadUrl(intent.getStringExtra("url"));
        webViewSetting();


    }



  private void   initBackListener(){
         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 WebViewActivity.this.finish();
             }
         });
    }

//    private  void webViewSetting(){
//
//    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void  webViewSetting(){
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    progressBar.setVisibility(View.GONE);

                } else {
                    // 加载中
                    progressBar.setVisibility(View.VISIBLE);
                }

            }
        });
    }


}
