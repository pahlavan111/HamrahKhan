package com.bp.hamrahkhan.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.bp.hamrahkhan.R;
import com.bp.hamrahkhan.model.path.GetPathResponse;
import com.bp.hamrahkhan.model.path.SingleBody;
import com.bp.hamrahkhan.retrofit.ApiClient;
import com.bp.hamrahkhan.retrofit.ApiService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityPathList extends AppCompatActivity {

    Toolbar toolbar;
    Long mobile;
    String token;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_list);
        toolbar= findViewById(R.id.toolbar);

        mobile= Objects.requireNonNull(getIntent().getExtras()).getLong("mobile");
        token = getIntent().getExtras().getString("token");

        Toast.makeText(this, mobile+" " +token, Toast.LENGTH_SHORT).show();

        Log.d("beh",mobile.toString());
        Log.d("beh",token);

        RecyclerView rv_path=findViewById(R.id.rv_path_list);
        getPathList();
    }

    private void getPathList() {
        Map<String,String> headers = new HashMap<>();
        headers.put("apiCode",MainActivity.API_KEY);
        headers.put("Authorization",token);

//        String str="{\n" +
//                "\"mobile\": 9112129219\n" +
//                "}";

        ApiService service = ApiClient.getClient().create(ApiService.class);
        service.getPathList(headers,new SingleBody(mobile)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetPathResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetPathResponse getPathResponse) {
                        Log.d("Beh",getPathResponse.getCode()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ActivityPathList.this, e.toString()+"", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}