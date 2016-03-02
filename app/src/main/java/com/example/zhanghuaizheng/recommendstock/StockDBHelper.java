package com.example.zhanghuaizheng.recommendstock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhanghuaizheng on 16/1/13.
 */
public class StockDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "StockRecommendation";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_STOCKID = "stock_id";
    public static final String COLUMN_NAME_STOCKNAME = "stock_name";
    public static final String COLUMN_NAME_BUYPRICE = "buy_price";
    public static final String COLUMN_NAME_BUYDAY = "buy_day";
    public static final String COLUMN_NAME_SELLPRICE = "sell_price";
    public static final String COLUMN_NAME_RECOMMENDPEOPLE = "recommend_people";
    public static final String COLUMN_NAME_NOWPRICE = "now_price";
    public static final String COLUMN_NAME_INCREASE = "increase";

    private static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME_STOCKID + " TEXT NOT NULL,"
                    + COLUMN_NAME_STOCKNAME + " TEXT NOT NULL,"
                    + COLUMN_NAME_BUYPRICE + " TEXT NOT NULL,"
                    + COLUMN_NAME_BUYDAY + " TEXT NOT NULL,"
                    + COLUMN_NAME_SELLPRICE + " TEXT NOT NULL,"
                    + COLUMN_NAME_RECOMMENDPEOPLE+ " TEXT NOT NULL,"
                    + COLUMN_NAME_NOWPRICE + " TEXT NOT NULL,"
                    + COLUMN_NAME_INCREASE + " TEXT NOT NULL);";

    private static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StockDB";

    public StockDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }

}
