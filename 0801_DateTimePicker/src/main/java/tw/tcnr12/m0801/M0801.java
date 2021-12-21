package tw.tcnr12.m0801;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class M0801 extends AppCompatActivity implements View.OnClickListener {

    private DatePicker mDate;
    private TimePicker mTime;
    private Button b001;
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.ListActivityActionBar); //讓ListActivity顯示action bar ,可自訂無限多個 日間模式和夜間模式是這樣用
        setContentView(R.layout.m0801);
   setupViewComponent();

    }




    private void setupViewComponent() {
        mDate = (DatePicker) findViewById(R.id.m0801_date01);
        mTime = (TimePicker) findViewById(R.id.m0801_time01);
        b001 = (Button) findViewById(R.id.m0801_b001);
        mTxtResult = (TextView) findViewById(R.id.m0801_t001);

        b001.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String s = getString(R.string.m0801_t001);

//        mTxtResult.setText(s + "\n" +
//                mDate.getYear() + getString(R.string.m0801_t002) +
//                (mDate.getMonth() + 1) + getString(R.string.m0801_t003) +
//                mDate.getDayOfMonth() + getString(R.string.m0801_t004) +
//
//                mTime.getHour() + getString(R.string.m0801_t005) +
//                mTime.getMinute() + getString(R.string.m0801_t006)

        String ss = Convert24to12(mTime.getHour() + ":" + mTime.getMinute() + ":00");
        mTxtResult.setText(s + "\n" +
                mDate.getYear() + getString(R.string.m0801_t002) +
                (mDate.getMonth() + 1) + getString(R.string.m0801_t003) +
                mDate.getDayOfMonth() + getString(R.string.m0801_t004) + "\n" + ss
        );
    }

    private String Convert24to12(String time) {
        String convertTime = "";
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat displayFormat = new SimpleDateFormat("hh:mm a");

            Date date = parseFormat.parse(time);
            convertTime = displayFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertTime;
    }


}