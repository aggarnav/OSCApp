package com.dhua.osctutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.Activity;
import com.dhua.osctutorial.Osc;

public class MainActivity extends Activity {

    Osc osc;
    final String ip = "192.168.1.4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void showEarth(View view){
        osc.oscRequest(ip, "earth");

    }
    public void showMars(View view){
        osc.oscRequest(ip, "mars");

    }
    public void showMoon(View view){
        osc.oscRequest(ip, "moon");

    }
    public void showSky(View view){
        osc.oscRequest(ip, "sky");

    }

    public void delhi(View view) {
        final String latlong   ="New Delhi,77.1025,28.7041";
        osc.oscRequest(ip, latlong);
    }

    public void newYork(View view) {
        final String latlong   ="New York City,-74.006028,40.7128";
        osc.oscRequest(ip, latlong);
    }

    public void sanJose(View view) {
        final String latlong   ="San Jose,-121.886328,37.3382";
        osc.oscRequest(ip, latlong);
    }

    public void shanghai(View view) {
        final String latlong   ="Shanghai,121.4737,31.2304";
        osc.oscRequest(ip, latlong);
    }

    public void london(View view) {
        final String latlong   ="London,0.127,51.5074";
        osc.oscRequest(ip, latlong);
    }
}
