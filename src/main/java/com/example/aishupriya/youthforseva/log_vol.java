package com.example.aishupriya.youthforseva;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class log_vol extends Activity {
TextView tv;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_vol);
        tv=(TextView)findViewById(R.id.textView3);
        bt=(Button)findViewById(R.id.button1);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(log_vol.this,dash_volunteer.class);
                startActivity(it);
            }
        });

        tv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(log_vol.this,dash_volunteer.class);
                startActivity(it);
            }
        });
    }

}
