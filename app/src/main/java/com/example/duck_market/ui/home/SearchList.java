package com.example.duck_market.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duck_market.DataManger;
import com.example.duck_market.Entity.Merchandise;
import com.example.duck_market.R;
import com.example.duck_market.RetrofitInterface;
import com.example.duck_market.ui.mdpost.MDPostActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class SearchList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);


        DataManger dataManger = DataManger.getInstance();
        String searchword=dataManger.getSerarch_word();
        Log.d("searchword",searchword);

        /*
        HashMap<String,Object> hashMap=new HashMap<String, Object>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-124-76-170.ap-northeast-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final RetrofitInterface retrofitinterface = retrofit.create(RetrofitInterface.class);

        Call<List<Merchandise>> call=retrofitinterface.readMerchandiseByName(searchword,hashMap);

        call.enqueue(new Callback<List<Merchandise>>() {
            @Override
            public void onResponse(Call<List<Merchandise>> call, Response<List<Merchandise>> response) {
                List<Merchandise> searchmdlist = response.body();
                final ArrayList<Merchandise> MDarry = new ArrayList<>();
                ListView listView = (ListView)findViewById(R.id.search_list_view);

                for (Merchandise merchandise : searchmdlist) {
                    MDarry.add(merchandise);
                }

                ArrayAdapter<Merchandise> adapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,MDarry);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View view, int position, long id) {
                        Intent intent;

                        intent = (Intent)new Intent(getApplicationContext(), MDPostActivity.class);

                        Merchandise md=MDarry.get(position);
                        DataManger dm = DataManger.getInstance();
                        dm.setMerchandise(md);

                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Merchandise>> call, Throwable t) {

            }
        });

        */
    }
}
