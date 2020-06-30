package com.example.duck_market.ui.setting;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duck_market.DataManger;
import com.example.duck_market.Entity.User;
import com.example.duck_market.R;
import com.example.duck_market.RetrofitInterface;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AlertDialog;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopInfoActivity extends AppCompatActivity {

    private ConstraintLayout shopNameChange;
    private EditText shopInfo;
    private TextView shopName,shopCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        shopNameChange = findViewById(R.id.shop_name_layout);

        shopNameChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSettingDialog();
            }
        });

        User user =DataManger.getInstance().getUser();

        shopName = findViewById(R.id.shop_name);
        shopInfo = findViewById(R.id.info_content);
        shopCount = findViewById(R.id.text_count);
        shopName.setText(user.getShopname());
        shopInfo.setText(user.getShopdes());


        shopInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = shopInfo.getText().toString();
                shopCount.setText(String.format(("%d/1000"),input.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void showSettingDialog(){

        final EditText edittext = new EditText(this);

        User user =DataManger.getInstance().getUser();
        edittext.setText(user.getShopname());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("상점명 변경");
        builder.setView(edittext);



        builder.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView userShopName = findViewById(R.id.shop_name);
                        userShopName.setText(edittext.getText().toString());

                        final HashMap<String,Object> hashMap=new HashMap<>();
                        final DataManger dataManger=DataManger.getInstance();

                        User user =dataManger.getUser();
                        hashMap.put("id",user.getId());
                        hashMap.put("username",user.getUsername());
                        hashMap.put("password",user.getPassword());
                        hashMap.put("nickname",user.getNickname());
                        hashMap.put("shopdname",edittext.getText().toString());//여기만 변경
                        hashMap.put("shopdes",user.getShopdes());
                        hashMap.put("phone",user.getPhone());


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://ec2-13-124-76-170.ap-northeast-2.compute.amazonaws.com:8080")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        RetrofitInterface retrofitinterface = retrofit.create(RetrofitInterface.class);
                        Call<User> call=retrofitinterface.updateUser(hashMap, user.getId());

                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                dataManger.setUser(response.body());
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.d("err",t.toString());
                            }
                        });



                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();

    }
}
