package com.bp.hamrahkhan.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.bp.hamrahkhan.R;
import com.bp.hamrahkhan.model.path.GetPathResponse;
import com.bp.hamrahkhan.model.path.Path;
import com.bp.hamrahkhan.model.path.SingleBody;
import com.bp.hamrahkhan.retrofit.ApiClient;
import com.bp.hamrahkhan.retrofit.ApiService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPathList extends AppCompatActivity {

    Toolbar toolbar;
    Long mobile;
    String token;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_list);
        toolbar = findViewById(R.id.toolbar);
        progressBar= findViewById(R.id.spin_kit_2);
        progressBar.setVisibility(View.VISIBLE);

        mobile = Objects.requireNonNull(getIntent().getExtras()).getLong("mobile");
        token = getIntent().getExtras().getString("token");

       // Toast.makeText(this, mobile + " " + token, Toast.LENGTH_SHORT).show();

        Log.d("beh", mobile.toString());
        Log.d("beh", token);

        recyclerView = findViewById(R.id.rv_path_list);
        getPathList();
    }

    private void getPathList() {
        Map<String, String> headers = new HashMap<>();
        headers.put("apiCode", MainActivity.API_KEY);
        headers.put("Authorization", token);

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<GetPathResponse> call = service.getPathList(headers, new SingleBody(mobile));
        call.enqueue(new Callback<GetPathResponse>() {
            @Override
            public void onResponse(Call<GetPathResponse> call, Response<GetPathResponse> response) {

                GetPathResponse getPathResponse = response.body();
                progressBar.setVisibility(View.GONE);
                assert getPathResponse != null;
                PathAdapter pathAdapter= new PathAdapter(getApplicationContext(),getPathResponse.getData().getPaths());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(pathAdapter);


            }

            @Override
            public void onFailure(Call<GetPathResponse> call, Throwable t) {

            }
        });
    }
}



