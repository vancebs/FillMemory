package com.hf.fillmemory;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Fan on 2015/11/27.
 */
public class MyService extends Service {
    public static final String ACTION_FILL = "com.hf.fillmemory.ACTION_FILL";
    public static final String ACTION_QUERY = "com.hf.fillmemory.ACTION_QUERY";
    public static final String ACTION_QUERY_RESULT = "com.hf.fillmemory.ACTION_QUERY_RESULT";

    public static final String EXTRA_FILL_SIZE = "extra_fill_size";

    private static final int NOTIFICATION_ID = 10086;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (ACTION_FILL.equals(action)) {
            onFill(intent);
        } else if (ACTION_QUERY.equals(action)) {
            onQuery();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void onFill(Intent intent) {
        int fillSize = intent.getIntExtra(EXTRA_FILL_SIZE, 0);

        // fill
        if (fillSize > 0) {
            fill(fillSize);
            android.util.Log.i("==MyTest==", "fillSize > 0");

            // start foreground
            Notification.Builder nb = new Notification.Builder(this);
            nb.setContentTitle("Filled Size: " + fillSize + "M");
            nb.setContentInfo("Filled Size: " + fillSize + "M");
            nb.setColor(0xFFBBBBFF);
            nb.setCategory(Notification.CATEGORY_SERVICE);
            Notification notification = nb.build();
            startForeground(NOTIFICATION_ID, notification);
        } else {
            fill(0);
            stopForeground(true);
        }

        // notify change
        onQuery();
    }

    private void onQuery() {
        Intent intent = new Intent(ACTION_QUERY_RESULT);
        intent.putExtra(EXTRA_FILL_SIZE, getFilledSize());
        sendBroadcast(intent);
    }

    private native void fill(int size);
    private native int getFilledSize();

    static {
        System.loadLibrary("JniNativeFill");
    }

}
