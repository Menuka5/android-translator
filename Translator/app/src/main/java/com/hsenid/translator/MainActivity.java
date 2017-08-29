package com.hsenid.translator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button yandexTranslateBtn;
    Button googleTranslateBtn;

    public void init(){
        yandexTranslateBtn = (Button) findViewById(R.id.yandexBtn);
        googleTranslateBtn = (Button) findViewById(R.id.googleBtn);

        yandexTranslateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yandexActivity = new Intent(MainActivity.this, YandexTranslate.class);
                startActivity(yandexActivity);
            }
        });

        googleTranslateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleActivity = new Intent(MainActivity.this, GoogleTranslate.class);
                startActivity(googleActivity);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
