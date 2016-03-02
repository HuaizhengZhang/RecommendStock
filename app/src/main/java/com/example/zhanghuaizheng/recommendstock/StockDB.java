package com.example.zhanghuaizheng.recommendstock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zhanghuaizheng on 16/1/13.
 */
public class StockDB {
    final Context context;
    StockDBHelper DBHelper;
    SQLiteDatabase db;

    public StockDB(Context ctx){
        this.context = ctx;
        DBHelper = new StockDBHelper(this.context);
    }



    public StockDB open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        DBHelper.close();
    }

    public long insertStock(String stockID, String stockName, String buyPrice,
                            String buyDay, String sellPrice, String recommendPeople,
                            String nowPrice, String increase){

        ContentValues initialValues = new ContentValues();
        initialValues.put(StockDBHelper.COLUMN_NAME_STOCKID, stockID);
        initialValues.put(StockDBHelper.COLUMN_NAME_STOCKNAME, stockName);
        initialValues.put(StockDBHelper.COLUMN_NAME_BUYPRICE, buyPrice);
        initialValues.put(StockDBHelper.COLUMN_NAME_BUYDAY, buyDay);
        initialValues.put(StockDBHelper.COLUMN_NAME_SELLPRICE, sellPrice);
        initialValues.put(StockDBHelper.COLUMN_NAME_RECOMMENDPEOPLE, recommendPeople);
        initialValues.put(StockDBHelper.COLUMN_NAME_NOWPRICE, nowPrice);
        initialValues.put(StockDBHelper.COLUMN_NAME_INCREASE, increase);
        return db.insert(StockDBHelper.TABLE_NAME, null, initialValues);
    }

    public Cursor getAllStock(){
        return db.query(StockDBHelper.TABLE_NAME,
                new String[]{
                        StockDBHelper.COLUMN_NAME_ID,
                        StockDBHelper.COLUMN_NAME_STOCKID,
                        StockDBHelper.COLUMN_NAME_STOCKNAME,
                        StockDBHelper.COLUMN_NAME_BUYPRICE,
                        StockDBHelper.COLUMN_NAME_BUYDAY,
                        StockDBHelper.COLUMN_NAME_SELLPRICE,
                        StockDBHelper.COLUMN_NAME_RECOMMENDPEOPLE,
                        StockDBHelper.COLUMN_NAME_NOWPRICE,
                        StockDBHelper.COLUMN_NAME_INCREASE
        },
                null, null, null, null, null);
    }

    public Cursor getContact(long rowId) throws SQLException{
        Cursor mCursor = db.query(true, StockDBHelper.TABLE_NAME,
                new String[]{
                        StockDBHelper.COLUMN_NAME_ID,
                        StockDBHelper.COLUMN_NAME_STOCKID,
                        StockDBHelper.COLUMN_NAME_STOCKNAME,
                        StockDBHelper.COLUMN_NAME_BUYPRICE,
                        StockDBHelper.COLUMN_NAME_BUYDAY,
                        StockDBHelper.COLUMN_NAME_SELLPRICE,
                        StockDBHelper.COLUMN_NAME_RECOMMENDPEOPLE,
                        StockDBHelper.COLUMN_NAME_NOWPRICE,
                        StockDBHelper.COLUMN_NAME_INCREASE},
                StockDBHelper.COLUMN_NAME_ID + "=" + rowId, null,null,null,null,null);

        if (mCursor != null){
            mCursor.moveToFirst();
        }

        return mCursor;

    }

    public boolean deleteContact(long rowId){
        return db.delete(StockDBHelper.TABLE_NAME,
                StockDBHelper.COLUMN_NAME_ID + "=" + rowId,null) > 0;
    }

    public boolean updateContact(long rowId, String stockID, String stockName, String buyPrice,
                                 String buyDay, String sellPrice, String recommendPeople,
                                 String nowPrice, String increase){
        ContentValues initialValues = new ContentValues();
        initialValues.put(StockDBHelper.COLUMN_NAME_STOCKID, stockID);
        initialValues.put(StockDBHelper.COLUMN_NAME_STOCKNAME, stockName);
        initialValues.put(StockDBHelper.COLUMN_NAME_BUYPRICE, buyPrice);
        initialValues.put(StockDBHelper.COLUMN_NAME_BUYDAY, buyDay);
        initialValues.put(StockDBHelper.COLUMN_NAME_SELLPRICE, sellPrice);
        initialValues.put(StockDBHelper.COLUMN_NAME_RECOMMENDPEOPLE, recommendPeople);
        initialValues.put(StockDBHelper.COLUMN_NAME_NOWPRICE, nowPrice);
        initialValues.put(StockDBHelper.COLUMN_NAME_INCREASE, increase);
        return db.update(StockDBHelper.TABLE_NAME, initialValues, StockDBHelper.COLUMN_NAME_ID + "=" + rowId, null) > 0;
    }

}