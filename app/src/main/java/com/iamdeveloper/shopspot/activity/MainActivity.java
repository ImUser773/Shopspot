package com.iamdeveloper.shopspot.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.iamdeveloper.shopspot.R;
import com.iamdeveloper.shopspot.adapter.RecycleAdapter;
import com.iamdeveloper.shopspot.model.ImageModel;
import com.iamdeveloper.shopspot.onClick.OnItemClick;
import com.iamdeveloper.shopspot.rest.ImageInterface;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private ArrayList<String> imageList = new ArrayList<String>();
    private static List<ImageModel.ResultsBean> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();




        if(savedInstanceState != null){
            Log.i(TAG, "not null");
        }else {
            Log.i(TAG, "null");
            getImageDataApi();

        }

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("KEY",imageList);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageList = savedInstanceState.getStringArrayList("KEY");
        if (imageList != null){
            recyclerViewConfig();

        }
    }

    private void bindView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

    }


    private void getImageDataApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ImageInterface ApiService = retrofit.create(ImageInterface.class);
        Call<ImageModel> apiCall = ApiService.getPosts();
        apiCall.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                int resSize = response.body().getResults().size();
                Log.i(TAG, String.valueOf(resSize));
                model = null;
                model = response.body().getResults();
                Log.i(TAG, String.valueOf("model " +model.size()));
                for (int i = 0; i < resSize; i++) {
                    imageList.add(response.body().getResults().get(i).getUrl());
                    Log.i(TAG, String.valueOf(imageList.get(i)));
                }
                recyclerViewConfig();


            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {
                Log.i(TAG, t.toString());
            }
        });
    }

    private void recyclerViewConfig() {
        adapter = new RecycleAdapter(imageList, this, new OnItemClick() {
            @Override
            public void onItemClick(View v, int position) {

                Log.i("name",model.size() +"");
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("image",model.get(position).getUrl());
                i.putExtra("name",model.get(position).getType());
                i.putExtra("date",model.get(position).getCreatedAt());
                startActivity(i);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

}
