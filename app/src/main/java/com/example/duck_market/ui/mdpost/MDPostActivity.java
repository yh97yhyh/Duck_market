package com.example.duck_market.ui.mdpost;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.duck_market.DataManger;
import com.example.duck_market.Entity.Merchandise;
import com.example.duck_market.R;

import org.w3c.dom.Text;

public class MDPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdpost);
        Merchandise mymd=DataManger.getInstance().getMerchandise();

        TextView mdtitle=(TextView)findViewById(R.id.md_name);
        TextView mdprice=(TextView)findViewById(R.id.md_price);
        TextView mdconent=(TextView)findViewById(R.id.md_content);

        mdtitle.setText(mymd.getName());
        mdprice.setText(String.valueOf(mymd.getPrice())+"원");
        mdconent.setText(mymd.getDescription());


    }


    int myLike = 0; //좋아요 여부
    float rate = 0; //별점

    // 별점
    public void likebuttonClicked(View v) {
//        ImageView imageview = null;
//        imageview = (ImageView)findViewById(R.id.like_button);
//        Drawable drawable_myState = imageview.getDrawable();
//        Bitmap myState = ((BitmapDrawable)drawable_myState).getBitmap();
//        Bitmap likeState = BitmapFactory.decodeResource(getResources(), R.drawable.ic_star2);

        if(myLike == 1) { //좋아요 한 상태
            ImageView imagevew = null;
            imagevew = (ImageView)findViewById(R.id.like_button);
            imagevew.setImageResource(R.drawable.ic_star);
            myLike = 0;
            rate = 0;
        }
        else //좋아요 안 한 상태일 때
            showRatingbarDialog();
    }

    public void showRatingbarDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final RatingBar ratingBar = new RatingBar(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        ratingBar.setLayoutParams(lp);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(0.5f);

        linearLayout.addView(ratingBar);
        builder.setView(linearLayout);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println("Rated val:"+v);
                rate = v;
            }
        });

        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        ImageView imageview = null;
                        imageview = (ImageView)findViewById(R.id.like_button);
                        imageview.setImageResource(R.drawable.ic_star2);
                        if(rate > 0)
                            myLike = 1;
                        dialogInterface.dismiss();
                    }
                });

        builder.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });

        builder.create();
        builder.show();
    }

}