package com.bp.hamrahkhan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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

import com.bp.hamrahkhan.R;
import com.bp.hamrahkhan.data.verify.CodeSendResponse;
import com.bp.hamrahkhan.retrofit.ApiClient;
import com.bp.hamrahkhan.retrofit.ApiService;
import com.bp.hamrahkhan.data.verify.CodeSend;
import com.bp.hamrahkhan.data.sms.MobileSend;
import com.bp.hamrahkhan.data.sms.MobileSendResponse;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    TextView txtDesc, txtEditNum, txtTimer;
    ImageView imgLocation;
    EditText edtNumber, edtCode;
    Button btnGetCode, btnSendCode;
    LinearLayout linearSendNum, linearGetCode;
    RelativeLayout rlTimerContainer;
    private static final String API_KEY = "0b49ad807b3c63860558cef5d1a6b46bc49afcf7";
    long mobile;
    ProgressBar progressBar;
    CountDownTimer timer;


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

        txtEditNum.setOnClickListener(v -> {
            timer.cancel();
            progressBar.setVisibility(View.GONE);
            turnToSendNumMode();
        });

        btnSendCode.setOnClickListener(v -> {
            if (checkCodeNumber()) {
                progressBar.setVisibility(View.VISIBLE);
                sendCode();

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
            edtCode.setError("کد به صورت صحیح وارد نشده است!");
            return false;
        }
    }

    private void sendSms() {

        mobile = Long.parseLong(edtNumber.getText().toString());
        ApiService service = ApiClient.getClient().create(ApiService.class);
        service.login(API_KEY,new MobileSend(mobile, API_KEY) ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MobileSendResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(MobileSendResponse mobileSendResponse) {

                 Log.d("beh",mobileSendResponse.getData().getMobileValidation()+"");
                switch (mobileSendResponse.getCode()) {
                    case 200:
                        if (linearSendNum.getVisibility() != View.GONE) {
                            turnToGetCodeMode();
                            timer.start();
                        }

                        progressBar.setVisibility(View.INVISIBLE);
                        break;

                    case 400:
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "شماره تلفن اشتباه است", Toast.LENGTH_SHORT).show();
                        break;
                    case 503:
                    case 403:
                    case 402:
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this,mobileSendResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        progressBar.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onError(Throwable e) {


            }
        });
    }

    private void sendCode() {
        String code = edtCode.getText().toString();
        ApiService service = ApiClient.getClient().create(ApiService.class);
        service.verify(API_KEY,new CodeSend(mobile,code,0,"")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CodeSendResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CodeSendResponse codeSendResponse) {
                        progressBar.setVisibility(View.INVISIBLE);
                        timer.cancel();
                        Toast.makeText(MainActivity.this, codeSendResponse.getCode()+"", Toast.LENGTH_SHORT).show();

                        if (codeSendResponse.getCode()==200){
                            Intent intent=new Intent(MainActivity.this,ActivityPathList.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void setUpView() {

        txtDesc = findViewById(R.id.txt_desc);
        txtTimer = findViewById(R.id.txt_timer);
        imgLocation = findViewById(R.id.img_location);
        edtNumber = findViewById(R.id.editTextNumberPassword);
        edtCode = findViewById(R.id.editGetCode);
        btnGetCode = findViewById(R.id.btn_send_num);
        btnSendCode = findViewById(R.id.btn_send_code);
        linearSendNum = findViewById(R.id.ll_send_sms);
        linearGetCode = findViewById(R.id.ll_get_sms);
        rlTimerContainer = findViewById(R.id.rl_timer_container);
        txtEditNum = findViewById(R.id.txt_edit_num);

        progressBar = findViewById(R.id.spin_kit);
        // Sprite doubleBounce = new DoubleBounce();
        // progressBar.setIndeterminateDrawable(doubleBounce);

        txtTimer.setOnClickListener(v -> {
            if (txtTimer.getText().toString().equals("ارسال دوباره کد!!")) {
                sendSms();
                startTimer();
            }
        });

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
        startTimer();

    }

    void startTimer() {
        timer = new CountDownTimer(60000, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                txtTimer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                txtTimer.setText("ارسال دوباره کد!!");
            }
        }.start();
    }

    @Override
    public void onBackPressed() {

        if (progressBar.getVisibility()==View.VISIBLE){
            progressBar.setVisibility(View.GONE);
        }else{
            super.onBackPressed();
        }

    }
}