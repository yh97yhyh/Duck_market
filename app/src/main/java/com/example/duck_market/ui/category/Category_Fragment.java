package com.example.duck_market.ui.category;


import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duck_market.DataManger;
import com.example.duck_market.Entity.Merchandise;
import com.example.duck_market.R;
import com.example.duck_market.RetrofitInterface;
import com.example.duck_market.ui.setting.ShopInfoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class Category_Fragment extends Fragment {
    private View root;
    private ExpandableListView listView;
    ArrayList<MyGroup> DataList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_category, container, false);


        DataList = new ArrayList<MyGroup>();
        listView = (ExpandableListView) root.findViewById(R.id.mylist);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-124-76-170.ap-northeast-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final RetrofitInterface retrofitinterface = retrofit.create(RetrofitInterface.class);

        Call<List<String>> call = retrofitinterface.readByMajor();

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> majornamelist = response.body();
                for (int i = 0; i < majornamelist.size(); i++) {
                    String major = majornamelist.get(i);
                    final MyGroup group = new MyGroup(major);

                    DataList.add(group);

                    Call<List<String>> call2 = retrofitinterface.readSubByMajor(major);
                    ;
                    call2.enqueue(new Callback<List<String>>() {
                        @Override
                        public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                            List<String> sublist = response.body();
                            group.setChild((ArrayList<String>) sublist);

                        }

                        @Override
                        public void onFailure(Call<List<String>> call, Throwable t) {

                        }
                    });
                }


                //Log.d("major", String.valueOf(DataList.size()));
                final ExpandAdapter adapter = new ExpandAdapter(getContext(), R.layout.group_row, R.layout.child_row, DataList);
                listView.setIndicatorBounds(0, 111110); //이 코드를 지우면 화살표 위치가 바뀐다.
                listView.setAdapter(adapter);


                // 차일드 클릭 했을 경우 이벤트
                listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {

                        MyGroup group = (MyGroup) parent.getExpandableListAdapter().getGroup(groupPosition);

                        final String groupName = group.getGroupName();
                        String childName = group.getChild().get(childPosition);

                        //Log.d("group", groupName);
                        //Log.d("child", childName);

                        DataManger dataManger=DataManger.getInstance();
                        dataManger.setMajorcate(groupName);
                        dataManger.setSubcate(childName);

                        Intent intent = new Intent(getContext(), PostList.class);
                        startActivity(intent);



                        return false;
                }
            });


            // 그룹이 열릴 경우 이벤트
                listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()

            {
                @Override
                public void onGroupExpand ( int groupPosition){
                int groupCount = adapter.getGroupCount();
                // 한 그룹을 클릭하면 나머지 그룹들은 닫힌다.
                for (int i = 0; i < groupCount; i++) {
                    if (!(i == groupPosition))
                        listView.collapseGroup(i);
                }
            }
            });

                return;
        }

        @Override
        public void onFailure (Call < List < String >> call, Throwable t){
            Log.d("Fail", t.toString());
        }
    } );





        return root;
}

}
