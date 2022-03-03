package com.yt.splashexample3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        final ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!rippleBackground.isRippleAnimationRunning()){
                    imageView.setColorFilter(Color.argb(255, 255, 255, 255)); //change the logo color while staring animation
                    rippleBackground.startRippleAnimation(); //starting the animation
                }else {
                    imageView.setColorFilter(null); //get back to previous logo color while stopping animation
                    rippleBackground.stopRippleAnimation(); //stopping the animation
                }
            }
        });
    }
}
