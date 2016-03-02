package com.example.zhanghuaizheng.recommendstock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zhanghuaizheng on 15/12/30.
 */
public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(JPushInterface.ACTION_MESSAGE_RECEIVED)){
            Bundle bundle = intent.getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            Toast.makeText(context, "Message title:" + title + " content:" + message, Toast.LENGTH_LONG).show();
        }else if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)){
            System.out.println("用户收到了通知");
        }else if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){
            Bundle bundle = intent.getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_ALERT);

            Intent i = new Intent(context, StockPriceActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("Stock", message);
            context.startActivity(i);

        }
    }
}
