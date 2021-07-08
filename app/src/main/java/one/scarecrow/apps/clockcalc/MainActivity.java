package one.scarecrow.apps.clockcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button fromButton;
    Button toButton;

    TextView results;

    int fromHour, fromMinute;
    int toHour, toMinute;
    int initHour, initMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  Night mode looks nice right now.... maybe going to add more themes
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        //Change bottom nav bar to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        fromButton = findViewById(R.id.timeButton1);
        toButton = findViewById(R.id.timeButton2);
        results = findViewById(R.id.textViewResult);
        results.setText("");

        Calendar c = Calendar.getInstance();
        fromHour = c.get(Calendar.HOUR_OF_DAY);
        fromMinute = c.get(Calendar.MINUTE);
        toHour = c.get(Calendar.HOUR_OF_DAY);
        toMinute = c.get(Calendar.MINUTE);
        initHour = c.get(Calendar.HOUR_OF_DAY);
        initMinute = c.get(Calendar.MINUTE);

    }

    public void setResults(){
        // If they do not equal each other then calculate
        int minResult = toMinute - fromMinute;
        int hourResult = toHour - fromHour;
        if(minResult == 0){
            results.setText(hourResult + " Hours ");
        }else if(hourResult == 0) {
            results.setText( minResult + " Minutes");
        }else {
            results.setText(hourResult + " Hours " + minResult + " Minutes");
        }


    }

    // First button (From)
    public void popTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                fromHour = selectedHour;
                fromMinute = selectedMinute;

                boolean isPM = (fromHour >= 12);
                fromButton.setText(String.format("%02d:%02d %s", (fromHour == 12 || fromHour == 0) ? 12 : fromHour % 12, fromMinute, isPM ? "PM" : "AM"));
                setResults();
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, fromHour, fromMinute, false);

        timePickerDialog.show();


    }


    // Second button (To)
    public void popTime2(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                toHour = selectedHour;
                toMinute = selectedMinute;

                boolean isPM = (toHour >= 12);
                toButton.setText(String.format("%02d:%02d %s", (toHour == 12 || toHour == 0) ? 12 : toHour % 12, toMinute, isPM ? "PM" : "AM"));
                setResults();
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, toHour, toMinute, false);
        timePickerDialog.show();


    }
}