package tw.tcnr12.m0802a;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class M0802a extends AppCompatActivity implements View.OnClickListener {

    private TextView time,ans01,text001;
    private Button b001;
    private TimePicker time01;
    private DatePicker date01;
    private MediaPlayer startmusic;
    private int years01,months01,dates01,hours01,minius01;
    private Calendar cg;
    private long endTime,spentTime,hours,minius,seconds;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.ListActivityActionBar); //讓ListActivity顯示action bar ,可自訂無限多個 日間模式和夜間模式是這樣用
        setContentView(R.layout.m0802a);
   setupViewComponent();

    }




    private void setupViewComponent() {
        date01 = (DatePicker) findViewById(R.id.m0802a_date01);
        time01 = (TimePicker) findViewById(R.id.m0802a_time01);
        b001 = (Button) findViewById(R.id.m0802a_b001);
        ans01 = (TextView) findViewById(R.id.m0802a_ans01);
        time = (TextView)findViewById(R.id.m0802a_timer);
        text001 = (TextView)findViewById(R.id.m0802a_t001);

        startmusic = MediaPlayer.create(M0802a.this,R.raw.s01);

        b001.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String s = getString(R.string.m0802a_ans01);
        years01 = date01.getYear();//取得畫面的"年"
        months01 = date01.getMonth();//取得畫面的"月"
        dates01 = date01.getDayOfMonth();//取得畫面的"日"
        hours01 = time01.getHour();// 取得畫面的"小時"
        minius01 = time01.getMinute();// 取得畫面的"分鐘"

        // 顯示選擇的日期和時間
        ans01.setText(s +
                years01 + getString(R.string.n_yy) +
                (months01 + 1) + getString(R.string.n_mm)     +
                dates01 + getString(R.string.m_dd) +
                hours01 + getString(R.string.d_hh) +
                minius01      + getString(R.string.d_mm));
        //--------------------------------------
        cg = Calendar.getInstance();//設定日曆新物件
        cg.set(years01, months01, dates01, hours01, minius01);//將日期及時間設定進去物件
        endTime = cg.getTimeInMillis();//取得時間毫秒資料
        //設定開始Delay的時間
        handler.postDelayed(updateTimer, 100); //停多久開始做這個動作
    }


    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            spentTime = endTime - System.currentTimeMillis(); //剩餘時間
            hours = (spentTime/1000)/60/60; //小時
            minius = (spentTime/1000)/60%60; //分鐘
            seconds = (spentTime/1000)%60;

            if(spentTime < 0 || hours > 99){
                Toast.makeText(getApplicationContext(), getString(R.string.m0802a_err), Toast.LENGTH_SHORT).show();
                time.setText(getString(R.string.m0802a_timer));
                text001.setText(getString(R.string.m0802a_choose));
            }else {
                text001.setText(getString(R.string.m0802a_alerm));
                music_set();

                time.setText(String.format("%02d",hours)+ " : " +String.format("%02d",minius) + " : " + String.format("%02d",seconds));
//time.setText(minius + ":" + seconds);
                handler.postDelayed(this, 1000);//真正延遲的時間 若要5秒更新一次 則改成5000

                if(hours == 0 && minius == 0 && seconds == 0){
                    startmusic.start();
                    text001.setText(getString(R.string.m0802a_t001));
                    handler.removeCallbacks(updateTimer);
                }
            }
        }
    };

    private void music_set() {
        if(startmusic != null && startmusic.isPlaying()){
            startmusic.stop();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(updateTimer);
        this.finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Toast.makeText(getApplicationContext(), "禁用返回鍵", Toast.LENGTH_SHORT).show();
    }
}