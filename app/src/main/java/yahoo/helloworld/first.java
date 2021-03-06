package yahoo.helloworld;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 25.09.2017.
 */

public class first extends Activity {
    Button btnStart, btnStop, btnReset;
    TextView tvTime;
    Timer timer;
    // Get a handler that can be used to post to the main thread
    Handler mainHandler;
    private boolean isRunning = false;
    private int minutes, seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnReset = (Button) findViewById(R.id.btnReset);
        tvTime = (TextView) findViewById(R.id.tvTime);
        timer = new Timer();

        mainHandler = new Handler(getMainLooper());


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning)
                    onTimerStart();
                else
                    onTimerPause();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//вац

    }


    private void onTimerPause() {
        isRunning = false;
        btnStart.setText(getResources().getString(R.string.start));
    }


    private void onTimerStart() {
        isRunning = true;
        btnStart.setText(getResources().getString(R.string.pause));
        timer.schedule(new timertask(), 0, 1000);
    }

    private void tick() {


        Log.d("TIMER", "elapsed: " + minutes + "minutes " + seconds + " seconds");
//я понял, на javascript создавал таймер, такой же принцип обнуления секунд
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                tvTime.setText(minutes + ":" + seconds);
            } // This is your code
        };
        mainHandler.post(myRunnable);
        if (seconds < 59) {
            seconds++;
        } else {
            minutes++;
            seconds = 0;
        }
    }

    class timertask extends TimerTask {
        @Override
        public void run() {
            if (isRunning) tick();
        }
    }
}
