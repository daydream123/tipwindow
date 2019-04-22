package com.feizhang.tipwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TipWindow tipWindow = new TipWindow(this);
        findViewById(R.id.topLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipWindow.show(findViewById(R.id.topLeft), "hello my world");
            }
        });

        findViewById(R.id.topRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipWindow.show(findViewById(R.id.topRight), "hello my world");
            }
        });

        findViewById(R.id.bottomLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipWindow.show(findViewById(R.id.bottomLeft), "hello my world");
            }
        });

        findViewById(R.id.bottomRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipWindow.show(findViewById(R.id.bottomRight), "hello my world");
            }
        });

        findViewById(R.id.center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipWindow.show(findViewById(R.id.center), "hello my world");
            }
        });
    }
}
