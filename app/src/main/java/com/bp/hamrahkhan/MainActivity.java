package com.bp.hamrahkhan;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bp.hamrahkhan.data.ApiClient;
import com.bp.hamrahkhan.data.ApiService;
import com.bp.hamrahkhan.data.MobileSend;
import com.bp.hamrahkhan.data.MobileSendResponse;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ImageView imgLocation;
    EditText edtNumber,edtCode;
    Button btnGetCode,btnSendCode;
    LinearLayout linearSendNum,linearGetCode;
    private static final String API_KEY = "0b49ad807b3c63860558cef5d1a6b46bc49afcf7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
        startMyAnimations();
        btnGetCode.setOnClickListener(v -> {

            sendSms();

        });



    }

    private void sendSms() {

        Long num=Long.parseLong(edtNumber.getText().toString());
        ApiService service=ApiClient.getClient().create(ApiService.class);
        service.login(new MobileSend(num,API_KEY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MobileSendResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(MobileSendResponse mobileSendResponse) {

                Toast.makeText(MainActivity.this, mobileSendResponse.getMessage(), Toast.LENGTH_SHORT).show();

                turnToGetCodeMode();
//                if (mobileSendResponse.getData().isMobileValidation()){
//
//                    Toast.makeText(MainActivity.this, "fffffffffff", Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onError(Throwable e) {


            }
        });
    }


    private void setUpView() {

        imgLocation = findViewById(R.id.img_location);
        edtNumber = findViewById(R.id.editTextNumberPassword);
        edtCode = findViewById(R.id.editGetCode);
        btnGetCode = findViewById(R.id.button);
        btnSendCode = findViewById(R.id.btn_send_code);
        linearSendNum = findViewById(R.id.ll_send_sms);
        linearGetCode = findViewById(R.id.ll_get_sms);


    }

    private void startMyAnimations() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.enter_bottom);
        edtNumber.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.enter_bottom_slow);
        btnGetCode.startAnimation(animation);
        animation= AnimationUtils.loadAnimation(this, R.anim.blink);
        imgLocation.startAnimation(animation);
    }


    void turnToGetCodeMode(){

        linearSendNum.setVisibility(View.GONE);
        linearGetCode.setVisibility(View.VISIBLE);

    }

}