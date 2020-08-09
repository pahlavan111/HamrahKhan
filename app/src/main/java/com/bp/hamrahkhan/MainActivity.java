package com.bp.hamrahkhan;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

    boolean x = true;
    TextView txtDesc,txtEditNum;
    ImageView imgLocation;
    EditText edtNumber, edtCode;
    Button btnGetCode, btnSendCode;
    LinearLayout linearSendNum, linearGetCode;
    RelativeLayout rlTimerContainer;
    private static final String API_KEY = "0b49ad807b3c63860558cef5d1a6b46bc49afcf7";
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
        turnToSendNumMode();

        btnGetCode.setOnClickListener(v -> {

            if (checkMobileNumber()) {
                sendSms();


                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                final LayoutInflater inflater= LayoutInflater.from(MainActivity.this);
                View view= inflater.inflate(R.layout.dialog,null);
                builder.setView(view);
                dialog=builder.create();
                dialog.show();
            }
        });

        txtEditNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnToSendNumMode();
            }
        });


    }

    private boolean checkMobileNumber() {

        String num = edtNumber.getText().toString();
        if (num.length() == 10) {
            return true;
        } else {
            edtNumber.setError("شماره به صورت صحیح وارد نشده است!");
            return false;
        }
    }

    private void sendSms() {

        Long num = Long.parseLong(edtNumber.getText().toString());
        ApiService service = ApiClient.getClient().create(ApiService.class);
        service.login(new MobileSend(num, API_KEY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MobileSendResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(MobileSendResponse mobileSendResponse) {

                switch (mobileSendResponse.getCode()) {
                    case 200:
                        turnToGetCodeMode();
                        dialog.dismiss();
                        break;

                    case 400:
                    case 503:
                    case 403:
                    case 402:
                        Toast.makeText(MainActivity.this, mobileSendResponse.getCode() + " -> " + mobileSendResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onError(Throwable e) {


            }
        });
    }


    private void setUpView() {

        txtDesc = findViewById(R.id.txt_desc);
        imgLocation = findViewById(R.id.img_location);
        edtNumber = findViewById(R.id.editTextNumberPassword);
        edtCode = findViewById(R.id.editGetCode);
        btnGetCode = findViewById(R.id.btn_send_num);
        btnSendCode = findViewById(R.id.btn_send_code);
        linearSendNum = findViewById(R.id.ll_send_sms);
        linearGetCode = findViewById(R.id.ll_get_sms);
        rlTimerContainer = findViewById(R.id.rl_timer_container);
        txtEditNum = findViewById(R.id.txt_edit_num);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
        imgLocation.startAnimation(animation);

    }

    private void turnToSendNumMode() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        linearGetCode.startAnimation(animation);
        linearGetCode.setVisibility(View.GONE);

        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        txtDesc.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.enter_bottom);
        edtNumber.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.enter_bottom_slow);
        btnGetCode.startAnimation(animation);
        linearSendNum.setVisibility(View.VISIBLE);
    }


    void turnToGetCodeMode() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        linearSendNum.startAnimation(animation);
        linearSendNum.setVisibility(View.GONE);
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        rlTimerContainer.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.enter_bottom);
        edtCode.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.enter_bottom_slow);
        btnSendCode.startAnimation(animation);

        linearGetCode.setVisibility(View.VISIBLE);
    }


}