package com.example.duck_market.ui.myshop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;

import com.example.duck_market.DataManger;
import com.example.duck_market.Entity.Merchandise;
import com.example.duck_market.Entity.User;
import com.example.duck_market.R;
import com.example.duck_market.RetrofitInterface;
import com.example.duck_market.ui.mdpost.MDPostActivity;
import com.example.duck_market.ui.review.Review_Activity;

import org.w3c.dom.Text;

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

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyShopFragment extends Fragment {
    View root;
    @SuppressLint("DefaultLocale")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_myshop, container, false);

        ImageView btn_set;
        btn_set = root.findViewById(R.id.setting_button);

        btn_set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = (Intent) new Intent(getActivity(), com.example.duck_market.ui.setting.SettingActivity.class);
                startActivity(intent);

            }
        });

        TextView btn_review = root.findViewById(R.id.review_setting);
        btn_review.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = (Intent) new Intent(getActivity(), Review_Activity.class);
                startActivity(intent);

            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-124-76-170.ap-northeast-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitInterface retrofitinterface = retrofit.create(RetrofitInterface.class);

        DataManger dataManger = DataManger.getInstance();
        User user = dataManger.getUser();

        Call<User> call = retrofitinterface.readUser(user.getId());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                TextView nick_tv = (TextView) root.findViewById(R.id.nickname);
                nick_tv.setText(user.getNickname());

                TextView shopName_tv = (TextView) root.findViewById(R.id.shop_name);
                shopName_tv.setText(user.getShopname());

                TextView shopInfo_tv = (TextView) root.findViewById(R.id.shop_info);
                shopInfo_tv.setText(user.getShopdes());


                Call<List<Merchandise>> call2 = retrofitinterface.readMerchandiseByUser(user.getUsername());

                call2.enqueue(new Callback<List<Merchandise>>() {
                    @Override
                    public void onResponse(Call<List<Merchandise>> call, Response<List<Merchandise>> response) {
                        List<Merchandise> submdlist = response.body();
                        final ArrayList<Merchandise> MDarry = new ArrayList<>();
                        ListView listView = (ListView)root.findViewById(R.id.my_md_listview);

                        for (Merchandise merchandise : submdlist) {
                            MDarry.add(merchandise);
                        }

                        ArrayAdapter<Merchandise> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,MDarry);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView parent, View view, int position, long id) {
                                Intent intent;

                                intent = (Intent)new Intent(getContext(), MDPostActivity.class);

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


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("err", t.toString());
            }
        });
        return  root;
    }











}