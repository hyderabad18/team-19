package com.example.youthforseva;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EventPostActivity extends Activity {
EditText et1,et2,et3,et4,et5,et6;
Button bt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventpost);
		et1=(EditText)findViewById(R.id.eventdes1);
		et2=(EditText)findViewById(R.id.location);
		et3=(EditText)findViewById(R.id.sdate);
		et4=(EditText)findViewById(R.id.edate);
		et5=(EditText)findViewById(R.id.students);
		et6=(EditText)findViewById(R.id.status);
		bt1=(Button)findViewById(R.id.button1);
		 bt1.setOnClickListener(new View.OnClickListener(){
			 public void onClick(View view){
				 
				 
			 }
		 });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_eventpost, menu);
		return true;
	}

}
