package com.example.duck_market.ui.review;

import androidx.appcompat.app.AppCompatActivity;
import com.example.duck_market.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ReviewWrite extends AppCompatActivity {
    private EditText review_title;
    private EditText review_content;
    String title_str,content_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_write);

        //EditText
        review_title = (EditText)findViewById(R.id.review_title);
        review_content = (EditText)findViewById(R.id.review_content);

        ImageButton btn_back;
        btn_back=(ImageButton) findViewById(R.id.button_back_2);
        btn_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        //String으로 저장
        title_str =  review_title.getText().toString();
        content_str = review_content.getText().toString();
    }
}
