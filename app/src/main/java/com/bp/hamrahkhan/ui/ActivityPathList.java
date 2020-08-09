package com.bp.hamrahkhan.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

import com.bp.hamrahkhan.R;

public class ActivityPathList extends AppCompatActivity {

    Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_list);
        toolbar= findViewById(R.id.toolbar);
        setActionBar(toolbar);

        RecyclerView rv_path=findViewById(R.id.rv_path_list);



    }
}