package com.example.joseluis.holamundo;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonChi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Cui cui we", Toast.LENGTH_SHORT)
                        .show();

                final View lasChelasKrnal = findViewById(R.id.img_chelas);
                lasChelasKrnal.animate()
                        .alpha(1)
                        .setDuration(300)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {}

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                lasChelasKrnal.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {}

                            @Override
                            public void onAnimationRepeat(Animator animation) {}
                        });
            }
        });
    }
}
