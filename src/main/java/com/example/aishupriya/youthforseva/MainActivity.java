package com.example.aishupriya.youthforseva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
ImageButton m1,m2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        m1=(ImageButton)findViewById(R.id.imageButton4);
        m2=(ImageButton)findViewById(R.id.imageButton5);

        m1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,Act_login.class);
                startActivity(it);
            }
        });

        m2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,Act_login.class);
                startActivity(it);
            }
        });

    }
}
