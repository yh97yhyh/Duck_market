package com.example.duck_market;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.duck_market.Entity.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginActivity extends AppCompatActivity {
    private Button btnRegist;
    private Button btn_Login;
    TextView textView_id;
    TextView textView_pw;

    String str_username , str_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView_id=(TextView) findViewById(R.id.textid);
        textView_pw=(TextView)  findViewById(R.id.textpw);

        btnRegist = (Button) findViewById(R.id.btnRegist);
        btnRegist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
                startActivity(intent);
            }
        });

        btn_Login = (Button) findViewById(R.id.btnLogin);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                str_username = textView_id.getText().toString();
                str_pw = textView_pw.getText().toString();


                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("username",str_username);
                hashMap.put("password",str_pw);

                login(hashMap);
            }
        });


    }

    public void login(HashMap<String, Object> parameters) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-124-76-170.ap-northeast-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitinterface = retrofit.create(RetrofitInterface.class);
        Call<User> call = retrofitinterface.loginUser(parameters);
        Call<User> call2 = retrofitinterface.loginUser(parameters);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    User user = response.body();

                    DataManger dataManger=DataManger.getInstance();
                    dataManger.setUser(user);

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), "존재하지 않는 ID이거나 비밀번호가 틀렸습니다.",Toast.LENGTH_LONG).show();;
                Log.d(TAG, "onFailure2: Something Wrong");
            }
        });
    }
    public String getStr_username()
    {
        return str_username;
    }


}
