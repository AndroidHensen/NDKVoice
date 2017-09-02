package com.handsome.ndkvoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import org.fmod.FMOD;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ExecutorService fixedThreadPool;
    private PlayerThread playerThread;
    private String path = "file:///android_asset/hensen.mp3";
    private int type;

    private LinearLayout normal, luoli, dashu, jingsong, gaoguai, kongling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normal = (LinearLayout) findViewById(R.id.normal);
        luoli = (LinearLayout) findViewById(R.id.luoli);
        dashu = (LinearLayout) findViewById(R.id.dashu);
        jingsong = (LinearLayout) findViewById(R.id.jingsong);
        gaoguai = (LinearLayout) findViewById(R.id.gaoguai);
        kongling = (LinearLayout) findViewById(R.id.kongling);
        normal.setOnClickListener(this);
        luoli.setOnClickListener(this);
        dashu.setOnClickListener(this);
        jingsong.setOnClickListener(this);
        gaoguai.setOnClickListener(this);
        kongling.setOnClickListener(this);

        fixedThreadPool = Executors.newFixedThreadPool(1);
        FMOD.init(this);
    }

    class PlayerThread implements Runnable {
        @Override
        public void run() {
            Utils.fix(path, type);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal:
                type = Utils.MODE_NORMAL;
                break;
            case R.id.luoli:
                type = Utils.MODE_LUOLI;
                break;
            case R.id.dashu:
                type = Utils.MODE_DASHU;
                break;
            case R.id.jingsong:
                type = Utils.MODE_JINGSONG;
                break;
            case R.id.gaoguai:
                type = Utils.MODE_GAOGUAI;
                break;
            case R.id.kongling:
                type = Utils.MODE_KONGLING;
                break;
        }

        playerThread = new PlayerThread();
        fixedThreadPool.execute(playerThread);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FMOD.close();
    }
}
