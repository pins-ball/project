package com.pinball.playgame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.pinball.playgame.mole;

public class MainActivity extends Activity {
    SharedPreferences pref;
    int spoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        pre_call();
        //setContentView(R.layout.activity_main);
        setContentView(new mole(this, spoint, pref));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void pre_call(){
        pref = getSharedPreferences("num",Activity.MODE_PRIVATE);
        spoint= pref.getInt("maxjumsu", 0);
        Toast.makeText(getApplicationContext(), "" +
                "최고점수"+spoint, Toast.LENGTH_SHORT).show();

    }
}

