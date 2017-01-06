package com.example.nguyendinh.exam_php;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    }
    public void click(View v)
    {
        if(v.getId() == R.id.btnListMain)
        {
           // Intent i = new Intent(getApplicationContext(), ListActivity.class);
            //startActivity(i);

            finish();
        }
        if(v.getId() == R.id.btnAddData)
        {
            Intent i = new Intent(getApplicationContext(), Add_Activity.class);
            startActivity(i);

            finish();
        }
    }
}
