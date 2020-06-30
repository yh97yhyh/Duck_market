package com.example.duck_market.ui.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.duck_market.R;
//-------------------------------------


public class Review_Activity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent intent = new Intent(this.getIntent());


        ImageButton btn_back;
        btn_back=(ImageButton) findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        Button wrt_review = (Button) findViewById(R.id.button2);
        wrt_review.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent;
                intent = (Intent)new Intent();
                intent = (Intent)new Intent(getApplicationContext(), ReviewWrite.class);
                startActivity(intent);

            }
        });

    }

}
