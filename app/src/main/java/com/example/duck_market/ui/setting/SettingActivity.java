package com.example.duck_market.ui.setting;

import androidx.appcompat.app.AppCompatActivity;
import com.example.duck_market.R;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SettingActivity extends AppCompatActivity {

    final Context context = this;
    private Boolean isName = true;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ConstraintLayout shopSettingLayout, userNameChange, userPhoneChange, keywordSettingLayout;

        //상점소개
        shopSettingLayout = findViewById(R.id.shop_setting_layout);
        shopSettingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShopInfoActivity.class);
                startActivity(intent);
            }
        });

        //계정 설정
        userNameChange = findViewById(R.id.user_name_layout);
        userNameChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isName = true;
                showSettingDialog();
            }
        });


        /*
        if(//전화번호가 없으면 ){
            TextView userPhone = findViewById(R.id.user_phone);
            userPhone.setVisibility(View.GONE);
            TextView noPhone = findViewById(R.id.no_user_phone);
            noPhone.setVisibility(View.VISIBLE);
        }
         */

        userPhoneChange = findViewById(R.id.user_phone_layout);
        userPhoneChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isName = false;
                showSettingDialog();
            }
        });



        //알림 설정
        keywordSettingLayout = findViewById(R.id.keyword_setting);
        TextView t= keywordSettingLayout.findViewById(R.id.title);
        t.setText("키워드 세팅");

    }

    private void showSettingDialog(){
        final EditText edittext = new EditText(this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(isName)
            builder.setTitle("닉네임 변경");
        else
            builder.setTitle("연락처 변경");

        builder.setView(edittext);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(isName){

                            TextView userName = findViewById(R.id.user_name);
                            userName.setText(edittext.getText().toString());
                        }else{
                            TextView userPhone = findViewById(R.id.user_phone);
                            userPhone.setText(edittext.getText().toString());
                        }

                    }
                });
        builder.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();

    }
}
