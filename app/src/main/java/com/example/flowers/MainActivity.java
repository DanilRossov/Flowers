package com.example.flowers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView;

    List<Chveti> mFlowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mFlowers = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        Adapter adapter = new Adapter(mFlowers);
        mRecyclerView.setAdapter(adapter);

        mProgressBar.setVisibility(View.VISIBLE);

        FlowersAPI flowersAPI = FlowersAPI.retrofit.create(FlowersAPI.class);
        final Call<List<Chveti>> call = flowersAPI.getData();
        call.enqueue(new Callback<List<Chveti>>() {
                         @Override
                         public void onResponse(Call<List<Chveti>> call, Response<List<Chveti>> response) {
                             if (response.isSuccessful()) {
                                 mFlowers.addAll(response.body());
                                 mRecyclerView.getAdapter().notifyDataSetChanged();
                                 mProgressBar.setVisibility(View.INVISIBLE);
                             } else {
                                 // Обрабатываем ошибку
                                 ResponseBody errorBody = response.errorBody();
                                 try {
                                     Toast.makeText(MainActivity.this, errorBody.string(),
                                             Toast.LENGTH_SHORT).show();
                                     mProgressBar.setVisibility(View.INVISIBLE);
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Chveti>> call, Throwable throwable)  {
                             Toast.makeText(MainActivity.this, "Ошибка!!!! " + throwable.getMessage(),
                                     Toast.LENGTH_SHORT).show();
                             mProgressBar.setVisibility(View.INVISIBLE);
                         }
                     }
        );
    }
}