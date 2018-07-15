package com.example.fragment;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

Button FirstFragment, SecondFragment,ThirdFragment;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
// get the reference of Button's
FirstFragment = (Button)findViewById(R.id.firstFragment);
SecondFragment = (Button)findViewById(R.id.secondFragment);
ThirdFragment = (Button)findViewById(R.id.thirdFragment);


// perform setOnClickListener event on First Button
FirstFragment.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
// load First Fragment
loadFragment(new Firstfragment());
}
});
// perform setOnClickListener event on Second Button
SecondFragment.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
// load Second Fragment
loadFragment(new SecondFragment());
}
});
ThirdFragment.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
// load Second Fragment
loadFragment(new ThirdActivity());
}
});
}

private void loadFragment(Fragment fragment) {
// create a FragmentManager
FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
fragmentTransaction.replace(R.id.frameLayout, thirdActivity);
fragmentTransaction.commit(); // save the changes
}
}