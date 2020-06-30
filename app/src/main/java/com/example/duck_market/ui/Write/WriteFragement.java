package com.example.duck_market.ui.Write;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.widget.AdapterView;
import android.widget.Toast;

import com.example.duck_market.DataManger;
import com.example.duck_market.Entity.Merchandise;
import com.example.duck_market.Entity.SubCategory;
import com.example.duck_market.R;
import com.example.duck_market.RetrofitInterface;
import com.example.duck_market.ui.category.ExpandAdapter;
import com.example.duck_market.ui.category.MyGroup;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class WriteFragement extends Fragment {
    EditText title, price, content;
    View root;
    public String str_title, str_content, str_idol, str_submenu;
    int price_int;
    public String str_username;
    private ImageView imageView;
    byte[] b_image = null;
    Uri image_uri = null;
    public ArrayList<String> mainList;
    ArrayList<String> subList;

    private static final int PICK_IMAGE = 1111;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_write, container, false);


        //갤러리 버튼 클릭 이벤트 처리
        ImageButton btn_galley;
        imageView = (ImageView) root.findViewById(R.id.gallery_imageview);
        btn_galley = (ImageButton) root.findViewById(R.id.btnGallery);
        btn_galley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetImageFromAlbum();
            }
        });

        //Spinner에 major 카테고리 추가
        mainList = new ArrayList<>();
        subList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-124-76-170.ap-northeast-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitInterface retrofitinterface = retrofit.create(RetrofitInterface.class);

        Call<List<String>> call = retrofitinterface.readByMajor();

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(final Call<List<String>> call, Response<List<String>> response) {
                List<String> majornamelist = response.body();
                for (int i = 0; i < majornamelist.size(); i++) {
                    String major = majornamelist.get(i);
                    mainList.add(major);

                    Log.d("Major", major);
                }

                Log.d("list size", String.valueOf(mainList.size()));

                ArrayAdapter mainAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, mainList);
                Spinner main_spinner = (Spinner) root.findViewById(R.id.main_spinner);
                main_spinner.setAdapter(mainAdapter);

                main_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        str_idol = mainList.get(i);
                        Log.d("Select", str_idol);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        str_idol = mainList.get(0);
                        Log.d("Select", str_idol);

                    }
                });

                subList.add("concert");
                subList.add("Album");


                ArrayAdapter subAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, subList);
                Spinner sub_spinner = (Spinner) root.findViewById(R.id.sub_spinner);
                sub_spinner.setAdapter(subAdapter);

                sub_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        str_submenu = subList.get(i);
                        Log.d("Select", str_submenu);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        str_submenu = subList.get(0);
                        Log.d("Select", str_submenu);

                    }
                });


                Button btn_save;
                btn_save = (Button) root.findViewById(R.id.save_button);

                btn_save.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(View v) {

                        boolean full = true;
                        title = (EditText) root.findViewById(R.id.title_editText);
                        price = (EditText) root.findViewById(R.id.price_editText);
                        content = (EditText) root.findViewById(R.id.content_editText);

                        if (title.getText().toString().length() == 0) {
                            full = false;
                        } else {
                            str_title = title.getText().toString();
                        }
                        if (content.getText().toString().length() == 0) {
                            full = false;
                        } else {
                            str_content = content.getText().toString();
                        }
                        if (price.getText().toString().length() == 0) {
                            full = false;
                        } else {
                            price_int = Integer.parseInt(price.getText().toString());
                        }

                        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();

                        if (drawable == null) {
                            //이미지 선택X
                            full = false;
                        } else {
                            //사진 처리

                    /*
                    Bitmap bm = drawable.getBitmap();


                    Configuration config=getResources().getConfiguration();
                    if(config.smallestScreenWidthDp>=800)
                        bm = Bitmap.createScaledBitmap(bm, 400, 240, true);
                    else if(config.smallestScreenWidthDp>=600)
                        bm = Bitmap.createScaledBitmap(bm, 300, 180, true);
                    else if(config.smallestScreenWidthDp>=400)
                        bm = Bitmap.createScaledBitmap(bm, 200, 120, true);
                    else if(config.smallestScreenWidthDp>=360)
                        bm = Bitmap.createScaledBitmap(bm, 180, 108, true);
                    else
                        bm = Bitmap.createScaledBitmap(bm, 160, 96, true);

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    b_image = bos.toByteArray();

                    String image= Base64.encodeToString(b_image, Base64.DEFAULT);
                    String temp="";

                    try{
                        temp="&imagedevice="+ URLEncoder.encode(image,"utf-8");
                    }catch (Exception e){
                        Log.e("exception",e.toString());
                    }

                    */
                        }

                        if (full == true) {
                            Log.d("제목", str_title);
                            Log.d("가격", String.valueOf(price_int));
                            Log.d("내용", str_content);
                            Log.d("아이돌", str_idol);
                            Log.d("중분류", str_submenu);

                            HashMap<String, Object> hashMap = new HashMap<>();

                            hashMap.put("name", str_title);
                            hashMap.put("major", str_idol);
                            hashMap.put("sub", str_submenu);
                            hashMap.put("description", str_content);
                            hashMap.put("price", price_int);
                            DataManger dataManger = DataManger.getInstance();
                            hashMap.put("user", dataManger.getUser().getUsername());

                            Call<Merchandise> call2 = retrofitinterface.createMerchandise(hashMap);
                            call2.enqueue(new Callback<Merchandise>() {
                                @Override
                                public void onResponse(Call<Merchandise> call, Response<Merchandise> response) {
                                    if (response.isSuccessful()) {
                                        Merchandise merchandise = response.body();
                                        Log.d(TAG, "Success");
                                        Log.d(TAG,merchandise.toString());
                                        title.setText(null);
                                        price.setText("");
                                        content.setText(null);
                                        imageView.setImageDrawable(null);


                                    }
                                }

                                @Override
                                public void onFailure(Call<Merchandise> call, Throwable t) {
                                    Log.d(TAG, t.toString());
                                }

                            });

                        } else {
                            Toast.makeText(v.getContext(), "빈공간이 있습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("ERR", t.toString());
            }

        });


        return root;
    }


    private void GetImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }

            Uri uri = data.getData();

            try {
                InputStream in = getContext().getApplicationContext().getContentResolver().openInputStream(uri);
                Bitmap image = BitmapFactory.decodeStream(in);
                //imageView.setImageBitmap(image);


                imageView.setImageURI(uri);
                image_uri = uri;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}