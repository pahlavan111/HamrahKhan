package com.bp.hamrahkhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imgLocation;
    EditText edtNumber;
    Button btnGetCode;
    String num="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpView();
        startMyAnimations();




    }

    private String getNumber() {




        return edtNumber.getText().toString();
    }

    private void setUpView() {
        edtNumber = findViewById(R.id.editTextNumberPassword);
        btnGetCode = findViewById(R.id.button);
        imgLocation = findViewById(R.id.img_location);

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num =getNumber();

            }
        });
    }

    private void startMyAnimations() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.enter_bottom);
        edtNumber.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.enter_bottom_slow);
        btnGetCode.startAnimation(animation);
        animation= AnimationUtils.loadAnimation(this, R.anim.blink);
        imgLocation.startAnimation(animation);
    }

}