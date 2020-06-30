package com.example.duck_market.ui.Message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duck_market.R;
import com.example.duck_market.ui.review.ReviewWrite;

public class Activity_chatting extends AppCompatActivity {

    ListView m_ListView;
    CustomAdapter m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        Intent intent = new Intent(this.getIntent());


        // 커스텀 어댑터 생성
        m_Adapter = new CustomAdapter();

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listView1);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        m_Adapter.add("이건 뭐지", 1);
        m_Adapter.add("쿨쿨", 1);
        m_Adapter.add("쿨쿨쿨쿨", 0);
        m_Adapter.add("재미있게", 1);
        m_Adapter.add("재미있게", 1);
        m_Adapter.add("재미있게", 0);
        m_Adapter.add("2015/11/20", 2);
        m_Adapter.add("재미있게", 1);
        m_Adapter.add("재미있게", 1);

        findViewById(R.id.btn_send).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.send_text);
                String inputValue = editText.getText().toString();
                editText.setText("");
                refresh(inputValue, 1); }
        }
        );
    }

    private void refresh(String inputValue, int _str) {
        m_Adapter.add(inputValue, _str);
        m_Adapter.notifyDataSetChanged();
    }

}

