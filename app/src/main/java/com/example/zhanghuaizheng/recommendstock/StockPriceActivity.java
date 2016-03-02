package com.example.zhanghuaizheng.recommendstock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StockPriceActivity extends AppCompatActivity {
    StockDB db;
    public final String StockID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new StockDB(this);
        String StockID = getIntent().getExtras().getString("Stock");
        setContentView(R.layout.activity_stock_price);

        System.out.println(StockID);
        saveStock();
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);


        webView.setWebChromeClient(new WebChromeClient());
        String Url = MessageFormat.format("http://stocks.sina.cn/sh/?code=sz{0}&vt=4", StockID);

        webView.loadUrl(Url);
    }



    public void addStock(String stockID, String stockName, String buyPrice,
                         String buyDay, String sellPrice, String recommendPeople,
                         String nowPrice, String increase){
        db.open();
        long id = db.insertStock(stockID, stockName, buyPrice, buyDay, sellPrice, recommendPeople, nowPrice, increase);
        if (id>0){
            Toast.makeText(this,"添加记录成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,StockID,Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void saveStock(){

            String time = new SimpleDateFormat("HH:mm MMM DDD yyy").format(Calendar.getInstance().getTime());
            addStock("000917", "川大智胜", "36.00", time, "40.00", "白老师", "36.62", "0.36%");

    }

}
