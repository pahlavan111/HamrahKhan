package com.bp.hamrahkhan.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.bp.hamrahkhan.model.verify.CodeSendResponse;
import com.bp.hamrahkhan.retrofit.ApiClient;
import com.bp.hamrahkhan.retrofit.ApiService;
import com.bp.hamrahkhan.model.verify.CodeSendBody;
import com.bp.hamrahkhan.model.sms.MobileSendBody;
import com.bp.hamrahkhan.model.sms.MobileSendResponse;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    TextView txtDesc, txtEditNum, txtTimer;
    ImageView imgLocation;
    EditText edtNumber, edtCode;
    Button btnGetCode, btnSendCode;
    LinearLayout linearSendNum, linearGetCode;
    RelativeLayout rlTimerContainer;
    public static final String API_KEY = "0b49ad807b3c63860558cef5d1a6b46bc49afcf7";
    long mobile;
    ProgressBar progressBar;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
        turnToSendNumMode();

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = edtNumber.getText().toString();
                if (str.equals("0")) {
                    edtNumber.setText("");
                    Toast.makeText(MainActivity.this, "شماره همراه را بدون صفر وارد کنید", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnGetCode.setOnClickListener(v -> {

            if (checkMobileNumber()) {
                progressBar.setVisibility(View.VISIBLE);
                sendSms();

            }
        });

        txtEditNum.setOnClickListener(v -> {
            timer.cancel();
            edtCode.setText("");
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
        service.login(API_KEY, new MobileSendBody(mobile, API_KEY)).enqueue(new Callback<MobileSendResponse>() {
            @Override
            public void onResponse(@NonNull Call<MobileSendResponse> call, @NonNull Response<MobileSendResponse> response) {

                if (response.code()==200){
                    MobileSendResponse mobileSendResponse = response.body();
                    if (linearSendNum.getVisibility() != View.GONE) {
                        turnToGetCodeMode();
                        timer.start();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

//                    assert mobileSendResponse != null;
//                    Log.d("beh", String.valueOf(mobileSendResponse.getData().getMobileValidation()));



                }else if (response.code()== 400) {

                    Toast.makeText(MainActivity.this, "داری اشتباه میزنی!!!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, response.code()+ "  یه جای کار میلنگه!!  ", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<MobileSendResponse> call, Throwable t) {

                Log.d("beh", t.toString());

            }
        });

    }

    private void sendCode() {
        String code = edtCode.getText().toString();

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<CodeSendResponse> call = service.verify(API_KEY, new CodeSendBody(mobile, code, 0, ""));
        call.enqueue(new Callback<CodeSendResponse>() {
            @Override
            public void onResponse(@NonNull Call<CodeSendResponse> call, @NonNull Response<CodeSendResponse> response) {

                if (response.code() == 200) {

                    CodeSendResponse codeSendResponse = response.body();
                    timer.cancel();


                    Intent intent = new Intent(MainActivity.this, ActivityPathList.class);
                    intent.putExtra("mobile", mobile);
                    intent.putExtra("token", codeSendResponse.getData().getToken());
                    startActivity(intent);
                    finish();

                } else if (response.code() == 400) {

                    Toast.makeText(MainActivity.this, " داری اشتباه میزنی!!! ", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(MainActivity.this, response.code() + " یه جای کار میلنگه ", Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<CodeSendResponse> call, Throwable t) {

                Log.d("beh_fail", t.toString());

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

        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }

    }
}