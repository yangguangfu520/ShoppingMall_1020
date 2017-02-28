package com.atguigu.addsubview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private AddSubView av;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        av = (AddSubView) findViewById(R.id.av);
        av.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                Toast.makeText(MainActivity.this, "value=="+value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
