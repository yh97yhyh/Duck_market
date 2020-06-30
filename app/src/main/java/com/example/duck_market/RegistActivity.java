package com.example.duck_market;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duck_market.Entity.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RegistActivity extends AppCompatActivity {

    private EditText textid;
    private EditText textpw;
    private EditText textpwCheck;
    private EditText textphoneNum;
    private EditText textNickname;
    private EditText textStorename;
    private Button btnDone;
    private Button btnCancel;

    String str_username,str_pw,str_nickname,str_shopname,str_phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);


        textid = (EditText)findViewById(R.id.textid);
        textpw = (EditText)findViewById(R.id.textpw);
        textpwCheck = (EditText)findViewById(R.id.textpwCheck);
        textphoneNum = (EditText)findViewById(R.id.textPhoneNumber);
        textNickname = (EditText)findViewById(R.id.textnickName);
        textStorename = (EditText)findViewById(R.id.textStoreName);



        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnDone = (Button)findViewById(R.id.btnDone);

        textpwCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = textpw.getText().toString();
                String confirm = textpwCheck.getText().toString();

                if(password.equals(confirm)){
                    textpw.setBackgroundColor(Color.GREEN);
                    textpwCheck.setBackgroundColor(Color.GREEN);
                } else{
                    textpw.setBackgroundColor(Color.RED);
                    textpwCheck.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnDone.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(textid.getText().toString().length()==0){
                    Toast.makeText(RegistActivity.this, "ID를 입력하세요.", Toast.LENGTH_SHORT).show();
                    textid.requestFocus();
                    return;
                }
                if( textpw.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    textpw.requestFocus();
                    return;
                }
                if( textpwCheck.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "비밀번호 확인을 입력하세요.", Toast.LENGTH_SHORT).show();
                    textpwCheck.requestFocus();
                    return;
                }
                if( textphoneNum.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "핸드폰 번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    textphoneNum.requestFocus();
                    return;
                }
                if( textNickname.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "닉네임을 입력하세요.", Toast.LENGTH_SHORT).show();
                    textNickname.requestFocus();
                    return;
                }
                if( textStorename.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "상점 이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    textStorename.requestFocus();
                    return;
                }


                if( !textpw.getText().toString().equals(textpwCheck.getText().toString()) ) {
                    Toast.makeText(RegistActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    textpw.setText("");
                    textpwCheck.setText("");
                    textpw.requestFocus();
                    return;
                }

                str_username = textid.getText().toString();
                str_pw = textpw.getText().toString();
                str_phone = textphoneNum.getText().toString();
                str_nickname=textNickname.getText().toString();
                str_shopname=textStorename.getText().toString();


                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("username",str_username);
                hashMap.put("password",str_pw);

                hashMap.put("phone",str_phone);
                hashMap.put("nickname",str_nickname);
                hashMap.put("shopname",str_shopname);
                hashMap.put("shopdes","우리 상점 소개합니다");

                register(hashMap);



/*
                Intent result = new Intent();
                result.putExtra("ID", textid.getText().toString());

                setResult(RESULT_OK, result);
                finish();
*/

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent,1000);
            }
        });
    }

    public void register(HashMap<String, Object> parameters) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-124-76-170.ap-northeast-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitinterface = retrofit.create(RetrofitInterface.class);
        //Log.d(TAG, "retrofit create success");
        Call<User> call = retrofitinterface.createUser(parameters);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Log.d(TAG, "onResponse1: Something sucess");

                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                } else {

                    Object object = response.errorBody();
                    Log.d(TAG, object.toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG,t.getMessage());
                Log.d(TAG, "onFailure1: Something Wrong");
            }
        });
    }
}