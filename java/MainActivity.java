package com.example.aishupriya.youthforseva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,Act_login.class);
                startActivity(it);
            }
        });
    }
}
