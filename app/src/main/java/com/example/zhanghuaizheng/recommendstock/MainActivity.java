package com.example.zhanghuaizheng.recommendstock;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    StockDB db;
    private static final int CAMERA_SELECT = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new StockDB(this);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("股票推荐1.0");
//        mToolbar.setLogo();
        mToolbar.inflateMenu(R.menu.menu_main);
//        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.main_logo);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.stock_account:
                        Toast.makeText(MainActivity.this, "个人信息", Toast.LENGTH_SHORT).show();
                        goToAccount();
                        break;

                    case R.id.search:
                        Toast.makeText(MainActivity.this, "搜素", Toast.LENGTH_SHORT).show();
                        goToSearch();
                        break;

                    default:
                        break;
                }
                return true;
            }
        });

        ArrayList<String[]> notes = getStocks();

        for (int i = 0; i < notes.size(); i++) {
            String[] note = notes.get(i);
            insertRow(note[1], note[2],note[3], note[4],note[5], note[6],note[7], note[8]);
        }
    }

    Intent myIntent;

    public void goToAccount() {
        myIntent = new Intent(this, StockPriceActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    public void goToSearch() {
        myIntent = new Intent(this, StockPriceActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    public void goToPic(){
        myIntent = new Intent(Intent.ACTION_GET_CONTENT);
        myIntent.setType("image/*");
        startActivityForResult(myIntent, CAMERA_SELECT);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }


    public ArrayList<String[]> getStocks() {

        db.open();
        Cursor c = db.getAllStock();
        ArrayList<String[]> stocks = new ArrayList<String[]>();
        if (c.moveToFirst()) {
            do {
                String[] note = {c.getString(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4), c.getString(5),
                        c.getString(6), c.getString(7), c.getString(8)};
                stocks.add(note);
            } while (c.moveToNext());
        }
        db.close();
        return stocks;
    }


    public void insertRow(String stockID, String stockName, String buyPrice,
                          String buyDay, String sellPrice, String recommendPeople,
                          String nowPrice, String increase) {
        String temp = new String();
        ViewGroup grid = (ViewGroup) findViewById(R.id.stock_show);
        LinearLayout newLine = new LinearLayout(grid.getContext());
        newLine.setLayoutParams(new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        newLine.setOrientation(LinearLayout.VERTICAL);
        newLine.setBackgroundResource(R.drawable.stock_each_view);
        newLine.setClickable(true);
        newLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPic();
            }
        });


        TextView timeText = new TextView(newLine.getContext());
        TextView noteText = new TextView(newLine.getContext());
        TextView priceText = new TextView(newLine.getContext());
        TextView peopleText = new TextView(newLine.getContext());


        timeText.setText(String.valueOf(stockID) + "  " + String.valueOf(buyDay));
        timeText.setTextSize(20f);
        timeText.setPadding(20, 20, 20, 0);

//        if (String.valueOf(stockName).length() > 24) {
//            temp = String.valueOf(stockName).substring(0, 22) + "...";
//
//        } else if (String.valueOf(stockName).indexOf("\n") > 0 && String.valueOf(stockName).indexOf("\n") < 24) {
//            temp = String.valueOf(stockName).substring(0, String.valueOf(stockName).indexOf("\n")) + "...";
//        } else
        temp = stockName;
        noteText.setText(temp);
        noteText.setTextSize(30f);
        noteText.setTextColor(Color.parseColor("#000000"));
        noteText.setPadding(20, 0, 20, 0);

        priceText.setText("买入价: " + String.valueOf(buyPrice) +
                "  现在价格: " + String.valueOf(nowPrice) +
                "  卖出价格: " + String.valueOf(sellPrice));
        priceText.setTextSize(20f);
        priceText.setPadding(20, 0, 20, 0);

        peopleText.setText("涨幅: " + String.valueOf(increase) + "  推荐人: " + String.valueOf(recommendPeople));
        peopleText.setTextSize(20f);
        peopleText.setPadding(20, 0, 20, 20);

        newLine.addView(timeText);
        newLine.addView(noteText);
        newLine.addView(priceText);
        newLine.addView(peopleText);

        grid.addView(newLine);
    }

    @Override
    public void onRestart() {
        super.onRestart();

        ArrayList<String[]> tempnotes = getStocks();
        String[] note = tempnotes.get(tempnotes.size() - 1);
        insertRow(note[1], note[2],note[3], note[4],note[5], note[6],note[7], note[8]);

    }
}