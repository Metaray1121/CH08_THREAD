package tw.tcnr12.m0802;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class M0802<onStop> extends AppCompatActivity {

    private TextView time;
    private long starTime;
    private Handler handler = new Handler();
    private long spendtime;
    private long minius;
    private long seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802);
        setupViewComponent();
    }

    private void setupViewComponent() {

        time = (TextView)findViewById(R.id.m0802_timer);
        //取得目前時間
        starTime = System.currentTimeMillis();
        //設定Delay的時間
        handler.postDelayed(updateTimer, 100); // 1 = 1千毫秒  , 100 = 0.1秒 , updateTimer不要 alt + enter

        //time.setText((starTime/1000)/60 +"");
    }



    private Runnable updateTimer = new Runnable() {     //這行自己打的  , Runnable=執行你所需要的內容
        @Override
        public void run() {
            spendtime = System.currentTimeMillis() - starTime; //經過的時間
            //計算目前已過分鐘數
            minius = (spendtime/1000)/60;
            //計算目前已過秒數
            seconds = (spendtime/1000)%60;

            time.setText(String.format("%02d",minius) + ":" + String.format("%02d",seconds));
//time.setText(minius + ":" + seconds);
            handler.postDelayed(this, 1000);
        }
    };
//    @Override
//    protected void onStop() {
//        super.onStop();
//        handler.removeCallbacks(updateTimer);
//        this.finish();
//    }



    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(getApplication(),"禁用返回鍵",Toast.LENGTH_SHORT).show();
    }

}
