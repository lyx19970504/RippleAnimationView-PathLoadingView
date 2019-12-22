package com.example.listenerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.listenerdemo.util.ViewCalculateUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RippleAnimationView rippleAnimationView = findViewById(R.id.layout_RippleAnimation);
        ImageView imageView = findViewById(R.id.ImageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rippleAnimationView.isRunning()){
                    Log.d(TAG, "onClick: "+"stop");
                    rippleAnimationView.stopAnimation();
                }else{
                    Log.d(TAG, "onClick: "+"start");
                    rippleAnimationView.startAnimation();
                }
            }
        });
    }
}
