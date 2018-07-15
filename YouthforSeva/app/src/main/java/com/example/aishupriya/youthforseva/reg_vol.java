package com.example.aishupriya.youthforseva;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class reg_vol extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_vol);
Button bt;
        bt=(Button)findViewById(R.id.button1);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(reg_vol.this,EventDisplay.class);
                startActivity(it);
            }
        });
    }

}
