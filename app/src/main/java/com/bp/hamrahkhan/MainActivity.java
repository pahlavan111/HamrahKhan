package com.bp.hamrahkhan;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bp.hamrahkhan.data.ApiClient;
import com.bp.hamrahkhan.data.ApiService;
import com.bp.hamrahkhan.data.CodeSend;
import com.bp.hamrahkhan.data.CodeSendResponse;
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
    long mobile;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
        turnToSendNumMode();

        btnGetCode.setOnClickListener(v -> {

            if (checkMobileNumber()) {
                progressBar.setVisibility(View.VISIBLE);
                sendSms();

            }
        });

        txtEditNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnToSendNumMode();
            }
        });

        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCodeNumber()) {
                    progressBar.setVisibility(View.VISIBLE);
                    sendCode();

                }
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

    private boolean checkCodeNumber() {

        String num = edtCode.getText().toString();
        if (num.length() == 4) {
            return true;
        } else {
            edtNumber.setError("کد به صورت صحیح وارد نشده است!");
            return false;
        }
    }

    private void sendSms() {

        mobile= Long.parseLong(edtNumber.getText().toString());
        ApiService service = ApiClient.getClient().create(ApiService.class);

        service.login(API_KEY,new MobileSend(mobile, API_KEY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MobileSendResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(MobileSendResponse mobileSendResponse) {

               // Log.d("beh",mobileSendResponse.getData().isMobileValidation()+"");
                switch (mobileSendResponse.getCode()) {
                    case 200:
                        turnToGetCodeMode();
                        progressBar.setVisibility(View.INVISIBLE);
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

    private void sendCode() {
        String code =edtCode.getText().toString();
        Long referrer = Long.valueOf(0);
        ApiService service = ApiClient.getClient().create(ApiService.class);
        service.verify(API_KEY,new CodeSend(mobile,code,referrer,"")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CodeSendResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CodeSendResponse codeSendResponse) {
                        Toast.makeText(MainActivity.this, codeSendResponse.getCode()+"", Toast.LENGTH_SHORT).show();

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

        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
       // Sprite doubleBounce = new DoubleBounce();
       // progressBar.setIndeterminateDrawable(doubleBounce);
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