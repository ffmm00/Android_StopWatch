package com.fm_example.stopwatch;

import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView timer_m, timer_s, timer_ms;
    Button startbt, stopbt, resetbt;
    int min, sec, m_sec;
    long startDate;
    private Loop loop = new Loop();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer_m = (TextView) findViewById(R.id.textView1);
        timer_s = (TextView) findViewById(R.id.textView3);
        timer_ms = (TextView) findViewById(R.id.textView5);
        startbt = (Button) findViewById(R.id.b_start);
        stopbt = (Button) findViewById(R.id.b_stop);
        resetbt = (Button) findViewById(R.id.b_reset);

        startbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate = System.currentTimeMillis();
                loop.start();
            }
        });

        stopbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loop.stop();
            }
        });

        resetbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loop.reset();
            }
        });

    }


    public void times() {
        min = (int) (((System.currentTimeMillis() - startDate)) / 1000) / 60;
        sec = (int) (((System.currentTimeMillis() - startDate)) / 1000) % 60;
        m_sec = (int) (((System.currentTimeMillis() - startDate)) / 10) % 10;
        timer_m.setText(String.format("%1$02d", min));
        timer_s.setText(String.format("%1$02d", sec));
        timer_ms.setText(String.format("%1$01d", m_sec));
    }

    class Loop extends Handler {
        private boolean isUpdate;

        public void start() {
            this.isUpdate = true;
            handleMessage(new Message());
        }

        public void stop() {
            this.isUpdate = false;
        }

        public void reset() {
            this.isUpdate = false;
        }

        public void handleMessage(Message msg) {
            this.removeMessages(0);
            if (this.isUpdate) {
                MainActivity.this.times();
                sendMessageDelayed(obtainMessage(0), 25);

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
