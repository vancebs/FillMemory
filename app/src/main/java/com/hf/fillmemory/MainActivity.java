package com.hf.fillmemory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mCurSize;
    private EditText mNewSize;
    private Button mQuery;
    private Button mFill;

    private BroadcastReceiver mQueryResultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int size = intent.getIntExtra(MyService.EXTRA_FILL_SIZE, 0);
            mCurSize.setText(size + "M");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCurSize = (TextView) findViewById(R.id.cur_size);
        mNewSize = (EditText) findViewById(R.id.new_size);
        mQuery = (Button) findViewById(R.id.query);
        mFill = (Button) findViewById(R.id.fill);

        mQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuery();
            }
        });

        mFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFill();
            }
        });

        // query result result
        IntentFilter filter = new IntentFilter(MyService.ACTION_QUERY_RESULT);
        registerReceiver(mQueryResultReceiver, filter);
    }

    private void onQuery() {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.ACTION_QUERY);
        startService(intent);
    }

    private void onFill() {
        int size = 0;
        try {
            size = Integer.valueOf(mNewSize.getText().toString());
        } catch (NumberFormatException e) {
            return;
        }

        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.ACTION_FILL);
        intent.putExtra(MyService.EXTRA_FILL_SIZE, size);
        startService(intent);
    }
}
